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
package com.buession.security.pac4j.profile;

import com.buession.beans.BeanUtils;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.pac4j.core.profile.CommonProfile;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Profile 工具类
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public class ProfileUtils {

	private final static String ROLES_FIELD = "roles";

	private final static String PERMISSIONS_FIELD = "permissions";

	/**
	 * 从 {@link Pac4jPrincipal} 获取用户 Profile
	 *
	 * @param principal
	 *        {@link Pac4jPrincipal}
	 *
	 * @return 用户 Profile
	 */
	public static CommonProfile getProfileFromPac4jPrincipal(Pac4jPrincipal principal){
		return principal == null ? null : principal.getProfile();
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
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T convert(CommonProfile profile, Class<T> type)
			throws InstantiationException, IllegalAccessException{
		return convert(profile, type, "id", "realName");
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
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T convert(CommonProfile profile, Class<T> type, String idFieldName, String realNameFieldName)
			throws InstantiationException, IllegalAccessException{
		T instance = type.newInstance();
		Map<String, Object> attributes = new LinkedHashMap<>(profile.getAttributes());

		if(Validate.hasText(idFieldName)){
			attributes.put(idFieldName, getId(profile, idFieldName));
		}

		if(Validate.hasText(realNameFieldName)){
			attributes.put(realNameFieldName, getRealName(profile, realNameFieldName));
		}

		attributes.put(ROLES_FIELD, profile.getRoles());
		attributes.put(PERMISSIONS_FIELD, profile.getPermissions());

		BeanUtils.populate(instance, attributes);

		return instance;
	}

	private static String getId(final CommonProfile profile, final String idField){
		if(Validate.hasText(idField)){
			return getProfileStringAttribute(profile, idField);
		}else{
			return profile.getId();
		}
	}

	private static String getRealName(final CommonProfile profile, final String realNameField){
		if(Validate.hasText(realNameField)){
			return getProfileStringAttribute(profile, realNameField);
		}else{
			return StringUtils.join(profile.getFirstName(), profile.getFamilyName());
		}
	}

	private static String getProfileStringAttribute(final CommonProfile profile, final String field){
		return asString(profile.getAttribute(field));
	}

	private static String asString(final Object obj){
		return obj == null ? null : obj.toString();
	}

}
