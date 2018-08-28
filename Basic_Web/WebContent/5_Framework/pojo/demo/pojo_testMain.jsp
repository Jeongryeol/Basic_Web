<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//���Ǻҷ�����
	String smem_name = null;
	String sstatus = null;
	if(session.getAttribute("smem_name")!=null){
		smem_name = session.getAttribute("smem_name").toString();
	}
	if(session.getAttribute("sstatus")!=null){
		sstatus = session.getAttribute("sstatus").toString();
	}
	
	//��Ű�ҷ�����
	Cookie[] cs = request.getCookies();
	String c_value = null;
	String sunglass = null;
	String shortpants = null;
	String suncream = null;
	String shortshirts = null;
	String slipper = null;
	if(cs !=null && cs.length>0){//��Ű�� �����ϴ�?
		for(int i=0;i<cs.length;i++){
			String c_name = cs[i].getName();
			if("JSESSIONID".equals(c_name)){
				c_value = cs[i].getValue();
				out.print("c_value:"+c_value);
			}
			else if("cmem_name".equals(c_name)){
				c_value = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			/* ��ǰ ���� ��ȸ �ϱ� ���� */
			else if(c_name=="sunglass"){
				sunglass = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			else if(c_name=="shortpants"){
				shortpants = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			else if(c_name=="suncream"){
				suncream = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			else if(c_name=="shortshirts"){
				shortshirts = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			else if(c_name=="slipper"){
				slipper = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			/* ��ǰ ���� ��ȸ �ϱ�  �� */
		}	
	}///end of if
	//out.print(c_value);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>POJO Framework ���� ����������</title>

<jsp:include page="../../../0_src/_includeList/commonUI_S.jsp" flush="false"/>
<script type="text/javascript">
	function overlapTest(){
		var userid = $("#t_id").val();
		var param = "mem_id="+userid;
		$.ajax({
			method:"post",
			/*   /(������Ʈ�̸�)/(������Ʈ������ ���߱�)/���θ� /ȸ������ / ��ȸ���� /���̵��ߺ��˻� ��������	*/
			url:"/Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo",
			data:param,
			success:function(result){
				//���翩�ο� ���� �˾�â�� ������ jsp�������� �������Ͽ� �����ϹǷ� �����ʿ����
				console.log("���ƿԽ��ϴ�");
				$("#d_idCheckResult").html(result);
			},
			error:function(xhrObject){
				alert(xhrObject);
				console.log(xhrObject.toString());
			}
			
		});
	}
</script>

</head>
<body>
<div>
	<h1>POJO Framework Example</h1>
	<h3>&nbsp;�α��� �׽�Ʈ</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value=" �α��������� �̵� " onclick="javascript:location.href='./member/pojo_login.jsp'" />
</div>
<div><br><br></div>

<div>
	<h3>&nbsp;���̵� �ߺ��˻� üũ</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<b>URL-Pattern</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	/Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo
	<br>
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="t_id" type="text" value="test"/>
	<input id="btn_overlap" type="button" value="�ߺ��˻�" onclick="overlapTest()" />
</div>
<div id="d_idCheckResult"></div>
<div><br><br></div>

<div>
	<h3>&nbsp;ȸ������ �׽�Ʈ</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<b>URL-Pattern</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br>
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value=" ȸ������������ �̵� " onclick="javascript:location.href='./member/pojo_signup.jsp'" />
</div>
<div><br><br></div>

</body>
</html>