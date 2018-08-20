<%@page import="util.web.CookieMgr"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	CookieMgr cm = new CookieMgr(request,response);
%>
<script>
	alert("로그인에 실패하셨습니다. (code:<%=cm.getCookieValue("logOnStatus") %>)");
	console.log("회원가입실패");
	history.back();
	//histroy.go(-1);
</script>