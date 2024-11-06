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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.geetest.api.v4;

import com.buession.core.builder.MapBuilder;
import com.buession.core.utils.Assert;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.RequestException;
import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.CaptchaValidateFailureException;
import com.buession.security.captcha.core.RequiredParameterCaptchaException;
import com.buession.security.captcha.geetest.api.AbstractGeetestClient;
import com.buession.security.captcha.core.InitResponse;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.utils.ResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 极验行为验证 V4 版本 Client
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeetestV4Client extends AbstractGeetestClient {

	private final static String VALIDATE_URL = "https://gcaptcha4.geetest.com/validate";

	private final static Logger logger = LoggerFactory.getLogger(GeetestV4Client.class);

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 */
	public GeetestV4Client(final String appId, final String secretKey) {
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
	public GeetestV4Client(final String appId, final String secretKey, final HttpClient httpClient) {
		super(appId, secretKey, httpClient);
	}

	@Override
	public InitResponse initialize(RequestData requestData) {
		if(logger.isDebugEnabled()){
			logger.debug("验证初始化");
		}

		return null;
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException {
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		GeetestV4RequestData requestV4Data = (GeetestV4RequestData) requestData;
		if(checkParam(requestV4Data) == false){
			return Status.FAILURE;
		}

		GeetestV4ParametersBuilder parametersBuilder = new GeetestV4ParametersBuilder(appId, secretKey, getSdkName());
		Map<String, Object> parameters = new HashMap<>(parametersBuilder.build(requestV4Data));
		EncodedFormRequestBody requestBody = new EncodedFormRequestBody();

		parameters.forEach((key, value)->requestBody.addRequestBodyElement(key, value.toString()));

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", requestBody);
		}

		Response response = null;
		try{
			response = getHttpClient().post(VALIDATE_URL, requestBody, MapBuilder.of("captcha_id", appId),
					getHeaders());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			GeetestV4ValidateResponse resp = parseObject(response.getBody(), GeetestV4ValidateResponse.class);

			if("success".equals(resp.getResult())){
				return Status.SUCCESS;
			}else{
				logger.error("二次验证失败: {}", resp);
				throw new CaptchaValidateFailureException(resp.getCode(), resp.getMsg(), resp.getReason());
			}
		}catch(RequestException e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage());
		}catch(IOException e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage());
		}finally{
			ResponseUtils.close(response);
		}
	}

	@Override
	public String getVersion() {
		return "v4";
	}

	/**
	 * 检查客户端的请求是否合法，只要有一个为空，则判断不合法
	 *
	 * @param requestData
	 *        {@link GeetestV4RequestData}
	 *
	 * @return 检测结果
	 */
	private static boolean checkParam(final GeetestV4RequestData requestData)
			throws RequiredParameterCaptchaException {
		Assert.isBlank(requestData.getLotNumber(), ()->new RequiredParameterCaptchaException("lot_number"));
		Assert.isBlank(requestData.getCaptchaOutput(), ()->new RequiredParameterCaptchaException("captcha_output"));
		Assert.isBlank(requestData.getPassToken(), ()->new RequiredParameterCaptchaException("pass_token"));
		Assert.isBlank(requestData.getGenTime(), ()->new RequiredParameterCaptchaException("gen_time"));

		return true;
	}

}
