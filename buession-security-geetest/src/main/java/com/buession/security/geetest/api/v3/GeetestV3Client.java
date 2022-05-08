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

import com.buession.core.id.SimpleIdGenerator;
import com.buession.core.utils.StatusUtils;
import com.buession.core.validator.Validate;
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
	public GeetestV3Client(String geetestId, String geetestKey){
		super(geetestId, geetestKey);
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
		Map<String, Object> parameters = new HashMap<>(7);

		parameters.put("gt", JSON_FORMAT);
		parameters.put("json_format", JSON_FORMAT);
		parameters.put("digestmod", digestMode.getName());
		parameters.put("sdk", SDK_NAME);

		if(requestV3Data.getUserId() != null){
			parameters.put("user_id", requestV3Data.getUserId());
		}

		if(requestV3Data.getClientType() != null){
			parameters.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(requestV3Data.getIpAddress() != null){
			parameters.put("ip_address", requestV3Data.getIpAddress());
		}

		if(logger.isDebugEnabled()){
			logger.debug("验证初始化, parameters：{}.", parameters);
		}

		InitV3Result initResult;
		try{
			Response response = httpClient.get(REGISTER_URL, parameters);

			initResult = OBJECT_MAPPER.readValue(response.getBody(), InitV3Result.class);
		}catch(Exception e){
			logger.error("验证初始化失败: {}", e.getMessage());
			initResult = new InitV3Result();
		}

		initResult.setSuccess(true);
		initResult.setNewCaptcha(true);

		if(initResult.getChallenge() == null || initResult.getChallenge().isEmpty() ||
				"0".equals(initResult.getChallenge())){
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
		if(checkParam(requestV3Data.getChallenge(), "aa", requestV3Data.getSeccode()) == false){
			return Status.FAILURE;
		}

		Map<String, Object> formData = new HashMap<>(7);

		formData.put("challenge", requestV3Data.getChallenge());
		//formData.put("validate", validate);
		formData.put("seccode", requestV3Data.getSeccode());
		formData.put("json_format", JSON_FORMAT);
		formData.put("sdk", SDK_NAME);

		if(requestV3Data.getUserId() != null){
			formData.put("user_id", requestV3Data.getUserId());
		}

		if(requestV3Data.getClientType() != null){
			formData.put("client_type", requestV3Data.getClientType().getValue());
		}

		if(requestV3Data.getIpAddress() != null){
			formData.put("ip_address", requestV3Data.getIpAddress());
		}

		Response response;
		try{
			response = httpClient.post(VALIDATE_URL, formData);

			logger.debug("Enhenced Validate response: {}", response);

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
