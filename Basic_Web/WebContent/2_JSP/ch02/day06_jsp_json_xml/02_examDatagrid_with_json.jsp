<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>json포맷을 받아서 표로 출력하는 EasyUI예제 :: Datagrid</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->

	<!-- css import code -->
	<link rel="stylesheet" type="text/css" href="/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/themes/icon.css?1">
	<link rel="stylesheet" type="text/css" href="/demo/demo.css">

	<!-- jQuery script import code :: $-undifined error 주의!!! -->
	<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>
	
	<!-- EasyUI for jQuery script import code -->
	<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>

</head>
<!--
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-->
<body>
<!-- ―――[[BODY content]]――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
	<table id="dg_dept" class="easyui-datagrid" style="width:400px;height:250px"
        data-options="url:'jsonExample.jsp',fitColumns:true,singleSelect:true">
	<thead>
		<tr>
			<th data-options="field:'deptno',width:100">부서번호</th>
			<th data-options="field:'dname',width:100">부서명</th>
			<th data-options="field:'loc',width:100">지역</th>
		</tr>
	</thead>	
	</table>
</body>
</html>