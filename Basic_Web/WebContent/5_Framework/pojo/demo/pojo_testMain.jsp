<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//세션불러오기
	String smem_name = null;
	String sstatus = null;
	if(session.getAttribute("smem_name")!=null){
		smem_name = session.getAttribute("smem_name").toString();
	}
	if(session.getAttribute("sstatus")!=null){
		sstatus = session.getAttribute("sstatus").toString();
	}
	
	//쿠키불러오기
	Cookie[] cs = request.getCookies();
	String c_value = null;
	String sunglass = null;
	String shortpants = null;
	String suncream = null;
	String shortshirts = null;
	String slipper = null;
	if(cs !=null && cs.length>0){//쿠키가 존재하니?
		for(int i=0;i<cs.length;i++){
			String c_name = cs[i].getName();
			if("JSESSIONID".equals(c_name)){
				c_value = cs[i].getValue();
				out.print("c_value:"+c_value);
			}
			else if("cmem_name".equals(c_name)){
				c_value = URLDecoder.decode(cs[i].getValue(),"utf-8");
			}
			/* 상품 정보 조회 하기 시작 */
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
			/* 상품 정보 조회 하기  끝 */
		}	
	}///end of if
	//out.print(c_value);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>POJO Framework 설계 시작페이지</title>

<jsp:include page="../../../0_src/_includeList/commonUI_S.jsp" flush="false"/>
<script type="text/javascript">
	function overlapTest(){
		var userid = $("#t_id").val();
		var param = "mem_id="+userid;
		$.ajax({
			method:"post",
			/*   /(프로젝트이름)/(프로젝트저장경로 맞추기)/매핑명 /회원업무 / 조회업무 /아이디중복검사 서블릿매핑	*/
			url:"/Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo",
			data:param,
			success:function(result){
				//존재여부에 따른 팝업창을 별도의 jsp페이지를 포워딩하여 진행하므로 내용필요없음
				console.log("돌아왔습니다");
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
	<h3>&nbsp;로그인 테스트</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value=" 로그인페이지 이동 " onclick="javascript:location.href='./member/pojo_login.jsp'" />
</div>
<div><br><br></div>

<div>
	<h3>&nbsp;아이디 중복검사 체크</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<b>URL-Pattern</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	/Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo
	<br>
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="t_id" type="text" value="test"/>
	<input id="btn_overlap" type="button" value="중복검사" onclick="overlapTest()" />
</div>
<div id="d_idCheckResult"></div>
<div><br><br></div>

<div>
	<h3>&nbsp;회원가입 테스트</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<b>URL-Pattern</b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br>
</div>
<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value=" 회원가입페이지 이동 " onclick="javascript:location.href='./member/pojo_signup.jsp'" />
</div>
<div><br><br></div>

</body>
</html>