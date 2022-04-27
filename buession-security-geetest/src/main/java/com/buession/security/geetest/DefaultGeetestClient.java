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
package com.buession.security.geetest;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.httpclient.HttpClient;
import com.buession.lang.Status;
import com.buession.security.geetest.api.v3.GeetestV3Client;
import com.buession.security.geetest.api.v4.GeetestV4Client;
import com.buession.security.geetest.core.DigestMode;
import com.buession.security.geetest.core.InitResult;
import com.buession.security.geetest.core.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认极验 Client
 *
 * @author Yong.Teng
 */
public class DefaultGeetestClient implements GeetestClient {

	private HttpClient httpClient;

	private GeetestClient geetestClient;

	private final static Logger logger = LoggerFactory.getLogger(DefaultGeetestClient.class);

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 *
	 * @throws GeetestException
	 * 		初始化异常
	 */
	public DefaultGeetestClient(String geetestId, String geetestKey) throws GeetestException{
		this.geetestClient = new GeetestV4Client(geetestId, geetestKey);
	}

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 * @param version
	 * 		版本
	 *
	 * @throws GeetestException
	 * 		初始化异常
	 */
	public DefaultGeetestClient(String geetestId, String geetestKey, String version) throws GeetestException{
		Assert.isBlank(version, "Version cloud empty or null.");
		if(StringUtils.endsWithIgnoreCase(version, "v3")){
			this.geetestClient = new GeetestV3Client(geetestId, geetestKey);
		}else if(StringUtils.endsWithIgnoreCase(version, "v4")){
			this.geetestClient = new GeetestV4Client(geetestId, geetestKey);
		}else{
			throw new GeetestException("Not support version: " + version);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 *
	 * @throws GeetestException
	 * 		初始化异常
	 */
	public DefaultGeetestClient(String geetestId, String geetestKey, HttpClient httpClient) throws GeetestException{
		this(geetestId, geetestKey);
		setHttpClient(httpClient);
	}

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 * @param version
	 * 		版本
	 *
	 * @throws GeetestException
	 * 		初始化异常
	 */
	public DefaultGeetestClient(String geetestId, String geetestKey, String version, HttpClient httpClient) throws GeetestException{
		this(geetestId, geetestKey, version);
		setHttpClient(httpClient);
	}

	/**
	 * 获取 Http Client
	 *
	 * @return Http Client
	 */
	public HttpClient getHttpClient(){
		return httpClient;
	}

	/**
	 * 设置 Http Client
	 *
	 * @param httpClient
	 * 		Http Client
	 */
	public void setHttpClient(HttpClient httpClient){
		Assert.isNull(httpClient, "HttpClient cloud not be null.");
		this.httpClient = httpClient;
	}

	@Override
	public InitResult initialize(DigestMode digestMode, RequestData requestData) throws GeetestException{
		return geetestClient.initialize(digestMode, requestData);
	}

	@Override
	public Status validate(RequestData requestData) throws GeetestException{
		return geetestClient.validate(requestData);
	}

	@Override
	public String getVersion(){
		return geetestClient.getVersion();
	}

}
