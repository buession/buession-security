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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * @author Yong.Teng
 */
public class HexTest {

	public final static String byte2Hex(byte buffer[]){
		StringBuilder sb = new StringBuilder();

		for(byte b : buffer){
			String hex = Integer.toHexString(b & 0xFF);

			if(hex.length() == 1){
				sb.append('0');
			}

			sb.append(hex.toUpperCase());
		}

		return sb.toString();
	}

	public final static byte[] hex2Byte(String hexStr){
		int length = hexStr.length();

		if(length < 1){
			return null;
		}

		int size = length / 2;
		byte[] result = new byte[size];

		for(int i = 0; i < size; i++){
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);

			result[i] = (byte) (high * 16 + low);
		}

		return result;
	}

	@Test
	public void convert(){
		String str = "abc";

		System.out.println(byte2Hex(str.getBytes()));
		System.out.println(Hex.encodeHexString(str.getBytes()));

		System.out.println(new String(hex2Byte(byte2Hex(str.getBytes()))));
		try{
			System.out.println(new String(Hex.decodeHex(Hex.encodeHexString(str.getBytes()))));
		}catch(DecoderException e){
			e.printStackTrace();
		}
	}

}
