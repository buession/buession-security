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
package com.liangvi.security.geetest.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yong.Teng
 */
public class ProcessResult {

    private boolean success;

    private String gt;

    private String challenge;

    @JsonProperty(value = "new_captcha")
    private Boolean newCaptcha;

    @JsonProperty(value = "session_id")
    private String sessionId;

    public boolean getSuccess(){
        return success;
    }

    public boolean isSuccess(){
        return getSuccess();
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public String getGt(){
        return gt;
    }

    public void setGt(String gt){
        this.gt = gt;
    }

    public String getChallenge(){
        return challenge;
    }

    public void setChallenge(String challenge){
        this.challenge = challenge;
    }

    @JsonProperty(value = "new_captcha")
    public Boolean getNewCaptcha(){
        return newCaptcha;
    }

    @JsonProperty(value = "new_captcha")
    public void setNewCaptcha(Boolean newCaptcha){
        this.newCaptcha = newCaptcha;
    }

    @JsonProperty(value = "session_id")
    public String getSessionId(){
        return sessionId;
    }

    @JsonProperty(value = "session_id")
    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

}
