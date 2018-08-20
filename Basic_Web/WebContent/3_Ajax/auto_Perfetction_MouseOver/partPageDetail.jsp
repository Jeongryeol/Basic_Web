<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map, java.util.HashMap, java.util.ArrayList" %>    
<%@ page import="java.util.List" %>    
<%
//var param = "picture="+picture;
	String imgName = request.getParameter("picture");
	String picDetail = null;
	List<Map<String,Object>> picList = new ArrayList<Map<String,Object>>();
	Map<String,Object> detailMap = new HashMap<String,Object>();
	detailMap.put("pic1.jpg","첫번째 그림설명");
	detailMap.put("pic2.jpg","두번째 그림설명");
	detailMap.put("pic3.jpg","세번째 그림설명");
	detailMap.put("pic4.jpg","네번째 그림설명");
	picList.add(detailMap);
	//Iterator<Map<String,Object>> it = picList.iterator();
	Object keys[] = picList.get(0).keySet().toArray();//4
	for(int i=0;i<keys.length;i++){
		String val = (String)keys[i];
		if(val.equals(imgName)){
			picDetail = (String)picList.get(0).get(imgName);
			break;
		}//////////end of if
	}//////////////end of for
	//out.print("picDetail"+picDetail);
%>    
<table width="350px" height="300px" border="0" borderColor="red">
	<tr>
		<td colspan="2" height="240px" align="center"><img src="./<%=imgName%>" width="300px" height="240px"></td>
	</tr>
	<tr>
		<td width="280px" height="60px"><%=picDetail %></td>
		<td align="center" width="70px" height="60px"><input type="button" value="닫기" onClick="clearMethod()"></td>
	</tr>
</table>