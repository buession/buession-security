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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
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
 * Data Access Object design pattern specification to enable {@link Session} access to an EIS (Enterprise Information System).
 *
 * @author Yong.Teng
 * @see org.apache.shiro.session.mgt.eis.SessionDAO
 */
public abstract class AbstractSessionDAO extends org.apache.shiro.session.mgt.eis.AbstractSessionDAO
		implements SessionDAO {

	/**
	 * 默认 SESSION Key 前缀
	 */
	public final static String DEFAULT_SESSION_KEY_PREFIX = "shiro:session:";

	/**
	 * 默认过期时间
	 */
	public final static int DEFAULT_EXPIRE = -2;

	/**
	 * 永不过期时间
	 */
	public final static int NO_EXPIRE = -1;

	/**
	 * 默认 SESSION 是否存储在内存中
	 */
	public final static boolean DEFAULT_SESSION_IN_MEMORY_ENABLED = true;

	/**
	 * 默认 SESSION 存储在内存中的过期时间
	 */
	public final static long DEFAULT_SESSION_IN_MEMORY_TIMEOUT = 1000L;

	/**
	 * SESSION Key 前缀
	 */
	private String keyPrefix = DEFAULT_SESSION_KEY_PREFIX;

	/**
	 * 过期时间（单位：秒）
	 * <p>-1：永不过期</p>
	 * <p>-2：和 Session 过期时间相同</p>
	 * <P>确保过期时间大于会话超时时间</P>
	 */
	private int expire = DEFAULT_EXPIRE;

	/**
	 * SESSION 是否存储在内存中
	 */
	private boolean sessionInMemoryEnabled = DEFAULT_SESSION_IN_MEMORY_ENABLED;

	/**
	 * SESSION 存储在内存中的过期时间（单位：毫秒）
	 */
	private long sessionInMemoryTimeout = DEFAULT_SESSION_IN_MEMORY_TIMEOUT;

	private static MemorySessionDAO memorySessionDao;

	private final static Logger logger = LoggerFactory.getLogger(AbstractSessionDAO.class);

	/**
	 * 构造函数
	 */
	public AbstractSessionDAO(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		SESSION Key 前缀
	 * @param expire
	 * 		过期时间（单位：秒）{@link #expire}
	 */
	public AbstractSessionDAO(String keyPrefix, int expire){
		super();
		this.keyPrefix = keyPrefix;
		this.expire = expire;
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		SESSION Key 前缀
	 * @param expire
	 * 		过期时间（单位：秒）{@link #expire}
	 * @param sessionInMemoryEnabled
	 * 		SESSION 是否存储在内存中
	 * @param sessionInMemoryTimeout
	 * 		SESSION 存储在内存中的过期时间
	 */
	public AbstractSessionDAO(String keyPrefix, int expire, boolean sessionInMemoryEnabled,
							  long sessionInMemoryTimeout){
		this(keyPrefix, expire);
		this.sessionInMemoryEnabled = sessionInMemoryEnabled;
		this.sessionInMemoryTimeout = sessionInMemoryTimeout;
	}

	/**
	 * 返回 SESSION Key 前缀
	 *
	 * @return SESSION Key 前缀
	 */
	public String getKeyPrefix(){
		return keyPrefix;
	}

	/**
	 * 设置SESSION Key 前缀
	 *
	 * @param keyPrefix
	 * 		SESSION Key 前缀
	 */
	public void setKeyPrefix(String keyPrefix){
		this.keyPrefix = keyPrefix;
	}

	/**
	 * 返回过期时间（单位：秒）
	 *
	 * @return 过期时间
	 *
	 * @see #expire
	 */
	public int getExpire(){
		return expire;
	}

	/**
	 * 设置过期时间
	 *
	 * @param expire
	 * 		过期时间（单位：秒）
	 *
	 * @see #expire
	 */
	public void setExpire(int expire){
		this.expire = expire;
	}

	/**
	 * 返回 SESSION 是否存储在内存中
	 *
	 * @return SESSION 是否存储在内存中
	 */
	public boolean isSessionInMemoryEnabled(){
		return sessionInMemoryEnabled;
	}

	/**
	 * 设置 SESSION 是否存储在内存中
	 *
	 * @param sessionInMemoryEnabled
	 * 		SESSION 是否存储在内存中
	 */
	public void setSessionInMemoryEnabled(boolean sessionInMemoryEnabled){
		this.sessionInMemoryEnabled = sessionInMemoryEnabled;
	}

	/**
	 * 返回 SESSION 存储在内存中的过期时间（单位：毫秒）
	 *
	 * @return SESSION 存储在内存中的过期时间
	 */
	public long getSessionInMemoryTimeout(){
		return sessionInMemoryTimeout;
	}

	/**
	 * 设置 SESSION 存储在内存中的过期时间
	 *
	 * @param sessionInMemoryTimeout
	 * 		SESSION 存储在内存中的过期时间（单位：毫秒）
	 */
	public void setSessionInMemoryTimeout(long sessionInMemoryTimeout){
		this.sessionInMemoryTimeout = sessionInMemoryTimeout;
	}

	@Override
	public void update(Session session) throws UnknownSessionException{
		if(logger.isDebugEnabled()){
			logger.debug("Update session: {}.", session == null ? "null" : session.getId());
		}
		removeExpiredSessionInMemory();

		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			throw new UnknownSessionException("session or session id is null");
		}

		doSaveSession(session);

		if(sessionInMemoryEnabled){
			getMemorySessionDAO().update(session);
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
		if(logger.isDebugEnabled()){
			logger.debug("Delete session: {}.", session == null ? "null" : session.getId());
		}
		removeExpiredSessionInMemory();

		if(session == null || session.getId() == null){
			logger.error("session or session id is null.");
			return;
		}

		doDeleteSession(session);
	}

	@Override
	protected Serializable doCreate(Session session){
		if(logger.isDebugEnabled()){
			logger.debug("Create session: {}.", session == null ? "null" : session.getId());
		}
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
		if(logger.isDebugEnabled()){
			logger.debug("Read session: {}.", sessionId);
		}
		removeExpiredSessionInMemory();

		if(sessionId == null){
			logger.error("session id is null.");
			return null;
		}

		Session session;

		if(sessionInMemoryEnabled){
			session = getMemorySessionDAO().readSession(sessionId);
			if(session != null){
				return session;
			}
		}

		session = doReadSpecialSession(sessionId);
		if(sessionInMemoryEnabled){
			if(session != null){
				getMemorySessionDAO().update(session);
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
