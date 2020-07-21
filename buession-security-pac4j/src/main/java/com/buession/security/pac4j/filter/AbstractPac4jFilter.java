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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.filter;

import com.buession.security.pac4j.context.ShiroSessionStore;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.http.adapter.HttpActionAdapter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractPac4jFilter implements Filter {

	private Config config;

	private SessionStore<JEEContext> sessionStore = null;

	private HttpActionAdapter<Object, JEEContext> httpActionAdapter;

	public AbstractPac4jFilter(){
	}

	public AbstractPac4jFilter(Config config){
		this.config = config;
	}

	public Config getConfig(){
		return config;
	}

	public void setConfig(Config config){
		this.config = config;
	}

	public HttpActionAdapter<Object, JEEContext> getHttpActionAdapter(){
		return httpActionAdapter;
	}

	public void setHttpActionAdapter(HttpActionAdapter<Object, JEEContext> httpActionAdapter){
		this.httpActionAdapter = httpActionAdapter;
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException{
	}

	@Override
	public void destroy(){
	}

	@SuppressWarnings({"unchecked"})
	protected SessionStore<JEEContext> getSessionStore(){
		if(sessionStore == null){
			sessionStore = getConfig().getSessionStore();
			if(sessionStore == null){
				sessionStore = new ShiroSessionStore();
			}
		}

		return sessionStore;
	}

}
