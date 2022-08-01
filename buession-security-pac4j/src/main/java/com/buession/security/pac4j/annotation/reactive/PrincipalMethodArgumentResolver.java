/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.annotation.reactive;

import com.buession.core.utils.Assert;
import com.buession.security.pac4j.annotation.Principal;
import com.buession.security.pac4j.annotation.PrincipalAnnotationUtils;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.web.reactive.result.method.annotation.AbstractNamedValueArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 方法参数注解 {@link Principal} 解析器
 *
 * @author Yong.Teng
 * @since 2.0.3
 */
public class PrincipalMethodArgumentResolver extends AbstractNamedValueArgumentResolver {

	public PrincipalMethodArgumentResolver(ConfigurableBeanFactory factory,
										   ReactiveAdapterRegistry registry){
		super(factory, registry);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter){
		return parameter.hasParameterAnnotation(Principal.class);
	}

	@Override
	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter){
		Principal principal = parameter.getParameterAnnotation(Principal.class);
		Assert.isNull(principal, "No Principal annotation");
		return new PrincipalNamedValueInfo(principal, parameter.getNestedParameterType());
	}

	@Override
	protected Mono<Object> resolveName(String name, MethodParameter parameter, ServerWebExchange exchange){
		return exchange.getPrincipal().map((principal)->{
			Principal annotation = parameter.getParameterAnnotation(Principal.class);
			Class<?> paramType = parameter.getParameterType();

			return PrincipalAnnotationUtils.toObject((Pac4jPrincipal) principal, annotation,
					paramType);
		});
	}

	private final static class PrincipalNamedValueInfo extends NamedValueInfo {

		private PrincipalNamedValueInfo(Principal annotation, Class<?> paramType){
			super(Principal.class.getName() + "_" + paramType.getName(), annotation.required(), null);
		}

	}

}
