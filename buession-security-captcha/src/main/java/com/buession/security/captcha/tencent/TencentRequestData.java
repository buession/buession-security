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

import com.buession.security.captcha.core.RequestData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 腾讯云请求数据
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class TencentRequestData implements RequestData {

	/**
	 * 前端回调函数返回的随机字符串
	 */
	@JsonProperty(value = "Randstr")
	private String randstr;

	/**
	 * 前端回调函数返回的用户验证票据
	 */
	@JsonProperty(value = "Ticket")
	private String ticket;

	/**
	 * 客户端 IP
	 */
	@JsonProperty(value = "UserIp")
	private String userIp;

	/**
	 * 返回前端回调函数返回的随机字符串
	 *
	 * @return 前端回调函数返回的随机字符串
	 */
	public String getRandstr(){
		return randstr;
	}

	/**
	 * 设置前端回调函数返回的随机字符串
	 *
	 * @param randstr
	 * 		前端回调函数返回的随机字符串
	 */
	public void setRandstr(String randstr){
		this.randstr = randstr;
	}

	/**
	 * 返回前端回调函数返回的用户验证票据
	 *
	 * @return 前端回调函数返回的用户验证票据
	 */
	public String getTicket(){
		return ticket;
	}

	/**
	 * 设置前端回调函数返回的用户验证票据
	 *
	 * @param ticket
	 * 		前端回调函数返回的用户验证票据
	 */
	public void setTicket(String ticket){
		this.ticket = ticket;
	}

	/**
	 * 返回客户端 IP
	 *
	 * @return 客户端 IP
	 */
	public String getUserIp(){
		return userIp;
	}

	/**
	 * 设置客户端 IP
	 *
	 * @param userIp
	 * 		客户端 IP
	 */
	public void setUserIp(String userIp){
		this.userIp = userIp;
	}

	@JsonIgnore
	@Override
	public String getClientIp(){
		return getUserIp();
	}

	@JsonIgnore
	@Override
	public void setClientIp(String clientIp){
		setUserIp(clientIp);
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("randstr=" + randstr)
				.add("ticket=" + ticket)
				.add("userIp=" + userIp)
				.toString();
	}

}
