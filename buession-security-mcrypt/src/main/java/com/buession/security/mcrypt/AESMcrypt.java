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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.mcrypt;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

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
	private Mode mode = Mode.ECB;

	/**
	 * 补码方式
	 *
	 * @since 2.0.0
	 */
	private Padding padding = Padding.PKCS5_PADDING;

	private Cipher cipher = null;

	private final static Logger logger = LoggerFactory.getLogger(AESMcrypt.class);

	/**
	 * 构造函数
	 */
	public AESMcrypt(){
		super(Algo.AES);
	}

	/**
	 * 构造函数
	 *
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AESMcrypt(final Provider provider){
		super(Algo.AES, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 */
	public AESMcrypt(final String characterEncoding){
		super(Algo.AES, characterEncoding);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public AESMcrypt(final Charset charset){
		super(Algo.AES, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AESMcrypt(final String characterEncoding, final Provider provider){
		this(characterEncoding, null, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AESMcrypt(final Charset charset, final Provider provider){
		this(charset, null, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AESMcrypt(final String characterEncoding, final String salt){
		this(characterEncoding, salt, (Provider) null);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AESMcrypt(final Charset charset, final String salt){
		this(charset, salt, (Provider) null);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AESMcrypt(final String characterEncoding, final String salt, final Provider provider){
		super(Algo.AES, characterEncoding, salt, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AESMcrypt(final Charset charset, final String salt, final Provider provider){
		super(Algo.AES, charset, salt, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param mode
	 * 		加密模式
	 */
	public AESMcrypt(final Mode mode){
		this();
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 */
	public AESMcrypt(final Provider provider, final Mode mode){
		this(provider);
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
	public AESMcrypt(final String characterEncoding, final Mode mode){
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
	public AESMcrypt(final Charset charset, final Mode mode){
		this(charset);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 */
	public AESMcrypt(final String characterEncoding, final Provider provider, final Mode mode){
		this(characterEncoding, provider);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 */
	public AESMcrypt(final Charset charset, final Provider provider, final Mode mode){
		this(charset, provider);
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
	public AESMcrypt(final String characterEncoding, final String salt, final Mode mode){
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
	public AESMcrypt(final Charset charset, final String salt, final Mode mode){
		this(charset, salt);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 */
	public AESMcrypt(final String characterEncoding, final String salt, final Provider provider, final Mode mode){
		this(characterEncoding, salt, provider);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 */
	public AESMcrypt(final Charset charset, final String salt, final Provider provider, final Mode mode){
		this(charset, salt, provider);
		this.mode = mode;
	}

	/**
	 * 构造函数
	 *
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Padding padding){
		this();
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Provider provider, final Padding padding){
		this(provider);
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
	public AESMcrypt(final String characterEncoding, final Padding padding){
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
	public AESMcrypt(final Charset charset, final Padding padding){
		this(charset);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final String characterEncoding, final Provider provider, final Padding padding){
		this(characterEncoding, provider);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Charset charset, final Provider provider, final Padding padding){
		this(charset, provider);
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
	public AESMcrypt(final String characterEncoding, final String salt, final Padding padding){
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
	public AESMcrypt(final Charset charset, final String salt, final Padding padding){
		this(charset, salt);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final String characterEncoding, final String salt, final Provider provider, final Padding padding){
		this(characterEncoding, salt, provider);
		this.padding = padding;
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Charset charset, final String salt, final Provider provider, final Padding padding){
		this(charset, salt, provider);
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
	public AESMcrypt(final Mode mode, final Padding padding){
		this(mode);
		this.padding = padding;
	}

	/**
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Provider provider, final Mode mode, final Padding padding){
		this(provider, mode);
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
	public AESMcrypt(final String characterEncoding, final Mode mode, final Padding padding){
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
	public AESMcrypt(final Charset charset, final Mode mode, final Padding padding){
		this(charset, mode);
		this.padding = padding;
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final String characterEncoding, final Provider provider, final Mode mode, final Padding padding){
		this(characterEncoding, null, provider, mode);
		this.padding = padding;
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Charset charset, final Provider provider, final Mode mode, final Padding padding){
		this(charset, provider, mode);
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
	public AESMcrypt(final String characterEncoding, final String salt, final Mode mode, final Padding padding){
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
	public AESMcrypt(final Charset charset, final String salt, final Mode mode, final Padding padding){
		this(charset, salt, mode);
		this.padding = padding;
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final String characterEncoding, final String salt, final Provider provider, final
	Mode mode, final Padding padding){
		this(characterEncoding, salt, provider, mode);
		this.padding = padding;
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 * @param mode
	 * 		加密模式
	 * @param padding
	 * 		补码方式
	 */
	public AESMcrypt(final Charset charset, final String salt, final Provider provider, final
	Mode mode, final Padding padding){
		this(charset, salt, provider, mode);
		this.padding = padding;
	}

	@Override
	public String encode(final Object object){
		Assert.isNull(object, "Mcrypt encode object could not be null");

		try{
			Key key = getKey();

			initCipher();

			// 初始化为加密模式的密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] result = cipher.doFinal(object2Bytes(object));
			return Base64.encodeBase64String(result);
		}catch(InvalidKeyException e){
			logger.error(e.getMessage());
		}catch(IllegalBlockSizeException e){
			logger.error(e.getMessage());
		}catch(BadPaddingException e){
			logger.error(e.getMessage());
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
		}catch(NoSuchPaddingException e){
			logger.error(e.getMessage());
		}

		return null;
	}

	@Override
	public String decode(final CharSequence cs){
		Assert.isNull(cs, "Mcrypt decode object could not be null");

		try{
			Key key = getKey();

			initCipher();

			// 初始化为解密模式的密码器
			cipher.init(Cipher.DECRYPT_MODE, key);

			// 明文
			byte[] result = cipher.doFinal(Base64.decodeBase64(cs.toString()));
			return new String(result);
		}catch(InvalidKeyException e){
			logger.error(e.getMessage());
		}catch(IllegalBlockSizeException e){
			logger.error(e.getMessage());
		}catch(BadPaddingException e){
			logger.error(e.getMessage());
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
		}catch(NoSuchPaddingException e){
			logger.error(e.getMessage());
		}

		return null;
	}

	private Cipher initCipher() throws NoSuchAlgorithmException, NoSuchPaddingException{
		if(cipher == null){
			cipher = Cipher.getInstance(Algo.AES.getName() + "/" + mode.name() + "/" + padding.getValue());
		}

		return cipher;
	}

	private Key getKey() throws NoSuchAlgorithmException{
		String salt = getRealSalt();
		KeyGenerator keyGenerator = KeyGenerator.getInstance(Algo.AES.getName());

		keyGenerator.init(128);

		if(salt.length() < 16){
			salt += StringUtils.repeat(' ', 16 - salt.length());
		}else if(salt.length() > 16){
			salt = StringUtils.substr(salt, 0, 16);
		}

		return new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), Algo.AES.getName());
	}

	/**
	 * 加密模式
	 *
	 * @since 2.0.0
	 */
	public enum Mode {

		ECB("Electronic Codebook Book", "电码本模式"),

		CBC("Cipher Block Chaining", "密码分组链接模式"),

		CTR("Counter", "计算器模式"),

		CFB("Cipher FeedBack", "密码反馈模式"),

		OFB("Output FeedBack", "输出反馈模式");

		private final String value;

		private final String name;

		Mode(final String value, final String name){
			this.value = value;
			this.name = name;
		}

		public String getValue(){
			return value;
		}

		public String getName(){
			return name;
		}

	}

	/**
	 * 补码方式
	 *
	 * @since 2.0.0
	 */
	public enum Padding {

		NO_PADDING("NoPadding"),

		ZERO_PADDING("ZeroPadding"),

		PKCS5_PADDING("PKCS5Padding"),

		PKCS7_PADDING("PKCS7Padding"),

		ISO10126_PADDING("ISO10126Padding"),

		ANSIX923_PADDING("ANSIX923Padding");

		private final String value;

		Padding(final String value){
			this.value = value;
		}

		public String getValue(){
			return value;
		}

	}

}