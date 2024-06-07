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
package com.buession.security.mcrypt;

import com.buession.security.crypto.Algorithm;

/**
 * 加密算法
 *
 * @author Yong.Teng
 */
@Deprecated
public enum Algo {

	AES(Algorithm.AES),

	DES(Algorithm.DES),

	MD5(Algorithm.MD5),

	SHA(Algorithm.SHA),

	SHA1(Algorithm.SHA1),

	SHA224(Algorithm.SHA224),

	SHA256(Algorithm.SHA256),

	SHA384(Algorithm.SHA384),

	SHA512(Algorithm.SHA512),

	HMAC_SHA1(Algorithm.HMAC_SHA1),

	HMAC_SHA224(Algorithm.HMAC_SHA224),

	HMAC_SHA256(Algorithm.HMAC_SHA256),

	HMAC_SHA384(Algorithm.HMAC_SHA384),

	HMAC_SHA512(Algorithm.HMAC_SHA512),

	HMAC_MD5(Algorithm.HMAC_MD5),

	SM2(Algorithm.SM2),

	SM3(Algorithm.SM3),

	SM4(Algorithm.SM4),

	BASE64(Algorithm.BASE64);

	private final Algorithm algorithm;

	Algo(final Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public String getName() {
		return algorithm.getName();
	}

}
