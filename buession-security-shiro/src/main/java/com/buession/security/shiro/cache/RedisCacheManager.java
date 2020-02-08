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

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Yong.Teng
 */
public class RedisCacheManager extends AbstractCacheManager {

	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

	private RedisManager redisManager;

	private final static Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

	public RedisCacheManager(){
		super();
	}

	public RedisCacheManager(String keyPrefix, int expire){
		super(keyPrefix, expire);
	}

	public RedisCacheManager(String keyPrefix, int expire, String principalIdFieldName){
		super(keyPrefix, expire, principalIdFieldName);
	}

	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire){
		this(keyPrefix, expire);
		setRedisManager(redisManager);
	}

	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire, String principalIdFieldName){
		this(keyPrefix, expire, principalIdFieldName);
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
	@SuppressWarnings({"unchecked"})
	public <K, V> Cache<K, V> getCache(String name) throws CacheException{
		logger.debug("Get cache, name: ", name);

		Cache<K, V> cache = caches.get(name);

		if(cache == null){
			cache = new RedisCache<>(redisManager, makeKey(name), getExpire(), getPrincipalIdFieldName());

			if(cache == null){
				caches.put(name, cache);
			}
		}

		return cache;
	}

	protected String makeKey(final String key){
		if(Validate.isEmpty(getKeyPrefix())){
			return key;
		}else{
			StringBuilder sb = new StringBuilder(getKeyPrefix().length() + key.length());

			sb.append(getKeyPrefix()).append(':').append(key).append(':');

			return sb.toString();
		}
	}

}