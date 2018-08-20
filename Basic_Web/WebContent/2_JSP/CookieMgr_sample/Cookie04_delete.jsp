<%@page import="util.web.CookieMgr"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>쿠키삭제하기</title>
</head>
<body>

<h3>[내장객체로 삭제한 쿠키 : cookie1]</h3>

<%
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■[ [ 기 본 방 법 ] ]■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■ 내장객체 Cookie 클래스 활용하여 쿠키변경하기 ■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■	

	//각 개인 PC에 저장된 쿠키정보를 서버에 요청하고, 요청헤더에 쓰인 쿠키를 배열로 받음
	Cookie[] cs = request.getCookies();
 	
	int result = 0;//변경반영여부 확인할 변수
	
	//삭제하고자하는 쿠키의 이름
	String del_CookieName = "cookie1";
	out.print("<br>삭제하고자하는 쿠키의 이름 : "+del_CookieName+"<br><br>");
	
	if(cs!=null&&cs.length>0){				//쿠키배열객체 cs가 존재할 때
		for(int i=0;i<cs.length;i++){		//반복수행
			
			if("JSESSIONID".equals(cs[i].getName())) continue;
			
			//쿠키에 들어있는 이름을 얻음
			String cName = cs[i].getName();
			
			//쿠키에 들어있는 값과 사용자 선택 값이 같은지 비교
			if(del_CookieName.equals(cName)){
				
				//쿠키삭제하기 3단계
				Cookie delete_Cookie = new Cookie(cName,"");//쿠키에 들어있던 이름에 빈칸으로 초기화
				delete_Cookie.setMaxAge(0);					//최대시간 0 = 쿠키삭제
				response.addCookie(delete_Cookie);			//삭제된 내용을 쿠키에 반영하기
				
				result = 1;//수행했으므로 결과값을 1로 변경
				out.print("쿠키삭제결과가 1을 반영합니다. <br>");
				
			}////end : if
		}////////end : for
	}////////////end : if
	
	//수행결과에 대하여
	if(result==1){
		//response.sendRedirect("getCookieList.jsp");
		out.print("대상쿠키( "+del_CookieName+")의 쿠키삭제가 성공했습니다.");
	} else {
		//response.sendRedirect("cookieUpdateFail.jsp");
		out.print("대상쿠키( "+del_CookieName+")의 쿠키삭제에 실패했습니다. 이미 지워졌나봐요.");
	}
	out.print("<br><br>===============================================<br>");
%>

<h3>[CookieMgr 클래스로 삭제한 쿠키 : cookie2]</h3>

<%
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■[ [ 쿠키 매니저 ] ]■■■■■■■■■■■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	■■■■■ CookieMgr.java 클래스 활용하여 쿠기 변경하기 ■■■■■
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

	//인스턴스할때 생성자에 요청객체를 넘겨서 매니저클래스 내부에 쿠키값을 Map으로 준비해둠
	CookieMgr cm = new CookieMgr(request,response);

	String del_CookieName2 = "cookie2";
	out.print("<br>삭제하고자하는 쿠키의 이름 : "+del_CookieName2+"<br><br>");

	//쿠키가 존재할 때
	if(cm.checkCookie(del_CookieName2)){
		
		//해당 이름을 가진 쿠키 값 변경하기
		//변경 및 res객체담기 성공하면 1반환 / 실패하면 0반환
		int result2 = cm.deleteCookie(del_CookieName2);
		
		out.print("쿠키삭제결과가 1을 반영합니다. <br>");
	}

	//수행결과에 대하여
	if(result==1){
		//response.sendRedirect("getCookieList.jsp");
		out.print("대상쿠키( "+del_CookieName2+")의 쿠키삭제가 성공했습니다.<br>");
	} else {
		//response.sendRedirect("cookieUpdateFail.jsp");
		out.print("대상쿠키( "+del_CookieName2+")의 쿠키삭제에 실패했습니다. 이미 지워졌나봐요.<br>");
	}	
	
%>

<br>
<br>
<br>
아래 링크를 눌러 페이지를 이동하십시오.<br>
<br>
<a href="Cookie01_set.jsp">쿠키를 생성하는 페이지</a>

</body>
</html>