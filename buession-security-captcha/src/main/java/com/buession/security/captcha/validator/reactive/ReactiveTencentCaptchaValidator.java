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
package com.buession.security.captcha.validator.reactive;

import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.tencent.TencentCaptchaClient;
import com.buession.security.captcha.tencent.TencentParameter;
import com.buession.security.captcha.tencent.TencentRequestData;
import com.buession.security.captcha.validator.TencentCaptchaValidator;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

/**
 * Reactive 环境腾讯云验证码验证
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ReactiveTencentCaptchaValidator extends TencentCaptchaValidator implements ReactiveCaptchaValidator {

	/**
	 * 构造函数
	 *
	 * @param tencentCaptchaClient
	 *        {@link TencentCaptchaClient} 实例
	 * @param parameter
	 *        {@link TencentParameter} 实例
	 */
	public ReactiveTencentCaptchaValidator(final TencentCaptchaClient tencentCaptchaClient,
										   final TencentParameter parameter){
		super(tencentCaptchaClient, parameter);
	}

	@Override
	public Status validate(final ServerHttpRequest request) throws CaptchaException{
		final TencentRequestData requestData = new TencentRequestData();

		MultiValueMap<String, String> parameters = request.getQueryParams();
		requestData.setRandstr(parameters.getFirst(parameter.getRandStr()));
		requestData.setTicket(parameters.getFirst(parameter.getTicket()));

		return validate(requestData);
	}

}
