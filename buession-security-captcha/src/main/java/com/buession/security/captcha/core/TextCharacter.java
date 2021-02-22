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

	private char character;

	private double width;

	private double height;

	private double x;

	private double y;

	private double rotateX;

	private double rotateY;

	private double ascent;

	private double descent;

	private Font font;

	private Color color;

	public char getCharacter(){
		return character;
	}

	public void setCharacter(char character){
		this.character = character;
	}

	public double getWidth(){
		return width;
	}

	public void setWidth(double width){
		this.width = width;
	}

	public double getHeight(){
		return height;
	}

	public void setHeight(double height){
		this.height = height;
	}

	public double getX(){
		return x;
	}

	public void setX(double x){
		this.x = x;
	}

	public double getY(){
		return y;
	}

	public void setY(double y){
		this.y = y;
	}

	public double getRotateX(){
		return rotateX;
	}

	public void setRotateX(double rotateX){
		this.rotateX = rotateX;
	}

	public double getRotateY(){
		return rotateY;
	}

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

	public Font getFont(){
		return font;
	}

	public void setFont(Font font){
		this.font = font;
	}

	public Color getColor(){
		return color;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public AttributedCharacterIterator iterator(){
		AttributedString aString = new AttributedString(String.valueOf(character));

		aString.addAttribute(TextAttribute.FONT, font, 0, 1);

		return aString.getIterator();
	}


}
