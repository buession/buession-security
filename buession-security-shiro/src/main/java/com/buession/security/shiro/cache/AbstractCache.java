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

import java.util.StringJoiner;

/**
 * 缓存抽象类
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @see org.apache.shiro.cache.Cache
 */
public abstract class AbstractCache<K, V> implements Cache<K, V>, org.apache.shiro.cache.Cache<K, V> {

	/**
	 * Key 前缀
	 */
	private String keyPrefix = AbstractCacheManager.DEFAULT_KEY_PREFIX;

	/**
	 * 有效期（单位：秒）
	 */
	private int expire = AbstractCacheManager.DEFAULT_EXPIRE;

	/**
	 * 身份信息 ID 字段名称
	 */
	private String principalIdFieldName = AbstractCacheManager.DEFAULT_PRINCIPAL_ID_FIELD_NAME;

	/**
	 * 构造函数
	 */
	public AbstractCache(){
	}

	/**
	 * 构造函数
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 * @param expire
	 * 		有效期（单位：秒）
	 */
	public AbstractCache(String keyPrefix, int expire){
		this.keyPrefix = keyPrefix;
		this.expire = expire;
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
	public AbstractCache(String keyPrefix, int expire, String principalIdFieldName){
		this(keyPrefix, expire);
		setPrincipalIdFieldName(principalIdFieldName);
	}

	/**
	 * 返回 Key 前缀
	 *
	 * @return Key 前缀
	 */
	public String getKeyPrefix(){
		return keyPrefix;
	}

	/**
	 * 设置 Key 前缀
	 *
	 * @param keyPrefix
	 * 		Key 前缀
	 */
	public void setKeyPrefix(String keyPrefix){
		this.keyPrefix = keyPrefix;
	}

	/**
	 * 返回有效期（单位：秒）
	 *
	 * @return 有效期
	 */
	public int getExpire(){
		return expire;
	}

	/**
	 * 设置有效期
	 *
	 * @param expire
	 * 		有效期（单位：秒）
	 */
	public void setExpire(int expire){
		this.expire = expire;
	}

	/**
	 * 返回身份信息 ID 字段名称
	 *
	 * @return 身份信息 ID 字段名称
	 */
	public String getPrincipalIdFieldName(){
		return principalIdFieldName;
	}

	/**
	 * 设置身份信息 ID 字段名称
	 *
	 * @param principalIdFieldName
	 * 		身份信息 ID 字段名称
	 */
	public void setPrincipalIdFieldName(String principalIdFieldName){
		this.principalIdFieldName = principalIdFieldName;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", getClass().getName() + "[", "]")
				.add("keyPrefix=" + keyPrefix)
				.add("expire=" + expire)
				.add("principalIdFieldName=" + principalIdFieldName)
				.toString();
	}

}
