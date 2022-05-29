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

import com.buession.security.captcha.core.ValidateResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 腾讯二次校验返回结果
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class TencentValidateResponse implements ValidateResponse {

	private final static long serialVersionUID = 402465840048648582L;

	/**
	 * 错误码，1：验证成功，0：验证失败，100：AppSecretKey 参数校验错误
	 */
	private int response;

	/**
	 * 恶意等级，取值：0 ~ 100
	 */
	@JsonProperty(value = "evil_level")
	private int evilLevel;

	/**
	 * 验证错误信息
	 */
	@JsonProperty(value = "err_msg")
	private String errMsg;

	/**
	 * 返回错误码
	 *
	 * @return 错误码，1：验证成功，0：验证失败，100：AppSecretKey 参数校验错误
	 */
	public int getResponse(){
		return response;
	}

	/**
	 * 设置错误码
	 *
	 * @param response
	 * 		错误码
	 */
	public void setResponse(int response){
		this.response = response;
	}

	/**
	 * 返回恶意等级
	 *
	 * @return 恶意等级，取值：0 ~ 100
	 */
	public int getEvilLevel(){
		return evilLevel;
	}

	/**
	 * 设置恶意等级
	 *
	 * @param evilLevel
	 * 		恶意等级
	 */
	public void setEvilLevel(int evilLevel){
		this.evilLevel = evilLevel;
	}

	/**
	 * 返回验证错误信息
	 *
	 * @return 验证错误信息
	 */
	public String getErrMsg(){
		return errMsg;
	}

	/**
	 * 设置验证错误信息
	 *
	 * @param errMsg
	 * 		验证错误信息
	 */
	public void setErrMsg(String errMsg){
		this.errMsg = errMsg;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("response=" + response)
				.add("evilLevel=" + evilLevel)
				.add("errMsg=" + errMsg)
				.toString();
	}

}
