<%@page pageEncoding="UTF-8" %><%
	String uri = (String)request.getAttribute("com.qfc.pf.web.requesturl");
	org.slf4j.LoggerFactory.getLogger(this.getClass()).error("Page Is Restricted：{}, referer: {}",uri, request.getHeader("referer"));
%>
Page Is Restricted : <%=uri%>