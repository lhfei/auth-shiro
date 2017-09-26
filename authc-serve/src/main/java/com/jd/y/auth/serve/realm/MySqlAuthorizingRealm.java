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

package com.jd.y.auth.serve.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jd.y.auth.serve.orm.domain.AuthUser;
import com.jd.y.auth.serve.orm.mapper.UserMapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 22, 2017
 */
public class MySqlAuthorizingRealm extends JdbcRealm {
	private static final Logger LOG = LoggerFactory.getLogger(MySqlAuthorizingRealm.class);

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		userName = upToken.getUsername();
		
		LOG.info("Accoutn: [{}/{}]", userName, password);
		
		AuthUser user = userMapper.getByName(userName);
		
		if(user == null) { // account not exist
            throw new UnknownAccountException();
        }

        if(Boolean.TRUE.equals(user.getLocked())) { // account disabled
            throw new LockedAccountException();
        }
        
        boolean isAuthenticated = true;
        
        AuthenticationInfo authenticationInfo = null;
        try {
			if (isAuthenticated) {
				authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
						user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName() // realm name
				);
				
				return authenticationInfo;
			} else {
				throw new AuthenticationException("Authenticating user[" + userName + "] failed.");
			}
		} catch (Exception e) {
			LOG.warn("Authenticated failed. {}", e.getMessage(), e);
			throw new AuthenticationException(e.getMessage(), e);
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		String userName = (String)principals.getPrimaryPrincipal();
		LOG.info("User is: {}", userName);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
		//return super.doGetAuthorizationInfo(principals);
        
        return authorizationInfo;
	}
	
	private AuthUser getUser(String userName) {
		AuthUser user = new AuthUser();
		user.setUsername("admin");
		user.setPassword("3ab82b226b3b60f4eab8cd0a88887ba0");
		user.setSalt("Y2QwZmFmNmY4MjE4MDkwNDRlMzVlMzVmZDIzY2Y0NGE=");
		user.setLocked(false);
		
		return user;
	} 
	
	@Autowired
	private UserMapper userMapper;

}
