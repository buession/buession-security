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
package com.buession.security.web.config;

import com.buession.security.core.SameSite;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.time.Duration;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Csrf 配置
 * <p><a href="https://baike.baidu.com/item/%E8%B7%A8%E7%AB%99%E8%AF%B7%E6%B1%82%E4%BC%AA%E9%80%A0/13777878"
 * target="_blank">https://baike.baidu.com/item/跨站请求伪造/13777878</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Csrf extends BaseConfig {

	/**
	 * Csrf 模式
	 */
	private CsrfMode mode = CsrfMode.COOKIE;

	/**
	 * Cookie Csrf Token Repository 配置
	 */
	private Cookie cookie;

	/**
	 * Session Csrf Token Repository 配置
	 */
	private Session session;

	/**
	 * 忽略请求匹配
	 *
	 * @since 4.0.0
	 */
	private Set<String> ignoringRequestMatchers;

	/**
	 * 构造函数
	 */
	public Csrf() {
		super(true);
	}

	/**
	 * 返回 Csrf 模式
	 *
	 * @return Csrf 模式
	 */
	public CsrfMode getMode() {
		return mode;
	}

	/**
	 * 设置 Csrf 模式
	 *
	 * @param mode
	 * 		Csrf 模式
	 */
	public void setMode(CsrfMode mode) {
		this.mode = mode;
	}

	/**
	 * 返回 Cookie Csrf Token Repository 配置
	 *
	 * @return Cookie Csrf Token Repository 配置
	 */
	public Cookie getCookie() {
		return cookie;
	}

	/**
	 * 设置 Cookie Csrf Token Repository 配置
	 *
	 * @param cookie
	 * 		Cookie Csrf Token Repository 配置
	 */
	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}

	/**
	 * 设置 Session Csrf Token Repository 配置
	 *
	 * @return Session Csrf Token Repository 配置
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * 设置 Session Csrf Token Repository 配置
	 *
	 * @param session
	 * 		Session Csrf Token Repository 配置
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * 返回忽略请求匹配
	 *
	 * @return 忽略请求匹配
	 *
	 * @since 4.0.0
	 */
	public Set<String> getIgnoringRequestMatchers() {
		return ignoringRequestMatchers;
	}

	/**
	 * 设置忽略请求匹配
	 *
	 * @param ignoringRequestMatchers
	 * 		忽略请求匹配
	 *
	 * @since 4.0.0
	 */
	public void setIgnoringRequestMatchers(Set<String> ignoringRequestMatchers) {
		this.ignoringRequestMatchers = ignoringRequestMatchers;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "Csrf = {", "}")
				.add("enabled=" + getEnabled())
				.add("mode=" + mode)
				.add("cookie=" + cookie)
				.add("session=" + session)
				.add("ignoringRequestMatchers=" + ignoringRequestMatchers)
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
		private String parameterName = "_csrf";

		/**
		 * Csrf 请求头名称
		 */
		private String headerName = "X-Xsrf-Token";

		/**
		 * Csrf Cookie 名称
		 */
		private String name = "XSRF-TOKEN";

		/**
		 * Csrf Cookie 作用域
		 */
		private String domain;

		/**
		 * Csrf Cookie 作用路径
		 */
		private String path;

		/**
		 * Csrf Cookie 是否可通过客户端脚本访问
		 */
		private boolean httpOnly = true;

		/**
		 * Csrf Cookie 是否仅通过安全的 HTTPS 连接访问
		 *
		 * @since 4.0.0
		 */
		private boolean secure = true;

		/**
		 * The cookie "Max-Age" attribute.
		 *
		 * @since 4.0.0
		 */
		private Duration maxAge;

		/**
		 * The "Partitioned" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		private boolean partitioned;

		/**
		 * The "SameSite" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		private SameSite sameSite;

		/**
		 * 返回 Csrf 请求参数名
		 *
		 * @return Csrf 请求参数名
		 */
		public String getParameterName() {
			return parameterName;
		}

		/**
		 * 设置 Csrf 请求参数名
		 *
		 * @param parameterName
		 * 		Csrf 请求参数名
		 */
		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}

		/**
		 * 返回 Csrf 请求头名称
		 *
		 * @return Csrf 请求头名称
		 */
		public String getHeaderName() {
			return headerName;
		}

		/**
		 * 设置 Csrf 请求头名称
		 *
		 * @param headerName
		 * 		Csrf 请求头名称
		 */
		public void setHeaderName(String headerName) {
			this.headerName = headerName;
		}

		/**
		 * 返回 Csrf 名称
		 *
		 * @return Csrf 名称
		 */
		public String getName() {
			return name;
		}

		/**
		 * 设置 Csrf 名称
		 *
		 * @param name
		 * 		Csrf 名称
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 返回 Csrf 作用域
		 *
		 * @return Csrf 作用域
		 */
		public String getDomain() {
			return domain;
		}

		/**
		 * 设置 Csrf 作用域
		 *
		 * @param domain
		 * 		Csrf 作用域
		 */
		public void setDomain(String domain) {
			this.domain = domain;
		}

		/**
		 * 返回 Csrf 作用路径
		 *
		 * @return Csrf 作用路径
		 */
		public String getPath() {
			return path;
		}

		/**
		 * 设置 Csrf 作用路径
		 *
		 * @param path
		 * 		Csrf 作用路径
		 */
		public void setPath(String path) {
			this.path = path;
		}

		/**
		 * 返回 Csrf 是否可通过客户端脚本访问
		 *
		 * @return Csrf 是否可通过客户端脚本访问
		 */
		public boolean isHttpOnly() {
			return getHttpOnly();
		}

		/**
		 * 返回 Csrf 是否可通过客户端脚本访问
		 *
		 * @return Csrf 是否可通过客户端脚本访问
		 */
		public boolean getHttpOnly() {
			return httpOnly;
		}

		/**
		 * 设置 Csrf 是否可通过客户端脚本访问
		 *
		 * @param httpOnly
		 * 		Csrf 是否可通过客户端脚本访问
		 */
		public void setHttpOnly(boolean httpOnly) {
			this.httpOnly = httpOnly;
		}

		/**
		 * 返回 Csrf 是否仅通过安全的 HTTPS 连接访问
		 *
		 * @return true / false
		 *
		 * @since 4.0.0
		 */
		public boolean isSecure() {
			return getSecure();
		}

		/**
		 * 返回 Csrf 是否仅通过安全的 HTTPS 连接访问
		 *
		 * @return true / false
		 *
		 * @since 4.0.0
		 */
		public boolean getSecure() {
			return secure;
		}

		/**
		 * 设置 Csrf 是否仅通过安全的 HTTPS 连接访问
		 *
		 * @param secure
		 * 		true / false
		 *
		 * @since 4.0.0
		 */
		public void setSecure(boolean secure) {
			this.secure = secure;
		}

		/**
		 * Return the cookie "Max-Age" attribute.
		 *
		 * @return The cookie "Max-Age" attribute.
		 *
		 * @since 4.0.0
		 */
		public Duration getMaxAge() {
			return maxAge;
		}

		/**
		 * Set the cookie "Max-Age" attribute.
		 *
		 * @param maxAge
		 * 		The cookie "Max-Age" attribute.
		 *
		 * @since 4.0.0
		 */
		public void setMaxAge(Duration maxAge) {
			this.maxAge = maxAge;
		}

		/**
		 * Return the "Partitioned" attribute to the cookie.
		 *
		 * @return The "Partitioned" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		public boolean isPartitioned() {
			return getPartitioned();
		}

		/**
		 * Return the "Partitioned" attribute to the cookie.
		 *
		 * @return The "Partitioned" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		public boolean getPartitioned() {
			return partitioned;
		}

		/**
		 * Set the "Partitioned" attribute to the cookie.
		 *
		 * @param partitioned
		 * 		The "Partitioned" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		public void setPartitioned(boolean partitioned) {
			this.partitioned = partitioned;
		}

		/**
		 * Return the "SameSite" attribute to the cookie.
		 *
		 * @return The "SameSite" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		public SameSite getSameSite() {
			return sameSite;
		}

		/**
		 * Set the "SameSite" attribute to the cookie.
		 *
		 * @param sameSite
		 * 		The "SameSite" attribute to the cookie.
		 *
		 * @since 4.0.0
		 */
		public void setSameSite(SameSite sameSite) {
			this.sameSite = sameSite;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", "Cookie = {", "}")
					.add("parameterName=" + parameterName)
					.add("headerName=" + headerName)
					.add("name=" + name)
					.add("domain=" + domain)
					.add("path=" + path)
					.add("httpOnly=" + httpOnly)
					.add("secure=" + secure)
					.add("maxAge=" + maxAge)
					.add("partitioned=" + partitioned)
					.add("sameSite=" + sameSite)
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
		private String parameterName = "_csrf";

		/**
		 * Csrf 请求头名称
		 */
		private String headerName = "X-Xsrf-Token";

		/**
		 * Csrf Session 属性名称
		 */
		private String sessionAttributeName = HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");

		/**
		 * 返回 Csrf 请求参数名
		 *
		 * @return Csrf 请求参数名
		 */
		public String getParameterName() {
			return parameterName;
		}

		/**
		 * 设置 Csrf 请求参数名
		 *
		 * @param parameterName
		 * 		Csrf 请求参数名
		 */
		public void setParameterName(String parameterName) {
			this.parameterName = parameterName;
		}

		/**
		 * 返回 Csrf 请求头名称
		 *
		 * @return Csrf 请求头名称
		 */
		public String getHeaderName() {
			return headerName;
		}

		/**
		 * 设置 Csrf 请求头名称
		 *
		 * @param headerName
		 * 		Csrf 请求头名称
		 */
		public void setHeaderName(String headerName) {
			this.headerName = headerName;
		}

		/**
		 * 返回 Csrf Session 属性名称
		 *
		 * @return Csrf Session 属性名称
		 */
		public String getSessionAttributeName() {
			return sessionAttributeName;
		}

		/**
		 * 设置 Csrf Session 属性名称
		 *
		 * @param sessionAttributeName
		 * 		Csrf Session 属性名称
		 */
		public void setSessionAttributeName(String sessionAttributeName) {
			this.sessionAttributeName = sessionAttributeName;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", "Session = {", "}")
					.add("parameterName=" + parameterName)
					.add("headerName=" + headerName)
					.add("sessionAttributeName=" + sessionAttributeName)
					.toString();
		}

	}

}
