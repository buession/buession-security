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

import com.buession.core.converter.Converter;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.security.mcrypt.HmacSha256Mcrypt;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
class RequestFormRequestBodyConverter implements Converter<GeetestV4RequestData, EncodedFormRequestBody> {

	private final String appId;

	private final String secretKey;

	private final String sdkName;

	RequestFormRequestBodyConverter(final String appId, final String secretKey, final String sdkName){
		this.appId = appId;
		this.secretKey = secretKey;
		this.sdkName = sdkName;
	}

	@Override
	public EncodedFormRequestBody convert(final GeetestV4RequestData requestData){
		final EncodedFormRequestBody requestBody = new EncodedFormRequestBody();

		requestBody.addRequestBodyElement("lot_number", requestData.getLotNumber());
		requestBody.addRequestBodyElement("captcha_output", requestData.getCaptchaOutput());
		requestBody.addRequestBodyElement("pass_token", requestData.getPassToken());
		requestBody.addRequestBodyElement("gen_time", requestData.getGenTime());
		requestBody.addRequestBodyElement("sign_token", sign(requestData));

		return requestBody;
	}

	/**
	 * 生成签名
	 * 生成签名使用标准的 hmac 算法，使用用户当前完成验证的流水号 lot_number 作为原始消息 message，使用客户验证私钥作为 key
	 * 采用 sha256 散列算法将 message 和 key 进行单向散列生成最终的签名
	 *
	 * @param requestData
	 * 		请求数据
	 *
	 * @return 生成签名结果
	 */
	private String sign(final GeetestV4RequestData requestData){
		HmacSha256Mcrypt hmacSha256Mcrypt = new HmacSha256Mcrypt(secretKey);
		return hmacSha256Mcrypt.encode(requestData.getLotNumber());
	}

}
