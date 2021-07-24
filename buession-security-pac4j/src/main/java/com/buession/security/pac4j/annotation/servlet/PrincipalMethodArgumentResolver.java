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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.annotation.servlet;

import com.buession.core.utils.Assert;
import com.buession.security.pac4j.annotation.Principal;
import com.buession.security.pac4j.profile.ProfileUtils;
import com.buession.web.method.MethodParameterUtils;
import com.buession.web.servlet.method.AbstractHandlerMethodArgumentResolver;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Resolves method arguments annotated with an {@link Principal}
 *
 * @author Yong.Teng
 */
public class PrincipalMethodArgumentResolver extends AbstractHandlerMethodArgumentResolver<Principal> {

	private final static Logger logger = LoggerFactory.getLogger(PrincipalMethodArgumentResolver.class);

	/**
	 * 构造函数
	 */
	public PrincipalMethodArgumentResolver(){
		super(Principal.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception{
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		Assert.isNull(servletRequest, "No HttpServletRequest");

		methodParameter = methodParameter.nestedIfOptional();

		Principal principal = methodParameter.getParameterAnnotation(Principal.class);
		Object result = read(methodParameter, principal, methodParameter.getNestedParameterType());
		return MethodParameterUtils.adaptArgumentIfNecessary(methodParameter, result);
	}

	protected <T> Object read(MethodParameter methodParameter, Principal principal, Class<T> paramType) throws MethodArgumentTypeMismatchException{
		CommonProfile profile = ProfileUtils.getCurrent();

		if(profile == null && checkRequired(methodParameter, principal)){
			throw new MethodArgumentTypeMismatchException("Principal is missing: " + methodParameter.getExecutable().toGenericString(), methodParameter.getNestedParameterType(), methodParameter.getParameterName(), methodParameter, null);
		}

		try{
			return ProfileUtils.convert(ProfileUtils.getCurrent(), paramType,
					ValueConstants.DEFAULT_NONE.equals(principal.id()) ? null : principal.id(),
					ValueConstants.DEFAULT_NONE.equals(principal.realName()) ? null : principal.realName());
		}catch(InstantiationException e){
			logger.error("CommonProfile convert to {} error: {}.", paramType.getName(), e.getMessage());
		}catch(IllegalAccessException e){
			logger.error("CommonProfile convert to {} error: {}.", paramType.getName(), e.getMessage());
		}catch(InvocationTargetException e){
			logger.error("CommonProfile convert to {} error: {}.", paramType.getName(), e.getMessage());
		}

		return null;
	}

	protected static boolean checkRequired(MethodParameter parameter, Principal principal){
		return (principal != null && principal.required() && parameter.isOptional() == false);
	}

}
