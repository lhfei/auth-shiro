/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jd.y.auth.serve.session.mysql;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jd.y.auth.serve.common.util.SerializableUtils;
import com.jd.y.auth.serve.orm.domain.AuthSession;
import com.jd.y.auth.serve.orm.mapper.SessionHandlerMapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 25, 2017
 */
@Service("mySqlCachingSessionMapper")
public class MySqlCachingSessionMapper extends CachingSessionDAO {

	private final String defaultActiveSessionsCacheName = "shiro-activeSessionCache";

	@Override
	public void setActiveSessionsCacheName(String activeSessionsCacheName) {
		setActiveSessionsCacheName(defaultActiveSessionsCacheName);
	}

	@Override
	@Autowired
	@Qualifier("sessionIdGenerator")
	public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
		super.setSessionIdGenerator(sessionIdGenerator);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);

		AuthSession authSession = new AuthSession();
		authSession.setId(sessionId.toString());
		authSession.setSession(SerializableUtils.serialize(session));

		sessionMapper.createSession(authSession);

		return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		AuthSession authcSession = sessionMapper.getSession(sessionId.toString());

		if (null == authcSession)
			return null;
		return SerializableUtils.deserialize(authcSession.getSession());
	}

	@Override
	protected void doUpdate(Session session) {
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
			return;
		}

		AuthSession authcSession = new AuthSession();
		authcSession.setId(session.getId().toString());
		authcSession.setSession(SerializableUtils.serialize(session));

		sessionMapper.updateSession(authcSession);
	}

	@Override
	protected void doDelete(Session session) {
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
			return;
		}
		sessionMapper.deleteSession(session.getId().toString());
	}


	public void setSessionMapper(SessionHandlerMapper sessionMapper) {
		this.sessionMapper = sessionMapper;
	}

	@Autowired
	private SessionHandlerMapper sessionMapper;
	
}
