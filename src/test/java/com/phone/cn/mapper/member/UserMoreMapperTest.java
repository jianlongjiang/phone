package com.phone.cn.mapper.member;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.phone.cn.entity.member.UserMore;

import base.BaseTest;

public class UserMoreMapperTest extends BaseTest {

	@Autowired
	private UserMoreMapper mapper;
	@Test
	public void test() {
		fail("Not yet implemented");
	}
		
	@Test
	public void findUser(){
		UserMore userMore = mapper.selectByPrimaryKey(5);
		System.out.println(userMore);
	}
}
