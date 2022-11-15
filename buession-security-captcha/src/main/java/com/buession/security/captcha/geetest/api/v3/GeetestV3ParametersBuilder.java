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
package com.buession.security.captcha.geetest.api.v3;

import com.buession.core.builder.MapBuilder;
import com.buession.security.captcha.core.ParametersBuilder;

import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
class GeetestV3ParametersBuilder implements ParametersBuilder<GeetestV3RequestData> {

	private final String appId;

	private final String secretKey;

	private final String sdkName;

	GeetestV3ParametersBuilder(final String appId, final String secretKey, final String sdkName){
		this.appId = appId;
		this.secretKey = secretKey;
		this.sdkName = sdkName;
	}

	@Override
	public Map<String, String> build(final GeetestV3RequestData requestData){
		MapBuilder<String, String> builder = MapBuilder.<String, String>create(9)
				.put("captchaid", appId)
				.put("challenge", requestData.getChallenge())
				.put("validate", requestData.getValidate())
				.put("seccode", requestData.getSeccode())
				.put("json_format", "1")
				.put("sdk", sdkName);

		if(requestData.getUserId() != null){
			builder.put("user_id", requestData.getUserId());
		}

		if(requestData.getClientType() != null){
			builder.put("client_type", requestData.getClientType().getValue());
		}

		if(requestData.getIpAddress() != null){
			builder.put("ip_address", requestData.getIpAddress());
		}

		return builder.build();
	}

}
