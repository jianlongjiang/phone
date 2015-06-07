package com.phone.cn.web.filter;
//package com.wsf.cn.web.filter;
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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//
//public class LoginFilter implements Filter {
//
//	Logger logger = LoggerFactory.getLogger(LoginFilter.class);
//
//	@Override
//	public void doFilter(ServletRequest servletRequest,
//			ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		if (!(servletRequest instanceof HttpServletRequest)
//				|| !(servletResponse instanceof HttpServletResponse)) {
//			throw new ServletException(
//					"OncePerRequestFilter just supports HTTP requests");
//		}
//		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//		HttpSession session = httpRequest.getSession(true);
//
//		Object object = session.getAttribute("user");
//		UserBean user = object == null ? null : (UserBean) object;
//		if (user == null) {
//			boolean isAjaxRequest = isAjaxRequest(httpRequest);
//			if (isAjaxRequest) {
//				httpResponse.setCharacterEncoding("UTF-8");
//				httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),
//						"���Ѿ�̫��ʱ��û�в���,��ˢ��ҳ��");
//			}
//			httpResponse.sendRedirect("/console/login");
//			return;
//		}
//		filterChain.doFilter(servletRequest, servletResponse);
//		return;
//	}
//
//	/**
//	 * �ж��Ƿ�ΪAjax����
//	 *
//	 * @param request
//	 *            HttpServletRequest
//	 * @return ��true, ��false
//	 */
//	public static boolean isAjaxRequest(HttpServletRequest request) {
//		return request.getRequestURI().startsWith("/api");
//		// String requestType = request.getHeader("X-Requested-With");
//		// return requestType != null && requestType.equals("XMLHttpRequest");
//	}
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		/* �����Ҫע�룬��ȡ��ע�� */
//		// ServletContext servletContext = filterConfig.getServletContext();
//		// WebApplicationContext applicationContext = (WebApplicationContext)
//		// servletContext.
//		// getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//		// if (null == topConstantsImpl) {
//		// //��Spring AC �м���app configuration����
//		// topConstantsImpl =
//		// applicationContext.getBean(TopConstantsImpl.class);
//		// }
//	}
//
//	@Override
//	public void destroy() {
//		// To change body of implemented methods use File | Settings | File
//		// Templates.
//	}
//
//}
