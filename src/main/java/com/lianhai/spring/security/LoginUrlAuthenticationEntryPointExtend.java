package com.lianhai.spring.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.phone.cn.bean.ResultBean;


/**
 * url  分配器,  普通登入 or  ajax 请求
* @ClassName: LoginUrlAuthenticationEntryPointExtend
* @Description: (这里用一句话描述这个类的作用)
* @author jiangjianlong-蒋建龙
* @date 2015年5月12日 上午10:59:35
*
 */
public class LoginUrlAuthenticationEntryPointExtend extends LoginUrlAuthenticationEntryPoint {

	public LoginUrlAuthenticationEntryPointExtend(String loginFormUrl) {
		super(loginFormUrl);
	}

	private static final Logger logger = LoggerFactory.getLogger(LoginUrlAuthenticationEntryPointExtend.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException,
			ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String redirectUrl = null;

		String url = request.getRequestURI();

		if (logger.isDebugEnabled()) {
			logger.debug("url:" + url);
		}
		
		logger.info("\n=====================   LoginUrlAuthenticationEntryPointExtend  请求 的返回方式,  request or  ajax");

		// 非ajax请求
		if (url.indexOf("ajax") == -1) {

			if (this.isUseForward()) {

				if (this.isForceHttps() && "http".equals(request.getScheme())) {
					// First redirect the current request to HTTPS.
					// When that request is received, the forward to the login
					// page will be used.
					redirectUrl = buildHttpsRedirectUrlForRequest(httpRequest);
				}
				if (redirectUrl == null) {

					String loginForm = determineUrlToUseForThisRequest(httpRequest, httpResponse, authException);

					if (logger.isDebugEnabled()) {
						logger.debug("Server side forward to: " + loginForm);
					}

					RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginForm);

					dispatcher.forward(request, response);

					return;
				}
			} else {
				// redirect to login page. Use https if forceHttps true
				redirectUrl = buildRedirectUrlToLoginPage(httpRequest, httpResponse, authException);
			}
			redirectStrategy.sendRedirect(httpRequest, httpResponse, redirectUrl);
		} else {
			// ajax请求，返回json，替代redirect到login page
			if (logger.isDebugEnabled()) {
				logger.debug("ajax request or post");
			}

			ObjectMapper objectMapper = new ObjectMapper();
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
			try {
				// JsonData jsonData = new JsonData(2, null);
				objectMapper.writeValue(jsonGenerator, ResultBean.fail("ajax请求"));
			} catch (JsonProcessingException ex) {
				throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
			}
		}
	}
}
