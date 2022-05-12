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
package com.buession.security.geetest.api.v3;

import com.buession.core.builder.MapBuilder;
import com.buession.core.id.SimpleIdGenerator;
import com.buession.core.utils.StatusUtils;
import com.buession.core.validator.Validate;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.geetest.GeetestException;
import com.buession.security.geetest.api.AbstractGeetestClient;
import com.buession.security.geetest.core.DigestMode;
import com.buession.security.geetest.core.EnhencedResult;
import com.buession.security.geetest.core.InitResult;
import com.buession.security.geetest.core.InitV3Result;
import com.buession.security.geetest.core.RequestData;
import com.buession.security.geetest.core.RequestV3Data;
import com.buession.security.geetest.utils.Digester;
import com.buession.security.mcrypt.MD5Mcrypt;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 极验行为验证 V3 版本 Client
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeetestV3Client extends AbstractGeetestClient {

	private final static String CHECK_STATUS_URL = "https://api.geetest.com//v1/bypass_status.php";

	private final static String REGISTER_URL = "https://api.geetest.com/register.php";

	private final static String VALIDATE_URL = "https://api.geetest.com/validate.php";

	private final static String JSON_FORMAT = "1";

	private final static Logger logger = LoggerFactory.getLogger(GeetestV3Client.class);

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 */
	public GeetestV3Client(final String geetestId, final String geetestKey){
		super(geetestId, geetestKey);
	}

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 * @param httpClient
	 *        {@link HttpClient}
	 */
	public GeetestV3Client(final String geetestId, final String geetestKey, final HttpClient httpClient){
		super(geetestId, geetestKey, httpClient);
	}

	@Override
	public Status checkStatus(){
		try{
			Response response = httpClient.get(REGISTER_URL, MapBuilder.of("gt", geetestId));

			Map<String, Object> data = OBJECT_MAPPER.readValue(response.getBody(),
					new TypeReference<Map<String, Object>>() {

					});

			if(data == null){
				return "success".equals(data.get("status")) ? Status.SUCCESS : Status.FAILURE;
			}
		}catch(Exception e){
			//
		}

		return Status.FAILURE;
	}

	@Override
	public InitResult initialize(DigestMode digestMode, RequestData requestData) throws GeetestException{
		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, DigestMode: {}", digestMode);
		}

		if(digestMode == null){
			digestMode = DigestMode.MD5;
		}

		RequestV3Data requestV3Data = (RequestV3Data) requestData;
		MapBuilder<String, Object> parametersBuilder = MapBuilder.<String, Object>create()
				.put("gt", geetestId)
				.put("json_format", JSON_FORMAT)
				.put("digestmod", digestMode.getName())
				.put("sdk", getSdkName());

		if(requestV3Data.getClientType() != null){
			parametersBuilder.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(requestV3Data.getIpAddress() != null){
			parametersBuilder.put("ip_address", requestV3Data.getIpAddress());
		}

		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, parameters：{}.", parametersBuilder.build());
		}

		InitV3Result initResult;
		try{
			Response response = httpClient.get(REGISTER_URL, parametersBuilder.build());

			initResult = OBJECT_MAPPER.readValue(response.getBody(), InitV3Result.class);

			if(logger.isInfoEnabled()){
				logger.info("register api return data: {}", initResult);
			}
		}catch(Exception e){
			logger.error("验证初始化失败: {}", e.getMessage());
			initResult = new InitV3Result();
		}

		initResult.setSuccess(true);
		initResult.setNewCaptcha(true);

		if(Validate.isBlank(initResult.getChallenge()) || "0".equals(initResult.getChallenge())){
			initResult.setSuccess(false);
			initResult.setChallenge(new SimpleIdGenerator().nextId());
		}else{
			initResult.setGt(geetestId);

			Digester digester = new Digester(digestMode, geetestKey);
			initResult.setChallenge(digester.hex(initResult.getChallenge()));
		}

		return initResult;
	}

	@Override
	public Status validate(RequestData requestData) throws GeetestException{
		if(logger.isDebugEnabled()){
			logger.debug("二次验证 正常模式, 请求参数：{}.", requestData);
		}

		RequestV3Data requestV3Data = (RequestV3Data) requestData;
		if(checkParam(requestV3Data.getChallenge(), requestV3Data.getValidate(), requestV3Data.getSeccode()) == false){
			return Status.FAILURE;
		}

		MapBuilder<String, Object> formDataBuilder = MapBuilder.<String, Object>create()
				.put("captchaid", geetestId)
				.put("challenge", requestV3Data.getChallenge())
				.put("validate", requestV3Data.getValidate())
				.put("seccode", requestV3Data.getSeccode())
				.put("json_format", JSON_FORMAT)
				.put("sdk", getSdkName());

		if(requestV3Data.getUserId() != null){
			formDataBuilder.put("user_id", requestV3Data.getUserId());
		}

		if(requestV3Data.getClientType() != null){
			formDataBuilder.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(requestV3Data.getIpAddress() != null){
			formDataBuilder.put("ip_address", requestV3Data.getIpAddress());
		}

		if(logger.isDebugEnabled()){
			logger.debug("二次验证, parameters：{}.", formDataBuilder.build());
		}

		Response response;
		try{
			response = httpClient.post(VALIDATE_URL, formDataBuilder.build());

			if(logger.isInfoEnabled()){
				logger.info("Enhenced Validate response: {}", response);
			}

			EnhencedResult returnMap = OBJECT_MAPPER.readValue(response.getBody(), EnhencedResult.class);

			final MD5Mcrypt md5Mcrypt = new MD5Mcrypt();
			return StatusUtils.valueOf(md5Mcrypt.encode(requestV3Data.getSeccode()).equals(returnMap.getSeccode()));
		}catch(Exception e){
			logger.error("Enhenced Validate failure: {}", e.getMessage());
		}

		return Status.FAILURE;
	}

	@Override
	public String getVersion(){
		return "v3";
	}

	/**
	 * 检查客户端的请求是否合法,三个只要有一个为空，则判断不合法
	 *
	 * @param challenge
	 * 		challenge
	 * @param validate
	 * 		validate
	 * @param seccode
	 * 		seccode
	 *
	 * @return 检测结果
	 */
	private static boolean checkParam(String challenge, String validate, String seccode){
		return Validate.hasText(challenge) && Validate.hasText(validate) && Validate.hasText(seccode);
	}

}
