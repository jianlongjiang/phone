package com.phone.cn.service.sys;

import base.BaseCRUDTest;

import com.phone.cn.bean.sys.ManagerToRoleBean;
import com.phone.cn.entity.sys.ManagerToRole;
import com.phone.cn.utils.CreateBeanFactory;

public class ManagerToRoleServiceTest extends BaseCRUDTest<ManagerToRoleBean, ManagerToRole, Integer> {

	@Override
	public void testSaveAndQuery() {
		super.testSaveAndQuery();
	}
  
	public static void main(String[] args) {
		
		ManagerToRole managerToRole = CreateBeanFactory.createBean(ManagerToRole.class);
		System.out.println(managerToRole);
		
	}
}
