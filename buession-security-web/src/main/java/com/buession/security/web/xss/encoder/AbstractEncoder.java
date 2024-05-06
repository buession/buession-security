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
package com.buession.security.web.xss.encoder;

import com.buession.core.utils.Assert;
import com.buession.security.web.xss.Options;

/**
 * @author Yong.Teng
 * @since 2.2.0
 */
public abstract class AbstractEncoder<T> implements Encoder<T> {

	/**
	 * XSS 处理选项
	 */
	private Options options = Options.Builder.getInstance().build();

	/**
	 * 构造函数
	 */
	public AbstractEncoder() {

	}

	/**
	 * 构造函数
	 *
	 * @param options
	 *        {@link Options}
	 *
	 * @since 2.3.3
	 */
	public AbstractEncoder(final Options options) {
		setOptions(options);
	}

	/**
	 * 返回 XSS 处理选项
	 *
	 * @return XSS 处理选项
	 *
	 * @since 2.3.3
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * 设置 XSS 处理选项
	 *
	 * @param options
	 * 		XSS 处理选项
	 *
	 * @since 2.3.3
	 */
	public void setOptions(Options options) {
		Assert.isNull(options, "Options cloud not be null");
		this.options = options;
	}

}
