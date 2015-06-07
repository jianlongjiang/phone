package com.phone.cn.constant;

public interface ErrorCodeConstant {
	
	/** app 登入超时 自动登入   其他网页主动登入**/
	Integer  OUT_TIME_CODE = 1000000;
	String OUT_TIME_MSG = "登入超时";
	
	Integer  USER_FREEZE_CODE = 1000001;
	String USER_FREEZE_MSG = "用户冻结";
	
	
	Integer  THIRD_LOGIN_CODE = 1000002;
	String THIRD_LOGIN_MSG = "不支持该登入方式";
	
	Integer  ACCOUNT_EMAILL_MOBILE_EXIST_CODE = 1000003;
	String ACCOUNT_EMAILL_MOBILE_EXIST_MSG = "该账号已被注册使用";
	
	Integer  CERTIFICATION_CONDITION_CODE = 1000004;
	String CERTIFICATION_CONDITION_MSG = "认证用户才可以使用";
	
	Integer  CERTIFICATION_PERSION_CONDITION_CODE = 1000005;
	String CERTIFICATION_PERSION_CONDITION_MSG = "个人认证用户才可以使用";
	
	Integer  CERTIFICATION_COMPANY_CONDITION_CODE = 1000005;
	String CERTIFICATION_COMPANY_CONDITION_MSG = "公司认证用户才可以使用";
	
	Integer  MEMEBER_AUDIT_INFO_EXIST_CODE = 2000000;
	String MEMEBER_AUDIT_INFO_EXIST_MSG = "用户认证信息已提交";
	
	Integer  NULL_CODE = 2000001;
	String NULL_MSG = "空指针";
	
	Integer AUDIT_STATUS_HAS_DONE_CODE =  2000002;
	String  AUDIT_STATUS_HAS_DONE_MSG  = "认证信息已处理";
	
	Integer THIRD_ACCOUNT_HAS_BIND_CODE =  2000003;
	String  THIRD_ACCOUNT_HAS_BIND_MSG  = "该账号已被绑定";
	
}
