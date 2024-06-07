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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.mcrypt;

import com.buession.security.crypto.Algorithm;
import com.buession.security.crypto.DiscuzCrypto;

import java.nio.charset.Charset;

/**
 * Discuz 版加解密
 *
 * @author Yong.Teng
 */
@Deprecated
public final class DiscuzMycrypt extends AbstractMcrypt {

	/**
	 * 构造函数
	 */
	public DiscuzMycrypt() {
		super((Algorithm) null);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 */
	@Deprecated
	public DiscuzMycrypt(final String characterEncoding) {
		super(null, characterEncoding);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public DiscuzMycrypt(final Charset charset) {
		super((Algorithm) null, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DiscuzMycrypt(final String characterEncoding, final String salt) {
		super(null, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DiscuzMycrypt(final Charset charset, final String salt) {
		super((Algorithm) null, charset, salt);
	}

	@Override
	public String encrypt(final Object object) {
		final DiscuzCrypto crypto = new DiscuzCrypto(getCharset(), getSalt());
		return crypto.encrypt(object);
	}

	@Override
	public String decrypt(final CharSequence cs) {
		final DiscuzCrypto crypto = new DiscuzCrypto(getCharset(), getSalt());
		return crypto.decrypt(cs);
	}

}