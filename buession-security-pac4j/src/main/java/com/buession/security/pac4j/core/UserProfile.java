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
package com.buession.security.pac4j.core;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yong.Teng
 */
public class UserProfile<I> implements Serializable {

    private static final long serialVersionUID = -4531769744819093960L;

    private I id;

    private String username;

    private String nickname;

    private String realName;

    private String email;

    private String mobile;

    private String avatar;

    private String password;

    private String salt;

    private Date createdAt;

    private String createdIp;

    private Date loginTime;

    private String loginIp;

    private Date lastLoginTime;

    private String lastLoginIp;

    private int loginTimes;

    public I getId(){
        return id;
    }

    public void setId(final I id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(final String username){
        this.username = username;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(final String nickname){
        this.nickname = nickname;
    }

    public String getRealName(){
        return realName;
    }

    public void setRealName(final String realName){
        this.realName = realName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(final String email){
        this.email = email;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(final String mobile){
        this.mobile = mobile;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setAvatar(final String avatar){
        this.avatar = avatar;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(final String password){
        this.password = password;
    }

    public String getSalt(){
        return salt;
    }

    public void setSalt(final String salt){
        this.salt = salt;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt){
        this.createdAt = createdAt;
    }

    public String getCreatedIp(){
        return createdIp;
    }

    public void setCreatedIp(final String createdIp){
        this.createdIp = createdIp;
    }

    public Date getLoginTime(){
        return loginTime;
    }

    public void setLoginTime(final Date loginTime){
        this.loginTime = loginTime;
    }

    public String getLoginIp(){
        return loginIp;
    }

    public void setLoginIp(final String loginIp){
        this.loginIp = loginIp;
    }

    public Date getLastLoginTime(){
        return lastLoginTime;
    }

    public void setLastLoginTime(final Date lastLoginTime){
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp(){
        return lastLoginIp;
    }

    public void setLastLoginIp(final String lastLoginIp){
        this.lastLoginIp = lastLoginIp;
    }

    public int getLoginTimes(){
        return loginTimes;
    }

    public void setLoginTimes(final int loginTimes){
        this.loginTimes = loginTimes;
    }
}
