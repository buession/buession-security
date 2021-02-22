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
public class CurvesImageOp extends AbstractImageOp {

	private float strokeMin;

	private float strokeMax;

	private ColorFactory colorFactory = new SingleColorFactory();

	public CurvesImageOp(){
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

		double width = dest.getWidth();
		double height = dest.getHeight();
		Graphics2D g2d = (Graphics2D) src.getGraphics();

		g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		int cp = 4 + RandomUtils.nextInt(0, 3);
		int[] xPoints = new int[cp];
		int[] yPoints = new int[cp];

		width -= 10;

		for(int i = 0; i < cp; i++){
			xPoints[i] = (int) (5 + (i * width) / (cp - 1));
			yPoints[i] = (int) (height * (Math.random() * 0.5 + 0.2));
		}

		int subsections = 6;
		int[] xPointsSpline = new int[(cp - 1) * subsections];
		int[] yPointsSpline = new int[(cp - 1) * subsections];

		for(int i = 0, l = cp - 1; i < l; i++){
			double x0 = i > 0 ? xPoints[i - 1] : 2 * xPoints[i] - xPoints[i + 1];
			double x1 = xPoints[i];
			double x2 = xPoints[i + 1];
			double x3 = (i + 2 < cp) ? xPoints[i + 2] : 2 * xPoints[i + 1] - xPoints[i];
			double y0 = i > 0 ? yPoints[i - 1] : 2 * yPoints[i] - yPoints[i + 1];
			double y1 = yPoints[i];
			double y2 = yPoints[i + 1];
			double y3 = (i + 2 < cp) ? yPoints[i + 2] : 2 * yPoints[i + 1] - yPoints[i];

			for(int j = 0; j < subsections; j++){
				xPointsSpline[i * subsections + j] = (int) catmullRomSpline(x0, x1, x2, x3, 1.0 / subsections * j);
				yPointsSpline[i * subsections + j] = (int) catmullRomSpline(y0, y1, y2, y3, 1.0 / subsections * j);
			}
		}
		
		g2d.setStroke(createStroke());

		for(int i = 0, l = xPointsSpline.length - 1; i < l; i++){
			g2d.setColor(colorFactory.getColor());
			g2d.drawLine(xPointsSpline[i], yPointsSpline[i], xPointsSpline[i + 1], yPointsSpline[i + 1]);
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

	private final static double hermiteSpline(double x1, double a1, double x2, double a2, double t){
		double t2 = t * t;
		double t3 = t2 * t;
		double b = -a2 - 2.0 * a1 - 3.0 * x1 + 3.0 * x2;
		double a = a1 + a2 + 2.0 * x1 - 2.0 * x2;
		return a * t3 + b * t2 + a1 * t + x1;
	}

	private final static double catmullRomSpline(double x0, double x1, double x2, double x3, double t){
		double a1 = (x2 - x0) / 2;
		double a2 = (x3 - x1) / 2;
		return hermiteSpline(x1, a1, x2, a2, t);
	}

}
