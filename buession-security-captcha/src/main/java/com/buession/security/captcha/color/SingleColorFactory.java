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
package com.buession.security.captcha.color;

import java.awt.*;

/**
 * 单一颜色工厂类
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class SingleColorFactory extends AbstractColorFactory {

	/**
	 * 默认颜色
	 */
	public final static Color DEFAULT_COLOR = Color.BLACK;

	/**
	 * 颜色
	 */
	private Color color = DEFAULT_COLOR;

	/**
	 * 构造函数，黑色
	 */
	public SingleColorFactory(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param color
	 * 		验证
	 */
	public SingleColorFactory(Color color){
		super();
		this.color = color;
	}

	@Override
	public Color getColor(){
		return color;
	}

	/**
	 * 设置颜色
	 *
	 * @param color
	 * 		颜色
	 */
	public void setColor(Color color){
		this.color = color;
	}

}
