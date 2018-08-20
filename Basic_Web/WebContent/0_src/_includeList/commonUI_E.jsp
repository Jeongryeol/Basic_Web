<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%	
	//프로젝트 root path를 불러서 절대경로로 전환하기 : root path는 server-module에서 확인가능
	String Project_Root = "/Basic_Web";
%>
<!-- JQeury -->
	<script src="<%=Project_Root %>/0_src/js/jquery/jquery-1.12.0.js"></script>
<!-- EasyUI -->
	<script src="<%=Project_Root %>/0_src/js/jquery/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=Project_Root %>/0_src/css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=Project_Root %>/0_src/css/easyui/themes/icon.css?1">
	<link rel="stylesheet" type="text/css" href="<%=Project_Root %>/0_src/css/easyui/demo/demo.css">