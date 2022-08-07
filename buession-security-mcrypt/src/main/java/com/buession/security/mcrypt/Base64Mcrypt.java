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
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * base64 编码、解码
 *
 * @author Yong.Teng
 */
public final class Base64Mcrypt extends AbstractMcrypt {

	/**
	 * 构造函数
	 */
	public Base64Mcrypt(){
		super(Algo.BASE64);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 */
	@Deprecated
	public Base64Mcrypt(final String characterEncoding){
		super(Algo.BASE64, characterEncoding);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 */
	public Base64Mcrypt(final Charset charset){
		super(Algo.BASE64, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param characterEncoding
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public Base64Mcrypt(final String characterEncoding, final String salt){
		super(Algo.BASE64, characterEncoding, salt);
	}

	/**
	 * 构造函数
	 *
	 * @param charset
	 * 		字符编码
	 * @param salt
	 * 		加密密钥
	 */
	public Base64Mcrypt(final Charset charset, final String salt){
		super(Algo.BASE64, charset, salt);
	}

	@Override
	public String encode(final Object object){
		Assert.isNull(object, "Mcrypt encode object could not be null.");
		return Base64.encodeBase64String((object2String(object) + getRealSalt()).getBytes(getCharset()));
	}

	@Override
	public String decode(final CharSequence cs){
		Assert.isNull(cs, "Mcrypt decode object could not be null.");
		return new String(Base64.decodeBase64(cs.toString()), getCharset());
	}

}