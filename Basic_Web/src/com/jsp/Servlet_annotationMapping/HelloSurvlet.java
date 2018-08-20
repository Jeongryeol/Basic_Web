package com.jsp.Servlet_annotationMapping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.PortableInterceptor.ForwardRequest;

import com.jsp.Servlet_basic.HelloServlet;
import com.sun.istack.internal.logging.Logger;
import util.web.Post_Hangul_Conversion;
					//패턴경로의 가장 앞의 슬래쉬로 dev_jsp20180417 path로부터 출발한다는 의미를 내포함
@WebServlet(urlPatterns = "/ch17/hello.dk")
public class HelloSurvlet extends HttpServlet {

	Logger logger = Logger.getLogger(HelloSurvlet.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException{
		logger.info("doGet호출성공 - log4j를 활용해서 다양한 로그 확인 ");
		//이것은 서버의 콘솔 즉 톰캣 콘솔에 출력되는 것
		System.out.println("doGet호출성공");
		//이것은 브라우저 출력되는 것
		res.setContentType("text/html;charset=euc-kr");
		PrintWriter out = res.getWriter();
		out.print("<b>나는<font color='blue' size='20'>html</font>문서</b>");
	}
	/**********************************************************************
	 *  화면단에서 post방식으로 요청하면 doPost메소드가 호출됨
	 *  @req - 톰켓서버가 서블릿에 대한 요청이 일어나면 요청객체를 주입해줌.
	 *  @res - 톰켓서버가 서블릿에 대한 요청이 일어나면 응답객체를 주입해줌.
	 *  doPost는 메소드 오버라이딩된 메소드이므로 리턴타입을 바꿀 수 없음.
	 *  doPost는 URL요청으로는 절대 호출할 수 없음. [필수암기사항!]
	 *  	해결방법 : 자바스크립트 코드에서만 호출할 수 있음.
	 *  	페이지를 이동하는 메소드와 함수 정리해둘 것.
	 **********************************************************************/
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException{
		logger.info("doPost호출성공 - log4j를 활용해서 다양한 로그 확인 ");
		//서버의 콘솔 즉, 톰켓 콘솔에 출력한다.
		System.out.println("doPost호출 성공");
		req.setCharacterEncoding("euc-kr");//받는 인코딩 타입을 지정
		res.setCharacterEncoding("euc-kr");//내보내는 인코딩 타입을 지정
		res.setContentType("text/html;charset=euc-kr");//내보내는 자료의 마임타입과 내부기록방식(차셋)
		PrintWriter out = res.getWriter();
		//사용자가 화면에 입력한 이름을 출력해보세요
		//insert here
		String name = req.getParameter("tb_name");//인코딩이  필요할 것
		//String name = Post_Hangul_Conversion.toKor(req.getParameter("tb_name"));
		logger.info("사용자가 입력한 이름 : "+name);
		//아래는 브라우저에 출력된다.
		out.print("<b>사용자가 입력한 이름은<font color='blue' size='20'>"+name+"</font> 입니다.</b>");
		//res.sendRedirect("./end.jsp");//페이지 이동
	}
	
}
