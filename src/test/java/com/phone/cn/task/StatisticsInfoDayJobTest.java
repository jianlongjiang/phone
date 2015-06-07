package com.phone.cn.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseTest;

public class StatisticsInfoDayJobTest extends  BaseTest{

	@Autowired
	StatisticsInfoDayJob statisticsInfoDayJob;
	
	@Test
	public void test() {
		statisticsInfoDayJob.dayJob();
	}

}
