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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.profile.utils;

import com.buession.core.converter.Converter;
import com.buession.core.utils.ReflectUtils;
import com.buession.security.pac4j.subject.Pac4jPrincipal;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yong.Teng
 */
public class PrincipalConvert<T> implements Converter<Principal, T> {

	private ThreadLocal<Map<String, T>> PRINCIPAL_CACHE = ThreadLocal.withInitial(()->new HashMap<>());

	private final static Logger logger = LoggerFactory.getLogger(PrincipalConvert.class);

	@Override
	public T convert(final Principal source){
		if(source == null){
			return null;
		}

		if(source.getClass().isAssignableFrom(Pac4jPrincipal.class) == false){
			logger.warn("{} is not assignable from {}.", source.getClass().getName(), Pac4jPrincipal.class.getName());
			return null;
		}

		Pac4jPrincipal pac4jPrincipal = (Pac4jPrincipal) source;

		Optional<CommonProfile> optional = pac4jPrincipal.getProfile();

		if(optional == null){
			logger.error("Principal profile optional is null.");
			return null;
		}

		CommonProfile profile = optional.get();

		Map<String, T> principals = PRINCIPAL_CACHE.get();

		if(principals != null){
			T principal = principals.get(profile.getId());
			if(principal != null){
				logger.debug("Get user from ThreadLocal.");
				return principal;
			}
		}

		return ReflectUtils.setter(profile.getAttributes(), newInstance(getClass()));
	}

	@SuppressWarnings("unchecked")
	private T newInstance(Class<? extends Converter> clazz){
		Class<T> clz = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[1];

		try{
			return clz.newInstance();
		}catch(InstantiationException e){
			logger.error("New the instance of {} is failure: {}", clz.getName(), e.getMessage());
		}catch(IllegalAccessException e){
			logger.error("New the instance of {} is failure: {}", clz.getName(), e.getMessage());
		}

		return null;
	}

}
