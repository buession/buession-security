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

import com.buession.lang.Status;
import com.buession.security.captcha.session.Session;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码处理器
 *
 * @author yong.teng
 * @since 1.2.0
 */
public interface Handler {

	/**
	 * 返回 Session
	 *
	 * @return Session
	 */
	Session getSession();

	/**
	 * 设置 Session
	 *
	 * @param session
	 * 		Session
	 */
	void setSession(Session session);

	/**
	 * 验证验证码
	 *
	 * @param code
	 * 		待验证的值
	 *
	 * @return 成功返回 Status.SUCCESS；失败，则返回 Status.FAILURE
	 */
	Status validate(String code);

	/**
	 * 绘制验证码
	 *
	 * @param outputStream
	 * 		输出流
	 *
	 * @throws IOException
	 * 		IOException
	 */
	void draw(OutputStream outputStream) throws IOException;

}
