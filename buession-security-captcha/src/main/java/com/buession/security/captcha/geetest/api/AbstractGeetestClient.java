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
package com.buession.security.captcha.geetest.api;

import com.buession.core.utils.VersionUtils;
import com.buession.httpclient.HttpClient;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.geetest.GeetestClient;

/**
 * 极验行为验证 API Client 抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractGeetestClient extends AbstractCaptchaClient implements GeetestClient {

	/**
	 * 公钥
	 */
	protected final String appId;

	/**
	 * 私钥
	 */
	protected final String secretKey;

	protected String sdkName = null;

	/**
	 * 构造函数
	 *
	 * @param appId
	 * 		应用 ID
	 * @param secretKey
	 * 		私钥
	 */
	public AbstractGeetestClient(final String appId, final String secretKey) {
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
	 *        {@link HttpClient}
	 */
	public AbstractGeetestClient(final String appId, final String secretKey, final HttpClient httpClient) {
		this(appId, secretKey);
		setHttpClient(httpClient);
	}

	protected String getSdkName() {
		if(sdkName == null){
			sdkName = "Geetest-Java-SDK-" + getClass().getName() + '/' + VersionUtils.determineClassVersion(getClass());
		}

		return sdkName;
	}

}
