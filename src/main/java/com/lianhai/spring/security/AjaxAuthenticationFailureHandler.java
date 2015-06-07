package com.lianhai.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.phone.cn.bean.ResultBean;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public AjaxAuthenticationFailureHandler() {
	}

//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
//			throws IOException, ServletException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		response.setHeader("Content-Type", "application/json;charset=UTF-8");
//		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
//		try {
//			// 失败为1
//			JsonData jsonData = new JsonData(1, exception.getMessage());
//			objectMapper.writeValue(jsonGenerator, jsonData);
//		} catch (JsonProcessingException ex) {
//			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
//		}
//	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, 
			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		try {
			// 失败为1
//			JsonData jsonData = new JsonData(1, exception.getMessage());
			objectMapper.writeValue(jsonGenerator, ResultBean.fail(exception.getMessage()));
		} catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}
}
