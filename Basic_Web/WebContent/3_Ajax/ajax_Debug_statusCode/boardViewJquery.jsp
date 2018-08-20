<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src ="../../js/jquery-1.12.0.js"></script>
<script type="text/javascript">
	function account(){
		$.ajax({
			 //url : "../180712_experience/boardAction.jsp?"+new Date().getTime()
			 url : "/dev_HTML5/BoardAction?"+new Date().getTime()
					 //xml문서에 들어간 UML패턴으로 Survlet에서 호출된 URL경로에서, 포트번호 이후의 주소가 들어가야한다.
			,method : "GET"
			,statusCode : {//디버깅용 힌트 옵션!!! jsp의 status를 jquery에서 확인하는 status code!!!
				200 : function(){
					alert("200 :: Ok");
				},
				404 : function(){
					alert("page not found");
				},
				405 : function(){//method이름이 에러가 났을때 405 상태가 되고, 그때 알림창이 뜨도록 설정
					alert("405 error");
				}
			}
			,success : function(data){//4이면서 200ok
				//alert(data);//request1.responseText(json), request1.responseXml
				alert("새로 집계된 판매량 : "+data);
				//insert here - 변경된 판매량으로 마진 금액을 계산하여 총 마진 금액을 출력하시오.
				var real = ($("#price").text()-$("#cost").text())*$("#board_sold").text()
				
				//$("#d_margin).text("마진금액은 "+????+"입니다.");
				//$("#d_margin").text("<font color ='red'>마진금액</font>은 "+1000+"입니다.");
				$("#d_margin").html("<font color ='red'>마진금액</font>은 "+real+"입니다.");
			}
			,error : function(request1, status){
				alert("status : "+status);
				alert(request1.responseText);
			}
		});
	}
</script>
</head>
<body>
<h2>웨이크 보드 판매현황-Ajax적용</h2>
<table border="1" width="300px" height="80px">
	<tr>
		<th width="130px">보드판매량</th>
		<td width="170px"><span id="board_sold">12</span></td>
	</tr>
	<tr>
		<th width="130px">소비자가</th>
		<td width="170px"><span id="price">3800000</span></td>
	</tr>
	<tr>
		<th width="130px">구매가</th>
		<td width="170px"><span id="cost">2500000</td>
	</tr>		
</table>
<br>
<input type="button" value="마진은?" onClick="javascript:account()"/>
<div id="d_margin">총마진 금액은 XXXXX입니다.</div>
</body>
</html>