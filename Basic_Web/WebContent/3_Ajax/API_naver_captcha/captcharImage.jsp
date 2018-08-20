<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.OutputStream"%>

<%--
	이 페이지는 화면을 처리하는 페이지가 아닙니다.
	captchaView.jsp에 뿌려질 캡챠이미지를 (네이버API를 통해 웹에서)생성하고
	이 페이지를 요청한 위치에 ajax로 뿌리기 전까지 가져다주는 일을 처리하는 곳입니다.
	
	그러므로 반환값으로 캡챠이미지 이름이 필요합니다.
	왜냐하면 d_captcha에 그 이미지를 뿌릴 때 src속성에 이미지이름을 넣어야 이미지를 불러올 수 있기 때문이지요.
--%>
<%	//out.print("./googlePage.png");//단위테스트용으로 이미지를 뿌려줌
	
	//1단계 : 캡챠이미지 발급용 키발급
	String clientId = "4hItvAK19u6MoyPVewbq";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "e8v3XoiIwv";//애플리케이션 클라이언트 시크릿값";
    StringBuffer sb = null;//키값이 담길 스트링버퍼
   	String sendKey = null;
    try {
        String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;//response는 예약어이므로 br로 변경
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        sb = new StringBuffer();//키값이 담길 스트링버퍼
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        	//System.out.println(sb.toString());//확인
        	sendKey = sb.substring(8,24);
        }
        br.close();
    } catch (Exception e) {
        System.out.println(e);
    }
	    
    String apiURL //서버에 발급받은 키를 넘길 준비
       = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key="+sendKey;//이전에 발급받은 키 끼워넣음

    //out.print(apiURL);
    
%>
<!-- 넘어간 영역에서 이지유아이를 부분처리하기위해 이 페이지에서 새로 임포트 -->
<script type="text/javascript" src="/dev_HTML5/js/jquery.easyui.min.js"></script>
		<!-- 3단계 캡챠이미지 설치 -->
	<img id="captchaIMG" width="200px" height="90px" src="<%=apiURL %>">
	<br><!-- 새로고침 버튼 -->
	<a id="btn_reload" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
		onclick="newCaptcha()">새로고침</a>
	<input id="captchaKey" type="hidden" value="<%=sendKey%>">
<%	
/*  
    //캡챠이미지 발급받기 ( 이미지를 다운받아서 로컬에 생성하는 과정까지 를 수행하는 기본API구문)
    String imgPath = "E:\\dev_kosmo201804\\dev_HTML5\\WebContent\\ajax\\naver\\";//이미지경로
    try {
        String apiURL //서버에 발급받은 키를 넘길 준비
        	= "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key="+sendKey;//이전에 발급받은 키 끼워넣음
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        int responseCode = con.getResponseCode();
        if(responseCode==200) { // 정상 호출
            InputStream is = con.getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            // 랜덤한 이름으로  파일 생성
            String tempname = Long.valueOf(new Date().getTime()).toString();
            String filepath = imgPath+tempname+".jpg";
            File f = new File(filepath);
            f.createNewFile();
            OutputStream outputStream = new FileOutputStream(f);//실제 이미지 생성
            while ((read =is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            is.close();
            outputStream.close();
        	System.out.println(filepath);//생성파일경로 확인
        	out.print(filepath);//브라우저에 출력 (ajax가 인식하는 HTML부분)
        } else {  // 에러 발생
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            String inputLine;
            sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            System.out.println("[[에러에러에러]]response.toString()");
            
        }
    } catch (Exception e) {
        System.out.println(e);
    }
     */
%>