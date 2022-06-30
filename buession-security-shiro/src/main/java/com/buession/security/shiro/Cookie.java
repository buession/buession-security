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
package com.buession.security.shiro;

import com.buession.web.http.SameSite;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 响应 Cookie，关于 Set-Cookie 的描述，参考：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cookie" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cookie</a>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Cookie implements Serializable {

	private final static long serialVersionUID = -3254679847210465610L;

	/**
	 * Cookie 名称
	 */
	private String name;

	/**
	 * Cookie 作用域
	 */
	private String domain;

	/**
	 * Cookie 作用路径
	 */
	private String path;

	/**
	 * Cookie 最大有效期
	 */
	private Integer maxAge;

	/**
	 * 是否启用安全 Cookie
	 */
	private Boolean secure;

	/**
	 * 是否开启 HttpOnly
	 */
	private Boolean httpOnly;

	/**
	 * 限制第三方 Cookie 方式
	 */
	private SameSite sameSite;

	/**
	 * 构造函数
	 */
	public Cookie(){
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 */
	public Cookie(String name){
		this.name = name;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param maxAge
	 * 		Cookie 最大有效期
	 */
	public Cookie(String name, int maxAge){
		this.name = name;
		this.maxAge = maxAge;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public Cookie(String name, boolean secure){
		this.name = name;
		this.secure = secure;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public Cookie(String name, int maxAge, boolean secure){
		this(name, maxAge);
		this.secure = secure;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 * @param httpOnly
	 * 		是否开启 HttpOnly
	 */
	public Cookie(String name, int maxAge, boolean secure, boolean httpOnly){
		this(name, maxAge, secure);
		this.httpOnly = httpOnly;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 */
	public Cookie(String name, String domain){
		this.name = name;
		this.domain = domain;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param maxAge
	 * 		Cookie 最大有效期
	 */
	public Cookie(String name, String domain, int maxAge){
		this(name, domain);
		this.maxAge = maxAge;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public Cookie(String name, String domain, boolean secure){
		this(name, domain);
		this.secure = secure;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public Cookie(String name, String domain, int maxAge, boolean secure){
		this(name, domain, maxAge);
		this.secure = secure;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 * @param httpOnly
	 * 		是否开启 HttpOnly
	 */
	public Cookie(String name, String domain, int maxAge, boolean secure, boolean httpOnly){
		this(name, domain, maxAge, secure);
		this.httpOnly = httpOnly;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 */
	public Cookie(String name, String domain, String path){
		this(name, domain);
		this.path = path;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 * @param maxAge
	 * 		Cookie 最大有效期
	 */
	public Cookie(String name, String domain, String path, int maxAge){
		this(name, domain, path);
		this.maxAge = maxAge;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public Cookie(String name, String domain, String path, boolean secure){
		this(name, domain, path);
		this.secure = secure;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 * @param secure
	 * 		是否启用安全 Cookie
	 * @param httpOnly
	 * 		是否开启 HttpOnly
	 */
	public Cookie(String name, String domain, String path, boolean secure, boolean httpOnly){
		this(name, domain, path, secure);
		this.httpOnly = httpOnly;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public Cookie(String name, String domain, String path, int maxAge, boolean secure){
		this(name, domain, path, maxAge);
		this.secure = secure;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 * @param httpOnly
	 * 		是否开启 HttpOnly
	 */
	public Cookie(String name, String domain, String path, int maxAge, boolean secure, boolean httpOnly){
		this(name, domain, path, maxAge, secure);
		this.httpOnly = httpOnly;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		Cookie 名称
	 * @param domain
	 * 		Cookie 作用域
	 * @param path
	 * 		Cookie 作用路径
	 * @param maxAge
	 * 		Cookie 最大有效期
	 * @param secure
	 * 		是否启用安全 Cookie
	 * @param httpOnly
	 * 		是否开启 HttpOnly
	 * @param sameSite
	 *        {@link SameSite}
	 */
	public Cookie(String name, String domain, String path, int maxAge, boolean secure, boolean httpOnly,
				  SameSite sameSite){
		this(name, domain, path, maxAge, secure, httpOnly);
		this.sameSite = sameSite;
	}

	/**
	 * 返回 Cookie 名称
	 *
	 * @return Cookie 名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置 Cookie 名称
	 *
	 * @param name
	 * 		Cookie 名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 返回 Cookie 作用域
	 *
	 * @return Cookie 作用域
	 */
	public String getDomain(){
		return domain;
	}

	/**
	 * 设置 Cookie 作用域
	 *
	 * @param domain
	 * 		Cookie 作用域
	 */
	public void setDomain(String domain){
		this.domain = domain;
	}

	/**
	 * 返回 Cookie 作用路径
	 *
	 * @return Cookie 作用路径
	 */
	public String getPath(){
		return path;
	}

	/**
	 * 设置 Cookie 作用路径
	 *
	 * @param path
	 * 		Cookie 作用路径
	 */
	public void setPath(String path){
		this.path = path;
	}

	/**
	 * 返回 Cookie 最大有效期
	 *
	 * @return Cookie 最大有效期
	 */
	public Integer getMaxAge(){
		return maxAge;
	}

	/**
	 * 设置 Cookie 最大有效期
	 *
	 * @param maxAge
	 * 		Cookie 最大有效期
	 */
	public void setMaxAge(Integer maxAge){
		this.maxAge = maxAge;
	}

	/**
	 * 返回是否启用安全 Cookie
	 *
	 * @return 是否启用安全 Cookie
	 */
	public Boolean getSecure(){
		return secure;
	}

	/**
	 * 设置是否启用安全 Cookie
	 *
	 * @param secure
	 * 		是否启用安全 Cookie
	 */
	public void setSecure(Boolean secure){
		this.secure = secure;
	}

	/**
	 * 返回是否开启 HttpOnly
	 *
	 * @return 是否开启 HttpOnly
	 */
	public Boolean getHttpOnly(){
		return httpOnly;
	}

	/**
	 * 设置是否开启 HttpOnly
	 *
	 * @param httpOnly
	 * 		是否开启 HttpOnly
	 */
	public void setHttpOnly(Boolean httpOnly){
		this.httpOnly = httpOnly;
	}

	/**
	 * 返回 {@link SameSite}
	 *
	 * @return {@link SameSite}
	 */
	public SameSite getSameSite(){
		return sameSite;
	}

	/**
	 * 设置 {@link SameSite}
	 *
	 * @param sameSite
	 *        {@link SameSite}
	 */
	public void setSameSite(SameSite sameSite){
		this.sameSite = sameSite;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "[", "]")
				.add("name=" + name)
				.add("domain=" + domain)
				.add("path=" + path)
				.add("maxAge=" + maxAge)
				.add("secure=" + secure)
				.add("httpOnly=" + httpOnly)
				.add("sameSite=" + sameSite)
				.toString();
	}

}