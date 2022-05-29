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

import com.buession.core.builder.MapBuilder;
import com.buession.core.utils.Assert;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.captcha.AbstractCaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.Manufacturer;
import com.buession.security.captcha.core.RequestData;
import com.buession.security.captcha.utils.ObjectMapperUtils;
import com.buession.security.mcrypt.Algo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 阿里云行为验证 Client，文档：<a href="https://help.aliyun.com/document_detail/28309.html" target="_blank">https://help.aliyun.com/document_detail/28309.html</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliYunCaptchaClient extends AbstractCaptchaClient {

	public final static String ACTION = "AuthenticateSig";

	public final static String FORMAT = "JSON";

	public final static String SIGNATURE_VERSION = "1.0";

	/**
	 * 密钥 ID
	 */
	private final String accessKeyId;

	/**
	 * 服务使用的 App Key
	 */
	private final String accessKeySecret;

	/**
	 * 端点
	 */
	private String endpoint = "https://afs.aliyuncs.com";

	private final static Logger logger = LoggerFactory.getLogger(AliYunCaptchaClient.class);

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		密钥 ID
	 * @param accessKeySecret
	 * 		服务使用的 App Key
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret){
		Assert.isBlank(accessKeyId, "AccessKeyId cloud not be empty or null");
		Assert.isBlank(accessKeySecret, "AccessKeySecret cloud not be empty or null");
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		密钥 ID
	 * @param accessKeySecret
	 * 		阿里云验证码配置对应的 App Key
	 * @param httpClient
	 *        {@link HttpClient} 实例
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final HttpClient httpClient){
		this(accessKeyId, accessKeySecret);
		setHttpClient(httpClient);
	}

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		密钥 ID
	 * @param accessKeySecret
	 * 		阿里云验证码配置对应的 App Key
	 * @param regionId
	 * 		区域 ID
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final String regionId){
		this(accessKeyId, accessKeySecret);

		Assert.isBlank(regionId, "RegionId cloud not be empty or null");

		endpoint = String.format("https://afs.%s.aliyuncs.com", regionId);
	}

	/**
	 * 构造函数
	 *
	 * @param accessKeyId
	 * 		密钥 ID
	 * @param accessKeySecret
	 * 		阿里云验证码配置对应的 App Key
	 * @param regionId
	 * 		区域 ID
	 * @param httpClient
	 * 		Http Client
	 */
	public AliYunCaptchaClient(final String accessKeyId, final String accessKeySecret, final String regionId,
							   final HttpClient httpClient){
		this(accessKeyId, accessKeySecret, regionId);
		setHttpClient(httpClient);
	}

	@Override
	public Status validate(RequestData requestData) throws CaptchaException{
		if(logger.isDebugEnabled()){
			logger.debug("二次验证, 请求参数：{}.", requestData);
		}

		AliYunRequestData aliYunRequestData = (AliYunRequestData) requestData;

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DDThh:mm:ssZ");
		MapBuilder<String, Object> parametersBuilder = MapBuilder.<String, Object>create()
				.put("Action", ACTION).put("Format", FORMAT).put("Version", getVersion())
				.put("SignatureMethod", Algo.HMAC_SHA1.getName()).put("SignatureNonce", randomStr())
				.put("SignatureVersion", SIGNATURE_VERSION)
				.put("AccessKeyId", accessKeyId).put("AppKey", accessKeySecret)
				.put("Timestamp", sdf.format(new Date()));

		//sb.append("&Signature=").append(getVersion());

		if(aliYunRequestData.getToken() != null){
			parametersBuilder.put("Token", aliYunRequestData.getToken());
		}

		if(aliYunRequestData.getSig() != null){
			parametersBuilder.put("Sig", aliYunRequestData.getSig());
		}

		if(aliYunRequestData.getSessionId() != null){
			parametersBuilder.put("SessionId", aliYunRequestData.getSessionId());
		}

		if(aliYunRequestData.getScene() != null){
			parametersBuilder.put("Scene", aliYunRequestData.getScene());
		}

		if(aliYunRequestData.getClientIp() != null){
			parametersBuilder.put("RemoteIp", aliYunRequestData.getClientIp());
		}

		if(aliYunRequestData.getSourceIp() != null){
			parametersBuilder.put("SourceIp", aliYunRequestData.getSourceIp());
		}

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", parametersBuilder.build());
		}

		Response response;
		try{
			response = getHttpClient().get(endpoint, parametersBuilder.build());

			if(logger.isInfoEnabled()){
				logger.info("二次验证 response: {}", response);
			}

			AliyunEnhencedResult result = ObjectMapperUtils.createObjectMapper().readValue(response.getBody(),
					AliyunEnhencedResult.class);

			if(Objects.equals(result.getSuccess(), Boolean.TRUE)){
				if(result.getData() == null){
					throw new CaptchaException("Validate failure");
				}

				if(Objects.equals(result.getData().getCode(), "100")){
					return Status.SUCCESS;
				}else{
					throw new CaptchaException("Validate failure(code: " + result.getData().getCode() + ")");
				}
			}else{
				if(Objects.equals(result.getCode(), "400")){
					throw new CaptchaException("Request parameter error");
				}else{
					throw new CaptchaException("Aliyun afs server system error");
				}
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

}
