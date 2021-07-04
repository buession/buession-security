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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.profile;

import com.buession.beans.BeanResolver;
import com.buession.beans.DefaultBeanResolver;
import com.buession.core.utils.FieldUtils;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.security.pac4j.subject.Pac4jPrincipal;
import org.apache.shiro.SecurityUtils;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Profile 工具类
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public class ProfileUtils {

	private final static Logger logger = LoggerFactory.getLogger(ProfileUtils.class);

	/**
	 * 获取当前登录用户 Profile
	 *
	 * @return 当前登录用户 Profile
	 */
	public static CommonProfile getCurrent(){
		Pac4jPrincipal principal = (Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal();
		return getProfileFromPac4jPrincipal(principal);
	}

	/**
	 * 从 {@link Pac4jPrincipal} 获取用户 Profile
	 *
	 * @param principal
	 *        {@link Pac4jPrincipal}
	 *
	 * @return 用户 Profile
	 */
	public static CommonProfile getProfileFromPac4jPrincipal(Pac4jPrincipal principal){
		return principal.getProfile().get();
	}

	/**
	 * 从 {@link CommonProfile} 转换为指定对象
	 *
	 * @param profile
	 *        {@link CommonProfile}
	 * @param type
	 * 		目标对象类型
	 * @param <T>
	 * 		目标对象类型
	 *
	 * @return 目标对象
	 *
	 * @throws InstantiationException
	 * 		实例化异常
	 * @throws IllegalAccessException
	 * 		权限异常
	 * @throws InvocationTargetException
	 * 		反射异常
	 */
	public static <T> T convert(CommonProfile profile, Class<T> type) throws InstantiationException,
			IllegalAccessException, InvocationTargetException{
		return convert(profile, type, new DefaultBeanResolver());
	}

	/**
	 * 从 {@link CommonProfile} 转换为指定对象
	 *
	 * @param profile
	 *        {@link CommonProfile}
	 * @param type
	 * 		目标对象类型
	 * @param beanResolver
	 * 		Bean 解析器
	 * @param <T>
	 * 		目标对象类型
	 *
	 * @return 目标对象
	 *
	 * @throws InstantiationException
	 * 		实例化异常
	 * @throws IllegalAccessException
	 * 		权限异常
	 * @throws InvocationTargetException
	 * 		反射异常
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T convert(CommonProfile profile, Class<T> type, BeanResolver beanResolver) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		return convert(profile, type, beanResolver, "id", "realName");
	}

	/**
	 * 从 {@link CommonProfile} 转换为指定对象
	 *
	 * @param profile
	 *        {@link CommonProfile}
	 * @param type
	 * 		目标对象类型
	 * @param idFieldName
	 * 		ID 字段
	 * @param realNameFieldName
	 * 		真实姓名字段
	 * @param <T>
	 * 		目标对象类型
	 *
	 * @return 目标对象
	 *
	 * @throws InstantiationException
	 * 		实例化异常
	 * @throws IllegalAccessException
	 * 		权限异常
	 * @throws InvocationTargetException
	 * 		反射异常
	 */
	public static <T> T convert(CommonProfile profile, Class<T> type, String idFieldName, String realNameFieldName) throws InstantiationException, IllegalAccessException, InvocationTargetException{
		return convert(profile, type, new DefaultBeanResolver(), idFieldName, realNameFieldName);
	}

	/**
	 * 从 {@link CommonProfile} 转换为指定对象
	 *
	 * @param profile
	 *        {@link CommonProfile}
	 * @param type
	 * 		目标对象类型
	 * @param beanResolver
	 * 		Bean 解析器
	 * @param idFieldName
	 * 		ID 字段
	 * @param realNameFieldName
	 * 		真实姓名字段
	 * @param <T>
	 * 		目标对象类型
	 *
	 * @return 目标对象
	 *
	 * @throws InstantiationException
	 * 		实例化异常
	 * @throws IllegalAccessException
	 * 		权限异常
	 * @throws InvocationTargetException
	 * 		反射异常
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T convert(CommonProfile profile, Class<T> type, BeanResolver beanResolver, String idFieldName,
								String realNameFieldName) throws InstantiationException, IllegalAccessException,
			InvocationTargetException{
		T instance = type.newInstance();

		beanResolver.populate(instance, profile.getAttributes());

		if(Map.class.isAssignableFrom(type)){
			if(Validate.hasText(idFieldName)){
				((Map) instance).put(idFieldName, getId(profile, idFieldName));
			}

			if(Validate.hasText(realNameFieldName)){
				((Map) instance).put(realNameFieldName, getRealName(profile, realNameFieldName));
			}
		}else{
			if(Validate.hasText(idFieldName)){
				Field idField = FieldUtils.getField(type, idFieldName, true);

				if(idField != null){
					try{
						FieldUtils.writeField(instance, idFieldName, idField.getType().cast(getId(profile,
								idFieldName)), true);
					}catch(Exception e){
						if(logger.isErrorEnabled()){
							logger.error("Write {}::{} failure: {}", type.getName(), idFieldName, e.getMessage());
						}
					}
				}
			}

			if(Validate.hasText(realNameFieldName)){
				FieldUtils.writeField(instance, realNameFieldName, getRealName(profile, realNameFieldName), true);
			}
		}

		return instance;
	}

	private final static String getId(final CommonProfile profile, final String idField){
		if(Validate.hasText(idField)){
			Object id = profile.getAttribute(idField);
			return id == null ? null : id.toString();
		}else{
			return profile.getId();
		}
	}

	private final static String getRealName(final CommonProfile profile, final String realNameField){
		if(Validate.hasText(realNameField)){
			Object realName = profile.getAttribute(realNameField);
			return realName == null ? null : realName.toString();
		}else{
			return StringUtils.join(profile.getFirstName(), profile.getFamilyName());
		}
	}

}
