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

import com.buession.security.captcha.core.Captcha;
import com.buession.security.captcha.core.ImageType;
import com.buession.security.captcha.background.BackgroundFactory;
import com.buession.security.captcha.filter.FilterFactory;
import com.buession.security.captcha.text.TextFactory;
import com.buession.security.captcha.word.WordFactory;

/**
 * 验证码生成器
 *
 * @author yong.teng
 * @since 1.2.0
 */
public interface Generator {

	/**
	 * 返回验证码宽度
	 *
	 * @return 验证码宽度
	 */
	int getWidth();

	/**
	 * 设置验证码宽度
	 *
	 * @param width
	 * 		验证码宽度
	 */
	void setWidth(int width);

	/**
	 * 返回验证码高度
	 *
	 * @return 验证码高度
	 */
	int getHeight();

	/**
	 * 设置验证码高度
	 *
	 * @param height
	 * 		验证码高度
	 */
	void setHeight(int height);

	/**
	 * 返回背景工厂
	 *
	 * @return 背景工厂
	 */
	BackgroundFactory getBackgroundFactory();

	/**
	 * 设置背景工厂
	 *
	 * @param backgroundFactory
	 * 		背景工厂
	 */
	void setBackgroundFactory(BackgroundFactory backgroundFactory);


	/**
	 * 返回单词工厂
	 *
	 * @return 单词工厂
	 */
	WordFactory getWordFactory();

	/**
	 * 设置单词工厂
	 *
	 * @param wordFactory
	 * 		单词工厂
	 */
	void setWordFactory(WordFactory wordFactory);

	/**
	 * 返回文本工厂
	 *
	 * @return 文本工厂
	 */
	TextFactory getTextFactory();

	/**
	 * 设置文本工厂
	 *
	 * @param textFactory
	 * 		文本工厂
	 */
	void setTextFactory(TextFactory textFactory);

	/**
	 * 返回过滤器工厂
	 *
	 * @return 过滤器工厂
	 */
	FilterFactory getFilterFactory();

	/**
	 * 设置过滤器工厂
	 *
	 * @param filterFactory
	 * 		过滤器工厂
	 */
	void setFilterFactory(FilterFactory filterFactory);

	/**
	 * 返回图片类型
	 *
	 * @return 图片类型
	 */
	ImageType getImageType();

	/**
	 * 创建验证码
	 *
	 * @return 验证码对象
	 */
	Captcha create();

}
