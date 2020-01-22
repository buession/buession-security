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
package com.buession.security.shiro.cache;

import com.buession.core.serializer.SerializerException;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.security.shiro.Constants;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RedisSessionDAO extends AbstractSessionDAO {

	private RedisManager redisManager;

	private String keyPrefix = Constants.DEFAULT_KEY_PREFIX;

	private int expire = Constants.DEFAULT_EXPIRE;

	private int timeout = Constants.DEFAULT_TIMEOUT;

	private long sessionInMemoryTimeout = Constants.DEFAULT_SESSION_IN_MEMORY_TIMEOUT;

	private static ThreadLocal<Map<Serializable, SessionInMemory>> sessionsInThread = new ThreadLocal<>();

	private final static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	public RedisSessionDAO(){
	}

	public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire){
		setRedisManager(redisManager);
		this.keyPrefix = keyPrefix;
		this.expire = expire;
	}

	public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire, int timeout){
		this(redisManager, keyPrefix, expire);
		this.timeout = timeout;
	}

	public RedisManager getRedisManager(){
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager){
		Assert.isNull(redisManager, "RedisManager could not be null.");
		this.redisManager = redisManager;
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

	public void setExpire(final int expire){
		this.expire = expire;
	}

	public int getTimeout(){
		return timeout;
	}

	public void setTimeout(int timeout){
		this.timeout = timeout;
	}

	@Override
	public Collection<Session> getActiveSessions(){
		Set<Session> sessions = new HashSet<>();
		byte[] pattern;

		try{
			pattern = Constants.KEY_SERIALIZER.serialize(makeKey("*"));
			Set<byte[]> keys = redisManager.keys(pattern);

			if(Validate.isEmpty(keys) == false){
				for(byte[] key : keys){
					Session session = (Session) Constants.VALUE_SERIALIZER.deserialize(redisManager.get(key));
					sessions.add(session);
				}
			}
		}catch(SerializerException e){
			logger.error("get active sessions error.");
		}
		return sessions;
	}

	@Override
	public void update(Session session) throws UnknownSessionException{
		saveSession(session);
	}

	@Override
	public void delete(Session session){
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			return;
		}

		try{
			redisManager.delete(getSessionKey(session.getId()));
		}catch(SerializerException e){
			logger.error("delete session error: {}. session id: {}", e.getMessage(), session.getId());
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

		if(session != null){
			return session;
		}

		logger.debug("Read session from redis");

		try{
			byte[] value = redisManager.get(getSessionKey(sessionId));

			if(value != null){
				session = (Session) Constants.VALUE_SERIALIZER.deserialize(value);
				setSessionToThreadLocal(sessionId, session);
			}
		}catch(SerializerException e){
			logger.error("read session error: {}. session id: {}", e.getMessage(), sessionId);
		}

		return session;
	}

	protected void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			throw new UnknownSessionException("session or session id is null");
		}

		if(expire < session.getTimeout()){
			logger.warn("Read session expire time: {} less than session timeout: {}. It may cause some problems.",
					timeout, session.getTimeout());
		}

		byte[] key;
		byte[] value;

		try{
			key = getSessionKey(session.getId());
			value = Constants.VALUE_SERIALIZER.serialize(session);
		}catch(SerializerException e){
			logger.error("serialize session error: {}. session id: {}", e.getMessage(), session.getId());
			throw new UnknownSessionException(e);
		}

		session.setTimeout(getTimeout());
		redisManager.set(key, value, expire);
	}

	protected Session getSessionFromThreadLocal(Serializable sessionId){
		Map<Serializable, SessionInMemory> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			return null;
		}

		SessionInMemory sessionInMemory = sessionMap.get(sessionId);
		if(sessionInMemory == null){
			return null;
		}

		long duration = System.currentTimeMillis() - sessionInMemory.getCreateTime().getTime();
		Session s = null;

		if(duration < sessionInMemoryTimeout){
			s = sessionInMemory.getSession();
			logger.debug("read session from memory");
		}else{
			sessionMap.remove(sessionId);
		}

		return s;
	}

	protected void setSessionToThreadLocal(Serializable sessionId, Session session){
		Map<Serializable, SessionInMemory> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			sessionMap = new HashMap<>(32, 0.8F);
		}

		SessionInMemory sessionInMemory = new SessionInMemory();
		sessionInMemory.setCreateTime(new Date());
		sessionInMemory.setSession(session);

		sessionMap.put(sessionId, sessionInMemory);

		sessionsInThread.set(sessionMap);
	}

	protected byte[] getSessionKey(Serializable sessionId) throws SerializerException{
		return Constants.KEY_SERIALIZER.serialize(makeKey(sessionId.toString()));
	}

	protected String makeKey(final String key){
		if(Validate.isEmpty(keyPrefix)){
			return key;
		}else{
			StringBuilder sb = new StringBuilder(keyPrefix.length() + key.length());

			sb.append(keyPrefix).append(key);

			return sb.toString();
		}
	}

}