<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="../../0_src/_includeList/commonUI_B.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Naver OPEN API :: Captcha Service</title>
<script type="text/javascript">
	function testPopup(){
		//alert("감지되었습니다.");
		location.href = "./captchaView.jsp";
	}
</script>
</head>
<body>
<!-- 위치지정 -->
<div align="center">
<table width="600" height="400">
<tr valign="middle"><td align="center">
<!-- 테두리 -->
<table border="1">
<tr><td>
<!-- 로그인박스 -->
<table>
	<tr valign="middle">
		<td align="center">
		<!-- 아이디 입력칸 -->
		<div style="margin-top:10px;margin-bottom:10px;margin-right:5px">
		<a href="#" title="아이디를 입력해주세요" class="easyui-tooltip" position="left">
		<input class="easyui-textbox" prompt="ID"
				iconWidth="28" data-options="iconCls:'icon-man'"
				style="text-align:center;width:100%;height:34px;padding:10px 10px;"/></a>
		</div>
		<!-- 비밀번호 입력칸 -->
		<div style="margin-bottom:10px;margin-right:5px">
		<a href="#" title="비밀번호를 입력해주세요" class="easyui-tooltip" position="left">
		<input class="easyui-passwordbox" prompt="Password" iconWidth="28"
				style="text-align:center;width:100%;height:34px;padding:10px 10px" /></a>
		</div>
		</td>
		<td align="center"><!-- 로그인버튼 -->
		<a type="submit" class="easyui-linkbutton"
			 iconCls="icon-ok" style="width:100%;height:80px">Login</a>
		</td>
	</tr>
	<tr valign="middle"><!-- 옵션 -->
		<td align="center" colspan="2">
			<a href="javascript:testPopup()">회원가입</a>
			&nbsp;|&nbsp;
			<a href="javascript:testPopup()">비밀번호찾기</a>
		</td>
	</tr>
</table><!-- 로그인박스 끝 -->
</td></tr>
</table><!-- 테투리 끝-->
</td></tr>
</table>
</div><!-- 위치지정 끝 -->

</body>
</html>