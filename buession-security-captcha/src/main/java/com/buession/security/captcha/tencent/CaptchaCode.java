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

import java.util.StringJoiner;

/**
 * 错误码
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public enum CaptchaCode {

	OK(1, "OK", "验证通过"),

	USER_CODE_LEN_ERROR(6, "user code len error", "验证码长度不匹配，请检查请求是否带Randstr参数，Randstr参数大小写是否有误"),

	CAPTCHA_NO_MATCH(7, "captcha no match", "验证码答案不匹配/Randstr参数不匹配，请重新生成Randstr、Ticket进行校验"),

	VERIFY_TIMEOUT(8, "verify timeout", "验证码签名超时，票据已过期，请重新生成Randstr、Ticket票进行校验"),

	SEQUNCE_REPEAT(9, "Sequnce repeat", "验证码签名重放，票据重复使用，请重新生成Randstr、Ticket进行校验"),

	SEQUNCE_INVALID(10, "Sequnce invalid", "验证码签名序列"),

	COOKIE_INVALID(11, "Cookie invalid", "验证码cookie信息不合法，非法请求，可能存在不规范接入"),

	SIG_LEN_ERROR(12, "sig len error", "签名长度错误"),

	VERIFY_IP_NO_MATCH(13, "verify ip no match", "ip不匹配，非法请求，可能存在不规范接入"),

	DECRYPT_FAIL(15, "decrypt fail", "验证码签名解密失败，票据校验失败，请检查Ticket票据是否与前端返回Ticket一致"),

	APPID_NO_MATCH(16, "appid no match",
			"验证码强校验appid错误，前端代码 data-appid 和后端 CaptchaAppId 所填写的值，必须和 验证码控制台 中【验证详情】>【基础配置】内的 AppID 一致,请检查CaptchaAppId是否为控制台基础配置界面系统分配的APPID"),

	CMD_NO_MUCH(17, "cmd no much", "验证码系统命令不匹配"),

	UIN_NO_MATCH(18, "uin no match", "号码不匹配"),

	SEQ_REDIRECT(19, "seq redirect", "重定向验证"),

	OPT_NO_VCODE(20, "opt no vcode", "操作使用pt免验证码校验错误"),

	DIFF(21, "diff", "差别，验证错误 "),

	CAPTCHA_TYPE_NOT_MATCH(22, "captcha type not match", "验证码类型与拉取时不一致"),

	VERIFY_TYPE_ERROR(23, "verify type error", "验证类型错误"),

	INVALID_PKG(24, "invalid pkg", "非法请求包"),

	BAD_VISITOR(25, "bad visitor", "策略拦截"),

	SYSTEM_BUSY(26, "system busy", "系统内部错误"),

	PARAM_ERR_APPSECRETKEY(100, "param err appsecretkey",
			"参数校验错误，CaptchaAppId 与对应 AppSecretKey 不一致，需检查 AppSecretKey 参数是否有误"),

	TICKET_REUSE(104, "Ticket Reuse", "票据重复使用，同个票据验证多次，请重新生成Randstr、Ticket进行校验");

	/**
	 * 错误码
	 */
	private final int code;

	/**
	 * 错误信息
	 */
	private final String message;

	/**
	 * 错误信息中文表示
	 */
	private final String text;

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param message
	 * 		错误信息
	 * @param text
	 * 		错误信息中文表示
	 */
	CaptchaCode(final int code, final String message, final String text){
		this.code = code;
		this.message = message;
		this.text = text;
	}

	/**
	 * 返回错误码
	 *
	 * @return 错误码
	 */
	public int getCode(){
		return code;
	}

	/**
	 * 返回错误信息
	 *
	 * @return 错误信息
	 */
	public String getMessage(){
		return message;
	}

	/**
	 * 返回错误信息中文表示
	 *
	 * @return 错误信息中文表示
	 */
	public String getText(){
		return text;
	}

	/**
	 * 从错误码创建 {@link CaptchaCode} 实例
	 *
	 * @param code
	 * 		错误码
	 *
	 * @return {@link CaptchaCode} 实例
	 */
	public static CaptchaCode fromCode(final int code){
		for(CaptchaCode captchaCode : CaptchaCode.values()){
			if(captchaCode.code == code){
				return captchaCode;
			}
		}

		return null;
	}

	@Override
	public String toString(){
		return new StringJoiner(" ")
				.add(Integer.toString(code))
				.add(message)
				.add(text)
				.toString();
	}

}
