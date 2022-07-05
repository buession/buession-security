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

import com.buession.security.captcha.core.RequestData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 阿里云请求数据
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliYunRequestData implements RequestData {

	/**
	 * 请求唯一标识
	 */
	@JsonProperty(value = "Token")
	private String token;

	/**
	 * 签名串
	 */
	@JsonProperty(value = "Sig")
	private String sig;

	/**
	 * 会话 ID
	 */
	@JsonProperty(value = "SessionId")
	private String sessionId;

	/**
	 * 使用场景标识；在统计报表中将根据该字段的内容进行分类展示
	 */
	@JsonProperty(value = "Scene")
	private String scene;

	/**
	 * 客户端 IP
	 */
	@JsonProperty(value = "RemoteIp")
	private String remoteIp;

	/**
	 * 返回请求唯一标识
	 *
	 * @return 请求唯一标识
	 */
	public String getToken(){
		return token;
	}

	/**
	 * 设置请求唯一标识
	 *
	 * @param token
	 * 		请求唯一标识
	 */
	public void setToken(String token){
		this.token = token;
	}

	/**
	 * 返回签名串
	 *
	 * @return 签名串
	 */
	public String getSig(){
		return sig;
	}

	/**
	 * 设置签名串
	 *
	 * @param sig
	 * 		签名串
	 */
	public void setSig(String sig){
		this.sig = sig;
	}

	/**
	 * 返回会话 ID
	 *
	 * @return 会话 ID
	 */
	public String getSessionId(){
		return sessionId;
	}

	/**
	 * 设置会话 ID
	 *
	 * @param sessionId
	 * 		会话 ID
	 */
	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}

	/**
	 * 返回使用场景标识；在统计报表中将根据该字段的内容进行分类展示
	 *
	 * @return 使用场景标识
	 */
	public String getScene(){
		return scene;
	}

	/**
	 * 设置使用场景标识；在统计报表中将根据该字段的内容进行分类展示
	 *
	 * @param scene
	 * 		使用场景标识
	 */
	public void setScene(String scene){
		this.scene = scene;
	}

	/**
	 * 返回客户端 IP
	 *
	 * @return 客户端 IP
	 */
	public String getRemoteIp(){
		return remoteIp;
	}

	/**
	 * 设置客户端 IP
	 *
	 * @param remoteIp
	 * 		客户端 IP
	 */
	public void setRemoteIp(String remoteIp){
		this.remoteIp = remoteIp;
	}

	@JsonIgnore
	@Override
	public String getClientIp(){
		return getRemoteIp();
	}

	@JsonIgnore
	@Override
	public void setClientIp(String clientIp){
		setRemoteIp(clientIp);
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("token=" + token)
				.add("sig=" + sig)
				.add("sessionId=" + sessionId)
				.add("scene=" + scene)
				.add("remoteIp=" + remoteIp)
				.toString();
	}

}
