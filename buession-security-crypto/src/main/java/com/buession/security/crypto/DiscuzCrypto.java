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

import com.buession.core.datetime.DateTime;
import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Constants;
import com.buession.security.crypto.utils.ObjectUtils;

import java.nio.charset.Charset;
import java.util.Optional;

/**
 * Discuz 版加解密
 *
 * @author Yong.Teng
 * @since 2.3.2
 */
public final class DiscuzCrypto extends AbstractCrypto {

	private final static int KEY_LENGTH = 4;

	/**
	 * 构造函数
	 */
	public DiscuzCrypto() {
		super(null);
	}

	/**
	 * 构造函数
	 *
	 * @param salt
	 * 		加密密钥
	 */
	public DiscuzCrypto(final String salt) {
		super(null, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public DiscuzCrypto(final Charset charset) {
		super(null, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DiscuzCrypto(final String characterEncoding, final String salt) {
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
	public DiscuzCrypto(final Charset charset, final String salt) {
		super(null, charset, salt);
	}

	@Override
	public String encrypt(final Object object) {
		Assert.isNull(object, "Mcrypt encrypt object could not be null");

		Base64Crypto crypto = new Base64Crypto();
		String s = ObjectUtils.toString(object);

		String key = md5(md5(getRealSalt()));
		String keya = md5(StringUtils.substr(key, 16, 16));
		String keyb = StringUtils.substr(md5(StringUtils.replace(DateTime.microtime(), Constants.SPACING_STRING, ".")),
				-4);
		String keyc = getResultKey(key, keyb);

		s = StringUtils.repeat('0', 10) + StringUtils.substr(md5(s + keya), 0, 16) + s;
		s = StringUtils.replace(crypto.encrypt(mod(s, keyc)), "=", Constants.EMPTY_STRING);

		return keyb + s;
	}

	@Override
	public String decrypt(final CharSequence cs) {
		Assert.isNull(cs, "Mcrypt decrypt object could not be null");

		Base64Crypto crypto = new Base64Crypto();
		String s = cs.toString();

		String key = md5(md5(getRealSalt()));
		String keya = md5(StringUtils.substr(key, 16, 16));
		String keyb = StringUtils.substr(cs.toString(), 0, KEY_LENGTH);
		String keyc = getResultKey(key, keyb);

		s = crypto.decrypt(StringUtils.substr(s, KEY_LENGTH));

		String result = mod(s, keyc);

		String s1 = StringUtils.substr(result, 0, 10);
		String s2 = StringUtils.substr(result, 26);
		long j = Long.parseLong(s1);
		String k1 = md5(s2 + keya);
		long timestamp = DateTime.unixtime();

		return (j == 0 || j - timestamp > 0) && StringUtils.substr(result, 10, 16).equals(StringUtils.substr(k1, 0,
				16)) ? s2 : Constants.EMPTY_STRING;
	}

	private static String md5(final String str) {
		MD5Crypto crypto = new MD5Crypto();
		return crypto.encrypt(Optional.ofNullable(str).orElse(Constants.EMPTY_STRING)).toLowerCase();
	}

	private static String getResultKey(final String str, final String key) {
		if(key.length() <= 16){
			return md5(key + StringUtils.substr(str, 0, 16) + StringUtils.substr(str, 16));
		}else{
			return md5(StringUtils.substr(key, 0, 16) + StringUtils.substr(key, 0, 16) + (StringUtils.substr(key, 16)) +
					StringUtils.substr(str, 16));
		}
	}

	private static String mod(final String str, final String key) {
		int strLength = str.length();
		char[] result = new char[strLength];

		for(int i = 0; i < strLength; i++){
			int j = str.charAt(i);
			int k = key.charAt(i % 32);

			result[i] = (char) (j ^ k);
		}

		return new String(result);
	}

}