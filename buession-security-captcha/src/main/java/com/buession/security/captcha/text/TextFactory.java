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
import com.buession.security.captcha.font.FontFactory;

import java.awt.image.BufferedImage;

/**
 * 文本工厂接口
 *
 * @author yong.teng
 * @since 1.2.0
 */
public interface TextFactory {

	/**
	 * 返回上边距
	 *
	 * @return 上边距
	 */
	int getTopMargin();

	/**
	 * 设置上边距
	 *
	 * @param topMargin
	 * 		上边距
	 */
	void setTopMargin(int topMargin);

	/**
	 * 返回下边距
	 *
	 * @return 下边距
	 */
	int getBottomMargin();

	/**
	 * 设置下边距
	 *
	 * @param bottomMargin
	 * 		下边距
	 */
	void setBottomMargin(int bottomMargin);

	/**
	 * 返回左边距
	 *
	 * @return 左边距
	 */
	int getLeftMargin();

	/**
	 * 设置左边距
	 *
	 * @param leftMargin
	 * 		左边距
	 */
	void setLeftMargin(int leftMargin);

	/**
	 * 返回右边距
	 *
	 * @return 右边距
	 */
	int getRightMargin();

	/**
	 * 设置右边距
	 *
	 * @param rightMargin
	 * 		右边距
	 */
	void setRightMargin(int rightMargin);

	/**
	 * 返回字体工厂
	 *
	 * @return 字体工厂
	 */
	FontFactory getFontFactory();

	/**
	 * 设置字体工厂
	 *
	 * @param fontFactory
	 * 		字体工厂
	 */
	void setFontFactory(FontFactory fontFactory);

	/**
	 * 返回颜色工厂
	 *
	 * @return 颜色工厂
	 */
	ColorFactory getColorFactory();

	/**
	 * 设置颜色工厂
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 */
	void setColorFactory(ColorFactory colorFactory);

	/**
	 * 绘制字符
	 *
	 * @param bufferedImage
	 * 		图像
	 * @param text
	 * 		文本
	 */
	void draw(BufferedImage bufferedImage, String text);

}
