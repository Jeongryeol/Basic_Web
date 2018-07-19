<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	/* 변수선언하고 news_InsAction.jsp에서 받은 값을 저장하자 */
	String n_title = request.getParameter("n_title");
	String n_content = request.getParameter("n_content");
	out.print("파라미터로 넘어온 제목 : "+n_title+", 내용 : "+n_content);
/*
	만약 이 과정에서 인코딩에러가 발생한다면, Servers의 Tomcat폴더에서 server.xml을 확인하기 바랍니다.
	server.xml의 63번 connector 태그에 [ URIEncoding="EUC-KR" ] 옵션을 추가해야합니다.
*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Ajax활용한 NEWS화면 구현하기  + 댓글</title>

<!-- $ is defined  -->
<script type="text/javascript" src="/js/jquery-1.12.0.js"></script>

<script type="text/javascript">
	function newsInsert(){
		alert("newsInsert호출 성공");
		$("#f_newsIns").attr("method","get");
	//	$("#f_newsIns").attr("action","DB연동처리페이지와 연결");
		$("#f_newsIns").attr("action","news_InsAction.jsp");
		$("#f_newsIns").submit();
	}
	function login(){
		/* 로그인 버튼 처리 */
		//로그인을 위해 사용자가 입력한 정보는 <input>태그에 입력되어있습니다.
		//이 값을 얻어와서 아이디와 비밀번호로 담아보냅시다.
		var id =$("#mem_id").val();//화면단에서 id로 읽은 대상의 값을 읽음 
		var pw =$("#mem_pw").val();//화면단에서 id로 읽은 대상의 값을 읽음
		var param ="mem_id="+id+"&mem_pw="+pw;
		
		$.ajax({
			//로그인 처리할 페이지(DB연동과정까지 들어가야함)
			 url : "./login_action.jsp"
			//계정정보는 공개하지 않는게 좋겠습니다.
			,method : "POST"
			//POST방식은 데이터 속성으로 별도로 넘겨야합니다.
			,data : param
			,success : function(result){
				//alert("처리결과 : "+result);//단위테스트용
				//화면바꿔치기를 위해서 <div>태그를 이용했습니다.
				$("#d_login").hide();
				$("#d_loginSucess").html(result);
			}
			,error : function(xhrObject){
				alert("error : "+xhrObject.responseText);
			}
		});//end ajax
	}
	function logout(){
		/* 로그아웃 버튼 처리 */
		$.ajax({
			 url : "./logout_view.jsp"
			,method : "GET"
			,success : function(result){
				//alert("로그아웃 되었습니다.");
				$("#d_loginSucess").html(result);
			}
			,error : function(xhrObject){
				alert("error : "+xhrObject.responseText);
			}
		});
	}
</script>

</head>
<!-- 		위는 HEAD태그
========================================================================================================= 
			아래는 BODY태그 -->  
<body>
<script type="text/javascript">
	/* DOM이 구성되고난 뒤에 화면에 변화를 ajax로 대응하자(동적변화대응하기) */
	$(document).ready(function() {
		/* 댓글달기 버튼 처리 */
		$("#btn_reple").click(function() {
			//insert here : ajax code add
			$.ajax({
				 url : "./news_action.jsp"
			//	 url : 처리페이지 url 위치 : "./XXX.jsp 또는 /hello.dk"
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
	});//////////end-DOM-ready
</script>
<!-- 외곽테이블시작  -전체크기 지정 + 기본구획 설정  :: 3등분 (TOP-NEWS-BOTTOM) -->
<table width="800px" height="600px">
<!-- TOP영억 시작 -->
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
			<!-- [[왼쪽왼쪽왼쪽왼쪽]] -->
			<td width="40%" align="center">
				<table >
				<!-- 로그인화면 추가 -->
				<tr>
					<td>
					<div id="d_login">
						<table id="t_login" width="250px" height="60px" border="1" bordercolor="red">
							<tr>
								<td width="150px" align="center">
									<!-- 아이디 입력창 -->
									<input type="text" id="mem_id" value="ID" size="10"
										onClick="javascript:document.getElementById('mem_id').value=''">
								</td>
									<!-- 로그인버튼+확장-->
								<td rowspan="2" width="80px" align="center">
									<input type="button" id="btn_login" value="LOGIN"
									 onClick="javascript:login()">
								</td>
							</tr>
							<tr>
								<td align="center" >
									<!-- 비밀번호 입력창 -->
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
			<!-- [[오른쪽오른쪽오른쪽오른쪽]] -->
			<td width="60%" align="center">
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