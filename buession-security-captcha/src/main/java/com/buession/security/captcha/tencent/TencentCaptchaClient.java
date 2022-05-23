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
package com.buession.security.captcha.tencent;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.httpclient.HttpClient;
import com.buession.lang.Status;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.DigestMode;
import com.buession.security.captcha.core.RequestData;

import java.util.Date;

/**
 * 腾讯云行为验证 Client，文档：<a href="https://cloud.tencent.com/document/product/1110/36334" target="_blank">https://cloud.tencent.com/document/product/1110/36334</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class TencentCaptchaClient extends AbstractCaptchaClient {

	public final static String VALIDATE_URL = "https://captcha.tencentcloudapi.com/";

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
	public TencentCaptchaClient(final String appId, final String secretKey){
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
	public TencentCaptchaClient(final String appId, final String secretKey, final HttpClient httpClient){
		this(appId, secretKey);
		setHttpClient(httpClient);
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		StringBuilder sb = new StringBuilder(VALIDATE_URL);

		/*
		sb.append('?');
		sb.append("Action=DescribeCaptchaResult");
		sb.append("&Version=").append(getVersion());
		sb.append("&CaptchaType=9");
		sb.append("&Ticket=").append(sdf.format(new Date()));
		sb.append("&UserIp=").append(requestData.getClientIp());
		sb.append("&Randstr=").append(randomStr());
		sb.append("&CaptchaAppId=").append(appId);
		sb.append("&AppSecretKey=").append(secretKey);
		sb.append("&BusinessId=").append();
		sb.append("&SceneId=").append();
		sb.append("&MacAddress=").append();
		sb.append("&Imei=").append();
		sb.append("&NeedGetCaptchaTime=1");

		 */
		return null;
	}

	@Override
	public String getVersion(){
		return "2019-07-22";
	}

}
