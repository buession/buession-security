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
 * XSS 配置
 * <p><a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-XSS-Protection"
 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-XSS-Protection</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Xss {

	/**
	 * 是否启用 Xss 配置
	 */
	private boolean enabled = true;

	private Boolean block;

	private Boolean enabledProtection;

	/**
	 * XSS 策略配置文件路径
	 */
	private String policyConfigLocation;

	/**
	 * 返回是否启用 Xss 配置
	 *
	 * @return 是否启用 Xss 配置
	 */
	@Deprecated
	public Boolean isEnabled(){
		return getEnabled();
	}

	/**
	 * 返回是否启用 Xss 配置
	 *
	 * @return 是否启用 Xss 配置
	 */
	public Boolean getEnabled(){
		return enabled;
	}

	/**
	 * 配置是否启用 Xss 配置
	 *
	 * @param enabled
	 * 		是否启用 Xss 配置
	 */
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}

	@Deprecated
	public Boolean isBlock(){
		return getBlock();
	}

	public Boolean getBlock(){
		return block;
	}

	public void setBlock(Boolean block){
		this.block = block;
	}

	@Deprecated
	public Boolean isEnabledProtection(){
		return getEnabledProtection();
	}

	public Boolean getEnabledProtection(){
		return enabledProtection;
	}

	public void setEnabledProtection(Boolean enabledProtection){
		this.enabledProtection = enabledProtection;
	}

	/**
	 * 返回 XSS 策略配置文件路径
	 *
	 * @return XSS 策略配置文件路径
	 */
	public String getPolicyConfigLocation(){
		return policyConfigLocation;
	}

	/**
	 * 设置 XSS 策略配置文件路径
	 *
	 * @param policyConfigLocation
	 * 		XSS 策略配置文件路径
	 */
	public void setPolicyConfigLocation(String policyConfigLocation){
		this.policyConfigLocation = policyConfigLocation;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "Xss = {", "}")
				.add("enabled=" + enabled)
				.add("block=" + block)
				.add("enabledProtection=" + enabledProtection)
				.add("policyConfigLocation=" + policyConfigLocation)
				.toString();
	}

}
