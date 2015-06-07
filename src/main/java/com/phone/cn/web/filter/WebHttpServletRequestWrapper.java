package com.phone.cn.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;


public class WebHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public WebHttpServletRequestWrapper(HttpServletRequest request) {

		super(request);
		// Auto-generated constructor stub
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = HtmlUtils.htmlEscape(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return HtmlUtils.htmlEscape(value);
	}

	public String getQueryString() {
		String queryString = super.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			return HtmlUtils.htmlEscape(queryString).replace("&amp;", "&");
		} else {
			return null;
		}
	}
}
