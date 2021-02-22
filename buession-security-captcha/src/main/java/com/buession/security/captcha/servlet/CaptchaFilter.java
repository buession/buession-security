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
package com.buession.security.captcha.servlet;

import com.buession.security.captcha.handler.AbstractHandler;
import com.buession.security.captcha.handler.Handler;
import com.buession.security.captcha.session.HttpServletSession;
import com.buession.security.captcha.session.Session;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码 Filter
 *
 * @author yong.teng
 * @since 1.2.0
 */
public class CaptchaFilter extends OncePerRequestFilter {

	/**
	 * 验证码处理器
	 */
	private Handler handler;

	/**
	 * Session key
	 */
	private String sessionKey;

	private Session session;

	/**
	 * 构造函数
	 */
	public CaptchaFilter(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param handler
	 * 		验证码处理器
	 */
	public CaptchaFilter(Handler handler){
		super();
		this.handler = handler;
	}

	/**
	 * 构造函数
	 *
	 * @param sessionKey
	 * 		Session key
	 */
	public CaptchaFilter(String sessionKey){
		super();
		this.sessionKey = sessionKey;
	}

	/**
	 * 构造函数
	 *
	 * @param handler
	 * 		验证码处理器
	 * @param sessionKey
	 * 		Session key
	 */
	public CaptchaFilter(Handler handler, String sessionKey){
		super();
		this.handler = handler;
		this.sessionKey = sessionKey;
	}

	/**
	 * 返回验证码处理器
	 *
	 * @return 验证码处理器
	 */
	public Handler getHandler(){
		return handler;
	}

	/**
	 * 设置验证码处理器
	 *
	 * @param handler
	 * 		验证码处理器
	 */
	public void setHandler(Handler handler){
		this.handler = handler;
	}

	/**
	 * 返回 Session key
	 *
	 * @return Session key
	 */
	public String getSessionKey(){
		return sessionKey;
	}

	/**
	 * 设置 Session key
	 *
	 * @param sessionKey
	 * 		Session key
	 */
	public void setSessionKey(String sessionKey){
		this.sessionKey = sessionKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		AbstractHandler handler = (AbstractHandler) getHandler();

		if(session == null){
			session = new HttpServletSession(request.getSession(), sessionKey);
		}

		handler.setSession(session);

		response.setContentType("image/" + handler.getGenerator().getImageType().getValue());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		handler.draw(response.getOutputStream());
	}

}
