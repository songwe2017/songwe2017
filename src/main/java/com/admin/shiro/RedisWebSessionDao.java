package com.admin.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * redis 全局会话管理，当然也可以通过 spring session 来做，但是既然用了 shiro，还是用这种方式更合理
 * @author Songwe
 * @since 2022/6/3 1:26
 */
public class RedisWebSessionDao extends AbstractSessionDAO {
    @Override
    protected Serializable doCreate(Session session) {
        // 生成唯一sessionId
        Serializable sessionId = generateSessionId(session);
        // 为 session 指定全局唯一ID
        assignSessionId(session, sessionId);
        // todo 放到 redis 缓存
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
