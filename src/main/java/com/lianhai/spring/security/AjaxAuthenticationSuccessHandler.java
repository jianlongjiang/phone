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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.phone.cn.bean.ResultBean;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  
  
    public AjaxAuthenticationSuccessHandler() {  
    }  
  
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,  
            Authentication authentication) throws IOException, ServletException {  
  
        ObjectMapper objectMapper = new ObjectMapper();  
        response.setHeader("Content-Type", "application/json;charset=UTF-8");  
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),  
                JsonEncoding.UTF8);  
        try {  
                            //成功为0  
//            JsonData jsonData = new JsonData(0, null);  
//          JSONObject jsonData = new JSONObject();
          
            objectMapper.writeValue(jsonGenerator, ResultBean.suc("登入成功"));  
        } catch (JsonProcessingException ex) {  
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);  
        }  
    }  

}
