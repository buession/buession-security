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
package com.buession.security.spring.web.csrf;

/**
 * Csrf Token Repository
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface CsrfTokenRepository {

	/**
	 * 默认 Csrf Cookie 名称
	 */
	String DEFAULT_CSRF_COOKIE_NAME = "XSRF-TOKEN";

	/**
	 * 默认 Csrf Session 名称
	 */
	String DEFAULT_CSRF_SESSION_ATTRIBUTE_NAME = CsrfTokenRepository.class.getName() + ".CSRF_TOKEN";

	/**
	 * 默认 Csrf 参数名称
	 */
	String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

	/**
	 * 默认 Csrf 响应头名称
	 */
	String DEFAULT_CSRF_HEADER_NAME = "X-Xsrf-Token";

}
