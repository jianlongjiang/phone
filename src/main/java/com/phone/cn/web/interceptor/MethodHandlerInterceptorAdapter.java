package com.phone.cn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public abstract class MethodHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
    public final boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截方法
        if (!ClassUtils.isAssignableValue(HandlerMethod.class, handler)) {
            return Boolean.TRUE;
        }
        return appPreHandle(request, response, handler);
    }

    public abstract boolean appPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    @Override
    public final void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 拦截方法
        if (!ClassUtils.isAssignableValue(HandlerMethod.class, handler)) {
            return;
        }
        appPostHandle(request, response, handler, modelAndView);
    }

    public abstract void appPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;

    public final void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        appAfterCompletion(request, response, handler, ex);
    }

    public abstract void appAfterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;
}
