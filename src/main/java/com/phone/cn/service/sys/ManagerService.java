package com.phone.cn.service.sys;

import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.ManagerBean;
import com.phone.cn.entity.sys.Manager;
import com.phone.cn.service.BaseService;
import com.phone.cn.utils.PasswordUtils;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:00:59
 * 
 */
@Service
public class ManagerService extends BaseService<Manager, Integer> {

	public Manager loadByUserName(String account) {
		ManagerBean bean = new ManagerBean();
		bean.setAccount(account);
		
		return super.selectOneByExample(bean);
	}
	
	@Override
	protected void initSave(Manager m) {
		if(org.apache.commons.lang3.StringUtils.isBlank(m.getPassword())){
			try {
				m.setPassword(PasswordUtils.encrypt("123456"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.initSave(m);
	}

}
