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
package com.buession.security.shiro.session;

import com.buession.security.shiro.cache.MemorySession;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractSessionDAO extends org.apache.shiro.session.mgt.eis.AbstractSessionDAO implements
		SessionDAO {

	private String keyPrefix = DEFAULT_SESSION_KEY_PREFIX;

	private int expire = DEFAULT_EXPIRE;

	private boolean sessionInMemoryEnabled = DEFAULT_SESSION_IN_MEMORY_ENABLED;

	private long sessionInMemoryTimeout = DEFAULT_SESSION_IN_MEMORY_TIMEOUT;

	private static ThreadLocal<Map<Serializable, MemorySession>> sessionsInThread = new ThreadLocal<>();

	private final static Logger logger = LoggerFactory.getLogger(AbstractSessionDAO.class);

	public AbstractSessionDAO(){
		super();
	}

	public AbstractSessionDAO(String keyPrefix, int expire){
		this.keyPrefix = keyPrefix;
		this.expire = expire;
	}

	public AbstractSessionDAO(String keyPrefix, int expire, boolean sessionInMemoryEnabled, long
			sessionInMemoryTimeout){
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
		saveSession(session);

		if(sessionInMemoryEnabled){
			setSessionToThreadLocal(session.getId(), session);
		}
	}

	@Override
	protected Serializable doCreate(Session session){
		if(session == null){
			logger.error("session is null");
			throw new UnknownSessionException("session is null");
		}

		Serializable sessionId = generateSessionId(session);

		assignSessionId(session, sessionId);
		saveSession(session);

		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId){
		if(sessionId == null){
			logger.error("session id is null");
			return null;
		}

		Session session = getSessionFromThreadLocal(sessionId);
		return session == null ? getSession(sessionId) : session;
	}

	protected abstract Session getSession(Serializable sessionId);

	protected abstract void saveSession(Session session) throws UnknownSessionException;

	protected Session getSessionFromThreadLocal(Serializable sessionId){
		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			return null;
		}

		MemorySession memorySession = sessionMap.get(sessionId);
		if(memorySession == null){
			return null;
		}

		long liveTime = getMemorySessionLiveTime(memorySession);
		if(liveTime > sessionInMemoryTimeout){
			sessionMap.remove(sessionId);
			return null;
		}

		Session session = memorySession.getSession();
		logger.debug("read session from memory");

		return session;
	}

	protected void setSessionToThreadLocal(Serializable sessionId, Session session){
		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			sessionMap = new HashMap<>(32, 0.8F);
			sessionsInThread.set(sessionMap);
		}

		removeExpiredSessionInMemory(sessionMap);

		MemorySession memorySession = new MemorySession();
		memorySession.setCreateTime(new Date());
		memorySession.setSession(session);

		sessionMap.put(sessionId, memorySession);
	}

	protected void removeExpiredSessionInMemory(Map<Serializable, MemorySession> sessionMap){
		logger.debug("Remove expired session in memory.");
		Iterator<Serializable> iterator = sessionMap.keySet().iterator();
		while(iterator.hasNext()){
			Serializable sessionId = iterator.next();
			MemorySession memorySession = sessionMap.get(sessionId);

			if(memorySession == null){
				iterator.remove();
				continue;
			}

			long liveTime = getMemorySessionLiveTime(memorySession);
			if(liveTime > sessionInMemoryTimeout){
				iterator.remove();
			}
		}
	}

	protected long getMemorySessionLiveTime(MemorySession memorySession){
		return System.currentTimeMillis() - memorySession.getCreateTime().getTime();
	}

}
