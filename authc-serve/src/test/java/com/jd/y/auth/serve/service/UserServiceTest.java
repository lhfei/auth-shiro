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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.y.auth.serve.BaseTestSuite;
import com.jd.y.auth.serve.orm.domain.AuthUser;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 26, 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/application-context.xml"})
public class UserServiceTest extends BaseTestSuite {

	@Test
	public void create() {
		AuthUser user = new AuthUser();
		user.setUsername("lhfei");
		user.setPassword("Lhfei");
		user.setSalt("cd0faf6f821809044e35e35fd23cf44a");
	
		userService.createUser(user);
	}
	
	@Autowired
	private UserService userService;
}
