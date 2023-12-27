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
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.nio.charset.Charset;

/**
 * Hmac 加解密
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractHmacCrypto extends AbstractCrypto {

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 */
	public AbstractHmacCrypto(final Algorithm algorithm) {
		super(algorithm);
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		请求算法的名称
	 * @param salt
	 * 		加密密钥
	 */
	public AbstractHmacCrypto(final Algorithm algorithm, final String salt) {
		super(algorithm, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 */
	public AbstractHmacCrypto(final Algorithm algorithm, final Charset charset) {
		super(algorithm, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AbstractHmacCrypto(final Algorithm algorithm, final String characterEncoding, final String salt) {
		super(algorithm, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AbstractHmacCrypto(final Algorithm algorithm, final Charset charset, final String salt) {
		super(algorithm, charset, salt);
	}

	@Override
	public String encrypt(final Object object) {
		Assert.isNull(object, "Mcrypt encrypt object could not be null");
		Assert.isNull(getAlgorithm(), "Algo could not be null");

		HmacUtils hmacUtils = new HmacUtils(getHmacAlgorithms(), getRealSalt().getBytes(getCharset()));
		return hmacUtils.hmacHex(ObjectUtils.toBytes(object, getCharset()));
	}

	protected abstract HmacAlgorithms getHmacAlgorithms();

}
