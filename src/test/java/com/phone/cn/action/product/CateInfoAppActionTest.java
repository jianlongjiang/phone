package com.phone.cn.action.product;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import base.BaseTestAction;


public class CateInfoAppActionTest extends BaseTestAction {

	@Autowired
	CateInfoAppAction cateInfoAppAction;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Override
	public void beforeMethod() {
		init(cateInfoAppAction);
	}
	
	@Test
	public void loadByParent() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/app/mobilecate/loadByParent")
				//.param("societyUnionId", societyUnionId).param("pageSize", pageSize)
				.param("parentId", "0")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

}
