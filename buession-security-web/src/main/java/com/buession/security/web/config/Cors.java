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

import com.buession.web.http.HttpMethod;

import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 跨源资源共享（CORS）配置
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Cors extends BaseConfig {

	private OpenerPolicy openerPolicy;

	private EmbedderPolicy embedderPolicy;

	private ResourcePolicy resourcePolicy;

	private Map<String, Options> options;

	public Cors() {
		super(true);
	}

	public OpenerPolicy getOpenerPolicy() {
		return openerPolicy;
	}

	public void setOpenerPolicy(OpenerPolicy openerPolicy) {
		this.openerPolicy = openerPolicy;
	}

	public EmbedderPolicy getEmbedderPolicy() {
		return embedderPolicy;
	}

	public void setEmbedderPolicy(EmbedderPolicy embedderPolicy) {
		this.embedderPolicy = embedderPolicy;
	}

	public ResourcePolicy getResourcePolicy() {
		return resourcePolicy;
	}

	public void setResourcePolicy(ResourcePolicy resourcePolicy) {
		this.resourcePolicy = resourcePolicy;
	}

	public Map<String, Options> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Options> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add("enabled=" + getEnabled())
				.add("embedderPolicy=" + embedderPolicy)
				.add("openerPolicy=" + openerPolicy)
				.add("options=" + options)
				.add("resourcePolicy=" + resourcePolicy)
				.toString();
	}

	public final static class Options {

		/**
		 * 允许请求的域
		 */
		private Set<String> origins;

		/**
		 * 允许请求的方法
		 */
		private Set<HttpMethod> allowedMethods;

		/**
		 * 实际请求中允许携带的首部字段
		 */
		private Set<String> allowedHeaders;

		/**
		 * 允许浏览器访问的头
		 */
		private Set<String> exposedHeaders;

		/**
		 * 当浏览器的 credentials 设置为 true 时是否允许浏览器读取 response 的内容
		 */
		private Boolean allowCredentials;

		/**
		 * 是否允许为私有网络
		 *
		 * @since 4.0.0
		 */
		private Boolean allowPrivateNetwork;

		/**
		 * 指定了 preflight 请求的结果能够被缓存时间（单位：秒）
		 */
		private Long maxAge;

		/**
		 * 返回允许请求的域
		 *
		 * @return 允许请求的域
		 */
		public Set<String> getOrigins() {
			return origins;
		}

		/**
		 * 设置允许请求的域
		 *
		 * @param origins
		 * 		允许请求的域
		 */
		public void setOrigins(Set<String> origins) {
			this.origins = origins;
		}

		/**
		 * 返回允许请求的方法
		 *
		 * @return 允许请求的方法
		 */
		public Set<HttpMethod> getAllowedMethods() {
			return allowedMethods;
		}

		/**
		 * 设置允许请求的方法
		 *
		 * @param allowedMethods
		 * 		允许请求的方法
		 */
		public void setAllowedMethods(Set<HttpMethod> allowedMethods) {
			this.allowedMethods = allowedMethods;
		}

		/**
		 * 返回实际请求中允许携带的首部字段
		 *
		 * @return 实际请求中允许携带的首部字段
		 */
		public Set<String> getAllowedHeaders() {
			return allowedHeaders;
		}

		/**
		 * 设置实际请求中允许携带的首部字段
		 *
		 * @param allowedHeaders
		 * 		实际请求中允许携带的首部字段
		 */
		public void setAllowedHeaders(Set<String> allowedHeaders) {
			this.allowedHeaders = allowedHeaders;
		}

		/**
		 * 设置允许浏览器访问的头
		 *
		 * @return 允许浏览器访问的头
		 */
		public Set<String> getExposedHeaders() {
			return exposedHeaders;
		}

		/**
		 * 返回允许浏览器访问的头
		 *
		 * @param exposedHeaders
		 * 		允许浏览器访问的头
		 */
		public void setExposedHeaders(Set<String> exposedHeaders) {
			this.exposedHeaders = exposedHeaders;
		}

		/**
		 * 返回是否允许浏览器读取 response 的内容
		 *
		 * @return 当浏览器的 credentials 设置为 true 时是否允许浏览器读取 response 的内容
		 */
		public Boolean getAllowCredentials() {
			return allowCredentials;
		}

		/**
		 * 设置是否允许浏览器读取 response 的内容
		 *
		 * @param allowCredentials
		 * 		是否允许浏览器读取 response 的内容
		 */
		public void setAllowCredentials(Boolean allowCredentials) {
			this.allowCredentials = allowCredentials;
		}

		public Boolean getAllowPrivateNetwork() {
			return allowPrivateNetwork;
		}

		public void setAllowPrivateNetwork(Boolean allowPrivateNetwork) {
			this.allowPrivateNetwork = allowPrivateNetwork;
		}

		/**
		 * 返回 preflight 请求的结果能够被缓存时间（单位：秒）
		 *
		 * @return preflight 请求的结果能够被缓存时间
		 */
		public Long getMaxAge() {
			return maxAge;
		}

		/**
		 * 设置 preflight 请求的结果能够被缓存时间（单位：秒）
		 *
		 * @param maxAge
		 * 		preflight 请求的结果能够被缓存时间（单位：秒）
		 */
		public void setMaxAge(Long maxAge) {
			this.maxAge = maxAge;
		}

		@Override
		public String toString() {
			return new StringJoiner(", ", "{", "}")
					.add("origins=" + origins)
					.add("allowedMethods=" + allowedMethods)
					.add("allowedHeaders=" + allowedHeaders)
					.add("exposedHeaders=" + exposedHeaders)
					.add("allowCredentials=" + allowCredentials)
					.add("allowPrivateNetwork=" + allowPrivateNetwork)
					.add("maxAge=" + maxAge)
					.toString();
		}

	}

	public enum OpenerPolicy {

		UNSAFE_NONE("unsafe-none"),

		SAME_ORIGIN_ALLOW_POPUPS("same-origin-allow-popups"),

		SAME_ORIGIN("same-origin");

		private final String policy;

		OpenerPolicy(final String policy) {
			this.policy = policy;
		}

		public String getPolicy() {
			return this.policy;
		}

		@Override
		public String toString() {
			return getPolicy();
		}

	}

	public enum EmbedderPolicy {

		UNSAFE_NONE("unsafe-none"),

		REQUIRE_CORP("require-corp");

		private final String policy;

		EmbedderPolicy(final String policy) {
			this.policy = policy;
		}

		public String getPolicy() {
			return this.policy;
		}

		@Override
		public String toString() {
			return getPolicy();
		}

	}

	public enum ResourcePolicy {

		SAME_SITE("same-site"),

		SAME_ORIGIN("same-origin"),

		CROSS_ORIGIN("cross-origin");

		private final String policy;

		ResourcePolicy(final String policy) {
			this.policy = policy;
		}

		public String getPolicy() {
			return this.policy;
		}

		@Override
		public String toString() {
			return getPolicy();
		}

	}

}
