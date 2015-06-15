package com.phone.cn.task;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phone.cn.entity.sys.StatisticsInfo;
import com.phone.cn.service.member.RegistrationService;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.product.DownloadLogService;
import com.phone.cn.service.sys.LoginLogService;
import com.phone.cn.service.sys.StatisticsInfoService;


/**
 * 统计 数据
 * @author jiangjianlong
 *
 */
@Component("statisticsInfoDayJob")
public class StatisticsInfoDayJob {
	@Autowired	
	LoginLogService loginLogService;
	
	@Autowired
	UserInfoService  userInfoService;
	
	/// 签到
	@Autowired
	RegistrationService  registrationService;
	
	
	@Autowired
	DownloadLogService  downloadLogService;
	
	
	@Autowired
	StatisticsInfoService statisticsInfoService; 
	
	private  static  Long time = 0l;
	
	/**
	 * 晚上 3-4
	 */
	public  void  dayJob(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		
		// 多核 该检查无效
		if(time == calendar.getTimeInMillis()) return ;
		time = calendar.getTimeInMillis();
		
		Integer  downCount = downloadLogService.loadDayDownAmount(calendar.getTime());
		Integer  registAmount =  userInfoService.loadDayRegisterAmount(calendar.getTime());
		Integer  downloadPeople = downloadLogService.loadDay_downloadPeople(calendar.getTime()); 
		
		Integer  vipAmount = userInfoService.loadDay_vipAmount(calendar.getTime());
		
		Integer  loginAmount = loginLogService.loadDay_loginAmount(calendar.getTime());
		
		Integer  registrationAmount = registrationService.loadDay_registrationAmount(calendar.getTime());
		
		
		StatisticsInfo  statisticsInfo= new StatisticsInfo();
		statisticsInfo.setDownloadAmount(downCount);
		statisticsInfo.setRegistAmount(registAmount);
		statisticsInfo.setDownloadPeople(downloadPeople);
		statisticsInfo.setVipAmount(vipAmount);
		statisticsInfo.setLoginAmount(loginAmount);
		statisticsInfo.setInt1(registrationAmount);
		statisticsInfo.setCreateTime(calendar.getTime());
		
		try {
			statisticsInfoService.save(statisticsInfo);
		} catch (Exception e) {
//			e.printStackTrace();
//			服务器为四核  四个线程同时跑
		}
	}
	
}
