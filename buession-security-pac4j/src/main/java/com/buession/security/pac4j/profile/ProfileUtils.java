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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.profile;

import com.buession.beans.BeanConverter;
import com.buession.beans.DefaultBeanConverter;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.BeanUtils;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Profile 工具类
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public class ProfileUtils {

	/**
	 * 从 {@link Principal} 获取用户 Profile
	 *
	 * @param principal
	 *        {@link Principal}
	 *
	 * @return 用户 Profile
	 */
	public static CommonProfile getProfileFromPac4jPrincipal(final Pac4jPrincipal principal) {
		return principal == null ? null : principal.getProfile();
	}

	/**
	 * 将 pac4j {@link CommonProfile} 转换为 Map
	 *
	 * @param profile
	 *        {@link CommonProfile} 实例
	 *
	 * @return Map
	 */
	public static Map<String, Object> toMap(final CommonProfile profile) {
		final Map<String, Object> attributes = new LinkedHashMap<>(profile.getAttributes().size() + 15);

		attributes.put("id", profile.getId());
		attributes.put("email", profile.getEmail());
		attributes.put("firstName", profile.getFirstName());
		attributes.put("familyName", profile.getFamilyName());
		attributes.put("displayName", profile.getDisplayName());
		attributes.put("username", profile.getUsername());
		attributes.put("gender", profile.getGender());
		attributes.put("avatar", profile.getGender());
		attributes.put("profileUrl", profile.getProfileUrl());
		attributes.put("location", profile.getLocation());
		attributes.put("linkedId", profile.getLinkedId());
		attributes.put("expired", profile.isExpired());
		attributes.put("remembered", profile.isRemembered());
		attributes.put("roles", profile.getRoles());
		attributes.put("permissions", profile.getPermissions());

		attributes.putAll(profile.getAttributes());

		return attributes;
	}

	/**
	 * 将 pac4j {@link CommonProfile} 转换为 Principal 实例
	 *
	 * @param profile
	 *        {@link CommonProfile} 实例
	 * @param type
	 * 		Principal 类型
	 * @param <T>
	 * 		Principal 类型
	 *
	 * @return Principal 实例
	 *
	 * @since 2.3.0
	 */
	public static <T> T toObject(final CommonProfile profile, final Class<T> type) {
		final BeanConverter beanConverter = new DefaultBeanConverter();
		T instance = BeanUtils.instantiateClass(type);

		beanConverter.convert(profile, instance);
		beanConverter.convert(profile.getAttributes(), instance);

		return instance;
	}

	/**
	 * 将 pac4j {@link Pac4jPrincipal} 转换为 Principal 实例
	 *
	 * @param principal
	 *        {@link Pac4jPrincipal} 实例
	 * @param type
	 * 		Principal 类型
	 * @param <T>
	 * 		Principal 类型
	 *
	 * @return Principal 实例
	 *
	 * @since 2.3.0
	 */
	public static <T> T toObject(final Pac4jPrincipal principal, final Class<T> type) {
		return toObject(getProfileFromPac4jPrincipal(principal), type);
	}

}
