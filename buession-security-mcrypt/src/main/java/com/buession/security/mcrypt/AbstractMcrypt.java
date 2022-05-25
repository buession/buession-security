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
import com.buession.core.utils.CharacterUtils;
import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * 对象加解密抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractMcrypt implements Mcrypt {

	/**
	 * 请求算法的名称
	 */
	private Algo algo;

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
	 */
	public AbstractMcrypt(){
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 */
	public AbstractMcrypt(final Algo algo){
		this.algo = algo;
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public AbstractMcrypt(final Algo algo, final Provider provider){
		this.algo = algo;
		this.provider = provider;
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param characterEncoding
	 * 		字符编码
	 */
	public AbstractMcrypt(final Algo algo, final String characterEncoding){
		this.algo = algo;
		if(Validate.hasText(characterEncoding)){
			this.charset = Charset.forName(characterEncoding);
		}
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 */
	public AbstractMcrypt(final Algo algo, final Charset charset){
		this.algo = algo;
		this.charset = charset;
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
	public AbstractMcrypt(final Algo algo, final String characterEncoding, final Provider provider){
		this(algo, characterEncoding);
		this.provider = provider;
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
	public AbstractMcrypt(final Algo algo, final Charset charset, final Provider provider){
		this(algo, charset);
		this.provider = provider;
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
	public AbstractMcrypt(final Algo algo, final String characterEncoding, final String salt){
		this(algo, characterEncoding);
		this.salt = salt;
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
	public AbstractMcrypt(final Algo algo, final Charset charset, final String salt){
		this(algo, charset);
		this.salt = salt;
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
	public AbstractMcrypt(final Algo algo, final String characterEncoding, final String salt, final Provider provider){
		this(algo, characterEncoding, salt);
		this.provider = provider;
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
	public AbstractMcrypt(final Algo algo, final Charset charset, final String salt, final Provider provider){
		this(algo, charset, salt);
		this.provider = provider;
	}

	/**
	 * 返回请求算法的名称
	 *
	 * @return 返回请求算法的名称
	 */
	@Override
	public Algo getAlgo(){
		return algo;
	}

	/**
	 * 设置请求算法的名称
	 *
	 * @param algo
	 * 		请求算法的名称
	 */
	@Override
	public void setAlgo(final Algo algo){
		this.algo = algo;
	}

	/**
	 * 返回加密密钥
	 *
	 * @return 加密密钥
	 */
	@Override
	public String getSalt(){
		return salt;
	}

	/**
	 * 设置加密密钥
	 *
	 * @param salt
	 * 		加密密钥
	 */
	@Override
	public void setSalt(final String salt){
		this.salt = salt;
	}

	/**
	 * 获取字符串编码
	 *
	 * @return 字符串编码
	 */
	@Override
	public Charset getCharset(){
		return charset;
	}

	/**
	 * 设置字符串编码
	 *
	 * @param charset
	 * 		字符串编码
	 */
	@Override
	public void setCharset(final Charset charset){
		this.charset = charset;
	}

	/**
	 * 返回此信息摘要对象的提供者
	 *
	 * @return 信息摘要对象的提供者
	 */
	@Override
	public Provider getProvider(){
		return provider;
	}

	/**
	 * 设置信息摘要对象的提供者
	 *
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	@Override
	public void setProvider(final Provider provider){
		this.provider = provider;
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
		Assert.isNull(getAlgo(), "Algo could not be null");

		try{
			MessageDigest messageDigest = getProvider() == null ? MessageDigest.getInstance(getAlgo().getName()) :
					MessageDigest.getInstance(getAlgo().getName(), getProvider());

			if(object instanceof char[]){
				return encode(new String((char[]) object), messageDigest);
			}else if(object instanceof byte[]){
				return encode(new String((byte[]) object, getCharset()), messageDigest);
			}else{
				return encode(object.toString(), messageDigest);
			}
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
			throw new SecurityException(e);
		}
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
		Assert.isNull(getAlgo(), "Algo could not be null");
		throw new UnsupportedOperationException("Algo '" + getAlgo() + "' unsupported decode");
	}

	protected String object2String(final Object object){
		Assert.isNull(object, "Mcrypt encode object could not be null");

		if(object instanceof char[]){
			return new String((char[]) object);
		}else if(object instanceof byte[]){
			return new String((byte[]) object);
		}else{
			return object.toString();
		}
	}

	protected byte[] object2Bytes(final Object object){
		Assert.isNull(object, "Mcrypt encode object could not be null");

		if(object instanceof char[]){
			return CharacterUtils.toBytes((char[]) object);
		}else if(object instanceof byte[]){
			return (byte[]) object;
		}else if(object instanceof String){
			return getCharset() == null ? ((String) object).getBytes() : ((String) object).getBytes(getCharset());
		}else{
			String str = object.toString();
			return getCharset() == null ? str.getBytes() : str.getBytes(getCharset());
		}
	}

	protected String getRealSalt(){
		String salt = getSalt();
		return salt == null ? Constants.EMPTY_STRING : salt;
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
	private String encode(String str, final MessageDigest messageDigest){
		if(StringUtils.isNotEmpty(salt)){
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
	private static String getFormattedText(byte[] bytes){
		final StringBuilder buffer = new StringBuilder(bytes.length * 2);

		for(byte b : bytes){
			buffer.append(Constants.HEX_DIGITS[(b >> 4) & 0x0f]);
			buffer.append(Constants.HEX_DIGITS[b & 0x0f]);
		}

		return buffer.toString();
	}

}
