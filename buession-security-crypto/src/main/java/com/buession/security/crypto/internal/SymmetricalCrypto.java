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
package com.buession.security.crypto.internal;

import com.buession.core.utils.StringUtils;
import com.buession.lang.Constants;
import com.buession.security.crypto.Algorithm;
import com.buession.security.crypto.Mode;
import com.buession.security.crypto.Padding;
import com.buession.security.crypto.utils.CipherBuilder;
import com.buession.security.crypto.utils.ObjectUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Optional;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class SymmetricalCrypto {

	/**
	 * 加密算法
	 */
	private final Algorithm algorithm;

	/**
	 * 字符串编码
	 */
	private final Charset charset;

	/**
	 * 加密密钥
	 */
	private final String salt;

	private final Cipher cipher;

	private final Key key;

	public SymmetricalCrypto(final Algorithm algorithm, final Charset charset, final Mode mode, final Padding padding
			, final Provider provider, final Key key) throws NoSuchPaddingException, NoSuchAlgorithmException {
		this.algorithm = algorithm;
		this.charset = charset;
		this.salt = null;
		this.key = key;
		this.cipher = getCipher(mode, padding, provider);
	}

	public SymmetricalCrypto(final Algorithm algorithm, final Charset charset, final Mode mode, final Padding padding
			, final Provider provider, final String salt) throws NoSuchPaddingException, NoSuchAlgorithmException {
		this.algorithm = algorithm;
		this.charset = charset;
		this.salt = salt;
		this.key = getKey();
		this.cipher = getCipher(mode, padding, provider);
	}

	public String encrypt(final Object object) throws GeneralSecurityException {
		// 初始化为加密模式的密码器
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = cipher.doFinal(ObjectUtils.toBytes(object, charset));
		return Base64.encodeBase64String(result);
	}

	public String decrypt(final CharSequence cs) throws GeneralSecurityException {
		// 初始化为解密模式的密码器
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] result = cipher.doFinal(Base64.decodeBase64(cs.toString()));
		return new String(result);
	}

	private Key getKey() throws NoSuchAlgorithmException {
		String salt = Optional.ofNullable(this.salt).orElse(Constants.EMPTY_STRING);
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.getName());

		keyGenerator.init(128);

		if(salt.length() < 16){
			salt += StringUtils.repeat(' ', 16 - salt.length());
		}else if(salt.length() > 16){
			salt = StringUtils.substr(salt, 0, 16);
		}

		return new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), algorithm.getName());
	}

	private Cipher getCipher(final Mode mode, final Padding padding, final Provider provider)
			throws NoSuchPaddingException, NoSuchAlgorithmException {
		final CipherBuilder cipherBuilder =
				CipherBuilder.getInstance(algorithm.getName()).mode(mode).padding(padding).provider(provider);
		return cipherBuilder.create();
	}

}
