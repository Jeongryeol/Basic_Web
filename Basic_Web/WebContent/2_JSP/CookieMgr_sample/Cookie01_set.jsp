<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="util.web.CookieMgr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Cookies클래스를 이용한 쿠키 생성하기</title>
</head>
<body>
<%
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■[ [ 기 본 방 법 ] ]■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■ 내장객체 Cookie 클래스 활용하여 쿠키생성하기 ■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	//쿠키생성하기
	Cookie cookie1 = new Cookie("cookie1",URLEncoder.encode("웹서버 내장객체로 만든 쿠키","UTF-8"));
	//응답객체에 쿠키추가하기
	response.addCookie(cookie1);
%>
<br>
쿠키생성이 완료되었습니다.<br>
<br>
<h3>[내장객체로 생성한 쿠키 : cookie1]</h3>

▼▼쿠키의 주소번지가 나옵니다<br>
	<font color="blue">
	생성하자마자 현재 페이지에서는 생성된 쿠키를 확인할 수 없습니다.<br></font>
Cookie[]
		= <%=request.getCookies() %><br>

▼▼이름이 나옵니다.<br>
cookie1.getName()
		= <%=cookie1.getName() %><br> 

▼▼값이 나옵니다.<br>
cookie1.getValue()
		= <%=URLDecoder.decode(cookie1.getValue(),"UTF-8")%><br>
<br>
<%
	out.print("===============================================<br>");

//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■[ [ 쿠키 매니저 ] ]■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■ CookieMgr.java 클래스 활용하여 쿠기 생성하기 ■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	//인스턴스할때 생성자에 요청객체를 넘겨기기 (최초실행시 쿠키 생성 전이므로 비어있는 로그가 찍힘)
	CookieMgr cm = new CookieMgr(request,response);
	//쿠키생성 및 응답객체에 쿠키추가하기
	cm.setCookie("cookie2","한글도 상관없는 쿠키매니저");
	
%>

<h3>[CookieMgr 클래스로 생성한 쿠키 : cookie2]</h3>

▼▼쿠키의 주소번지가 나옵니다<br>
	<font color="blue">
	생성하자마자 현재 페이지에서는 생성된 쿠키를 확인할 수 없습니다.<br>
	그러나 쿠키 매니저를 이용하면 응답객체에 더해진 쿠키를 새로고침하지않아도 불러올 수 있습니다.<br></font>
cm.getCookie("cookie2")
		= <%=cm.getCookie("cookie2")%><br>

▼▼쿠키의 이름이 나옵니다<br>
cm.getCookieName("cookie2")
		= <%=cm.getCookieNameFromValue("cookie2")%><br>

▼▼값이 나옵니다.<br>
cm.getCookieValue("cookie2")
		= <%=cm.getCookieValue("cookie2")%><br>


<br>
<br>
아래 링크를 눌러 페이지를 이동하십시오.<br>
<br>
<a href="Cookie02_get.jsp">쿠키를 읽어오는 페이지</a>

</body>
</html>