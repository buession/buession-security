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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.mcrypt;

import com.buession.core.utils.Assert;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Hmac 加解密
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class AbstractHmacMcrypt extends AbstractMcrypt {

	/**
	 * 构造函数
	 */
	public AbstractHmacMcrypt(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 */
	public AbstractHmacMcrypt(final Algo algo){
		super(algo);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param characterEncoding
	 * 		字符编码
	 */
	public AbstractHmacMcrypt(final Algo algo, final String characterEncoding){
		super(algo, characterEncoding);
	}

	/**
	 * 构造函数
	 *
	 * @param algo
	 * 		请求算法的名称
	 * @param charset
	 * 		字符编码
	 */
	public AbstractHmacMcrypt(final Algo algo, final Charset charset){
		super(algo, charset);
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
	public AbstractHmacMcrypt(final Algo algo, final String characterEncoding, final String salt){
		super(algo, characterEncoding, salt);
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
	public AbstractHmacMcrypt(final Algo algo, final Charset charset, final String salt){
		super(algo, charset, salt);
	}

	@Override
	public String encode(final Object object){
		Assert.isNull(object, "Mcrypt encode object could not be null");
		Assert.isNull(getAlgo(), "Algo could not be null");

		try{
			SecretKeySpec secretKeySpec = new SecretKeySpec(getRealSalt().getBytes(getCharset()),
					getAlgo().getName());

			Mac mac = Mac.getInstance(getAlgo().getName());
			mac.init(secretKeySpec);

			return Hex.encodeHexString(mac.doFinal(object2Bytes(object)));
		}catch(NoSuchAlgorithmException e){
			logger.error(e.getMessage());
			throw new SecurityException(e);
		}catch(InvalidKeyException e){
			logger.error(e.getMessage());
			throw new SecurityException(e);
		}
	}

}
