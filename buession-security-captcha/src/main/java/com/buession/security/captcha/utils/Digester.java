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
package com.buession.security.captcha.utils;

import com.buession.security.captcha.core.DigestMode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Digester {

	private final DigestMode digestMode;

	private final String key;

	public Digester(final DigestMode digestMode, final String key){
		this.digestMode = digestMode;
		this.key = key;
	}

	public String hex(final String value){
		if(digestMode != null){
			switch(digestMode){
				case MD5:
					return DigestUtils.md5Hex(value + key);
				case SHA256:
					return DigestUtils.sha256Hex(value + key);
				case HMAC_SHA1:
					return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, key).hmacHex(value);
				case HMAC_SHA256:
					return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmacHex(value);
				default:
					break;
			}
		}

		return null;
	}

}
