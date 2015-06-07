package com.phone.cn.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;

public class CharacterEncode extends CharacterEncodingFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

   if (request.getRequestURI().indexOf("resources") > -1) {
       super.doFilterInternal(request, response, filterChain);
   } else {
       request.setAttribute("com.pvgo.cn.web.requesturl", request.getRequestURI());
       request.setAttribute("com.pvgo.cn.web.requestquery", request.getQueryString());
       super.doFilterInternal(new WebHttpServletRequestWrapper(request), response, filterChain);
   }
	}

}
