<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List, java.util.Map" %>
<%-- src.util.db_mybatis._sample.mapper.SampleQuery.xml 파일의 id 값을 아래 Bean액션태그에 입력함 --%>
<jsp:useBean id="testMapSelect" scope="request" class="com.mybatis.Sample_DAO_MyBatis"/>
<%=
	"<b>@title</b>	MyBatis를 활용하여 CRUD 수행 및 리턴하기"+
"<br><b>@date</b>	2018.07.26"+
"<br><b>@author</b>	Jeongryeol Lee"+
"<br><b>@email</b>	duxbellorn@gmail.com"+
"<br><b>@comment</b>"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"이 jsp파일은 아래 두 경로의 자료를 토대로 준비된 자료입니다."+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"Java Resource/src/util/db_myBatis 및 하위폴더"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"Java Resource/src/com/ajax/mybatis/BasicDAO_MyBatis.java"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"<b><font color='red'>반드시 함께 자료를 확인하시고</font></b>"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"<b>Back-end에서 Front-end까지 자료가 연동되는 과정을 이해하시기 바랍니다.</b>"+
"<br>────────────────────────────────────────────<br>"
%>
<%
	//요청수행페이지에 화면단에서 요청객체(request)로 넘어올 조건값(사용자 입력)
	int param_num = 10;//시뮬레이션 클래스로, 그 값을 10이라는 테스트용 객체를 만들었음.
	out.print("<b>[ 알림 ]</b> 사용자가 "+param_num+"으로 검색요청하였습니다.<br>");
	
	out.print("<b>[ 알림 ]</b> DB로 자료조회를 요청합니다.<br>");
	List<Map<String,Object>> rlist 			 	 //반환된 자료를 담을 List객체
	 		= testMapSelect.getDataList(param_num);//DB로 조회요청을 하는 메소드로 조건값 전달
	//DB로부터 자료가 도착하여 수행페이지에 자료가 저장됨
	
	out.print("<b>[ 알림 ]</b> DB에서 요청한 자료가 도착하였습니다.<br>");
	out.print("<b>[ 알림 ]</b> 자료를 출력합니다.<br>");
	out.print("────────────────────────────────────────────</br>");
	//저장한 자료 화면에 출력하기
	if(rlist!=null){
		for(Map pMap:rlist){//개선된 for문
		out.print(pMap);	//List타입의 큰 집합에 Map타입으로 담긴 각 요소들을 하나씩 출력
		out.print("<br>");	//줄바꿈
		}
	}else{
		out.print("조회에 실패했습니다!<br>rlist = "+rlist);
	}
	//이 자료를 담는 과정을 거쳐서 화면에 띄우면 됩니다.
%>
