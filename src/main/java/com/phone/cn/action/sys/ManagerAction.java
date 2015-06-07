package com.phone.cn.action.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lianhai.spring.security.util.SpringSecurityContent;
import com.phone.cn.bean.sys.ManagerBean;
import com.phone.cn.bean.sys.RoleBean;
import com.phone.cn.conf.DataConfig;
import com.phone.cn.entity.sys.Manager;
import com.phone.cn.entity.sys.Role;
import com.phone.cn.service.sys.ManagerService;
import com.phone.cn.service.sys.ManagerToRoleService;
import com.phone.cn.service.sys.RoleService;
import com.phone.cn.utils.PasswordUtils;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:56:23
 *   
 */
@Controller
@RequestMapping(value="admin/manager")
public class ManagerAction extends BaseCRUDController<ManagerBean, Manager, Integer>{
	@Autowired
	RoleService roleService;
	@Autowired
	ManagerToRoleService  managerToRoleService;
	
	@Override
	protected String _loadPrefix() {
		return "manager_";
	}
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("login")
	public  String login(Model model){
		return  "admin/login";
	}

	/**
	 * 登入动作
	 */
	@ResponseBody
	@RequestMapping("loginIn")
	public Map<String, Object> loginIn(Model model, String account,
			String password, String yzm, HttpServletRequest request) {
		
		Manager  adminInfo = managerService.loadByUserName(account);
		if(StringUtils.isEmpty(yzm) || !yzm.equalsIgnoreCase(String.valueOf(request.getSession().getAttribute("yanzhengma")))){
			return fail("Verification code is wrong!");
		}
		
		if(adminInfo!=null){
			if(PasswordUtils.validPassword(adminInfo.getPassword(), password)){
				request.getSession().setAttribute(DataConfig.SESSION_ADMIN_USER, adminInfo);
				logger.debug("===========================================================");
				request.getSession().removeAttribute("yanzhengma");
				return suc(SUCCESS);
			}else{
				return fail("Password wrong！");
			}
		}else{
			return fail("UserName not exist！");
		}
		
	}
	
	
	@RequestMapping("index")
	public String  index(){
		Manager  manager = SpringSecurityContent.getUser();
		request.getSession().setAttribute(DataConfig.SESSION_ADMIN_USER, manager);
		return  "admin/manager/index";
	}
	

	@AdminUserLogin
	@Override
	public String add(Manager m, Model model) {
		model.addAttribute("roles", roleService.queryAll( new RoleBean() ));
		return super.add(m, model);
	}
	@AdminUserLogin
	@Override
	public String input(@PathVariable Integer id, Model model) {
		Role role = roleService.loadOneRole(id);
		if(role!= null){
			model.addAttribute("roleId", role.getId());
		}
		
		model.addAttribute("roles", roleService.queryAll( new RoleBean() ));
		return super.input(id, model);
	}
	
	@AdminUserLogin
	@Override
	public Map<String, Object> save(Manager m, HttpServletRequest request) {
		Map<String, Object>  back = super.save(m, request);
		managerToRoleService.build(m, m.getRoleId());
		return back;
	}
	
	@AdminUserLogin
	@RequestMapping("defaultPwd")
	@ResponseBody
	public Object defaultPwd(Manager  m){
		try {
			m.setPassword(PasswordUtils.encrypt("123456"));
			baseService.save(m);
			return suc(SUCCESS);
		} catch (Exception e) {
			return fail(FAIL);
		}
	}
	
	
	@RequestMapping("toModifyPwd")
	@AdminUserLogin
	public String toModifyPwd(){
		return viewName("modifyPwd");
	}
	
	@RequestMapping("modifyPwd")
	@ResponseBody
	@AdminUserLogin
	public Object  modifyPwd(Model moedl, String password,
			String oldPassword, HttpServletRequest request) throws Exception {
		Manager manager = SpringSecurityContent.getUser();
		manager = managerService.findOne(manager.getId());
		if(PasswordUtils.validPassword(manager.getPassword(), oldPassword)){
			manager.setPassword(PasswordUtils.encrypt(password));
			managerService.update(manager);
			return suc("密码修改成功");
		}else {
			return  fail("原密码不正确");
		}
	}
	
}
