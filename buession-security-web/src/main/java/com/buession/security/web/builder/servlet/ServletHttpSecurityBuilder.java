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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.builder.servlet;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.security.web.builder.HttpSecurityBuilder;
import com.buession.security.web.config.ContentSecurityPolicy;
import com.buession.security.web.config.Cors;
import com.buession.security.web.config.Csrf;
import com.buession.security.web.config.FormLogin;
import com.buession.security.web.config.FrameOptions;
import com.buession.security.web.config.Hpkp;
import com.buession.security.web.config.Hsts;
import com.buession.security.web.config.HttpBasic;
import com.buession.security.web.config.ReferrerPolicy;
import com.buession.security.web.config.Xss;
import com.buession.security.web.config.converter.servlet.ReferrerPolicyConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Objects;

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
	protected ServletHttpSecurityBuilder(final HttpSecurity httpSecurity) {
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
	public static ServletHttpSecurityBuilder getInstance(final HttpSecurity httpSecurity) {
		return new ServletHttpSecurityBuilder(httpSecurity);
	}

	@Override
	public ServletHttpSecurityBuilder httpBasic(final HttpBasic config) {
		if(config.isEnabled() == false){
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
	public ServletHttpSecurityBuilder csrf(final Csrf config) {
		try{
			CsrfConfigurer<HttpSecurity> csrfConfigurer = httpSecurity.csrf();

			if(config.isEnabled()){
				PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();

				if(config.getMode() == Csrf.CsrfMode.SESSION){
					Csrf.Session session = config.getSession();

					HttpSessionCsrfTokenRepository sessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();

					propertyMapper.from(session.getParameterName())
							.to(sessionCsrfTokenRepository::setParameterName);
					propertyMapper.from(session.getHeaderName()).to(sessionCsrfTokenRepository::setHeaderName);
					propertyMapper.from(session.getSessionAttributeName())
							.to(sessionCsrfTokenRepository::setSessionAttributeName);

					csrfConfigurer.csrfTokenRepository(new LazyCsrfTokenRepository(sessionCsrfTokenRepository));
				}else{
					Csrf.Cookie cookie = config.getCookie();

					CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();

					propertyMapper.from(cookie.getParameterName())
							.to(cookieCsrfTokenRepository::setParameterName);
					propertyMapper.from(cookie.getHeaderName()).to(cookieCsrfTokenRepository::setHeaderName);
					propertyMapper.from(cookie.getCookieName()).to(cookieCsrfTokenRepository::setCookieName);
					propertyMapper.from(cookie.getCookieDomain())
							.to(cookieCsrfTokenRepository::setCookieDomain);
					propertyMapper.from(cookie.getCookiePath()).to(cookieCsrfTokenRepository::setCookiePath);

					cookieCsrfTokenRepository.setCookieHttpOnly(cookie.getCookieHttpOnly());

					csrfConfigurer.csrfTokenRepository(new LazyCsrfTokenRepository(cookieCsrfTokenRepository));
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
	public ServletHttpSecurityBuilder cors(final Cors config) {
		try{
			CorsConfigurer<HttpSecurity> corsConfigurer = httpSecurity.cors();

			if(config.isEnabled()){
				UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
				urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config.toCorsConfiguration());

				corsConfigurer.configurationSource(urlBasedCorsConfigurationSource);
			}else{
				corsConfigurer.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("cors config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder frameOptions(final FrameOptions config) {
		try{
			HeadersConfigurer<HttpSecurity>.FrameOptionsConfig frameOptionsConfig = httpSecurity.headers()
					.frameOptions();

			if(config.isEnabled()){
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
	public ServletHttpSecurityBuilder hsts(final Hsts config) {
		try{
			HeadersConfigurer<HttpSecurity>.HstsConfig hstsConfig = httpSecurity.headers()
					.httpStrictTransportSecurity();

			if(config.isEnabled()){
				final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

				propertyMapper.from(config::getMaxAge).to(hstsConfig::maxAgeInSeconds);
				propertyMapper.from(config::getIncludeSubDomains).to(hstsConfig::includeSubDomains);
				propertyMapper.from(config::getPreload).to(hstsConfig::preload);

				if(config.getMatcher() != null){
					hstsConfig.requestMatcher(config.getMatcher().newInstance());
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
	public ServletHttpSecurityBuilder hpkp(final Hpkp config) {
		try{
			HeadersConfigurer<HttpSecurity>.HpkpConfig hpkpConfig = httpSecurity.headers().httpPublicKeyPinning();

			if(config.isEnabled()){
				final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

				propertyMapper.from(config::getMaxAge).to(hpkpConfig::maxAgeInSeconds);
				propertyMapper.from(config::getIncludeSubDomains).to(hpkpConfig::includeSubDomains);
				propertyMapper.from(config::getReportOnly).to(hpkpConfig::reportOnly);
				propertyMapper.from(config::getPins).to(hpkpConfig::withPins);
				propertyMapper.from(config::getSha256Pins).to(hpkpConfig::addSha256Pins);
				propertyMapper.from(config::getReportUri).to(hpkpConfig::reportUri);
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
	public ServletHttpSecurityBuilder contentSecurityPolicy(final ContentSecurityPolicy config) {
		if(config.isEnabled() && Validate.hasText(config.getPolicyDirectives())){
			try{
				HeadersConfigurer<HttpSecurity>.ContentSecurityPolicyConfig contentSecurityPolicyConfig = httpSecurity.headers()
						.contentSecurityPolicy(config.getPolicyDirectives());

				if(Objects.equals(config.getReportOnly(), true)){
					contentSecurityPolicyConfig.reportOnly();
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("contentSecurityPolicy config error: {}<{}>", e.getMessage(), config);
				}
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder referrerPolicy(final ReferrerPolicy config) {
		if(config.isEnabled() && config.getPolicy() != null){
			try{
				ReferrerPolicyConverter.ToNativeReferrerPolicyConverter toNativeReferrerPolicyConverter = new ReferrerPolicyConverter.ToNativeReferrerPolicyConverter();
				ReferrerPolicyHeaderWriter.ReferrerPolicy referrerPolicy = toNativeReferrerPolicyConverter.convert(
						config.getPolicy());

				if(referrerPolicy != null){
					httpSecurity.headers().referrerPolicy(referrerPolicy);
				}
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("referrerPolicy config error: {}<{}>", e.getMessage(), config);
				}
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder xss(final Xss config) {
		try{
			HeadersConfigurer<HttpSecurity>.XXssConfig xssConfig = httpSecurity.headers().xssProtection();

			if(config.isEnabled()){
				final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

				propertyMapper.from(config::getBlock).to(xssConfig::block);
				propertyMapper.from(config::getEnabledProtection).to(xssConfig::xssProtectionEnabled);
			}else{
				xssConfig.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("xss config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

	@Override
	public ServletHttpSecurityBuilder formLogin(FormLogin config) {
		try{
			FormLoginConfigurer<HttpSecurity> formLoginConfigurer = httpSecurity.formLogin();

			if(config.isEnabled()){
				if(Validate.hasText(config.getLoginPage())){
					formLoginConfigurer.loginPage(config.getLoginPage());
				}
			}else{
				formLoginConfigurer.disable();
			}
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("form login config error: {}<{}>", e.getMessage(), config);
			}
		}

		return this;
	}

}
