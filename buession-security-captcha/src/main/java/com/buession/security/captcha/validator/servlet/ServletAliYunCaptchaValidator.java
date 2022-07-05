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
package com.buession.security.captcha.validator.servlet;

import com.buession.lang.Status;
import com.buession.security.captcha.aliyun.AliYunCaptchaClient;
import com.buession.security.captcha.aliyun.AliYunRequestData;
import com.buession.security.captcha.aliyun.AliyunParameter;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.validator.AliYunCaptchaValidator;
import com.buession.web.servlet.http.request.RequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet 环境阿里云验证码验证
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ServletAliYunCaptchaValidator extends AliYunCaptchaValidator implements ServletCaptchaValidator {

	/**
	 * 构造函数
	 *
	 * @param aliYunCaptchaClient
	 *        {@link AliYunCaptchaClient} 实例
	 * @param parameter
	 *        {@link AliyunParameter} 实例
	 */
	public ServletAliYunCaptchaValidator(final AliYunCaptchaClient aliYunCaptchaClient,
										 final AliyunParameter parameter){
		super(aliYunCaptchaClient, parameter);
	}

	@Override
	public Status validate(final HttpServletRequest request) throws CaptchaException{
		final AliYunRequestData requestData = new AliYunRequestData();

		requestData.setSessionId(request.getParameter(parameter.getSessionId()));
		requestData.setSig(request.getParameter(parameter.getSig()));
		requestData.setToken(request.getParameter(parameter.getToken()));
		requestData.setScene(request.getParameter(parameter.getScene()));
		requestData.setRemoteIp(RequestUtils.getClientIp(request));

		return validate(requestData);
	}

}