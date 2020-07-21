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
package com.buession.security.pac4j.annotation;

import com.buession.security.pac4j.subject.Pac4jPrincipal;
import org.apache.shiro.SecurityUtils;
import org.pac4j.core.profile.CommonProfile;

import java.util.Optional;

/**
 * @author Yong.Teng
 */
public class ProfileUtils {

	private final static ThreadLocal<CommonProfile> PROFILES_CACHE = new ThreadLocal<>();

	public static CommonProfile getCurrent(){
		CommonProfile profile = PROFILES_CACHE.get();

		if(profile == null){
			Pac4jPrincipal principal = (Pac4jPrincipal) SecurityUtils.getSubject().getPrincipal();
			Optional<CommonProfile> optional = principal.getProfile();

			profile = optional.get();
			if(profile != null){
				PROFILES_CACHE.set(profile);
			}
		}

		return profile;
	}

}
