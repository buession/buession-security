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
import com.buession.security.shiro.serializer.ObjectSerializer;
import com.buession.security.shiro.serializer.StringSerializer;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class RedisCache<K, V> implements Cache<K, V> {

	private RedisManager redisManager;

	private String keyPrefix = Constants.DEFAULT_KEY_PREFIX;

	private StringSerializer keySerializer = new StringSerializer();

	private ObjectSerializer valueSerializer = new ObjectSerializer();

	private int expire = 0;

	private final static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	public RedisCache(){
	}

	public RedisCache(RedisManager redisManager, String keyPrefix, int expire){
		setRedisManager(redisManager);
		this.keyPrefix = keyPrefix;
		this.expire = expire;
	}

	public RedisManager getRedisManager(){
		return redisManager;
	}

	public void setRedisManager(final RedisManager redisManager){
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

	@Override
	public Set<K> keys(){
		logger.debug("Get RedisCache Keys");
		Set<byte[]> keys;

		try{
			byte[] pattern = keySerializer.serialize(makeKey("*"));
			keys = redisManager.keys(pattern);
		}catch(SerializerException e){
			logger.error("Get cache keys error", e);
			return Collections.emptySet();
		}

		if(Validate.isEmpty(keys)){
			return Collections.emptySet();
		}else{
			Set<K> newKeys = new HashSet<>(keys.size());

			try{
				for(byte[] key : keys){
					newKeys.add((K) keySerializer.deserialize(key));
				}
			}catch(SerializerException e){
				logger.error("deserialize keys error", e);
			}

			return newKeys;
		}
	}

	@Override
	public V get(K key) throws CacheException{
		logger.debug("Get RedisCache: {}", key);
		if(key == null){
			return null;
		}

		try{
			return (V) valueSerializer.deserialize(redisManager.get(buildKey(key)));
		}catch(SerializerException e){
			logger.error("Get cache error", e);
			throw new CacheException(e);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException{
		logger.debug("Put RedisCache: {} => {}", key, value);
		if(key == null){
			logger.warn("Saving a null key is meaningless, return value directly without call Redis.");
			return value;
		}

		try{
			redisManager.set(buildKey(key), valueSerializer.serialize(value), expire);
			return value;
		}catch(SerializerException e){
			logger.error("Put cache error", e);
			throw new CacheException(e);
		}
	}

	@Override
	public V remove(K key) throws CacheException{
		logger.debug("Remove RedisCache: {}", key);
		if(key == null){
			return null;
		}

		try{
			byte[] cacheKey = buildKey(key);
			byte[] rawValue = redisManager.get(cacheKey);
			V previous = (V) valueSerializer.deserialize(rawValue);

			redisManager.delete(cacheKey);

			return previous;
		}catch(SerializerException e){
			logger.error("Remove cache error", e);
			throw new CacheException(e);
		}
	}

	@Override
	public void clear() throws CacheException{
		logger.debug("Clear RedisCache");
		Set<byte[]> keys = null;

		try{
			byte[] pattern = keySerializer.serialize(makeKey("*"));
			keys = redisManager.keys(pattern);
		}catch(SerializerException e){
			logger.error("Clear cache keys failure", e);
		}

		if(Validate.isEmpty(keys)){
			return;
		}

		redisManager.delete(keys.toArray(new byte[][]{}));
	}

	@Override
	public int size(){
		try{
			Long longSize = redisManager.dbSize();
			return longSize.intValue();
		}catch(Throwable t){
			throw new CacheException(t);
		}
	}

	@Override
	public Collection<V> values(){
		logger.debug("Get RedisCache Values");
		Set<byte[]> keys;

		try{
			byte[] pattern = keySerializer.serialize(makeKey("*"));
			keys = redisManager.keys(pattern);
		}catch(SerializerException e){
			logger.error("Get cache values error", e);
			return Collections.emptySet();
		}

		List<V> values = new ArrayList<>(keys.size());

		try{
			for(byte[] key : keys){
				V value = (V) valueSerializer.deserialize(redisManager.get(key));

				if(value != null){
					values.add(value);
				}
			}
		}catch(SerializerException e){
			logger.error("deserialize values error", e);
		}

		return Collections.unmodifiableList(values);
	}

	protected final byte[] buildKey(K key) throws SerializerException{
		if(key == null){
			return null;
		}

		String redisKey;
		if(this.keySerializer instanceof StringSerializer){
			redisKey = getStringRedisKey(key);
		}else{
			redisKey = key.toString();
		}

		return keySerializer.serialize(makeKey(redisKey));
	}

	protected String getStringRedisKey(K key){
		String redisKey;

		if(key instanceof PrincipalCollection){
			redisKey = getRedisKeyFromPrincipalCollection((PrincipalCollection) key);
		}else{
			redisKey = key.toString();
		}

		return redisKey;
	}

	protected static String getRedisKeyFromPrincipalCollection(PrincipalCollection key){
		List<String> realmNames = getRealmNames(key);
		Collections.sort(realmNames);
		return joinRealmNames(realmNames);
	}

	protected static List<String> getRealmNames(PrincipalCollection key){
		return new ArrayList<>(key.getRealmNames());
	}

	protected static String joinRealmNames(List<String> realms){
		return realms.stream().collect(Collectors.joining(""));
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