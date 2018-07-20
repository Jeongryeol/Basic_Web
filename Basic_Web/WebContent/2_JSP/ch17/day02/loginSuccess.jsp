<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//자바영역
	String result = request.getParameter("result");
	if("1".equals(result)){
	//서블릿으로 처리한다면 out.print()안에 태그를 모두 담아야 됨. 직관성이 매우 떨어짐
	//jsp에는 자바코드가 없었으면 좋겠다.
	//서블릿의 라이프 사이클은 톰캣이 관리하고
	//내(개발자)가 필요할때 그 서블릿이 소멸되어 있으면 페이지를 볼 수 없음
%>
	<!-- HTML영역 -->
	회원가입에 성공하였습니다.
<%
	//자바영역
	}
	else {
%>
	<!-- HTML영역 -->
	회원가입에 실패하였습니다.
<%
	//자바영역
	}
%>