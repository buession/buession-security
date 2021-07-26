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

import com.buession.core.validator.Validate;
import org.pac4j.core.profile.BasicUserProfile;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileHelper;
import org.pac4j.core.util.CommonHelper;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Yong.Teng
 */
public class Pac4jPrincipal implements Principal, Serializable {

	private final static long serialVersionUID = 96494800982373373L;

	private final String principalNameAttribute;

	private final List<CommonProfile> profiles;

	public Pac4jPrincipal(final List<CommonProfile> profiles){
		this.principalNameAttribute = null;
		this.profiles = profiles;
	}

	public Pac4jPrincipal(final List<CommonProfile> profiles, String principalNameAttribute){
		this.principalNameAttribute = Validate.hasText(principalNameAttribute) ? principalNameAttribute.trim() : null;
		this.profiles = profiles;
	}

	@Override
	public String getName(){
		Optional<CommonProfile> profile = getProfile();
		if(principalNameAttribute == null){
			return profile.map(BasicUserProfile::getId).orElse(null);
		}

		Object value = profile.map(commonProfile->commonProfile.getAttribute(principalNameAttribute)).orElse(null);
		return null == value ? null : String.valueOf(value);
	}

	public Optional<CommonProfile> getProfile(){
		return ProfileHelper.flatIntoOneProfile(profiles);
	}

	public Optional<List<CommonProfile>> getProfiles(){
		return Optional.of(profiles);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Pac4jPrincipal){
			final Pac4jPrincipal that = (Pac4jPrincipal) obj;
			return Objects.deepEquals(profiles, that.profiles);
		}

		return false;
	}

	@Override
	public int hashCode(){
		return Objects.hashCode(profiles);
	}

	@Override
	public String toString(){
		return CommonHelper.toNiceString(getClass(), "profiles", getProfiles());
	}

}
