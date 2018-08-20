<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	div#d_detail{
		position:absolute;
	}
</style>
<script type="text/javascript" src="../../js/jquery-1.12.0.js"></script>
<script type="text/javascript">
	function startMethod(imgObj,picture){
		//alert("imgObj:"+imgObj.attr("id"));//id의 값을 읽어오는 코드
		var param = "picture="+picture;
		$.ajax({
			method:"POST",
			url:"partPageDetail.jsp",
			data:param,
			success:function(result){
				$("#d_detail").css("border","#FFAAAA 5px dotted");
				$("#d_detail").css("left",imgObj.offset().left+156+"px");
				$("#d_detail").css("top",imgObj.offset().top+"px");
				$("#d_detail").html(result);				
			},
			error:function(xhrObject){
				alert(xhrObject.responseText);
			}
		});
		//$("#d_detail").text("onmouseover");
		//$("#d_detail").css("left",imgObj.offset().left+156+"px");
		//$("#d_detail").css("top",imgObj.offset().top+"px");
		//$("#d_detail").html("<table border='1'><tr><td>onmouseover</td></tr></table>");
	}
	function clearMethod(){
		//alert("onmouseout");
		//$("#d_detail").text("지워짐.");
		$("#d_detail").css("backgroundColor","#FFFFFF");
		$("#d_detail").css("border","none");
		$("#d_detail").html("");			
	}	
</script>
</head>
<body>
<div id="d_detail">상세화면</div>
<table border="1" width="550px">
	<tr>
		<td align="center" colspan="2" width="550px">카다로그</td>
	</tr>
	<tr>
		<td width="400px">&nbsp;그림제목1</td>
		<td width="150px" id="p1">&nbsp;<img src="./pic1.jpg" width="120px" height="90px" onmouseover="startMethod($('#p1'),'pic1.jpg')" ></td>
	</tr>
	<tr>
		<td width="400px">&nbsp;그림제목2</td>
		<td width="150px" id="p2">&nbsp;<img src="./pic2.jpg" width="120px" height="90px" onmouseover="startMethod($('#p2'),'pic2.jpg')" ></td>
	</tr>
	<tr>
		<td width="400px">&nbsp;그림제목3</td>
		<td width="150px" id="p3">&nbsp;<img src="./pic3.jpg" width="120px" height="90px" onmouseover="startMethod($('#p3'),'pic3.jpg')" ></td>
	</tr>
	<tr>
		<td width="400px">&nbsp;그림제목4</td>
		<td width="150px" id="p4">&nbsp;<img src="./pic4.jpg" width="120px" height="90px" onmouseover="startMethod($('#p4'),'pic4.jpg')" ></td>
	</tr>
</table>
</body>
</html>