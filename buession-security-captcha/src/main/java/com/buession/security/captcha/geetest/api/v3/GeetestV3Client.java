/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.geetest.api.v3;

import com.buession.core.builder.MapBuilder;
import com.buession.core.id.SimpleIdGenerator;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.CaptchaValidateFailureException;
import com.buession.security.captcha.core.RequiredParameterCaptchaException;
import com.buession.security.captcha.geetest.api.AbstractGeetestClient;
import com.buession.security.captcha.core.InitResponse;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.mcrypt.Algo;
import com.buession.security.mcrypt.MD5Mcrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 极验行为验证 V3 版本 Client
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeetestV3Client extends AbstractGeetestClient {

	private final static String REGISTER_URL = "https://api.geetest.com/register.php";

	private final static String VALIDATE_URL = "https://api.geetest.com/validate.php";

	private final static Logger logger = LoggerFactory.getLogger(GeetestV3Client.class);

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 */
	public GeetestV3Client(final String appId, final String secretKey) {
		super(appId, secretKey);
	}

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 * @param httpClient
	 *        {@link HttpClient}
	 */
	public GeetestV3Client(final String appId, final String secretKey, final HttpClient httpClient) {
		super(appId, secretKey, httpClient);
	}

	@Override
	public InitResponse initialize(RequestData requestData) {
		if(logger.isDebugEnabled()){
			logger.debug("验证初始化");
		}

		GeetestV3RequestData requestV3Data = (GeetestV3RequestData) requestData;
		MapBuilder<String, Object> parametersBuilder = MapBuilder.<String, Object>create(4)
				.put("gt", appId)
				.put("json_format", "1")
				.put("digestmod", Algo.MD5.getName())
				.put("sdk", getSdkName())
				.putIfPresent("ip_address", requestV3Data.getIpAddress());

		if(requestV3Data.getClientType() != null){
			parametersBuilder.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, parameters：{}.", parametersBuilder.build());
		}

		GeetestV3InitResponse initResult;
		try{
			Response response = getHttpClient().get(REGISTER_URL, parametersBuilder.build());

			initResult = parseObject(response.getBody(), GeetestV3InitResponse.class);

			if(logger.isInfoEnabled()){
				logger.info("register api return data: {}", initResult);
			}
		}catch(Exception e){
			logger.error("验证初始化失败: {}", e.getMessage());
			initResult = new GeetestV3InitResponse();
		}

		initResult.setSuccess(true);
		initResult.setNewCaptcha(true);

		if(Validate.isBlank(initResult.getChallenge()) || "0".equals(initResult.getChallenge())){
			initResult.setSuccess(false);
			initResult.setChallenge(new SimpleIdGenerator().nextId());
		}else{
			initResult.setGt(appId);
			initResult.setChallenge(sign(initResult));
		}

		return initResult;
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException {
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		GeetestV3RequestData requestV3Data = (GeetestV3RequestData) requestData;
		if(checkParam(requestV3Data) == false){
			return Status.FAILURE;
		}

		GeetestV3ParametersBuilder parametersBuilder = new GeetestV3ParametersBuilder(appId, secretKey, getSdkName());
		Map<String, Object> parameters = new HashMap<>(parametersBuilder.build(requestV3Data));

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", parameters);
		}

		Response response;
		try{
			response = getHttpClient().post(VALIDATE_URL, parameters, getHeaders());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			GeetestV3ValidateResponse resp = parseObject(response.getBody(), GeetestV3ValidateResponse.class);
			if("false".equals(resp.getSeccode())){
				logger.error("二次验证失败: {}", resp);
				throw new CaptchaValidateFailureException(null, null);
			}else{
				return Status.SUCCESS;
			}
		}catch(Exception e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage(), e);
		}
	}

	@Override
	public String getVersion() {
		return "v3";
	}

	/**
	 * 检查客户端的请求是否合法，只要有一个为空，则判断不合法
	 *
	 * @param requestData
	 *        {@link GeetestV3RequestData}
	 *
	 * @return 检测结果
	 */
	private static boolean checkParam(final GeetestV3RequestData requestData)
			throws RequiredParameterCaptchaException {
		Assert.isBlank(requestData.getChallenge(), ()->new RequiredParameterCaptchaException("challenge"));
		Assert.isBlank(requestData.getValidate(), ()->new RequiredParameterCaptchaException("validate"));
		Assert.isBlank(requestData.getSeccode(), ()->new RequiredParameterCaptchaException("seccode"));

		return true;
	}

	/**
	 * 生成签名
	 * 生成签名使用标准的 hmac 算法，使用用户当前完成验证的流水号 lot_number 作为原始消息 message，使用客户验证私钥作为 key
	 * 采用 sha256 散列算法将 message 和 key 进行单向散列生成最终的签名
	 *
	 * @param initResponse
	 * 		初始化结果
	 *
	 * @return 生成签名结果
	 */
	private String sign(final GeetestV3InitResponse initResponse) {
		MD5Mcrypt md5Mcrypt = new MD5Mcrypt(StandardCharsets.UTF_8, secretKey);
		return md5Mcrypt.encode(initResponse.getChallenge());
	}

}
