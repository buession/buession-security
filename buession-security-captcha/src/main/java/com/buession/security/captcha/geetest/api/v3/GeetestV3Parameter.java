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
package com.buession.security.captcha.geetest.api.v3;

import com.buession.core.validator.Validate;
import com.buession.security.captcha.geetest.GeetestParameter;

import java.util.StringJoiner;

/**
 * 极验 V3 版本参数定义接口
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestV3Parameter implements GeetestParameter {

	private final static long serialVersionUID = 5519418826643028121L;

	/**
	 * 默认流水号参数名称
	 */
	public final static String DEFAULT_CHALLENGE = "challenge";

	/**
	 * 默认核心校验数据参数名称
	 */
	public final static String DEFAULT_SECCODE = "seccode";

	/**
	 * 默认核心校验数据参数名称
	 */
	public final static String DEFAULT_VALIDATE = "validate";

	/**
	 * 默认用户的唯一标识参数名称
	 */
	public final static String DEFAULT_USER_ID = "user_id";

	/**
	 * 默认客户端类型参数名称
	 */
	public final static String DEFAULT_CLIENT_TYPE = "client_type";

	/**
	 * 流水号参数名称
	 */
	private String challenge = DEFAULT_CHALLENGE;

	/**
	 * 核心校验数据参数名称
	 */
	private String seccode = DEFAULT_SECCODE;

	/**
	 * 核心校验数据参数名称
	 */
	private String validate = DEFAULT_VALIDATE;

	/**
	 * 用户的唯一标识参数名称
	 */
	private String userId = DEFAULT_USER_ID;

	/**
	 * 客户端类型参数名称
	 */
	private String clientType = DEFAULT_CLIENT_TYPE;

	/**
	 * 返回流水号参数名称
	 *
	 * @return 流水号参数名称
	 */
	public String getChallenge(){
		return challenge;
	}

	/**
	 * 设置流水号参数名称
	 *
	 * @param challenge
	 * 		流水号参数名称
	 */
	public void setChallenge(String challenge){
		if(Validate.hasText(challenge)){
			this.challenge = challenge;
		}
	}

	/**
	 * 返回核心校验数据参数名称
	 *
	 * @return 核心校验数据参数名称
	 */
	public String getSeccode(){
		return seccode;
	}

	/**
	 * 设置核心校验数据参数名称
	 *
	 * @param seccode
	 * 		核心校验数据参数名称
	 */
	public void setSeccode(String seccode){
		if(Validate.hasText(seccode)){
			this.seccode = seccode;
		}
	}

	/**
	 * 返回核心校验数据参数名称
	 *
	 * @return 核心校验数据参数名称
	 */
	public String getValidate(){
		return validate;
	}

	/**
	 * 设置核心校验数据参数名称
	 *
	 * @param validate
	 * 		核心校验数据参数名称
	 */
	public void setValidate(String validate){
		if(Validate.hasText(validate)){
			this.validate = validate;
		}
	}

	/**
	 * 返回用户的唯一标识参数名称
	 *
	 * @return 用户的唯一标识参数名称
	 */
	public String getUserId(){
		return userId;
	}

	/**
	 * 设置用户的唯一标识参数名称
	 *
	 * @param userId
	 * 		用户的唯一标识参数名称
	 */
	public void setUserId(String userId){
		if(Validate.hasText(userId)){
			this.userId = userId;
		}
	}

	/**
	 * 返回客户端类型参数名称
	 *
	 * @return 客户端类型参数名称
	 */
	public String getClientType(){
		return clientType;
	}

	/**
	 * 设置客户端类型参数名称
	 *
	 * @param clientType
	 * 		客户端类型参数名称
	 */
	public void setClientType(String clientType){
		if(Validate.hasText(clientType)){
			this.clientType = clientType;
		}
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("challenge=" + challenge)
				.add("seccode=" + seccode)
				.add("validate=" + validate)
				.add("userId=" + userId)
				.add("clientType=" + clientType)
				.toString();
	}

}
