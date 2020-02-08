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
import com.buession.security.shiro.exception.CacheManagerPrincipalIdNotAssignedException;
import com.buession.security.shiro.exception.PrincipalIdNullException;
import com.buession.security.shiro.exception.PrincipalInstanceException;
import com.buession.security.shiro.serializer.StringSerializer;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RedisCache<K, V> extends AbstractCache<K, V> {

	private RedisManager redisManager;

	private final static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	public RedisCache(){
	}

	public RedisCache(String keyPrefix, int expire){
		super(keyPrefix, expire);
	}

	public RedisCache(String keyPrefix, int expire, String principalIdFieldName){
		super(keyPrefix, expire, principalIdFieldName);
	}

	public RedisCache(RedisManager redisManager, String keyPrefix, int expire){
		this(keyPrefix, expire);
		setRedisManager(redisManager);
	}

	public RedisCache(RedisManager redisManager, String keyPrefix, int expire, String principalIdFieldName){
		this(keyPrefix, expire, principalIdFieldName);
		setRedisManager(redisManager);
	}

	public RedisManager getRedisManager(){
		return redisManager;
	}

	public void setRedisManager(final RedisManager redisManager){
		Assert.isNull(redisManager, "RedisManager could not be null.");
		this.redisManager = redisManager;
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public Set<K> keys(){
		logger.debug("Get RedisCache Keys");
		Set<byte[]> keys;

		try{
			byte[] pattern = makeKey("*");
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
					newKeys.add((K) Constants.KEY_SERIALIZER.deserialize(key));
				}
			}catch(SerializerException e){
				logger.error("deserialize keys error", e);
			}

			return newKeys;
		}
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public V get(K key) throws CacheException{
		logger.debug("Get RedisCache: {}", key);
		if(key == null){
			return null;
		}

		try{
			byte[] redisKey = makeKey(key);
			byte[] rawValue = redisManager.get(redisKey);

			if(rawValue == null){
				return null;
			}

			return (V) Constants.VALUE_SERIALIZER.deserialize(rawValue);
		}catch(SerializerException e){
			logger.error("Get cache error", e);
			throw new CacheException(e);
		}
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public V put(K key, V value) throws CacheException{
		logger.debug("Put RedisCache: {} => {}", key, value);
		if(key == null){
			logger.warn("Saving a null key is meaningless, return value directly without call Redis.");
			return value;
		}

		try{
			byte[] redisKey = makeKey(key);
			byte[] rawValue = Constants.VALUE_SERIALIZER.serialize(value);

			redisManager.set(redisKey, rawValue, getExpire());
			return value;
		}catch(SerializerException e){
			logger.error("Put cache error", e);
			throw new CacheException(e);
		}
	}

	@Override
	@SuppressWarnings({"unchecked"})
	public V remove(K key) throws CacheException{
		logger.debug("Remove RedisCache: {}", key);
		if(key == null){
			return null;
		}

		try{
			byte[] cacheKey = makeKey(key);
			byte[] rawValue = redisManager.get(cacheKey);
			V previous = (V) Constants.VALUE_SERIALIZER.deserialize(rawValue);

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
			byte[] pattern = makeKey("*");
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
	@SuppressWarnings({"unchecked"})
	public Collection<V> values(){
		logger.debug("Get RedisCache Values");
		Set<byte[]> keys;

		try{
			byte[] pattern = makeKey("*");
			keys = redisManager.keys(pattern);
		}catch(SerializerException e){
			logger.error("Get cache values error", e);
			return Collections.emptySet();
		}

		List<V> values = new ArrayList<>(keys.size());

		try{
			for(byte[] key : keys){
				V value = (V) Constants.VALUE_SERIALIZER.deserialize(redisManager.get(key));
				values.add(value);
			}
		}catch(SerializerException e){
			logger.error("deserialize values error", e);
		}

		return Collections.unmodifiableList(values);
	}

	protected final byte[] makeKey(K key) throws SerializerException{
		if(key == null){
			return null;
		}

		String redisKey;
		if(Constants.KEY_SERIALIZER instanceof StringSerializer){
			redisKey = getStringRedisKey(key);
		}else{
			redisKey = key.toString();
		}

		return makeKey(redisKey);
	}

	protected final byte[] makeKey(String key) throws SerializerException{
		if(key == null){
			return null;
		}

		StringBuilder sb = new StringBuilder(getKeyPrefix().length() + key.length());


		if(Validate.isEmpty(getKeyPrefix()) == false){
			sb.append(getKeyPrefix());
		}

		sb.append(key);

		return Constants.KEY_SERIALIZER.serialize(sb.toString());
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

	protected String getRedisKeyFromPrincipalCollection(PrincipalCollection principalCollection){
		Object principalObject = principalCollection.getPrimaryPrincipal();
		if(principalObject instanceof String){
			return principalObject.toString();
		}

		Method principalIdGetter = getPrincipalIdGetter(principalObject);
		String redisKey;

		try{
			Object idObj = principalIdGetter.invoke(principalObject);
			if(idObj == null){
				throw new PrincipalIdNullException(principalObject.getClass(), getPrincipalIdFieldName());
			}
			redisKey = idObj.toString();
		}catch(IllegalAccessException e){
			throw new PrincipalInstanceException(principalObject.getClass(), getPrincipalIdFieldName(), e);
		}catch(InvocationTargetException e){
			throw new PrincipalInstanceException(principalObject.getClass(), getPrincipalIdFieldName(), e);
		}
		return redisKey;
	}

	private Method getPrincipalIdGetter(Object principalObject){
		Method principalIdGetter;
		String principalIdMethodName = getPrincipalIdMethodName();

		try{
			principalIdGetter = principalObject.getClass().getMethod(principalIdMethodName);
		}catch(NoSuchMethodException e){
			throw new PrincipalInstanceException(principalObject.getClass(), getPrincipalIdFieldName());
		}

		return principalIdGetter;
	}

	private String getPrincipalIdMethodName(){
		String principalIdFieldName = getPrincipalIdFieldName();

		if(Validate.hasText(principalIdFieldName) == false){
			throw new CacheManagerPrincipalIdNotAssignedException();
		}

		StringBuilder sb = new StringBuilder(principalIdFieldName.length() + 3);

		sb.append("get");
		sb.append(principalIdFieldName.substring(0, 1).toUpperCase()).append(principalIdFieldName.substring(1));

		return sb.toString();
	}

}