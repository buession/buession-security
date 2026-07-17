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
package com.buession.security.web.servlet.config;

import com.buession.core.validator.Validate;
import com.buession.security.web.AbstractHttpSecurityConfiguration;
import com.buession.security.web.config.*;
import com.buession.security.web.config.converter.servlet.ReferrerPolicyConverter;
import com.buession.web.http.XssProtection;
import com.buession.web.servlet.OnServletCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Servlet Web 安全适配配置类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
@Configuration(proxyBeanMethods = false)
@Conditional(OnServletCondition.class)
public class ServletHttpSecurityConfiguration extends AbstractHttpSecurityConfiguration<HttpSecurity> {

	private HttpSecurity httpSecurity;

	private final static Logger logger = LoggerFactory.getLogger(ServletHttpSecurityConfiguration.class);

	/**
	 * 构造函数
	 */
	public ServletHttpSecurityConfiguration() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 */
	public ServletHttpSecurityConfiguration(final Configurer configurer) {
		super(configurer);
	}

	/**
	 * 构造函数
	 *
	 * @param httpSecurity
	 * 		Web 安全适配配置
	 */
	public ServletHttpSecurityConfiguration(final HttpSecurity httpSecurity) {
		this(new Configurer(), httpSecurity);
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 * @param httpSecurity
	 *        {@link HttpSecurity} 实例
	 */
	public ServletHttpSecurityConfiguration(final Configurer configurer, final HttpSecurity httpSecurity) {
		super(configurer);
		this.httpSecurity = httpSecurity;
	}

	public SecurityFilterChain createSecurityFilterChain() throws Exception {
		return apply(httpSecurity).build();
	}

	@Override
	protected HttpSecurity authorizeExchange(final HttpSecurity httpSecurity, final AuthorizeExchange config) {
		return httpSecurity;
	}

	@Override
	protected HttpSecurity contentSecurityPolicy(final HttpSecurity httpSecurity, final ContentSecurityPolicy config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				if(config.isEnabled()){
					if(Validate.hasText(config.getPolicyDirectives())){
						headersConfigurer.contentSecurityPolicy((policyConfig)->{
							policyConfig.policyDirectives(config.getPolicyDirectives());
							if(Objects.equals(config.getReportOnly(), true)){
								policyConfig.reportOnly();
							}
						});
					}
				}else{
					headersConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("contentSecurityPolicy config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity cors(final HttpSecurity httpSecurity, final Cors config) {
		try{
			return httpSecurity.cors((corsConfigurer)->{
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
									configuration.setAllowedMethods(
											options.getAllowedMethods().stream().map(Enum::toString).collect(
													Collectors.toList()));
								}

								if(Validate.isNotEmpty(options.getAllowedHeaders())){
									configuration.setAllowedHeaders(new ArrayList<>(options.getAllowedHeaders()));
								}

								if(Validate.isNotEmpty(options.getExposedHeaders())){
									configuration.setExposedHeaders(new ArrayList<>(options.getExposedHeaders()));
								}

								propertyMapper.from(options.getAllowCredentials())
										.to(configuration::setAllowCredentials);
								propertyMapper.from(options.getAllowPrivateNetwork())
										.to(configuration::setAllowPrivateNetwork);
								propertyMapper.from(options.getMaxAge()).to(configuration::setMaxAge);

								urlBasedCorsConfigurationSource.registerCorsConfiguration(path, configuration);
							}
						});

						corsConfigurer.configurationSource(urlBasedCorsConfigurationSource);
					}
				}else{
					corsConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("cors config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity csrf(final HttpSecurity httpSecurity, final Csrf config) {
		try{
			return httpSecurity.csrf((csrfConfigurer)->{
				if(config.isEnabled()){
					if(Validate.isNotEmpty(config.getIgnoringRequestMatchers())){
						csrfConfigurer.ignoringRequestMatchers(
								config.getIgnoringRequestMatchers().toArray(new String[0]));
					}

					if(config.getMode() == Csrf.CsrfMode.SESSION){
						Csrf.Session session = config.getSession();

						if(session == null){
							return;
						}

						HttpSessionCsrfTokenRepository sessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();

						propertyMapper.from(session.getParameterName())
								.to(sessionCsrfTokenRepository::setParameterName);
						propertyMapper.from(session.getHeaderName()).to(sessionCsrfTokenRepository::setHeaderName);
						propertyMapper.from(session.getSessionAttributeName())
								.to(sessionCsrfTokenRepository::setSessionAttributeName);

						csrfConfigurer.csrfTokenRepository(sessionCsrfTokenRepository);
					}else{
						Csrf.Cookie cookie = config.getCookie();

						if(cookie == null){
							return;
						}

						CookieCsrfTokenRepository cookieCsrfTokenRepository = new CookieCsrfTokenRepository();

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

						csrfConfigurer.csrfTokenRepository(cookieCsrfTokenRepository);
					}
				}else{
					csrfConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("csrf config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity featurePolicy(final HttpSecurity httpSecurity, final FeaturePolicy config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				if(config.isEnabled()){
					propertyMapper.from(config.getPolicy()).to(headersConfigurer::featurePolicy);
				}else{
					headersConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("featurePolicy config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity formLogin(final HttpSecurity httpSecurity, final FormLogin config) {
		try{
			return httpSecurity.formLogin((formLoginConfigurer)->{
				if(config.isEnabled()){
					propertyMapper.from(config.getLoginPage()).to(formLoginConfigurer::loginPage);
					propertyMapper.from(config.getUsernameParameter()).to(formLoginConfigurer::usernameParameter);
					propertyMapper.from(config.getPasswordParameter()).to(formLoginConfigurer::passwordParameter);
					propertyMapper.from(config.getSuccessForwardUrl()).to(formLoginConfigurer::successForwardUrl);
					propertyMapper.from(config.getFailureForwardUrl()).to(formLoginConfigurer::failureForwardUrl);
				}else{
					formLoginConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("form login config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity frameOptions(final HttpSecurity httpSecurity, final FrameOptions config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				headersConfigurer.frameOptions((frameOptionsConfig)->{
					if(config.isEnabled()){
						if(config.getMode() == FrameOptions.XFrameOptionsMode.ALLOW_FROM){
							// empty
						}else if(config.getMode() == FrameOptions.XFrameOptionsMode.SAMEORIGIN){
							frameOptionsConfig.sameOrigin();
						}else if(config.getMode() == FrameOptions.XFrameOptionsMode.DENY){
							frameOptionsConfig.deny();
						}
					}else{
						frameOptionsConfig.disable();
					}
				});
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("frameOptions config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity hsts(final HttpSecurity httpSecurity, final Hsts config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				headersConfigurer.httpStrictTransportSecurity((hstsConfig)->{
					if(config.isEnabled()){
						propertyMapper.from(config::getMaxAge).to(hstsConfig::maxAgeInSeconds);
						propertyMapper.from(config::getIncludeSubDomains).to(hstsConfig::includeSubDomains);
						propertyMapper.from(config::getPreload).to(hstsConfig::preload);
					}else{
						headersConfigurer.disable();
					}
				});
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("hsts config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity httpBasic(final HttpSecurity httpSecurity, final HttpBasic config) {
		try{
			return httpSecurity.httpBasic((httpBasicConfigurer)->{
				if(config.isEnabled()){
					propertyMapper.from(config::getRealmName).to(httpBasicConfigurer::realmName);
				}else{
					httpBasicConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("httpBasic config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity logout(final HttpSecurity httpSecurity, final Logout config) {
		try{
			return httpSecurity.logout((logoutConfigurer)->{
				if(config.isEnabled()){
					propertyMapper.from(config.getLogoutUrl()).to(logoutConfigurer::logoutUrl);
					propertyMapper.from(config.getLogoutSuccessUrl()).to(logoutConfigurer::logoutSuccessUrl);
					propertyMapper.from(config.getPermitAll()).to(logoutConfigurer::permitAll);
					propertyMapper.from(config.getClearAuthentication()).to(logoutConfigurer::clearAuthentication);
					propertyMapper.from(config.getInvalidateHttpSession()).to(logoutConfigurer::invalidateHttpSession);
				}else{
					logoutConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("form login config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity permissionsPolicy(final HttpSecurity httpSecurity, final PermissionsPolicy config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				if(config.isEnabled()){
					if(config.getPolicy() != null){
						headersConfigurer.permissionsPolicyHeader((permissionsPolicyConfig)->{
							permissionsPolicyConfig.policy(config.getPolicy());
						});
					}
				}else{
					headersConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("referrerPolicy config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity referrerPolicy(final HttpSecurity httpSecurity, final ReferrerPolicy config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				if(config.isEnabled()){
					if(config.getPolicy() != null){
						ReferrerPolicyConverter.ToNativeReferrerPolicyConverter toNativeReferrerPolicyConverter = new ReferrerPolicyConverter.ToNativeReferrerPolicyConverter();
						ReferrerPolicyHeaderWriter.ReferrerPolicy referrerPolicy = toNativeReferrerPolicyConverter.convert(
								config.getPolicy());

						headersConfigurer.referrerPolicy((referrerPolicyConfig)->{
							referrerPolicyConfig.policy(referrerPolicy);
						});
					}
				}else{
					headersConfigurer.disable();
				}
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("referrerPolicy config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

	@Override
	protected HttpSecurity xss(final HttpSecurity httpSecurity, final Xss config) {
		try{
			return httpSecurity.headers((headersConfigurer)->{
				headersConfigurer.xssProtection((xXssConfig)->{
					if(config.isEnabled()){
						if(config.getPolicy() == XssProtection.DISABLED){
							xXssConfig.headerValue(XXssProtectionHeaderWriter.HeaderValue.DISABLED);
						}else if(config.getPolicy() == XssProtection.ENABLED){
							xXssConfig.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED);
						}else if(config.getPolicy() == XssProtection.ENABLED_MODE_BLOCK){
							xXssConfig.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK);
						}
					}else{
						xXssConfig.disable();
					}
				});
			});
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("xss config error: {}<{}>", e.getMessage(), config);
			}

			return httpSecurity;
		}
	}

}
