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
package com.buession.security.captcha.aliyun;

import com.buession.core.builder.MapBuilder;
import com.buession.core.utils.StringUtils;
import com.buession.security.captcha.core.ParametersBuilder;
import com.buession.security.mcrypt.Algo;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
class AliyunParametersBuilder implements ParametersBuilder<AliYunRequestData> {

	private final static String ACTION = "AuthenticateSig";

	private final static String FORMAT = "JSON";

	private final static Algo SIGNATURE_METHOD = Algo.HMAC_SHA1;

	private final static String SIGNATURE_VERSION = "1.0";

	private final static TimeZone TIMESTAMP_TIMEZONE = new SimpleTimeZone(0, "GMT");

	private final String accessKeyId;

	private final String accessKeySecret;

	private final String appKey;

	private AliYunCaptchaClient client;

	AliyunParametersBuilder(final String accessKeyId, final String accessKeySecret, final String appKey,
							final AliYunCaptchaClient client){
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.appKey = appKey;
		this.client = client;
	}

	@Override
	public Map<String, String> build(final AliYunRequestData requestData){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = new Date();

		sdf.setTimeZone(TIMESTAMP_TIMEZONE);

		MapBuilder<String, String> builder = MapBuilder.<String, String>create()
				.put("Action", ACTION).put("Format", FORMAT).put("Version", client.getVersion())
				.put("SignatureMethod", SIGNATURE_METHOD.getName().toUpperCase()).put("SignatureNonce", randomStr(date))
				.put("SignatureVersion", SIGNATURE_VERSION).put("AccessKeyId", accessKeyId)
				.put("AppKey", appKey).put("Timestamp", sdf.format(date)).put("Token", requestData.getToken())
				.put("Sig", requestData.getSig()).put("SessionId", requestData.getSessionId())
				.put("Scene", requestData.getScene());

		if(requestData.getClientIp() != null){
			builder.put("RemoteIp", requestData.getClientIp());
		}

		Map<String, String> parameters = builder.build();

		parameters.put("Signature", signature(accessKeySecret, parameters));

		return parameters;
	}

	protected static String randomStr(final Date date){
		final StringBuilder sb = new StringBuilder(20);

		sb.append(StringUtils.random(7)).append('_').append(date.getTime());

		return sb.toString();
	}

	protected static String percentEncode(final String value){
		try{
			return value != null ? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A")
					.replace("%7E", "~") : null;
		}catch(UnsupportedEncodingException e){
			return value;
		}
	}

	protected static String signature(final String signKey, final Map<String, String> parameters){
		String[] sortedKeys = parameters.keySet().toArray(new String[0]);
		Arrays.sort(sortedKeys);

		StringBuilder queryString = new StringBuilder();

		for(int i = 0; i < sortedKeys.length; ++i){
			String key = sortedKeys[i];
			if(i > 0){
				queryString.append('&');
			}
			queryString.append(percentEncode(key)).append('=').append(percentEncode(parameters.get(key)));
		}

		StringBuilder signature = new StringBuilder("GET");

		signature.append('&').append(percentEncode("/"));
		signature.append('&').append(percentEncode(queryString.toString()));

		try{
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(new SecretKeySpec((signKey + "&").getBytes(StandardCharsets.UTF_8), "HmacSHA1"));
			byte[] signData = mac.doFinal(signature.toString().getBytes(StandardCharsets.UTF_8));
			return DatatypeConverter.printBase64Binary(signData);
		}catch(NoSuchAlgorithmException e){
			throw new IllegalArgumentException(e.getMessage());
		}catch(InvalidKeyException e){
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
