<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "javax.mail.*, javax.mail.internet.*, java.util.Properties" %>

<%
	//Naver POP3/STMP를 이용한 메일발송 프로그램 설정하기
	  String smtpServer   = "smtp.naver.com";		//SMTP서버명
	  int 	 smtpPort 	  = 465;					//SMTP포트번호
	  String sendMailAddr = "coco-chloe@naver.com";	//계정메일주소
final String sendId 	  = "coco-chloe";				//NAVER계정명
final String sendPw 	  = "qwer2100";			//비밀번호
	
	//메일 받는 사람 설정
	String receiveMailAddr = "duxbellorn@gmail.com";
	String mailSubject = "안녕하세요. jar파일을 이용한 메일 테스트 입니다.";
	String mailContent = "메일 발신을 테스트하는 중입니다.";
	
	Properties props = System.getProperties();
	props.put("mail.smtp.host",smtpServer);
	props.put("mail.smtp.port",smtpPort);
	props.put("mail.smtp.auth",true);
	props.put("mail.smtp.ssl.enable",true);
	props.put("mail.smtp.ssl.trust",smtpServer);
	
	Session session2 = Session.getDefaultInstance(props, new Authenticator(){
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(sendId,sendPw);
		}
	});
	
	session2.setDebug(true);
	Message msg = new MimeMessage(session2);
	msg.setFrom(new InternetAddress(sendMailAddr));
	msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAddr));
	msg.setSubject(mailSubject);
	msg.setText(mailContent);
	Transport.send(msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>