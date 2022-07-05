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

import com.buession.security.captcha.core.ValidateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 阿里云二次校验返回结果
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliyunEnhencedResult implements ValidateResponse {

	private final static long serialVersionUID = 402465840048648582L;

	/**
	 * 调用返回编码
	 */
	@JsonProperty(value = "Code")
	private int code;

	/**
	 * 请求 ID
	 */
	@JsonProperty(value = "RequestId")
	private String requestId;

	@JsonProperty(value = "RiskLevel")
	private String riskLevel;

	/**
	 * 消息
	 */
	@JsonProperty(value = "Msg")
	private String msg;

	/**
	 * 返回调用返回编码
	 *
	 * @return 调用返回编码
	 */
	public int getCode(){
		return code;
	}

	/**
	 * 设置调用返回编码
	 *
	 * @param code
	 * 		调用返回编码
	 */
	public void setCode(int code){
		this.code = code;
	}

	/**
	 * 返回请求 ID
	 *
	 * @return 请求 ID
	 */
	public String getRequestId(){
		return requestId;
	}

	/**
	 * 设置请求 ID
	 *
	 * @param requestId
	 * 		请求 ID
	 */
	public void setRequestId(String requestId){
		this.requestId = requestId;
	}

	public String getRiskLevel(){
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel){
		this.riskLevel = riskLevel;
	}

	/**
	 * 返回消息
	 *
	 * @return 消息
	 */
	public String getMsg(){
		return msg;
	}

	/**
	 * 设置消息
	 *
	 * @param msg
	 * 		消息
	 */
	public void setMsg(String msg){
		this.msg = msg;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("code=" + code)
				.add("requestId='" + requestId + "'")
				.add("riskLevel='" + riskLevel + "'")
				.add("msg='" + msg + "'")
				.toString();
	}

}
