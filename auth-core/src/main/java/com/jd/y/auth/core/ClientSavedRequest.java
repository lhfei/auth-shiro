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

package com.jd.y.auth.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.util.SavedRequest;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Sep 22, 2017
 */
public class ClientSavedRequest extends SavedRequest {
	private static final long serialVersionUID = 8200158741699228118L;
	
	public ClientSavedRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}


}
