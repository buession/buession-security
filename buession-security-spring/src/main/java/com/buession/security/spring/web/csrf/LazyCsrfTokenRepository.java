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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.spring.web.csrf;

import com.buession.core.utils.Assert;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Yong.Teng
 */
public final class LazyCsrfTokenRepository implements CsrfTokenRepository {

	public final static String HEADER_NAME = "X-Csrf-Header";

	public final static String HEADER_TOKEN_NAME = "X-Csrf-Token";

	private final static String HTTP_RESPONSE_ATTRIBUTE = HttpServletResponse.class.getName();

	private final CsrfTokenRepository delegate;

	private String headerName = HEADER_NAME;

	private String headerTokenName = HEADER_TOKEN_NAME;

	public LazyCsrfTokenRepository(CsrfTokenRepository delegate){
		Assert.isNull(delegate, "delegate cannot be null");
		this.delegate = delegate;
	}

	public LazyCsrfTokenRepository(CsrfTokenRepository delegate, String headerName, String headerTokenName){
		this(delegate);
		this.headerName = headerName;
		this.headerTokenName = headerTokenName;
	}

	@Override
	public CsrfToken generateToken(HttpServletRequest request){
		return wrap(request, delegate.generateToken(request));
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response){
		if(token == null){
			delegate.saveToken(token, request, response);
		}
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request){
		return delegate.loadToken(request);
	}

	private CsrfToken wrap(HttpServletRequest request, CsrfToken token){
		HttpServletResponse response = getResponse(request);

		response.addHeader(HEADER_NAME, token.getHeaderName());
		response.addHeader(HEADER_TOKEN_NAME, token.getToken());

		return new SaveOnAccessCsrfToken(delegate, request, response, token);
	}

	private HttpServletResponse getResponse(HttpServletRequest request){
		HttpServletResponse response = (HttpServletResponse) request.getAttribute(HTTP_RESPONSE_ATTRIBUTE);
		Assert.isNull(response, "The HttpServletRequest attribute must contain an HttpServletResponse for the " +
				"attribute " + HTTP_RESPONSE_ATTRIBUTE);
		return response;
	}

	private static final class SaveOnAccessCsrfToken implements CsrfToken {

		private transient CsrfTokenRepository tokenRepository;

		private transient HttpServletRequest request;

		private transient HttpServletResponse response;

		private final CsrfToken delegate;

		SaveOnAccessCsrfToken(CsrfTokenRepository tokenRepository, HttpServletRequest request, HttpServletResponse
				response, CsrfToken delegate){
			this.tokenRepository = tokenRepository;
			this.request = request;
			this.response = response;
			this.delegate = delegate;
		}

		@Override
		public String getHeaderName(){
			return delegate.getHeaderName();
		}

		@Override
		public String getParameterName(){
			return delegate.getParameterName();
		}

		@Override
		public String getToken(){
			saveTokenIfNecessary();
			return delegate.getToken();
		}

		@Override
		public String toString(){
			return "SaveOnAccessCsrfToken [delegate=" + delegate + "]";
		}

		@Override
		public int hashCode(){
			final int prime = 31;
			return prime + ((delegate == null) ? 0 : delegate.hashCode());
		}

		@Override
		public boolean equals(Object obj){
			if(this == obj){
				return true;
			}

			if(obj == null){
				return false;
			}

			if(getClass() != obj.getClass()){
				return false;
			}

			SaveOnAccessCsrfToken that = (SaveOnAccessCsrfToken) obj;
			if(delegate == null){
				if(that.delegate != null){
					return false;
				}
			}else if(delegate.equals(that.delegate) == false){
				return false;
			}

			return true;
		}

		private void saveTokenIfNecessary(){
			if(tokenRepository == null){
				return;
			}

			synchronized(this){
				if(tokenRepository != null){
					tokenRepository.saveToken(delegate, request, response);
					tokenRepository = null;
					request = null;
					response = null;
				}
			}
		}

	}

}
