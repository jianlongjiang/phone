package com.phone.cn.action.alipay;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.ResultBean;
import com.phone.cn.conf.enums.SysConfigEnum;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.OrdRecord;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.sys.OrdRecordService;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.utils.AlipayNotify;
import com.phone.cn.utils.JsonMapper;

/**
 * @author zgdcool
 * @version 2015年2月1日 上午11:14:04
 * 
 */
@Controller
public class AlipayApiAction {

	@Autowired
	private OrdRecordService ordRecordService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SysConfigService sysConfigService;

	
	
	
	
	
	@RequestMapping(value = "ordernotifyurl")
	public String ordernotifyurl(HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("apphuidiaochenggong--------------------------------------------------------------");
		Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
		// String total_fee = new
		// String(request.getParameter("total_fee").getBytes("ISO-8859-1"),
		// "UTF-8");
		boolean verify_result = AlipayNotify.verify(params);
		if (verify_result) {// 验证成功
			app_alipay(out_trade_no, trade_no, trade_status);
			if (out_trade_no.startsWith("MORD")) {
				return "redirect:/mobile/order/list/p1/";
			} else {
				return "redirect:/cus/order/list/p1/";
			}
		} else {
			return "验证失败";
		}
	}



	
	@RequestMapping("test_alipay")
	@ResponseBody
	public  Object  test_alipay(String  out_trade_no ){
		app_alipay(out_trade_no , System.currentTimeMillis()+"", "TRADE_FINISHED");
		ResultBean resultBean = ResultBean.suc("测试成功");
		return  JsonMapper.beanToMap(resultBean);
	}


	/**
	 * 
	 * @param out_trade_no  自己的商业编号
	 * @param trade_no		支付宝的 id
	 * @param trade_status  TRADE_FINISHED
	 */
	private void app_alipay(String out_trade_no, String trade_no, String trade_status) {
		if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
			OrdRecord order = ordRecordService.findByOrderCode(out_trade_no);
			if (StringUtils.isNotEmpty(order.getTradeType()) && order.getTradeType().equals(OrdRecord.TradeTypeEnum.pay_ok.getValue())) {
			} else {
				if (order != null) {
					order.setTradeType(OrdRecord.TradeTypeEnum.pay_ok.getValue());
					order.setTradeno(trade_no);
					// Double d = Double.parseDouble(total_fee);
					// order.setTradeCount(tradeCount);
					ordRecordService.save(order);
					UserInfo userInfo = userInfoService.findOne(order.getUserId());
					Double beVip = Double.parseDouble(sysConfigService.findOne(SysConfigEnum.to_be_vip.getValue()).getConfigValue());
					Double balance = userInfo.getBalance() + order.getTradeCount() - beVip;
					userInfo.setBalance(balance);
					userInfo.setIsVip(true);
					userInfo.setVipTime(new Date());
					userInfoService.save(userInfo);
					try {
						userInfoService.toBeVip(userInfo);
					} catch (SimpleException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
