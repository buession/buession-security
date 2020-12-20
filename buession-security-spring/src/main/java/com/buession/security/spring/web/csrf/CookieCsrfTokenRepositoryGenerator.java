/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.spring.web.csrf;

import com.buession.core.validator.Validate;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Cookie Csrf Token Repository 生成器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class CookieCsrfTokenRepositoryGenerator extends AbstractCsrfTokenRepositoryGenerator<CookieCsrfTokenRepository> {

	/**
	 * 默认 Csrf Cookie 名称
	 */
	public final static String DEFAULT_CSRF_COOKIE_NAME = "Xsrf-Token";

	/**
	 * Csrf Cookie 名称
	 */
	private String cookieName = DEFAULT_CSRF_COOKIE_NAME;

	/**
	 * Csrf Cookie 作用域
	 */
	private String cookieDomain;

	/**
	 * Csrf Cookie 作用路径
	 */
	private String cookiePath;

	/**
	 * Csrf Cookie 是否可通过客户端脚本访问
	 */
	private boolean cookieHttpOnly = true;

	/**
	 * 构造函数
	 */
	public CookieCsrfTokenRepositoryGenerator(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param cookieName
	 * 		Csrf Cookie 名称
	 * @param cookieDomain
	 * 		Csrf Cookie 作用域
	 * @param cookiePath
	 * 		Csrf Cookie 作用路径
	 * @param cookieHttpOnly
	 * 		Csrf Cookie 是否可通过客户端脚本访问
	 */
	public CookieCsrfTokenRepositoryGenerator(String cookieName, String cookieDomain, String cookiePath,
			boolean cookieHttpOnly){
		this.cookieName = cookieName;
		this.cookieDomain = cookieDomain;
		this.cookiePath = cookiePath;
		this.cookieHttpOnly = cookieHttpOnly;
	}

	/**
	 * 构造函数
	 *
	 * @param parameterName
	 * 		Csrf 请求参数名
	 * @param headerName
	 * 		Csrf 请求头名称
	 * @param cookieName
	 * 		Csrf Cookie 名称
	 * @param cookieDomain
	 * 		Csrf Cookie 作用域
	 * @param cookiePath
	 * 		Csrf Cookie 作用路径
	 * @param cookieHttpOnly
	 * 		Csrf Cookie 是否可通过客户端脚本访问
	 */
	public CookieCsrfTokenRepositoryGenerator(String parameterName, String headerName, String cookieName,
			String cookieDomain, String cookiePath, boolean cookieHttpOnly){
		this(cookieName, cookieDomain, cookiePath, cookieHttpOnly);
		setParameterName(parameterName);
		setHeaderName(headerName);
	}

	/**
	 * 返回 Csrf Cookie 名称
	 *
	 * @return Csrf Cookie 名称
	 */
	public String getCookieName(){
		return cookieName;
	}

	/**
	 * 设置 Csrf Cookie 名称
	 *
	 * @param cookieName
	 * 		Csrf Cookie 名称
	 */
	public void setCookieName(String cookieName){
		this.cookieName = cookieName;
	}

	/**
	 * 返回 Csrf Cookie 作用域
	 *
	 * @return Csrf Cookie 作用域
	 */
	public String getCookieDomain(){
		return cookieDomain;
	}

	/**
	 * 设置 Csrf Cookie 作用域
	 *
	 * @param cookieDomain
	 * 		Csrf Cookie 作用域
	 */
	public void setCookieDomain(String cookieDomain){
		this.cookieDomain = cookieDomain;
	}

	/**
	 * 返回 Csrf Cookie 作用路径
	 *
	 * @return Csrf Cookie 作用路径
	 */
	public String getCookiePath(){
		return cookiePath;
	}

	/**
	 * 设置 Csrf Cookie 作用路径
	 *
	 * @param cookiePath
	 * 		Csrf Cookie 作用路径
	 */
	public void setCookiePath(String cookiePath){
		this.cookiePath = cookiePath;
	}

	/**
	 * 返回 Csrf Cookie 是否可通过客户端脚本访问
	 *
	 * @return Csrf Cookie 是否可通过客户端脚本访问
	 */
	public boolean isCookieHttpOnly(){
		return getCookieHttpOnly();
	}

	/**
	 * 返回 Csrf Cookie 是否可通过客户端脚本访问
	 *
	 * @return Csrf Cookie 是否可通过客户端脚本访问
	 */
	public boolean getCookieHttpOnly(){
		return cookieHttpOnly;
	}

	/**
	 * 设置 Csrf Cookie 是否可通过客户端脚本访问
	 *
	 * @param cookieHttpOnly
	 * 		Csrf Cookie 是否可通过客户端脚本访问
	 */
	public void setCookieHttpOnly(boolean cookieHttpOnly){
		this.cookieHttpOnly = cookieHttpOnly;
	}

	@Override
	public CookieCsrfTokenRepository generate(){
		CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();

		if(Validate.hasText(getParameterName())){
			csrfTokenRepository.setParameterName(getParameterName());
		}

		if(Validate.hasText(getHeaderName())){
			csrfTokenRepository.setHeaderName(getHeaderName());
		}

		if(Validate.hasText(getCookieName())){
			csrfTokenRepository.setCookieName(getCookieName());
		}

		if(Validate.hasText(getCookieDomain())){
			csrfTokenRepository.setCookieDomain(getCookieDomain());
		}

		if(Validate.hasText(getCookiePath())){
			csrfTokenRepository.setCookiePath(getCookiePath());
		}

		csrfTokenRepository.setCookieHttpOnly(getCookieHttpOnly());

		return csrfTokenRepository;
	}

}
