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

import java.util.ArrayList;

/**
 * 简单文本工厂抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class SimpleTextFactory extends AbstractTextFactory {

	/**
	 * 构造函数，边距为 5px
	 */
	public SimpleTextFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param margin
	 * 		边距
	 */
	public SimpleTextFactory(int margin){
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
	public SimpleTextFactory(int topBottomMargin, int leftRightMargin){
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
	public SimpleTextFactory(int topMargin, int bottomMargin, int leftMargin, int rightMargin){
		super(topMargin, bottomMargin, leftMargin, rightMargin);
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param fontFactory
	 * 		字体工厂
	 */
	public SimpleTextFactory(FontFactory fontFactory){
		super(fontFactory);
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 */
	public SimpleTextFactory(ColorFactory colorFactory){
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
	public SimpleTextFactory(FontFactory fontFactory, ColorFactory colorFactory){
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
	public SimpleTextFactory(FontFactory fontFactory, int margin){
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
	public SimpleTextFactory(FontFactory fontFactory, int topBottomMargin, int leftRightMargin){
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
	public SimpleTextFactory(FontFactory fontFactory, int topMargin, int bottomMargin, int leftMargin,
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
	public SimpleTextFactory(ColorFactory colorFactory, int margin){
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
	public SimpleTextFactory(ColorFactory colorFactory, int topBottomMargin, int leftRightMargin){
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
	public SimpleTextFactory(ColorFactory colorFactory, int topMargin, int bottomMargin, int leftMargin,
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
	public SimpleTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int margin){
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
	public SimpleTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int topBottomMargin,
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
	public SimpleTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int topMargin, int bottomMargin,
							 int leftMargin, int rightMargin){
		super(fontFactory, colorFactory, topMargin, bottomMargin, leftMargin, rightMargin);
	}

	@Override
	protected void arrangeCharacters(TextString ts, int width, int height){
		ArrayList<TextCharacter> characters = ts.getCharacters();
		int charactersSize = characters.size();
		int w = width / charactersSize;
		int size;
		double x;
		double y;
		double rotateX;
		double rotateY;
		int i = 0;

		for(TextCharacter tc : characters){
			size = w - tc.getFont().getSize() + 2;
			x = leftMargin + (width - (charactersSize - i) * w) + size;
			y = topMargin + (height + tc.getAscent() * 0.7) / 2;
			rotateX = (width - (charactersSize - i) * w) + w / 2;
			rotateY = height / 2 + 2;

			tc.setX(x);
			tc.setY(y);
			tc.setRotateX(rotateX);
			tc.setRotateY(rotateY);
			i++;
		}
	}

}
