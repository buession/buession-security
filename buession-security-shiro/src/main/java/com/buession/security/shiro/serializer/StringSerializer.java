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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 */
public class StringSerializer implements RedisSerializer<String> {

	private Charset charset = StandardCharsets.UTF_8;

	public Charset getCharset(){
		return charset;
	}

	public void setCharset(Charset charset){
		this.charset = charset;
	}

	@Override
	public byte[] serialize(String v) throws SerializerException{
		return v == null ? null : v.getBytes(getCharset());
	}

	@Override
	public String deserialize(byte[] bytes) throws DeserializerException{
		return bytes == null ? null : new String(bytes, getCharset());
	}

}
