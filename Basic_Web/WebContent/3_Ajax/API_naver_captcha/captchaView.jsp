<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="../../0_src/_includeList/commonUI_E.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">
	function checkCaptcha(){
		var param = "captchakey="+$("#captchaKey").val();//발급되서 넘어온 키값
				  +"&value="+$("#d_input").val();//사용자가 입력한 문자값
		$.ajax({
			 method:"POST"
			,url:"./captchaResult.jsp"
			,data:param
			,success:function(response){
				console.log(response);
				alert(response.toString());
				//공백을 없애고 반환한 문자열을 비교함
				//if(response.trim()=="200 ok"){
				if(response=="200 ok"){
					alert("승인되었습니다.")
					//location.href="./captchaIntro.jsp";
				}else{
					alert("오류났습니다. 다시하세요.")
					//ocation.href="pwConfirmFail.jsp";
				}
				
			}
			,error:function(xhrObject){
				alert(xhrObject);
			}
		});
	}
	//캡챠서비스 아작스 
	function newCaptcha() {
		var param = "command=getIMG";
		$.ajax({
			method : "POST",
			url : "./captcharImage.jsp", /* 1단계:키발급,2단계:캡챠이미지생성(ajax/naver)*/
			data : param,
			success : function(captchaIMG) {
				/* 	//이미지가 넘어오는 방식에 대한 단위테스트
					alert("captchaIMG = "+captchaIMG);
					alert("captchaIMG.length = "+captchaIMG.length);
					alert("captchaIMG.substring(1,99) = "+captchaIMG.substring(1,99)); */
				$("#d_captcha").html(captchaIMG);
			},
			error : function(xhrObject) {
				alert(xhrObject);
			}
		});
	}
</script>

</head>
<body>
<!--
재사용하고자 한다면 HEAD스크립트로!
	재사용할 함수가 아니라면 BODY스크립트로!
 -->
	<script type="text/javascript">
		//DOM
		$(document).ready(function(){
				newCaptcha();//캡차서비스 호출
		});
	</script>

	<!-- 캡챠이미지 + 갱신버튼 -->
	<div align="center">
		<div id="d_captcha">
			<h1>캡챠이미지</h1>
		</div>

		<br>&nbsp;
	</div>
	<!-- 입력란 + 확인버튼 -->
	<div align="center">
		<input id="d_input" class="easyui-textbox" style="width: 120px" />
		<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			onclick="checkCaptcha()">확인</a>
	</div>
</body>
</html>