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
package com.buession.security.geetest.api;

import com.buession.core.utils.VersionUtils;
import com.buession.httpclient.HttpClient;
import com.buession.security.geetest.GeetestClient;
import com.buession.security.geetest.api.v3.GeetestV3Client;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 极验 API Client 抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractGeetestClient implements GeetestClient {

	protected final static String SDK_NAME = "Geetest-Java-SDK-" + GeetestV3Client.class.getPackage().getName() + "/" + VersionUtils.determineClassVersion(GeetestV3Client.class);

	protected final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 公钥
	 */
	protected final String geetestId;

	/**
	 * 私钥
	 */
	protected final String geetestKey;

	protected HttpClient httpClient;

	static{
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 */
	public AbstractGeetestClient(final String geetestId, final String geetestKey){
		this.geetestId = geetestId;
		this.geetestKey = geetestKey;
	}

	public HttpClient getHttpClient(){
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient){
		this.httpClient = httpClient;
	}
}
