<%@page import="util.web.CookieMgr"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	String mem_id = "tester";
	session.setAttribute("mem_id", mem_id);

	CookieMgr cm = new CookieMgr(request, response);
	cm.setCookie(mem_id+"_basket", "apple=1/water=2/bear=5/cable=1");
%>
<html>
<head>
<meta charset="EUC-KR">
<title>장바구니 담기 테스트페이지</title>
<jsp:include page="../../0_src/_includeList/commonUI_S.jsp"/>
</head>
<body>

<div>
 	water : 
 	<input id="t_ammount" type="text"/><br>
 	<input id="btn_basket" type="button" value="장바구니 담기" onclick="javascript:setBasket()"/>
</div>
<script type="text/javascript">
	function setBasket(){
		var param_ammount =	"water"	+"="+$("#t_ammount").val()
		$.ajax({
			 method:"POST"
			,data:param_ammount
			,url:"./test.bsk"
			,success:function(htmlCode){
				alert("돌아왔네요. 자바콘솔창 확인하세요");
			}
		});
	}
</script>
</body>
</html>