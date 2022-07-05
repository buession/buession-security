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

import com.buession.core.utils.VersionUtils;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为验证 Client 抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractCaptchaClient implements CaptchaClient {

	protected final static String CLIENT_VERSION = VersionUtils.determineClassVersion(CaptchaClient.class);

	protected final static String USER_AGENT = "Buession Captcha Client For Java/" + CLIENT_VERSION;

	/**
	 * 前端 JavaScript 库地址
	 */
	private String[] javascript;

	/**
	 * {@link HttpClient} 实例
	 */
	private HttpClient httpClient;

	@Override
	public String[] getJavaScript(){
		return javascript;
	}

	@Override
	public void setJavaScript(String[] url){
		this.javascript = url;
	}

	public HttpClient getHttpClient(){
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient){
		this.httpClient = httpClient;
	}

	protected List<Header> getHeaders(){
		final List<Header> headers = new ArrayList<>();

		headers.add(new Header("User-Agent", USER_AGENT));
		headers.add(new Header("X-Sdk-Client", "java/" + CLIENT_VERSION));
		headers.add(new Header("X-Sdk-Invoke-Type", "normal"));

		return headers;
	}

}
