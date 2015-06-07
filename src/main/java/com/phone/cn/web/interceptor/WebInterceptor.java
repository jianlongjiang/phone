package com.phone.cn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.phone.cn.conf.AppConfig;

public class WebInterceptor extends MethodHandlerInterceptorAdapter {

	private static boolean firstRequest = Boolean.TRUE;

	@Override
	public boolean appPreHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (firstRequest) { // 存在并发性线程安全问题，必须允许可多次赋�?
			WebApplicationContext ws = WebApplicationContextUtils
					.getWebApplicationContext(
							request.getSession().getServletContext(),
							"org.springframework.web.servlet.FrameworkServlet.CONTEXT.Spring MVC Dispatcher Servlet");
			if (ws == null) {
				ws = WebApplicationContextUtils
						.getWebApplicationContext(request.getSession()
								.getServletContext(),
								"org.springframework.web.servlet.FrameworkServlet.CONTEXT.");
			}
			// 初始化应用程序数�?
			FreeMarkerViewResolver fvr = ws
					.getBean(FreeMarkerViewResolver.class);
			fvr.setAttributesMap(AppConfig.getAppMap());
			firstRequest = Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void appPostHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void appAfterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// Auto-generated method stub

	}

}
