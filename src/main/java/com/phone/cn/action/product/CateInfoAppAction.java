package com.phone.cn.action.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.product.CateInfoBean;
import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.web.action.BaseCRUDController;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:44:21
 * 
 */
@Controller
@RequestMapping(value = "app/mobilecate")
public class CateInfoAppAction extends
		BaseCRUDController<CateInfoBean, CateInfo, Integer> {
//	@Autowired
//	private CateInfoService cateInfoService;

	@RequestMapping("loadByParent")
	@ResponseBody
	public Object loadByParent(@RequestParam Integer parentId) {
		CateInfoBean bean = new CateInfoBean();
		bean.setParentCateId(parentId);
		return suc(baseService.queryAll(bean));
	}

}
