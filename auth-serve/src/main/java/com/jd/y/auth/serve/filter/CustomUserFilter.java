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

package com.jd.y.auth.serve.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.jd.y.auth.serve.common.constant.StandardConstants;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 23, 2017
 */
public class CustomUserFilter extends PathMatchingFilter {
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, 
			Object mappedValue) throws Exception {
		
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute(StandardConstants.CURRENT_USER, username);
		
		return true;
	}
}
