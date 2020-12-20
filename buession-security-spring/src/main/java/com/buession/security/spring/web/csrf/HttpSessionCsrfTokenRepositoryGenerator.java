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
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Http Session Csrf Token Repository 生成器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class HttpSessionCsrfTokenRepositoryGenerator extends AbstractCsrfTokenRepositoryGenerator<HttpSessionCsrfTokenRepository> {

	/**
	 * 默认 Csrf Session 属性名称
	 */
	public final static String DEFAULT_CSRF_TOKEN_SESSION_ATTRIBUTE_NAME =
			HttpSessionCsrfTokenRepository.class.getName() + ".CSRF_TOKEN";

	/**
	 * Csrf Session 属性名称
	 */
	private String sessionAttributeName = DEFAULT_CSRF_TOKEN_SESSION_ATTRIBUTE_NAME;

	/**
	 * 构造函数
	 */
	public HttpSessionCsrfTokenRepositoryGenerator(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param sessionAttributeName
	 * 		Csrf Session 属性名称
	 */
	public HttpSessionCsrfTokenRepositoryGenerator(String sessionAttributeName){
		this.sessionAttributeName = sessionAttributeName;
	}

	/**
	 * 构造函数
	 *
	 * @param parameterName
	 * 		Csrf 请求参数名
	 * @param headerName
	 * 		Csrf 请求头名称
	 * @param sessionAttributeName
	 * 		Csrf Session 属性名称
	 */
	public HttpSessionCsrfTokenRepositoryGenerator(String parameterName, String headerName,
			String sessionAttributeName){
		this(sessionAttributeName);
		setParameterName(parameterName);
		setHeaderName(headerName);
	}

	/**
	 * 返回 Csrf Session 属性名称
	 *
	 * @return Csrf Session 属性名称
	 */
	public String getSessionAttributeName(){
		return sessionAttributeName;
	}

	/**
	 * 设置 Csrf Session 属性名称
	 *
	 * @param sessionAttributeName
	 * 		Csrf Session 属性名称
	 */
	public void setSessionAttributeName(String sessionAttributeName){
		this.sessionAttributeName = sessionAttributeName;
	}

	@Override
	public HttpSessionCsrfTokenRepository generate(){
		HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

		if(Validate.hasText(getParameterName())){
			csrfTokenRepository.setParameterName(getParameterName());
		}

		if(Validate.hasText(getHeaderName())){
			csrfTokenRepository.setHeaderName(getHeaderName());
		}

		if(Validate.hasText(getSessionAttributeName())){
			csrfTokenRepository.setSessionAttributeName(getSessionAttributeName());
		}

		return csrfTokenRepository;
	}

}
