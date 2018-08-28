<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List, java.util.Map" %>
<%-- src.util.db_mybatis._sample.mapper.SampleQuery.xml ������ id ���� �Ʒ� Bean�׼��±׿� �Է��� --%>
<jsp:useBean id="testMapSelect" scope="request" class="com.mybatis.Sample_DAO_MyBatis"/>
<%=
	"<b>@title</b>	MyBatis�� Ȱ���Ͽ� CRUD ���� �� �����ϱ�"+
"<br><b>@date</b>	2018.07.26"+
"<br><b>@author</b>	Jeongryeol Lee"+
"<br><b>@email</b>	duxbellorn@gmail.com"+
"<br><b>@comment</b>"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"�� jsp������ �Ʒ� �� ����� �ڷḦ ���� �غ�� �ڷ��Դϴ�."+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"Java Resource/src/util/db_myBatis �� ��������"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"Java Resource/src/com/ajax/mybatis/BasicDAO_MyBatis.java"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"<b><font color='red'>�ݵ�� �Բ� �ڷḦ Ȯ���Ͻð�</font></b>"+
"<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"<b>Back-end���� Front-end���� �ڷᰡ �����Ǵ� ������ �����Ͻñ� �ٶ��ϴ�.</b>"+
"<br>����������������������������������������������������������������������������������������<br>"
%>
<%
	//��û������������ ȭ��ܿ��� ��û��ü(request)�� �Ѿ�� ���ǰ�(����� �Է�)
	int param_num = 10;//�ùķ��̼� Ŭ������, �� ���� 10�̶�� �׽�Ʈ�� ��ü�� �������.
	out.print("<b>[ �˸� ]</b> ����ڰ� "+param_num+"���� �˻���û�Ͽ����ϴ�.<br>");
	
	out.print("<b>[ �˸� ]</b> DB�� �ڷ���ȸ�� ��û�մϴ�.<br>");
	List<Map<String,Object>> rlist 			 	 //��ȯ�� �ڷḦ ���� List��ü
	 		= testMapSelect.getDataList(param_num);//DB�� ��ȸ��û�� �ϴ� �޼ҵ�� ���ǰ� ����
	//DB�κ��� �ڷᰡ �����Ͽ� ������������ �ڷᰡ �����
	
	out.print("<b>[ �˸� ]</b> DB���� ��û�� �ڷᰡ �����Ͽ����ϴ�.<br>");
	out.print("<b>[ �˸� ]</b> �ڷḦ ����մϴ�.<br>");
	out.print("����������������������������������������������������������������������������������������</br>");
	//������ �ڷ� ȭ�鿡 ����ϱ�
	if(rlist!=null){
		for(Map pMap:rlist){//������ for��
		out.print(pMap);	//ListŸ���� ū ���տ� MapŸ������ ��� �� ��ҵ��� �ϳ��� ���
		out.print("<br>");	//�ٹٲ�
		}
	}else{
		out.print("��ȸ�� �����߽��ϴ�!<br>rlist = "+rlist);
	}
	//�� �ڷḦ ��� ������ ���ļ� ȭ�鿡 ���� �˴ϴ�.
%>
