package com.phone.cn.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.sys.Message;
import com.phone.cn.mapper.sys.MessageMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:01:11
 *   
 */
@Service
public class MessageService extends BaseService<Message, Integer>{

	@Autowired
	MessageMapper  mapper;
	
	public void userReadAll(Integer  userId) {
		mapper.userReadAll(userId);
	}

	@Override
	protected void initSave(Message m) {
		if(m.getIsSee() == null)
			m.setIsSee(Boolean.FALSE);
		m.setDoStatus(Message.DoStatusEnum.add.getValue());
	}
	
}
