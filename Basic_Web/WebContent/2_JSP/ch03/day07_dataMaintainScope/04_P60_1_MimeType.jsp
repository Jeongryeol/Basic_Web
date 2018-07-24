<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!--
	JSP의 역할
	1) 화면처리 : 회원가입화면이나 로그인화면같은..
	2) json포맷 파일 생성 : xxx.json
		json의 경우, 구글의 Gson을 활용하므로 해당없음
	3) xml포맷 파일 생성 : xxx.xml
	 	xml의 경우, 개발자가 인위적으로 파일 포맷을 생성하므로
	 	페이지다이렉티브자리의 공백줄이 들어가는 문제 해결이 필요함
 -->
<%
	//MIME TYPE 변경시 response객체 활용합니다.
	//response.setContentType("text/xml;charset=euc-kr");
	out.print("<table border='1' width='300'>");
	out.print("<tr><td>404호</td></tr>");
	out.print("<tr><td>공과대학교 6강의동</td></tr>");
	out.print("</table>");
%>