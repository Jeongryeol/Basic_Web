<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ajax를 활용한 페이지 자동갱신하기 : 뉴스갱신하기</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
	<!-- jQuery script import code :: $-undifined error 주의!!! -->
	<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>

<!-- ―――[[ HEAD script ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	//var i=0;
	var watch;//지연시간을 리턴받을 변수
	
	/* 반복할 함수+ajax  */
	function autoReload(){
		//alert("autoReload 호출성공");
		//INSERT HERE : 자동갱신시 처리할 페이지 처리하기 (ajax code add)
			//$("#d_news").text("뉴스내용..."+i);
			//i=i+1;
		$.ajax({	//INSERT HERE : 자동갱신되는 5개의 뉴스 jsp 입력
			 url : "./getNewsTitle.jsp"
			,method:"GET"
			,success : function(clip){
				//alert("success");//단위테스트용
				$("#d_news").html(clip);
			}
			,error : function(xhrObject){//디버깅용
				alert(xhrObject.responseText());
			}
		})
	}
	
	/* 반복을 시작하는 함수  */
	function start(){
		//setInterval(f,ms):number
			//지정함수(f)를 지정시간(ms)의 간격으로 반복하고, 반복될때마다 지연된 시간을 반환하는 함수
		watch=setInterval(autoReload,1000);//1000ms는  1초
	}
	
	/* 반복을 초기화하는 함수  */
	function stop(){
		//setTimeout(f,ms):number
			//지정시간(ms)을 달성하면 지정함수()를 실행하는 함수
		//cleraInteval(interval):number
			//파라미터의 지연시간을 초기화하여 반환하는 함수
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