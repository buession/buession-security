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
package com.buession.security.mcrypt;

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
 */
public final class AESMcrypt extends AbstractMcrypt {

	/**
	 * 加密模式
	 *
	 * @since 2.0.0
	 */
	private com.buession.security.crypto.Mode mode = com.buession.security.crypto.Mode.ECB;

	/**
	 * 补码方式
	 *
	 * @since 2.0.0
	 */
	private com.buession.security.crypto.Padding padding = com.buession.security.crypto.Padding.PKCS5;

	/**
	 * 构造函数
	 */
	public AESMcrypt() {
		super(Algo.AES);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 */
	@Deprecated
	public AESMcrypt(final String characterEncoding) {
		super(Algo.AES, characterEncoding);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public AESMcrypt(final Charset charset) {
		super(Algo.AES, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AESMcrypt(final String characterEncoding, final String salt) {
		super(Algo.AES, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AESMcrypt(final Charset charset, final String salt) {
		super(Algo.AES, charset, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param mode
	 * 		加密模式
	 */
	@Deprecated
	public AESMcrypt(final Mode mode) {
		this(mode.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param mode
	 * 		加密模式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final com.buession.security.crypto.Mode mode) {
		this();
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 */
	@Deprecated
	public AESMcrypt(final String characterEncoding, final Mode mode) {
		this(characterEncoding, mode.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final String characterEncoding, final com.buession.security.crypto.Mode mode) {
		this(characterEncoding);
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
	@Deprecated
	public AESMcrypt(final Charset charset, final Mode mode) {
		this(charset, mode.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final Charset charset, final com.buession.security.crypto.Mode mode) {
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
	@Deprecated
	public AESMcrypt(final String characterEncoding, final String salt, final Mode mode) {
		this(characterEncoding, salt, mode.getOriginal());
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
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final String characterEncoding, final String salt, final com.buession.security.crypto.Mode mode) {
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
	@Deprecated
	public AESMcrypt(final Charset charset, final String salt, final Mode mode) {
		this(charset, salt, mode.getOriginal());
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
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final Charset charset, final String salt, final com.buession.security.crypto.Mode mode) {
		this(charset, salt);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param padding
	 * 		补码方式
	 */
	@Deprecated
	public AESMcrypt(final Padding padding) {
		this(padding.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final com.buession.security.crypto.Padding padding) {
		this();
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param padding
	 * 		补码方式
	 */
	@Deprecated
	public AESMcrypt(final String characterEncoding, final Padding padding) {
		this(characterEncoding, padding.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final String characterEncoding, final com.buession.security.crypto.Padding padding) {
		this(characterEncoding);
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
	@Deprecated
	public AESMcrypt(final Charset charset, final Padding padding) {
		this(charset, padding.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final Charset charset, final com.buession.security.crypto.Padding padding) {
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
	@Deprecated
	public AESMcrypt(final String characterEncoding, final String salt, final Padding padding) {
		this(characterEncoding, salt, padding.getOriginal());
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
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final String characterEncoding, final String salt,
					 final com.buession.security.crypto.Padding padding) {
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
	@Deprecated
	public AESMcrypt(final Charset charset, final String salt, final Padding padding) {
		this(charset, salt, padding.getOriginal());
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
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final Charset charset, final String salt, final com.buession.security.crypto.Padding padding) {
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
	@Deprecated
	public AESMcrypt(final Mode mode, final Padding padding) {
		this(mode.getOriginal(), padding.getOriginal());
	}

	/**
	 * 构造函数
	 *
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final com.buession.security.crypto.Mode mode, final com.buession.security.crypto.Padding padding) {
		this(mode);
		this.padding = padding;
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	@Deprecated
	public AESMcrypt(final String characterEncoding, final Mode mode, final Padding padding) {
		this(characterEncoding, mode.getOriginal(), padding.getOriginal());
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final String characterEncoding, final com.buession.security.crypto.Mode mode,
					 final com.buession.security.crypto.Padding padding) {
		this(characterEncoding, mode);
		this.padding = padding;
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	@Deprecated
	public AESMcrypt(final Charset charset, final Mode mode, final Padding padding) {
		this(charset, mode.getOriginal(), padding.getOriginal());
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final Charset charset, final com.buession.security.crypto.Mode mode,
					 final com.buession.security.crypto.Padding padding) {
		this(charset, mode);
		this.padding = padding;
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	@Deprecated
	public AESMcrypt(final String characterEncoding, final String salt, final Mode mode, final Padding padding) {
		this(characterEncoding, salt, mode.getOriginal(), padding.getOriginal());
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final String characterEncoding, final String salt, final com.buession.security.crypto.Mode mode,
					 final com.buession.security.crypto.Padding padding) {
		this(characterEncoding, salt, mode);
		this.padding = padding;
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	@Deprecated
	public AESMcrypt(final Charset charset, final String salt, final Mode mode, final Padding padding) {
		this(charset, salt, mode.getOriginal(), padding.getOriginal());
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 *
	 * @since 2.3.0
	 */
	public AESMcrypt(final Charset charset, final String salt, final com.buession.security.crypto.Mode mode,
					 final com.buession.security.crypto.Padding padding) {
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

	/**
	 * 加密模式
	 *
	 * @since 2.0.0
	 */
	@Deprecated
	public enum Mode {

		ECB(com.buession.security.crypto.Mode.ECB),

		CBC(com.buession.security.crypto.Mode.CBC),

		CTR(com.buession.security.crypto.Mode.CTR),

		CFB(com.buession.security.crypto.Mode.CFB),

		OFB(com.buession.security.crypto.Mode.OFB);

		private final com.buession.security.crypto.Mode original;

		Mode(final com.buession.security.crypto.Mode original) {
			this.original = original;
		}

		public String getValue() {
			return "";
		}

		public String getName() {
			return "";
		}

		public com.buession.security.crypto.Mode getOriginal() {
			return original;
		}

	}

	/**
	 * 补码方式
	 *
	 * @since 2.0.0
	 */
	@Deprecated
	public enum Padding {

		NO_PADDING(com.buession.security.crypto.Padding.NO),

		ZERO_PADDING(com.buession.security.crypto.Padding.ZERO),

		PKCS5_PADDING(com.buession.security.crypto.Padding.PKCS5),

		PKCS7_PADDING(com.buession.security.crypto.Padding.PKCS7),

		ISO10126_PADDING(com.buession.security.crypto.Padding.ISO10126),

		ANSIX923_PADDING(com.buession.security.crypto.Padding.ANSIX923);

		private final com.buession.security.crypto.Padding original;

		Padding(final com.buession.security.crypto.Padding original) {
			this.original = original;
		}

		public String getValue() {
			return original.toString();
		}

		public com.buession.security.crypto.Padding getOriginal() {
			return original;
		}

		@Override
		public String toString() {
			return original.toString();
		}

	}

}