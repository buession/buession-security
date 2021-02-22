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

/**
 * @author yong.teng
 * @since 1.2.0
 */
public class MarbleImageOp extends AbstractTransformImageOp {

	double scale;

	double amount;

	double turbulence;

	double[] tx;

	double[] ty;

	double randomX;

	double randomY;

	public MarbleImageOp(){
		scale = 15;
		amount = 1.1;
		turbulence = 6.2;
		randomX = 256 * Math.random();
		randomY = 256 * Math.random();
	}

	public double getScale(){
		return scale;
	}

	public void setScale(double scale){
		this.scale = scale;
	}

	public double getAmount(){
		return amount;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getTurbulence(){
		return turbulence;
	}

	public void setTurbulence(double turbulence){
		this.turbulence = turbulence;
	}

	@Override
	protected void init(){
		tx = new double[256];
		ty = new double[256];

		for(int i = 0; i < 256; i++){
			double angle = 2 * Math.PI * i * turbulence / 256;

			tx[i] = amount * Math.sin(angle);
			ty[i] = amount * Math.cos(angle);
		}
	}

	@Override
	protected void transform(int x, int y, double[] t){
		int d = limitByte((int) (127 * (1 + PerlinNoise.noise2D(((double) x) / scale + randomX,
				((double) y) / scale + randomY))));

		t[0] = x + tx[d];
		t[1] = y + ty[d];
	}

}
