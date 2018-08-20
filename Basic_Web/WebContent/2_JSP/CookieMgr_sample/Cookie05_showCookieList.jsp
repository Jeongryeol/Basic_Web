<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="util.web.CookieMgr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>생성된 모든 쿠키를 보는 사이트</title>
</head>
<body>
<h2>쿠키를 생성합니다.</h2>
<%
	CookieMgr cm = new CookieMgr(request,response);
	cm.setCookie("ckNo1", "첫번째로 생성했던 쿠키");
	cm.setCookie("ckNo2", "두번째로 생성했던 쿠키");
	cm.setCookie("ckNo3", "세번째로 생성했던 쿠키");
	cm.setCookie("ckNo4", "네번째로 생성했던 쿠키");
%>
---------------------------
<h2>콘솔창에 전체 쿠키를 조회합니다.</h2>
<%
	cm.showAllCookie_console();
%>
</body>
</html>