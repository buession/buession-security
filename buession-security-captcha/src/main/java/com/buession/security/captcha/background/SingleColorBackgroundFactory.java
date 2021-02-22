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
package com.buession.security.captcha.background;

import com.buession.core.utils.Assert;
import com.buession.security.captcha.color.ColorFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 单一颜色背景工厂类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class SingleColorBackgroundFactory extends AbstractBackgroundFactory {

	/**
	 * 默认背景色
	 */
	public final static Color DEFAULT_COLOR = Color.WHITE;

	/**
	 * 颜色工厂
	 */
	private Color color = DEFAULT_COLOR;

	/**
	 * 构造函数，生成白色背景
	 */
	public SingleColorBackgroundFactory(){
		super();
	}

	/**
	 * 构造函数，生成白色背景
	 *
	 * @param alpha
	 * 		透明度
	 */
	public SingleColorBackgroundFactory(float alpha){
		super(alpha);
	}

	/**
	 * 构造函数
	 *
	 * @param color
	 * 		颜色
	 */
	public SingleColorBackgroundFactory(Color color){
		super();
		this.color = color;
	}

	/**
	 * 构造函数，生成白色背景
	 *
	 * @param color
	 * 		颜色
	 * @param alpha
	 * 		透明度
	 */
	public SingleColorBackgroundFactory(Color color, float alpha){
		super(alpha);
		this.color = color;
	}

	/**
	 * 构造函数
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 */
	public SingleColorBackgroundFactory(ColorFactory colorFactory){
		super();

		Assert.isNull(colorFactory, "ColorFactory cloud not be null.");
		this.color = colorFactory.getColor();
	}

	/**
	 * 构造函数
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 * @param alpha
	 * 		透明度
	 */
	public SingleColorBackgroundFactory(ColorFactory colorFactory, float alpha){
		this(colorFactory);
		setAlpha(alpha);
	}

	/**
	 * 返回颜色
	 *
	 * @return 颜色
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * 设置颜色
	 *
	 * @param color
	 * 		颜色
	 */
	public void setColor(Color color){
		Assert.isNull(color, "Background color cloud not be null.");
		this.color = color;
	}

	@Override
	public void fill(BufferedImage bufferedImage){
		Graphics graphics = bufferedImage.getGraphics();
		fillBackground(bufferedImage, graphics, color);
	}

}
