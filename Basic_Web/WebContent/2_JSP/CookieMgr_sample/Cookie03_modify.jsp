<%@page import="java.net.URLEncoder"%>
<%@page import="util.web.CookieMgr"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쿠키수정하기</title>
</head>
<body>

<h3>[내장객체로 수정한 쿠키 : cookie1]</h3>

<%
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//■■■■■■■■■■■■■■[ [ 기 본 방 법 ] ]■■■■■■■■■■■■■■■
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//■■■■■■ 내장객체 Cookie 클래스 활용하여 쿠키변경하기 ■■■■■■
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
				//해당 이름의 쿠키가 가진 값을 수정하기
				Cookie cookie1 = new Cookie("cookie1",URLEncoder.encode("웹서버 내장객체로 만든 쿠키의 값을 바꿔 끼움","UTF-8"));
				//응답객체에 쿠키추가하기
				response.addCookie(cookie1);
			}
		}
	}
	
	out.print("<br>===============================================<br>");
%>

<h3>[CookieMgr 클래스로 수정한 쿠키 : cookie2]</h3>

<%
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■[ [ 쿠키 매니저 ] ]■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■ CookieMgr.java 클래스 활용하여 쿠기 변경하기 ■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	//인스턴스할때 생성자에 요청객체를 넘겨서 매니저클래스 내부에 쿠키값을 Map으로 준비해둠
	CookieMgr cm = new CookieMgr(request,response);
	
	//쿠키가 존재할 때
	if(cm.checkCookie("cookie2")){
		
		//해당 이름을 가진 쿠키 값 변경하기
		//변경 및 res객체담기 성공하면 1반환 / 실패하면 0반환
		int result = cm.changeCookieValue("cookie2","변경도 인코딩 안해도 됩니다.");
		
		//변경한 쿠키값 얻어오기
		String changedValue = cm.getCookieValue("cookie2");
		
		out.print("CookieMgr.java 클래스로 변경요청한 결과 : result = "+result+"<br>");
		out.print("CookieMgr.java 클래스로 변경요청한 쿠키의 값: cookie2 = "+changedValue+"<br>");
		
	}
	
%>

<br>
아래 링크를 눌러 페이지를 이동하십시오.<br>
<br>
<a href="Cookie04_delete.jsp">쿠키를 삭제하는 페이지</a>
</body>
</html>