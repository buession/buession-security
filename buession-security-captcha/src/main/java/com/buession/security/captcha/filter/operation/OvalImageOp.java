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
package com.buession.security.captcha.filter.operation;

import com.buession.core.utils.RandomUtils;
import com.buession.security.captcha.color.ColorFactory;
import com.buession.security.captcha.color.SingleColorFactory;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author yong.teng
 * @since 1.2.0
 */
public class OvalImageOp extends AbstractImageOp {

	private int ovalCount = 20;

	private float strokeMin;

	private float strokeMax;

	private ColorFactory colorFactory = new SingleColorFactory();

	public OvalImageOp(){
	}

	public int getOvalCount(){
		return ovalCount;
	}

	public void setOvalCount(int ovalCount){
		this.ovalCount = ovalCount;
	}

	public float getStrokeMin(){
		return strokeMin;
	}

	public void setStrokeMin(float strokeMin){
		this.strokeMin = strokeMin;
	}

	public float getStrokeMax(){
		return strokeMax;
	}

	public void setStrokeMax(float strokeMax){
		this.strokeMax = strokeMax;
	}

	public ColorFactory getColorFactory(){
		return colorFactory;
	}

	public void setColorFactory(ColorFactory colorFactory){
		this.colorFactory = colorFactory;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest){
		if(dest == null){
			dest = createCompatibleDestImage(src, null);
		}

		int width = dest.getWidth();
		int height = dest.getHeight();
		Graphics2D g2d = (Graphics2D) src.getGraphics();

		g2d.setStroke(createStroke());

		for(int i = 0; i < getOvalCount(); i++){
			g2d.setColor(colorFactory.getColor());
			g2d.drawOval(RandomUtils.nextInt(width), RandomUtils.nextInt(height), RandomUtils.nextInt(0, 10) + 10,
					RandomUtils.nextInt(0, 10) + 10);
		}

		return src;
	}

	private final BasicStroke createStroke(){
		if(getStrokeMin() > 0 && getStrokeMax() > 0){
			return new BasicStroke(RandomUtils.nextFloat(getStrokeMin(), getStrokeMax()));
		}else{
			return new BasicStroke(2 + 2 * RandomUtils.nextFloat(0, 1));
		}
	}

}
