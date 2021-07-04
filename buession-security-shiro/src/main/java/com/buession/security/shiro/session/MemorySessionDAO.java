package com.buession.security.shiro.session;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 1.2.2
 */
class MemorySessionDAO {

	private final long sessionTimeout;

	private static ThreadLocal<Map<Serializable, MemorySession>> sessionsInThread = new ThreadLocal<>();

	private final static Logger logger = LoggerFactory.getLogger(MemorySessionDAO.class);

	public MemorySessionDAO(final long sessionTimeout){
		this.sessionTimeout = sessionTimeout;
	}

	public void save(final Session session){
		logger.debug("Save session: {} in memory.", session);

		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			sessionMap = new HashMap<>(32, 0.8F);
			sessionsInThread.set(sessionMap);
		}

		sessionMap.put(session.getId(), createMemorySession(session));
	}

	public Session read(final Serializable sessionId){
		logger.debug("read session from memory.");
		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();

		if(sessionMap == null){
			return null;
		}

		MemorySession memorySession = sessionMap.get(sessionId);
		if(memorySession == null){
			return null;
		}

		long liveTime = getMemorySessionLiveTime(memorySession);
		if(liveTime > sessionTimeout){
			sessionMap.remove(sessionId);
			return null;
		}

		Session session = memorySession.getSession();
		logger.debug("read session from memory is ok.");

		return session;
	}

	public void clearExpiredSession(){
		logger.debug("Clean expired session in memory.");
		Map<Serializable, MemorySession> sessionMap = sessionsInThread.get();
		if(sessionMap == null){
			return;
		}

		Iterator<Serializable> iterator = sessionMap.keySet().iterator();

		while(iterator.hasNext()){
			Serializable sessionId = iterator.next();
			MemorySession memorySession = sessionMap.get(sessionId);

			if(memorySession == null){
				iterator.remove();
				continue;
			}

			long liveTime = getMemorySessionLiveTime(memorySession);
			if(liveTime > sessionTimeout){
				iterator.remove();
			}
		}

		if(sessionMap.size() == 0){
			sessionsInThread.remove();
		}
	}

	protected final static MemorySession createMemorySession(final Session session){
		return new MemorySession(session, new Date());
	}

	protected final static long getMemorySessionLiveTime(MemorySession memorySession){
		return System.currentTimeMillis() - memorySession.getCreateTime().getTime();
	}

}
