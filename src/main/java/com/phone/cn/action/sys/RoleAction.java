package com.phone.cn.action.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.sys.ResourcesDto;
import com.phone.cn.bean.sys.RoleBean;
import com.phone.cn.entity.sys.Resources;
import com.phone.cn.entity.sys.Role;
import com.phone.cn.service.sys.ResourcesService;
import com.phone.cn.service.sys.RoleService;
import com.phone.cn.service.sys.RoleToResourcesService;
import com.phone.cn.web.action.BaseCRUDController;

@Controller
@RequestMapping("admin/role")
public class RoleAction extends BaseCRUDController<RoleBean, Role, Integer> {
	@Autowired
	RoleService roleService;

	@Autowired
	ResourcesService resourcesService;
	@Autowired
	RoleToResourcesService  roleToResourcesService;
	
	@Override
	protected String _loadPrefix() {
		return "role_";
	}

	@Override
	public String add(Role m, Model model) {
		model.addAttribute("resources", resourcesService.loadAll());
		return super.add(m, model);
	}

	@Override
	public String input(@PathVariable Integer id, Model model) {
		List<Resources> list = resourcesService.findAll();

		List<Resources> my = resourcesService.findByRoleId(id);
		List<ResourcesDto> dtos = new ArrayList<ResourcesDto>();
		for (Resources resources : list) {
			ResourcesDto dto = new ResourcesDto();
			BeanUtils.copyProperties(resources, dto);
			if (CollectionUtils.isNotEmpty(my)) {
				for (Resources myResources : my) {
					if (resources.getId().intValue() == myResources.getId().intValue()) {
						dto.setHasRole(true);
					}
				}
			}
			dtos.add(dto);
		}
		model.addAttribute("resources", dtos);
		return super.input(id, model);
	}
	
	
	
	@RequestMapping("saveAndResource")
	@ResponseBody
	public Map<String, Object> saveAndResource(Role m, HttpServletRequest request,Integer[] resourceIds) {
		Map<String, Object> back  =super.save(m, request);
		roleToResourcesService.build(m, resourceIds);
		return back;
	}
	
	
	
}