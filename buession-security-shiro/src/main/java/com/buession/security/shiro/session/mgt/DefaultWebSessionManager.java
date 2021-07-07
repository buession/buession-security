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
package com.buession.security.shiro.session.mgt;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 默认 Web Session 管理器，替代 shiro 的默认 Web Session 管理器，
 * 以优化在每个 request 周期内，不用每次取 sessionDAO 中读取，而从 request attribute 中返回
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public class DefaultWebSessionManager extends org.apache.shiro.web.session.mgt.DefaultWebSessionManager {

	private final static Logger logger = LoggerFactory.getLogger(DefaultWebSessionManager.class);

	/**
	 * 构造函数
	 */
	public DefaultWebSessionManager(){
		super();
	}

	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException{
		Serializable sessionId = getSessionId(sessionKey);
		if(sessionId == null){
			logger.debug("Unable to resolve session ID from SessionKey [{}]. Returning null to indicate a session " +
					"could not be found.", sessionKey);
			return null;
		}

		String sessionIdValue = sessionId.toString();

		HttpServletRequest request = null;
		if(sessionKey instanceof WebSessionKey){
			request = WebUtils.getHttpRequest(sessionKey);

			if(request != null){
				Object s = request.getAttribute(sessionIdValue);
				if(s != null){
					return (Session) s;
				}
			}
		}

		Session s = retrieveSessionFromDataSource(sessionId);
		if(s == null){
			//session ID was provided, meaning one is expected to be found, but we couldn't find one:
			throw new UnknownSessionException("Could not find session with ID [" + sessionId + "]");
		}

		if(request != null){
			request.setAttribute(sessionIdValue, s);
		}

		return s;
	}

}
