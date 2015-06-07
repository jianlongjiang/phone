package com.phone.cn.action.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lianhai.spring.security.util.SpringSecurityContent;
import com.phone.cn.bean.sys.MessageBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.Manager;
import com.phone.cn.entity.sys.Message;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:56:43
 *   
 */
@Controller
@RequestMapping(value="admin/message")
@AdminUserLogin
public class MessageAction extends BaseCRUDController<MessageBean, Message, Integer>{
		
	@Autowired
	private UserInfoService userInfoService;
	
	
	@Override
	public Map<String, Object> save(Message m, HttpServletRequest request) {
		Manager manager = SpringSecurityContent.getUser();
		m.setIsSee(false);
		m.setManagerId(manager.getId());
		return super.save(m, request);
	}
	
	
	
	@ResponseBody
	@RequestMapping("replayAllByAdmin")
	public Object replayAllByAdmin(Model model, Integer[] userIds, String msg){
		if(userIds != null){
			for (Integer userId : userIds) {
				UserInfo userInfo = userInfoService.findOne(userId);
				Message message = new Message();
//				message.setId(userId);
				message.setTitle("系统回复");
				message.setContent("系统系统回复");
				// TODO  管理严给 用户发信息.. 状态待设置
//				message.setAuditStatus(Messag);
				message.setUserName(userInfo.getUserName());
				message.setMobile(userInfo.getMobile());
				message.setUserId(userId);
				message.setReplyContent(msg);
				message.setDoStatus("1");
				message.setIsSee(false);
				 baseService.save(message);
			}
			return suc(SUCCESS);
		}
		return fail(FAIL	);
	}
	
	
	
	@ResponseBody
	@RequestMapping("replayAll")
	public Object replayAll(Model model, Integer[] ids, @RequestParam String msg){
		Manager manager = SpringSecurityContent.getUser();
		if(ids != null  ){
			for (Integer id : ids) {
				Message message = new Message();
				message.setId(id);
				message.setReplyContent(msg);
				message.setIsSee(false);
				message.setManagerId(manager.getId());
				message.setDoStatus(Message.DoStatusEnum.suc.getValue());
				 baseService.save(message);
			}
			return suc(SUCCESS);
		}
		return fail(FAIL	);
	}
}
