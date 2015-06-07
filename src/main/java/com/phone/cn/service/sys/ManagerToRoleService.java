package com.phone.cn.service.sys;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.ManagerToRoleBean;
import com.phone.cn.entity.sys.Manager;
import com.phone.cn.entity.sys.ManagerToRole;
import com.phone.cn.mapper.sys.ManagerToRoleMapper;
import com.phone.cn.service.BaseService;

@Service
public class ManagerToRoleService extends BaseService<ManagerToRole, Integer> {

	@Autowired
	ManagerToRoleMapper managerToRoleMapper;
	
	
	public void build(Manager m, Integer roleId) {
		ManagerToRoleBean managerToRoleBean = new ManagerToRoleBean();
		if(m.getId() != null){
			managerToRoleBean.setManagerId(m.getId());
			List<ManagerToRole>  managerToRoles = super.queryAll(managerToRoleBean);
			if(CollectionUtils.isNotEmpty(managerToRoles)){
				for (ManagerToRole managerToRole : managerToRoles) {
					super.delete(managerToRole.getId());
				}
			}
		}
		
		ManagerToRole managerToRole = new ManagerToRole();
		managerToRole.setManagerId(m.getId());
		managerToRole.setRoleId(roleId);
		super.save(managerToRole);
	}

}
