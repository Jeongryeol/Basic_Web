<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ������</title>
<jsp:include page="../../../../0_src/_includeList/commonUI_S.jsp" flush="false"/>

<script type="text/javascript">
/* ������������������������������� [[ ȸ������ ���Խ�û�ڵ� ����  ]] ������������������������������� */
	function memberInsert(){
		$("#f_member").attr("method", "post");
		$("#f_member").attr("action", "/Basic_Web/5_Framework/pojo/demo/member/insert/signup.demo");
		$("#f_member").submit();
	}
/* ������������������������������� [[ ȸ������ ���Խ�û�ڵ� �Ϸ�  ]] ������������������������������� */
</script>

</head>
<body>
<script type="text/javascript">
$(document).ready(function() {	//DOM����
/* ������������������������������� [[ ��й�ȣ ��ġȮ���ڵ� ����  ]] ������������������������������� */
	$("#mem_pw").keyup(function() {//ù��° ��й�ȣ�� Ű �Է���
		if($("#mem_pw").val()==""){		//��ĭ�϶�
			$("#d_checkPW").html("");	//Ȯ�θ޽��� ���
		}else{
			if($("#mem_pw").val().length<8){//�Է±��ڰ� 8���� �̸��϶� 8�� �̻� �Է¿�û�ϴ� �޽������
				$("#d_checkPW").html("��й�ȣ�� 8�� �̻� �Է����ּ���.");
			}else{
				$("#d_checkPW").html("");	//8���ڸ� �Է��ϸ� Ȯ�θ޽��� ���
			}
		}
	});
	$("#mem_pw2").keyup(function() {//�ι�° ��й�ȣ�� Ű�� ���� ����
		if($("#mem_pw").val().length>=8){//ù��° ��й�ȣ�� �Է°��� 8�� �̻��϶�
			if($("#mem_pw").val()==$("#mem_pw2").val()){//�� ���� ��ġ�ϸ�
				$("#d_checkPW").html("<font color='green'>��й�ȣ�� ��ġ�մϴ�.</font>");//��ġ�޽��� ���
			}else{//�� ���� ����ġ�ϸ� ��й�ȣ ��ġ��û �޽��� ���
				$("#d_checkPW").html("<font color='red'>��й�ȣ�� ��ġ�����ּ���.</font>");
			}
		}else{//ù��° ��й�ȣ �ڸ��� 8�ڸ��� �ȵȴٸ� �ȳ��޽����� ���
			$("#d_checkPW").html("��й�ȣ�� 8�� �̻� �Է����ּ���");
		}
	});
/* ������������������������������� [[ ��й�ȣ ��ġȮ���ڵ� �Ϸ�  ]] ������������������������������� */
});
</script>

<div><br></div>
<div class="ui equal width grid">
	<div class="column"></div>
	<div class="column">
	    <div align="center"><h2>ȸ������ �׽�Ʈ</h2></div>
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
		      <label>������ ���� ���� ����</label>
		    </div>
		  </div>
		  <a href="javascript:memberInsert();" class="ui button" type="submit">ȸ������</a>
		</form>
		
		</body>
		</html>
	</div>
	<div class="column"></div>
</div>