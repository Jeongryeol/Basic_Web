<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html> <!-- 이렇게 하면 HTML5 문서 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>JavaServer Pages(JSP) 코드선언규격</title>
</head>
<body>
<!-- 
	[[ 실행순서 ]]
	time.jsp -> time_jsp.java(서블릭) -> time_jsp.class(출력-html태그) - 브라우저
 -->

<!-- 실행된 JSP의 컴파일된 결과물 (java 및 class파일)이 모여있는 경로
E:\dev_kosmo201804\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\dev_jsp20180417\org\apache\jsp\ch02
time.jsp -> time_jsp.java(서블릿) -> time_jsp.class(출력-html태그) - 브라우저
 -->
<%!
	//디클러레이션(decleration)
	//	전역변수 선언가능
	//	메소드 선언가능 ( POJO class : 순수클래스  )
	//	인스턴스화 불가 : 재사용성이 떨어져서 거의 사용안함
	//	(WAS,브라우저마다 명명규칙이 달라짐)
	
	int i; // 전역변수(멤버변수,글로벌변수)
	public void methodA(){}
%>

<%
	//스크립틀릿(scriptlet) : service() {} 작동부
	//	지역변수 선언가능
	//	인스턴스화가능
	//	제어문 사용가능 : 조건문 / 반복문
	//	자료구조 출력가능 ( json / xml )
	//	ㄴjsp가 받도록 하는건 안됨 (MODEL2방식에서는 servlet이 받음)
	
	int i = 0; // 지역변수(로컬변수,오토매틱변수)
	out.print("스크립틀릿");
	out.print("<br>");//서블릿을 거쳐서 브라우저가 다운받는 <br>줄바꿈코드
%>
<br><!-- HTML문서로 브라우저가 다운받는 <br>코드 -->
<%=
	//익스프레션(expression)
	//	<td>태그안의 셀에  출력하는 용도로 주로 사용
	//	세미콜론(;)을 찍지 말자
	
	"출력"
%>
</body>
</html>