package com.phone.cn.action.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.product.DownloadLogBean;
import com.phone.cn.entity.product.DownloadLog;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:54:43
 *   
 */
@Controller
@RequestMapping(value="admin/downlog")
@AdminUserLogin
public class DownloadLogAction extends BaseCRUDController<DownloadLogBean, DownloadLog, Integer>{

}
