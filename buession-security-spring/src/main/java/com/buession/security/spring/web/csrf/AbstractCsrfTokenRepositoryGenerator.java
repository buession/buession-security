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

import org.springframework.security.web.csrf.CsrfTokenRepository;

/**
 * Csrf Token Repository 生成器抽象类
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public abstract class AbstractCsrfTokenRepositoryGenerator<R extends CsrfTokenRepository> implements CsrfTokenRepositoryGenerator<R> {

	/**
	 * 默认 Csrf 请求参数名
	 */
	public final static String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

	/**
	 * 默认 Csrf 请求头名称
	 */
	public final static String DEFAULT_CSRF_HEADER_NAME = "X-Xsrf-Token";

	/**
	 * Csrf 请求参数名
	 */
	private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

	/**
	 * Csrf 请求头名称
	 */
	private String headerName = DEFAULT_CSRF_HEADER_NAME;

	/**
	 * 构造函数
	 */
	public AbstractCsrfTokenRepositoryGenerator(){
	}

	/**
	 * 构造函数
	 *
	 * @param parameterName
	 * 		Csrf 请求参数名
	 * @param headerName
	 * 		Csrf 请求头名称
	 */
	public AbstractCsrfTokenRepositoryGenerator(String parameterName, String headerName){
		setParameterName(parameterName);
		setHeaderName(headerName);
	}

	/**
	 * 获取 Csrf 请求参数名
	 *
	 * @return Csrf 请求参数名
	 */
	public String getParameterName(){
		return parameterName;
	}

	/**
	 * 设置 Csrf 请求参数名
	 *
	 * @param parameterName
	 * 		Csrf 请求参数名
	 */
	public void setParameterName(String parameterName){
		this.parameterName = parameterName;
	}

	/**
	 * 获取 Csrf 请求头名称
	 *
	 * @return Csrf 请求头名称
	 */
	public String getHeaderName(){
		return headerName;
	}

	/**
	 * 设置 Csrf 请求头名称
	 *
	 * @param headerName
	 * 		Csrf 请求头名称
	 */
	public void setHeaderName(String headerName){
		this.headerName = headerName;
	}

}
