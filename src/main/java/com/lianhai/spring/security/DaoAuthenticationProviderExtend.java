package com.lianhai.spring.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.phone.cn.utils.PasswordUtils;

public class DaoAuthenticationProviderExtend extends DaoAuthenticationProvider {

	/**
	 * userDetails  数据库查询,    authentication 前台参数
	 */
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
//		Object salt = null;
//
//		if (this.saltSource != null) {
//			salt = this.saltSource.getSalt(userDetails);
//		}
//
//		if (authentication.getCredentials() == null) {
//			this.logger.debug("Authentication failed: no credentials provided");
//
//			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//		}
//
//		String presentedPassword = authentication.getCredentials().toString();
//
//		if (!this.passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
//			this.logger.debug("Authentication failed: password does not match stored value");
//
//			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//		}
		
		
//		String presentedPassword = authentication.getCredentials().toString();
//		if (!this.passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
//			this.logger.debug("Authentication failed: password does not match stored value");
//
//			throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
//		}
		
		String presentedPassword = authentication.getCredentials().toString();
		if ( !PasswordUtils.validPassword(userDetails.getPassword(), presentedPassword)) {
			/*
			 * 在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
			 * 这样我们可以在登录失败后，向用户提供相应的信息。 if (forwardToDestination) {
			 * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } else { HttpSession session =
			 * request.getSession(false);
			 * 
			 * if (session != null || allowSessionCreation) {
			 * request.getSession(
			 * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } }
			 */
			throw new AuthenticationServiceException("密码错误！");
		}
	}

}
