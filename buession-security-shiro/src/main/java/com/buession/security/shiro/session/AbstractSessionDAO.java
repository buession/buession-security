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
package com.buession.security.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Yong.Teng
 */
public abstract class AbstractSessionDAO extends org.apache.shiro.session.mgt.eis.AbstractSessionDAO implements SessionDAO {

	private String keyPrefix = DEFAULT_SESSION_KEY_PREFIX;

	private int expire = DEFAULT_EXPIRE;

	private boolean sessionInMemoryEnabled = DEFAULT_SESSION_IN_MEMORY_ENABLED;

	private long sessionInMemoryTimeout = DEFAULT_SESSION_IN_MEMORY_TIMEOUT;

	private static MemorySessionDAO memorySessionDao;

	private final static Logger logger = LoggerFactory.getLogger(AbstractSessionDAO.class);

	public AbstractSessionDAO(){
		super();
	}

	public AbstractSessionDAO(String keyPrefix, int expire){
		this.keyPrefix = keyPrefix;
		this.expire = expire;
	}

	public AbstractSessionDAO(String keyPrefix, int expire, boolean sessionInMemoryEnabled,
							  long sessionInMemoryTimeout){
		this(keyPrefix, expire);
		this.sessionInMemoryEnabled = sessionInMemoryEnabled;
		this.sessionInMemoryTimeout = sessionInMemoryTimeout;
	}

	public String getKeyPrefix(){
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix){
		this.keyPrefix = keyPrefix;
	}

	public int getExpire(){
		return expire;
	}

	public void setExpire(int expire){
		this.expire = expire;
	}

	public boolean isSessionInMemoryEnabled(){
		return sessionInMemoryEnabled;
	}

	public void setSessionInMemoryEnabled(boolean sessionInMemoryEnabled){
		this.sessionInMemoryEnabled = sessionInMemoryEnabled;
	}

	public long getSessionInMemoryTimeout(){
		return sessionInMemoryTimeout;
	}

	public void setSessionInMemoryTimeout(long sessionInMemoryTimeout){
		this.sessionInMemoryTimeout = sessionInMemoryTimeout;
	}

	@Override
	public void update(Session session) throws UnknownSessionException{
		logger.debug("Update session: {}.", session == null ? "null" : session.getId());
		removeExpiredSessionInMemory();

		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			throw new UnknownSessionException("session or session id is null");
		}

		doSaveSession(session);

		if(sessionInMemoryEnabled){
			getMemorySessionDAO().save(session);
		}
	}

	@Override
	public Collection<Session> getActiveSessions(){
		logger.debug("Get active sessions.");
		removeExpiredSessionInMemory();

		return doGetActiveSessions();
	}

	@Override
	public void delete(Session session){
		logger.debug("Delete session: {}.", session == null ? "null" : session.getId());
		removeExpiredSessionInMemory();

		if(session == null || session.getId() == null){
			logger.error("session or session id is null.");
			return;
		}

		doDeleteSession(session);
	}

	@Override
	protected Serializable doCreate(Session session){
		logger.debug("Create session: {}.", session == null ? "null" : session.getId());
		removeExpiredSessionInMemory();

		if(session == null){
			logger.error("session is null.");
			throw new UnknownSessionException("session is null");
		}

		Serializable sessionId = generateSessionId(session);

		assignSessionId(session, sessionId);
		doSaveSession(session);

		return sessionId;
	}

	protected abstract void doSaveSession(final Session session) throws UnknownSessionException;

	@Override
	protected Session doReadSession(Serializable sessionId){
		logger.debug("Read session: {}.", sessionId);
		removeExpiredSessionInMemory();

		if(sessionId == null){
			logger.error("session id is null.");
			return null;
		}

		Session session;

		if(sessionInMemoryEnabled){
			session = getMemorySessionDAO().read(sessionId);
			if(session != null){
				return session;
			}
		}

		session = doReadSpecialSession(sessionId);
		if(sessionInMemoryEnabled){
			if(session != null){
				getMemorySessionDAO().save(session);
			}
		}

		return session;
	}

	protected abstract Session doReadSpecialSession(Serializable sessionId);

	protected abstract Collection<Session> doGetActiveSessions();

	protected abstract void doDeleteSession(Session session);

	protected void removeExpiredSessionInMemory(){
		if(sessionInMemoryEnabled){
			getMemorySessionDAO().clearExpiredSession();
		}
	}

	private MemorySessionDAO getMemorySessionDAO(){
		if(memorySessionDao == null){
			memorySessionDao = new MemorySessionDAO(sessionInMemoryTimeout);
		}

		return memorySessionDao;
	}

}
