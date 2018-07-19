<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<form id="f_newsIns"><!-- form태그가 있어야 합니다  -->
<table>
  <!-- ────────────────────────────────────────────────────── -->
  <tr>
    <td>제목 : </td>
    <td> 
    	<input type="text" name="n_title" size="40">
    </td>
  </tr>
  <!-- ────────────────────────────────────────────────────── -->
  <tr>
    <td>내용 : </td>
    <td> 
		<textarea name="n_content" rows="5" cols="40"></textarea>
    </td>
  </tr>
  <!-- ────────────────────────────────────────────────────── -->
  <tr>
    <td colspan="2" align="center">
    <!-- 등록버튼에 대한 이벤트 처리는 어느 페이지 에서 해야될 까요?
    	 답 : news_ajax.jsp에서...	onclick을 줘보자! -->
    	<input type="button" id="btn_ins" value="등록" onclick="newsInsert()">
    </td>
  </tr>
  <!-- ────────────────────────────────────────────────────── -->
</table>
</form>
