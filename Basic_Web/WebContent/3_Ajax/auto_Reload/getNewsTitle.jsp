<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%! 
	//디클러레이션 : 전역변수 선언하거나 메소드를 선언할 수 있음. (인스턴스화 규칙이 달라서 사용빈도가 낮음.)
	int i = 0;
%>
<%
	StringBuilder news1 = new StringBuilder();
	StringBuilder news2 = new StringBuilder();
	StringBuilder news3 = new StringBuilder();
	StringBuilder news4 = new StringBuilder();
	StringBuilder news5 = new StringBuilder();
	StringBuilder news6 = new StringBuilder();
	//INSERT HERE-뉴스정보 담기
	news1.append("[★] 뉴스제목 1");
	news2.append("[☆] 뉴스제목 2");
	news3.append("[★] 뉴스제목 3");
	news4.append("[☆] 뉴스제목 4");
	news5.append("[★] 뉴스제목 5");
	news6.append("[☆] 뉴스제목 6");
	
	//무한루프
		switch(i){
			case 0 : {
				StringBuilder ntitle = new StringBuilder();
				ntitle.append("<table border='1'>");
				ntitle.append("<tr><td>"+news1.toString()+"</td></tr>");
				ntitle.append("</table>");
				out.clear();
				out.print(ntitle.toString());
				i++;
				break;
			}
			case 1 : {
				StringBuilder ntitle = new StringBuilder();
				ntitle.append("<table border='1'>");
				ntitle.append("<tr><td>"+news2.toString()+"</td></tr>");
				ntitle.append("</table>");
				out.clear();
				out.print(ntitle.toString());
				i++;
				break;
			}
			case 2 : {
				StringBuilder ntitle = new StringBuilder();
				ntitle.append("<table border='1'>");
				ntitle.append("<tr><td>"+news3.toString()+"</td></tr>");
				ntitle.append("</table>");
				out.clear();
				out.print(ntitle.toString());
				i++;
				break;
			}
			case 3 : {
				StringBuilder ntitle = new StringBuilder();
				ntitle.append("<table border='1'>");
				ntitle.append("<tr><td>"+news4.toString()+"</td></tr>");
				ntitle.append("</table>");
				i++;
				out.clear();
				out.print(ntitle.toString());
				break;
			}
			case 4 : {
				StringBuilder ntitle = new StringBuilder();
				ntitle.append("<table border='1'>");
				ntitle.append("<tr><td>"+news5.toString()+"</td></tr>");
				ntitle.append("</table>");
				out.clear();
				out.print(ntitle.toString());
				i++;
				break;
			}
			case 5 : {
				StringBuilder ntitle = new StringBuilder();
				ntitle.append("<table border='1'>");
				ntitle.append("<tr><td>"+news6.toString()+"</td></tr>");
				ntitle.append("</table>");
				out.clear();
				out.print(ntitle.toString());
			//	i++;
				i=0;
				break;
			}
		/* 	default : {
				i=0;
				break;
			} */
		}
%>
