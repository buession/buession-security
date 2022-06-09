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
package com.buession.security.web.utils;

import com.buession.core.utils.Assert;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * {@link Policy} 工具类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class PolicyUtils {

	/**
	 * 从配置文件创建 {@link Policy} 实例
	 *
	 * @param configLocation
	 * 		配置文件路径
	 *
	 * @return {@link Policy} 实例
	 *
	 * @throws IOException
	 * 		配置文件不存在时抛出
	 * @throws PolicyException
	 *        {@link Policy} 实例初始化时抛出
	 */
	public static Policy createFromConfigFile(final String configLocation) throws IOException, PolicyException{
		Assert.isBlank(configLocation, "Policy config location cloud not be empty or null.");
		PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resourceResolver.getResources(configLocation);

		return Policy.getInstance(resources[0].getInputStream());
	}

}
