<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//���ǿ� ��� ��� �� �����ϱ�
	session.invalidate();
	response.sendRedirect("../pojo_testMain.jsp");
%>