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

import com.buession.core.serializer.SerializerException;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.security.shiro.Constants;
import com.buession.security.shiro.cache.RedisManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RedisSessionDAO extends AbstractSessionDAO {

	private RedisManager redisManager;

	private final static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	public RedisSessionDAO(){
		super();
	}

	public RedisSessionDAO(String keyPrefix, int expire){
		super(keyPrefix, expire);
	}

	public RedisSessionDAO(String keyPrefix, int expire, boolean sessionInMemoryEnabled, long sessionInMemoryTimeout){
		super(keyPrefix, expire, sessionInMemoryEnabled, sessionInMemoryTimeout);
	}

	public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire){
		this(keyPrefix, expire);
		setRedisManager(redisManager);
	}

	public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire, boolean sessionInMemoryEnabled,
						   long sessionInMemoryTimeout){
		this(keyPrefix, expire, sessionInMemoryEnabled, sessionInMemoryTimeout);
		this.redisManager = redisManager;
	}

	public RedisManager getRedisManager(){
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager){
		Assert.isNull(redisManager, "RedisManager could not be null.");
		this.redisManager = redisManager;
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
					byte[] value = redisManager.get(key);
					Session session = (Session) Constants.VALUE_SERIALIZER.deserialize(value);

					sessions.add(session);
				}
			}
		}catch(SerializerException e){
			logger.error("get active sessions error.");
		}
		return sessions;
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
	protected Session getSession(Serializable sessionId){
		Session session = null;

		logger.debug("Read session from redis");

		try{
			byte[] value = redisManager.get(getSessionKey(sessionId));

			if(value != null){
				session = (Session) Constants.VALUE_SERIALIZER.deserialize(value);
				if(isSessionInMemoryEnabled()){
					setSessionToThreadLocal(sessionId, session);
				}
			}
		}catch(SerializerException e){
			logger.error("read session error: {}. session id: {}", e.getMessage(), sessionId);
		}

		return session;
	}

	@Override
	protected void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			logger.error("session or session id is null");
			throw new UnknownSessionException("session or session id is null");
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

		int millisecondsInASecond = 1000;
		int expire = getExpire();

		if(expire == DEFAULT_EXPIRE){
			redisManager.set(key, value, (int) (session.getTimeout() / millisecondsInASecond));
			return;
		}

		long millisecondsExpire = expire * millisecondsInASecond;
		if(expire != NO_EXPIRE && millisecondsExpire < session.getTimeout()){
			logger.warn("Redis session expire time: {} is less than Session timeout: {}. It may cause some problems.",
					millisecondsExpire, session.getTimeout());
		}

		redisManager.set(key, value, expire);
	}

	protected byte[] getSessionKey(Serializable sessionId) throws SerializerException{
		return Constants.KEY_SERIALIZER.serialize(makeKey(sessionId.toString()));
	}

	protected String makeKey(final String key){
		if(Validate.isEmpty(getKeyPrefix())){
			return key;
		}else{
			StringBuilder sb = new StringBuilder(getKeyPrefix().length() + key.length());

			sb.append(getKeyPrefix()).append(key);

			return sb.toString();
		}
	}

}