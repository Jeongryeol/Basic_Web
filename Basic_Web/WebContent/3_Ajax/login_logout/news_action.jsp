<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<form id="f_newsIns"><!-- form�±װ� �־�� �մϴ�  -->
<table>
  <!-- ������������������������������������������������������������������������������������������������������������ -->
  <tr>
    <td>���� : </td>
    <td> 
    	<input type="text" name="n_title" size="40">
    </td>
  </tr>
  <!-- ������������������������������������������������������������������������������������������������������������ -->
  <tr>
    <td>���� : </td>
    <td> 
		<textarea name="n_content" rows="5" cols="40"></textarea>
    </td>
  </tr>
  <!-- ������������������������������������������������������������������������������������������������������������ -->
  <tr>
    <td colspan="2" align="center">
    <!-- ��Ϲ�ư�� ���� �̺�Ʈ ó���� ��� ������ ���� �ؾߵ� ���?
    	 �� : news_ajax.jsp����...	onclick�� �ຸ��! -->
    	<input type="button" id="btn_ins" value="���" onclick="newsInsert()">
    </td>
  </tr>
  <!-- ������������������������������������������������������������������������������������������������������������ -->
</table>
</form>