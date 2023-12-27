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
package com.buession.security.pac4j.spring.reactive;

import com.buession.security.pac4j.annotation.reactive.PrincipalMethodArgumentResolver;
import com.buession.web.reactive.OnWebFluxCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

/**
 * @author Yong.Teng
 * @since 2.1.0
 */
@Configuration(proxyBeanMethods = false)
@Conditional(OnWebFluxCondition.class)
public class Pac4jWebFluxConfigurerAdapter implements WebFluxConfigurer {

	private final ConfigurableBeanFactory factory;

	private final ReactiveAdapterRegistry registry;

	public Pac4jWebFluxConfigurerAdapter(@NonNull ConfigurableBeanFactory factory,
										 @NonNull ReactiveAdapterRegistry registry) {
		this.factory = factory;
		this.registry = registry;
	}

	@Override
	public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
		configurer.addCustomResolver(new PrincipalMethodArgumentResolver(factory, registry));
	}

}
