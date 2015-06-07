package com.phone.cn.mapper.member;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseTest;

import com.phone.cn.entity.member.CashUserLog;

public class CashUserLogMapperTest extends BaseTest {

	@Test
	public void test() {
		fail("Not yet implemented");
		
	}
	
	@Autowired
	CashUserLogMapper  cashUserLogMapper;
	
	@Test
	public void testSave(){
		
		List<CashUserLog> list = cashUserLogMapper.query(null);
		System.out.println(list);
	}

	
	public static void main(String[] args) {
		
	}
}
