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
package com.buession.security.pac4j.filter;

import com.buession.security.pac4j.engine.ShiroCallbackLogic;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.engine.CallbackLogic;
import org.pac4j.core.http.adapter.HttpActionAdapter;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.pac4j.core.util.CommonHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class CallbackFilter extends AbstractPac4jFilter {

    private String defaultUrl;

    private Boolean saveInSession;

    private Boolean multiProfile;

    private String defaultClient;

    private CallbackLogic<Object, JEEContext> callbackLogic;

    private HttpActionAdapter<Object, JEEContext> httpActionAdapter;

    public CallbackFilter(){
        callbackLogic = new ShiroCallbackLogic<>();
    }

    public CallbackFilter(Config config){
        super(config);
        callbackLogic = new ShiroCallbackLogic<>();
    }

    public String getDefaultUrl(){
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl){
        this.defaultUrl = defaultUrl;
    }

    public Boolean getSaveInSession(){
        return saveInSession;
    }

    public void setSaveInSession(Boolean saveInSession){
        this.saveInSession = saveInSession;
    }

    public Boolean getMultiProfile(){
        return multiProfile;
    }

    public void setMultiProfile(Boolean multiProfile){
        this.multiProfile = multiProfile;
    }

    public String getDefaultClient(){
        return defaultClient;
    }

    public void setDefaultClient(String defaultClient){
        this.defaultClient = defaultClient;
    }

    public CallbackLogic<Object, JEEContext> getCallbackLogic(){
        return callbackLogic;
    }

    public void setCallbackLogic(CallbackLogic<Object, JEEContext> callbackLogic){
        this.callbackLogic = callbackLogic;
    }

    public HttpActionAdapter<Object, JEEContext> getHttpActionAdapter(){
        return httpActionAdapter;
    }

    public void setHttpActionAdapter(HttpActionAdapter<Object, JEEContext> httpActionAdapter){
        this.httpActionAdapter = httpActionAdapter;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final
    FilterChain filterChain) throws IOException, ServletException{
        CommonHelper.assertNotNull("callbackLogic", callbackLogic);
        CommonHelper.assertNotNull("config", getConfig());

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final SessionStore<JEEContext> sessionStore = getSessionStore();
        final JEEContext context = new JEEContext(request, response, sessionStore);
        final HttpActionAdapter<Object, JEEContext> adapter = httpActionAdapter != null ? httpActionAdapter :
                JEEHttpActionAdapter.INSTANCE;

        callbackLogic.perform(context, getConfig(), adapter, getDefaultUrl(), getSaveInSession(), getMultiProfile(),
                false, getDefaultClient());
    }

}
