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

import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Hpkp 配置
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Public_Key_Pinning"
 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Public_Key_Pinning</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Hpkp {

	/**
	 * 是否启用 Hpkp
	 */
	private boolean enabled = true;

	private Map<String, String> pins;

	private String[] sha256Pins;

	/**
	 * 浏览器应记住仅使用其中一个已定义的密钥访问此站点的时间
	 */
	private long maxAge;

	/**
	 * 配置此规则也适用于该网站的所有子域名
	 */
	private boolean includeSubDomains;

	private boolean reportOnly;

	private String reportUri;

	/**
	 * 返回是否启用 Hpkp
	 *
	 * @return 是否启用 Hpkp
	 */
	public boolean isEnabled(){
		return getEnabled();
	}

	/**
	 * 返回是否启用 Hpkp
	 *
	 * @return 是否启用 Hpkp
	 */
	public boolean getEnabled(){
		return enabled;
	}

	/**
	 * 配置是否启用 Hsts
	 *
	 * @param enabled
	 * 		是否启用 Hsts
	 */
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public Map<String, String> getPins(){
		return pins;
	}

	public void setPins(Map<String, String> pins){
		this.pins = pins;
	}

	public String[] getSha256Pins(){
		return sha256Pins;
	}

	public void setSha256Pins(String[] sha256Pins){
		this.sha256Pins = sha256Pins;
	}

	/**
	 * 返回浏览器应记住仅使用其中一个已定义的密钥访问此站点的时间
	 *
	 * @return 浏览器应记住仅使用其中一个已定义的密钥访问此站点的时间
	 */
	public long getMaxAge(){
		return maxAge;
	}

	/**
	 * 设置浏览器应记住仅使用其中一个已定义的密钥访问此站点的时间
	 *
	 * @param maxAge
	 * 		浏览器应记住仅使用其中一个已定义的密钥访问此站点的时间
	 */
	public void setMaxAge(long maxAge){
		this.maxAge = maxAge;
	}

	/**
	 * 返回此规则是否也适用于该网站的所有子域名
	 *
	 * @return 配置是否也规则也适用于该网站的所有子域名
	 */
	public boolean isIncludeSubDomains(){
		return getIncludeSubDomains();
	}

	/**
	 * 返回此规则是否也适用于该网站的所有子域名
	 *
	 * @return 配置是否也规则也适用于该网站的所有子域名
	 */
	public boolean getIncludeSubDomains(){
		return includeSubDomains;
	}

	/**
	 * 设置此规则是否也适用于该网站的所有子域名
	 *
	 * @param includeSubDomains
	 * 		此规则是否也也适用于该网站的所有子域名
	 */
	public void setIncludeSubDomains(boolean includeSubDomains){
		this.includeSubDomains = includeSubDomains;
	}

	public boolean isReportOnly(){
		return getReportOnly();
	}

	public boolean getReportOnly(){
		return reportOnly;
	}

	public void setReportOnly(boolean reportOnly){
		this.reportOnly = reportOnly;
	}

	public String getReportUri(){
		return reportUri;
	}

	public void setReportUri(String reportUri){
		this.reportUri = reportUri;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "Hpkp = {", "}")
				.add("enabled=" + enabled)
				.add("pins=" + pins)
				.add("sha256Pins=" + Arrays.toString(sha256Pins))
				.add("maxAge=" + maxAge)
				.add("includeSubDomains=" + includeSubDomains)
				.add("reportOnly=" + reportOnly)
				.add("reportUri=" + reportUri)
				.toString();
	}

}
