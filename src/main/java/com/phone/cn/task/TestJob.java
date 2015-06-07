package com.phone.cn.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("testJob")
public class TestJob {

	// @Scheduled(cron="")

	@Scheduled(cron = "0-59 * * * * ?")
	public void job1() {
		System.out.println("认证进行中啊   ");
		
	}

}
