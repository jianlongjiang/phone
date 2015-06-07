//package com.phone.cn.action.ajax;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("ajax")
//public class JsonpAction {
//	
//	@ResponseBody
//	@RequestMapping("jsonp")
//	public Object jsonp(HttpServletRequest request, Model model) {
//		String  jsonp = request.getParameter("callbackparam");
//		String  str =  "[{'id':'1','name':'测试1'},{'id':'2','name':'测试2'}]";
//		return jsonp+"("+str+")";
//	}
//}
