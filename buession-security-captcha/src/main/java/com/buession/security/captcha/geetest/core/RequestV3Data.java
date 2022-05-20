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
package com.buession.security.captcha.geetest.core;

import com.buession.security.captcha.core.RequestData;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * V3 版本请求数据
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class RequestV3Data implements RequestData {

	/**
	 * 流水号
	 */
	private String challenge;

	/**
	 * 核心校验数据
	 */
	private String seccode;

	/**
	 * 核心校验数据
	 */
	private String validate;

	/**
	 * 用户的唯一标识
	 */
	@JsonProperty(value = "user_id")
	private String userId;

	/**
	 * 客户端类型
	 */
	@JsonProperty(value = "client_type")
	private ClientType clientType;

	@JsonProperty(value = "ip_address")
	private String ipAddress;

	/**
	 * 返回流水号
	 *
	 * @return 流水号
	 */
	public String getChallenge(){
		return challenge;
	}

	/**
	 * 设置流水号
	 *
	 * @param challenge
	 * 		流水号
	 */
	public void setChallenge(String challenge){
		this.challenge = challenge;
	}

	/**
	 * 返回核心校验数据
	 *
	 * @return 核心校验数据
	 */
	public String getSeccode(){
		return seccode;
	}

	/**
	 * 设置核心校验数据
	 *
	 * @param seccode
	 * 		核心校验数据
	 */
	public void setSeccode(String seccode){
		this.seccode = seccode;
	}

	/**
	 * 返回核心校验数据
	 *
	 * @return 核心校验数据
	 */
	public String getValidate(){
		return validate;
	}

	/**
	 * 设置核心校验数据
	 *
	 * @param validate
	 * 		核心校验数据
	 */
	public void setValidate(String validate){
		this.validate = validate;
	}

	/**
	 * 返回用户的唯一标识
	 *
	 * @return 用户的唯一标识
	 */
	public String getUserId(){
		return userId;
	}

	/**
	 * 设置用户的唯一标识
	 *
	 * @param userId
	 * 		用户的唯一标识
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}

	/**
	 * 返回客户端类型
	 *
	 * @return 客户端类型
	 */
	public ClientType getClientType(){
		return clientType;
	}

	/**
	 * 设置客户端类型
	 *
	 * @param clientType
	 * 		客户端类型
	 */
	public void setClientType(ClientType clientType){
		this.clientType = clientType;
	}

	/**
	 * 返回客户端请求SDK服务器的ip地址
	 *
	 * @return 客户端请求SDK服务器的ip地址
	 */
	public String getIpAddress(){
		return ipAddress;
	}

	/**
	 * 设置客户端请求SDK服务器的ip地址
	 *
	 * @param ipAddress
	 * 		客户端请求SDK服务器的ip地址
	 */
	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("challenge=" + challenge)
				.add("seccode=" + seccode)
				.add("validate=" + validate)
				.add("userId=" + userId)
				.add("clientType=" + clientType)
				.add("ipAddress=" + ipAddress)
				.toString();
	}
}
