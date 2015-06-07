package com.phone.cn.action.member;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import base.BaseTestAction;

public class UserInfoAppActionTest extends BaseTestAction{

	@Autowired(required=true)
	UserInfoAppAction controller;
	
	@Before
	public void before(){
		System.out.println("before ------------");
		init(controller);
	}
	
	@Override
	public void beforeMethod() {
		init(controller);// TODO Auto-generated method stub
	}
	
	@Test
	public void test() {
//		fail("Not yet implemented");
		System.out.println(controller);
	}
	
	
	
	@Test
	public void msgManager() throws Exception{
//		String societyUnionId= "1";
//		String pageSize = "2";
//		String pageNo = "1";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/userinfo/msgManager")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				//.param("pageNo", pageNo)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void unreadCount() throws Exception{
//		String societyUnionId= "1";
//		String pageSize = "2";
//		String pageNo = "1";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/userinfo/unreadCount")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				//.param("pageNo", pageNo)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void updatebyWeixin() throws Exception{
//		String societyUnionId= "1";
//		String pageSize = "2";
//		String pageNo = "1";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/userinfo/updatebyWeixin")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				.param("weixin", "weixin3")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void updateUserInfo() throws Exception{
//		String societyUnionId= "1";
//		String pageSize = "2";
//		String pageNo = "1";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/userinfo/updateUserInfo")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				.param("userName", "昵称")
				.param("sex", "1")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	
	@Test
	public void bindAlipay() throws Exception{
//		String societyUnionId= "1";
//		String pageSize = "2";
//		String pageNo = "1";
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/userinfo/bindAlipay")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				.param("alipayAccount", "alipayAccount")
				.param("alipayRealName", "alipayRealName")
				
				   
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
	
	

}
