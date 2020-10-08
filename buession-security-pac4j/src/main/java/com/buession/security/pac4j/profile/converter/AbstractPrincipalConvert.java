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
package com.buession.security.pac4j.profile.converter;

import com.buession.beans.BeanResolver;
import com.buession.beans.DefaultBeanResolver;
import com.buession.security.pac4j.subject.Pac4jPrincipal;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yong.Teng
 */
public abstract class AbstractPrincipalConvert<T> implements PrincipalConvert<T> {

	private ThreadLocal<Map<String, T>> PRINCIPAL_CACHE = ThreadLocal.withInitial(()->new HashMap<>());

	private BeanResolver beanResolver = new DefaultBeanResolver();

	private final static Logger logger = LoggerFactory.getLogger(AbstractPrincipalConvert.class);

	public BeanResolver getBeanResolver(){
		return beanResolver;
	}

	public void setBeanResolver(BeanResolver beanResolver){
		this.beanResolver = beanResolver;
	}

	@Override
	public T convert(final Principal source){
		if(source == null){
			return null;
		}

		if(Pac4jPrincipal.class.isAssignableFrom(source.getClass()) == false){
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

		try{
			T object = getInstance();
			
			beanResolver.populate(object, profile.getAttributes());
			PRINCIPAL_CACHE.get().put(profile.getId(), object);

			return object;
		}catch(IllegalAccessException e){
			logger.error("CommonProfile convert to {} error: {}.", getType().getName(), e.getMessage());
		}catch(InvocationTargetException e){
			logger.error("CommonProfile convert to {} error: {}.", getType().getName(), e.getMessage());
		}catch(InstantiationException e){
			logger.error("CommonProfile convert to {} error: {}.", getType().getName(), e.getMessage());
		}

		return null;
	}

	protected T getInstance() throws IllegalAccessException, InstantiationException{
		return getType().newInstance();
	}

	protected Class<T> getType(){
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
