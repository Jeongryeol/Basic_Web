<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	/* 변수선언하고 news_InsAction.jsp에서 받은 값을 저장하자 */
	String n_title = request.getParameter("n_title");
	String n_content = request.getParameter("n_content");
	out.print("파라미터로 넘어온 제목 : "+n_title+", 내용 : "+n_content);
/*
	만약 위 과정에서 인코딩에러가 발생한다면, Servers의 Tomcat폴더에서 server.xml을 확인하기 바랍니다.
	server.xml의 63번 connector 태그에 [ URIEncoding="EUC-KR" ] 옵션을 추가해야합니다.
*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Ajax활용한 NEWS화면 구현하기  + 댓글</title>

<!-- ―――[[ import ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
	<!-- ↙ $-undifined error 주의!!! -->
<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>

<!-- ―――[[ HEAD script ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	function newsInsert(){
		alert("newsInsert호출 성공");//함수호출단위테스트용 팝업창
	//	$("#f_newsIns").attr("action","연결방식 GET|POST");
		$("#f_newsIns").attr("method","get");
	//	$("#f_newsIns").attr("action","DB연동처리페이지와 연결");
		$("#f_newsIns").attr("action","news_InsAction.jsp");
		$("#f_newsIns").submit();
	}
</script>
</head>
<!--
■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
-->
<body>
<!-- ―――[[ BODY script ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<script type="text/javascript">
	/* DOM이 구성되고난 뒤에 화면의 변화를 ajax로 대응하자(동적변화대응하기) */
	$(document).ready(function() {
		$("#btn_reple").click(function() {
			//insert here : ajax code add
			$.ajax({
			//	 url : 처리페이지 url 위치 : "./XXX.jsp 또는 /hello.dk"
				 url : "./news_action.jsp"
			/* 	 	news_action.jsp의 역할은?
					div_reple에 들어갈 화면 내용을 담음 (jsp이지만  HTML태그 ) */
				,method : "GET"
				,success : function(data){
					//$("#d_riple").html("<b>댓글쓰기 화면</b>")#btn_reple"#btn_reple"
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

<!-- ―――[[ BODY content ]]―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― -->
<!-- 외곽테이블시작  -전체크기 지정 + 기본구획 설정  :: 3등분 (TOP-NEWS-BOTTOM) -->
<table width="800px" height="600px">
<!-- TOP영역 시작 -->
<tr>
<td width="100%" height="80px">
	<!-- 할당된 영역안에서 내부테이블 생성 및 외곽크기 확인 ( border ) -->
	<table width="100%" height="100%" border="1" bordercolor="orange">
		<tr>
			<td align="center">
				top 영역 입니다.(변화없이 고정-ajax미적용부분)
			</td>
		</tr>
	</table>
</td>
</tr>
<!-- TOP영역 끝 -->
<!-- NEWS영억 시작 -->
<tr>
<td width="100%" height="480px">
	<table width="100%" height="100%" border="1" bordercolor="green">
		<tr>
			<td align="center">
				NEWS 영역입니다.<br>
				뉴스제목 : 제목.....<br>
				뉴스내용 : 내용.....<br>
				<!-- INSERT HERE : 작성된 댓글 내용이 여기에 출력되도록 하세요. -->
<%
	//댓글내용이 있나요? - 있을때 처리해보세요.
			if((n_title!=null)&&(n_content!=null)){
				out.print("댓글제목 : "+n_title);
				out.print("<br>");
				out.print("댓글내용 : "+n_content);
			}
%>								
				<div id="d_btnreple"><input id="btn_reple" type="button" value="댓글쓰기"></div>
				<div id="d_reple">여기에 댓글쓰기 화면 구현 [jsp내용 삽입]</div>
			</td>
		</tr>
	</table>
</td>
</tr>
<!-- NEWS영역 끝 -->
<!-- BOTTOM영억 시작 -->
<tr>
<td width="100%" height="40px">
	<table width="100%" height="100%" border="1" bordercolor="blue">
		<tr>
			<td align="center">
				회사소개 | 인사말 | 정보책임자(변화없이 고정-ajax미적용부분)
			</td>
		</tr>
	</table>
</td>
</tr>
<!-- BOTTOM영역 끝 -->
<!-- 외곽테이블 끝 -->
</body>

</html>