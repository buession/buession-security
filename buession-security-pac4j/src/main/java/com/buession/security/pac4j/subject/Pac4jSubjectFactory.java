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
package com.buession.security.pac4j.subject;

import com.buession.security.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public class Pac4jSubjectFactory extends DefaultWebSubjectFactory {

	private final static Logger logger = LoggerFactory.getLogger(Pac4jSubjectFactory.class);

	@Override
	public Subject createSubject(SubjectContext context){
		if(logger.isInfoEnabled()){
			logger.info("Create subject by: {}", context);
		}
		if(context.isAuthenticated()){
			logger.info("Subject context is authenticated");
			AuthenticationToken token = context.getAuthenticationToken();

			if(token instanceof Pac4jToken){
				logger.info("Authentication token instanceof {}", Pac4jToken.class.getName());
				final Pac4jToken clientToken = (Pac4jToken) token;
				if(clientToken.isRememberMe()){
					logger.info("Token is remember me");
					context.setAuthenticated(false);
				}
			}
		}

		return super.createSubject(context);
	}

}
