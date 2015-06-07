package com.lianhai.spring.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.phone.cn.entity.sys.Manager;
import com.phone.cn.utils.PasswordUtils;

/* 
 * 
 *  
 *  账号密码登入
 *  
 *  
 * UsernamePasswordAuthenticationFilter源码 
 attemptAuthentication 
 this.getAuthenticationManager() 
 ProviderManager.java 
 authenticate(UsernamePasswordAuthenticationToken authRequest) 
 AbstractUserDetailsAuthenticationProvider.java 
 authenticate(Authentication authentication) 
 P155 user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication); 
 DaoAuthenticationProvider.java 
 P86 loadUserByUsername 
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public static final String VALIDATE_CODE = "yzm";
	public static final String USERNAME = "account";
	public static final String PASSWORD = "password";
	
	// 是否开启验证码功能  
    private boolean openValidateCode = false;  
  

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MyUserDetailsService myUserDetailsService;;

	public MyUserDetailsService getMyUserDetailsService() {
		return myUserDetailsService;
	}

	public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
	}

	
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String yzm = null;
		String username = obtainUsername(request);
		String password = obtainPassword(request);
//		public static final String VALIDATE_CODE = "yzm";
//		public static final String USERNAME = "account";
//		public static final String PASSWORD = "password";
		HttpSession session = request.getSession();
		session.setAttribute(USERNAME, username);
		session.setAttribute(PASSWORD, password);
		if(openValidateCode)
		// 检测验证码
			yzm =checkValidateCode(request);
		session.setAttribute(VALIDATE_CODE, yzm);
	
		logger.info("=========>username:"+username +"----passwod:"+password);
		// 验证用户账号与密码是否对应
		username = username.trim();
		Manager manager = this.myUserDetailsService.loadByUserName(username);
		
		if(manager == null) {
			throw new AuthenticationServiceException("账号不存在");
		}
		//String  str = WebAttributes.AUTHENTICATION_EXCEPTION;
		//WebAttributes.AUTHENTICATION_EXCEPTION;
		if ( !PasswordUtils.validPassword(manager.getPassword(), password)) {
//			/*
//			 * 在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
//			 * 这样我们可以在登录失败后，向用户提供相应的信息。 if (forwardToDestination) {
//			 * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
//			 * exception); } else { HttpSession session =
//			 * request.getSession(false);
//			 * 
//			 * if (session != null || allowSessionCreation) {
//			 * request.getSession(
//			 * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
//			 * exception); } }
//			 */
			throw new AuthenticationServiceException("密码错误！");
		}

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Place the last username attempted into HttpSession for views
		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager()
				.authenticate(authRequest);
	}
	
	

	protected String checkValidateCode(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		logger.info("=============> sessionValidateCode"+sessionValidateCode);
		// 让上一次的验证码失效
//		session.setAttribute(VALIDATE_CODE, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		logger.info("=============> validateCodeParameter"+validateCodeParameter);
		if (StringUtils.isEmpty(validateCodeParameter) 
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
		return  validateCodeParameter;
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	public boolean isOpenValidateCode() {
		return openValidateCode;
	}

	public void setOpenValidateCode(boolean openValidateCode) {
		this.openValidateCode = openValidateCode;
	}
	

    
  
  

}
