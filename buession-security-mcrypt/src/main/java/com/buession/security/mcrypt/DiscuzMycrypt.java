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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.mcrypt;

import com.buession.core.datetime.DateTime;
import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import com.buession.lang.Constants;

import java.nio.charset.Charset;

/**
 * Discuz 版加解密
 *
 * @author Yong.Teng
 */
public final class DiscuzMycrypt extends AbstractMcrypt {

	private final static int KEY_LENGTH = 4;

	private final static MD5Mcrypt md5Mcrypt = new MD5Mcrypt();

	private final static Base64Mcrypt base64Mcrypt = new Base64Mcrypt();

	public DiscuzMycrypt(){
		super(null);
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 */
	public DiscuzMycrypt(final String characterEncoding){
		super(null, characterEncoding);
	}

	/**
	 * @param charset
	 * 		字符编码
	 */
	public DiscuzMycrypt(final Charset charset){
		super(null, charset);
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DiscuzMycrypt(final String characterEncoding, final String salt){
		super(null, characterEncoding, salt);
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DiscuzMycrypt(final Charset charset, final String salt){
		super(null, charset, salt);
	}

	/**
	 * 对象加密
	 *
	 * @param object
	 * 		需要加密的字符串
	 *
	 * @return 加密后的字符串
	 */
	@Override
	public String encode(final Object object){
		Assert.isNull(object, "Mcrypt encode object could not be null");

		String s = object2String(object);

		String key = md5(md5(getRealSalt()));
		String keya = md5(StringUtils.substr(key, 16, 16));
		String keyb = StringUtils.substr(md5(StringUtils.replace(DateTime.microtime(), " ", ".")), -4);
		String keyc = getResultKey(key, keyb);

		s = StringUtils.repeat('0', 10) + StringUtils.substr(md5(s + keya), 0, 16) + s;
		s = StringUtils.replace(base64Mcrypt.encode(mod(s, keyc)), "=", Constants.EMPTY_STRING);

		StringBuilder sb = new StringBuilder(keyb.length() + s.length());

		sb.append(keyb).append(s);

		return sb.toString();
	}

	/**
	 * 字符串解密
	 * 该方法需要提供信息摘要算法支持双向解密才可用
	 *
	 * @param cs
	 * 		要被解密的 char 值序列
	 *
	 * @return 解密后的字符串
	 */
	@Override
	public String decode(final CharSequence cs){
		Assert.isNull(cs, "Mcrypt decode object could not be null");

		String s = cs.toString();

		String key = md5(md5(getRealSalt()));
		String keya = md5(StringUtils.substr(key, 16, 16));
		String keyb = StringUtils.substr(cs.toString(), 0, KEY_LENGTH);
		String keyc = getResultKey(key, keyb);

		s = base64Mcrypt.decode(StringUtils.substr(s, KEY_LENGTH));

		String result = mod(s, keyc);

		String s1 = StringUtils.substr(result, 0, 10);
		String s2 = StringUtils.substr(result, 26);
		long j = Long.parseLong(s1);
		String k1 = md5Mcrypt.encode(s2 + keya);
		long timestamp = System.currentTimeMillis() / 1000;

		return (j == 0 || j - timestamp > 0) && StringUtils.substr(result, 10, 16).equals(StringUtils.substr(k1, 0,
				16)) ? s2 : Constants.EMPTY_STRING;
	}

	private static String md5(final String str){
		return md5Mcrypt.encode(str == null ? "" : str).toLowerCase();
	}

	private static String getResultKey(final String str, final String key){
		if(key.length() <= 16){
			return md5(key + StringUtils.substr(str, 0, 16) + StringUtils.substr(str, 16));
		}else{
			return md5(StringUtils.substr(key, 0, 16) + StringUtils.substr(key, 0, 16) + (StringUtils.substr(key, 16)) + StringUtils.substr(str, 16));
		}
	}

	private static String mod(final String str, final String key){
		int strLength = str.length();
		StringBuilder sb = new StringBuilder(strLength);

		for(int i = 0; i < strLength; i++){
			int j = str.charAt(i);
			int k = key.charAt(i % 32);

			sb.append((char) (j ^ k));
		}

		return sb.toString();
	}

}