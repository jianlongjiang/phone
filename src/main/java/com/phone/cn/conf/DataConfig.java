package com.phone.cn.conf;

public interface DataConfig {
	
	/** 短信验证有效时间**/
	String  SMS_TIME = "smsTime";
    
	/**
	 * 入出库单类型
	 */
	String RELATE_TYPE_ORDER = "order";
	String RELATE_TYPE_REFUND = "refund";
 
	String  SESSION_ADMIN_USER = "adminUser";
 /**
  * 操作名称
  */
 String OP_NAME = "op";


 /**
  * 消息key
  */
 String MESSAGE = "message";

 /**
  * 错误key
  */
 String ERROR = "error";

 /**
  * 上个页面地址
  */
 String BACK_URL = "BackURL";

 String IGNORE_BACK_URL = "ignoreBackURL";

 /**
  * 当前请求的地址 带参数
  */
 String CURRENT_URL = "orrentURL";

 /**
  * 当前请求的地址 不带参数
  */
 String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

 String CONTEXT_PATH = "ctx";

 
 
 String  APP_TOKEN = "appToken";
 
 String  WATER_MARK = "waterMark.png";
 
 /**
  * 当前登录的用户
  */
 String CURRENT_USER = "user";
 String CURRENT_USERNAME = "username";
 String CURRENT_USER_SHOPINGCART_COUNT = "userShopingCart";
 
 /**
  * 当前登录的后台用户
  */
 String CURRENT_ADMIN_USER = "adminuser";
 String CURRENT_ADMIN_USERNAME = "adminusername";
 
 String COMP_USERCACHE = "comp_userCache";
 
 String APP_USER = "app_user";
 
 Integer DEFAULT_PRICE = 0;
 Integer AMOUNT_PRICE = 1; 
 Integer SKU_PRICE = 2;
 
 Integer MAX_COMMENT_DAYS = 3;
 
 /**  默认用户的信用值**/
 Integer DEFAULT_CREDIT_VALUE = 3;
 
 /**  认证通过加 信用值**/
 Integer AUTH_THROUGH = 5;
}
