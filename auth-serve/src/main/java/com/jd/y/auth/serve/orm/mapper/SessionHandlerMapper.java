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

package com.jd.y.auth.serve.orm.mapper;

import java.util.List;

import com.jd.y.auth.serve.orm.domain.AuthSession;


/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 25, 2017
 */
public interface SessionHandlerMapper {
	
	/**
	 * @param session
	 */
	void createSession(AuthSession session);
	
	/**
	 * @param session
	 */
	void updateSession(AuthSession session);
	
	/**
	 * @param sessionId
	 */
	void deleteSession(String sessionId);
	
	/**
	 * @param sessionId
	 * @return
	 */
	AuthSession getSession(String sessionId);
	
	/**
	 * @param sessionId
	 * @return
	 */
	List<AuthSession> getSessions(String sessionId);
	
	/**
	 * @param sessionId
	 * @return
	 */
	Long countSessions(String sessionId);
}
