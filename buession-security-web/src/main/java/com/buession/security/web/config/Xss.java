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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.config;

import com.buession.web.http.XssProtection;

import java.util.StringJoiner;

/**
 * XSS 配置
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-XSS-Protection"
 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-XSS-Protection</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Xss {

	/**
	 * 是否启用 Xss 配置
	 */
	private boolean enabled = true;

	/**
	 * 策略模式
	 *
	 * @since 4.0.0
	 */
	private XssProtection policy;

	/**
	 * 返回是否启用 Xss 配置
	 *
	 * @return 是否启用 Xss 配置
	 */
	public boolean isEnabled() {
		return getEnabled();
	}

	/**
	 * 返回是否启用 Xss 配置
	 *
	 * @return 是否启用 Xss 配置
	 */
	public boolean getEnabled() {
		return enabled;
	}

	/**
	 * 配置是否启用 Xss 配置
	 *
	 * @param enabled
	 * 		是否启用 Xss 配置
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 返回策略模式
	 *
	 * @return 策略模式
	 *
	 * @since 4.0.0
	 */
	public XssProtection getPolicy() {
		return policy;
	}

	/**
	 * 设置策略模式
	 *
	 * @param policy
	 * 		策略模式
	 *
	 * @since 4.0.0
	 */
	public void setPolicy(XssProtection policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "Xss = {", "}")
				.add("enabled=" + enabled)
				.add("policy=" + policy)
				.toString();
	}

}
