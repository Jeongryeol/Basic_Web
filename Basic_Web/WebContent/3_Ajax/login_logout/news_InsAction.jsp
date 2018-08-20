<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Map, java.util.HashMap, java.net.URLEncoder" %>
<%
	//DB연동처리 - SKIP
	//사용자가 입력한 댓글 제목 
	String newsTitle = request.getParameter("n_title");
	//사용자가 입력한 댓글 내용
	String newsContent = request.getParameter("n_content");
	//get방식에대한 한글처리를 server.xml에 해둔 상태이므로 글자가 깨지지 않음
	
	//제목-값, 댓글-값 으로 "HashMap"에 담음
	Map<String,Object> rMap = new HashMap<String,Object>();
	rMap.put("n_title",newsTitle);
	rMap.put("n_content",newsContent);
	
	//응답으로 보냄 : 원래화면으로 돌아와야겠지??
	//response.sendRedirect("./news_index_ajax.jsp");

	//news_InsAction.jsp에서 받아온 값들을 news_index_ajax.jsp에서 사용할 수 있을까?
	//만약 가능하다면 방법은??
	//foward처리  또는 쿠키/세션처리
	//그러나 이번에는 이렇게 처리해보자
	newsTitle = URLEncoder.encode(newsTitle,"euc-kr");
	newsContent = URLEncoder.encode(newsContent,"euc-kr");
	response.sendRedirect("./news_index_ajax.jsp?n_title="+newsTitle+"&n_content="+newsContent);
	//받는쪽 news_index_ajax.jsp에서 변수선언하고 받아보자. 
%>