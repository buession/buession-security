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

import com.buession.core.utils.Assert;
import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.security.captcha.core.CaptchaException;
import com.buession.security.captcha.core.ClientType;
import com.buession.security.captcha.geetest.GeetestCaptchaClient;
import com.buession.security.captcha.geetest.GeetestParameter;
import com.buession.security.captcha.geetest.api.v3.GeetestV3Parameter;
import com.buession.security.captcha.geetest.api.v3.GeetestV3RequestData;
import com.buession.security.captcha.geetest.api.v4.GeetestV4Parameter;
import com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;
import com.buession.security.captcha.validator.GeetestCaptchaValidator;
import com.buession.web.reactive.http.request.RequestUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

/**
 * Reactive 环境极验验证码验证
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ReactiveGeetestCaptchaValidator extends GeetestCaptchaValidator implements ReactiveCaptchaValidator {

	/**
	 * {@link GeetestParameter} 实例
	 */
	private final GeetestParameter parameter;

	/**
	 * 构造函数
	 *
	 * @param geetestCaptchaClient
	 *        {@link GeetestCaptchaClient} 实例
	 * @param parameter
	 *        {@link GeetestParameter} 实例
	 */
	public ReactiveGeetestCaptchaValidator(final GeetestCaptchaClient geetestCaptchaClient,
										   final GeetestParameter parameter){
		super(geetestCaptchaClient);
		Assert.isNull(parameter, "GeetestParameter cloud not be null.");
		this.parameter = parameter;
	}

	@Override
	public Status validate(final ServerHttpRequest request) throws CaptchaException{
		MultiValueMap<String, String> parameters = request.getQueryParams();

		if("v3".equals(captchaClient.getVersion())){
			final GeetestV3Parameter geetestV3Parameter = (GeetestV3Parameter) parameter;
			final GeetestV3RequestData requestData = new GeetestV3RequestData();

			requestData.setChallenge(parameters.getFirst(geetestV3Parameter.getChallenge()));
			requestData.setSeccode(parameters.getFirst(geetestV3Parameter.getSeccode()));
			requestData.setValidate(parameters.getFirst(geetestV3Parameter.getValidate()));
			requestData.setUserId(parameters.getFirst(geetestV3Parameter.getUserId()));
			requestData.setIpAddress(RequestUtils.getClientIp(request));

			String clientType = parameters.getFirst(geetestV3Parameter.getClientType());

			if(Validate.hasText(clientType)){
				requestData.setClientType(EnumUtils.getEnumIgnoreCase(ClientType.class, clientType));
			}else{
				requestData.setClientType(ClientType.WEB);
			}

			return validate(requestData);
		}else if("v4".equals(captchaClient.getVersion())){
			final GeetestV4Parameter geetestV4Parameter = (GeetestV4Parameter) parameter;
			final GeetestV4RequestData requestData = new GeetestV4RequestData();

			requestData.setLotNumber(parameters.getFirst(geetestV4Parameter.getLotNumber()));
			requestData.setCaptchaOutput(parameters.getFirst(geetestV4Parameter.getCaptchaOutput()));
			requestData.setPassToken(parameters.getFirst(geetestV4Parameter.getPassToken()));
			requestData.setGenTime(parameters.getFirst(geetestV4Parameter.getGenTime()));

			return validate(requestData);
		}

		return Status.FAILURE;
	}

}