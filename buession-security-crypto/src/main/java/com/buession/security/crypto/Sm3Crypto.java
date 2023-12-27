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

import com.buession.core.utils.Assert;
import com.buession.security.crypto.utils.ObjectUtils;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.digests.SM3Digest;

import java.nio.charset.Charset;

/**
 * SM3 加密对象
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
public final class Sm3Crypto extends AbstractCrypto implements HashCrypto {

	/**
	 * 构造函数
	 */
	public Sm3Crypto() {
		super(Algorithm.SM3);
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 */
	public Sm3Crypto(final String salt) {
		super(Algorithm.SM3, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public Sm3Crypto(final Charset charset) {
		super(Algorithm.SM3, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public Sm3Crypto(final String characterEncoding, final String salt) {
		super(Algorithm.SM3, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public Sm3Crypto(final Charset charset, final String salt) {
		super(Algorithm.SM3, charset, salt);
	}

	@Override
	public String encrypt(final Object object) {
		Assert.isNull(object, "Mcrypt encrypt object could not be null");

		SM3Digest sm3Digest = new SM3Digest();
		byte[] salt = ObjectUtils.toBytes(getSalt(), getCharset());
		byte[] in = ObjectUtils.toBytes(object, getCharset());

		byte[] data = new byte[salt.length + in.length];
		System.arraycopy(salt, 0, data, 0, salt.length);
		System.arraycopy(in, 0, data, salt.length, in.length);

		sm3Digest.update(data, 0, data.length);
		byte[] hash = new byte[sm3Digest.getDigestSize()];
		sm3Digest.doFinal(hash, 0);

		return Base64.encodeBase64String(hash);
	}

}
