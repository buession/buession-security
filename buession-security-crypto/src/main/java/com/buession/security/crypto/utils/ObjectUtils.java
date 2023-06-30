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
import com.buession.core.utils.CharacterUtils;

import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ObjectUtils {

	private ObjectUtils() {

	}

	public static String toString(final Object object) {
		Assert.isNull(object, "Mcrypt encrypt object could not be null");

		if(object instanceof char[]){
			return new String((char[]) object);
		}else if(object instanceof byte[]){
			return new String((byte[]) object);
		}else{
			return object.toString();
		}
	}

	public static byte[] toBytes(final Object object, final Charset charset) {
		Assert.isNull(object, "Mcrypt decrypt object could not be null");

		if(object instanceof char[]){
			return CharacterUtils.toBytes((char[]) object);
		}else if(object instanceof byte[]){
			return (byte[]) object;
		}else if(object instanceof String){
			return charset == null ? ((String) object).getBytes() : ((String) object).getBytes(charset);
		}else{
			String str = object.toString();
			return charset == null ? str.getBytes() : str.getBytes(charset);
		}
	}

}
