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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
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
import org.springframework.security.web.server.header.XXssProtectionServerHttpHeadersWriter;
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

	private final static PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();

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
		serverHttpSecurity.httpBasic((configurer)->{
			if(config.isEnabled()){
			}else{
				configurer.disable();
			}
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder csrf(Csrf config) {
		serverHttpSecurity.csrf((configurer)->{
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

						configurer.csrfTokenRepository(sessionCsrfTokenRepository);
					}else{
						Csrf.Cookie cookie = config.getCookie();

						CookieServerCsrfTokenRepository cookieCsrfTokenRepository = new CookieServerCsrfTokenRepository();

						propertyMapper.from(cookie.getParameterName()).to(cookieCsrfTokenRepository::setParameterName);
						propertyMapper.from(cookie.getHeaderName()).to(cookieCsrfTokenRepository::setHeaderName);
						propertyMapper.from(cookie.getCookieName()).to(cookieCsrfTokenRepository::setCookieName);
						propertyMapper.from(cookie.getCookiePath()).to(cookieCsrfTokenRepository::setCookiePath);

						cookieCsrfTokenRepository.setCookieCustomizer((builder)->{
							builder.domain(cookie.getCookieDomain()).httpOnly(cookie.getCookieHttpOnly());
						});

						configurer.csrfTokenRepository(cookieCsrfTokenRepository);
					}
				}
			}else{
				configurer.disable();
			}
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder cors(Cors config) {
		serverHttpSecurity.cors((configurer)->{
			if(config.isEnabled()){
				UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
				urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config.toCorsConfiguration());

				configurer.configurationSource(urlBasedCorsConfigurationSource);
			}else{
				configurer.disable();
			}
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder frameOptions(FrameOptions config) {
		serverHttpSecurity.headers((configurer)->{
			configurer.frameOptions((frameOptionsConfig)->{
				if(config.isEnabled()){
					if(config.getMode() != null){
						switch(config.getMode()){
							case ALLOW_FROM:
								// empty
								break;
							case SAMEORIGIN:
								frameOptionsConfig.mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN);
								break;
							case DENY:
								frameOptionsConfig.mode(XFrameOptionsServerHttpHeadersWriter.Mode.DENY);
								break;
							default:
								break;
						}
					}
				}else{
					frameOptionsConfig.disable();
				}
			});
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder hsts(Hsts config) {
		serverHttpSecurity.headers((configurer)->{
			configurer.hsts((hstsConfig)->{
				if(config.isEnabled()){
					PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

					propertyMapper.from(config::getMaxAge).as(Duration::ofMillis).to(hstsConfig::maxAge);
					propertyMapper.from(config::getIncludeSubDomains).to(hstsConfig::includeSubdomains);
					propertyMapper.from(config::getPreload).to(hstsConfig::preload);

					if(config.getMatcher() != null){
					}
				}else{
					hstsConfig.disable();
				}
			});
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder contentSecurityPolicy(ContentSecurityPolicy config) {
		serverHttpSecurity.headers((configurer)->{
			if(config.isEnabled() && Validate.hasText(config.getPolicyDirectives())){
				configurer.contentSecurityPolicy((contentSecurityPolicyConfig)->{
					contentSecurityPolicyConfig.policyDirectives(config.getPolicyDirectives());
					if(config.getReportOnly() != null){
						contentSecurityPolicyConfig.reportOnly(config.getReportOnly());
					}
				});
			}else{
				configurer.disable();
			}
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder referrerPolicy(ReferrerPolicy config) {
		serverHttpSecurity.headers((configurer)->{
			if(config.isEnabled() && config.getPolicy() != null){
				ReferrerPolicyConverter.ToNativeReferrerPolicyConverter toNativeReferrerPolicyConverter = new ReferrerPolicyConverter.ToNativeReferrerPolicyConverter();
				ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy referrerPolicy = toNativeReferrerPolicyConverter.convert(
						config.getPolicy());

				configurer.referrerPolicy((referrerPolicyConfig)->{
					referrerPolicyConfig.policy(referrerPolicy);
				});
			}else{
				configurer.disable();
			}
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder xss(Xss config) {
		serverHttpSecurity.headers((configurer)->{
			configurer.xssProtection((xssProtectionConfig)->{
				if(config.isEnabled()){
					if(config.getPolicy() != null){
						switch(config.getPolicy()){
							case DISABLED:
								xssProtectionConfig.headerValue(
										XXssProtectionServerHttpHeadersWriter.HeaderValue.DISABLED);
								break;
							case ENABLED:
								xssProtectionConfig.headerValue(
										XXssProtectionServerHttpHeadersWriter.HeaderValue.ENABLED);
								break;
							case ENABLED_MODE_BLOCK:
								xssProtectionConfig.headerValue(
										XXssProtectionServerHttpHeadersWriter.HeaderValue.ENABLED_MODE_BLOCK);
								break;
							default:
								break;
						}
					}
				}else{
					xssProtectionConfig.disable();
				}
			});
		});

		return this;
	}

	@Override
	public ReactiveHttpSecurityBuilder formLogin(FormLogin config) {
		serverHttpSecurity.formLogin((configurer)->{
			if(config.isEnabled()){
				propertyMapper.from(config.getLoginPage()).to(configurer::loginPage);
			}else{
				configurer.disable();
			}
		});

		return this;
	}

}
