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
package com.buession.security.captcha.background;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 背景工厂抽象类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public abstract class AbstractBackgroundFactory implements BackgroundFactory {

	/**
	 * 默认透明度
	 */
	public final static float DEFAULT_ALPHA = 1F;

	/**
	 * 透明度
	 */
	private float alpha = DEFAULT_ALPHA;

	/**
	 * 构造函数
	 */
	public AbstractBackgroundFactory(){
	}

	/**
	 * 构造函数
	 *
	 * @param alpha
	 * 		透明度
	 */
	public AbstractBackgroundFactory(float alpha){
		this.alpha = alpha;
	}

	@Override
	public float getAlpha(){
		return alpha;
	}

	@Override
	public void setAlpha(float alpha){
		this.alpha = alpha;
	}

	protected void fillBackground(final BufferedImage bufferedImage, final Graphics graphics, final Color color){
		graphics.setColor(color);
		graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

		if(getAlpha() >= 0F){
			((Graphics2D) graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getAlpha()));
		}
	}

}
