package com.phone.cn.service.member;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseCRUDTest;

import com.phone.cn.bean.member.CashUserLogBean;
import com.phone.cn.entity.member.CashUserLog;

public class CashUserLogServiceTest extends BaseCRUDTest<CashUserLogBean,CashUserLog,Integer>{

	@Autowired
	CashUserLogService cashUserLogService;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Override
	public void testSaveAndQuery() {
		super.testSaveAndQuery();
	}
	
	@Test
	public void find(){
		cashUserLogService.findOne(1);
	}

}
