<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>페이지가 달라도 세션에 남아있기 때문에 30분동안은 유지됩니다.</title>
</head>
<body>
02_session에서 <b>session</b>에 저장했었던 "bread" 과연 여기서도 기억될까?<br>
<br>
	카트에 담긴 물건 목록 : <%= session.getAttribute("bread").toString() %><br>
<br>
	톰켓은 세션을 기본적으로 30분간 유지합니다. 그러므로 페이지가 달라도 담을 수있습니다.
<br>
<br>
그렇다면, 02_session에서 <b>sendRedirect</b>로 이곳에 넘겨온  myName은 여기서도 불러질까요?<br>
	<%-- <%= myName %> --%>
	에러가 발생할겁니다.<br>
	URL이 바뀌었기 때문에 새로운 요청이 일어났으므로 스크립트상의 데이터유지가 끊어진 것입니다.<br>
<br>
<br>
그렇다면, 02_session에서 <b>forward</b>로 페이지 이동을 한다면 어떨까요?<br>
	<b><%= request.getAttribute("myName") %><br></b>
	화면은 여기 화면이 열려서 지금 이 글을 읽고 있겠지만,<br>
	주소는 02_session의 URL을 그대로 이용하고 있을겁니다.<br>
	그러면서 데이터는 또 유지되고 있겠죠.<br>

</body>
</html>