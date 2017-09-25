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
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.y.auth.serve.orm.domain.AuthUser;

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
		// TODO Auto-generated method stub
		String userName = (String)token.getPrincipal();
		AuthUser user = getUser(userName);
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName()  //realm name
        );
		
		//return super.doGetAuthenticationInfo(token);
		
		return authenticationInfo;
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
		user.setUsername("lhfei");
		user.setPassword("Lhfei");
		user.setLocked(false);
		
		return user;
	} 

}
