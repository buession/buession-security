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
package com.buession.security.web.servlet.config;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.security.web.builder.servlet.ServletHttpSecurityBuilder;
import com.buession.security.web.config.Configurer;
import com.buession.web.servlet.OnServletCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Servlet Web 安全适配配置类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
@Configuration(proxyBeanMethods = false)
@Conditional(OnServletCondition.class)
public class ServletWebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Web 安全适配配置
	 */
	private final Configurer configurer;

	/**
	 * 构造函数
	 */
	public ServletWebSecurityConfigurerAdapterConfiguration(){
		this(new Configurer());
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 */
	public ServletWebSecurityConfigurerAdapterConfiguration(final Configurer configurer){
		super();
		this.configurer = configurer;
	}

	/**
	 * 构造函数
	 *
	 * @param configurer
	 * 		Web 安全适配配置
	 * @param disableDefaults
	 * 		是否禁用默认配置
	 */
	public ServletWebSecurityConfigurerAdapterConfiguration(final Configurer configurer, final boolean disableDefaults){
		super(disableDefaults);
		this.configurer = configurer;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		if(httpSecurity == null){
			return;
		}

		PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		ServletHttpSecurityBuilder builder = ServletHttpSecurityBuilder.getInstance(httpSecurity);

		propertyMapper.from(configurer::getHttpBasic).to(builder::httpBasic);
		propertyMapper.from(configurer::getCsrf).to(builder::csrf);
		propertyMapper.from(configurer::getCors).to(builder::cors);
		propertyMapper.from(configurer::getFrameOptions).to(builder::frameOptions);
		propertyMapper.from(configurer::getHsts).to(builder::hsts);
		propertyMapper.from(configurer::getHpkp).to(builder::hpkp);
		propertyMapper.from(configurer::getContentSecurityPolicy).to(builder::contentSecurityPolicy);
		propertyMapper.from(configurer::getReferrerPolicy).to(builder::referrerPolicy);
		propertyMapper.from(configurer::getXss).to(builder::xss);
		propertyMapper.from(configurer::getFormLogin).to(builder::formLogin);
	}

}
