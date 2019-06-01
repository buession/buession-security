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
package com.buession.security.shiro.cache;

import com.buession.core.exception.SerializationException;
import com.buession.core.validator.Validate;
import com.buession.security.shiro.Constants;
import com.buession.security.shiro.serializer.ObjectSerializer;
import com.buession.security.shiro.serializer.StringSerializer;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private final static StringSerializer keySerializer = new StringSerializer();

    private final static ObjectSerializer valueSerializer = new ObjectSerializer();

    private RedisManager redisManager;

    private String keyPrefix = Constants.DEFAULT_KEY_PREFIX;

    private int expire = Constants.DEFAULT_EXPIRE;

    private long sessionInMemoryTimeout = Constants.DEFAULT_SESSION_IN_MEMORY_TIMEOUT;

    private static ThreadLocal<Map<Serializable, SessionInMemory>> sessionsInThread = new ThreadLocal<>();

    private final static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    public RedisSessionDAO(){
    }

    public RedisSessionDAO(RedisManager redisManager, String keyPrefix, int expire){
        setRedisManager(redisManager);
        this.keyPrefix = keyPrefix;
        this.expire = expire;
    }

    public RedisManager getRedisManager(){
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager){
        if(redisManager == null){
            throw new IllegalArgumentException("RedisManager could not be null.");
        }
        this.redisManager = redisManager;
    }

    public String getKeyPrefix(){
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix){
        this.keyPrefix = keyPrefix;
    }

    public int getExpire(){
        return expire;
    }

    public void setExpire(final int expire){
        this.expire = expire;
    }

    @Override
    public Collection<Session> getActiveSessions(){
        Set<Session> sessions = new HashSet<>();
        byte[] pattern;

        try{
            pattern = keySerializer.serialize(keyPrefix == null ? "*" : keyPrefix + "*");
            Set<byte[]> keys = redisManager.keys(pattern);

            if(Validate.isEmpty(keys) == false){
                for(byte[] key : keys){
                    Session session = (Session) valueSerializer.deserialize(redisManager.get(key));
                    sessions.add(session);
                }
            }
        }catch(SerializationException e){
            logger.error("get active sessions error.");
        }
        return sessions;
    }

    @Override
    public void update(Session session) throws UnknownSessionException{
        saveSession(session);
    }

    @Override
    public void delete(Session session){
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }

        try{
            redisManager.delete(getSessionKey(session.getId()));
        }catch(SerializationException e){
            logger.error("delete session error: {}. session id: {}", e.getMessage(), session.getId());
        }
    }

    @Override
    protected Serializable doCreate(Session session){
        if(session == null){
            logger.error("session is null");
            throw new UnknownSessionException("session is null");
        }

        Serializable sessionId = generateSessionId(session);

        assignSessionId(session, sessionId);
        saveSession(session);

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId){
        if(sessionId == null){
            logger.error("session id is null");
            return null;
        }

        Session session = getSessionFromThreadLocal(sessionId);

        if(session != null){
            return session;
        }

        logger.debug("Read session from redis");

        try{
            session = (Session) valueSerializer.deserialize(redisManager.get(getSessionKey(sessionId)));
            setSessionToThreadLocal(sessionId, session);
        }catch(SerializationException e){
            logger.error("read session error: {}. session id: {}", e.getMessage(), sessionId);
        }

        return session;
    }

    private void saveSession(Session session) throws UnknownSessionException{
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            throw new UnknownSessionException("session or session id is null");
        }

        if(expire * Constants.MILLISECONDS_IN_A_SECOND < session.getTimeout()){
            logger.warn("Redis session expire time: {} is less than Session timeout: {}. It may cause some problems"
                    + ".", expire * Constants.MILLISECONDS_IN_A_SECOND, session.getTimeout());
        }

        byte[] key;
        byte[] value;
        try{
            key = getSessionKey(session.getId());
            value = valueSerializer.serialize(session);
        }catch(SerializationException e){
            logger.error("serialize session error: {}. session id: {}", e.getMessage(), session.getId());
            throw new UnknownSessionException(e);
        }

        //session.setTimeout(expire * Constants.MILLISECONDS_IN_A_SECOND);
        redisManager.set(key, value, expire);
    }

    private Session getSessionFromThreadLocal(Serializable sessionId){
        Session s = null;

        if(sessionsInThread.get() == null){
            return null;
        }

        Map<Serializable, SessionInMemory> sessionMap = sessionsInThread.get();
        SessionInMemory sessionInMemory = sessionMap.get(sessionId);
        if(sessionInMemory == null){
            return null;
        }

        Date now = new Date();
        long duration = now.getTime() - sessionInMemory.getCreateTime().getTime();

        if(duration < sessionInMemoryTimeout){
            s = sessionInMemory.getSession();
            logger.debug("read session from memory");
        }else{
            sessionMap.remove(sessionId);
        }

        return s;
    }

    private void setSessionToThreadLocal(Serializable sessionId, Session session){
        Map<Serializable, SessionInMemory> sessionMap = sessionsInThread.get();
        if(sessionMap == null){
            sessionMap = new HashMap<>(16);
            sessionsInThread.set(sessionMap);
        }

        SessionInMemory sessionInMemory = new SessionInMemory();
        sessionInMemory.setCreateTime(new Date());
        sessionInMemory.setSession(session);

        sessionMap.put(sessionId, sessionInMemory);
    }

    private byte[] getSessionKey(Serializable sessionId) throws SerializationException{
        final String key = keyPrefix == null ? sessionId.toString() : keyPrefix + sessionId;
        return keySerializer.serialize(key);
    }

}