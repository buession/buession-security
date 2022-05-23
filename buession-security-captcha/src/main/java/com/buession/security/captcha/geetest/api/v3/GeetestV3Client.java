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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.geetest.api.v3;

import com.buession.core.builder.MapBuilder;
import com.buession.core.id.SimpleIdGenerator;
import com.buession.core.utils.StatusUtils;
import com.buession.core.validator.Validate;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.RequiredParameterCaptchaException;
import com.buession.security.captcha.geetest.api.AbstractGeetestClient;
import com.buession.security.captcha.core.DigestMode;
import com.buession.security.captcha.core.InitResult;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.utils.Digester;
import com.buession.security.captcha.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 极验行为验证 V3 版本 Client
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeetestV3Client extends AbstractGeetestClient {

	private final static String REGISTER_URL = "https://api.geetest.com/register.php";

	private final static String VALIDATE_URL = "https://api.geetest.com/validate.php";

	private final static String JSON_FORMAT = "1";

	private final static Logger logger = LoggerFactory.getLogger(GeetestV3Client.class);

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 */
	public GeetestV3Client(final String appId, final String secretKey){
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
	public GeetestV3Client(final String appId, final String secretKey, final HttpClient httpClient){
		super(appId, secretKey, httpClient);
	}

	@Override
	public InitResult initialize(DigestMode digestMode, RequestData requestData){
		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, DigestMode: {}", digestMode);
		}

		if(digestMode == null){
			digestMode = DigestMode.MD5;
		}

		GeetestV3RequestData requestV3Data = (GeetestV3RequestData) requestData;
		MapBuilder<String, Object> parametersBuilder = MapBuilder.<String, Object>create()
				.put("gt", appId)
				.put("json_format", JSON_FORMAT)
				.put("digestmod", digestMode.getName())
				.put("sdk", getSdkName());

		if(requestV3Data.getClientType() != null){
			parametersBuilder.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(requestV3Data.getIpAddress() != null){
			parametersBuilder.put("ip_address", requestV3Data.getIpAddress());
		}

		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, parameters：{}.", parametersBuilder.build());
		}

		GeetestV3InitResult initResult;
		try{
			Response response = httpClient.get(REGISTER_URL, parametersBuilder.build());

			initResult = ObjectMapperUtils.createObjectMapper()
					.readValue(response.getBody(), GeetestV3InitResult.class);

			if(logger.isInfoEnabled()){
				logger.info("register api return data: {}", initResult);
			}
		}catch(Exception e){
			logger.error("验证初始化失败: {}", e.getMessage());
			initResult = new GeetestV3InitResult();
		}

		initResult.setSuccess(true);
		initResult.setNewCaptcha(true);

		if(Validate.isBlank(initResult.getChallenge()) || "0".equals(initResult.getChallenge())){
			initResult.setSuccess(false);
			initResult.setChallenge(new SimpleIdGenerator().nextId());
		}else{
			initResult.setGt(appId);

			Digester digester = new Digester(digestMode, secretKey);
			initResult.setChallenge(digester.hex(initResult.getChallenge()));
		}

		return initResult;
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		GeetestV3RequestData requestV3Data = (GeetestV3RequestData) requestData;
		if(checkParam(requestV3Data) == false){
			return Status.FAILURE;
		}

		MapBuilder<String, Object> formDataBuilder = MapBuilder.<String, Object>create()
				.put("captchaid", appId)
				.put("challenge", requestV3Data.getChallenge())
				.put("validate", requestV3Data.getValidate())
				.put("seccode", requestV3Data.getSeccode())
				.put("json_format", JSON_FORMAT)
				.put("sdk", getSdkName());

		if(requestV3Data.getUserId() != null){
			formDataBuilder.put("user_id", requestV3Data.getUserId());
		}

		if(requestV3Data.getClientType() != null){
			formDataBuilder.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(requestV3Data.getIpAddress() != null){
			formDataBuilder.put("ip_address", requestV3Data.getIpAddress());
		}

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", formDataBuilder.build());
		}

		Response response;
		try{
			response = httpClient.post(VALIDATE_URL, formDataBuilder.build());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			GeetestV3EnhencedResult result = ObjectMapperUtils.createObjectMapper().readValue(response.getBody(),
					GeetestV3EnhencedResult.class);
			return StatusUtils.valueOf(result != null && Validate.hasText(result.getSeccode()));
		}catch(Exception e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage(), e);
		}
	}

	@Override
	public String getVersion(){
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
			throws RequiredParameterCaptchaException{
		if(Validate.hasText(requestData.getChallenge()) == false){
			throw new RequiredParameterCaptchaException("challenge");
		}

		if(Validate.hasText(requestData.getValidate()) == false){
			throw new RequiredParameterCaptchaException("validate");
		}

		if(Validate.hasText(requestData.getSeccode()) == false){
			throw new RequiredParameterCaptchaException("seccode");
		}

		return true;
	}

}
