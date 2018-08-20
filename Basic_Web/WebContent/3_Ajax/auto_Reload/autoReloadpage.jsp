<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ajax를 활용한 페이지 자동갱신하기 : 뉴스갱신하기</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
	<!-- jQuery script import code :: $-undifined error 주의!!! -->
	<script type="text/javascript" src="../../js/jquery-1.12.0.js"></script>

<!-- ―――[[ HEAD script ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	var watch;
	var i=0;
	function autoReload(){
		//alert("autoReload 호출성공");
		//INSERT HERE : 자동갱신시 처리할 페이지 처리하기 (ajax code add)
		//$("#d_news").text("뉴스내용..."+i);
		//i=i+1;
		
		//INSERT HERE : 자동갱신되는 5개의 뉴스 jsp 입력
		$.ajax({
			 url : "./getNewsTitle.jsp"
			,method:"GET"
			,success : function(clip){
				//alert("success");//단위테스트용
				$("#d_news").html(clip);
			}
			,error : function(xhrObject){
				alert(xhrObject.responseText());
			}
		})
	}
	function start(){
		watch=setInterval(autoReload,1000);//1000ms는  1초
	}
	function stop(){
		setTimeout(function(){
			clearInterval(watch);
		},100000);
	}
</script>

</head>
<!--
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-->
<body>
<!-- ―――[[ BODY script ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	start();
	stop();
</script>

<!-- ―――[[ BODY content ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<h3>자동 갱신 페이지 구현하기 : 뉴스변경</h3>
<input type="button" value="시작" onClick="start()">
<input type="button" value="끝" onClick="stop()">
<div id="d_news"></div>
</body>
</html>