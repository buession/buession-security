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
package com.buession.security.captcha.text;

import com.buession.security.captcha.color.ColorFactory;
import com.buession.security.captcha.core.TextCharacter;
import com.buession.security.captcha.font.FontFactory;

/**
 * 工厂抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class BestFitTextFactory extends AbstractTextFactory {

	/**
	 * 构造函数，边距为 5px
	 */
	public BestFitTextFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param margin
	 * 		边距
	 */
	public BestFitTextFactory(int margin){
		super(margin);
	}

	/**
	 * 构造函数
	 *
	 * @param topBottomMargin
	 * 		上下边距
	 * @param leftRightMargin
	 * 		左右边距
	 */
	public BestFitTextFactory(int topBottomMargin, int leftRightMargin){
		super(topBottomMargin, leftRightMargin);
	}

	/**
	 * 构造函数
	 *
	 * @param topMargin
	 * 		上边距
	 * @param bottomMargin
	 * 		下边距
	 * @param leftMargin
	 * 		左边距
	 * @param rightMargin
	 * 		右边距
	 */
	public BestFitTextFactory(int topMargin, int bottomMargin, int leftMargin, int rightMargin){
		super(topMargin, bottomMargin, leftMargin, rightMargin);
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param fontFactory
	 * 		字体工厂
	 */
	public BestFitTextFactory(FontFactory fontFactory){
		super(fontFactory);
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 */
	public BestFitTextFactory(ColorFactory colorFactory){
		super(colorFactory);
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param colorFactory
	 * 		颜色工厂
	 */
	public BestFitTextFactory(FontFactory fontFactory, ColorFactory colorFactory){
		super(fontFactory, colorFactory);
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param margin
	 * 		边距
	 */
	public BestFitTextFactory(FontFactory fontFactory, int margin){
		super(fontFactory, margin);
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param topBottomMargin
	 * 		上下边距
	 * @param leftRightMargin
	 * 		左右边距
	 */
	public BestFitTextFactory(FontFactory fontFactory, int topBottomMargin, int leftRightMargin){
		super(fontFactory, topBottomMargin, leftRightMargin);
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param topMargin
	 * 		上边距
	 * @param bottomMargin
	 * 		下边距
	 * @param leftMargin
	 * 		左边距
	 * @param rightMargin
	 * 		右边距
	 */
	public BestFitTextFactory(FontFactory fontFactory, int topMargin, int bottomMargin, int leftMargin,
							  int rightMargin){
		super(fontFactory, topMargin, bottomMargin, leftMargin, rightMargin);
	}

	/**
	 * 构造函数
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 * @param margin
	 * 		边距
	 */
	public BestFitTextFactory(ColorFactory colorFactory, int margin){
		super(colorFactory, margin);
	}

	/**
	 * 构造函数
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 * @param topBottomMargin
	 * 		上下边距
	 * @param leftRightMargin
	 * 		左右边距
	 */
	public BestFitTextFactory(ColorFactory colorFactory, int topBottomMargin, int leftRightMargin){
		super(colorFactory, topBottomMargin, leftRightMargin);
	}

	/**
	 * 构造函数
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 * @param topMargin
	 * 		上边距
	 * @param bottomMargin
	 * 		下边距
	 * @param leftMargin
	 * 		左边距
	 * @param rightMargin
	 * 		右边距
	 */
	public BestFitTextFactory(ColorFactory colorFactory, int topMargin, int bottomMargin, int leftMargin,
							  int rightMargin){
		super(colorFactory, topMargin, bottomMargin, leftMargin, rightMargin);
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param colorFactory
	 * 		颜色工厂
	 * @param margin
	 * 		边距
	 */
	public BestFitTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int margin){
		super(fontFactory, colorFactory, margin);
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param colorFactory
	 * 		颜色工厂
	 * @param topBottomMargin
	 * 		上下边距
	 * @param leftRightMargin
	 * 		左右边距
	 */
	public BestFitTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int topBottomMargin,
							  int leftRightMargin){
		super(fontFactory, colorFactory, topBottomMargin, leftRightMargin);
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param colorFactory
	 * 		颜色工厂
	 * @param topMargin
	 * 		上边距
	 * @param bottomMargin
	 * 		下边距
	 * @param leftMargin
	 * 		左边距
	 * @param rightMargin
	 * 		右边距
	 */
	public BestFitTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int topMargin, int bottomMargin,
							  int leftMargin, int rightMargin){
		super(fontFactory, colorFactory, topMargin, bottomMargin, leftMargin, rightMargin);
	}

	@Override
	protected void arrangeCharacters(TextString ts, int width, int height){
		double widthRemaining = (width - ts.getWidth() - leftMargin - rightMargin) / ts.getCharacters().size();
		double x = leftMargin + widthRemaining / 2;

		height -= topMargin + bottomMargin;

		for(TextCharacter tc : ts.getCharacters()){
			double y = topMargin + (height + tc.getAscent() * 0.7) / 2;

			tc.setX(x);
			tc.setY(y);
			tc.setRotateX(-1);
			tc.setRotateY(-1);
			x += tc.getWidth() + widthRemaining;
		}
	}

}
