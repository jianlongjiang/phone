package com.phone.cn.service.sys;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.RedPacketRuleBean;
import com.phone.cn.conf.enums.CashUserLogActionEnum;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.RedPacketRule;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.BaseService;
import com.phone.cn.service.member.UserInfoService;


@Service
public class RedPacketRuleService extends BaseService<RedPacketRule, Integer>{

	@Autowired
	UserInfoService userInfoService;
	
	Random random = new Random();
	
	/**
	 * 给用户发红包,  通过规则
	 * @param inviteeUser
	 * @throws SimpleException 
	 */
	public int sendRedPack(UserInfo userInfo, UserInfo from) throws SimpleException {
		List<RedPacketRule>  rules = loadAll();
		Integer  userRedCount = userInfo.getRedPackCount();
		userRedCount = userRedCount == null? 0: userRedCount;
		int index = userRedCount%rules.size();
		RedPacketRule rule = rules.get(index);
		int  money = random.nextInt(rule.getEnd() - rule.getStart()) + rule.getStart();
		userInfo.setRedPackCount(userRedCount+1); 
		userInfoService.addMoney(userInfo, CashUserLogActionEnum.SYS.getValue(), from.getMobile()+"推荐奖励",(double) money, false, null);
		
		return  money;
	}
	
	public void sendGroupRedPack(UserInfo userInfo, UserInfo from, int  groupMoney) throws SimpleException {
//		List<RedPacketRule>  rules = loadAll();
//		Integer  userRedCount = userInfo.getRedPackCount();
//		userRedCount = userRedCount == null? 0: userRedCount;
//		int index = userRedCount%rules.size();
//		RedPacketRule rule = rules.get(index);
//		int  money = random.nextInt(rule.getEnd() - rule.getStart()) + rule.getStart();
//		userInfo.setRedPackCount(userRedCount+1); 
		userInfoService.addMoney(userInfo, CashUserLogActionEnum.SYS.getValue(), from.getMobile()+"团队奖励",(double) groupMoney*0.1, false, null);
		
//		return  money;
	}
	
	
	
	
	
	
	public  List<RedPacketRule> loadAll(){
		RedPacketRuleBean  redPacketRuleBean = new RedPacketRuleBean();
		redPacketRuleBean.setSort("ruleId.asc");
		return  super.queryAll(redPacketRuleBean);
	}

}
