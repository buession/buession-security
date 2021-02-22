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
public abstract class AbstractConvolveImageOp extends AbstractImageOp {

	private float[][] matrix;

	protected AbstractConvolveImageOp(float[][] matrix){
		this.matrix = matrix;
	}

	@Override
	protected void filter(int[] inPixels, int[] outPixels, int width, int height){
		int matrixWidth = matrix[0].length;
		int matrixHeight = matrix.length;
		int matrixTop = -matrixHeight / 2;
		int mattrixLeft = -matrixWidth / 2;

		for(int y = 0; y < height; y++){
			int ytop = y + matrixTop;
			int ybottom = y + matrixTop + matrixHeight;

			for(int x = 0; x < width; x++){
				float[] sum = {0.5f, 0.5f, 0.5f, 0.5f};
				int xleft = x + mattrixLeft;
				int xright = x + mattrixLeft + matrixWidth;
				int matrixY = 0;

				for(int my = ytop; my < ybottom; my++, matrixY++){
					int matrixX = 0;

					for(int mx = xleft; mx < xright; mx++, matrixX++){
						int pixel = getPixel(inPixels, width, height, mx, my, EDGE_ZERO);
						float m = matrix[matrixY][matrixX];

						sum[0] += m * ((pixel >> 24) & 0xff);
						sum[1] += m * ((pixel >> 16) & 0xff);
						sum[2] += m * ((pixel >> 8) & 0xff);
						sum[3] += m * (pixel & 0xff);
					}
				}
				outPixels[x + y * width] =
						(limitByte((int) sum[0]) << 24) | (limitByte((int) sum[1]) << 16) | (limitByte((int) sum[2]) << 8) | (limitByte((int) sum[3]));
			}
		}
	}

}
