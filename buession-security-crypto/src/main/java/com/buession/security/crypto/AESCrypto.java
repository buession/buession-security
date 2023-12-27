/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.crypto;

import com.buession.core.utils.Assert;
import com.buession.security.crypto.internal.SymmetricalCrypto;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/**
 * AES 加密对象
 * slat 不足 16 位，以空格填充；slat 超过 16 位将截取前 16 位
 * 加密结果以 Base64 返回
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
public final class AESCrypto extends AbstractCrypto {

	/**
	 * 加密模式
	 */
	private Mode mode = Mode.ECB;

	/**
	 * 补码方式
	 */
	private Padding padding = Padding.PKCS5;

	/**
	 * 构造函数
	 */
	public AESCrypto() {
		super(Algorithm.AES);
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 */
	public AESCrypto(final String salt) {
		super(Algorithm.AES, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public AESCrypto(final Charset charset) {
		super(Algorithm.AES, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AESCrypto(final String characterEncoding, final String salt) {
		super(Algorithm.AES, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AESCrypto(final Charset charset, final String salt) {
		super(Algorithm.AES, charset, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param mode
	 * 		加密模式
	 */
	public AESCrypto(final Mode mode) {
		this();
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 */
	public AESCrypto(final String salt, final Mode mode) {
		this(salt);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 */
	public AESCrypto(final Charset charset, final Mode mode) {
		this(charset);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 */
	public AESCrypto(final String characterEncoding, final String salt, final Mode mode) {
		this(characterEncoding, salt);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 */
	public AESCrypto(final Charset charset, final String salt, final Mode mode) {
		this(charset, salt);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final Padding padding) {
		this();
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final String salt, final Padding padding) {
		this(salt);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final Charset charset, final Padding padding) {
		this(charset);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final String characterEncoding, final String salt, final Padding padding) {
		this(characterEncoding, salt);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final Charset charset, final String salt, final Padding padding) {
		this(charset, salt);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final Mode mode, final Padding padding) {
		this(mode);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final String salt, final Mode mode, final Padding padding) {
		this(salt, mode);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final Charset charset, final Mode mode, final Padding padding) {
		this(charset, mode);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final String characterEncoding, final String salt, final Mode mode, final Padding padding) {
		this(characterEncoding, salt, mode);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESCrypto(final Charset charset, final String salt, final Mode mode, final Padding padding) {
		this(charset, salt, mode);
		this.padding = padding;
	}

	@Override
	public String encrypt(final Object object) {
		Assert.isNull(object, "Mcrypt encrypt object could not be null");

		try{
			SymmetricalCrypto crypto = new SymmetricalCrypto(getAlgorithm(), getCharset(), mode, padding, getProvider(),
					getSalt());
			return crypto.encrypt(object);
		}catch(GeneralSecurityException e){
			logger.error(e.getMessage());
			throw new SecurityException(e);
		}
	}

	@Override
	public String decrypt(final CharSequence cs) {
		Assert.isNull(cs, "Mcrypt decrypt object could not be null");

		try{
			SymmetricalCrypto crypto = new SymmetricalCrypto(getAlgorithm(), getCharset(), mode, padding,
					getProvider(), getSalt());
			return crypto.decrypt(cs);
		}catch(GeneralSecurityException e){
			logger.error(e.getMessage());
			throw new SecurityException(e);
		}
	}

}