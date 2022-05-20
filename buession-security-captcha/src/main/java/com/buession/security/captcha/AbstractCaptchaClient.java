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
package com.buession.security.captcha;

import com.buession.httpclient.HttpClient;

/**
 * 行为验证 Client 抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractCaptchaClient implements CaptchaClient {

	/**
	 * 应用 ID
	 */
	private String appId;

	/**
	 * 密钥
	 */
	private String secretKey;

	/**
	 * 前端 JavaScript 库地址
	 */
	private String javascript;

	/**
	 * {@link HttpClient} 实例
	 */
	private HttpClient httpClient;

	/**
	 * 构造函数
	 */
	public AbstractCaptchaClient(){
	}

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		密钥
	 */
	public AbstractCaptchaClient(String appId, String secretKey){
		this.appId = appId;
		this.secretKey = secretKey;
	}

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 * @param httpClient
	 *        {@link HttpClient} 实例
	 */
	public AbstractCaptchaClient(String appId, String secretKey, HttpClient httpClient){
		this(appId, secretKey);
		this.httpClient = httpClient;
	}

	/**
	 * 返回应用 ID
	 *
	 * @return 应用 ID
	 */
	public String getAppId(){
		return appId;
	}

	/**
	 * 设置应用 ID
	 *
	 * @param appId
	 * 		应用 ID
	 */
	public void setAppId(String appId){
		this.appId = appId;
	}

	/**
	 * 返回私钥
	 *
	 * @return 私钥
	 */
	public String getSecretKey(){
		return secretKey;
	}

	/**
	 * 设置私钥
	 *
	 * @param secretKey
	 * 		私钥
	 */
	public void setSecretKey(String secretKey){
		this.secretKey = secretKey;
	}

	@Override
	public String getJavaScript(){
		return javascript;
	}

	@Override
	public void setJavaScript(String url){
		this.javascript = url;
	}

	public HttpClient getHttpClient(){
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient){
		this.httpClient = httpClient;
	}

}
