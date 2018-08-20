<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="util.web.CookieMgr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쿠키 읽어오기</title>
</head>
<body>

<h3>[내장객체로 읽어온 쿠키 : cookie1]</h3>

<%
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//■■■■■■■■■■■■■■[ [ 기 본 방 법 ] ]■■■■■■■■■■■■■■■
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//■■■■■■ 내장객체 Cookie 클래스 활용하여 쿠키읽어오기 ■■■■■■
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■	
	
	//요청객체 헤더에 담긴 쿠키를 모두 얻어서 배열로 담음
	Cookie[] cookies = request.getCookies();
	
	//배열에 담긴 쿠키가 존재할때
	if(cookies!=null&&cookies.length>0){
		//배열의 마지막까지 반복수행함
		for(int i=0;i<cookies.length;i++){	
			
			//JSESSIONID는 걸러냄
			if("JSESSIONID".equals(cookies[i].getName())) continue;
		
			//각방에 담긴 쿠키의 이름 읽어오기
			out.print("넘어온 쿠키["+i+"] : "+cookies[i].getName()+"<br>");
			
			//이름조건이 맞으면
			if("cookie1".equals(cookies[i].getName())){
				//쿠키에 담긴 name속성 출력하기
				out.print("내장객체로 읽은 쿠키의 값 : "+URLDecoder.decode(cookies[i].getValue(),"UTF-8")+"<br>");
			}
		}
	}
	
	out.print("<br>===============================================<br>");
%>

<h3>[CookieMgr클래스로 읽은 쿠키 : cookie2]</h3>

<%	
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■[ [ 쿠키 매니저 ] ]■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■ CookieMgr.java 클래스 활용하여 쿠기 읽어오기 ■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	//인스턴스할때 생성자에 요청객체를 넘겨서 매니저클래스 내부에 쿠키값을 Map으로 준비해둠
	CookieMgr cm = new CookieMgr(request,response);
	
	//쿠키가 존재할 때
	if(cm.checkCookie("cookie2")){
		
		//해당 이름을 가진 쿠키 읽기(주소번지)
		Cookie c = cm.getCookie("cookie2");
	
		//해당 이름을 가진 쿠키 읽기(주소번지)
		String cn = cm.getCookieNameFromValue("cookie2");	
		
		//해당 이름을 가진 쿠키 값읽기
		String cv = cm.getCookieValue("cookie2");			
		
		out.print("CookieMgr.java 클래스로 대상을 선택해서 읽은 쿠키주소번지 : "+c+"<br>");
		out.print("CookieMgr.java 클래스로 대상을 선택해서 읽은 쿠키이름 : "+cn+"<br>");
		out.print("CookieMgr.java 클래스로 대상을 선택해서 읽은 쿠키의 값 : "+cv+"<br>");
	}
%>



<br>
아래 링크를 눌러 페이지를 이동하십시오.<br>
<br>
<a href="Cookie03_modify.jsp">쿠키를 수정하는 페이지</a>
</body>
</html>