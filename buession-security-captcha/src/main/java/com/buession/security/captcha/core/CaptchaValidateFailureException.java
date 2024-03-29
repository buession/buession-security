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
package com.buession.security.captcha.core;

import java.util.StringJoiner;

/**
 * 验证码验证异常
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class CaptchaValidateFailureException extends CaptchaException {

	private final static long serialVersionUID = -3533294801605015984L;

	/**
	 * 错误码
	 */
	private final String code;

	/**
	 * 错误信息文本
	 */
	private final String text;

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		错误信息
	 */
	public CaptchaValidateFailureException(String code, String message){
		this(code, message, message);
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		错误信息
	 * @param text
	 * 		错误信息文本
	 */
	public CaptchaValidateFailureException(String code, String message, String text){
		super(message);
		this.code = code;
		this.text = text;
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		错误信息
	 * @param cause
	 * 		上游异常
	 */
	public CaptchaValidateFailureException(String code, String message, Throwable cause){
		this(code, message, message, cause);
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		错误信息
	 * @param text
	 * 		错误信息文本
	 * @param cause
	 * 		上游异常
	 */
	public CaptchaValidateFailureException(String code, String message, String text, Throwable cause){
		super(message, cause);
		this.code = code;
		this.text = text;
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
	 * 返回错误信息文本
	 *
	 * @return 错误信息文本
	 */
	public String getText(){
		return text;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", CaptchaValidateFailureException.class.getSimpleName() + "[", "]")
				.add("code='" + code + "'")
				.add("text='" + text + "'")
				.toString();
	}
}
