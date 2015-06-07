package com.phone.cn.action.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.product.NewsCateBean;
import com.phone.cn.entity.product.NewsCate;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:52:03
 *   
 */
@Controller
@RequestMapping(value="admin/newscate")
@AdminUserLogin
public class NewsCateAction extends BaseCRUDController<NewsCateBean, NewsCate, Integer>{

}
