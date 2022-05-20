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

import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.geetest.api.AbstractGeetestClient;
import com.buession.security.captcha.core.DigestMode;
import com.buession.security.captcha.geetest.core.InitResult;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.geetest.core.RequestV4Data;
import com.buession.security.captcha.utils.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
			logger.debug("二次验证 正常模式, 请求参数：{}.", requestData);
		}
		RequestV4Data requestV4Data = (RequestV4Data) requestData;
		Map<String, Object> formData = new HashMap<>(7);

		formData.put("lot_number", requestV4Data.getLotNumber());
		formData.put("captcha_output", requestV4Data.getCaptchaOutput());
		formData.put("pass_token", requestV4Data.getPassToken());
		formData.put("gen_time", requestV4Data.getGenTime());

		// 生成签名
		// 生成签名使用标准的 hmac 算法，使用用户当前完成验证的流水号 lot_number 作为原始消息 message，使用客户验证私钥作为 key
		// 采用 sha256 散列算法将 message 和 key 进行单向散列生成最终的签名
		Digester digester = new Digester(DigestMode.HMAC_SHA256, appId);
		String signToken = digester.hex(requestV4Data.getLotNumber());

		formData.put("sign_token", signToken);

		Response response;
		try{
			response = httpClient.post(VALIDATE_URL + "?captcha_id=" + appId, formData);

			if(logger.isDebugEnabled()){
				logger.debug("Validate response: {}", response);
			}

			EnhencedResult enhencedResult = OBJECT_MAPPER.readValue(response.getBody(), EnhencedResult.class);

			if("success".equals(enhencedResult.getResult())){
				return Status.SUCCESS;
			}else{
				throw new CaptchaException(enhencedResult.getReason());
			}
		}catch(Exception e){
			logger.error("Validate failure: {}", e.getMessage());
			throw new CaptchaException(e.getMessage());
		}
	}

	@Override
	public String getVersion(){
		return "v4";
	}

	private final class EnhencedResult implements Serializable {

		private final static long serialVersionUID = 402465840048648582L;

		private String result;

		private String reason;

		public String getResult(){
			return result;
		}

		public void setResult(String result){
			this.result = result;
		}

		public String getReason(){
			return reason;
		}

		public void setReason(String reason){
			this.reason = reason;
		}
	}

}
