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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.shiro;

import com.buession.lang.Status;

import java.util.Set;

/**
 * Redis 管理器
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
public interface RedisManager {

	/**
	 * 根据模式获取所有 Key
	 *
	 * @param pattern
	 * 		模式
	 *
	 * @return 根据模式查询到的所有 Key
	 */
	Set<byte[]> keys(byte[] pattern);

	/**
	 * 将值 value 关联到 key，并返回 value
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		值
	 * @param expire
	 * 		过期时间
	 *
	 * @return value
	 */
	byte[] set(byte[] key, byte[] value, int expire);

	/**
	 * 获取 Key 的值
	 *
	 * @param key
	 * 		Key
	 *
	 * @return Key 的值
	 */
	byte[] get(byte[] key);

	/**
	 * 删除 Key
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status delete(byte[] key);

	/**
	 * 批量删除 Key
	 *
	 * @param keys
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status delete(byte[]... keys);

	/**
	 * 获取 db 大小
	 *
	 * @return db 大小
	 */
	Long dbSize();

}
