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

import com.buession.core.validator.Validate;
import com.buession.security.crypto.AbstractCrypto;
import com.buession.security.crypto.Algorithm;

import java.nio.charset.Charset;
import java.security.Provider;

/**
 * 对象加解密抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractMcrypt extends AbstractCrypto implements Mcrypt {

	/**
	 * 请求算法的名称
	 */
	private Algo algo;

	/**
	 * 构造函数
	 */
	@Deprecated
	public AbstractMcrypt() {
		super(null);
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 *
	 * @since 2.3.0
	 */
	public AbstractMcrypt(final Algorithm algorithm) {
		super(algorithm);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 */
	public AbstractMcrypt(final Algo algo) {
		super(algo.getAlgorithm());
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractMcrypt(final Algo algo, final Provider provider) {
		super(algo.getAlgorithm(), provider);
		this.algo = algo;
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param characterEncoding
	 * 		字符编码
	 */
	public AbstractMcrypt(final Algo algo, final String characterEncoding) {
		super(algo.getAlgorithm());
		this.algo = algo;
		if(Validate.hasText(characterEncoding)){
			setCharset(Charset.forName(characterEncoding));
		}
	}

	/**
	 * 构造函数
	 *
	 * @param algorithm
	 * 		加密算法
	 * @param charset
	 * 		字符编码
	 *
	 * @since 2.3.0
	 */
	public AbstractMcrypt(final Algorithm algorithm, final Charset charset) {
		super(algorithm, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 */
	public AbstractMcrypt(final Algo algo, final Charset charset) {
		super(algo.getAlgorithm(), charset);
		this.algo = algo;
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractMcrypt(final Algo algo, final String characterEncoding, final Provider provider) {
		this(algo, characterEncoding);
		setProvider(provider);
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
	 *
	 * @since 2.3.0
	 */
	public AbstractMcrypt(final Algorithm algorithm, final Charset charset, final Provider provider) {
		super(algorithm, charset, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractMcrypt(final Algo algo, final Charset charset, final Provider provider) {
		this(algo, charset);
		setProvider(provider);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AbstractMcrypt(final Algo algo, final String characterEncoding, final String salt) {
		this(algo, characterEncoding);
		setSalt(salt);
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
	 *
	 * @since 2.3.0
	 */
	public AbstractMcrypt(final Algorithm algorithm, final Charset charset, final String salt) {
		super(algorithm, charset, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public AbstractMcrypt(final Algo algo, final Charset charset, final String salt) {
		this(algo, charset);
		setSalt(salt);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractMcrypt(final Algo algo, final String characterEncoding, final String salt, final Provider provider) {
		this(algo, characterEncoding, salt);
		setProvider(provider);
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
	 *
	 * @since 2.3.0
	 */
	public AbstractMcrypt(final Algorithm algorithm, final Charset charset, final String salt,
						  final Provider provider) {
		super(algorithm, charset, salt, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractMcrypt(final Algo algo, final Charset charset, final String salt, final Provider provider) {
		this(algo, charset, salt);
		setProvider(provider);
	}

	@Override
	public Algo getAlgo() {
		return algo;
	}

	@Override
	public void setAlgo(final Algo algo) {
		this.algo = algo;
	}

}
