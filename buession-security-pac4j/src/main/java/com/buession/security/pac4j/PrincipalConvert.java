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
package com.buession.security.pac4j;

import com.buession.security.pac4j.core.UserProfile;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.pac4j.core.profile.CommonProfile;

import java.security.Principal;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class PrincipalConvert<I> {

    public UserProfile<I> convert(Principal principal){
        if(principal == null){
            return null;
        }

        if(!(principal instanceof Pac4jPrincipal)){
            return null;
        }

        Pac4jPrincipal pac4jPrincipal = (Pac4jPrincipal) principal;

        CommonProfile profile = pac4jPrincipal.getProfile();

        if(profile == null){
            return null;
        }

        final UserProfile<I> user = new UserProfile<>();

        Object id = profile.getAttribute("id");
        if(id != null){
            user.setId((I) id);
        }

        Object email = profile.getAttribute("email");
        if(email != null){
            user.setEmail((String) email);
        }

        Object password = profile.getAttribute("password");
        if(password != null){
            user.setPassword((String) password);
        }

        Object salt = profile.getAttribute("salt");
        if(salt != null){
            user.setSalt((String) salt);
        }

        Object realName = profile.getAttribute("realName");
        if(realName != null){
            user.setRealName((String) realName);
        }

        Object mobile = profile.getAttribute("mobile");
        if(mobile != null){
            user.setMobile((String) mobile);
        }

        Object avatar = profile.getAttribute("avatar");
        if(avatar != null){
            user.setAvatar((String) avatar);
        }

        Object createdAt = profile.getAttribute("createdAt");
        if(createdAt != null){
            user.setCreatedAt((Date) createdAt);
        }

        Object createdIp = profile.getAttribute("createdIp");
        if(createdIp != null){
            user.setCreatedIp((String) createdIp);
        }

        Object loginTimes = profile.getAttribute("loginTimes");
        if(loginTimes != null){
            user.setLoginTimes((Short) loginTimes);
        }

        Object loginTime = profile.getAttribute("loginTime");
        if(loginTime != null){
            user.setLoginTime((Date) loginTime);
        }

        Object loginIp = profile.getAttribute("loginIp");
        if(loginIp != null){
            user.setLoginIp((String) loginIp);
        }

        Object lastLoginTime = profile.getAttribute("lastLoginTime");
        if(lastLoginTime != null){
            user.setLoginTime((Date) lastLoginTime);
        }

        Object lastLoginIp = profile.getAttribute("lastLoginIp");
        if(lastLoginIp != null){
            user.setLastLoginIp((String) lastLoginIp);
        }

        return user;
    }

}
