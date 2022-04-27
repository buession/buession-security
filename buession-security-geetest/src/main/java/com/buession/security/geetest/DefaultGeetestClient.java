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

import com.buession.core.builder.MapBuilder;
import com.buession.httpclient.HttpClient;
import com.buession.httpclient.core.Response;
import com.buession.lang.Status;
import com.buession.security.geetest.api.GeetestApiClient;
import com.buession.security.geetest.api.v3.GeetestV3Client;
import com.buession.security.mcrypt.MD5Mcrypt;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.buession.security.geetest.core.ProcessResult;
import com.buession.security.geetest.core.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private final static Map<String, Class<? extends GeetestApiClient>> GEETEST_API_CLIENTS = MapBuilder.<String, Class<? extends GeetestApiClient>>create().put("v3", GeetestV3Client.class).put("V3", GeetestV3Client.class).put("v4", GeetestV3Client.class).put("V4", GeetestV3Client.class).build();

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

	private GeetestApiClient geetestApiClient;

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
		this.geetestApiClient = null;
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
		return geetestApiClient.validate();
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
		//return StatusUtils.valueOf(requestIsLegal(challenge, validate, seccode));
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
		String url = URLBuilder.buildRegisterUrl(geetestId, requestData.getClientType(), requestData.getUserId(), requestData.getIpAddress());

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

				this.response = getSuccessPreProcessResult(md5Mcrypt.encode(processResult.getChallenge() + geetestKey));

				return Status.SUCCESS;
			}else{
				logger.error("gtServer register challenge error");
			}
		}catch(Exception e){
			logger.error("gtServer register challenge error: {}", e.getMessage());
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

	protected static long random(final int divisor){
		return Math.round(Math.random() * divisor);
	}

}
