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
package com.buession.security.shiro.session;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

/**
 * Memory Data Access Object design pattern specification to enable {@link Session} access to an EIS (Enterprise Information System).
 *
 * @author Yong.Teng
 * @since 1.2.2
 */
class MemorySessionDAO extends org.apache.shiro.session.mgt.eis.MemorySessionDAO {

	private final long sessionTimeout;

	private final static Logger logger = LoggerFactory.getLogger(MemorySessionDAO.class);

	public MemorySessionDAO(final long sessionTimeout) {
		super();
		this.sessionTimeout = sessionTimeout;
	}

	@Override
	protected Serializable doCreate(Session session) {
		session.setTimeout(sessionTimeout);
		return super.doCreate(session);
	}

	public void clearExpiredSession() {
		logger.debug("Clean expired session in memory.");
		Collection<Session> activeSessions = getActiveSessions();
		if(activeSessions == null){
			return;
		}
		
		for(Session session : activeSessions){
			if(System.currentTimeMillis() - session.getStartTimestamp().getTime() >= session.getTimeout()){
				delete(session);
			}
		}
	}

}
