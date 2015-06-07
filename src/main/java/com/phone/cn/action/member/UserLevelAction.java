package com.phone.cn.action.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.member.UserLevelBean;
import com.phone.cn.entity.member.UserLevel;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:49:27
 *   
 */
@Controller
@RequestMapping(value="admin/userlevel")
@AdminUserLogin
public class UserLevelAction extends BaseCRUDController<UserLevelBean, UserLevel, Integer>{

}
