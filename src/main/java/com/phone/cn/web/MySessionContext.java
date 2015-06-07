package com.phone.cn.web;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-11
 * Time: 上午10:57
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("rawtypes")
public class MySessionContext {
	private static MySessionContext instance;
	private HashMap mymap;
	private boolean  isTest= true;
	private  String  testSessionId ;
	//私有默认构造方法
	private MySessionContext() {
		mymap = new HashMap();
	}
    //静态工厂方法
	public static MySessionContext getInstance() {
		if (instance == null) {
			instance = new MySessionContext();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			if(isTest)
				testSessionId = session.getId();
			mymap.put(session.getId(), session);
		}
	}

	public synchronized void DelSession(HttpSession session) {
		if (session != null) {
			mymap.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null && isTest) {
			session_id = testSessionId;
		}
		return (HttpSession) mymap.get(session_id);
	}

}