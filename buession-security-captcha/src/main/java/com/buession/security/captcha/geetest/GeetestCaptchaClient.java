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
package com.buession.security.captcha.geetest;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.httpclient.HttpClient;
import com.buession.lang.Status;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.geetest.api.v3.GeetestV3Client;
import com.buession.security.captcha.geetest.api.v4.GeetestV4Client;
import com.buession.security.captcha.core.InitResponse;

/**
 * 极验行为验证 Client，
 * 文档：第三代 <a href="https://www.geetest.com/Sensebot" target="_blank">https://www.geetest.com/Sensebot</a>、第四代 <a href="https://www.geetest.com/adaptive-captcha" target="_blank">https://www.geetest.com/adaptive-captcha</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestCaptchaClient extends AbstractCaptchaClient implements GeetestClient {

	private final GeetestClient geetestClient;

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		密钥
	 */
	public GeetestCaptchaClient(final String appId, final String secretKey){
		Assert.isBlank(appId, "App Id cloud not be empty or null");
		Assert.isBlank(secretKey, "Secret Key cloud not be empty or null");
		this.geetestClient = new GeetestV4Client(appId, secretKey);
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
	public GeetestCaptchaClient(final String appId, final String secretKey, final HttpClient httpClient){
		Assert.isBlank(appId, "App Id cloud not be empty or null");
		Assert.isBlank(secretKey, "Secret Key cloud not be empty or null");
		this.geetestClient = new GeetestV4Client(appId, secretKey, httpClient);
	}

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		密钥
	 * @param version
	 * 		版本
	 */
	public GeetestCaptchaClient(final String appId, final String secretKey, final String version){
		Assert.isBlank(appId, "App Id cloud not be empty or null");
		Assert.isBlank(secretKey, "Secret Key cloud not be empty or null");
		Assert.isBlank(version, "Version cloud empty or null.");

		if(StringUtils.equalsIgnoreCase(version, "v3")){
			this.geetestClient = new GeetestV3Client(appId, secretKey);
		}else if(StringUtils.equalsIgnoreCase(version, "v4")){
			this.geetestClient = new GeetestV4Client(appId, secretKey);
		}else{
			throw new CaptchaException("Unknown geetest version: " + version);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		密钥
	 * @param version
	 * 		版本
	 * @param httpClient
	 * 		Http Client
	 */
	public GeetestCaptchaClient(final String appId, final String secretKey, final String version,
								final HttpClient httpClient){
		this(appId, secretKey, version);
		setHttpClient(httpClient);
	}

	@Override
	public void setHttpClient(HttpClient httpClient){
		super.setHttpClient(httpClient);
		geetestClient.setHttpClient(httpClient);
	}

	@Override
	public InitResponse initialize(RequestData requestData){
		return geetestClient.initialize(requestData);
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		return geetestClient.validate(requestData);
	}

	@Override
	public String getVersion(){
		return geetestClient.getVersion();
	}

}
