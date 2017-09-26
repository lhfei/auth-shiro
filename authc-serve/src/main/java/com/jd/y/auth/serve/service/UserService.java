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

package com.jd.y.auth.serve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.y.auth.serve.orm.domain.AuthUser;
import com.jd.y.auth.serve.orm.mapper.UserMapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 26, 2017
 */
@Service("userService")
public class UserService {
	
	public Long createUser(AuthUser user) {
		cryptographyDelegator.encryptPassword(user);
		return userMapper.createUser(user);
	}
	
	public void changePassword(Long userId, String newPassword) {
		AuthUser user = getUser(userId);
		user.setPassword(newPassword);
		cryptographyDelegator.encryptPassword(user);
		userMapper.updateUser(user);
	}

	public void updateUser(AuthUser user) {
		userMapper.updateUser(user);
	}

	public boolean deleteUser(Long id) {
		return userMapper.deleteUser(id);
	}

	public AuthUser getUser(Long id) {
		return userMapper.getUser(id);
	}
	

	public AuthUser getByName(String name) {
		return userMapper.getByName(name);
	}

	public List<AuthUser> getUsers(AuthUser user) {
		return userMapper.getUsers(user);
	}

	public List<AuthUser> findAll() {
		return userMapper.findAll();
	}

	public int countUsers(AuthUser user) {
		return userMapper.countUsers(user);
	}

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CryptographyDelegator cryptographyDelegator;
}
