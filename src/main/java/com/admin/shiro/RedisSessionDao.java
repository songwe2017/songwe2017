package com.admin.shiro;

import com.admin.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * redis 全局会话管理，当然也可以通过 spring session 来做，但是既然用了 shiro，还是用这种方式更合理
 * @author Songwe
 * @since 2022/6/3 1:26
 */
@Slf4j
public class RedisSessionDao extends CachingSessionDAO {

    @Override
    protected void doUpdate(Session session) {
        log.debug("Update session cache,new session:{}", JsonUtils.toJson(session));
        this.getActiveSessionsCache().put(session.getId(), session);
    }

    @Override
    protected void doDelete(Session session) {
        log.debug("Delete session cache, sessionId[{}]", session.getId());
        this.getActiveSessionsCache().remove(session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        log.debug("Create session cache, session:{}", JsonUtils.toJson(session));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.debug("Read session from cache, sessionId[{}]", sessionId);
        return this.getActiveSessionsCache().get(sessionId); 
    }
}
