<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	/* ���������ϰ� news_InsAction.jsp���� ���� ���� �������� */
	String n_title = request.getParameter("n_title");
	String n_content = request.getParameter("n_content");
	out.print("�Ķ���ͷ� �Ѿ�� ���� : "+n_title+", ���� : "+n_content);
/*
	���� �� �������� ���ڵ������� �߻��Ѵٸ�, Servers�� Tomcat�������� server.xml�� Ȯ���ϱ� �ٶ��ϴ�.
	server.xml�� 63�� connector �±׿� [ URIEncoding="EUC-KR" ] �ɼ��� �߰��ؾ��մϴ�.
*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>AjaxȰ���� NEWSȭ�� �����ϱ�  + ���</title>

<!-- $ is defined  -->
<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>

<script type="text/javascript">
	function newsInsert(){
		alert("newsInsertȣ�� ����");
		$("#f_newsIns").attr("method","get");
	//	$("#f_newsIns").attr("action","DB����ó���������� ����");
		$("#f_newsIns").attr("action","news_InsAction.jsp");
		$("#f_newsIns").submit();
	}
	function login(){
		/* �α��� ��ư ó�� */
		//�α����� ���� ����ڰ� �Է��� ������ <input>�±׿� �ԷµǾ��ֽ��ϴ�.
		//�� ���� ���ͼ� ���̵�� ��й�ȣ�� ��ƺ����ô�.
		var id =$("#mem_id").val();//ȭ��ܿ��� id�� ���� ����� ���� ���� 
		var pw =$("#mem_pw").val();//ȭ��ܿ��� id�� ���� ����� ���� ����
		var param ="mem_id="+id+"&mem_pw="+pw;
		
		$.ajax({
			//�α��� ó���� ������(DB������������ ������)
			 url : "./login_action.jsp"
			//���������� �������� �ʴ°� ���ڽ��ϴ�.
			,method : "POST"
			//POST����� ������ �Ӽ����� ������ �Ѱܾ��մϴ�.
			,data : param
			,success : function(result){
				//alert("ó����� : "+result);//�����׽�Ʈ��
				//ȭ��ٲ�ġ�⸦ ���ؼ� <div>�±׸� �̿��߽��ϴ�.
				$("#d_login").hide();
				$("#d_loginSucess").html(result);
			}
			,error : function(xhrObject){
				alert("error : "+xhrObject.responseText);
			}
		});//end ajax
	}
	function logout(){
		/* �α׾ƿ� ��ư ó�� */
		$.ajax({
			 url : "./logout_view.jsp"
			,method : "GET"
			,success : function(result){
				//alert("�α׾ƿ� �Ǿ����ϴ�.");
				$("#d_loginSucess").html(result);
			}
			,error : function(xhrObject){
				alert("error : "+xhrObject.responseText);
			}
		});
	}
</script>

</head>
<!-- 		���� HEAD�±�
========================================================================================================= 
			�Ʒ��� BODY�±� -->  
<body>
<script type="text/javascript">
	/* DOM�� �����ǰ� �ڿ� ȭ�鿡 ��ȭ�� ajax�� ��������(������ȭ�����ϱ�) */
	$(document).ready(function() {
		/* ��۴ޱ� ��ư ó�� */
		$("#btn_reple").click(function() {
			//insert here : ajax code add
			$.ajax({
				 url : "./news_action.jsp"
			//	 url : ó�������� url ��ġ : "./XXX.jsp �Ǵ� /hello.dk"
			/* 	 	news_action.jsp�� ������?
					div_reple�� �� ȭ�� ������ ���� (jsp������  HTML�±� ) */
				,method : "GET"
				,success : function(data){
					//$("#d_riple").html("<b>��۾��� ȭ��</b>")#btn_reple"#btn_reple"
					$("#d_btnreple").hide();
					$("#d_reple").html(data);
				}
				,error : function(xhrObject){
					alert("error : "+xhrObject.responseText);
				}
			});
		});
	});//////////end-DOM-ready
</script>
<!-- �ܰ����̺����  -��üũ�� ���� + �⺻��ȹ ����  :: 3��� (TOP-NEWS-BOTTOM) -->
<table width="800px" height="600px">
<!-- TOP���� ���� -->
<tr>
<td width="100%" height="80px">
	<!-- �Ҵ�� �����ȿ��� �������̺� ���� �� �ܰ�ũ�� Ȯ�� ( border ) -->
	<table width="100%" height="100%" border="1" bordercolor="orange">
		<tr>
			<td align="center">
				top ���� �Դϴ�.(��ȭ���� ����-ajax������κ�)
			</td>
		</tr>
	</table>
</td>
</tr>
<!-- TOP���� �� -->
<!-- NEWS���� ���� -->
<tr>
<td width="100%" height="480px">
	<table width="100%" height="100%" border="1" bordercolor="green">
		<tr>
			<!-- [[���ʿ��ʿ��ʿ���]] -->
			<td width="40%" align="center">
				<table >
				<!-- �α���ȭ�� �߰� -->
				<tr>
					<td>
					<div id="d_login">
						<table id="t_login" width="250px" height="60px" border="1" bordercolor="red">
							<tr>
								<td width="150px" align="center">
									<!-- ���̵� �Է�â -->
									<input type="text" id="mem_id" value="ID" size="10"
										onClick="javascript:document.getElementById('mem_id').value=''">
								</td>
									<!-- �α��ι�ư+Ȯ��-->
								<td rowspan="2" width="80px" align="center">
									<input type="button" id="btn_login" value="LOGIN"
									 onClick="javascript:login()">
								</td>
							</tr>
							<tr>
								<td align="center" >
									<!-- ��й�ȣ �Է�â -->
									<input type="text" id="mem_pw" value="PW" size="10"
										onClick="javascript:document.getElementById('mem_pw').value=''">
								</td>
							</tr>
						</table>
					</div>
					<div id="d_loginSucess"></div>
					</td>
				</tr>
				<tr height="300px">
					<td></td>
				</tr>
				</table>
			</td>
			<!-- [[�����ʿ����ʿ����ʿ�����]] -->
			<td width="60%" align="center">
				NEWS �����Դϴ�.<br>
				�������� : ����.....<br>
				�������� : ����.....<br>
				<!-- INSERT HERE : �ۼ��� ��� ������ ���⿡ ��µǵ��� �ϼ���. -->
<%
	//��۳����� �ֳ���? - ������ ó���غ�����.
			if((n_title!=null)&&(n_content!=null)){
				out.print("������� : "+n_title);
				out.print("<br>");
				out.print("��۳��� : "+n_content);
			}
%>								
				<div id="d_btnreple"><input id="btn_reple" type="button" value="��۾���"></div>
				<div id="d_reple">���⿡ ��۾��� ȭ�� ���� [jsp���� ����]</div>
			</td>
		</tr>
	</table>
</td>
</tr>
<!-- NEWS���� �� -->
<!-- BOTTOM���� ���� -->
<tr>
<td width="100%" height="40px">
	<table width="100%" height="100%" border="1" bordercolor="blue">
		<tr>
			<td align="center">
				ȸ��Ұ� | �λ縻 | ����å����(��ȭ���� ����-ajax������κ�)
			</td>
		</tr>
	</table>
</td>
</tr>
<!-- BOTTOM���� �� -->
<!-- �ܰ����̺� �� -->
</body>

</html>