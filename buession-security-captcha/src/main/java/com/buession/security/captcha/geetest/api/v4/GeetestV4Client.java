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
package com.buession.security.captcha.geetest.api.v4;

import com.buession.core.builder.MapBuilder;
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
 * 极验行为验证 V4 版本 Client
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeetestV4Client extends AbstractGeetestClient {

	public final static String VALIDATE_URL = "https://gcaptcha4.geetest.com/validate";

	private final static Logger logger = LoggerFactory.getLogger(GeetestV4Client.class);

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 */
	public GeetestV4Client(final String appId, final String secretKey){
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
	public GeetestV4Client(final String appId, final String secretKey, final HttpClient httpClient){
		super(appId, secretKey, httpClient);
	}

	@Override
	public InitResult initialize(DigestMode digestMode, RequestData requestData){
		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, DigestMode: {}", digestMode);
		}

		return null;
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		GeetestV4RequestData requestV4Data = (GeetestV4RequestData) requestData;
		if(checkParam(requestV4Data) == false){
			return Status.FAILURE;
		}

		MapBuilder<String, Object> formDataBuilder = MapBuilder.<String, Object>create()
				.put("lot_number", requestV4Data.getLotNumber())
				.put("captcha_output", requestV4Data.getCaptchaOutput())
				.put("pass_token", requestV4Data.getPassToken())
				.put("gen_time", requestV4Data.getGenTime());

		// 生成签名
		// 生成签名使用标准的 hmac 算法，使用用户当前完成验证的流水号 lot_number 作为原始消息 message，使用客户验证私钥作为 key
		// 采用 sha256 散列算法将 message 和 key 进行单向散列生成最终的签名
		Digester digester = new Digester(DigestMode.HMAC_SHA256, appId);
		String signToken = digester.hex(requestV4Data.getLotNumber());

		formDataBuilder.put("sign_token", signToken);

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", formDataBuilder.build());
		}

		Response response;
		try{
			response = httpClient.post(VALIDATE_URL + "?captcha_id=" + appId, formDataBuilder.build());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			GeetestV4EnhencedResult result = ObjectMapperUtils.createObjectMapper()
					.readValue(response.getBody(), GeetestV4EnhencedResult.class);

			if("success".equals(result.getResult())){
				return Status.SUCCESS;
			}else{
				throw new CaptchaException(result.getReason());
			}
		}catch(Exception e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage());
		}
	}

	@Override
	public String getVersion(){
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
			throws RequiredParameterCaptchaException{
		if(Validate.hasText(requestData.getLotNumber()) == false){
			throw new RequiredParameterCaptchaException("lot_number");
		}

		if(Validate.hasText(requestData.getCaptchaOutput()) == false){
			throw new RequiredParameterCaptchaException("captcha_output");
		}

		if(Validate.hasText(requestData.getPassToken()) == false){
			throw new RequiredParameterCaptchaException("pass_token");
		}

		if(Validate.hasText(requestData.getGenTime()) == false){
			throw new RequiredParameterCaptchaException("gen_time");
		}

		return true;
	}

}
