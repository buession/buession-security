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
package com.buession.security.shiro;

import com.buession.core.utils.Assert;
import com.buession.core.utils.StatusUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.RedisTemplate;
import com.buession.redis.core.Constants;
import com.buession.redis.core.ScanResult;
import com.buession.redis.exception.NotSupportedCommandException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Redis 管理器抽象类
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public abstract class AbstractRedisManager implements RedisManager {

	/**
	 * 默认返回 Key 数量
	 */
	protected final static int DEFAULT_RETURN_KEYS_COUNT = 100;

	/**
	 * 返回 Key 数量
	 */
	private int returnKeysCount = DEFAULT_RETURN_KEYS_COUNT;

	private RedisTemplate redisTemplate;

	/**
	 * 构造函数
	 */
	public AbstractRedisManager(){
	}

	/**
	 * 构造函数
	 *
	 * @param redisTemplate
	 *        {@link RedisTemplate}
	 */
	public AbstractRedisManager(RedisTemplate redisTemplate){
		setRedisTemplate(redisTemplate);
	}

	/**
	 * 返回 {@link RedisTemplate}
	 *
	 * @return {@link RedisTemplate}
	 */
	public RedisTemplate getRedisTemplate(){
		return redisTemplate;
	}

	/**
	 * 设置 {@link RedisTemplate}
	 *
	 * @param redisTemplate
	 *        {@link RedisTemplate}
	 */
	public void setRedisTemplate(RedisTemplate redisTemplate){
		Assert.isNull(redisTemplate, "RedisTemplate could not be null.");
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 返回返回 Key 数量
	 *
	 * @return 返回 Key 数量
	 */
	public int getReturnKeysCount(){
		return returnKeysCount;
	}

	/**
	 * 设置返回 Key 数量
	 *
	 * @param returnKeysCount
	 * 		返回 Key 数量
	 */
	public void setReturnKeysCount(int returnKeysCount){
		this.returnKeysCount = returnKeysCount;
	}

	@Override
	public Set<byte[]> keys(byte[] pattern){
		Set<byte[]> keys = new HashSet<>();

		byte[] cursor = Constants.SCAN_POINTER_START_BINARY;
		ScanResult<List<byte[]>> scanResult;

		do{
			scanResult = redisTemplate.scan(cursor, returnKeysCount);

			if(Validate.isNotEmpty(scanResult.getResults())){
				keys.addAll(scanResult.getResults());
			}

			cursor = scanResult.getCursor();
		}while(scanResult.getCursorAsString().compareTo(Constants.SCAN_POINTER_START) > 0);

		return keys;
	}

	@Override
	public byte[] set(byte[] key, byte[] value, int expire){
		if(redisTemplate.setEx(key, value, expire == 0 ? -1 : expire) == Status.SUCCESS){
			return value;
		}else{
			return null;
		}
	}

	@Override
	public byte[] get(byte[] key){
		return redisTemplate.get(key);
	}

	@Override
	public Status delete(byte[] key){
		Long result = redisTemplate.del(key);
		return result == null ? Status.FAILURE : StatusUtils.valueOf(result);
	}

	@Override
	public Status delete(byte[]... keys){
		Long result = redisTemplate.del(keys);
		return result == null ? Status.FAILURE : StatusUtils.valueOf(result);
	}

	@Override
	public Long dbSize(){
		try{
			return redisTemplate.dbSize();
		}catch(NotSupportedCommandException e){
			return null;
		}
	}

}
