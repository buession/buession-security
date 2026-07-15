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
package com.buession.security.web;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.security.web.config.*;

/**
 * HttpSecurity 配置基类
 *
 * @param <T>
 * 		HttpSecurity 类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractHttpSecurityConfiguration<T> {

	protected final static PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

	/**
	 * Web 安全适配配置
	 */
	protected final Configurer configurer;

	/**
	 * 构造函数
	 */
	public AbstractHttpSecurityConfiguration() {
		this(new Configurer());
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 */
	public AbstractHttpSecurityConfiguration(final Configurer configurer) {
		super();
		this.configurer = configurer;
	}

	protected T apply(final T httpSecurity) {
		if(httpSecurity != null){
			AuthorizeExchange authorizeExchange = configurer.getAuthorizeExchange();
			if(authorizeExchange != null){
				authorizeExchange(httpSecurity, authorizeExchange);
			}

			ContentSecurityPolicy contentSecurityPolicy = configurer.getContentSecurityPolicy();
			if(contentSecurityPolicy != null){
				contentSecurityPolicy(httpSecurity, contentSecurityPolicy);
			}

			Cors cors = configurer.getCors();
			if(cors != null){
				cors(httpSecurity, cors);
			}

			Csrf csrf = configurer.getCsrf();
			if(csrf != null){
				csrf(httpSecurity, csrf);
			}

			FeaturePolicy featurePolicy = configurer.getFeaturePolicy();
			if(featurePolicy != null){
				featurePolicy(httpSecurity, featurePolicy);
			}

			FormLogin formLogin = configurer.getFormLogin();
			if(formLogin != null){
				formLogin(httpSecurity, formLogin);
			}

			FrameOptions frameOptions = configurer.getFrameOptions();
			if(frameOptions != null){
				frameOptions(httpSecurity, frameOptions);
			}

			Hsts hsts = configurer.getHsts();
			if(hsts != null){
				hsts(httpSecurity, hsts);
			}

			HttpBasic httpBasic = configurer.getHttpBasic();
			if(httpBasic != null){
				httpBasic(httpSecurity, httpBasic);
			}

			Logout logout = configurer.getLogout();
			if(logout != null){
				logout(httpSecurity, logout);
			}

			PermissionsPolicy permissionsPolicy = configurer.getPermissionsPolicy();
			if(permissionsPolicy != null){
				permissionsPolicy(httpSecurity, permissionsPolicy);
			}

			ReferrerPolicy referrerPolicy = configurer.getReferrerPolicy();
			if(referrerPolicy != null){
				referrerPolicy(httpSecurity, referrerPolicy);
			}

			Xss xss = configurer.getXss();
			if(xss != null){
				xss(httpSecurity, xss);
			}
		}

		return httpSecurity;
	}

	protected abstract T authorizeExchange(final T httpSecurity, final AuthorizeExchange config);

	protected abstract T contentSecurityPolicy(final T httpSecurity, final ContentSecurityPolicy config);

	protected abstract T cors(final T httpSecurity, final Cors config);

	protected abstract T csrf(final T httpSecurity, final Csrf config);

	protected abstract T featurePolicy(final T httpSecurity, final FeaturePolicy config);

	protected abstract T formLogin(final T httpSecurity, final FormLogin config);

	protected abstract T frameOptions(final T httpSecurity, final FrameOptions config);

	protected abstract T hsts(final T httpSecurity, final Hsts config);

	protected abstract T httpBasic(final T httpSecurity, final HttpBasic config);

	protected abstract T logout(final T httpSecurity, final Logout config);

	protected abstract T permissionsPolicy(final T httpSecurity, final PermissionsPolicy config);

	protected abstract T referrerPolicy(final T httpSecurity, final ReferrerPolicy config);

	protected abstract T xss(final T httpSecurity, final Xss config);

}
