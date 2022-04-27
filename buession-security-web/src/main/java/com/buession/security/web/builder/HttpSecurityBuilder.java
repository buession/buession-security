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
package com.buession.security.web.builders;

import com.buession.security.web.config.ContentSecurityPolicy;
import com.buession.security.web.config.Csrf;
import com.buession.security.web.config.FrameOptions;
import com.buession.security.web.config.Hpkp;
import com.buession.security.web.config.Hsts;
import com.buession.security.web.config.HttpBasic;
import com.buession.security.web.config.ReferrerPolicy;
import com.buession.security.web.config.Xss;

/**
 * 浏览器安全性构建器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface HttpSecurityBuilder {

	/**
	 * 设置 Http Basic {@link HttpBasic} 配置
	 *
	 * @param config
	 * 		Http Basic {@link HttpBasic} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder httpBasic(final HttpBasic config) throws Exception;

	/**
	 * 设置 Csrf {@link Csrf} 配置
	 *
	 * @param config
	 * 		Csrf {@link Csrf} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder csrf(final Csrf config) throws Exception;

	/**
	 * 设置 FrameOptions {@link FrameOptions} 配置
	 *
	 * @param config
	 * 		FrameOptions {@link FrameOptions} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder frameOptions(final FrameOptions config) throws Exception;

	/**
	 * 设置 Hsts {@link Hsts} 配置
	 *
	 * @param config
	 * 		Hsts {@link Hsts} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder hsts(final Hsts config) throws Exception;

	/**
	 * 设置 Hpkp {@link Hpkp} 配置
	 *
	 * @param config
	 * 		Hpkp {@link Hpkp} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder hpkp(final Hpkp config) throws Exception;

	/**
	 * 设置 ContentSecurityPolicy {@link ContentSecurityPolicy} 配置
	 *
	 * @param config
	 * 		ContentSecurityPolicy {@link ContentSecurityPolicy} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder contentSecurityPolicy(final ContentSecurityPolicy config) throws Exception;

	/**
	 * 设置 ReferrerPolicy {@link ReferrerPolicy} 配置
	 *
	 * @param config
	 * 		ReferrerPolicy {@link ReferrerPolicy} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder referrerPolicy(final ReferrerPolicy config) throws Exception;

	/**
	 * 设置 Xss {@link Xss} 配置
	 *
	 * @param config
	 * 		Xss {@link Xss} 配置
	 *
	 * @return HttpSecurityBuilder 实例
	 *
	 * @throws Exception
	 * 		异常
	 */
	HttpSecurityBuilder xss(final Xss config) throws Exception;

}
