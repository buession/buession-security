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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.security.shiro.session;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 1.2.2
 */
class MemorySessionDAO {

	private final long sessionTimeout;

	private static ThreadLocal<Map<Serializable, MemorySession>> sessionsInThread = new ThreadLocal<>();

	private final static Logger logger = LoggerFactory.getLogger(MemorySessionDAO.class);

	public MemorySessionDAO(final long sessionTimeout){
		this.sessionTimeout = sessionTimeout;
	}

	public void save(final Session session){
		if(logger.isDebugEnabled()){
			logger.debug("Save session: {} in memory.", session);
		}

		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			sessionMap = new HashMap<>(32, 0.8F);
			sessionsInThread.set(sessionMap);
		}

		sessionMap.put(session.getId(), createMemorySession(session));
	}

	public Session read(final Serializable sessionId){
		if(logger.isDebugEnabled()){
			logger.debug("read session: [{}] from memory.", sessionId);
		}
		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			return null;
		}

		MemorySession memorySession = sessionMap.get(sessionId);
		if(memorySession == null){
			return null;
		}

		long liveTime = getMemorySessionLiveTime(memorySession);
		if(liveTime > sessionTimeout){
			sessionMap.remove(sessionId);
			return null;
		}

		Session session = memorySession.getSession();
		logger.debug("read session from memory is ok.");

		return session;
	}

	public void clearExpiredSession(){
		logger.debug("Clean expired session in memory.");
		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();
		if(sessionMap == null){
			return;
		}

		Iterator<Serializable> iterator = sessionMap.keySet().iterator();

		while(iterator.hasNext()){
			Serializable sessionId = iterator.next();
			MemorySession memorySession = sessionMap.get(sessionId);

			if(memorySession == null){
				iterator.remove();
				continue;
			}

			long liveTime = getMemorySessionLiveTime(memorySession);
			if(liveTime > sessionTimeout){
				iterator.remove();
			}
		}

		if(sessionMap.size() == 0){
			sessionsInThread.remove();
		}
	}

	protected static MemorySession createMemorySession(final Session session){
		return new MemorySession(session, new Date());
	}

	protected static long getMemorySessionLiveTime(MemorySession memorySession){
		return System.currentTimeMillis() - memorySession.getCreateTime().getTime();
	}

}
