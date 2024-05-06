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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.web.xss;

import java.util.Optional;

/**
 * XSS 处理选项
 *
 * @author Yong.Teng
 * @since 2.3.3
 */
public class Options {

	/**
	 * 策略
	 */
	private Policy policy = Policy.ESCAPE;

	/**
	 * HTML 转义模式选项
	 */
	private Escape escape;

	/**
	 * HTML 清理模式选项
	 */
	private Clean clean;

	/**
	 * 返回策略
	 *
	 * @return 策略
	 */
	public Policy getPolicy() {
		return policy;
	}

	/**
	 * 设置策略
	 *
	 * @param policy
	 * 		策略
	 */
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	/**
	 * 返回 HTML 转义模式选项
	 *
	 * @return HTML 转义模式选项
	 */
	public Escape getEscape() {
		return escape;
	}

	/**
	 * 设置 HTML 转义模式选项
	 *
	 * @param escape
	 * 		HTML 转义模式选项
	 */
	public void setEscape(Escape escape) {
		this.escape = escape;
	}

	/**
	 * 返回 HTML 清理模式选项
	 *
	 * @return HTML 清理模式选项
	 */
	public Clean getClean() {
		return clean;
	}

	/**
	 * 设置 HTML 清理模式选项
	 *
	 * @param clean
	 * 		HTML 清理模式选项
	 */
	public void setClean(Clean clean) {
		this.clean = clean;
	}

	/**
	 * 策略
	 */
	public enum Policy {
		/**
		 * HTML 转义
		 */
		ESCAPE,

		/**
		 * HTML 清理
		 */
		CLEAN
	}

	public interface PolicyOptions {

	}

	/**
	 * HTML 转义模式选项
	 */
	public final static class Escape implements PolicyOptions {

	}

	/**
	 * HTML 清理模式选项
	 */
	public final static class Clean implements PolicyOptions {

		/**
		 * 策略配置文件
		 */
		private String policyConfigLocation;

		/**
		 * 返回策略配置文件
		 *
		 * @return 策略配置文件
		 */
		public String getPolicyConfigLocation() {
			return policyConfigLocation;
		}

		/**
		 * 设置策略配置文件
		 *
		 * @param policyConfigLocation
		 * 		策略配置文件
		 */
		public void setPolicyConfigLocation(String policyConfigLocation) {
			this.policyConfigLocation = policyConfigLocation;
		}

	}

	/**
	 * XSS 选项构建器
	 */
	public final static class Builder {

		/**
		 * XSS 处理选项
		 */
		private final Options options = new Options();

		private Builder() {

		}

		/**
		 * 获取 XSS 选项构建器
		 *
		 * @return XSS 选项构建器实例
		 */
		public static Builder getInstance() {
			return new Builder();
		}

		/**
		 * 设置策略
		 *
		 * @param policy
		 * 		策略
		 *
		 * @return XSS 选项构建器实例
		 */
		public Builder policy(Policy policy) {
			Optional.ofNullable(policy).ifPresent(options::setPolicy);
			return this;
		}

		/**
		 * 设置转义模式选项
		 *
		 * @param escape
		 * 		转义模式选项
		 *
		 * @return XSS 选项构建器实例
		 */
		public Builder escape(Escape escape) {
			Optional.ofNullable(escape).ifPresent(options::setEscape);
			return this;
		}

		/**
		 * 设置清理模式选项
		 *
		 * @param clean
		 * 		清理模式选项
		 *
		 * @return XSS 选项构建器实例
		 */
		public Builder clean(Clean clean) {
			Optional.ofNullable(clean).ifPresent(options::setClean);
			return this;
		}

		public Options build() {
			return options;
		}
	}

}
