package com.phone.cn.web.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.phone.cn.conf.DataConfig;

/**
 * @author zgdcool
 * @version 2014年10月22日 下午3:59:23
 */
public class MobileCusUserLoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
			throws Exception {
		HttpSession session = request.getSession();
		if (isNeedLogin(handler, getCurrentUrl(request)) && session.getAttribute(DataConfig.CURRENT_USER) == null) {
			response.sendRedirect(request.getContextPath() + "/mobile/cus/login.html");
			return false;
		}

		return true;
	}

	public Boolean isNeedLogin( Object obj, String s ) {
		if (ClassUtils.isAssignableValue(HandlerMethod.class, obj)) {
			HandlerMethod hm = (HandlerMethod) obj;
			if (AnnotationUtils.findAnnotation(hm.getBeanType(), MobileCusUserLogin.class) != null
					|| hm.getMethodAnnotation(MobileCusUserLogin.class) != null) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	private static String getCurrentUrl( HttpServletRequest request ) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getRequestURL());
		if (request.getQueryString() != null)
			sb.append("?").append(escapeParamString(request.getQueryString()));
		if (sb.toString().endsWith("?"))
			sb.substring(0, sb.length() - 1);
		return URLEncoder.encode(sb.toString(), "UTF-8");
	}

	public static String escapeParamString( String value ) {

		if (value == null)
			return "";

		// value = StringUtils.replace(value, "|", "");
		value = StringUtils.replace(value, "&amp;", "");
		// value = StringUtils.replace(value, ";", "");
		value = StringUtils.replace(value, "$", "");
		// value = StringUtils.replace(value, "%", "");
		// value = StringUtils.replace(value, "@", "");
		value = StringUtils.replace(value, "'", "");
		value = StringUtils.replace(value, "\"", "");
		value = StringUtils.replace(value, "\\'", "");
		value = StringUtils.replace(value, "&lt;", "");
		value = StringUtils.replace(value, "&gt;", "");
		value = StringUtils.replace(value, "<", "");
		value = StringUtils.replace(value, ">", "");
		value = StringUtils.replace(value, "(", "");
		value = StringUtils.replace(value, ")", "");
		value = StringUtils.replace(value, "+", "");
		// value = StringUtils.replace(value, "\n", "");
		value = StringUtils.replace(value, "\r", "");
		// value = StringUtils.replace(value, ",", "");
		value = StringUtils.replace(value, "\\", "");

		return value;
	}
}
