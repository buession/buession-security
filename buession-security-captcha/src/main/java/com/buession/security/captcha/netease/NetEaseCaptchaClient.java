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
package com.buession.security.captcha.netease;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.RequestBodyElement;
import com.buession.lang.Status;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.Manufacturer;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.mcrypt.MD5Mcrypt;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * 网易行为验证 Client，文档：<a href="https://support.dun.163.com/documents/15588062143475712?docId=66716791402057728" target="_blank">https://support.dun.163.com/documents/15588062143475712?docId=66716791402057728</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class NetEaseCaptchaClient extends AbstractCaptchaClient {

	public final static String VALIDATE_URL = "https://c.dun.163.com/api/v2/verify";

	/**
	 * 公钥
	 */
	protected final String appId;

	/**
	 * 私钥
	 */
	protected final String secretKey;

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		密钥
	 */
	public NetEaseCaptchaClient(final String appId, final String secretKey){
		Assert.isBlank(appId, "App Id cloud not be empty or null");
		Assert.isBlank(secretKey, "Secret Key cloud not be empty or null");
		this.appId = appId;
		this.secretKey = secretKey;
	}

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		密钥
	 * @param httpClient
	 *        {@link HttpClient} 实例
	 */
	public NetEaseCaptchaClient(final String appId, final String secretKey, final HttpClient httpClient){
		this(appId, secretKey);
		setHttpClient(httpClient);
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		long timestamp = System.currentTimeMillis();
		EncodedFormRequestBody body = new EncodedFormRequestBody();

		body.addRequestBodyElement(new RequestBodyElement("version", getVersion()));
		body.addRequestBodyElement(new RequestBodyElement("nonce", randomStr(timestamp)));
		body.addRequestBodyElement(new RequestBodyElement("timestamp", timestamp));
		body.addRequestBodyElement(new RequestBodyElement("secretId", appId));
		body.addRequestBodyElement(new RequestBodyElement("captchaId", getVersion()));
		body.addRequestBodyElement(new RequestBodyElement("validate", getVersion()));
		body.addRequestBodyElement(new RequestBodyElement("signature", getVersion()));

		return null;
	}

	@Override
	public Manufacturer getManufacturer(){
		return Manufacturer.NETEASE;
	}

	@Override
	public String getVersion(){
		return "v2";
	}

	private static String genSignature(String secretKey, Map<String, String> params){
		String[] paramNames = params.keySet().toArray(new String[]{});
		Arrays.sort(paramNames);

		StringBuilder sb = new StringBuilder();
		for(String name : paramNames){
			String value = ObjectUtils.defaultIfNull(params.get(name), StringUtils.EMPTY);
			sb.append(name).append(value);
		}

		sb.append(secretKey);

		MD5Mcrypt md5Mcrypt = new MD5Mcrypt();
		return md5Mcrypt.encode(sb.toString());
	}


}
