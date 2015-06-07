package com.phone.cn.action.member;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import base.BaseTestAction;


public class CashUserLogAppActionTest extends BaseTestAction {

	@Autowired
	CashUserLogAppAction controller;
	
	@Override
	public void beforeMethod() {
		init(controller);
	}
	
	
	@Test
	public void testReflectMoney() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/redPack/reflectMoney")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				.param("money", "100")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testListBaseAppTokenBeanCashUserLogBean() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/redPack/list")
				//.param("societyUnionId", societyUnionId)
				.param("pageSize", "1")
				.param("money", "10")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}
}
