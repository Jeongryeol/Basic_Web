<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
<jsp:include page="../../../../0_src/_includeList/commonUI_S.jsp" flush="false"/>

<script type="text/javascript">
/* ������������������������������� [[ ȸ������ �α��ν�û �ڵ� ����  ]] ������������������������������� */
	function memberLogin(){
		$("#f_member").attr("method", "post");
		$("#f_member").attr("action", "/Basic_Web/5_Framework/pojo/demo/member/select/memberLogin.demo");
		$("#f_member").submit();
	}
/* ������������������������������� [[ ȸ������ �α��ν�û �ڵ� �Ϸ�  ]] ������������������������������� */
</script>

</head>
<body>

<div><br></div>
<div class="ui equal width grid">
	<div class="column"></div>
	<div class="column">
	    <div align="center"><h2>�α��� �׽�Ʈ</h2></div>
		<form id="f_member" class="ui form">
		  <div class="field">
		    <label>ID</label>
		    <input type="text" id="mem_id" name="mem_id" placeholder="insert id" >
		  </div>
		  <div class="field">
		    <label>PassWord</label>
		    <input type="password" id="mem_pw" name="mem_pw" placeholder="insert password">
		  </div>
		  <div class="ui checkbox">
		    <input type="checkbox" tabindex="0" class="hidden">
		    <label>�α��λ��� ���� (�̱���)</label>
		  </div>
		  <div align="right">
		  	<a href="javascript:memberLogin();" class="ui button" type="submit">�α���</a>
		  </div>
		</form>
	</div>
	<div class="column"></div>
</div>
</body>
</html>