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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.captcha.word;

import com.buession.core.utils.Assert;
import com.buession.core.utils.RandomUtils;
import com.buession.security.captcha.core.Challenge;
import com.buession.security.captcha.core.DictConstants;
import com.buession.security.captcha.core.WordType;

/**
 * 随机单词工厂抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class RandomWordFactory extends AbstractWordFactory {

	/**
	 * 随机字符串文本
	 */
	private char[] content = DictConstants.ALNUM;

	/**
	 * 构造函数
	 */
	public RandomWordFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param minLength
	 * 		最小字符长度
	 * @param maxLength
	 * 		最大字符长度
	 */
	public RandomWordFactory(int minLength, int maxLength){
		super(minLength, maxLength);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		随机字符串文本
	 */
	public RandomWordFactory(String content){
		this();

		Assert.isBlank(content, "Word content cloud not be null.");
		this.content = content.toCharArray();
	}

	/**
	 * 构造函数
	 *
	 * @param wordType
	 * 		随机字符串类型
	 */
	public RandomWordFactory(WordType wordType){
		this();

		Assert.isNull(wordType, "Word type cloud not be null.");

		switch(wordType){
			case DIGIT:
				this.content = DictConstants.DIGIT;
				break;
			case DIGIT_ZH_CN:
				this.content = DictConstants.DIGIT_ZH_CN;
				break;
			case DIGIT_ZH_HK:
				this.content = DictConstants.DIGIT_ZH_HK;
				break;
			case LOWER_ALPHA:
				this.content = DictConstants.LOWER_ALPHA;
				break;
			case UPPER_ALPHA:
				this.content = DictConstants.UPPER_ALPHA;
				break;
			case ALPHA:
				this.content = DictConstants.ALPHA;
				break;
			case LOWER_ALNUM:
				this.content = DictConstants.LOWER_ALNUM;
				break;
			case UPPER_ALNUM:
				this.content = DictConstants.UPPER_ALNUM;
				break;
			case ALNUM:
				this.content = DictConstants.ALNUM;
				break;
			case CHINESE:
				this.content = DictConstants.CHINESE;
				break;
			default:
				break;
		}
	}

	/**
	 * 构造函数
	 *
	 * @param minLength
	 * 		最小字符数
	 * @param maxLength
	 * 		最大字符数
	 * @param wordType
	 * 		随机字符串类型
	 */
	public RandomWordFactory(int minLength, int maxLength, WordType wordType){
		this(wordType);
		setMinMaxLength(minLength, maxLength);
	}

	/**
	 * 构造函数
	 *
	 * @param minLength
	 * 		最小字符数
	 * @param maxLength
	 * 		最大字符数
	 * @param content
	 * 		随机字符串文本
	 */
	public RandomWordFactory(int minLength, int maxLength, String content){
		this(minLength, maxLength);

		Assert.isBlank(content, "Word content cloud not be null.");
		this.content = content.toCharArray();
	}

	/**
	 * 返回随机字符串文本
	 *
	 * @return 随机字符串文本
	 */
	public char[] getContent(){
		return content;
	}

	/**
	 * 设置随机字符串文本
	 *
	 * @param content
	 * 		随机字符串文本
	 */
	public void setContent(char[] content){
		this.content = content;
	}

	@Override
	public Challenge getChallenge(){
		int length = RandomUtils.nextInt(minLength, maxLength);
		StringBuilder sb = new StringBuilder(length);

		for(int i = 0; i < length; i++){
			int j = RandomUtils.nextInt(content.length);
			sb.append(content[j]);
		}

		String result = sb.toString();

		return new Challenge(result, result);
	}

}
