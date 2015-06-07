package com.lianhai.spring.security.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.phone.cn.entity.sys.Manager;

public class SpringSecurityContent {

	/**
	 * 账号
	 * 
	 * @return
	 */
	static String getUsername() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			UserDetails details = (UserDetails) auth.getPrincipal();
			return details.getUsername();
		}
		return null;
	}

	/**
	 * 后台用户
	 * 
	 * @return
	 */
	
	public static Manager getUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			Manager user = null;
			try {
				user = (Manager) auth.getPrincipal();
			} catch (NullPointerException e) {

			}
			return user;
		}
		return null;
	}

	/**
	 * 用户id
	 * 
	 * @return
	 */
	public static Integer getUserId() {
		if (getUser() != null) {
			return getUser().getId();
		}
		return null;
	}

}
