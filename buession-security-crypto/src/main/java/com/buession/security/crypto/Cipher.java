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
package com.buession.security.crypto;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class Cipher {

	private final String algorithmName;

	private final Mode mode;

	private final Padding padding;

	private final Provider provider;

	public Cipher(final String algorithmName, final Mode mode, final Padding padding) {
		this(algorithmName, mode, padding, null);
	}

	public Cipher(final String algorithmName, final Mode mode, final Padding padding, final Provider provider) {
		this.algorithmName = algorithmName;
		this.mode = mode;
		this.padding = padding;
		this.provider = provider;
	}

	public javax.crypto.Cipher create() throws NoSuchPaddingException, NoSuchAlgorithmException {
		if(provider == null){
			return javax.crypto.Cipher.getInstance(algorithmName + "/" + mode.name() + "/" + padding.toString());
		}else{
			return javax.crypto.Cipher.getInstance(algorithmName + "/" + mode.name() + "/" + padding.toString(),
					provider);
		}
	}

}
