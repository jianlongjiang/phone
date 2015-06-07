package com.phone.cn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceInterceptor extends HandlerInterceptorAdapter {
	
	private org.apache.log4j.Logger log = Logger.getLogger(PerformanceInterceptor.class); 
	
	private boolean performance = false;
	
	private int limit = 100;

	private ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<StopWatch>(); 

	/**
	 * 请求处理�?
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object handler) throws Exception {
		if(performance)
		{
			StopWatch stopWatch = stopWatchThreadLocal.get();
			if(stopWatch != null)
			{
				if (!stopWatch.isRunning()) {
					stopWatch.start();
				}
			}else
			{
				stopWatch = new StopWatch();
				stopWatch.start();
				stopWatchThreadLocal.set(stopWatch);
			}
		}
		return true;
	}
	
	/**
	 * 页面加载完毕
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		if(performance)
		{
			StopWatch stopWatch = stopWatchThreadLocal.get();
			if(stopWatch != null)
			{
				stopWatch.stop();
				if(stopWatch.getTotalTimeMillis() > limit){
					String currentPath = request.getRequestURI();
	
				    String queryString  = request.getQueryString();
	
				    queryString = queryString == null ? "":"?" + queryString;
	
				    log.info("access url path:" + currentPath + queryString +  " |time:" + stopWatch.getTotalTimeMillis());
	
				    stopWatchThreadLocal.set(null);
				}
			}
		}
	}

	public boolean getPerformance() {
		return performance;
	}

	public void setPerformance(boolean performance) {
		this.performance = performance;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	


}
