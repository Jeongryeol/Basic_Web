<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
<jsp:include page="../../../../0_src/_includeList/commonUI_S.jsp" flush="false"/>

<script type="text/javascript">
/* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ [[ 회원정보 가입신청코드 개시  ]] ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ */
	function memberInsert(){
		$("#f_member").attr("method", "post");
		$("#f_member").attr("action", "/Basic_Web/5_Framework/pojo/demo/member/insert/signup.demo");
		$("#f_member").submit();
	}
/* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ [[ 회원정보 가입신청코드 완료  ]] ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ */
</script>

</head>
<body>
<script type="text/javascript">
$(document).ready(function() {	//DOM구성
/* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ [[ 비밀번호 일치확인코드 개시  ]] ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ */
	$("#mem_pw").keyup(function() {//첫번째 비밀번호의 키 입력후
		if($("#mem_pw").val()==""){		//빈칸일때
			$("#d_checkPW").html("");	//확인메시지 비움
		}else{
			if($("#mem_pw").val().length<8){//입력글자가 8글자 미만일때 8자 이상 입력요청하는 메시지출력
				$("#d_checkPW").html("비밀번호는 8자 이상 입력해주세요.");
			}else{
				$("#d_checkPW").html("");	//8글자를 입력하면 확인메시지 비움
			}
		}
	});
	$("#mem_pw2").keyup(function() {//두번째 비밀번호의 키를 떼고 나서
		if($("#mem_pw").val().length>=8){//첫번째 비밀번호의 입력값이 8자 이상일때
			if($("#mem_pw").val()==$("#mem_pw2").val()){//두 값이 일치하면
				$("#d_checkPW").html("<font color='green'>비밀번호가 일치합니다.</font>");//일치메시지 출력
			}else{//두 값이 불일치하면 비밀번호 일치요청 메시지 출력
				$("#d_checkPW").html("<font color='red'>비밀번호를 일치시켜주세요.</font>");
			}
		}else{//첫번째 비밀번호 자리가 8자리가 안된다면 안내메시지를 띄움
			$("#d_checkPW").html("비밀번호는 8자 이상 입력해주세요");
		}
	});
/* ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ [[ 비밀번호 일치확인코드 완료  ]] ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ */
});
</script>

<div><br></div>
<div class="ui equal width grid">
	<div class="column"></div>
	<div class="column">
	    <div align="center"><h2>회원가입 테스트</h2></div>
		<form id="f_member" class="ui form">
		  <div class="field">
		    <label>ID</label>
		    <input type="text" id="mem_id" name="mem_id" placeholder="insert id" >
		  </div>
		  <div class="field">
		    <label>PassWord</label>
		    <input type="password" id="mem_pw" name="mem_pw" placeholder="insert password">
		  </div>
		  <div class="field">
		    <label>PassWord</label>
		    <input type="password" id="mem_pw2" name="mem_pw" placeholder="repeat password">
		  </div>
		  <div id="d_checkPW"></div>
		  <div class="field">
		    <label>Name</label>
		    <input type="text" id="mem_name" name="mem_name" placeholder="insert your name" >
		  </div>
		  <div class="field">
		    <div class="ui checkbox">
		      <input type="checkbox" tabindex="0" class="hidden">
		      <label>마케팅 정보 수신 동의</label>
		    </div>
		  </div>
		  <a href="javascript:memberInsert();" class="ui button" type="submit">회원가입</a>
		</form>
		
		</body>
		</html>
	</div>
	<div class="column"></div>
</div>