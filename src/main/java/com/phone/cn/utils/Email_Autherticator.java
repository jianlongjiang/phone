package com.phone.cn.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class Email_Autherticator extends Authenticator {

	String username = "bohaihuijin@163.com";//服务器邮箱

	String password = "Haibo123456";//服务器邮箱密码

	public Email_Autherticator() {
		super();
	}

	public Email_Autherticator(String user, String pwd) {
		super();
		username = user;
		password = pwd;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}