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
package com.buession.security.captcha.handler;

import com.buession.security.captcha.core.Captcha;
import com.buession.security.captcha.handler.generator.PngGenerator;
import com.buession.security.captcha.handler.generator.Generator;
import com.buession.security.captcha.session.Session;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 默认验证码处理器
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class DefaultHandler extends AbstractHandler {

	/**
	 * 构造函数，使用默认验证码生成器 {@link PngGenerator}
	 */
	public DefaultHandler(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param session
	 * 		Session
	 */
	public DefaultHandler(Session session){
		super(session);
	}

	/**
	 * 构造函数
	 *
	 * @param generator
	 * 		验证码生成器
	 */
	public DefaultHandler(Generator generator){
		super(generator);
	}

	/**
	 * 构造函数
	 *
	 * @param session
	 * 		Session
	 * @param generator
	 * 		验证码生成器
	 */
	public DefaultHandler(Session session, Generator generator){
		super(session, generator);
	}

	@Override
	public void draw(OutputStream outputStream) throws IOException{
		Captcha captcha = generator.create();

		getSession().create(captcha.getChallenge().getValue());

		ImageIO.write(captcha.getImage(), generator.getImageType().getValue(), outputStream);
		outputStream.flush();
	}

}
