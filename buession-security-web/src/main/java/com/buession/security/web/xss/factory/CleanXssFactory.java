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
package com.buession.security.web.xss.factory;

import com.buession.core.validator.Validate;
import com.buession.security.web.xss.Options;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 清理模式 XSS 处理工厂
 *
 * @author Yong.Teng
 * @since 2.3.3
 */
public class CleanXssFactory extends AbstractXssFactory {

	private AntiSamy antiSamy;

	private final static Logger logger = LoggerFactory.getLogger(CleanXssFactory.class);

	/**
	 * 构造函数
	 *
	 * @param options
	 * 		XSS 处理选项
	 */
	public CleanXssFactory(Options options) {
		super(options);
		try{
			antiSamy =
					options.getClean() == null ||
							Validate.isEmpty(options.getClean().getPolicyConfigLocation()) ? new AntiSamy() :
							new AntiSamy(Policy.getInstance(options.getClean().getPolicyConfigLocation()));
		}catch(PolicyException e){
			antiSamy = new AntiSamy();
		}
	}

	@Override
	public String handle(String str) {
		final CleanResults cleanResults;

		try{
			cleanResults = antiSamy.scan(str);
			return cleanResults.getCleanHTML();
		}catch(ScanException e){
			logger.warn("AntiSamy scan error: {}.", e.getMessage(), e);
		}catch(PolicyException e){
			logger.warn("AntiSamy scan policy error{}.", e.getMessage(), e);
		}

		return str;
	}

}
