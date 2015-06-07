package com.phone.cn.action.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.phone.cn.bean.member.CashUserLogBean;
import com.phone.cn.conf.DataConfig;
import com.phone.cn.entity.member.CashUserLog;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.Manager;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.member.CashUserLogService;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.utils.ExcelView;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;


@Controller
@RequestMapping("admin/cashUserLog")
@AdminUserLogin
public class CashUserLogAdminAction extends BaseCRUDController<CashUserLogBean,CashUserLog,Integer> {
	
	UserInfoService  userInfoService;
	@Autowired
	private CashUserLogService cashUserLogService;
	
	@Override
	protected String _loadPrefix() {
		return "cashUserLog_";
	}

	@Override
	public String list(CashUserLogBean bean, Model model,@PathVariable Integer pageNo) {
		bean.setSort("update_time.desc");;
		return super.list(bean, model, pageNo);
	}
	
	
	/**
	 * 管理员  发红包
	 * @param userIds
	 * @param money
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addMoneys")
	public Object addMoneys(Integer[] userIds, Double  money  , HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute(DataConfig.SESSION_ADMIN_USER);
		try {
			for (Integer userId : userIds) {
				UserInfo userInfo =  userInfoService.findOne(userId);
				userInfoService.addMoney(userInfo, "admin", "管理严赠送红包",  money, false,  manager.getId());
			}
			return suc(SUCCESS);
		} catch (SimpleException e) {
				return fail(e.getMsg() , e.getCode());
		}
	}
	
	
	@RequestMapping(value = "exportExcel")
	public ModelAndView exportExcel(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, CashUserLogBean bean ) {
		ExcelView viewExcel = new ExcelView();
		Map<String, Object> obj = null;
		
		// 获取数据库表生成的workbook
		Map<String, Object> condition = new HashMap<String, Object>();
		HSSFWorkbook workbook = cashUserLogService.generateWorkbook2(condition, bean);
		try {
			viewExcel.buildExcelDocument(obj, workbook, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewExcel, model);
	}
	
	@RequestMapping("batchFail")
	@ResponseBody
	public Object batchFail(String ids){
		Integer flag =cashUserLogService.batchFail(ids);
		if (flag>0) {
			return suc(SUCCESS);
		}
		return fail(FAIL);
	}
	
	@RequestMapping("batchSuc")
	@ResponseBody
	public Object batchSuc(CashUserLogBean bean){
		List<CashUserLog> logs = cashUserLogService.queryAll(bean);
		for (CashUserLog log : logs) {
			log.setDoStatus("1");
			cashUserLogService.update(log);
		}
		return suc(SUCCESS);
	}
	
}
