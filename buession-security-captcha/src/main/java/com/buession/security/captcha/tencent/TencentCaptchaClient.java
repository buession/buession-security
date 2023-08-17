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
package com.buession.security.captcha.tencent;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.CaptchaValidateFailureException;
import com.buession.security.captcha.core.Manufacturer;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.core.RequiredParameterCaptchaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯云行为验证 Client，文档：<a href="https://007.qq.com/product.html" target="_blank">https://007.qq.com/product.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class TencentCaptchaClient extends AbstractCaptchaClient {

	private final static String VALIDATE_URL = "https://ssl.captcha.qq.com/ticket/verify";

	/**
	 * 密钥对中的 SecretId
	 */
	private final String secretId;

	/**
	 * 私钥
	 */
	private final String secretKey;

	private final static Logger logger = LoggerFactory.getLogger(TencentCaptchaClient.class);

	/**
	 * 构造函数
	 *
	 * @param secretId
	 * 		密钥对中的 SecretId
	 * @param secretKey
	 * 		原始的 SecretKey
	 */
	public TencentCaptchaClient(final String secretId, final String secretKey){
		Assert.isBlank(secretId, "Secret Id cloud not be empty or null");
		Assert.isBlank(secretKey, "Secret Key cloud not be empty or null");
		this.secretId = secretId;
		this.secretKey = secretKey;
	}

	/**
	 * 构造函数
	 *
	 * @param secretId
	 * 		密钥对中的 SecretId
	 * @param secretKey
	 * 		原始的 SecretKey
	 * @param httpClient
	 *        {@link HttpClient} 实例
	 */
	public TencentCaptchaClient(final String secretId, final String secretKey, final HttpClient httpClient){
		this(secretId, secretKey);
		setHttpClient(httpClient);
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		TencentRequestData tencentRequestData = (TencentRequestData) requestData;
		if(checkParam(tencentRequestData) == false){
			return Status.FAILURE;
		}

		TencentParametersBuilder parametersBuilder = new TencentParametersBuilder(secretId, secretKey);
		Map<String, Object> parameters = new HashMap<>(parametersBuilder.build(tencentRequestData));

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", parameters);
		}

		Response response;
		try{
			response = getHttpClient().get(VALIDATE_URL, parameters, getHeaders());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			TencentValidateResponse resp = parseObject(response.getBody(), TencentValidateResponse.class);

			if(resp.getResponse() == 1){
				return Status.SUCCESS;
			}else{
				logger.error("二次验证失败: {}", resp);
				throw new CaptchaValidateFailureException(Integer.toString(resp.getEvilLevel()), resp.getErrMsg(),
						resp.getErrMsg());
			}
		}catch(Exception e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage(), e);
		}
	}

	@Override
	public Manufacturer getManufacturer(){
		return Manufacturer.TENCENT;
	}

	@Override
	public String getVersion(){
		return "2019-07-22";
	}

	/**
	 * 检查客户端的请求是否合法，只要有一个为空，则判断不合法
	 *
	 * @param requestData
	 *        {@link TencentRequestData}
	 *
	 * @return 检测结果
	 */
	private static boolean checkParam(final TencentRequestData requestData)
			throws RequiredParameterCaptchaException{
		if(Validate.hasText(requestData.getTicket()) == false){
			throw new RequiredParameterCaptchaException("Ticket");
		}

		if(Validate.hasText(requestData.getRandstr()) == false){
			throw new RequiredParameterCaptchaException("Randstr");
		}

		return true;
	}

}
