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
package com.buession.security.web.builder.reactive;

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
import com.buession.security.web.config.converter.reactive.ReferrerPolicyConverter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.time.Duration;

/**
 * Reactive 浏览器安全性构建器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ReactiveHttpSecurityBuilder implements HttpSecurityBuilder {

	/**
	 * ServerHttpSecurity 实例
	 */
	private final ServerHttpSecurity serverHttpSecurity;

	/**
	 * 构造函数
	 *
	 * @param serverHttpSecurity
	 * 		ServerHttpSecurity 实例
	 */
	protected ReactiveHttpSecurityBuilder(final ServerHttpSecurity serverHttpSecurity) {
		this.serverHttpSecurity = serverHttpSecurity;
	}

	/**
	 * 获取 HttpSecurityBuilder 实例
	 *
	 * @param serverHttpSecurity
	 * 		ServerHttpSecurity 实例
	 *
	 * @return ReactiveHttpSecurityBuilder 实例
	 */
	public static ReactiveHttpSecurityBuilder getInstance(final ServerHttpSecurity serverHttpSecurity) {
		return new ReactiveHttpSecurityBuilder(serverHttpSecurity);
	}

	@Override
	public ReactiveHttpSecurityBuilder httpBasic(HttpBasic config) {
		if(config.isEnabled() == false){
			serverHttpSecurity.httpBasic().disable();
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder csrf(Csrf config) {
		PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		ServerHttpSecurity.CsrfSpec csrfSpec = serverHttpSecurity.csrf();

		if(config.isEnabled()){
			if(config.getMode() != null){
				if(config.getMode() == Csrf.CsrfMode.SESSION){
					Csrf.Session session = config.getSession();

					WebSessionServerCsrfTokenRepository sessionCsrfTokenRepository = new WebSessionServerCsrfTokenRepository();

					propertyMapper.from(session.getParameterName())
							.to(sessionCsrfTokenRepository::setParameterName);
					propertyMapper.from(session.getHeaderName()).to(sessionCsrfTokenRepository::setHeaderName);
					propertyMapper.from(session.getSessionAttributeName())
							.to(sessionCsrfTokenRepository::setSessionAttributeName);

					csrfSpec.csrfTokenRepository(sessionCsrfTokenRepository);
				}else{
					Csrf.Cookie cookie = config.getCookie();

					CookieServerCsrfTokenRepository cookieCsrfTokenRepository = new CookieServerCsrfTokenRepository();

					propertyMapper.from(cookie.getParameterName()).to(cookieCsrfTokenRepository::setParameterName);
					propertyMapper.from(cookie.getHeaderName()).to(cookieCsrfTokenRepository::setHeaderName);
					propertyMapper.from(cookie.getCookieName()).to(cookieCsrfTokenRepository::setCookieName);
					propertyMapper.from(cookie.getCookieDomain()).to(cookieCsrfTokenRepository::setCookieDomain);
					propertyMapper.from(cookie.getCookiePath()).to(cookieCsrfTokenRepository::setCookiePath);

					cookieCsrfTokenRepository.setCookieHttpOnly(cookie.getCookieHttpOnly());

					csrfSpec.csrfTokenRepository(cookieCsrfTokenRepository);
				}
			}
		}else{
			csrfSpec.disable();
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder cors(Cors config) {
		ServerHttpSecurity.CorsSpec corsSpec = serverHttpSecurity.cors();

		if(config.isEnabled()){
			UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config.toCorsConfiguration());

			corsSpec.configurationSource(urlBasedCorsConfigurationSource);
		}else{
			corsSpec.disable();
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder frameOptions(FrameOptions config) {
		ServerHttpSecurity.HeaderSpec.FrameOptionsSpec frameOptionsSpec = serverHttpSecurity.headers().frameOptions();

		if(config.isEnabled()){
			if(config.getMode() != null){
				switch(config.getMode()){
					case ALLOW_FROM:
						// empty
						break;
					case SAMEORIGIN:
						frameOptionsSpec.mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN);
						break;
					case DENY:
						frameOptionsSpec.mode(XFrameOptionsServerHttpHeadersWriter.Mode.DENY);
						break;
					default:
						break;
				}
			}
		}else{
			frameOptionsSpec.disable();
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder hsts(Hsts config) {
		ServerHttpSecurity.HeaderSpec.HstsSpec hstsSpec = serverHttpSecurity.headers().hsts();

		if(config.isEnabled()){
			PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

			propertyMapper.from(config::getMaxAge).as(Duration::ofMillis).to(hstsSpec::maxAge);
			propertyMapper.from(config::getIncludeSubDomains).to(hstsSpec::includeSubdomains);
			propertyMapper.from(config::getPreload).to(hstsSpec::preload);

			if(config.getMatcher() != null){
				// empty
			}
		}else{
			hstsSpec.disable();
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder hpkp(Hpkp config) {
		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder contentSecurityPolicy(ContentSecurityPolicy config) {
		if(config.isEnabled() && Validate.hasText(config.getPolicyDirectives())){
			ServerHttpSecurity.HeaderSpec.ContentSecurityPolicySpec contentSecurityPolicySpec = serverHttpSecurity.headers()
					.contentSecurityPolicy(config.getPolicyDirectives());

			if(config.getReportOnly() != null){
				contentSecurityPolicySpec.reportOnly(config.getReportOnly());
			}
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder referrerPolicy(ReferrerPolicy config) {
		if(config.isEnabled() && config.getPolicy() != null){
			ReferrerPolicyConverter.ToNativeReferrerPolicyConverter toNativeReferrerPolicyConverter = new ReferrerPolicyConverter.ToNativeReferrerPolicyConverter();
			ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy referrerPolicy = toNativeReferrerPolicyConverter.convert(
					config.getPolicy());

			if(referrerPolicy != null){
				serverHttpSecurity.headers().referrerPolicy(referrerPolicy);
			}
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder xss(Xss config) {
		ServerHttpSecurity.HeaderSpec.XssProtectionSpec xssProtectionSpec = serverHttpSecurity.headers()
				.xssProtection();

		if(config.isEnabled()){

		}else{
			xssProtectionSpec.disable();
		}

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder formLogin(FormLogin config) {
		ServerHttpSecurity.FormLoginSpec formLoginSpec = serverHttpSecurity.formLogin();

		if(config.isEnabled()){
			if(Validate.hasText(config.getLoginPage())){
				formLoginSpec.loginPage(config.getLoginPage());
			}
		}else{
			formLoginSpec.disable();
		}

		return this;
	}

}
