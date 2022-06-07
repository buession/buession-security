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
package com.buession.security.web.builder.servlet;

import com.buession.core.validator.Validate;
import com.buession.security.web.builder.HttpSecurityBuilder;
import com.buession.security.web.config.ContentSecurityPolicy;
import com.buession.security.web.config.Csrf;
import com.buession.security.web.config.FrameOptions;
import com.buession.security.web.config.Hpkp;
import com.buession.security.web.config.Hsts;
import com.buession.security.web.config.HttpBasic;
import com.buession.security.web.config.ReferrerPolicy;
import com.buession.security.web.config.Xss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

/**
 * Servlet 浏览器安全性构建器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ServletHttpSecurityBuilder implements HttpSecurityBuilder {

	/**
	 * HttpSecurity 实例
	 */
	private final HttpSecurity httpSecurity;

	private final static Logger logger = LoggerFactory.getLogger(ServletHttpSecurityBuilder.class);

	/**
	 * 构造函数
	 *
	 * @param httpSecurity
	 * 		HttpSecurity 实例
	 */
	protected ServletHttpSecurityBuilder(final HttpSecurity httpSecurity){
		this.httpSecurity = httpSecurity;
	}

	/**
	 * 获取 HttpSecurityBuilder 实例
	 *
	 * @param httpSecurity
	 * 		HttpSecurity 实例
	 *
	 * @return ServletHttpSecurityBuilder 实例
	 */
	public static ServletHttpSecurityBuilder getInstance(final HttpSecurity httpSecurity){
		return new ServletHttpSecurityBuilder(httpSecurity);
	}

	@Override
	public ServletHttpSecurityBuilder httpBasic(final HttpBasic config){
		if(config.isEnable() == false){
			try{
				httpSecurity.httpBasic().disable();
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("httpBasic config error: {}<{}>", e.getMessage(), config);
				}
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder csrf(final Csrf config){
		try{
			CsrfConfigurer<HttpSecurity> csrfConfigurer = httpSecurity.csrf();

			if(config.isEnable()){
				if(config.getMode() != null){
					switch(config.getMode()){
						case COOKIE:
							Csrf.Cookie cookie = config.getCookie();

							CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();

							if(Validate.hasText(cookie.getParameterName())){
								cookieCsrfTokenRepository.setParameterName(cookie.getParameterName());
							}

							if(Validate.hasText(cookie.getHeaderName())){
								cookieCsrfTokenRepository.setHeaderName(cookie.getHeaderName());
							}

							if(Validate.hasText(cookie.getCookieName())){
								cookieCsrfTokenRepository.setCookieName(cookie.getCookieName());
							}

							if(Validate.hasText(cookie.getCookieDomain())){
								cookieCsrfTokenRepository.setCookieDomain(cookie.getCookieDomain());
							}

							if(Validate.hasText(cookie.getCookiePath())){
								cookieCsrfTokenRepository.setCookiePath(cookie.getCookiePath());
							}

							cookieCsrfTokenRepository.setCookieHttpOnly(cookie.getCookieHttpOnly());

							csrfConfigurer.csrfTokenRepository(new LazyCsrfTokenRepository(cookieCsrfTokenRepository));
							break;
						case SESSION:
							Csrf.Session session = config.getSession();

							HttpSessionCsrfTokenRepository sessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();

							if(Validate.hasText(session.getParameterName())){
								sessionCsrfTokenRepository.setParameterName(session.getParameterName());
							}

							if(Validate.hasText(session.getHeaderName())){
								sessionCsrfTokenRepository.setHeaderName(session.getHeaderName());
							}

							if(Validate.hasText(session.getSessionAttributeName())){
								sessionCsrfTokenRepository.setSessionAttributeName(session.getSessionAttributeName());
							}

							csrfConfigurer.csrfTokenRepository(new LazyCsrfTokenRepository(sessionCsrfTokenRepository));
							break;
						default:
							break;
					}
				}
			}else{
				csrfConfigurer.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("csrf config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder frameOptions(final FrameOptions config){
		try{
			HeadersConfigurer<HttpSecurity>.FrameOptionsConfig frameOptionsConfig = httpSecurity.headers()
					.frameOptions();

			if(config.isEnable()){
				if(config.getMode() != null){
					switch(config.getMode()){
						case ALLOW_FROM:
							// empty
							break;
						case SAMEORIGIN:
							frameOptionsConfig.sameOrigin();
							break;
						case DENY:
							frameOptionsConfig.deny();
							break;
						default:
							break;
					}
				}
			}else{
				frameOptionsConfig.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("frameOptions config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder hsts(final Hsts config){
		try{
			HeadersConfigurer<HttpSecurity>.HstsConfig hstsConfig = httpSecurity.headers()
					.httpStrictTransportSecurity();

			if(config.isEnable()){
				if(config.getMatcher() == null){
					hstsConfig.maxAgeInSeconds(config.getMaxAge()).includeSubDomains(config.getIncludeSubDomains())
							.preload(config.isPreload());
				}else{
					hstsConfig.requestMatcher(config.getMatcher().newInstance()).maxAgeInSeconds(config.getMaxAge())
							.includeSubDomains(config.getIncludeSubDomains()).preload(config.isPreload());
				}
			}else{
				hstsConfig.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("hsts config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder hpkp(final Hpkp config){
		try{
			HeadersConfigurer<HttpSecurity>.HpkpConfig hpkpConfig = httpSecurity.headers().httpPublicKeyPinning();

			if(config.isEnable()){
				hpkpConfig.maxAgeInSeconds(config.getMaxAge()).includeSubDomains(config.getIncludeSubDomains())
						.reportOnly(config.isReportOnly());

				if(config.getPins() != null){
					hpkpConfig.withPins(config.getPins());
				}

				if(config.getSha256Pins() != null){
					hpkpConfig.addSha256Pins(config.getSha256Pins());
				}

				if(Validate.hasText(config.getReportUri())){
					hpkpConfig.reportUri(config.getReportUri());
				}
			}else{
				hpkpConfig.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("hpkp config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder contentSecurityPolicy(final ContentSecurityPolicy config){
		try{
			if(config.isEnable() && Validate.hasText(config.getPolicyDirectives())){
				HeadersConfigurer<HttpSecurity>.ContentSecurityPolicyConfig contentSecurityPolicyConfig = httpSecurity.headers()
						.contentSecurityPolicy(config.getPolicyDirectives());

				if(config.isReportOnly()){
					contentSecurityPolicyConfig.reportOnly();
				}
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("contentSecurityPolicy config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder referrerPolicy(final ReferrerPolicy config){
		try{
			if(config.isEnable()){
				if(config.getPolicy() != null){
					ReferrerPolicyHeaderWriter.ReferrerPolicy referrerPolicy = null;

					switch(config.getPolicy()){
						case NO_REFERRER:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER;
							break;
						case NO_REFERRER_WHEN_DOWNGRADE:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.NO_REFERRER_WHEN_DOWNGRADE;
							break;
						case SAME_ORIGIN:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN;
							break;
						case ORIGIN:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.ORIGIN;
							break;
						case STRICT_ORIGIN:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN;
							break;
						case ORIGIN_WHEN_CROSS_ORIGIN:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.ORIGIN_WHEN_CROSS_ORIGIN;
							break;
						case STRICT_ORIGIN_WHEN_CROSS_ORIGIN:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN;
							break;
						case UNSAFE_URL:
							referrerPolicy = ReferrerPolicyHeaderWriter.ReferrerPolicy.UNSAFE_URL;
							break;
						default:
							break;
					}

					if(referrerPolicy != null){
						httpSecurity.headers().referrerPolicy(referrerPolicy);
					}
				}
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("referrerPolicy config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder xss(final Xss config){
		try{
			HeadersConfigurer<HttpSecurity>.XXssConfig xXssConfig = httpSecurity.headers().xssProtection();

			if(config.isEnable()){
				xXssConfig.block(config.getBlock()).xssProtectionEnabled(config.isEnabledProtection());
			}else{
				xXssConfig.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("xss config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

}
