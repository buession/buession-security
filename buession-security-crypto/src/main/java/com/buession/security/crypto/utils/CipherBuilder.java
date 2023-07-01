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

import com.buession.core.utils.Assert;
import com.buession.security.crypto.Mode;
import com.buession.security.crypto.Padding;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class CipherBuilder {

	private final String algorithmName;

	private Mode mode;

	private Padding padding;

	private Provider provider;

	private CipherBuilder(final String algorithmName) {
		Assert.isBlank(algorithmName, "Algorithm name cloud not be blank, empty or null.");
		this.algorithmName = algorithmName;
	}

	public static CipherBuilder getInstance(final String algorithmName) {
		return new CipherBuilder(algorithmName);
	}

	public CipherBuilder mode(Mode mode) {
		this.mode = mode;
		return this;
	}

	public CipherBuilder padding(Padding padding) {
		this.padding = padding;
		return this;
	}

	public CipherBuilder provider(Provider provider) {
		this.provider = provider;
		return this;
	}

	public Cipher create() throws NoSuchPaddingException, NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder(algorithmName);

		if(mode != null){
			sb.append('/').append(mode.name());
		}
		if(padding != null){
			sb.append('/').append(padding);
		}

		if(provider == null){
			return Cipher.getInstance(sb.toString());
		}else{
			return Cipher.getInstance(sb.toString(), provider);
		}
	}

}
