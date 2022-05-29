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
package com.buession.security.captcha.geetest.api.v4;

import com.buession.security.captcha.geetest.GeetestValidateResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.StringJoiner;

/**
 * 极验 V4 版二次校验返回结果
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeetestV4ValidateResponse implements GeetestValidateResponse {

	private final static long serialVersionUID = -5177885027352003530L;

	/**
	 * 二次校验结果
	 */
	private String result;

	/**
	 * 错误码
	 */
	private String code;

	/**
	 * 错误消息
	 */
	private String msg;

	/**
	 * 校验结果说明
	 */
	private String reason;

	/**
	 * 验证输出参数
	 */
	@JsonProperty(value = "captcha_args")
	private Map<String, Object> captchaArgs;

	/**
	 * 返回二次校验结果
	 *
	 * @return 二次校验结果
	 */
	public String getResult(){
		return result;
	}

	/**
	 * 设置二次校验结果
	 *
	 * @param result
	 * 		二次校验结果
	 */
	public void setResult(String result){
		this.result = result;
	}

	/**
	 * 返回错误码
	 *
	 * @return 错误码
	 */
	public String getCode(){
		return code;
	}

	/**
	 * 设置错误码
	 *
	 * @param code
	 * 		错误码
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * 返回错误消息
	 *
	 * @return 错误消息
	 */
	public String getMsg(){
		return msg;
	}

	/**
	 * 设置错误消息
	 *
	 * @param msg
	 * 		错误消息
	 */
	public void setMsg(String msg){
		this.msg = msg;
	}

	/**
	 * 返回校验结果说明
	 *
	 * @return 校验结果说明
	 */
	public String getReason(){
		return reason;
	}

	/**
	 * 设置校验结果说明
	 *
	 * @param reason
	 * 		校验结果说明
	 */
	public void setReason(String reason){
		this.reason = reason;
	}

	/**
	 * 返回验证输出参数
	 *
	 * @return 验证输出参数
	 */
	public Map<String, Object> getCaptchaArgs(){
		return captchaArgs;
	}

	/**
	 * 设置验证输出参数
	 *
	 * @param captchaArgs
	 * 		验证输出参数
	 */
	public void setCaptchaArgs(Map<String, Object> captchaArgs){
		this.captchaArgs = captchaArgs;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("result=" + result)
				.add("code=" + code)
				.add("msg=" + msg)
				.add("reason=" + reason)
				.add("captchaArgs=" + captchaArgs)
				.toString();
	}

}
