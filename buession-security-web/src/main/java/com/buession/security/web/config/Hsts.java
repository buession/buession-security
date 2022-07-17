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

import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.StringJoiner;

/**
 * Hsts 配置
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/HTTP_Strict_Transport_Security"
 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/HTTP_Strict_Transport_Security</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Hsts {

	private final static long DEFAULT_MAX_AGE = 31536000L;

	/**
	 * 是否启用 Hsts
	 */
	private boolean enabled = true;

	/**
	 * Request 匹配器类
	 */
	private Class<? extends RequestMatcher> matcher;

	/**
	 * 缓存时间，在浏览器收到这个请求后的 maxAge 秒的时间内凡是访问这个域名下的请求都使用HTTPS请求，默认缓存一年
	 */
	private Long maxAge = DEFAULT_MAX_AGE;

	/**
	 * 配置此规则也适用于该网站的所有子域名
	 */
	private Boolean includeSubDomains;

	/**
	 * 配置是否预加载 HSTS
	 */
	private Boolean preload;

	/**
	 * 返回是否启用 Hsts
	 *
	 * @return 是否启用 Hsts
	 */
	public boolean isEnabled(){
		return getEnabled();
	}

	/**
	 * 返回是否启用 Hsts
	 *
	 * @return 是否启用 Hsts
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

	/**
	 * 返回 Request 匹配器类
	 *
	 * @return Request 匹配器类
	 */
	public Class<? extends RequestMatcher> getMatcher(){
		return matcher;
	}

	/**
	 * 设置 Request 匹配器类
	 *
	 * @param matcher
	 * 		Request 匹配器类
	 */
	public void setMatcher(Class<? extends RequestMatcher> matcher){
		this.matcher = matcher;
	}

	/**
	 * 返回缓存时间，在浏览器收到这个请求后的 maxAge 秒的时间内凡是访问这个域名下的请求都使用HTTPS请求
	 *
	 * @return 缓存时间
	 */
	public Long getMaxAge(){
		return maxAge;
	}

	/**
	 * 设置缓存时间，设置在浏览器收到这个请求后的 maxAge 秒的时间内凡是访问这个域名下的请求都使用HTTPS请求
	 *
	 * @param maxAge
	 * 		缓存时间（单位：秒）
	 */
	public void setMaxAge(Long maxAge){
		this.maxAge = maxAge;
	}

	/**
	 * 返回此规则是否适用于该网站的所有子域名
	 *
	 * @return 此规则是否适用于该网站的所有子域名
	 */
	@Deprecated
	public boolean isIncludeSubDomains(){
		return getIncludeSubDomains();
	}

	/**
	 * 返回此规则是否适也用于该网站的所有子域名
	 *
	 * @return 此规则是否也适用于该网站的所有子域名
	 */
	public Boolean getIncludeSubDomains(){
		return includeSubDomains;
	}

	/**
	 * 设置此规则是否也适用于该网站的所有子域名
	 *
	 * @param includeSubDomains
	 * 		此规则是否也适用于该网站的所有子域名
	 */
	public void setIncludeSubDomains(Boolean includeSubDomains){
		this.includeSubDomains = includeSubDomains;
	}

	/**
	 * 返回是否预加载 HSTS
	 *
	 * @return 是否预加载 HSTS
	 */
	@Deprecated
	public Boolean isPreload(){
		return getPreload();
	}

	/**
	 * 返回是否预加载 HSTS
	 *
	 * @return 是否预加载 HSTS
	 */
	public Boolean getPreload(){
		return preload;
	}

	/**
	 * 设置是否预加载 HSTS
	 *
	 * @param preload
	 * 		是否预加载 HSTS
	 */
	public void setPreload(Boolean preload){
		this.preload = preload;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "Hsts = {", "}")
				.add("enabled=" + enabled)
				.add("matcher=" + matcher)
				.add("maxAge=" + maxAge)
				.add("includeSubDomains=" + includeSubDomains)
				.add("preload=" + preload)
				.toString();
	}

}
