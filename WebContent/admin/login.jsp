<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
		<form action="/j_spring_security_check" method="post">  
    Account：<Input name="username"/><br/>  
    Password：<input name="password" type="password"/><br/>  
    验证码:   <input name="validateCode" type="text"/><br/>
    
    <input value="submit" type="submit"/>  
   </form>  
</body>
</html>