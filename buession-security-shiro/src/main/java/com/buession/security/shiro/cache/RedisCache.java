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
package com.buession.security.shiro.cache;

import com.buession.beans.PropertyDescriptorUtils;
import com.buession.core.serializer.SerializerException;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.security.shiro.RedisManager;
import com.buession.security.shiro.exception.CacheManagerPrincipalIdNotAssignedException;
import com.buession.security.shiro.exception.PrincipalIdNullException;
import com.buession.security.shiro.exception.PrincipalInstanceException;
import com.buession.security.shiro.serializer.ObjectSerializer;
import com.buession.security.shiro.serializer.RedisSerializer;
import com.buession.security.shiro.serializer.StringSerializer;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Redis 缓存
 *
 * @author Yong.Teng
 */
public class RedisCache<K, V> extends AbstractCache<K, V> {

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
	private RedisSerializer<Object> valueSerializer = new ObjectSerializer<>();

	private final static Logger logger = LoggerFactory.getLogger(RedisCache.class);

	/**
	 * 构造函数
	 */
	public RedisCache(){
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 */
	public RedisCache(String keyPrefix, int expire){
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
	public RedisCache(String keyPrefix, int expire, String principalIdFieldName){
		super(keyPrefix, expire, principalIdFieldName);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 */
	public RedisCache(RedisManager redisManager, String keyPrefix, int expire){
		this(keyPrefix, expire);
		setRedisManager(redisManager);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 * @param principalIdFieldName
	 * 		身份信息 ID 字段名称
	 */
	public RedisCache(RedisManager redisManager, String keyPrefix, int expire, String principalIdFieldName){
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
	public RedisCache(RedisSerializer<String> keySerializer, RedisSerializer<Object> valueSerializer){
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
	public RedisCache(String keyPrefix, int expire, RedisSerializer<String> keySerializer,
					  RedisSerializer<Object> valueSerializer){
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
	 * 		Principal Id 字段名称
	 * @param keySerializer
	 * 		Key 序列化对象
	 * @param valueSerializer
	 * 		值序列化对象
	 *
	 * @since 1.2.2
	 */
	public RedisCache(String keyPrefix, int expire, String principalIdFieldName, RedisSerializer<String> keySerializer
			, RedisSerializer<Object> valueSerializer){
		super(keyPrefix, expire, principalIdFieldName);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器
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
	public RedisCache(RedisManager redisManager, String keyPrefix, int expire, RedisSerializer<String> keySerializer,
					  RedisSerializer<Object> valueSerializer){
		this(keyPrefix, expire);
		setRedisManager(redisManager);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	/**
	 * 构造函数
	 *
	 * @param redisManager
	 * 		Redis 管理器
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
	public RedisCache(RedisManager redisManager, String keyPrefix, int expire, String principalIdFieldName,
					  RedisSerializer<String> keySerializer, RedisSerializer<Object> valueSerializer){
		this(keyPrefix, expire, principalIdFieldName);
		setRedisManager(redisManager);
		setKeySerializer(keySerializer);
		setValueSerializer(valueSerializer);
	}

	public RedisManager getRedisManager(){
		return redisManager;
	}

	public void setRedisManager(final RedisManager redisManager){
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
	public RedisSerializer<String> getKeySerializer(){
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
	public void setKeySerializer(RedisSerializer<String> keySerializer){
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
	public RedisSerializer<Object> getValueSerializer(){
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
	public void setValueSerializer(RedisSerializer<Object> valueSerializer){
		Assert.isNull(valueSerializer, "Value serializer could not be null.");
		this.valueSerializer = valueSerializer;
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
					newKeys.add((K) keySerializer.deserialize(key));
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
			byte[] rawValue = redisManager.get(makeKey(key));

			if(rawValue == null){
				return null;
			}

			return (V) valueSerializer.deserialize(rawValue);
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
			byte[] rawValue = valueSerializer.serialize(value);

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
				V value = (V) valueSerializer.deserialize(redisManager.get(key));
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

		String redisKey = keySerializer instanceof StringSerializer ? getStringRedisKey(key) : key.toString();
		return makeKey(redisKey);
	}

	protected final byte[] makeKey(String key) throws SerializerException{
		if(key == null){
			return null;
		}

		StringBuilder sb = new StringBuilder(getKeyPrefix() == null ? key.length() :
				getKeyPrefix().length() + key.length());

		if(Validate.isNotEmpty(getKeyPrefix())){
			sb.append(getKeyPrefix());
		}

		sb.append(key);

		return keySerializer.serialize(sb.toString());
	}

	protected String getStringRedisKey(K key){
		return key instanceof PrincipalCollection ? getRedisKeyFromPrincipalCollection((PrincipalCollection) key) :
				key.toString();
	}

	protected String getRedisKeyFromPrincipalCollection(PrincipalCollection principalCollection){
		Object principalObject = principalCollection.getPrimaryPrincipal();

		if(principalObject instanceof String){
			return principalObject.toString();
		}

		try{
			Method principalIdGetter = getPrincipalIdGetter(principalObject);

			if(principalIdGetter == null){
				throw new CacheManagerPrincipalIdNotAssignedException();
			}

			Object idObj = principalIdGetter.invoke(principalObject);

			if(idObj == null){
				throw new PrincipalIdNullException(principalObject.getClass(), getPrincipalIdFieldName());
			}

			return idObj.toString();
		}catch(IntrospectionException e){
			throw new PrincipalInstanceException(principalObject.getClass(), getPrincipalIdFieldName(), e);
		}catch(InvocationTargetException e){
			throw new PrincipalInstanceException(principalObject.getClass(), getPrincipalIdFieldName(), e);
		}catch(IllegalAccessException e){
			throw new PrincipalInstanceException(principalObject.getClass(), getPrincipalIdFieldName(), e);
		}
	}

	private Method getPrincipalIdGetter(Object principalObject) throws IntrospectionException{
		BeanInfo bi = Introspector.getBeanInfo(principalObject.getClass());
		PropertyDescriptor[] propertyDescriptors = bi.getPropertyDescriptors();

		for(PropertyDescriptor pd : propertyDescriptors){
			if(getPrincipalIdFieldName().equals(pd.getName())){
				return PropertyDescriptorUtils.getReadMethod(pd);
			}
		}

		return null;
	}

}