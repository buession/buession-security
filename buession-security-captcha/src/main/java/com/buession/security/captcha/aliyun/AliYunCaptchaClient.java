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
package com.buession.security.captcha.aliyun;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.Manufacturer;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.core.RequiredParameterCaptchaException;
import com.buession.security.captcha.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云行为验证 Client，文档：<a href="https://help.aliyun.com/document_detail/28309.html" target="_blank">https://help.aliyun.com/document_detail/28309.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliYunCaptchaClient extends AbstractCaptchaClient {

	/**
	 * AccessKey ID
	 */
	private final String accessKeyId;

	/**
	 * AccessKey Secret
	 */
	private final String accessKeySecret;

	/**
	 * 服务使用的 App Key
	 */
	private final String appKey;

	/**
	 * 端点
	 */
	private String endpoint = "https://afs.aliyuncs.com";

	private final static Logger logger = LoggerFactory.getLogger(AliYunCaptchaClient.class);

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		AccessKey ID
	 * @param accessKeySecret
	 * 		AccessKey Secret
	 * @param appKey
	 * 		服务使用的 App Key
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final String appKey){
		Assert.isBlank(accessKeyId, "AccessKeyId cloud not be empty or null");
		Assert.isBlank(accessKeySecret, "AccessKeySecret cloud not be empty or null");
		Assert.isBlank(appKey, "AppKey cloud not be empty or null");
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.appKey = appKey;
	}

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		AccessKey ID
	 * @param accessKeySecret
	 * 		AccessKey Secret
	 * @param appKey
	 * 		服务使用的 App Key
	 * @param httpClient
	 *        {@link HttpClient} 实例
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final String appKey,
							   final HttpClient httpClient){
		this(accessKeyId, accessKeySecret, appKey);
		setHttpClient(httpClient);
	}

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		AccessKey ID
	 * @param accessKeySecret
	 * 		AccessKey Secret
	 * @param appKey
	 * 		服务使用的 App Key
	 * @param regionId
	 * 		区域 ID
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final String appKey,
							   final String regionId){
		this(accessKeyId, accessKeySecret, appKey);

		Assert.isBlank(regionId, "RegionId cloud not be empty or null");

		endpoint = String.format("https://afs.%s.aliyuncs.com", regionId);
	}

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		AccessKey ID
	 * @param accessKeySecret
	 * 		AccessKey Secret
	 * @param appKey
	 * 		服务使用的 App Key
	 * @param regionId
	 * 		区域 ID
	 * @param httpClient
	 * 		Http Client
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final String appKey,
							   final String regionId, final HttpClient httpClient){
		this(accessKeyId, accessKeySecret, appKey, regionId);
		setHttpClient(httpClient);
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		AliYunRequestData aliYunRequestData = (AliYunRequestData) requestData;
		if(checkParam(aliYunRequestData) == false){
			return Status.FAILURE;
		}

		AliyunParametersBuilder parametersBuilder = new AliyunParametersBuilder(accessKeyId,
				accessKeySecret, appKey, this);
		Map<String, Object> parameters = new HashMap<>(parametersBuilder.build(aliYunRequestData));

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", parameters);
		}

		Response response;
		try{
			response = getHttpClient().get(endpoint, parameters, getHeaders());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			if(response.isSuccessful()){
				AliyunEnhencedResult result = ObjectMapperUtils.createObjectMapper().readValue(response.getBody(),
						AliyunEnhencedResult.class);

				if(result.getCode() == 100){
					return Status.SUCCESS;
				}else{
					throw new CaptchaException(
							"Validate failure: " + result.getMsg() + "(code: " + result.getCode() + ")");
				}
			}else{
				if(response.getStatusCode() == 400){
					throw new CaptchaException("Request parameter error");
				}

				return Status.FAILURE;
			}
		}catch(Exception e){
			logger.error("二次验证失败: {}", e.getMessage());
			throw new CaptchaException(e.getMessage(), e);
		}
	}

	@Override
	public Manufacturer getManufacturer(){
		return Manufacturer.ALIYUN;
	}

	@Override
	public String getVersion(){
		return "2018-01-12";
	}

	/**
	 * 返回端点
	 *
	 * @return 端点
	 */
	public String getEndpoint(){
		return endpoint;
	}

	/**
	 * 检查客户端的请求是否合法，只要有一个为空，则判断不合法
	 *
	 * @param requestData
	 *        {@link AliYunRequestData}
	 *
	 * @return 检测结果
	 */
	private static boolean checkParam(final AliYunRequestData requestData)
			throws RequiredParameterCaptchaException{
		if(Validate.hasText(requestData.getToken()) == false){
			throw new RequiredParameterCaptchaException("Token");
		}

		if(Validate.hasText(requestData.getSig()) == false){
			throw new RequiredParameterCaptchaException("Sig");
		}

		if(Validate.hasText(requestData.getSessionId()) == false){
			throw new RequiredParameterCaptchaException("SessionId");
		}

		if(Validate.hasText(requestData.getScene()) == false){
			throw new RequiredParameterCaptchaException("Scene");
		}

		return true;
	}

}
