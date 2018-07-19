package com.ch17.day01_console_browser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req
					 ,HttpServletResponse res) throws IOException, ServletException	{
		//이것은 서버의 콘솔 즉 톰캣 콘솔에 출력되는 것
		System.out.println("doGet호출성공");
		
		//이것은 브라우저 출력되는 것
		res.setContentType("text/html;charset=euc-kr");
		PrintWriter out = res.getWriter();
		out.print("<b>나는<font color='blue' size='20'>html</font>문서</b>");
	}
	
}
