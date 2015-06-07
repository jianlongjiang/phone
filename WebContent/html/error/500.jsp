<%@page isErrorPage="true" pageEncoding="UTF-8" %><%@page contentType="text/html; charset=UTF-8"%>
<%
	String uri = (String)request.getAttribute("com.qfc.pf.web.requesturl");
	String qstr = (String)request.getAttribute("com.qfc.pf.web.requestquery");
	org.slf4j.LoggerFactory.getLogger(this.getClass()).error("Page raised error：{}, referer & user-agent: {}", 
			uri + ( qstr == null ? "" : ( "?" + qstr ) ), 
			request.getHeader("referer") + " " + request.getHeader("user-agent"));
	
	if(exception!=null)
		exception.printStackTrace();
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>网上轻纺城 - 系统错误</title>
<link href="${request.contextPath}/html/css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>


<div style="width:950px;margin:71px auto 0; border:1px solid #EDEDED; text-align:center"><a href="http://www.qfc.cn/"><img src="http://img.qfc.cn/images/mem/common/404.gif" border="0" /></a></div>
</body>
</html>