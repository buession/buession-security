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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.shiro.session;

import com.buession.core.serializer.SerializerException;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.security.shiro.RedisManager;
import com.buession.security.shiro.serializer.ObjectSerializer;
import com.buession.security.shiro.serializer.RedisSerializer;
import com.buession.security.shiro.serializer.StringSerializer;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Redis Data Access Object design pattern specification to enable {@link Session} access to an EIS (Enterprise Information System).
 *
 * @author Yong.Teng
 */
public class RedisSessionDAO extends AbstractSessionDAO {

	/**
	 * Redis 管理器
	 */
	private RedisManager redisManager;

	/**
	 * Key 序列化对象
	 */
	private RedisSerializer<String> keySerializer = new StringSerializer();

	/**
	 * 值序列化对象
	 */
	private RedisSerializer<Session> valueSerializer = new ObjectSerializer<>();

	private final static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	/**
	 * 构造函数
	 */
	public RedisSessionDAO() {
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
	public RedisSessionDAO(String keyPrefix, int expire) {
		super(keyPrefix, expire);
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
	public RedisSessionDAO(String keyPrefix, int expire, boolean sessionInMemoryEnabled, long sessionInMemoryTimeout) {
		super(keyPrefix, expire, sessionInMemoryEnabled, sessionInMemoryTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 *        {@link RedisManager} 实例
	 * @param keyPrefix
	 * 		SESSION Key 前缀
	 * @param expire
	 * 		过期时间（单位：秒）{@link #expire}
	 */
	public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire) {
		this(keyPrefix, expire);
		setRedisManager(redisManager);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 *        {@link RedisManager} 实例
	 * @param keyPrefix
	 * 		SESSION Key 前缀
	 * @param expire
	 * 		过期时间（单位：秒）{@link #expire}
	 * @param sessionInMemoryEnabled
	 * 		SESSION 是否存储在内存中
	 * @param sessionInMemoryTimeout
	 * 		SESSION 存储在内存中的过期时间
	 */
	public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire, boolean sessionInMemoryEnabled,
						   long sessionInMemoryTimeout) {
		this(keyPrefix, expire, sessionInMemoryEnabled, sessionInMemoryTimeout);
		this.redisManager = redisManager;
	}

	/**
	 * 返回 {@link RedisManager} 实例
	 *
	 * @return {@link RedisManager} 实例
	 */
	public RedisManager getRedisManager() {
		return redisManager;
	}

	/**
	 * 设置 {@link RedisManager} 实例
	 *
	 * @param redisManager
	 *        {@link RedisManager} 实例
	 */
	public void setRedisManager(RedisManager redisManager) {
		Assert.isNull(redisManager, "RedisManager could not be null.");
		this.redisManager = redisManager;
	}

	/**
	 * 获取 Key 序列化对象
	 *
	 * @return Key 序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisSerializer<String> getKeySerializer() {
		return keySerializer;
	}

	/**
	 * 设置 Key 序列化对象
	 *
	 * @param keySerializer
	 * 		Key 序列化对象
	 *
	 * @since 1.2.2
	 */
	public void setKeySerializer(RedisSerializer<String> keySerializer) {
		Assert.isNull(keySerializer, "Key serializer could not be null.");
		this.keySerializer = keySerializer;
	}

	/**
	 * 获取值序列化对象
	 *
	 * @return 值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisSerializer<Session> getValueSerializer() {
		return valueSerializer;
	}

	/**
	 * 设置值序列化对象
	 *
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public void setValueSerializer(RedisSerializer<Session> valueSerializer) {
		Assert.isNull(valueSerializer, "Value serializer could not be null.");
		this.valueSerializer = valueSerializer;
	}

	@Override
	protected void doSaveSession(final Session session) throws UnknownSessionException {
		byte[] key;
		byte[] value;

		try{
			key = getSessionKey(session.getId());
			value = valueSerializer.serialize(session);
		}catch(SerializerException e){
			logger.error("serialize session: {} error: {}.", session.getId(), e.getMessage());
			throw new UnknownSessionException(e);
		}

		long millisecondsInASecond = 1000L;
		int expire = getExpire();

		if(expire == DEFAULT_EXPIRE){
			redisManager.set(key, value, (int) (session.getTimeout() / millisecondsInASecond));
			return;
		}

		long millisecondsExpire = expire * millisecondsInASecond;
		if(expire != NO_EXPIRE && millisecondsExpire < session.getTimeout()){
			if(logger.isWarnEnabled()){
				logger.warn("Redis session expire time: {} is less than Session timeout: {}. It may cause some " +
						"problems.", millisecondsExpire, session.getTimeout());
			}
		}

		redisManager.set(key, value, expire);
	}

	@Override
	protected Session doReadSpecialSession(Serializable sessionId) {
		Session session = null;

		try{
			byte[] key = getSessionKey(sessionId);
			byte[] value = redisManager.get(key);

			if(value != null){
				session = valueSerializer.deserialize(value);
			}
		}catch(Exception e){
			logger.error("read session: {} error: {}.", sessionId, e.getMessage());
		}

		return session;
	}

	@Override
	protected Collection<Session> doGetActiveSessions() {
		Set<Session> sessions = new HashSet<>();
		byte[] pattern;

		try{
			pattern = keySerializer.serialize(makeKey("*"));
			Set<byte[]> keys = redisManager.keys(pattern);

			if(Validate.isNotEmpty(keys)){
				for(byte[] key : keys){
					byte[] value = redisManager.get(key);
					Session session = valueSerializer.deserialize(value);
					sessions.add(session);
				}
			}
		}catch(Exception e){
			logger.error("get active sessions error: {}.", e.getMessage());
		}

		return sessions;
	}

	@Override
	protected void doDeleteSession(Session session) {
		try{
			redisManager.delete(getSessionKey(session.getId()));
		}catch(SerializerException e){
			logger.error("delete session: {}, error: {}.", session.getId(), e.getMessage());
		}
	}

	protected byte[] getSessionKey(Serializable sessionId) throws SerializerException {
		return keySerializer.serialize(makeKey(sessionId.toString()));
	}

	protected String makeKey(final String key) {
		return getKeyPrefix() == null ? key : getKeyPrefix() + key;
	}

}