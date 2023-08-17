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
package com.buession.security.mcrypt;

import com.buession.security.crypto.Crypto;

/**
 * 加解密
 *
 * @author Yong.Teng
 */
public interface Mcrypt extends Crypto {

	/**
	 * 返回请求算法的名称
	 *
	 * @return 返回请求算法的名称
	 */
	Algo getAlgo();

	/**
	 * 设置请求算法的名称
	 *
	 * @param algo
	 * 		请求算法的名称
	 */
	@Deprecated
	void setAlgo(final Algo algo);

	/**
	 * 对象加密
	 *
	 * @param object
	 * 		需要加密的字符串
	 *
	 * @return 加密后的字符串
	 */
	default String encode(final Object object) {
		return encrypt(object);
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
	default String decode(final CharSequence cs) {
		return decrypt(cs);
	}

}

