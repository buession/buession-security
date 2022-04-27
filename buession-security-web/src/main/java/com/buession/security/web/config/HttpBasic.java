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

/**
 * Http Basic 验证
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Authentication"
 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Authentication</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class HttpBasic {

	/**
	 * 是否启用 Http Basic 验证
	 */
	private boolean enable = false;

	/**
	 * 返回是否启用 Http Basic 验证
	 *
	 * @return 是否启用 Http Basic 验证
	 */
	public boolean isEnable(){
		return getEnable();
	}

	/**
	 * 返回是否启用 Http Basic 验证
	 *
	 * @return 是否启用 Http Basic 验证
	 */
	public boolean getEnable(){
		return enable;
	}

	/**
	 * 设置是否启用 Http Basic 验证
	 *
	 * @param enable
	 * 		是否启用 Http Basic 验证
	 */
	public void setEnable(boolean enable){
		this.enable = enable;
	}

	@Override
	public String toString(){
		final ConfigStringBuilder sb = ConfigStringBuilder.create("HttpBasic");

		sb.set("enable", enable);

		return sb.toString();
	}

}
