package com.ch17.day03_annotationMapping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ServletAnnotationTest")
public class WebSurvletAnnotation extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req
					 ,HttpServletResponse res)
				throws ServletException, IOException
	{
		res.setCharacterEncoding("EUC-KR");
		res.setContentType("text/html;cahrset=EUC-KR");
		
		PrintWriter out = res.getWriter();
		out.println("<html><head><title>WebServletTest</title></head>");
		out.println("<body>");
		out.println("나는 <font color='red' size='20'>html</font>입니다.");
		out.println("</body></html>");
	}
}
