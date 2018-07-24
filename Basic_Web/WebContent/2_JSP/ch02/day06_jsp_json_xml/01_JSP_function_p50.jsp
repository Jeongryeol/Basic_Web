<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>HTML 문서제목</title>
</head>
<body>
<%
	/* JSP 역할
		1) 화면제공 (HTML5)
		2) 결과처리에 대한 페이지 작성
		3) json포맷으로 결과를 내보내기
		4) xml포맷으로 결과 내보내기 [중요] : miplatform, trusfrom, nexacro...
	*/

	String bookTitle = "JSP2.3 웹 프로그래밍";
%>
<%= bookTitle%>
</body>
</html>