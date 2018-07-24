<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>xml 포맷을 받아서 ajax로 읽어내기 + jQuery</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->

	<!-- jQuery script import code :: $-undifined error 주의!!! -->
	<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>

</head>
<!--
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-->
<body>
<!-- ―――[[BODY content]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	$.ajax({
		 method:"get"
		,url:"./xmlExample2.xml"
		,dataType:"xml"
		,success:function(result){
			//alert(result);
			// [object XMLDocument]
			// 이 상태로는 XML문서에 접근할 수 없다. 호환하는 코드를 잘 숙지하기 바랍니다.
		
			/* [][중요]] xml포맷을 스크립트코드로 바꾸어 브라우저에 출력하는 코드 */
			$(result).find('deptList').each(function(){
				//alert("로우가 몇번뜨니?");//3번
				var $entry = $(this);
				alert($entry.find('deptno').text()
				+", "+$entry.find('dname').text()
				+", "+$entry.find('loc').text()
					 );
			});		
		}
		,error:function(xhrObject){
			alert(xhrObject.responseText);
		}
	})
</script>
</body>
</html>