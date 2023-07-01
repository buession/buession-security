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
package com.buession.security.crypto.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class SymmetricalCryptoUtils {

	private SymmetricalCryptoUtils() {

	}

	public static String encrypt(final Object object, final Cipher cipher, final Key key, final Charset charset)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 初始化为加密模式的密码器
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = cipher.doFinal(ObjectUtils.toBytes(object, charset));
		return Base64.encodeBase64String(result);
	}

	public static String decrypt(final CharSequence cs, final Cipher cipher, final Key key)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 初始化为解密模式的密码器
		cipher.init(Cipher.DECRYPT_MODE, key);

		// 明文
		byte[] result = cipher.doFinal(Base64.decodeBase64(cs.toString()));
		return new String(result);
	}

}
