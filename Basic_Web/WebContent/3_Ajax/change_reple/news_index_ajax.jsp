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

<!-- ������[[ import ]]�������������������������������������������������������������������������������������������������������������������������������������������������������������������� -->
	<!-- �� $-undifined error ����!!! -->
<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>

<!-- ������[[ HEAD script ]]���������������������������������������������������������������������������������������������������������������������������������������������������� -->
<script type="text/javascript">
	function newsInsert(){
		alert("newsInsertȣ�� ����");//�Լ�ȣ������׽�Ʈ�� �˾�â
	//	$("#f_newsIns").attr("action","������ GET|POST");
		$("#f_newsIns").attr("method","get");
	//	$("#f_newsIns").attr("action","DB����ó���������� ����");
		$("#f_newsIns").attr("action","news_InsAction.jsp");
		$("#f_newsIns").submit();
	}
</script>
</head>
<!--
�����������������������������������������������������������������������������������������������������
-->
<body>
<!-- ������[[ BODY script ]]���������������������������������������������������������������������������������������������������������������������������������������������������� -->
<script type="text/javascript">
	/* DOM�� �����ǰ� �ڿ� ȭ���� ��ȭ�� ajax�� ��������(������ȭ�����ϱ�) */
	$(document).ready(function() {
		$("#btn_reple").click(function() {
			//insert here : ajax code add
			$.ajax({
			//	 url : ó�������� url ��ġ : "./XXX.jsp �Ǵ� /hello.dk"
				 url : "./news_action.jsp"
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
	});
</script>

<!-- ������[[ BODY content ]]���������������������������������������������������������������������������������������������������������������������������������������������������� -->
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
			<td align="center">
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