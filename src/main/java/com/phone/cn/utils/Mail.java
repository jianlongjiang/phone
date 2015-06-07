package com.phone.cn.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
	private String host = "smtp.163.com";//电子信箱服务器(不同的服务器使用不同)

	private String mail_head_name = "this is head of this mail";

	private String mail_head_value = "this is head of this mail";

	private String mail_to = "";

	private String mail_from = "bohaihuijin@163.com";

	private String mail_subject = "test title";

	private String mail_body = "test body";

	private String personalName = "博海汇金找回密码";

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMail_head_name() {
		return mail_head_name;
	}

	public void setMail_head_name(String mail_head_name) {
		this.mail_head_name = mail_head_name;
	}

	public String getMail_head_value() {
		return mail_head_value;
	}

	public void setMail_head_value(String mail_head_value) {
		this.mail_head_value = mail_head_value;
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	public String getMail_from() {
		return mail_from;
	}

	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}

	public String getMail_subject() {
		return mail_subject;
	}

	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}

	public String getMail_body() {
		return mail_body;
	}

	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	
	public void sendMail(String mail_to,String mail_body, String personalName) throws SendFailedException {
		try {
			Properties props = new Properties();// 获取系统环境
			Authenticator auth = new Email_Autherticator();// 进行邮件服务用户认证
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			System.out.println(props);
			Session session = Session.getDefaultInstance(props, auth);// 设置session,和邮件服务器进行通讯
			
			MimeMessage message = new MimeMessage(session);
			message.setContent(mail_body, "text/html;charset=utf-8");// 设置邮件格式
			message.setSubject(personalName);// 设置邮件主题
//			message.setText(mail_body);// 设置邮件内容
			message.setHeader(mail_head_name, mail_head_value);// 设置邮件标题
			message.setSentDate(new Date());// 设置邮件发送时期
			Address address = new InternetAddress(mail_from, personalName);
			message.setFrom(address);// 设置邮件发送者的地址
			Address toaddress = new InternetAddress(mail_to);// 设置邮件接收者的地址
			message.addRecipient(Message.RecipientType.TO, toaddress);
			System.out.println(message);
			Transport.send(message);
			System.out.println("Send Mail Ok!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return flag;
	}

}

