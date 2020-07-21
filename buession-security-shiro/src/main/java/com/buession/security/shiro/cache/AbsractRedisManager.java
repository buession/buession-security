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
import com.buession.lang.Status;
import com.buession.redis.Constants;
import com.buession.redis.RedisTemplate;
import com.buession.redis.client.RedisClient;
import com.buession.redis.core.ScanResult;
import com.buession.redis.exception.NotSupportedCommandException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbsractRedisManager implements RedisManager {

	protected final static int DEFAULT_RETURN_KEYS_COUNT = 100;

	private int returnKeysCount = DEFAULT_RETURN_KEYS_COUNT;

	private RedisTemplate redisTemplate;

	public AbsractRedisManager(){
	}

	public AbsractRedisManager(RedisTemplate redisTemplate){
		setRedisTemplate(redisTemplate);
	}

	public RedisTemplate getRedisTemplate(){
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate){
		Assert.isNull(redisTemplate, "RedisTemplate could not be null.");
		this.redisTemplate = redisTemplate;
	}

	public int getReturnKeysCount(){
		return returnKeysCount;
	}

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
		Status result = expire == 0 ? redisTemplate.set(key, value) : redisTemplate.setEx(key, value, expire);
		return result == Status.SUCCESS ? value : null;
	}

	@Override
	public byte[] get(byte[] key){
		return redisTemplate.get(key);
	}

	@Override
	public Status delete(byte[] key){
		return redisTemplate.del(key);
	}

	@Override
	public Status delete(byte[]... keys){
		return Status.valueOf(redisTemplate.del(keys) > 0);
	}

	@Override
	public Long dbSize(){
		RedisClient redisClient = redisTemplate.getClient();

		try{
			return redisClient.dbSize();
		}catch(NotSupportedCommandException e){
			return null;
		}
	}

}
