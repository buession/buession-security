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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
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
public class FormLogin {

	/**
	 * 是否启动登录表单
	 */
	private boolean enabled = false;

	/**
	 * 登录页地址
	 */
	private String loginPage;

	/**
	 * 返回是否启动登录表单
	 *
	 * @return 是否启动登录表单
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 设置是否启动登录表单
	 *
	 * @param enabled
	 * 		是否启动登录表单
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	@Override
	public String toString() {
		return new StringJoiner(", ", "FormLogin = {", "}")
				.add("enabled=" + enabled)
				.add("loginPage='" + loginPage + "'")
				.toString();
	}

}
