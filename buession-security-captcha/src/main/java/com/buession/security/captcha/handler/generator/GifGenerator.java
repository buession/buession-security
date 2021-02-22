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
import com.buession.security.captcha.core.Challenge;
import com.buession.security.captcha.core.ImageType;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * PNG 验证码生成器
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class GifGenerator extends AbstractGenerator {

	/**
	 * 构造函数
	 */
	public GifGenerator(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param width
	 * 		验证码宽度
	 * @param height
	 * 		验证码高度
	 */
	public GifGenerator(int width, int height){
		super(width, height);
	}

	@Override
	public ImageType getImageType(){
		return ImageType.GIF;
	}

	@Override
	public Captcha create(){
		BufferedImage image = createBufferedImage();
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		Challenge challenge = getWordFactory().getChallenge();

		getBackgroundFactory().fill(image);
		getFilterFactory().apply(image);
		getTextFactory().draw(image, challenge.getText());

		g2d.dispose();

		return new Captcha(challenge, image);
	}

}