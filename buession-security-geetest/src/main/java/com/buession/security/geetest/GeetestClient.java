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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.geetest;

import com.buession.core.utils.StatusUtils;
import com.buession.core.validator.Validate;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.geetest.core.ClientType;
import com.buession.security.geetest.core.EnhencedResult;
import com.buession.security.mcrypt.MD5Mcrypt;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.buession.security.geetest.core.ProcessResult;
import com.buession.security.geetest.core.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class GeetestClient {

	public final static String GT_SERVER_STATUS_SESSION_KEY = "gt_server_status";

	/**
	 * 极验验证二次验证表单数据 chllenge
	 */
	public final static String GEETEST_CHALLENGE = "geetest_challenge";

	/**
	 * 极验验证二次验证表单数据 validate
	 */
	public final static String GEETEST_VALIDATE = "geetest_validate";

	/**
	 * 极验验证二次验证表单数据 seccode
	 */
	public final static String GEETEST_SECCODE = "geetest_seccode";

	public final static String PARAMETER_SESSION_ID = "session_id";

	public final static String PARAMETER_CHALLENGE = "verifyChallenge";

	public final static String PARAMETER_VALIDATE = "verifyValidate";

	public final static String PARAMETER_SECCODE = "verifySeccode";

	public final static int CHALLENGE_LENGTH = 32;

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	/**
	 * 公钥
	 */
	private String geetestId;

	/**
	 * 私钥
	 */
	private String geetestKey;

	/**
	 * 是否开启新的 failback
	 */
	private boolean newFailback = false;

	private HttpClient httpClient;

	/**
	 * 返回字符串
	 */
	private ProcessResult response;

	private final static Logger logger = LoggerFactory.getLogger(GeetestClient.class);

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
	 * @param newFailback
	 * 		是否开启新的 failback
	 */
	public GeetestClient(String geetestId, String geetestKey, boolean newFailback){
		this.geetestId = geetestId;
		this.geetestKey = geetestKey;
		this.newFailback = newFailback;
	}

	/**
	 * 构造函数
	 *
	 * @param geetestId
	 * 		公钥
	 * @param geetestKey
	 * 		私钥
	 * @param newFailback
	 * 		是否开启新的 failback
	 * @param httpClient
	 * 		Http Client
	 */
	public GeetestClient(String geetestId, String geetestKey, boolean newFailback, HttpClient httpClient){
		this(geetestId, geetestKey, newFailback);
		this.httpClient = httpClient;
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
		this.httpClient = httpClient;
	}

	/**
	 * 验证初始化预处理
	 *
	 * @param requestData
	 * 		请求数据
	 *
	 * @return 初始化预处理结果
	 */
	public Status preProcess(RequestData requestData){
		if(registerChallenge(requestData) == Status.FAILURE){
			response = getFailPreProcessResult();
			return Status.FAILURE;
		}

		return Status.SUCCESS;
	}

	/**
	 * 服务正常的情况下使用的验证方式,向gt-server进行二次验证,获取验证结果
	 *
	 * @param challenge
	 * 		challenge
	 * @param validate
	 * 		validate
	 * @param seccode
	 * 		seccode
	 * @param requestData
	 * 		请求数据
	 *
	 * @return 验证结果
	 */
	public Status enhencedValidateRequest(String challenge, String validate, String seccode, RequestData requestData){
		if(requestIsLegal(challenge, validate, seccode) == false){
			return Status.FAILURE;
		}

		logger.debug("request legitimate");

		Map<String, Object> formData = new HashMap<>(7);

		formData.put("challenge", challenge);
		formData.put("validate", validate);
		formData.put("seccode", seccode);
		formData.put("json_format", "1");

		if(requestData.getUserId() != null){
			formData.put("user_id", requestData.getUserId());
		}

		if(requestData.getClientType() != null){
			formData.put("client_type", requestData.getClientType().getValue());
		}

		if(requestData.getIpAddress() != null){
			formData.put("ip_address", requestData.getIpAddress());
		}

		logger.debug("param: {}", formData.toString());

		if(validate.length() <= 0){
			return Status.FAILURE;
		}

		if(checkResultByPrivate(geetestKey, challenge, validate) == false){
			return Status.FAILURE;
		}

		logger.debug("checkResultByPrivate");

		Response response;
		try{
			response = httpClient.post(Geetest.VALIDATE_URL, formData);

			logger.debug("Enhenced Validate response: {}", response);

			EnhencedResult returnMap = OBJECT_MAPPER.readValue(response.getBody(), EnhencedResult.class);

			final MD5Mcrypt md5Mcrypt = new MD5Mcrypt();
			return StatusUtils.valueOf(md5Mcrypt.encode(seccode).equals(returnMap.getSeccode()));
		}catch(Exception e){
			logger.error("Enhenced Validate failure: {}", e);
		}

		return Status.FAILURE;
	}

	/**
	 * failback 使用的验证方式
	 *
	 * @param challenge
	 * 		challenge
	 * @param validate
	 * 		validate
	 * @param seccode
	 * 		seccode
	 *
	 * @return 验证结果
	 */
	public Status failbackValidateRequest(String challenge, String validate, String seccode){
		logger.debug("in failback validate");
		return StatusUtils.valueOf(requestIsLegal(challenge, validate, seccode));
	}

	/**
	 * 获取本次验证初始化返回结果
	 *
	 * @return 初始化结果
	 */
	public ProcessResult getResponse(){
		return response;
	}

	public String getVersion(){
		return Geetest.VERSION;
	}

	/**
	 * 用 captchaID 进行注册，更新challenge
	 *
	 * @param requestData
	 * 		请求数据
	 *
	 * @return 注册结果
	 */
	protected Status registerChallenge(RequestData requestData){
		String url = URLBuilder.buildRegisterUrl(geetestId, requestData.getClientType(), requestData.getUserId(),
				requestData.getIpAddress());

		logger.debug("GET_URL: {}", url);

		try{
			Response response = httpClient.get(url);
			if(response == null){
				logger.warn("gtServer register challenge failure");
				return Status.FAILURE;
			}

			logger.debug("gtServer register challenge success: {} ", response.getBody());

			ProcessResult processResult = OBJECT_MAPPER.readValue(response.getBody(), ProcessResult.class);

			logger.debug("gtServer register challenge failed: {}", processResult.getChallenge());

			if(processResult.getChallenge().length() == CHALLENGE_LENGTH){
				final MD5Mcrypt md5Mcrypt = new MD5Mcrypt();

				this.response =
						getSuccessPreProcessResult(md5Mcrypt.encode(processResult.getChallenge() + geetestKey));

				return Status.SUCCESS;
			}else{
				logger.error("gtServer register challenge error");
			}
		}catch(Exception e){
			logger.error("gtServer register challenge error: {}", e);
		}

		return Status.FAILURE;
	}

	/**
	 * 返回预处理成功结果
	 *
	 * @param challenge
	 * 		challenge
	 *
	 * @return 预处理成功结果
	 */
	protected ProcessResult getSuccessPreProcessResult(String challenge){
		logger.debug("challenge: {}", challenge);
		ProcessResult processResult = new ProcessResult();

		processResult.setSuccess(true);
		processResult.setGt(geetestId);
		processResult.setChallenge(challenge);
		processResult.setNewCaptcha(newFailback);

		return processResult;
	}

	/**
	 * 返回预处理失败结果
	 *
	 * @return 预处理失败结果
	 */
	protected ProcessResult getFailPreProcessResult(){
		final MD5Mcrypt md5Mcrypt = new MD5Mcrypt();
		StringBuilder challenge = new StringBuilder(36);
		String s1 = md5Mcrypt.encode(random(100));
		String s2 = md5Mcrypt.encode(random(100));

		challenge.append(s1).append(s2, 0, 2);

		ProcessResult processResult = new ProcessResult();

		processResult.setSuccess(false);
		processResult.setGt(geetestId);
		processResult.setChallenge(challenge.toString());
		processResult.setNewCaptcha(newFailback);

		return processResult;
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
	protected final static boolean requestIsLegal(String challenge, String validate, String seccode){
		return Validate.hasText(challenge) && Validate.hasText(validate) && Validate.hasText(seccode);
	}

	protected final static boolean checkResultByPrivate(final String geetestKey, final String challenge,
			final String validate){
		final MD5Mcrypt md5Mcrypt = new MD5Mcrypt();
		final StringBuilder sb = new StringBuilder(geetestKey.length() + Geetest.NAME.length() + challenge.length());

		sb.append(geetestKey).append(Geetest.NAME).append(challenge);

		return validate.equals(md5Mcrypt.encode(sb.toString()));
	}

	protected final static long random(final int divisor){
		return Math.round(Math.random() * divisor);
	}

	protected final static class URLBuilder {

		public final static String buildRegisterUrl(final String geetestId, final ClientType clientType,
				final String userId, final String ip){
			final StringBuilder sb = new StringBuilder(Geetest.REGISTER_URL.length() + geetestId.length() + 24);

			sb.append(Geetest.REGISTER_URL).append('?');
			sb.append("gt=").append(geetestId);
			sb.append("&json_format=1");

			if(userId != null){
				sb.append("&user_id=").append(userId);
			}

			if(clientType != null){
				sb.append("&client_type=").append(clientType.getValue());
			}

			if(ip != null){
				sb.append("&ip_address=").append(ip);
			}

			return sb.toString();
		}

	}

}
