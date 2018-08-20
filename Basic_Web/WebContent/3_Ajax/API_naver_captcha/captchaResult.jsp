<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URL, java.net.HttpURLConnection" %>    
<%@ page import="java.io.BufferedReader, java.io.InputStreamReader" %>    
<%@ page trimDirectiveWhitespaces="true" %>
<%
	String clientId = "mhkDPKVN6jZJhjVRvCrB";//애플리케이션 클라이언트 아이디값";
	String clientSecret = "C2fuWbk3NP";//애플리케이션 클라이언트 시크릿값";
	try {
		String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
		String key = request.getParameter("captchakey"); // 캡차 키 발급시 받은 키값
		String value = request.getParameter("value"); // 사용자가 입력한 캡차 이미지 글자값
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value="+ value;
		//out.print("apiURL:"+apiURL);
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if (responseCode == 200) { // 정상 호출
			out.print("200 ok");
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			out.print("204 error");
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();
		//out.println(res.toString());
	} catch (Exception e) {
		out.println(e);
	}
%>