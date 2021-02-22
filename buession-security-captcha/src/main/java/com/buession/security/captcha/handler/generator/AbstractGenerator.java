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
package com.buession.security.captcha.handler.generator;

import com.buession.core.utils.Assert;
import com.buession.security.captcha.background.BackgroundFactory;
import com.buession.security.captcha.background.SingleColorBackgroundFactory;
import com.buession.security.captcha.filter.CurvesRippleFilterFactory;
import com.buession.security.captcha.filter.FilterFactory;
import com.buession.security.captcha.text.SimpleTextFactory;
import com.buession.security.captcha.text.TextFactory;
import com.buession.security.captcha.word.RandomWordFactory;
import com.buession.security.captcha.word.WordFactory;

import java.awt.image.BufferedImage;

/**
 * 验证码生成器抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public abstract class AbstractGenerator implements Generator {

	/**
	 * 默认验证码宽度
	 */
	public final static int DEFAULT_WIDTH = 240;

	/**
	 * 默认验证码高度
	 */
	public final static int DEFAULT_HEIGHT = 80;

	/**
	 * 默认背景工厂
	 */
	public final static BackgroundFactory DEFAULT_BACKGROUND_FACTORY = new SingleColorBackgroundFactory();

	/**
	 * 默认单词工厂
	 */
	public final static WordFactory DEFAULT_WORD_FACTORY = new RandomWordFactory();

	/**
	 * 默认文本工厂
	 */
	public final static TextFactory DEFAULT_TEXT_FACTORY = new SimpleTextFactory();

	/**
	 * 默认过滤器工厂
	 */
	public final static FilterFactory DEFAULT_FILTER_FACTORY = new CurvesRippleFilterFactory();

	/**
	 * 验证码宽度
	 */
	private int width = DEFAULT_WIDTH;

	/**
	 * 验证码高度
	 */
	private int height = DEFAULT_HEIGHT;

	/**
	 * 背景工厂
	 */
	private BackgroundFactory backgroundFactory = DEFAULT_BACKGROUND_FACTORY;

	/**
	 * 单词工厂
	 */
	private WordFactory wordFactory = DEFAULT_WORD_FACTORY;

	/**
	 * 文本工厂
	 */
	private TextFactory textFactory = DEFAULT_TEXT_FACTORY;

	/**
	 * 过滤器工厂
	 */
	private FilterFactory filterFactory = DEFAULT_FILTER_FACTORY;

	/**
	 * 构造函数
	 */
	public AbstractGenerator(){
	}

	/**
	 * 构造函数
	 *
	 * @param width
	 * 		验证码宽度
	 * @param height
	 * 		验证码高度
	 */
	public AbstractGenerator(int width, int height){
		setWidth(width);
		setHeight(height);
	}

	@Override
	public int getWidth(){
		return width;
	}

	@Override
	public void setWidth(int width){
		Assert.isZeroNegative(width, "The minimum captcha width value is 1px.");
		this.width = width;
	}

	@Override
	public int getHeight(){
		return height;
	}

	@Override
	public void setHeight(int height){
		Assert.isZeroNegative(height, "The minimum captcha height value is 1px.");
		this.height = height;
	}

	@Override
	public BackgroundFactory getBackgroundFactory(){
		return backgroundFactory;
	}

	@Override
	public void setBackgroundFactory(BackgroundFactory backgroundFactory){
		this.backgroundFactory = backgroundFactory;
	}

	@Override
	public WordFactory getWordFactory(){
		return wordFactory;
	}

	@Override
	public void setWordFactory(WordFactory wordFactory){
		this.wordFactory = wordFactory;
	}

	@Override
	public TextFactory getTextFactory(){
		return textFactory;
	}

	@Override
	public void setTextFactory(TextFactory textFactory){
		this.textFactory = textFactory;
	}

	@Override
	public FilterFactory getFilterFactory(){
		return filterFactory;
	}

	@Override
	public void setFilterFactory(FilterFactory filterFactory){
		this.filterFactory = filterFactory;
	}

	protected BufferedImage createBufferedImage(){
		// BufferedImage类是具有缓冲区的Image类，Image类是用于描述图像信息的类
		return new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	}

}
