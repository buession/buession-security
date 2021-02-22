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

import com.buession.core.utils.RandomUtils;
import com.buession.security.captcha.color.ColorFactory;
import com.buession.security.captcha.color.SingleColorFactory;
import com.buession.security.captcha.core.TextCharacter;
import com.buession.security.captcha.font.FontFactory;
import com.buession.security.captcha.font.SingleFontFactory;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * 文本工厂抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public abstract class AbstractTextFactory implements TextFactory {

	/**
	 * 默认边距
	 */
	public final static int DEFAULT_MARGIN = 5;

	/**
	 * 默认字体工厂
	 */
	public final static FontFactory DEFAULT_FONT_FACTORY = new SingleFontFactory();

	/**
	 * 默认颜色工厂
	 */
	public final static ColorFactory DEFAULT_COLOR_FACTORY = new SingleColorFactory();

	/**
	 * 上边距
	 */
	protected int topMargin = DEFAULT_MARGIN;

	/**
	 * 下边距
	 */
	protected int bottomMargin = DEFAULT_MARGIN;

	/**
	 * 左边距
	 */
	protected int leftMargin = DEFAULT_MARGIN;

	/**
	 * 右边距
	 */
	protected int rightMargin = DEFAULT_MARGIN;

	/**
	 * 字体工厂
	 */
	private FontFactory fontFactory = DEFAULT_FONT_FACTORY;

	/**
	 * 颜色工厂
	 */
	private ColorFactory colorFactory = DEFAULT_COLOR_FACTORY;

	/**
	 * 构造函数，边距为 5px
	 */
	public AbstractTextFactory(){
	}

	/**
	 * 构造函数
	 *
	 * @param margin
	 * 		边距
	 */
	public AbstractTextFactory(int margin){
		this(margin, margin, margin, margin);
	}

	/**
	 * 构造函数
	 *
	 * @param topBottomMargin
	 * 		上下边距
	 * @param leftRightMargin
	 * 		左右边距
	 */
	public AbstractTextFactory(int topBottomMargin, int leftRightMargin){
		this(topBottomMargin, topBottomMargin, leftRightMargin, leftRightMargin);
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
	public AbstractTextFactory(int topMargin, int bottomMargin, int leftMargin, int rightMargin){
		this.topMargin = topMargin;
		this.bottomMargin = bottomMargin;
		this.leftMargin = leftMargin;
		this.rightMargin = rightMargin;
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param fontFactory
	 * 		字体工厂
	 */
	public AbstractTextFactory(FontFactory fontFactory){
		this.fontFactory = fontFactory;
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 */
	public AbstractTextFactory(ColorFactory colorFactory){
		this.colorFactory = colorFactory;
	}

	/**
	 * 构造函数，边距为 5px
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param colorFactory
	 * 		颜色工厂
	 */
	public AbstractTextFactory(FontFactory fontFactory, ColorFactory colorFactory){
		this.fontFactory = fontFactory;
		this.colorFactory = colorFactory;
	}

	/**
	 * 构造函数
	 *
	 * @param fontFactory
	 * 		字体工厂
	 * @param margin
	 * 		边距
	 */
	public AbstractTextFactory(FontFactory fontFactory, int margin){
		this(margin);
		this.fontFactory = fontFactory;
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
	public AbstractTextFactory(FontFactory fontFactory, int topBottomMargin, int leftRightMargin){
		this(topBottomMargin, leftRightMargin);
		this.fontFactory = fontFactory;
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
	public AbstractTextFactory(FontFactory fontFactory, int topMargin, int bottomMargin, int leftMargin,
							   int rightMargin){
		this(topMargin, bottomMargin, leftMargin, rightMargin);
		this.fontFactory = fontFactory;
	}

	/**
	 * 构造函数
	 *
	 * @param colorFactory
	 * 		颜色工厂
	 * @param margin
	 * 		边距
	 */
	public AbstractTextFactory(ColorFactory colorFactory, int margin){
		this(margin);
		this.colorFactory = colorFactory;
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
	public AbstractTextFactory(ColorFactory colorFactory, int topBottomMargin, int leftRightMargin){
		this(topBottomMargin, leftRightMargin);
		this.colorFactory = colorFactory;
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
	public AbstractTextFactory(ColorFactory colorFactory, int topMargin, int bottomMargin, int leftMargin,
							   int rightMargin){
		this(topMargin, bottomMargin, leftMargin, rightMargin);
		this.colorFactory = colorFactory;
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
	public AbstractTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int margin){
		this(margin);
		this.fontFactory = fontFactory;
		this.colorFactory = colorFactory;
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
	public AbstractTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int topBottomMargin,
							   int leftRightMargin){
		this(topBottomMargin, leftRightMargin);
		this.fontFactory = fontFactory;
		this.colorFactory = colorFactory;
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
	public AbstractTextFactory(FontFactory fontFactory, ColorFactory colorFactory, int topMargin, int bottomMargin,
							   int leftMargin, int rightMargin){
		this(topMargin, bottomMargin, leftMargin, rightMargin);
		this.fontFactory = fontFactory;
		this.colorFactory = colorFactory;
	}

	@Override
	public int getTopMargin(){
		return topMargin;
	}

	@Override
	public void setTopMargin(int topMargin){
		this.topMargin = topMargin;
	}

	@Override
	public int getBottomMargin(){
		return bottomMargin;
	}

	@Override
	public void setBottomMargin(int bottomMargin){
		this.bottomMargin = bottomMargin;
	}

	@Override
	public int getLeftMargin(){
		return leftMargin;
	}

	@Override
	public void setLeftMargin(int leftMargin){
		this.leftMargin = leftMargin;
	}

	@Override
	public int getRightMargin(){
		return rightMargin;
	}

	@Override
	public void setRightMargin(int rightMargin){
		this.rightMargin = rightMargin;
	}

	@Override
	public FontFactory getFontFactory(){
		return fontFactory;
	}

	@Override
	public void setFontFactory(FontFactory fontFactory){
		this.fontFactory = fontFactory;
	}

	@Override
	public ColorFactory getColorFactory(){
		return colorFactory;
	}

	@Override
	public void setColorFactory(ColorFactory colorFactory){
		this.colorFactory = colorFactory;
	}

	@Override
	public void draw(BufferedImage bufferedImage, String text){
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

		TextString ts = convertToCharacters(text, g2d, getFontFactory(), getColorFactory());
		arrangeCharacters(ts, bufferedImage.getWidth(), bufferedImage.getHeight());

		setRenderingHint(g2d);

		for(TextCharacter tc : ts.getCharacters()){
			int degree = degree();
			double angdeg = Math.toRadians(degree);
			boolean rotate = tc.getRotateX() >= 0 && tc.getRotateY() >= 0;

			g2d.setColor(tc.getColor());

			if(rotate){
				g2d.rotate(angdeg, tc.getRotateX(), tc.getRotateY());
			}
			g2d.drawString(tc.iterator(), (float) tc.getX(), (float) tc.getY());
			if(rotate){
				g2d.rotate(-angdeg, tc.getX(), tc.getRotateY());
			}
		}
	}

	protected static void setRenderingHint(Graphics2D g2d){
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}

	protected static int degree(){
		int degree = RandomUtils.nextInt(0, 45);
		return RandomUtils.nextInt(2) == 0 ? -degree : degree;
	}

	protected abstract void arrangeCharacters(TextString ts, int width, int height);

	protected TextString convertToCharacters(String text, Graphics2D g2d, FontFactory fontFactory,
											 ColorFactory colorFactory){
		TextString characters = new TextString();
		FontRenderContext frc = g2d.getFontRenderContext();
		double lastX = 0;

		for(int i = 0; i < text.length(); i++){
			char c = text.charAt(i);
			Font font = fontFactory.getFont();
			Color color = colorFactory.getColor();
			FontMetrics fm = g2d.getFontMetrics(font);
			Rectangle2D bounds = font.getStringBounds(String.valueOf(c), frc);

			characters.addCharacter(createTextCharacter(c, font, color, fm, lastX, 0));
			lastX += bounds.getWidth();
		}

		return characters;
	}

	protected static TextCharacter createTextCharacter(char c, Font font, Color color, FontMetrics fm, double x,
													   double y){
		TextCharacter tc = new TextCharacter();

		tc.setCharacter(c);
		tc.setFont(font);
		tc.setWidth(fm.charWidth(c));
		tc.setHeight(fm.getAscent() + fm.getDescent());
		tc.setAscent(fm.getAscent());
		tc.setDescent(fm.getDescent());
		tc.setX(x);
		tc.setY(y);
		tc.setRotateX(0);
		tc.setRotateY(0);
		tc.setFont(font);
		tc.setColor(color);

		return tc;
	}

}
