package com.phone.cn.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.member.UserMoreBean;
import com.phone.cn.entity.member.UserMore;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.mapper.member.UserMoreMapper;
import com.phone.cn.service.BaseService;

@Service
public class UserMoreService extends BaseService<UserMore, java.lang.Integer> {
	
	@Autowired
	private UserMoreMapper mapper;
	
	@Autowired
	UserInfoService userInfoService;
	
	/**
	 * 绑定支付宝
	 * @param id
	 * @param alipayAccount
	 * @param alipayRealName
	 * @return
	 * @throws SimpleException 
	 */
	public Boolean bindAlipay(Integer id,String alipayAccount,String alipayRealName, String password) throws SimpleException{
		int count= 0;
		UserMore userMore = mapper.selectByPrimaryKey(id);
		if (userMore==null) {
			UserMore user = new UserMore();
			user.setId(id);
			user.setAlipayAccount(alipayAccount);
			user.setAlipayRealName(alipayRealName);
			count = mapper.insert(user);
		}else {
//			if(!StringUtils.equalsIgnoreCase(oldAccount, userMore.getAlipayAccount())) {
//				throw new SimpleException("原支付宝账号不正确");
//			}
//			UserInfo u = userInfoService.findOne(id);
//			if(!PasswordUtils.validPassword(u.getPassword(), password)){
//				throw new SimpleException("原支付宝账号不正确");
//			}
			
			userMore.setAlipayAccount(alipayAccount);
			userMore.setAlipayRealName(alipayRealName);
			count = mapper.updateByPrimaryKey(userMore);
		}
		if (count==1) {
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;	
		}
	}
	
	public boolean updateUser(Integer id,UserMoreBean userMoreBean){
		int count= 0;
		UserMore userMore = mapper.selectByPrimaryKey(id);
		if (userMore==null) {
			UserMore user = new UserMore();
			user =userMoreBean;
			user.setId(id);
			count = mapper.insert(user);
		}else {
			userMore.setReceiverName(userMoreBean.getReceiverName());
			userMore.setMobile(userMoreBean.getMobile());
			userMore.setZone(userMoreBean.getZone());
			userMore.setAddress(userMoreBean.getAddress());
			userMore.setMailCode(userMoreBean.getMailCode());
			count = mapper.updateByPrimaryKey(userMore);		
		}
		if (count==1) {
			return Boolean.TRUE;
		}else{
			return Boolean.FALSE;	
		}
	}



	public boolean saveOrUpdateForUser(UserMore userMore) {
		UserMore  check = findOne(userMore.getId());
		if(check == null){
			mapper.insertSelective(userMore);
		}
		// 插入时 处里2次是因为 有默认时间初始化处里, 
		return super.isSave(userMore);
	}
}
