<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
wait a second for login
<%
	String smem_name = null;
	String sstatus = null;
	if(session.getAttribute("smem_name")!=null){
		smem_name = session.getAttribute("smem_name").toString();
	}
	if(session.getAttribute("sstatus")!=null){
		smem_name = session.getAttribute("sstatus").toString();
	}
	//out.println(smem_name);
	//JSON�������� ��������
	StringBuilder sb = new StringBuilder();
	sb.append("[{'sstatus':'"+sstatus+"',"+"'smem_name':'"+smem_name+"'}]");
	out.clear();
	out.print(sb.toString());
%>
<script>
	alert("�α��ο� �����ϼ̽��ϴ�");
	location.href = "../../pojo_testMain.jsp";
</script>