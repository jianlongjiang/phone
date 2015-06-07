package com.phone.cn.service.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.phone.cn.entity.member.UserMore;

import base.BaseTest;

public class UserMoreServiceTest extends BaseTest {
	
	@Autowired
	UserMoreService userMoreService;

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void findUser(){
		UserMore userMore = userMoreService.findOne(5);
	}

}
