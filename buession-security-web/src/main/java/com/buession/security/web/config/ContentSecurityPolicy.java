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
package com.buession.security.web.config;

import java.util.StringJoiner;

/**
 * Content Security Policy 配置
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CSP"
 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CSP</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ContentSecurityPolicy {

	/**
	 * 是否启用 ContentSecurityPolicy
	 */
	private boolean enabled = true;

	private Boolean reportOnly;

	/**
	 * 策略
	 */
	private String policyDirectives;

	/**
	 * 返回是否启用 ContentSecurityPolicy
	 *
	 * @return 是否启用 ContentSecurityPolicy
	 */
	public boolean isEnabled(){
		return getEnabled();
	}

	/**
	 * 返回是否启用 ContentSecurityPolicy
	 *
	 * @return 是否启用 ContentSecurityPolicy
	 */
	public boolean getEnabled(){
		return enabled;
	}

	/**
	 * 配置是否启用 ContentSecurityPolicy
	 *
	 * @param enabled
	 * 		是否启用 ContentSecurityPolicy
	 */
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	@Deprecated
	public Boolean isReportOnly(){
		return getReportOnly();
	}

	public Boolean getReportOnly(){
		return reportOnly;
	}

	public void setReportOnly(Boolean reportOnly){
		this.reportOnly = reportOnly;
	}

	/**
	 * 返回策略
	 *
	 * @return 策略
	 */
	public String getPolicyDirectives(){
		return policyDirectives;
	}

	/**
	 * 设置策略
	 *
	 * @param policyDirectives
	 * 		策略
	 */
	public void setPolicyDirectives(String policyDirectives){
		this.policyDirectives = policyDirectives;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "ContentSecurityPolicy = {", "}")
				.add("enabled=" + enabled)
				.add("reportOnly=" + reportOnly)
				.add("policyDirectives=" + policyDirectives)
				.toString();
	}

}
