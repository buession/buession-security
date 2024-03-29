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
package com.buession.security.shiro.support;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collection;

/**
 * Shiro 功能视图标签支持
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ViewSupport {

	String ROLE_NAMES_DELIMITER = ",";

	String PERMISSION_NAMES_DELIMITER = ",";

	Logger logger = LoggerFactory.getLogger(ViewSupport.class);

	/**
	 * 验证是否为已认证通过的用户，不包含已记住的用户，这是与 isUser 标签方法的区别所在
	 *
	 * @return 用户是否已通过认证
	 */
	default boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isAuthenticated();
	}

	/**
	 * 验证是否为未认证通过用户，与 isAuthenticated 标签相对应，与 isGuest 标签的区别是，该标签包含已记住用户
	 *
	 * @return 用户是否未通过认证
	 */
	default boolean isNotAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isAuthenticated();
	}

	/**
	 * 验证用户是否为访客，即未认证（包含未记住）的用户
	 *
	 * @return 用户是否为访客
	 */
	default boolean isGuest() {
		Subject subject = SecurityUtils.getSubject();
		return subject == null || subject.getPrincipal() == null;
	}

	/**
	 * 验证用户是否认证通过或已记住的用户
	 *
	 * @return 用户是否认证通过或已记住的用户
	 */
	default boolean isUser() {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.getPrincipal() != null;
	}

	/**
	 * 验证用户是通过记住我登录的
	 *
	 * @return 用户是通过记住我登录的
	 */
	default boolean isRemembered() {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isRemembered();
	}

	/**
	 * 返回用户 Principal
	 *
	 * @return 用户 Principal
	 */
	default Object getPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		return subject != null ? subject.getPrincipal() : null;
	}

	/**
	 * 返回用户属性
	 *
	 * @param property
	 * 		属性名称
	 *
	 * @return 用户属性
	 */
	default Object getPrincipalProperty(String property) {
		Assert.isBlank(property, "property must be contains character.");

		Subject subject = SecurityUtils.getSubject();

		if(subject == null){
			return null;
		}

		Object principal = subject.getPrincipal();

		try{
			BeanInfo bi = Introspector.getBeanInfo(principal.getClass());
			PropertyDescriptor[] propertyDescriptors = bi.getPropertyDescriptors();

			for(PropertyDescriptor pd : propertyDescriptors){
				if(property.equals(pd.getName())){
					return pd.getReadMethod().invoke(principal);
				}
			}

			if(logger.isTraceEnabled()){
				logger.trace("Property [{}] not found in principal of type [{}]", property,
						principal.getClass().getName());
			}
		}catch(Exception e){
			if(logger.isWarnEnabled()){
				logger.warn("Error reading property [{}] from principal of type [{}]", property,
						principal.getClass().getName());
			}
		}

		return null;
	}

	/**
	 * 验证用户是否具备某角色
	 *
	 * @param roleName
	 * 		角色名称
	 *
	 * @return 用户是否具备某角色
	 */
	default boolean hasRole(String roleName) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.hasRole(roleName);
	}

	/**
	 * 验证用户是否不具备某角色，与 hasRole 逻辑相反
	 *
	 * @param roleName
	 * 		角色名称
	 *
	 * @return 用户是否不具备某角色
	 */
	default boolean lacksRole(String roleName) {
		return hasRole(roleName) == false;
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		以 delimiter 为分隔符的角色列表
	 * @param delimiter
	 * 		角色列表分隔符
	 *
	 * @return 用户是否具有以下任意一个角色
	 */
	@Deprecated
	default boolean hasAnyRoles(String roleNames, String delimiter) {
		return hasAnyRole(roleNames, delimiter);
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		以 delimiter 为分隔符的角色列表
	 * @param delimiter
	 * 		角色列表分隔符
	 *
	 * @return 用户是否具有以下任意一个角色
	 *
	 * @since 1.3.2
	 */
	default boolean hasAnyRole(String roleNames, String delimiter) {
		return hasAnyRole(StringUtils.split(roleNames, Validate.isBlank(delimiter) ? ROLE_NAMES_DELIMITER : delimiter));
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		以 ROLE_NAMES_DELIMITER 为分隔符的角色列表
	 *
	 * @return 用户是否具有以下任意一个角色
	 */
	@Deprecated
	default boolean hasAnyRoles(String roleNames) {
		return hasAnyRole(roleNames);
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		以 ROLE_NAMES_DELIMITER 为分隔符的角色列表
	 *
	 * @return 用户是否具有以下任意一个角色
	 */
	default boolean hasAnyRole(String roleNames) {
		return hasAnyRole(roleNames, ROLE_NAMES_DELIMITER);
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		角色列表
	 *
	 * @return 用户是否具有以下任意一个角色
	 */
	@Deprecated
	default boolean hasAnyRoles(Collection<String> roleNames) {
		return hasAnyRole(roleNames);
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		角色列表
	 *
	 * @return 用户是否具有以下任意一个角色
	 *
	 * @since 1.3.2
	 */
	default boolean hasAnyRole(Collection<String> roleNames) {
		if(Validate.isEmpty(roleNames)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null && roleNames.stream().anyMatch((role)->role != null && subject.hasRole(role.trim()));
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		角色列表
	 *
	 * @return 用户是否具有以下任意一个角色
	 */
	@Deprecated
	default boolean hasAnyRoles(String... roleNames) {
		return hasAnyRole(roleNames);
	}

	/**
	 * 验证用户是否具有以下任意一个角色
	 *
	 * @param roleNames
	 * 		角色列表
	 *
	 * @return 用户是否具有以下任意一个角色
	 *
	 * @since 1.3.2
	 */
	default boolean hasAnyRole(String... roleNames) {
		if(Validate.isEmpty(roleNames)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null &&
				Arrays.stream(roleNames).anyMatch((role)->role != null && subject.hasRole(role.trim()));
	}

	/**
	 * 验证用户是否具有以下所有角色
	 *
	 * @param roleNames
	 * 		以 delimiter 为分隔符的角色列表
	 * @param delimiter
	 * 		角色列表分隔符
	 *
	 * @return 用户是否具有以下所有角色
	 */
	default boolean hasRolesAll(String roleNames, String delimiter) {
		return hasRolesAll(
				StringUtils.split(roleNames, Validate.isBlank(delimiter) ? ROLE_NAMES_DELIMITER : delimiter));
	}

	/**
	 * 验证用户是否具有以下所有角色
	 *
	 * @param roleNames
	 * 		以 ROLE_NAMES_DELIMITER 为分隔符的角色列表
	 *
	 * @return 用户是否具有以下所有角色
	 */
	default boolean hasRolesAll(String roleNames) {
		return hasRolesAll(roleNames, ROLE_NAMES_DELIMITER);
	}

	/**
	 * 验证用户是否具有以下所有角色
	 *
	 * @param roleNames
	 * 		角色列表
	 *
	 * @return 用户是否具有以下所有角色
	 */
	default boolean hasRolesAll(Collection<String> roleNames) {
		if(Validate.isEmpty(roleNames)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.hasAllRoles(roleNames);
	}

	/**
	 * 验证用户是否具有以下所有角色
	 *
	 * @param roleNames
	 * 		角色列表
	 *
	 * @return 用户是否具有以下所有角色
	 *
	 * @since 1.3.2
	 */
	default boolean hasRolesAll(String... roleNames) {
		return Validate.isNotEmpty(roleNames) && hasRolesAll(Arrays.asList(roleNames));
	}

	/**
	 * 验证用户是否具备某权限
	 *
	 * @param permission
	 * 		权限名称
	 *
	 * @return 用户是否具备某权限
	 */
	default boolean hasPermission(String permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

	/**
	 * 验证用户是否具备某权限
	 *
	 * @param permission
	 * 		权限
	 *
	 * @return 用户是否具备某权限
	 */
	default boolean hasPermission(Permission permission) {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}

	/**
	 * 验证用户是否不具备某权限，与 hasPermission 逻辑相反
	 *
	 * @param permission
	 * 		权限名称
	 *
	 * @return 用户是否不具备某权限
	 */
	default boolean lacksPermission(String permission) {
		return hasPermission(permission) == false;
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		以 delimiter 为分隔符的权限列表
	 * @param delimiter
	 * 		权限列表分隔符
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	@Deprecated
	default boolean hasAnyPermissions(String permissions, String delimiter) {
		return hasAnyPermission(permissions, delimiter);
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		以 delimiter 为分隔符的权限列表
	 * @param delimiter
	 * 		权限列表分隔符
	 *
	 * @return 用户是否具有以下任意一个权限
	 *
	 * @since 1.3.2
	 */
	default boolean hasAnyPermission(String permissions, String delimiter) {
		return hasAnyPermission(
				StringUtils.split(permissions, Validate.isBlank(delimiter) ? PERMISSION_NAMES_DELIMITER : delimiter));
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		以 PERMISSION_NAMES_DELIMITER 为分隔符的权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	@Deprecated
	default boolean hasAnyPermissions(String permissions) {
		return hasAnyPermission(permissions);
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		以 PERMISSION_NAMES_DELIMITER 为分隔符的权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	default boolean hasAnyPermission(String permissions) {
		return hasAnyPermission(permissions, PERMISSION_NAMES_DELIMITER);
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	@Deprecated
	default boolean hasAnyPermissions(Collection<String> permissions) {
		return hasAnyPermission(permissions);
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	default boolean hasAnyPermission(Collection<String> permissions) {
		if(Validate.isEmpty(permissions)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return permissions.stream()
				.anyMatch((permission)->permission != null && subject.isPermitted(permission.trim()));
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	@Deprecated
	default boolean hasAnyPermissions(String... permissions) {
		return hasAnyPermission(permissions);
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	default boolean hasAnyPermission(String... permissions) {
		if(Validate.isEmpty(permissions)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null && Arrays.stream(permissions)
				.anyMatch((permission)->permission != null && subject.isPermitted(permission.trim()));
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 *
	 * @since 1.3.2
	 */
	@Deprecated
	default boolean hasAnyPermissions(Permission... permissions) {
		return hasAnyPermission(permissions);
	}

	/**
	 * 验证用户是否具有以下任意一个权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下任意一个权限
	 */
	default boolean hasAnyPermission(Permission... permissions) {
		if(Validate.isEmpty(permissions)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null && Arrays.stream(permissions)
				.anyMatch((permission)->permission != null && subject.isPermitted(permission));
	}

	/**
	 * 验证用户是否具有以下所有权限
	 *
	 * @param permissions
	 * 		以 delimiter 为分隔符的权限列表
	 * @param delimiter
	 * 		权限列表分隔符
	 *
	 * @return 用户是否具有以下所有权限
	 */
	default boolean hasPermissionsAll(String permissions, String delimiter) {
		return hasPermissionsAll(
				StringUtils.split(permissions, Validate.isBlank(delimiter) ? PERMISSION_NAMES_DELIMITER : delimiter));
	}

	/**
	 * 验证用户是否具有以下所有权限
	 *
	 * @param permissions
	 * 		以 PERMISSION_NAMES_DELIMITER 为分隔符的权限列表
	 *
	 * @return 用户是否具有以下所有权限
	 */
	default boolean hasPermissionsAll(String permissions) {
		return hasPermissionsAll(permissions, PERMISSION_NAMES_DELIMITER);
	}

	/**
	 * 验证用户是否具有以下所有权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下所有权限
	 */
	default boolean hasPermissionsAll(Collection<String> permissions) {
		return permissions != null && hasPermissionsAll(permissions.toArray(new String[]{}));
	}

	/**
	 * 验证用户是否具有以下所有权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下所有权限
	 */
	default boolean hasPermissionsAll(String... permissions) {
		if(Validate.isEmpty(permissions)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermittedAll(permissions);
	}

	/**
	 * 验证用户是否具有以下所有权限
	 *
	 * @param permissions
	 * 		权限列表
	 *
	 * @return 用户是否具有以下所有权限
	 */
	default boolean hasPermissionsAll(Permission... permissions) {
		if(Validate.isEmpty(permissions)){
			return false;
		}

		Subject subject = SecurityUtils.getSubject();
		return subject != null && subject.isPermittedAll(Arrays.asList(permissions));
	}

}
