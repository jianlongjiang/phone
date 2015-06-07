package com.phone.cn.action.sys;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.sys.MessageBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.Message;
import com.phone.cn.service.sys.MessageService;
import com.phone.cn.web.action.BaseAppController;
import com.phone.cn.web.interceptor.AppUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月18日 下午12:19:16
 *   
 */
@Controller
@RequestMapping(value="app/message")
public class MessageAppAction extends BaseAppController<MessageBean, Message, Integer>{
	@Autowired
	private MessageService messageService;
	
	
	
	/**
	 * 系统回复的会话 list 
	 * @param baseApp
	 * @param pageNo
	 * @return
	 */
	@ResponseBody
	@AppUserLogin
	@RequestMapping(value="allinfo")
	public Map<String, Object> allinfo(BaseAppTokenBean baseApp , @Validated MessageBean bean){
//		MessageBean bean = new MessageBean();
		bean.setUserId(baseApp.getAppUser().getId());
		//bean.setPageSize(5);
		bean.setSort("create_time.desc");
		messageService.userReadAll(baseApp.getAppUser().getId());
		return suc(messageService.queryAllWithPage(bean, bean.toPageBounds()));
	}
	
	/**
	 *  加回复
	 * @param baseApp
	 * @param message content
	 * @return
	 */
	@ResponseBody
	@AppUserLogin
	@RequestMapping(value="add")
	public Object appSave(BaseAppTokenBean baseApp, Message message){
		//TODO   留言提交状态
		UserInfo userInfo = baseApp.getAppUser();
		message.setUserId(userInfo.getId());
		message.setUserName(userInfo.getUserName());
		message.setIsSee(true);
		message.setMobile(userInfo.getMobile());
		message.setAuditStatus("system");
		message.setCreateTime(new Date());
		message.setUpdateTime(new Date());
		baseService.save(message);
		return suc(SUCCESS);
	}
	
}
