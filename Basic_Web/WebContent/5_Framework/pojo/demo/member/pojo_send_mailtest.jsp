<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "javax.mail.*, javax.mail.internet.*, java.util.Properties" %>

<%
	//Naver POP3/STMP�� �̿��� ���Ϲ߼� ���α׷� �����ϱ�
	  String smtpServer   = "smtp.naver.com";		//SMTP������
	  int 	 smtpPort 	  = 465;					//SMTP��Ʈ��ȣ
	  String sendMailAddr = "coco-chloe@naver.com";	//���������ּ�
final String sendId 	  = "coco-chloe";				//NAVER������
final String sendPw 	  = "qwer2100";			//��й�ȣ
	
	//���� �޴� ��� ����
	String receiveMailAddr = "duxbellorn@gmail.com";
	String mailSubject = "�ȳ��ϼ���. jar������ �̿��� ���� �׽�Ʈ �Դϴ�.";
	String mailContent = "���� �߽��� �׽�Ʈ�ϴ� ���Դϴ�.";
	
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