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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.crypto;

import org.apache.commons.codec.digest.HmacAlgorithms;

import java.nio.charset.Charset;

/**
 * Hmac SHA-1 加密对象
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
public final class HmacSha1Crypto extends AbstractHmacCrypto implements HmacCrypto {

	/**
	 * 构造函数
	 */
	public HmacSha1Crypto() {
		super(Algorithm.HMAC_SHA1);
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 */
	public HmacSha1Crypto(final String salt) {
		super(Algorithm.HMAC_SHA1, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public HmacSha1Crypto(final Charset charset) {
		super(Algorithm.HMAC_SHA1, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public HmacSha1Crypto(final String characterEncoding, final String salt) {
		super(Algorithm.HMAC_SHA1, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public HmacSha1Crypto(final Charset charset, final String salt) {
		super(Algorithm.HMAC_SHA1, charset, salt);
	}

	@Override
	protected HmacAlgorithms getHmacAlgorithms() {
		return HmacAlgorithms.HMAC_SHA_1;
	}

}
