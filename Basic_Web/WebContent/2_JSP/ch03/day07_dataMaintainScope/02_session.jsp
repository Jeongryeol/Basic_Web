<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	session.setAttribute("mem_id", "test");
	session.setAttribute("bread", "소보로빵");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>session 테스트 :: Tomcat은 기본이 30분입니다.</title>
</head>
<body>
카트에 담긴 물건 목록 : <%= session.getAttribute("bread").toString() %>
<%
	String myName = "myName이 출력되었네요. 데이터가 유지됩니다.";
	request.setAttribute("myName",myName);
	
	//sendRedirect를 사용하면 URL이 바뀐다.
	//URL이 바뀌면 새로운 요청을 한 것으로 인식하므로 request의 정보가 유지가 되지 않음
		//즉 샌드리다이렉트는 요청이 들어간 페이지의 주소와 응답이 나오는 페이지의 주소가 다름
	//response.sendRedirect("03_session_account.jsp");
	
	//include - URL이 그대로 이므로 유지된다.
	
	//forward - URL이 바뀌지 않는데 페이지가 바뀌면서 유지됨.
		//즉, 포워드는 요청이 들어간 페이지의 주소와 응답이 나오는 페이지의 주소가 같다
	RequestDispatcher view = request.getRequestDispatcher("03_session_account.jsp");
	view.forward(request,response);
	/* 
		나는 분명 02_session.jsp를 요청(request객체)했는데,
		실제화면에 출력되는건 03_session_account.jsp페이지가 출력됩니다.
		톰켓서버가 요청이 유지되는 것으로 판단하여
		02_session의 "주소(URL)"로  03_session_account의 "화면"을 출력합니다.
	*/
%>
</body>
</html>