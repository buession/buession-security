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
package com.buession.security.pac4j.annotation;

import com.buession.security.pac4j.profile.ProfileUtils;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;

import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.1.0
 */
public class PrincipalAnnotationUtils {

	private final static Logger logger = LoggerFactory.getLogger(PrincipalAnnotationUtils.class);

	public static <T> T toObject(final Pac4jPrincipal principal, final Principal annotation, final Class<T> paramType){
		if(principal == null){
			return null;
		}

		CommonProfile profile = ProfileUtils.getProfileFromPac4jPrincipal(principal);
		if(profile == null){
			return null;
		}

		try{
			T instance = BeanUtils.instantiateClass(paramType);
			Map<String, Object> attributes = ProfileUtils.toMap(profile);

			com.buession.beans.BeanUtils.populate(instance, attributes);

			return instance;
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("Pac4jPrincipal CommonProfile convert to {} error: {}", paramType.getName(),
						e.getMessage());
			}

			return null;
		}
	}

	public static Object resolve(final MethodParameter parameter, final Pac4jPrincipal principal){
		Principal annotation = parameter.getParameterAnnotation(Principal.class);
		Class<?> paramType = parameter.getParameterType();

		return toObject(principal, annotation, paramType);
	}

}
