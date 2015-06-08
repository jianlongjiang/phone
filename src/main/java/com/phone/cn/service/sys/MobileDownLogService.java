package com.phone.cn.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.MobileDownLogBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.MobileDownLog;
import com.phone.cn.mapper.sys.MobileDownLogMapper;
import com.phone.cn.service.BaseService;

@Service
public class MobileDownLogService extends BaseService<MobileDownLog, Integer> {
	
	@Autowired
	private MobileDownLogMapper  mobileDownLogMapper;

	public List<String> loadDownMobiles(UserInfo user) {
		MobileDownLogBean bean = new MobileDownLogBean();
		bean.setUserId(user.getId());
		List<MobileDownLog> list = queryAll(bean);
		List<String> mobiles = new ArrayList<String>();
		for (MobileDownLog log : list) {
			if(!StringUtils.isBlank(log.getMobiles())){
				mobiles.addAll(Arrays.asList(log.getMobiles().split(",")));
			}
		}
		return mobiles;
	}

	public Integer loadMobileDownCount(String mobile) {
		return mobileDownLogMapper.loadMobileDownCount(mobile);
	}

}
