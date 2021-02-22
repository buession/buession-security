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

import java.awt.*;

/**
 * 单一字体工厂类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class SingleFontFactory extends AbstractFontFactory {

	/**
	 * 默认字体
	 */
	public final static Font DEFAULT_FONT = new Font("Arial", Font.ITALIC | Font.BOLD, 56);

	/**
	 * 字体
	 */
	protected Font font = DEFAULT_FONT;

	/**
	 * 构造函数，默认生成 56px 大小，字体为 Arial，颜色为黑色的斜体和粗体的 Font
	 */
	public SingleFontFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param font
	 * 		Font
	 */
	public SingleFontFactory(Font font){
		super();
		this.font = font;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		字体名称
	 * @param size
	 * 		字体大小
	 * @param style
	 * 		字体样式
	 */
	public SingleFontFactory(String name, int size, int style){
		this(new Font(name, style, size));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		字体名称
	 * @param size
	 * 		字体大小
	 * @param bold
	 * 		是否加粗
	 * @param italic
	 * 		是否斜体
	 */
	public SingleFontFactory(String name, int size, boolean bold, boolean italic){
		super();

		int style = 0;
		if(bold){
			style |= Font.BOLD;
		}

		if(italic){
			style |= Font.ITALIC;
		}

		setFont(new Font(name, style, size));
	}

	@Override
	public Font getFont(){
		return font;
	}

	/**
	 * 设置字体
	 *
	 * @param font
	 */
	public void setFont(Font font){
		this.font = font;
	}

}
