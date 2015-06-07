package com.phone.cn.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.RoleBean;
import com.phone.cn.entity.sys.Role;
import com.phone.cn.mapper.sys.RoleMapper;
import com.phone.cn.service.BaseService;

@Service
public class RoleService extends BaseService<Role, java.lang.Integer> {

	@Autowired
	private RoleMapper roleMapper;
	
	public List<Role> getAllRoles() {
		
		return super.queryAll(new RoleBean());
		
	}

	public Set<Role> loadRolesByUser(Integer userId) {
		return roleMapper.loadRolesByUser( userId);
	}

	public List<Role> loadByResources(Integer resourceId) {
		return roleMapper.loadByResources( resourceId);
	}

	public Role loadOneRole(Integer userId) {
		Set<Role> roles =   loadRolesByUser(userId);
		if(roles != null){
			List<Role> roleList = new ArrayList<Role>(roles);
			if(CollectionUtils.isNotEmpty(roleList))
				return roleList.get(0);
		}
		return null;
	}

}
