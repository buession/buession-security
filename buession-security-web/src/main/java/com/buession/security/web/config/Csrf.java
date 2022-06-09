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

import com.buession.security.spring.web.csrf.CsrfTokenRepository;

import java.util.StringJoiner;

/**
 * Csrf 配置
 * <p><a href="https://baike.baidu.com/item/%E8%B7%A8%E7%AB%99%E8%AF%B7%E6%B1%82%E4%BC%AA%E9%80%A0/13777878"
 * target="_blank">https://baike.baidu.com/item/跨站请求伪造/13777878</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Csrf {

	/**
	 * 是否启用 Csrf
	 */
	private boolean enabled = true;

	/**
	 * Csrf 模式
	 */
	private CsrfMode mode;

	/**
	 * Cookie Csrf Token Repository 配置
	 */
	private Cookie cookie;

	/**
	 * Session Csrf Token Repository 配置
	 */
	private Session session;

	/**
	 * 返回是否启用 Csrf
	 *
	 * @return 是否启用 Csrf
	 */
	public boolean isEnabled(){
		return getEnabled();
	}

	/**
	 * 返回是否启用 Csrf
	 *
	 * @return 是否启用 Csrf
	 */
	public boolean getEnabled(){
		return enabled;
	}

	/**
	 * 设置是否启用 Csrf
	 *
	 * @param enabled
	 * 		是否启用 Csrf
	 */
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	/**
	 * 返回 Csrf 模式
	 *
	 * @return Csrf 模式
	 */
	public CsrfMode getMode(){
		return mode;
	}

	/**
	 * 设置 Csrf 模式
	 *
	 * @param mode
	 * 		Csrf 模式
	 */
	public void setMode(CsrfMode mode){
		this.mode = mode;
	}

	/**
	 * 返回 Cookie Csrf Token Repository 配置
	 *
	 * @return Cookie Csrf Token Repository 配置
	 */
	public Cookie getCookie(){
		return cookie;
	}

	/**
	 * 设置 Cookie Csrf Token Repository 配置
	 *
	 * @param cookie
	 * 		Cookie Csrf Token Repository 配置
	 */
	public void setCookie(Cookie cookie){
		this.cookie = cookie;
	}

	/**
	 * 设置 Session Csrf Token Repository 配置
	 *
	 * @return Session Csrf Token Repository 配置
	 */
	public Session getSession(){
		return session;
	}

	/**
	 * 设置 Session Csrf Token Repository 配置
	 *
	 * @param session
	 * 		Session Csrf Token Repository 配置
	 */
	public void setSession(Session session){
		this.session = session;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "Csrf = {", "}")
				.add("enabled=" + enabled)
				.add("mode=" + mode)
				.add("cookie=" + cookie)
				.add("session=" + session)
				.toString();
	}

	/**
	 * Csrf 模式
	 */
	public enum CsrfMode {

		COOKIE,

		SESSION

	}

	/**
	 * Cookie Csrf Token Repository 配置{@link org.springframework.security.web.csrf.CookieCsrfTokenRepository}
	 */
	public final static class Cookie {

		/**
		 * Csrf 请求参数名
		 */
		private String parameterName = CsrfTokenRepository.DEFAULT_CSRF_PARAMETER_NAME;

		/**
		 * Csrf 请求头名称
		 */
		private String headerName = CsrfTokenRepository.DEFAULT_CSRF_HEADER_NAME;

		/**
		 * Csrf Cookie 名称
		 */
		private String cookieName = CsrfTokenRepository.DEFAULT_CSRF_COOKIE_NAME;

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
		 * 返回 Csrf 请求参数名
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
		 * 返回 Csrf 请求头名称
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
		public String toString(){
			return new StringJoiner(", ", "Cookie = {", "}")
					.add("parameterName=" + parameterName)
					.add("headerName=" + headerName)
					.add("cookieName=" + cookieName)
					.add("cookieDomain=" + cookieDomain)
					.add("cookiePath=" + cookiePath)
					.add("cookieHttpOnly=" + cookieHttpOnly)
					.toString();
		}

	}

	/**
	 * Cookie Csrf Token Repository 配置{@link org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository}
	 */
	public final static class Session {

		/**
		 * Csrf 请求参数名
		 */
		private String parameterName = CsrfTokenRepository.DEFAULT_CSRF_PARAMETER_NAME;

		/**
		 * Csrf 请求头名称
		 */
		private String headerName = CsrfTokenRepository.DEFAULT_CSRF_HEADER_NAME;

		/**
		 * Csrf Session 属性名称
		 */
		private String sessionAttributeName = CsrfTokenRepository.DEFAULT_CSRF_SESSION_ATTRIBUTE_NAME;

		/**
		 * 返回 Csrf 请求参数名
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
		 * 返回 Csrf 请求头名称
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
		public String toString(){
			return new StringJoiner(", ", "Session = {", "}")
					.add("parameterName=" + parameterName)
					.add("headerName=" + headerName)
					.add("sessionAttributeName=" + sessionAttributeName)
					.toString();
		}

	}

}
