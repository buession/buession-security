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
package com.buession.security.captcha.validator;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.security.captcha.CaptchaClient;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.RequestData;

/**
 * 行为验证码验证抽象类
 *
 * @param <C>
 * 		验证码客户端
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractCaptchaValidator<C extends CaptchaClient> implements CaptchaValidator {

	protected String name;

	/**
	 * 验证码客户端
	 */
	protected final C captchaClient;

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		验证码名称
	 * @param captchaClient
	 * 		验证码客户端实例
	 */
	public AbstractCaptchaValidator(final String name, final C captchaClient){
		Assert.isNull(captchaClient, name + " captcha client cloud not be null.");
		this.name = name;
		this.captchaClient = captchaClient;
	}

	@Override
	public Status validate(final RequestData requestData) throws CaptchaException{
		return captchaClient.validate(requestData);
	}

}
