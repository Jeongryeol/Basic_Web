<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//세션에 담긴 모든 값 삭제하기
	session.invalidate();
	response.sendRedirect("../pojo_testMain.jsp");
%>