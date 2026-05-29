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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.config;

import org.springframework.http.HttpMethod;

import java.util.Set;
import java.util.StringJoiner;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class AuthorizeExchange extends BaseConfig {

	private HttpMethod httpMethod;

	private Set<String> paths;

	private Boolean authenticated;

	private Boolean permitAll;

	private Boolean denyAll;

	private String authority;

	private Set<String> authorities;

	private String role;

	private Set<String> anyRoles;

	private String ipAddress;

	public AuthorizeExchange() {
		super(false);
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Set<String> getPaths() {
		return paths;
	}

	public void setPaths(Set<String> paths) {
		this.paths = paths;
	}

	public Boolean isAuthenticated() {
		return getAuthenticated();
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Boolean isPermitAll() {
		return getPermitAll();
	}

	public Boolean getPermitAll() {
		return permitAll;
	}

	public void setPermitAll(Boolean permitAll) {
		this.permitAll = permitAll;
	}

	public Boolean isDenyAll() {
		return getDenyAll();
	}

	public Boolean getDenyAll() {
		return denyAll;
	}

	public void setDenyAll(Boolean denyAll) {
		this.denyAll = denyAll;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<String> getAnyRoles() {
		return anyRoles;
	}

	public void setAnyRoles(Set<String> anyRoles) {
		this.anyRoles = anyRoles;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add("enabled=" + getEnabled())
				.add("anyRoles=" + anyRoles)
				.add("authenticated=" + authenticated)
				.add("authority=" + authority)
				.add("authorities=" + authorities)
				.add("denyAll=" + denyAll)
				.add("httpMethod=" + httpMethod)
				.add("ipAddress=" + ipAddress)
				.add("paths=" + paths)
				.add("permitAll=" + permitAll)
				.toString();
	}

}
