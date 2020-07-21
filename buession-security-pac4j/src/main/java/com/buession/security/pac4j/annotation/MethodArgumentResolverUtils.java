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
package com.buession.security.pac4j.annotation;

import com.buession.core.reflect.FieldUtils;
import com.buession.core.utils.Assert;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
final public class MethodArgumentResolverUtils {

	private final static Logger logger = LoggerFactory.getLogger(MethodArgumentResolverUtils.class);

	private MethodArgumentResolverUtils(){
	}

	public final static Object convert(final MethodParameter methodParameter, final Principal principal){
		Assert.isNull(methodParameter, "MethodParameter cloud not be null.");

		final CommonProfile profile = ProfileUtils.getCurrent();

		if(Map.class.isAssignableFrom(methodParameter.getParameterType())){
			Map<String, Object> result = new HashMap<>(3);

			result.put("id", getId(profile, principal));
			result.put("username", profile.getUsername());
			result.put("realName", getRealName(profile, principal));

			return result;
		}else if(methodParameter.getParameterType().isArray() == false){
			try{
				Object instance = methodParameter.getParameterType().newInstance();

				Field idField = FieldUtils.getDeclaredField(methodParameter.getParameterType(), "id", true);

				if(idField != null){
					FieldUtils.writeDeclaredField(instance, "id", idField.getType().cast(getId(profile, principal)),
							true);
				}

				FieldUtils.writeDeclaredField(instance, "username", profile.getUsername(), true);
				FieldUtils.writeDeclaredField(instance, "realName", getRealName(profile, principal), true);

				return instance;
			}catch(InstantiationException e){
				logger.error("Principal convert to {} failure: {}.", methodParameter.getParameterType(),
						e.getMessage());
			}catch(IllegalAccessException e){
				logger.error("Principal convert to {} failure: {}.", methodParameter.getParameterType(),
						e.getMessage());
			}
		}

		return null;
	}

	private final static Object getId(final CommonProfile profile, final Principal principal){
		return principal.id().equals(ValueConstants.DEFAULT_NONE) ? profile.getId() :
				profile.getAttribute(principal.id());
	}

	private final static String getRealName(final CommonProfile profile, final Principal principal){
		return principal.realName().equals(ValueConstants.DEFAULT_NONE) ?
				profile.getFirstName() + profile.getFamilyName() :
				String.valueOf(profile.getAttribute(principal.realName()));
	}

}
