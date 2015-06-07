package com.phone.cn.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.phone.cn.web.MySessionContext;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		MySessionContext.getInstance().DelSession(arg0.getSession());
	}

	
}
