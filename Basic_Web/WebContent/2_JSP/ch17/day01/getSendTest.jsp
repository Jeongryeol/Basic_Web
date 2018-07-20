<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- 

	배포위치는 어디 ? dev_jsp20180417/ch17/getSendTest.jsp

	전송버튼을 클릭했을때...
		누가? 업무담당자
		어디에? TomCat Server-dev_20180417 폴더 안으로
		무엇을? 이 요청(전송)을 처리할 대상 (서블릿 )  

	요청을 서블릿이 받았을때...
		MVC패턴(모델2)을 의심할 수 있다.
		
	[질문] 자바코드(Survlet,서블릿)으로 브라우저에 페이지 처리가 가능한 이유는 무엇일까?
		JFrame도 아니고, Applet을 쓴 것도 아닌데.. 어떻게(issue)?
		
		서블릿 안에서 Writer객체를 생성한 다음 out.print();메소드를 호출하는데,
		이때 파라미터에 html태그를 적는다. 그러면 브라우저에 쓰기가 일어나겠죠?
		그런데 그냥 쓰기만 해서는 안되요. 왜냐하면 mime type이 없이는 그냥 text에 불과하기 때문이죠
		
		그래서 먼저 mime type을 꼭 지정해 주어야 합니다.
		그래야 브라우저가 mime type을 확인하고 플러그인 기능으로 자기가 처리할 수 있습니다.
		
		그런데 자바코드에 print한 것이므로 브라우저에 실행시키려면 반드시 URL pattern정보가 필요하다.
		이것을 web.xml문서에 등록합니다. ( xml 문서가 오염되면 404 에러가 발생할 수 있음 )
		톰캣 버서를 기동하면 맨 먼저 web.xml문서를 scan합니다.
		그래서 서블릿이름으로 같 은 서블릿 이름 태그정보를 찾게되고 그 태그 정보안에 URL pattern을 확인함
		그래서 URL로 서블릿 자바 코드를 ㅊ호출 할 수 있게 되는 것.
	
	[힌트] doGet메소드 혹은 doPost메소드는 왜 IOException을 던지도록 되어 있는가?
	
	[예시] 네이버 혹은 구글의 로그인 을 생각해봐요...(인증과정말이죠?)
		네이버나 구글에 나만 로그인하나요? 불특정 다수로 누구나 할 수 있지 않나요?
		이 로그인요청을 누가 처리하도록 되어 있을까요? jsp? survlet? 아님 둘다?
		
		jsp문서는 확장자가 ****.jsp이고 servlet문서는 확장자가 ****.java이다.
		jsp문서는 인스턴스화 할 수 없을까?
		만일 인스턴스화를 할 수 없다는 추상클래스나 인터페이스 같은걸까?  
		
		단독으로 인스턴스화를 할 수 없다면 jsp문서에는 메소드를 선언해야하는 걸까?
		선언할 수 있다면 어떻게 호출하지?... 인스턴스화를 할 수 없다는데...
		그런데 서블릿은 확장자가 java니까 직접 인스턴스화가 가능 할 것 같은데...
		
		그 안에 오버라이드 메소드가 doGet(req,res), post(request,response) 두 개로 웹서비스를 요청받고 응답한다는데
		***.jsp페이지에서 요청을 받을때  doGet, doPost메소드 호출을 어떻게 구분하지?
		내가 호출할 수 있는건가?
		
		doGet메소드와 doPost메소드 둘 모두 파라미터로 request와 response객체를 제공받던데
		이것들로 뭘 하는 걸까? 누가 이것들을 인스턴스화 해두었지? 인스턴스화 해주는 시점은 언제일까...?
		
	[사고하기]
		
		 

 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>get방식으로 요청하기</title>

<script type="text/javascript" src="../js/jquery-1.12.0.js"></script>
<script type="text/javascript">
	function getSend(){
		$("#f_get").attr("method","get");
		$("#f_get").attr("action","서블릿 호출");
		$("f_get").submit();
	}
</script>

</head>
<body>

<!-- [[ form tag OPEN ]]──────────────────────────────────────────────────────── -->
<form id="f_get"><!-- form태그를 통해 전송방식을 선택한다? -->

<input type="text" id="t_id" name="t_id">
<input type="button" value="전송" onClick="getSend()">

</form>
<!-- [[ form tag CLOSE ]]──────────────────────────────────────────────────────── -->

</body>
</html>