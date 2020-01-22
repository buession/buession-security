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
package com.buession.security.shiro;

import com.buession.security.shiro.serializer.ObjectSerializer;
import com.buession.security.shiro.serializer.StringSerializer;

/**
 * @author Yong.Teng
 */
public class Constants {

	public final static String DEFAULT_KEY_PREFIX = "buession_shiro_redis_session:";

	public final static int DEFAULT_EXPIRE = 1800;

	public final static int DEFAULT_TIMEOUT = 1000;

	public final static long DEFAULT_SESSION_IN_MEMORY_TIMEOUT = 1000L;

	public final static int MILLISECONDS_IN_A_SECOND_TIMEOUT = 1000;

	public final static String ALL_PERMISSION = "*:*";

	public final static StringSerializer KEY_SERIALIZER = new StringSerializer();

	public final static ObjectSerializer VALUE_SERIALIZER = new ObjectSerializer();

}
