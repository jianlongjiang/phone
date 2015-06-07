//package com.phone.cn.action.sys;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.phone.cn.bean.BaseAppTokenBean;
//import com.phone.cn.bean.sys.RedPackLogBean;
//import com.phone.cn.entity.member.UserMore;
//import com.phone.cn.entity.sys.RedPackLog;
//import com.phone.cn.service.member.UserMoreService;
//import com.phone.cn.service.sys.RedPackLogService;
//import com.phone.cn.utils.JsonMapper;
//import com.phone.cn.web.action.BaseCRUDController;
//
//@RequestMapping("app/redPack")
//@Controller
//public class RedPackLogAppAction extends BaseCRUDController<RedPackLogBean, RedPackLog, Integer>{
//	
//	@Autowired
//	private RedPackLogService redPackLogService;
//	@Autowired
//	private UserMoreService userMoreService;
//	
//	@RequestMapping("reflectMoney")
//	public Map<String, Object> reflectMoney(BaseAppTokenBean baseApp){
//		Integer id = 5 ;
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("redPackLog", redPackLogService.findUserId(id));
//		map.put("userMore", userMoreService.findOne(id));
//		return JsonMapper.beanToMap(map);
//	}
//
//}
