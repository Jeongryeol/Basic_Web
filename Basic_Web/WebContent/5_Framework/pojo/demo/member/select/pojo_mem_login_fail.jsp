<%@page import="util.web.CookieMgr"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	CookieMgr cm = new CookieMgr(request,response);
%>
<script>
	alert("�α��ο� �����ϼ̽��ϴ�. (code:<%=cm.getCookieValue("logOnStatus") %>)");
	console.log("ȸ�����Խ���");
	history.back();
	//histroy.go(-1);
</script>