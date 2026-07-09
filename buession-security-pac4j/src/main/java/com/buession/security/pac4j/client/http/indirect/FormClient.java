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
package com.buession.security.pac4j.client.http.indirect;

import org.pac4j.core.context.CallContext;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.AccountNotFoundException;
import org.pac4j.core.exception.BadCredentialsException;
import org.pac4j.core.exception.http.HttpAction;
import org.pac4j.core.exception.http.UnauthorizedAction;
import org.pac4j.core.profile.creator.ProfileCreator;

/**
 * {@link org.pac4j.http.client.indirect.FormClient} 扩展，支持区分未认证或认证失败
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class FormClient extends org.pac4j.http.client.indirect.FormClient {

	/**
	 * 构造函数
	 */
	public FormClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param loginUrl
	 * 		登录地址
	 * @param usernamePasswordAuthenticator
	 * 		a {@link Authenticator} object
	 */
	public FormClient(String loginUrl, Authenticator usernamePasswordAuthenticator) {
		super(loginUrl, usernamePasswordAuthenticator);
	}

	/**
	 * 构造函数
	 *
	 * @param loginUrl
	 * 		登录地址
	 * @param usernameParameter
	 * 		用户名参数名
	 * @param passwordParameter
	 * 		密码参数名
	 * @param usernamePasswordAuthenticator
	 * 		a {@link Authenticator} object
	 */
	public FormClient(String loginUrl, String usernameParameter, String passwordParameter,
	                  Authenticator usernamePasswordAuthenticator) {
		super(loginUrl, usernameParameter, passwordParameter, usernamePasswordAuthenticator);
	}

	/**
	 * 构造函数
	 *
	 * @param loginUrl
	 * 		登录地址
	 * @param usernamePasswordAuthenticator
	 * 		a {@link Authenticator} object
	 * @param profileCreator
	 * 		a {@link ProfileCreator} object
	 */
	public FormClient(String loginUrl, Authenticator usernamePasswordAuthenticator, ProfileCreator profileCreator) {
		super(loginUrl, usernamePasswordAuthenticator, profileCreator);
	}

	/**
	 * <p>handleInvalidCredentials.</p>
	 *
	 * @param ctx
	 * 		a {@link CallContext} object
	 * @param username
	 * 		a {@link String} object
	 * @param message
	 * 		a {@link String} object
	 * @param errorMessage
	 * 		a {@link String} object
	 *
	 * @return a {@link HttpAction} object
	 */
	@Override
	protected HttpAction handleInvalidCredentials(final CallContext ctx, final String username, final String message,
	                                              final String errorMessage) {
		// it's an AJAX request -> unauthorized (instead of a redirection)
		if(getAjaxRequestResolver().isAjax(ctx)){
			logger.info("AJAX request detected -> returning 401");

			if("AccountNotFoundException".equals(errorMessage)){
				UnauthorizedAction unauthenticatedAction = new UnauthorizedAction();

				unauthenticatedAction.initCause(new AccountNotFoundException(username));

				return unauthenticatedAction;
			}else if("BadCredentialsException".equals(errorMessage)){
				UnauthorizedAction unauthenticatedAction = new UnauthorizedAction();

				unauthenticatedAction.initCause(new BadCredentialsException(username));

				return unauthenticatedAction;
			}
		}

		return super.handleInvalidCredentials(ctx, username, message, errorMessage);
	}

}
