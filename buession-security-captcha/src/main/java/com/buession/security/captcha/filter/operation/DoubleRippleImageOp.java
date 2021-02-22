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
public class DoubleRippleImageOp extends RippleImageOp {

	@Override
	protected void transform(int x, int y, double[] t){
		double tx = Math.sin((double) y / yWavelength + yRandom) + 1.3 * Math.sin(0.6 * y / yWavelength + yRandom);
		double ty = Math.cos((double) x / xWavelength + xRandom) + 1.3 * Math.cos(0.6 * x / xWavelength + xRandom);

		t[0] = x + xAmplitude * tx;
		t[1] = y + yAmplitude * ty;
	}

}
