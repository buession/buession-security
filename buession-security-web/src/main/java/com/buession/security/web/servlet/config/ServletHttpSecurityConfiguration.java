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

import com.buession.security.web.AbstractHttpSecurityConfiguration;
import com.buession.security.web.builder.servlet.ServletHttpSecurityBuilder;
import com.buession.security.web.config.Configurer;
import com.buession.web.servlet.OnServletCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Servlet Web 安全适配配置类
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
@Configuration(proxyBeanMethods = false)
@Conditional(OnServletCondition.class)
public class ServletHttpSecurityConfiguration extends AbstractHttpSecurityConfiguration {

	/**
	 * 构造函数
	 */
	public ServletHttpSecurityConfiguration() {
		this(new Configurer());
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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		final ServletHttpSecurityBuilder builder = ServletHttpSecurityBuilder.getInstance(httpSecurity);

		apply(builder);

		return httpSecurity.build();
	}

}
