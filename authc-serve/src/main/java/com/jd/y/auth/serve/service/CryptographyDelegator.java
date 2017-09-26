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

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jd.y.auth.serve.orm.domain.AuthUser;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 26, 2017
 */
@Component("cryptographyDelegator")
public class CryptographyDelegator {

	private RandomNumberGenerator random = new SecureRandomNumberGenerator();

	public void encryptPassword(AuthUser user) {

		user.setSalt(random.nextBytes().toHex());

		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();

		user.setPassword(newPassword);
	}

	@Value("${authc.password.algorithm.name: md5}")
	private String algorithmName;
	@Value("${authc.password.hashIterations ?: 2}")
	private int hashIterations;
}
