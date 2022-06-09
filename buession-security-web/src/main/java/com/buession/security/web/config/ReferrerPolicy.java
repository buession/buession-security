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
package com.buession.security.web.config;

import java.util.StringJoiner;

/**
 * ReferrerPolicy 配置
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ReferrerPolicy {

	/**
	 * 是否启用 ReferrerPolicy
	 */
	private boolean enabled = true;

	private ReferrerPolicy.Policy policy = ReferrerPolicy.Policy.NO_REFERRER;

	/**
	 * 返回是否启用 ReferrerPolicy
	 *
	 * @return 是否启用 ReferrerPolicy
	 */
	public boolean isEnabled(){
		return getEnabled();
	}

	/**
	 * 返回是否启用 ReferrerPolicy
	 *
	 * @return 是否启用 ReferrerPolicy
	 */
	public boolean getEnabled(){
		return enabled;
	}

	/**
	 * 配置是否启用 ReferrerPolicy
	 *
	 * @param enabled
	 * 		是否启用 ReferrerPolicy
	 */
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public ReferrerPolicy.Policy getPolicy(){
		return policy;
	}

	public void setPolicy(ReferrerPolicy.Policy policy){
		this.policy = policy;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "ReferrerPolicy = {", "}")
				.add("enabled=" + enabled)
				.add("policy=" + policy)
				.toString();
	}

	public enum Policy {

		NO_REFERRER("no-referrer"),

		NO_REFERRER_WHEN_DOWNGRADE("no-referrer-when-downgrade"),

		SAME_ORIGIN("same-origin"),

		ORIGIN("origin"),

		STRICT_ORIGIN("strict-origin"),

		ORIGIN_WHEN_CROSS_ORIGIN("origin-when-cross-origin"),

		STRICT_ORIGIN_WHEN_CROSS_ORIGIN("strict-origin-when-cross-origin"),

		UNSAFE_URL("unsafe-url");

		private final String policy;

		Policy(final String policy){
			this.policy = policy;
		}

		public String getPolicy(){
			return this.policy;
		}

		@Override
		public String toString(){
			return getPolicy();
		}

	}

}
