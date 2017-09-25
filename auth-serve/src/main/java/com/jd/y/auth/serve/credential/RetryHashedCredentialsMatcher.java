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

package com.jd.y.auth.serve.credential;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 22, 2017
 */
public class RetryHashedCredentialsMatcher extends HashedCredentialsMatcher {
	private static final Logger LOG = LoggerFactory.getLogger(RetryHashedCredentialsMatcher.class);
	
	public RetryHashedCredentialsMatcher(CacheManager cacheManager) {
		accountCache = cacheManager.getCache("accountCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String userName = (String) token.getPrincipal();
		AtomicInteger retryTimes =  accountCache.get(userName);
		
		LOG.debug("Account: {}, Retry {} times.", userName, retryTimes);
		
		// TODO Auto-generated method stub
		return super.doCredentialsMatch(token, info);
	}

	
	private Cache<String, AtomicInteger> accountCache;
}
