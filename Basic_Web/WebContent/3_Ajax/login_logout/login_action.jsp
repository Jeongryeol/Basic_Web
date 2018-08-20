<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//DB에서 조회해야하는 정보를 아래 코드로 대체한다
	String db_id = "test";
	String db_pw = "123";
	String db_name = "null";
	//아이디와 비밀번호가 일치했을때 SELECT문으로 조회해서 얻어올 것.
	
	//화면에서 사용자가 입력한 아이디와 비밀번호를 담자
	//사용자가 입력한 값을 얻어낼때 필요한 코드! [기술면접 단골코드]
	String u_id = request.getParameter("mem_id");
	String u_pw = request.getParameter("mem_pw");
	
	/*
		<< 어느쪽이 더 안전한 코딩인가요? >>
			if(db_id.equals(u_id)){
			if(u_id.equals(db_id)){
		
		<< 답 : db_id.equals(u_id)>>
			대상객체의 equals(비교객체)메소드는 대상객체와 비교객체가 같은지를 비교한다.
			이때 대상객체가 null인 경우, NullPointException이 발생할 위험이 있으므로
			객체의 equals로 값을 비교할때는 null값이 입력될 여지가 있는 객체가 비교객체로 사용되어야한다.
		
		<< 참고 ==와는 어떻게 다른가요? >>
		[ == ]
			tests for reference equality
			(whether they are the same object).
		[ .equals() ]
			tests for value equality
			(whether they are logically "equal").
	*/
			
	if(db_id.equals(u_id)){//아이디가 일치하나요?
		if(db_pw.equals(u_pw)){//비밀번호도 일치하나요?
			db_name="관리자";
	
	
%>
<table width="250px" height="60px" border="1" bordercolor="red">
	<tr>
	<td align="center">
		<%out.print(db_name);%>님 환영합니다.
	</td>
	<!-- 로그아웃버튼-->
	<td width="80px" align="center">
	<input type="button" id="btn_logout" value="LOGOUT" onClick="javascript:logout()">
	</td>
</tr>
</table>
<%
		}else{//비밀번호가 틀렸네요
			out.print("비밀번호를 확인 바랍니다.");
		}
	}else{//아이디가 틀렸네요
		out.print("아이디를 확인 바랍니다.");
	}
		
		
%>
<!-- 
	스크립틀릿( <% %> )과 HTML태그( <TAG> )에 대한 비교 고찰
	
	1) 스크립틀릿과 html태그의 순서는 페이지 처리를 하는데 영향이 있다.|없다
		-만일 오라클 서버에서 조회한 결과를 <table>태그에 출력해야하는 경우라면, 순서에 영향이 있을 수 있다.
		-그러나 만일 자바코드에서 처리한 결과를 화면에 반영하는 경우가 아니라면, 영향이 없는 경우도 있다.
		-기본적으로 순서에 영햐이 없는 이유는 스크립틀릿, 즉 자바코드는 html태그보다 먼저 서버측에서 실행되기 때문이다.
		-그리고 그 결과가 html페이지에 출력되는 것이므로 위치에 의한 영향은 없다.
	
	2) 두 언어중 우선순위는 스크립틀릿이 먼저다
		-이 질문에서의 우선순위는 서버측에서 실행되는지 아니면 클라이언트 측에서 실행되는지에 대한 질문이다.
		-서버측에서 스크립틀릿의 실행이 먼저일어나서 결과를 html페이지로 출력하고 이후에 html태그와 함께 실행된다.

	3) 두 언어의 처리주체는 다르다.
		-서블릿 혹은 스크립틀릿(자바코드)는 모두 Tomcat서버가 "처리주체"이다
		-HTML태그와 스크립트, css는 브라우저가 "실행주체"이다.
	
	4) 선언과 사용에는 순서가 필요하다.
		-반드시 선언이 먼저이다. 선언이후 사용이 되어야한다.
		-넘어온 값을 받고자 한다면 페이지 상단에 스크립틀릿으로 변수를 선언한뒤에 필요한 위치에서 값을 받도록 한다.
	
	5) 자바로 선언한 변수를 <table>태그에 출력할때는
		-반드시 <table>태그 앞에 와야한다.
		-<tr>태그를 for문으로 돌려서 DB에서 조회된 결과를 <table>태그에 출력해야하기때문이다.
		-<table>태그에 출력하기전에 변수에 조회한 값을 먼저 담아주고 테이블에 담는 순서로 생각한다. 
 -->

