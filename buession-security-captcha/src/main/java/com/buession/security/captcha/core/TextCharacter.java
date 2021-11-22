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
package com.buession.security.captcha.core;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * 文本字符
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class TextCharacter {

	/**
	 * 字符
	 */
	private char character;

	/**
	 * 字符宽度
	 */
	private double width;

	/**
	 * 字符高度
	 */
	private double height;

	/**
	 * 横坐标
	 */
	private double x;

	/**
	 * 纵坐标
	 */
	private double y;

	/**
	 * 旋转横坐标
	 */
	private double rotateX;

	/**
	 * 旋转纵坐标
	 */
	private double rotateY;

	private double ascent;

	private double descent;

	/**
	 * 字体
	 */
	private Font font;

	/**
	 * 颜色
	 */
	private Color color;

	/**
	 * 返回字符
	 *
	 * @return 字符
	 */
	public char getCharacter(){
		return character;
	}

	/**
	 * 设置字符
	 *
	 * @param character
	 * 		字符
	 */
	public void setCharacter(char character){
		this.character = character;
	}

	/**
	 * 返回字符宽度
	 *
	 * @return 字符宽度
	 */
	public double getWidth(){
		return width;
	}

	/**
	 * 设置字符宽度
	 *
	 * @param width
	 * 		字符宽度
	 */
	public void setWidth(double width){
		this.width = width;
	}

	/**
	 * 返回字符高度
	 *
	 * @return 字符高度
	 */
	public double getHeight(){
		return height;
	}

	/**
	 * 设置字符高度
	 *
	 * @param height
	 * 		字符高度
	 */
	public void setHeight(double height){
		this.height = height;
	}

	/**
	 * 返回横坐标
	 *
	 * @return 横坐标
	 */
	public double getX(){
		return x;
	}

	/**
	 * 设置横坐标
	 *
	 * @param x
	 * 		横坐标
	 */
	public void setX(double x){
		this.x = x;
	}

	/**
	 * 返回纵坐标
	 *
	 * @return 纵坐标
	 */
	public double getY(){
		return y;
	}

	/**
	 * 设置纵坐标
	 *
	 * @param y
	 * 		纵坐标
	 */
	public void setY(double y){
		this.y = y;
	}

	/**
	 * 返回旋转横坐标
	 *
	 * @return 旋转横坐标
	 */
	public double getRotateX(){
		return rotateX;
	}

	/**
	 * 设置旋转横坐标
	 *
	 * @param rotateX
	 * 		旋转横坐标
	 */
	public void setRotateX(double rotateX){
		this.rotateX = rotateX;
	}

	/**
	 * 返回旋转纵坐标
	 *
	 * @return 旋转纵坐标
	 */
	public double getRotateY(){
		return rotateY;
	}

	/**
	 * 设置旋转纵坐标
	 *
	 * @param rotateY
	 * 		纵坐标
	 */
	public void setRotateY(double rotateY){
		this.rotateY = rotateY;
	}

	public double getAscent(){
		return ascent;
	}

	public void setAscent(double ascent){
		this.ascent = ascent;
	}

	public double getDescent(){
		return descent;
	}

	public void setDescent(double descent){
		this.descent = descent;
	}

	/**
	 * 返回字体
	 *
	 * @return 字体
	 */
	public Font getFont(){
		return font;
	}

	/**
	 * 设置字体
	 *
	 * @param font
	 * 		字体
	 */
	public void setFont(Font font){
		this.font = font;
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
		this.color = color;
	}

	public AttributedCharacterIterator iterator(){
		AttributedString aString = new AttributedString(String.valueOf(character));

		aString.addAttribute(TextAttribute.FONT, font, 0, 1);

		return aString.getIterator();
	}


}
