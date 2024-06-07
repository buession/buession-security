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

import com.buession.security.crypto.HashCrypto;

import java.nio.charset.Charset;
import java.security.Provider;

/**
 * SHA-224 加密对象
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
@Deprecated
public final class Sha224Mcrypt extends AbstractMcrypt implements HashCrypto {

	/**
	 * 构造函数
	 */
	public Sha224Mcrypt() {
		super(Algo.SHA224);
	}

	/**
	 * 构造函数
	 *
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public Sha224Mcrypt(final Provider provider) {
		super(Algo.SHA224, provider);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 */
	@Deprecated
	public Sha224Mcrypt(final String characterEncoding) {
		super(Algo.SHA224, characterEncoding);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public Sha224Mcrypt(final Charset charset) {
		super(Algo.SHA224, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public Sha224Mcrypt(final String characterEncoding, final Provider provider) {
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
	public Sha224Mcrypt(final Charset charset, final Provider provider) {
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
	public Sha224Mcrypt(final String characterEncoding, final String salt) {
		this(characterEncoding, salt, null);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public Sha224Mcrypt(final Charset charset, final String salt) {
		this(charset, salt, null);
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
	public Sha224Mcrypt(final String characterEncoding, final String salt, final Provider provider) {
		super(Algo.SHA224, characterEncoding, salt, provider);
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
	public Sha224Mcrypt(final Charset charset, final String salt, final Provider provider) {
		super(Algo.SHA224, charset, salt, provider);
	}

}
