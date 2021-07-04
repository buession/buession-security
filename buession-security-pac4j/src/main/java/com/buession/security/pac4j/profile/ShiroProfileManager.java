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
package com.buession.security.pac4j.profile;

import com.buession.core.utils.MapUtils;
import com.buession.core.validator.Validate;
import com.buession.security.pac4j.token.Pac4jToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.pac4j.core.authorization.authorizer.Authorizer;
import org.pac4j.core.authorization.authorizer.IsFullyAuthenticatedAuthorizer;
import org.pac4j.core.authorization.authorizer.IsRememberedAuthorizer;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.TechnicalException;
import org.pac4j.core.exception.http.HttpAction;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class ShiroProfileManager extends ProfileManager<CommonProfile> {

	private final static Authorizer<CommonProfile> IS_REMEMBERED_AUTHORIZER = new IsRememberedAuthorizer<>();

	private final static Authorizer<CommonProfile> IS_FULLY_AUTHENTICATED_AUTHORIZER =
			new IsFullyAuthenticatedAuthorizer<>();

	private final static Logger logger = LoggerFactory.getLogger(ShiroProfileManager.class);

	public ShiroProfileManager(final WebContext context){
		super(context);
	}

	@Override
	public void save(final boolean saveInSession, final CommonProfile profile, final boolean multiProfile){
		if(logger.isInfoEnabled()){
			logger.info("Save profile: {} with multi profile is {}", profile, multiProfile);
		}

		super.save(saveInSession, profile, multiProfile);

		try{
			populateSubject(retrieveAll(saveInSession));
		}catch(AuthenticationException e){
			super.remove(saveInSession);
			throw e;
		}
	}

	@Override
	public void remove(final boolean removeFromSession){
		super.remove(removeFromSession);
		SecurityUtils.getSubject().logout();
	}

	protected void populateSubject(final LinkedHashMap<String, CommonProfile> profiles){
		if(logger.isInfoEnabled()){
			logger.info("Populate subject: {}", profiles);
		}

		if(Validate.isEmpty(profiles)){
			return;
		}

		final List<CommonProfile> listProfiles = MapUtils.toList(profiles);
		final Subject subject = SecurityUtils.getSubject();

		try{
			if(IS_FULLY_AUTHENTICATED_AUTHORIZER.isAuthorized(context, listProfiles)){
				subject.login(new Pac4jToken(listProfiles, false));
			}else if(IS_REMEMBERED_AUTHORIZER.isAuthorized(context, listProfiles)){
				subject.login(new Pac4jToken(listProfiles, true));
			}
		}catch(HttpAction e){
			throw new TechnicalException(e);
		}
	}

}
