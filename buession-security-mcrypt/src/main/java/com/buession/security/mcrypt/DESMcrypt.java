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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.security.mcrypt;

import com.buession.core.utils.Assert;
import com.buession.core.utils.ObjectUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * DES 加密对象
 *
 * @author Yong.Teng
 */
public final class DESMcrypt extends AbstractMcrypt {

	private static Cipher cipher = null;

	private final static Logger logger = LoggerFactory.getLogger(DESMcrypt.class);

	public DESMcrypt(){
		super(Algo.DES);
	}

	/**
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public DESMcrypt(final Provider provider){
		super(Algo.DES, provider);
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 */
	public DESMcrypt(final String characterEncoding){
		super(Algo.DES, characterEncoding);
	}

	/**
	 * @param charset
	 * 		字符编码
	 */
	public DESMcrypt(final Charset charset){
		super(Algo.DES, charset);
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public DESMcrypt(final String characterEncoding, final Provider provider){
		this(characterEncoding, null, provider);
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public DESMcrypt(final Charset charset, final Provider provider){
		this(charset, null, provider);
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DESMcrypt(final String characterEncoding, final String salt){
		this(characterEncoding, salt, null);
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public DESMcrypt(final Charset charset, final String salt){
		this(charset, salt, null);
	}

	/**
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public DESMcrypt(final String characterEncoding, final String salt, final Provider provider){
		super(Algo.DES, characterEncoding, salt, provider);
	}

	/**
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 * @param provider
	 * 		信息摘要对象的提供者
	 */
	public DESMcrypt(final Charset charset, final String salt, final Provider provider){
		super(Algo.DES, charset, salt, provider);
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

		try{
			DESKeySpec dks = new DESKeySpec(getRealSalt().getBytes());
			SecretKeyFactory secretKeyFactory = getSecretKeyFactory();
			SecretKey secretKey = secretKeyFactory.generateSecret(dks);

			SecureRandom sr = new SecureRandom();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);

			byte[] result = cipher.doFinal(getCharset() == null ? ObjectUtils.toByte(object) :
					ObjectUtils.toByte(object, getCharset()));

			return result == null ? null : Hex.encodeHexString(result);
		}catch(IllegalBlockSizeException e){
			logger.error(e.getMessage());
		}catch(BadPaddingException e){
			logger.error(e.getMessage());
		}catch(InvalidKeySpecException e){
			logger.error(e.getMessage());
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
		}catch(InvalidKeyException e){
			logger.error(e.getMessage());
		}

		return null;
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

		try{
			DESKeySpec dks = new DESKeySpec(getRealSalt().getBytes());
			SecretKeyFactory secretKeyFactory = getSecretKeyFactory();
			SecretKey secretKey = secretKeyFactory.generateSecret(dks);

			SecureRandom sr = new SecureRandom();
			cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);

			byte[] result = cipher.doFinal(Hex.decodeHex(cs.toString()));
			return result == null ? null : new String(result, getCharset());
		}catch(IllegalBlockSizeException e){
			logger.error(e.getMessage());
		}catch(BadPaddingException e){
			logger.error(e.getMessage());
		}catch(InvalidKeySpecException e){
			logger.error(e.getMessage());
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
		}catch(InvalidKeyException e){
			logger.error(e.getMessage());
		}catch(DecoderException e){
			logger.error(e.getMessage());
		}

		return null;
	}

	private final SecretKeyFactory getSecretKeyFactory() throws NoSuchAlgorithmException{
		Provider provider = getProvider();
		return provider == null ? SecretKeyFactory.getInstance(Algo.DES.getName()) :
				SecretKeyFactory.getInstance(Algo.DES.getName(), provider);
	}

}