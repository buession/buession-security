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

import java.util.StringJoiner;

/**
 * Http Basic 验证
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Authentication"target="_blank">
 * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Authentication</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class HttpBasic extends BaseConfig {

	/**
	 * The HTTP Basic realm to use.
	 *
	 * @since 4.0.0
	 */
	private String realmName;

	/**
	 * 构造函数
	 */
	public HttpBasic() {
		super(true);
	}

	/**
	 * Return the HTTP Basic realm to use.
	 *
	 * @return The HTTP Basic realm to use.
	 *
	 * @since 4.0.0
	 */
	public String getRealmName() {
		return realmName;
	}

	/**
	 * Sets the HTTP Basic realm to use.
	 *
	 * @param realmName
	 * 		The HTTP Basic realm to use.
	 *
	 * @since 4.0.0
	 */
	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "{", "}")
				.add("enabled=" + getEnabled())
				.add("realmName=" + realmName)
				.toString();
	}

}
