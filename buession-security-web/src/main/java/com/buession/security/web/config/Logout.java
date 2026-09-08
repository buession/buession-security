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
 * 退出登录
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class Logout extends BaseConfig {

	/**
	 * 退出登录地址
	 */
	private String logoutUrl;

	/**
	 * 退出登录成功跳转地址
	 */
	private String logoutSuccessUrl = "/login";

	private boolean permitAll;

	private boolean clearAuthentication = true;

	private boolean invalidateHttpSession;

	/**
	 * 构造函数
	 */
	public Logout() {
		super(false);
	}

	/**
	 * 获取退出登录成功跳转地址
	 *
	 * @return 退出登录成功跳转地址
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * 设置退出登录成功跳转地址
	 *
	 * @param logoutUrl
	 * 		退出登录成功跳转地址
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * 获取退出登录成功跳转地址
	 *
	 * @return 退出登录成功跳转地址
	 */
	public String getLogoutSuccessUrl() {
		return logoutSuccessUrl;
	}

	/**
	 * 设置退出登录成功跳转地址
	 *
	 * @param logoutSuccessUrl
	 * 		退出登录成功跳转地址
	 */
	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}

	public boolean isPermitAll() {
		return getPermitAll();
	}

	public boolean getPermitAll() {
		return permitAll;
	}

	public void setPermitAll(boolean permitAll) {
		this.permitAll = permitAll;
	}

	public boolean isClearAuthentication() {
		return getClearAuthentication();
	}

	public boolean getClearAuthentication() {
		return clearAuthentication;
	}

	public void setClearAuthentication(boolean clearAuthentication) {
		this.clearAuthentication = clearAuthentication;
	}

	public boolean isInvalidateHttpSession() {
		return getInvalidateHttpSession();
	}

	public boolean getInvalidateHttpSession() {
		return invalidateHttpSession;
	}

	public void setInvalidateHttpSession(boolean invalidateHttpSession) {
		this.invalidateHttpSession = invalidateHttpSession;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add("enabled=" + getEnabled())
				.add("logoutUrl=" + logoutUrl)
				.add("logoutSuccessUrl=" + logoutSuccessUrl)
				.add("permitAll=" + permitAll)
				.add("clearAuthentication=" + clearAuthentication)
				.add("invalidateHttpSession=" + invalidateHttpSession)
				.toString();
	}

}
