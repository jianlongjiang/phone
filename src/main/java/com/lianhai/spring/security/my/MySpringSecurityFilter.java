//package com.lianhai.spring.security.my;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//public class MySpringSecurityFilter  implements Filter{
//		
//	///
//	AccessDecisionManager  accessDecisionManager;
//	
//	//  资源获取
//	FilterInvocationSecurityMetadataSource  filterInvocationSecurityMetadataSource;
//	
//	
//	//
//	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
//
//	String  loginUri ;
//	String   loginSuc ;
//	
//	String  charset = "utf-8";
//
//	@Override
//	public void destroy() {
//		
//	}
//
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain  filterChain) throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//		String url = request.getRequestURI();
//		HttpSession session ;
//		response.setCharacterEncoding(charset);
//		if(StringUtils.contains(url, loginUri)){
//			try {
//				//Authentication authentication = 	usernamePasswordAuthenticationFilter.attemptAuthentication(request, response);
//				filterChain.doFilter(request, response);
//			} catch (AuthenticationException e) {
//				e.printStackTrace();
//			}
//		}else {
//			;
//			accessDecisionManager.decide(paramAuthentication, paramObject, paramCollection);
//			
//		}
//	}
//
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		
//	}
//	
//	
//}
