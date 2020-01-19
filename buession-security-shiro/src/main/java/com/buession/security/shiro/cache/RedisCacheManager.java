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

import com.buession.security.shiro.Constants;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Yong.Teng
 */
public class RedisCacheManager implements CacheManager {

	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>(64, 0.8F);

	private RedisManager redisManager;

	private String keyPrefix = Constants.DEFAULT_KEY_PREFIX;

	private int expire = Constants.DEFAULT_EXPIRE;

	public RedisCacheManager(){
	}

	public RedisCacheManager(RedisManager redisManager, String keyPrefix, int expire){
		setRedisManager(redisManager);
		this.keyPrefix = keyPrefix;
		this.expire = expire;
	}

	public RedisManager getRedisManager(){
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager){
		if(redisManager == null){
			throw new IllegalArgumentException("RedisManager could not be null.");
		}
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

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException{
		Cache cache = caches.get(name);

		if(cache == null){
			cache = new RedisCache<K, V>(redisManager, makeKey(name), expire);
			caches.put(name, cache);
		}

		return cache;
	}

	protected String makeKey(final String key){
		if(keyPrefix == null){
			return key;
		}else{
			StringBuilder sb = new StringBuilder();

			sb.append(keyPrefix).append(key);

			return sb.toString();
		}
	}

}