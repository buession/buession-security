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
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Optional;

/**
 * 对象加解密抽象类
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractCrypto implements Crypto {

	/**
	 * 加密算法
	 */
	private Algorithm algorithm;

	/**
	 * 加密密钥
	 */
	private String salt;

	/**
	 * 字符串编码
	 */
	private Charset charset = Charset.defaultCharset();

	/**
	 * 信息摘要对象的提供者
	 */
	private Provider provider = null;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 */
	public AbstractCrypto(final Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param salt
	 * 		加密密钥
	 */
	public AbstractCrypto(final Algorithm algorithm, final String salt) {
		this(algorithm);
		this.salt = salt;
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param charset
	 * 		字符编码
	 */
	public AbstractCrypto(final Algorithm algorithm, final Charset charset) {
		this(algorithm);
		this.charset = charset;
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
	public AbstractCrypto(final Algorithm algorithm, final Charset charset, final String salt) {
		this(algorithm, charset);
		this.salt = salt;
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractCrypto(final Algorithm algorithm, final Provider provider) {
		this(algorithm);
		this.provider = provider;
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractCrypto(final Algorithm algorithm, final Charset charset, final Provider provider) {
		this(algorithm, charset);
		this.provider = provider;
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractCrypto(final Algorithm algorithm, final String salt, final Provider provider) {
		this(algorithm, salt);
		this.provider = provider;
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
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractCrypto(final Algorithm algorithm, final Charset charset, final String salt,
						  final Provider provider) {
		this(algorithm, charset, salt);
		this.provider = provider;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	@Override
	public String getAlgorithmName() {
		return algorithm.getName();
	}

	@Override
	public String getSalt() {
		return salt;
	}

	@Override
	public void setSalt(final String salt) {
		this.salt = salt;
	}

	@Override
	public Charset getCharset() {
		return charset;
	}

	@Override
	public void setCharset(final Charset charset) {
		this.charset = charset;
	}

	@Override
	public Provider getProvider() {
		return provider;
	}

	@Override
	public void setProvider(final Provider provider) {
		this.provider = provider;
	}

	@Override
	public String encrypt(final Object object) {
		Assert.isNull(object, "Mcrypt encode object could not be null");
		Assert.isNull(getAlgorithmName(), "Algo name could not be null");

		try{
			MessageDigest messageDigest = getProvider() == null ? MessageDigest.getInstance(getAlgorithmName()) :
					MessageDigest.getInstance(getAlgorithmName(), getProvider());

			if(object instanceof char[]){
				return encrypt(new String((char[]) object), messageDigest);
			}else if(object instanceof byte[]){
				return encrypt(new String((byte[]) object, getCharset()), messageDigest);
			}else{
				return encrypt(object.toString(), messageDigest);
			}
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
			throw new SecurityException(e);
		}
	}

	@Override
	public String decrypt(final CharSequence cs) {
		Assert.isNull(getAlgorithmName(), "Algo name could not be null");
		throw new UnsupportedOperationException("Algo '" + getAlgorithmName() + "' unsupported decode");
	}

	/**
	 * 字符串加密
	 *
	 * @param str
	 * 		需要加密的字符串
	 * @param messageDigest
	 * 		实现指定摘要算法的 MessageDigest 对象
	 *
	 * @return 加密后的字符串
	 */
	private String encrypt(String str, final MessageDigest messageDigest) {
		if(Validate.isNotEmpty(salt)){
			messageDigest.reset();

			if(charset == null){
				messageDigest.update(salt.getBytes());
			}else{
				messageDigest.update(salt.getBytes(charset));
			}
		}

		if(charset == null){
			messageDigest.update(str.getBytes());
		}else{
			messageDigest.update(str.getBytes(charset));
		}

		return getFormattedText(messageDigest.digest());
	}

	/**
	 * 字节填充
	 *
	 * @param bytes
	 * 		字节
	 *
	 * @return formatted string
	 */
	private static String getFormattedText(byte[] bytes) {
		final StringBuilder buffer = new StringBuilder(bytes.length * 2);

		for(byte b : bytes){
			buffer.append(Constants.HEX_DIGITS[(b >> 4) & 0x0f]);
			buffer.append(Constants.HEX_DIGITS[b & 0x0f]);
		}

		return buffer.toString();
	}

	protected String getRealSalt() {
		return Optional.ofNullable(getSalt()).orElse(Constants.EMPTY_STRING);
	}

}
