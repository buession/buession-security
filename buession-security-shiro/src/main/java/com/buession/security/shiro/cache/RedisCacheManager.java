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
package com.buession.security.shiro.cache;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.security.shiro.RedisManager;
import com.buession.security.shiro.serializer.ObjectSerializer;
import com.buession.security.shiro.serializer.RedisSerializer;
import com.buession.security.shiro.serializer.StringSerializer;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Redis 缓存 {@link Cache} 管理器
 *
 * @author Yong.Teng
 * @see Cache
 */
public class RedisCacheManager extends AbstractCacheManager {

	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

	/**
	 * Redis 管理器 {@link RedisManager} 实例
	 */
	private RedisManager redisManager;

	/**
	 * Key 序列化对象
	 */
	private RedisSerializer<String> keySerializer = new StringSerializer();

	/**
	 * 值序列化对象
	 */
	private RedisSerializer<Object> valueSerializer = new ObjectSerializer<>();

	private final static Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

	/**
	 * 构造函数
	 */
	public RedisCacheManager() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 */
	public RedisCacheManager(String keyPrefix, int expire) {
		super(keyPrefix, expire);
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param principalIdFieldName
	 * 		身份信息 ID 字段名称
	 */
	public RedisCacheManager(String keyPrefix, int expire, String principalIdFieldName) {
		super(keyPrefix, expire, principalIdFieldName);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器 {@link RedisManager} 实例
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 */
	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire) {
		this(keyPrefix, expire);
		setRedisManager(redisManager);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器 {@link RedisManager} 实例
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param principalIdFieldName
	 * 		身份信息 ID 字段名称
	 */
	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire, String principalIdFieldName) {
		this(keyPrefix, expire, principalIdFieldName);
		setRedisManager(redisManager);
	}

	/**
	 * 构造函数
	 *
	 * @param keySerializer
	 * 		Key 序列化对象
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisCacheManager(RedisSerializer<String> keySerializer, RedisSerializer<Object> valueSerializer) {
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param keySerializer
	 * 		Key 序列化对象
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisCacheManager(String keyPrefix, int expire, RedisSerializer<String> keySerializer,
							 RedisSerializer<Object> valueSerializer) {
		super(keyPrefix, expire);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param principalIdFieldName
	 * 		身份信息 ID 字段名称
	 * @param keySerializer
	 * 		Key 序列化对象
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisCacheManager(String keyPrefix, int expire, String principalIdFieldName,
							 RedisSerializer<String> keySerializer, RedisSerializer<Object> valueSerializer) {
		super(keyPrefix, expire, principalIdFieldName);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器 {@link RedisManager} 实例
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param keySerializer
	 * 		Key 序列化对象
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire,
							 RedisSerializer<String> keySerializer, RedisSerializer<Object> valueSerializer) {
		this(keyPrefix, expire);
		setRedisManager(redisManager);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器 {@link RedisManager} 实例
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param principalIdFieldName
	 * 		身份信息 ID 字段名称
	 * @param keySerializer
	 * 		Key 序列化对象
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire, String principalIdFieldName,
							 RedisSerializer<String> keySerializer, RedisSerializer<Object> valueSerializer) {
		this(keyPrefix, expire, principalIdFieldName);
		setRedisManager(redisManager);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 返回 Redis 管理器 {@link RedisManager} 实例
	 *
	 * @return Redis 管理器 {@link RedisManager} 实例
	 */
	public RedisManager getRedisManager() {
		return redisManager;
	}

	/**
	 * 设置 Redis 管理器 {@link RedisManager} 实例
	 *
	 * @param redisManager
	 * 		Redis 管理器 {@link RedisManager} 实例
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
	public RedisSerializer<Object> getValueSerializer() {
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
	public void setValueSerializer(RedisSerializer<Object> valueSerializer) {
		Assert.isNull(valueSerializer, "Value serializer could not be null.");
		this.valueSerializer = valueSerializer;
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if(logger.isDebugEnabled()){
			logger.debug("Get cache name: {}", name);
		}

		return caches.computeIfAbsent(name, (key)->{
			String principalIdFieldName = Validate.isEmpty(
					getPrincipalIdFieldName()) ? DEFAULT_PRINCIPAL_ID_FIELD_NAME : getPrincipalIdFieldName();
			return new RedisCache<>(redisManager, makeKey(name), getExpire(), principalIdFieldName,
					getKeySerializer(), getValueSerializer());
		});
	}

	protected String makeKey(final String key) {
		return getKeyPrefix() == null ? key + ':' : getKeyPrefix() + key + ':';
	}

}