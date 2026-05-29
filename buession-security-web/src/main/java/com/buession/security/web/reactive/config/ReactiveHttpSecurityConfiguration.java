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
package com.buession.security.web.reactive.config;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.security.web.AbstractHttpSecurityConfiguration;
import com.buession.security.web.config.*;
import com.buession.security.web.config.converter.reactive.ReferrerPolicyConverter;
import com.buession.web.http.XssProtection;
import com.buession.web.reactive.OnWebFluxCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.WebSessionServerCsrfTokenRepository;
import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.security.web.server.header.XXssProtectionServerHttpHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Reactive Web 安全适配配置类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
@Configuration(proxyBeanMethods = false)
@Conditional(OnWebFluxCondition.class)
public class ReactiveHttpSecurityConfiguration extends AbstractHttpSecurityConfiguration<ServerHttpSecurity> {

	private ServerHttpSecurity httpSecurity;

	/**
	 * 构造函数
	 */
	public ReactiveHttpSecurityConfiguration() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 */
	public ReactiveHttpSecurityConfiguration(final Configurer configurer) {
		super(configurer);
	}

	/**
	 * 构造函数
	 *
	 * @param httpSecurity
	 *        {@link ServerHttpSecurity} 实例
	 */
	public ReactiveHttpSecurityConfiguration(final ServerHttpSecurity httpSecurity) {
		this(new Configurer(), httpSecurity);
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 * @param httpSecurity
	 *        {@link ServerHttpSecurity} 实例
	 */
	public ReactiveHttpSecurityConfiguration(final Configurer configurer,
	                                         final ServerHttpSecurity httpSecurity) {
		super(configurer);
		apply(httpSecurity);
		this.httpSecurity = httpSecurity;
	}

	public SecurityWebFilterChain createSecurityWebFilterChain() {
		return httpSecurity.build();
	}

	@Override
	protected ServerHttpSecurity authorizeExchange(final ServerHttpSecurity httpSecurity,
	                                               final AuthorizeExchange config) {
		return httpSecurity.authorizeExchange((authorizeExchangeSpec)->{
			if(config.isEnabled()){
				String[] antPatterns = Validate.isEmpty(config.getPaths()) ? new String[]{"/**"} : config.getPaths()
						.toArray(new String[]{});
				ServerHttpSecurity.AuthorizeExchangeSpec.Access access;
				if(config.getHttpMethod() != null){
					access = authorizeExchangeSpec.pathMatchers(config.getHttpMethod(), antPatterns);
				}else{
					access = authorizeExchangeSpec.pathMatchers(antPatterns);
				}

				if(Objects.equals(config.getAuthenticated(), Boolean.TRUE)){
					access.authenticated();
				}

				if(Objects.equals(config.getDenyAll(), Boolean.TRUE)){
					access.permitAll();
				}

				if(Objects.equals(config.getDenyAll(), Boolean.TRUE)){
					access.denyAll();
				}

				PropertyMapper hasTextPropertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();

				hasTextPropertyMapper.from(config::getAuthority).to(access::hasAuthority);
				propertyMapper.from(config::getAuthorities).as((v)->v.toArray(new String[]{}))
						.to(access::hasAnyAuthority);
				hasTextPropertyMapper.from(config::getRole).to(access::hasRole);
				propertyMapper.from(config::getAnyRoles).as((v)->v.toArray(new String[]{})).to(access::hasAnyRole);
				hasTextPropertyMapper.from(config::getIpAddress).to(access::hasIpAddress);
			}
		});
	}

	@Override
	protected ServerHttpSecurity contentSecurityPolicy(final ServerHttpSecurity httpSecurity,
	                                                   final ContentSecurityPolicy config) {
		return httpSecurity.headers((headerSpec)->{
			if(config.isEnabled()){
				if(config.getPolicyDirectives() != null){
					headerSpec.contentSecurityPolicy((policySpec)->{
						policySpec.policyDirectives(config.getPolicyDirectives());
						propertyMapper.from(config::getReportOnly).to(policySpec::reportOnly);
					});
				}
			}else{
				headerSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity cors(final ServerHttpSecurity serverHttpSecurity, final Cors config) {
		return serverHttpSecurity.cors((corsSpec)->{
			if(config.isEnabled()){
				if(Validate.isNotEmpty(config.getOptions())){
					UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

					config.getOptions().forEach((path, options)->{
						if(Validate.isNotEmpty(path)){
							final CorsConfiguration configuration = new CorsConfiguration();

							if(Validate.isNotEmpty(options.getOrigins())){
								configuration.setAllowedOrigins(new ArrayList<>(options.getOrigins()));
							}

							if(Validate.isNotEmpty(options.getAllowedMethods())){
								configuration.setAllowedMethods(options.getAllowedMethods().stream().map(Enum::toString)
										.collect(Collectors.toList()));
							}

							if(Validate.isNotEmpty(options.getAllowedHeaders())){
								configuration.setAllowedHeaders(new ArrayList<>(options.getAllowedHeaders()));
							}

							if(Validate.isNotEmpty(options.getExposedHeaders())){
								configuration.setExposedHeaders(new ArrayList<>(options.getExposedHeaders()));
							}

							propertyMapper.from(options.getAllowCredentials()).to(configuration::setAllowCredentials);
							propertyMapper.from(options.getAllowPrivateNetwork())
									.to(configuration::setAllowPrivateNetwork);
							propertyMapper.from(options.getMaxAge()).to(configuration::setMaxAge);

							urlBasedCorsConfigurationSource.registerCorsConfiguration(path, configuration);
						}
					});

					corsSpec.configurationSource(urlBasedCorsConfigurationSource);
				}
			}else{
				corsSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity csrf(final ServerHttpSecurity httpSecurity, final Csrf config) {
		return httpSecurity.csrf((csrfSpec)->{
			if(config.isEnabled()){
				if(config.getMode() == Csrf.CsrfMode.SESSION){
					Csrf.Session session = config.getSession();

					if(session == null){
						return;
					}

					WebSessionServerCsrfTokenRepository sessionCsrfTokenRepository = new WebSessionServerCsrfTokenRepository();

					propertyMapper.from(session.getParameterName())
							.to(sessionCsrfTokenRepository::setParameterName);
					propertyMapper.from(session.getHeaderName()).to(sessionCsrfTokenRepository::setHeaderName);
					propertyMapper.from(session.getSessionAttributeName())
							.to(sessionCsrfTokenRepository::setSessionAttributeName);

					csrfSpec.csrfTokenRepository(sessionCsrfTokenRepository);
				}else{
					Csrf.Cookie cookie = config.getCookie();

					if(cookie == null){
						return;
					}

					CookieServerCsrfTokenRepository cookieCsrfTokenRepository = new CookieServerCsrfTokenRepository();

					propertyMapper.from(cookie.getParameterName()).to(cookieCsrfTokenRepository::setParameterName);
					propertyMapper.from(cookie.getHeaderName()).to(cookieCsrfTokenRepository::setHeaderName);
					propertyMapper.from(cookie.getName()).to(cookieCsrfTokenRepository::setCookieName);

					cookieCsrfTokenRepository.setCookieCustomizer((builder)->{
						propertyMapper.from(cookie.getDomain()).to(builder::domain);
						propertyMapper.from(cookie.getPath()).to(builder::path);
						propertyMapper.from(cookie.getHttpOnly()).to(builder::httpOnly);
						propertyMapper.from(cookie.getSecure()).to(builder::secure);
						propertyMapper.from(cookie.getMaxAge()).to(builder::maxAge);
						propertyMapper.from(cookie.getPartitioned()).to(builder::partitioned);
						propertyMapper.from(cookie.getSameSite()).as(Enum::name).to(builder::sameSite);
					});

					csrfSpec.csrfTokenRepository(cookieCsrfTokenRepository);
				}
			}else{
				csrfSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity featurePolicy(final ServerHttpSecurity httpSecurity, final FeaturePolicy config) {
		return httpSecurity;
	}

	@Override
	protected ServerHttpSecurity formLogin(final ServerHttpSecurity httpSecurity, final FormLogin config) {
		return httpSecurity.formLogin((formLoginSpec)->{
			if(config.isEnabled()){
				propertyMapper.from(config.getLoginPage()).to(formLoginSpec::loginPage);
			}else{
				formLoginSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity frameOptions(final ServerHttpSecurity httpSecurity, final FrameOptions config) {
		return httpSecurity.headers((headerSpec)->{
			headerSpec.frameOptions((frameOptionsSpec)->{
				if(config.isEnabled()){
					if(config.getMode() == FrameOptions.XFrameOptionsMode.ALLOW_FROM){
						// empty
					}else if(config.getMode() == FrameOptions.XFrameOptionsMode.SAMEORIGIN){
						frameOptionsSpec.mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN);
					}else if(config.getMode() == FrameOptions.XFrameOptionsMode.DENY){
						frameOptionsSpec.mode(XFrameOptionsServerHttpHeadersWriter.Mode.DENY);
					}
				}else{
					frameOptionsSpec.disable();
				}
			});
		});
	}

	@Override
	protected ServerHttpSecurity hsts(final ServerHttpSecurity httpSecurity, final Hsts config) {
		return httpSecurity.headers((headerSpec)->{
			headerSpec.hsts((hstsSpec)->{
				if(config.isEnabled()){
					propertyMapper.from(config::getMaxAge).as(Duration::ofMillis).to(hstsSpec::maxAge);
					propertyMapper.from(config::getIncludeSubDomains).to(hstsSpec::includeSubdomains);
					propertyMapper.from(config::getPreload).to(hstsSpec::preload);
				}else{
					hstsSpec.disable();
				}
			});
		});
	}

	@Override
	protected ServerHttpSecurity httpBasic(final ServerHttpSecurity httpSecurity, final HttpBasic config) {
		return httpSecurity.httpBasic((httpBasicSpec)->{
			if(config.isEnabled() == false){
				httpBasicSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity logout(final ServerHttpSecurity httpSecurity, final Logout config) {
		return httpSecurity.logout((logoutSpec)->{
			if(config.isEnabled()){
				propertyMapper.from(config.getLogoutUrl()).to(logoutSpec::logoutUrl);
			}else{
				logoutSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity permissionsPolicy(final ServerHttpSecurity httpSecurity,
	                                               final PermissionsPolicy config) {
		return httpSecurity.headers((headerSpec)->{
			if(config.isEnabled()){
				if(config.getPolicy() != null){
					headerSpec.permissionsPolicy((permissionsPolicySpec)->{
						permissionsPolicySpec.policy(config.getPolicy());
					});
				}
			}else{
				headerSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity referrerPolicy(final ServerHttpSecurity httpSecurity, ReferrerPolicy config) {
		return httpSecurity.headers((headerSpec)->{
			if(config.isEnabled() && config.getPolicy() != null){
				ReferrerPolicyConverter.ToNativeReferrerPolicyConverter toNativeReferrerPolicyConverter = new ReferrerPolicyConverter.ToNativeReferrerPolicyConverter();
				ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy referrerPolicy = toNativeReferrerPolicyConverter.convert(
						config.getPolicy());

				headerSpec.referrerPolicy((referrerPolicySpec)->{
					referrerPolicySpec.policy(referrerPolicy);
				});
			}else{
				headerSpec.disable();
			}
		});
	}

	@Override
	protected ServerHttpSecurity xss(final ServerHttpSecurity serverHttpSecurity, final Xss config) {
		return serverHttpSecurity.headers((headerSpec)->{
			headerSpec.xssProtection((xssProtectionSpec)->{
				if(config.isEnabled()){
					if(config.getPolicy() == XssProtection.DISABLED){
						xssProtectionSpec.headerValue(XXssProtectionServerHttpHeadersWriter.HeaderValue.DISABLED);
					}else if(config.getPolicy() == XssProtection.ENABLED){
						xssProtectionSpec.headerValue(XXssProtectionServerHttpHeadersWriter.HeaderValue.ENABLED);
					}else if(config.getPolicy() == XssProtection.ENABLED_MODE_BLOCK){
						xssProtectionSpec.headerValue(
								XXssProtectionServerHttpHeadersWriter.HeaderValue.ENABLED_MODE_BLOCK);
					}
				}else{
					xssProtectionSpec.disable();
				}
			});
		});
	}

}
