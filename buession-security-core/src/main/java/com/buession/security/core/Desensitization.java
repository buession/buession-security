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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.core;

import com.buession.core.validator.Validate;

/**
 * 数据脱敏
 *
 * @author Yong.Teng
 */
public class Desensitization {

	/**
	 * 多字符串进行脱敏处理
	 *
	 * @param str
	 * 		字符串
	 * @param length
	 * 		隐藏字符串长度
	 *
	 * @return 脱敏后的字符串
	 */
	public static String encode(final String str, final int length){
		if(Validate.isEmpty(str)){
			return str;
		}else{
			int start = (str.length() - length) / 2;
			int end = start + length;
			char[] result = new char[str.length()];

			for(int i = 0, l = str.length(); i < l; i++){
				result[i] = i >= start && i < end ? '*' : str.charAt(i);
			}

			return new String(result);
		}
	}

}
