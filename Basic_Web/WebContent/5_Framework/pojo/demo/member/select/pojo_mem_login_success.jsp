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
	//JSON포맷으로 내보내기
	StringBuilder sb = new StringBuilder();
	sb.append("[{'sstatus':'"+sstatus+"',"+"'smem_name':'"+smem_name+"'}]");
	out.clear();
	out.print(sb.toString());
%>
<script>
	alert("로그인에 성공하셨습니다");
	location.href = "../../pojo_testMain.jsp";
</script>