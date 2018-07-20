<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>START Page - Go to HelloSurvlet</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
	<!-- [경로확인 필수!!] 깊이 넣어놨으면 꼭 ../로 한번더 꺼내주세요~! -->
	<link rel="stylesheet" type="text/css" href="../../themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../../demo/demo.css">
	<script type="text/javascript" src="../../js/jquery-1.12.0.js"></script>
	<script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
</head>
<!--
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-->
<body>
<!-- ―――[[BODY script]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn_send").click(function(){
			$("#f_start").attr("method","post");
			$("#f_start").attr("action","/dev_jsp20180417/ch17/hello.dk");
			$("#f_start").submit();
		});
	});
</script>
<!-- ―――[[BODY content]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
여기서부터 업무처리가 시작됩니다.<br>
업무담당자가 필요한 업무처리를 위해 입력할 값들을 입력한 후 <br>
업무처리를 해 줄 서블릿클래스 (com.ch17.day03_annotation.HelloSurvlet.java)를 호출함<br>
<!-- ―――[[form OPEN ]]――― -->
<form id="f_start">
	<input class="easyui-textbox" id="tb_name" name="tb_name">
	<input id="btn_send" type="button" value="전송하기(업무처리요청)">
</form>
<!-- ―――[[form CLOSE ]]――― -->
</body>
</html>