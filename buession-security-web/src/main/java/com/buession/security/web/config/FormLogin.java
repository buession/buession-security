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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.config;

import java.util.StringJoiner;

/**
 * 登录表单
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class FormLogin extends BaseConfig {

	/**
	 * 登录页地址
	 */
	private String loginPage;

	/**
	 * 用户名参数名称
	 *
	 * @since 4.0.0
	 */
	private String usernameParameter;

	/**
	 * 密码参数名称
	 *
	 * @since 4.0.0
	 */
	private String passwordParameter;

	/**
	 * 认证成功跳转地址
	 *
	 * @since 4.0.0
	 */
	private String successForwardUrl;

	/**
	 * 认证失败跳转地址
	 *
	 * @since 4.0.0
	 */
	private String failureForwardUrl;

	public FormLogin() {
		super(false);
	}

	/**
	 * 返回登录页地址
	 *
	 * @return 登录页地址
	 */
	public String getLoginPage() {
		return loginPage;
	}

	/**
	 * 设置登录页地址
	 *
	 * @param loginPage
	 * 		登录页地址
	 */
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	/**
	 * 返回用户名参数名称
	 *
	 * @return 用户名参数名称
	 *
	 * @since 4.0.0
	 */
	public String getUsernameParameter() {
		return usernameParameter;
	}

	/**
	 * 设置用户名参数名称
	 *
	 * @param usernameParameter
	 * 		用户名参数名称
	 *
	 * @since 4.0.0
	 */
	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	/**
	 * 返回密码参数名称
	 *
	 * @return 密码参数名称
	 *
	 * @since 4.0.0
	 */
	public String getPasswordParameter() {
		return passwordParameter;
	}

	/**
	 * 设置密码参数名称
	 *
	 * @param passwordParameter
	 * 		密码参数名称
	 *
	 * @since 4.0.0
	 */
	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	/**
	 * 返回认证成功跳转地址
	 *
	 * @return 认证成功跳转地址
	 *
	 * @since 4.0.0
	 */
	public String getSuccessForwardUrl() {
		return successForwardUrl;
	}

	/**
	 * 设置认证成功跳转地址
	 *
	 * @param successForwardUrl
	 * 		认证成功跳转地址
	 *
	 * @since 4.0.0
	 */
	public void setSuccessForwardUrl(String successForwardUrl) {
		this.successForwardUrl = successForwardUrl;
	}

	/**
	 * 返回认证失败跳转地址
	 *
	 * @return 认证失败跳转地址
	 *
	 * @since 4.0.0
	 */
	public String getFailureForwardUrl() {
		return failureForwardUrl;
	}

	/**
	 * 设置认证失败跳转地址
	 *
	 * @param failureForwardUrl
	 * 		认证失败跳转地址
	 *
	 * @since 4.0.0
	 */
	public void setFailureForwardUrl(String failureForwardUrl) {
		this.failureForwardUrl = failureForwardUrl;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add("enabled=" + getEnabled())
				.add("loginPage=" + loginPage)
				.add("usernameParameter=" + usernameParameter)
				.add("passwordParameter=" + passwordParameter)
				.add("successForwardUrl=" + successForwardUrl)
				.add("failureForwardUrl=" + failureForwardUrl)
				.toString();
	}

}
