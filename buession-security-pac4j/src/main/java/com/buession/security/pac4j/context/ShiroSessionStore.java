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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.pac4j.context;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.Session;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.session.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class ShiroSessionStore implements SessionStore<JEEContext> {

	public final static ShiroSessionStore INSTANCE = new ShiroSessionStore();

	private final static Logger logger = LoggerFactory.getLogger(ShiroSessionStore.class);

	@Override
	public String getOrCreateSessionId(JEEContext context){
		final Session session = getSession(true);
		return session == null ? null : session.getId().toString();
	}

	@Override
	public Optional<Object> get(JEEContext context, String key){
		final Session session = getSession(false);
		return session == null ? Optional.empty() : Optional.ofNullable(session.getAttribute(key));
	}

	@Override
	public void set(JEEContext context, String key, Object value){
		final Session session = getSession(true);
		if(session == null){
			return;
		}

		if(value == null){
			session.removeAttribute(key);
		}else{
			try{
				session.setAttribute(key, value);
			}catch(UnavailableSecurityManagerException e){
				logger.warn("Should happen just once at startup in some specific case of Shiro Spring configuration",
						e);
			}
		}
	}

	@Override
	public boolean destroySession(JEEContext context){
		getSession(true).stop();
		return true;
	}

	@Override
	public Optional getTrackableSession(JEEContext context){
		//return Optional.ofNullable(getSession(true));
		return Optional.empty();
	}

	@Override
	public Optional<SessionStore<JEEContext>> buildFromTrackableSession(JEEContext context, Object trackableSession){
		return Optional.empty();
	}

	@Override
	public boolean renewSession(JEEContext context){
		final Session session = getSession(false);
		if(session == null){
			return true;
		}

		logger.debug("Discard old session: {}", session.getId());
		final Map<Object, Object> attributes = Collections.list(Collections.enumeration(session.getAttributeKeys()))
				.stream().collect(Collectors.toMap(k->k, session::getAttribute, (a, b)->b));

		session.stop();

		final Session newSession = getSession(true);
		logger.debug("And copy all data to the new one: {}", newSession.getId());
		attributes.forEach((k, v)->newSession.setAttribute(k, v));
		return true;
	}

	protected Session getSession(final boolean createSession){
		return SecurityUtils.getSubject().getSession(createSession);
	}

}
