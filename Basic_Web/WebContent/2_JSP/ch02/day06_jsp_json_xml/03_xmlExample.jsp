<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
	//마임타입 수정	
	response.setContentType("text/xml");
	
	//자료구조 생성하기
	List<Map<String,Object>> deptList = new ArrayList<Map<String,Object>>();
		//row-1st
	Map<String,Object> rMap	= new HashMap<String,Object>();
	rMap.put("deptno",10);
	rMap.put("dname","영업");
	rMap.put("loc","서울");
	deptList.add(rMap);
		//row-2nd
	rMap = new HashMap<String,Object>();
	rMap.put("deptno",20);
	rMap.put("dname","총무");
	rMap.put("loc","인천");
	deptList.add(rMap);
		//row-3rd
	rMap = new HashMap<String,Object>();
	rMap.put("deptno",30);
	rMap.put("dname","인사");
	rMap.put("loc","서울");
	deptList.add(rMap);
	
	//xml tree 구조 만들기
	StringBuilder sb = new StringBuilder();
	//sb.append("<?xml version=""\1.0""\ encoding=""\euc-kr""\?>");
	sb.append("<deptLists>");
	for(int i=0;i<deptList.size();i++){//개별로우 다시 뽑기
		if(i==deptList.size())break;
		Map<String,Object> reMap = (Map<String,Object>)deptList.get(i);
		sb.append("<deptList>");
		sb.append("<deptno>");
		sb.append(reMap.get("deptno"));
		sb.append("</deptno>");
		sb.append("<dname>");
		sb.append(reMap.get("dname"));
		sb.append("</dname>");
		sb.append("<loc>");
		sb.append(reMap.get("loc"));
		sb.append("</loc>");
		sb.append("</deptList>");
	}
	sb.append("</deptLists>");
	
	//잊지말자...출력....
	out.print(sb.toString());
%>