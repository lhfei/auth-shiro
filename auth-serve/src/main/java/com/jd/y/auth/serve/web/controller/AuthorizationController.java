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

package com.jd.y.auth.serve.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jd.y.auth.core.web.model.UserModel;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 24, 2017
 */
@Controller
@RequestMapping("/")
public class AuthorizationController extends BaseController {
	private static final String RETURN_URL_PARAM_NAME = "url";
	private static final String KEY_ACCOUNT = "accountInfo";
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("login");
		String redirectUrl = request.getParameter(RETURN_URL_PARAM_NAME);
		
		view.addObject(RETURN_URL_PARAM_NAME, redirectUrl);

		LOG.info(redirectUrl);

		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("userForm") UserModel user, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		String url = user.getUrl();
		try {
			// SecurityUtils.getSubject().login(token);
			LOG.info("{}", user.toString());

			if (null != url && user.getUrl().length() > 0) {
				return "redirect:" + url;
			} else
				return "main";

		} catch (AuthenticationException e) {
			LOG.warn("error.invalidLogin. {}", "The username or password was not correct.", e.getMessage());

			return "redirect:index/" + user.getUrl();
		}

	}
	
	@RequestMapping("/version")
	public @ResponseBody String version() {
		String version = "alpha-0.1";
		
		return version;
	}

	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:index";
	}
}
