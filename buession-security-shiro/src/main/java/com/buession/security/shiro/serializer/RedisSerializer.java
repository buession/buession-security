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
package com.buession.security.shiro.serializer;

import com.buession.core.deserializer.DeserializerException;
import com.buession.core.serializer.SerializerException;

/**
 * Redis 序列化和反序列化
 *
 * @param <V>
 * 		对象类型引用
 *
 * @author Yong.Teng
 */
public interface RedisSerializer<V> {

	/**
	 * 将类型为 V 的对象序列化为字节数组
	 *
	 * @param v
	 * 		对象
	 *
	 * @return byte 数组
	 *
	 * @throws SerializerException
	 * 		序列化异常
	 */
	byte[] serialize(V v) throws SerializerException;

	/**
	 * 将字节数组反序列化为 V 对象
	 *
	 * @param bytes
	 * 		字节数组
	 *
	 * @return V 对象
	 *
	 * @throws DeserializerException
	 * 		反序列化异常
	 */
	V deserialize(byte[] bytes) throws DeserializerException;

}
