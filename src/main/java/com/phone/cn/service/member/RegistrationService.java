package com.phone.cn.service.member;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.member.Registration;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.mapper.member.RegistrationMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月22日 下午6:05:46
 *   
 */
@Service
public class RegistrationService extends BaseService<Registration, Integer>{

	@Autowired
	private RegistrationMapper mapper;

	/**
	 * 获取用户当日的 签到日志
	 * @param user
	 * @return
	 */
	public Registration findOneDay(Integer user){
		Calendar today=Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE,0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Date startTime = today.getTime();
		today.add(Calendar.DATE, 1);
		Date endTime = today.getTime();
		return mapper.findOneDay(user, startTime, endTime);
	}

	/**
	 * 签到
	 * @param user
	 */
	public void sign(UserInfo user) {
		Registration  registration = new Registration();
		registration.setUser(user.getId());
		super.save(registration);
	}

	public Integer loadDay_registrationAmount(Date time) {
		return mapper.loadDay_registrationAmount( time) ;
	}
}
