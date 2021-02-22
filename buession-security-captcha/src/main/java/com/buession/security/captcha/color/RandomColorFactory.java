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
package com.buession.security.captcha.color;

import com.buession.core.utils.Assert;
import com.buession.core.utils.RandomUtils;

import java.awt.*;

/**
 * 随机颜色工厂类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class RandomColorFactory extends AbstractColorFactory {

	/**
	 * 默认最小颜色值
	 */
	public final static int DEFAULT_MIN = 0;

	/**
	 * 默认最大颜色值
	 */
	public final static int DEFAULT_MAX = 255;

	/**
	 * 最小颜色值
	 */
	protected int min = DEFAULT_MIN;

	/**
	 * 最大颜色值
	 */
	protected int max = DEFAULT_MAX;

	/**
	 * 构造函数
	 */
	public RandomColorFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param min
	 * 		最小颜色值
	 * @param max
	 * 		最大颜色值
	 */
	public RandomColorFactory(int min, int max){
		super();

		setMin(min);
		setMax(max);
	}

	/**
	 * 返回最小颜色值
	 *
	 * @return 最小颜色值
	 */
	public int getMin(){
		return min;
	}

	/**
	 * 设置最小颜色值
	 *
	 * @param min
	 * 		最小颜色值
	 */
	public void setMin(int min){
		Assert.isTrue(min < 0 || min > 255, "The color value are between 0 and 255.");
		this.min = min;
	}

	/**
	 * 返回最大颜色值
	 *
	 * @return 最大颜色值
	 */
	public int getMax(){
		return max;
	}

	/**
	 * 设置最大颜色值
	 *
	 * @param max
	 * 		最大颜色值
	 */
	public void setMax(int max){
		Assert.isTrue(max < 0 || max > 255, "The color value are between 0 and 255.");
		this.max = max;
	}

	@Override
	public Color getColor(){

		int r = RandomUtils.nextInt(min, max);
		int g = RandomUtils.nextInt(min, max);
		int b = RandomUtils.nextInt(min, max);

		return new Color(r, g, b);
	}

}
