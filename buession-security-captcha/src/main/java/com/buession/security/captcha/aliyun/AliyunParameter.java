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

import com.buession.core.validator.Validate;
import com.buession.security.captcha.core.Parameter;

import java.util.StringJoiner;

/**
 * 阿里云行为验证参数定义
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AliyunParameter implements Parameter {

	private final static long serialVersionUID = -1575628555899104535L;

	/**
	 * 默认会话 ID 参数名称
	 */
	public final static String DEFAULT_SESSION_ID = "sessionId";

	/**
	 * 默认签名串参数名称
	 */
	public final static String DEFAULT_SIG = "sig";

	/**
	 * 默认请求唯一标识参数名称
	 */
	public final static String DEFAULT_TOKEN = "token";

	/**
	 * 默认场景标识参数名称
	 */
	public final static String DEFAULT_SCENE = "scene";

	/**
	 * 会话 ID 参数名称
	 */
	private String sessionId = DEFAULT_SESSION_ID;

	/**
	 * 签名串参数名称
	 */
	private String sig = DEFAULT_SIG;

	/**
	 * 请求唯一标识参数名称
	 */
	private String token = DEFAULT_TOKEN;

	/**
	 * 场景标识参数名称
	 */
	private String scene = DEFAULT_SCENE;

	/**
	 * 返回会话 ID 参数名称
	 *
	 * @return 会话 ID 参数名称
	 */
	public String getSessionId(){
		return sessionId;
	}

	/**
	 * 设置会话 ID 参数名称
	 *
	 * @param sessionId
	 * 		会话 ID 参数名称
	 */
	public void setSessionId(String sessionId){
		if(Validate.hasText(sessionId)){
			this.sessionId = sessionId;
		}
	}

	/**
	 * 返回签名串参数名称
	 *
	 * @return 签名串参数名称
	 */
	public String getSig(){
		return sig;
	}

	/**
	 * 设置签名串参数名称
	 *
	 * @param sig
	 * 		签名串参数名称
	 */
	public void setSig(String sig){
		if(Validate.hasText(sig)){
			this.sig = sig;
		}
	}

	/**
	 * 返回请求唯一标识参数名称
	 *
	 * @return 请求唯一标识参数名称
	 */
	public String getToken(){
		return token;
	}

	/**
	 * 设置请求唯一标识参数名称
	 *
	 * @param token
	 * 		请求唯一标识参数名称
	 */
	public void setToken(String token){
		this.token = token;
	}

	/**
	 * 返回场景标识参数名称
	 *
	 * @return 场景标识参数名称
	 */
	public String getScene(){
		return scene;
	}

	/**
	 * 设置场景标识参数名称
	 *
	 * @param scene
	 * 		场景标识参数名称
	 */
	public void setScene(String scene){
		if(Validate.hasText(scene)){
			this.scene = scene;
		}
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("sessionId=" + sessionId)
				.add("sig=" + sig)
				.add("token=" + token)
				.add("scene=" + scene)
				.toString();
	}

}
