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

import com.buession.security.pac4j.context.ShiroSessionStore;
import com.buession.security.pac4j.profile.ShiroProfileManager;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.engine.DefaultLogoutLogic;
import org.pac4j.core.engine.LogoutLogic;
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
public class LogoutFilter extends AbstractPac4jFilter {

    private String defaultUrl;

    private String logoutUrlPattern;

    private Boolean localLogout;

    private Boolean centralLogout;

    private LogoutLogic<Object, JEEContext> logoutLogic;

    public LogoutFilter(){
        logoutLogic = new DefaultLogoutLogic<>();
        ((DefaultLogoutLogic<Object, JEEContext>) logoutLogic).setProfileManagerFactory(ShiroProfileManager::new);
    }

    public LogoutFilter(Config config){
        super(config);
        logoutLogic = new DefaultLogoutLogic<>();
        ((DefaultLogoutLogic<Object, JEEContext>) logoutLogic).setProfileManagerFactory(ShiroProfileManager::new);
    }

    public String getDefaultUrl(){
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl){
        this.defaultUrl = defaultUrl;
    }

    public String getLogoutUrlPattern(){
        return logoutUrlPattern;
    }

    public void setLogoutUrlPattern(String logoutUrlPattern){
        this.logoutUrlPattern = logoutUrlPattern;
    }

    public Boolean getLocalLogout(){
        return localLogout;
    }

    public void setLocalLogout(Boolean localLogout){
        this.localLogout = localLogout;
    }

    public Boolean getCentralLogout(){
        return centralLogout;
    }

    public void setCentralLogout(Boolean centralLogout){
        this.centralLogout = centralLogout;
    }

    public LogoutLogic<Object, JEEContext> getLogoutLogic(){
        return logoutLogic;
    }

    public void setLogoutLogic(LogoutLogic<Object, JEEContext> logoutLogic){
        this.logoutLogic = logoutLogic;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final
    FilterChain filterChain) throws IOException, ServletException{
        CommonHelper.assertNotNull("logoutLogic", logoutLogic);
        CommonHelper.assertNotNull("config", getConfig());

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final SessionStore<JEEContext> sessionStore = getConfig().getSessionStore();
        final JEEContext context = new JEEContext(request, response, sessionStore != null ? sessionStore :
                ShiroSessionStore.INSTANCE);

        logoutLogic.perform(context, getConfig(), JEEHttpActionAdapter.INSTANCE, getDefaultUrl(), getLogoutUrlPattern
                (), getLocalLogout(), false, getCentralLogout());
    }

}
