package com.phone.cn.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lianhai.spring.security.InvocationSecurityMetadataSourceService;
import com.phone.cn.bean.sys.RoleToResourcesBean;
import com.phone.cn.entity.sys.Role;
import com.phone.cn.entity.sys.RoleToResources;
import com.phone.cn.service.BaseService;

@Service
public class RoleToResourcesService extends BaseService<RoleToResources, java.lang.Integer> {

	@Autowired
	InvocationSecurityMetadataSourceService mySecurityMetadataSource;
	
	
	public void build(Role role, Integer[] resourceIds) {
		
		RoleToResourcesBean roleToResourcesBean = new RoleToResourcesBean();
		if(role.getId() != null){
			roleToResourcesBean.setRoleId(role.getId());
			List<RoleToResources> list = super.queryAll(roleToResourcesBean);
			for (RoleToResources roleToResources : list) {
				super.delete(roleToResources.getId());
			}
		}
		
		if(resourceIds != null){
			for (Integer resourceId : resourceIds) {
				RoleToResources roleToResources = new RoleToResources();
				roleToResources.setResourceId(resourceId);
				roleToResources.setRoleId(role.getId());
				super.save(roleToResources);
			}
		}
		
		//  spring security 服务器资源刷新
		try {
			mySecurityMetadataSource.loadResourceDefine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
