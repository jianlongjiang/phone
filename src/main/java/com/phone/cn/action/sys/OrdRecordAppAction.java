package com.phone.cn.action.sys;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.sys.OrdRecordBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.OrdRecord;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.service.sys.SysSequenceService;
import com.phone.cn.web.action.BaseAppController;
import com.phone.cn.web.interceptor.AppUserLogin;


@AppUserLogin
@RequestMapping("app/ordRecord")
@Controller
public class OrdRecordAppAction extends BaseAppController<OrdRecordBean, OrdRecord, Integer> {
	@Autowired
	SysSequenceService sysSequenceService;
	@Autowired
	private SysConfigService  sysConfigService;
	
	
	/**
	 * 生成 支付订单
	 * @param baseApp
	 * @return
	 */
	@AppUserLogin
	@RequestMapping("orderGenerate")
	@ResponseBody
	public   Object  orderGenerate(BaseAppTokenBean baseApp){
		UserInfo  userInfo = baseApp.getAppUser();
		
		if(userInfo.getIsVip()){
			return fail("已经是金蜗牛!");
		}
		OrdRecordBean bean = new OrdRecordBean();
		bean.setUserId(userInfo.getId());
		bean.setTradeType(OrdRecord.TradeTypeEnum.no_pay.getValue());
		//OrdRecord ordRecord = baseService.selectOneByExample(bean);
		List<OrdRecord> ordRecords =  baseService.queryAll(bean);
		for (OrdRecord ordRecord2 : ordRecords) {
			baseService.delete(ordRecord2.getId());
		}
		
//		if(ordRecord == null){
		OrdRecord	ordRecord = new OrdRecord();
			ordRecord.setUserId(userInfo.getId());
			ordRecord.setUserName(userInfo.getUserName());
			ordRecord.setTradeType(OrdRecord.TradeTypeEnum.no_pay.getValue());
			// 订单code
			String code = "APP"+ ymdFormat().format(Calendar.getInstance().getTime())
					+ StringUtils.leftPad(
							sysSequenceService.nextSequence("beViip", true)
									.getSeqValue(), 6, "0");
			ordRecord.setOuttradeno(code);
			ordRecord.setTradeCount(Double.parseDouble(sysConfigService.findOne(20).getConfigValue()));
//			ordRecord.setTradeCount(0.01);
			ordRecord.setContentType("order");
			baseService.save(ordRecord);
//		}
		return suc(ordRecord);
	}

	
	
	/**
	 * RecordId 
	 * @return
	 */
	@RequestMapping("cancelOrder")
	@ResponseBody
	public  Object  cancelOrder(BaseAppTokenBean baseAppTokenBean , OrdRecord ordRecord){
		UserInfo userInfo =  baseAppTokenBean.getAppUser();
		OrdRecord  oRecord = baseService.findOne(ordRecord.getRecordId());
		if(oRecord != null){
			if(oRecord.getUserId().intValue() != userInfo.getId()){
				return fail("非本人");
			}
			
			if(!StringUtils.equals(oRecord.getTradeType(), OrdRecord.TradeTypeEnum.no_pay.getValue())){
				return fail("不可删除");
			}
			
			baseService.delete(ordRecord.getRecordId());
			return  suc(SUCCESS);
		}
		
		return  fail("订单不存在");
	}
	
///	public 
	private static ThreadLocal<SimpleDateFormat> TRADE_DATE_FORMATs = new ThreadLocal<SimpleDateFormat>();
	public static SimpleDateFormat ymdFormat() {
		SimpleDateFormat simpleDateFormat = TRADE_DATE_FORMATs.get();
		if (simpleDateFormat == null) {
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			TRADE_DATE_FORMATs.set(simpleDateFormat);
		}
		return simpleDateFormat;
	}
	
	@RequestMapping("receiveTime")
	@ResponseBody
	public Object   receiveTime(){
		Date date = new Date();
		date = DateUtils.addDays(date, 2);
		return suc(date);
	}
	
}
