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
package com.buession.security.captcha.font;

import com.buession.core.utils.Assert;
import com.buession.core.utils.RandomUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单字体工厂类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class RandomFontFactory extends AbstractFontFactory {

	/**
	 * 默认字体
	 */
	public final static List<String> DEFAULT_FAMILIES = new ArrayList<>(3);

	/**
	 * 默认最小字号
	 */
	public final static int DEFAULT_MIN_SIZE = 45;

	/**
	 * 默认最大字号
	 */
	public final static int DEFAULT_MAX_SIZE = 50;

	static{
		DEFAULT_FAMILIES.add("Arial");
		DEFAULT_FAMILIES.add("Verdana");
		DEFAULT_FAMILIES.add("Tahoma");
	}

	/**
	 * 字体清单
	 */
	protected List<String> families = DEFAULT_FAMILIES;

	/**
	 * 最小字号
	 */
	protected int minSize = DEFAULT_MIN_SIZE;

	/**
	 * 最大字号
	 */
	protected int maxSize = DEFAULT_MAX_SIZE;

	/**
	 * 样式
	 */
	protected int style;

	/**
	 * 构造函数
	 */
	public RandomFontFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param families
	 * 		字体清单
	 */
	public RandomFontFactory(List<String> families){
		super();

		Assert.isEmpty(families, "Font families cloud not be empty.");
		this.families = families;
	}

	/**
	 * 构造函数
	 *
	 * @param families
	 * 		字体清单
	 * @param minSize
	 * 		最小字号
	 * @param maxSize
	 * 		最大字号
	 */
	public RandomFontFactory(List<String> families, int minSize, int maxSize){
		super();

		Assert.isEmpty(families, "Font families cloud not be empty.");
		Assert.isTrue(minSize < 1, "The minimum value of font size is 1.");
		Assert.isTrue(maxSize < 1, "The minimum value of font size is 1.");

		this.families = families;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	/**
	 * 构造函数
	 *
	 * @param families
	 * 		字体清单
	 * @param style
	 * 		样式
	 */
	public RandomFontFactory(List<String> families, int style){
		this(families);
		this.style = style;
	}

	/**
	 * 构造函数
	 *
	 * @param families
	 * 		字体清单
	 * @param minSize
	 * 		最大字号
	 * @param maxSize
	 * 		最小字号
	 * @param style
	 * 		样式
	 */
	public RandomFontFactory(List<String> families, int minSize, int maxSize, int style){
		this(families, minSize, maxSize);
		this.style = style;
	}

	/**
	 * 返回字体列表
	 *
	 * @return 字体列表
	 */
	public List<String> getFamilies(){
		return families;
	}

	/**
	 * 设置字体列表
	 *
	 * @return 字体列表
	 */
	public void setFamilies(List<String> families){
		this.families = families;
	}

	/**
	 * 返回最小字号
	 *
	 * @return 最小字号
	 */
	public int getMinSize(){
		return minSize;
	}

	/**
	 * 设置最小字号
	 *
	 * @param minSize
	 * 		最小字号
	 */
	public void setMinSize(int minSize){
		this.minSize = minSize;
	}

	/**
	 * 返回最大字号
	 *
	 * @return 最大字号
	 */
	public int getMaxSize(){
		return maxSize;
	}

	/**
	 * 设置最大字号
	 *
	 * @param maxSize
	 * 		最大字号
	 */
	public void setMaxSize(int maxSize){
		this.maxSize = maxSize;
	}

	/**
	 * 返回样式
	 *
	 * @return 样式
	 */
	public int getStyle(){
		return style;
	}

	/**
	 * 设置样式
	 *
	 * @param style
	 * 		样式
	 */
	public void setStyle(int style){
		this.style = style;
	}

	@Override
	public Font getFont(){
		String fontFamily = families.get(families.size() == 1 ? 0 : RandomUtils.nextInt(families.size()));
		return new Font(fontFamily, style, RandomUtils.nextInt(minSize, maxSize));
	}

}
