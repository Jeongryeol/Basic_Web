<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.List, java.util.Map, java.util.ArrayList"%>
<%@ page import = "java.util.HashMap" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page trimDirectiveWhitespaces = "true" %>
<%-- <!-- 이 주석은 보임, HTML주석 :: json포맷이나 xml포맷에선 형식이 안맞기 때문에 절대 사용 금지--> --%>
<%-- 이 주석은 안보임, JSP주석 :: json포맷이나 xml포맷에서도 왠만하면 사용하지 않는편이 좋음 --%>
<%
	// @ page trimDirectiveWhitespaces = "true"
	//		xml포맷이나 json포맷으로 내보낼 때, 공백을 제거해줌
	//		HTML소스상의 공백이 에러가 발생시킬 우려가 있음.
	
	List<Map<String,Object>> deptList
		= new ArrayList<Map<String,Object>>();
	Map<String,Object> rMap
		= new HashMap<String,Object>();
	
	rMap.put("deptno",10);
	rMap.put("dname","영업");
	rMap.put("loc","서울");
	deptList.add(rMap);
	
	rMap = new HashMap<String,Object>();
	rMap.put("deptno",20);
	rMap.put("dname","총무");
	rMap.put("loc","인천");
	deptList.add(rMap);
	
	rMap = new HashMap<String,Object>();
	rMap.put("deptno",30);
	rMap.put("dname","인사");
	rMap.put("loc","서울");
	deptList.add(rMap);
	
	//out.print(deptList);
	
	Gson gs = new Gson();
	String jsonDept = gs.toJson(deptList);
	
	out.print(jsonDept);
%>