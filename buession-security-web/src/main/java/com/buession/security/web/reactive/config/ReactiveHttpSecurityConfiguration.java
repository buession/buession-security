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

import com.buession.security.web.AbstractHttpSecurityConfiguration;
import com.buession.security.web.builder.reactive.ReactiveHttpSecurityBuilder;
import com.buession.security.web.config.Configurer;
import com.buession.web.reactive.OnWebFluxCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;

/**
 * Reactive Web 安全适配配置类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
@Configuration(proxyBeanMethods = false)
@Conditional(OnWebFluxCondition.class)
public class ReactiveHttpSecurityConfiguration extends AbstractHttpSecurityConfiguration {

	/**
	 * 构造函数
	 */
	public ReactiveHttpSecurityConfiguration() {
		super(new Configurer());
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
		initialize(httpSecurity);
	}

	protected void initialize(ServerHttpSecurity httpSecurity) {
		if(httpSecurity == null){
			return;
		}

		final ReactiveHttpSecurityBuilder builder = ReactiveHttpSecurityBuilder.getInstance(httpSecurity);

		apply(builder);
	}

}
