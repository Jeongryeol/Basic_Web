package com.jsp.Survlet_limit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/********************************************************************************
 * 	
 * 	다음은 게시판 관리를 위해 만든 클래스의 일부입니다.
 * 	이 클래스에서 "조회/입력/수정/삭제"기능이 처리 되기를 원합니다.
 * 	이 업무를 타인에게 위임하기로 했다면
 * 	타인은 위 4가지 업무처리를 위해 메소드를 어떻게 나누어 관리해야 할까요?
 * 
 ********************************************************************************/
/*
 * 
 * 	1. 	한개의 클래스로 서블릿을 받고 , [ /board/crud.do ] 
 * 		업무에 따라 query/String을 병렬로 주고, if/switch로 분기함
 * 			[req.getParameter("name") : String // id는 화면단에서 , name은 서버단에서 사용한다!!]
 * 							    ▼query/String
 *			/board/crud.do?command=select
 *			/board/crud.do?command=insert
 *			/board/crud.do?command=update
 *			/board/crud.do?command=delete
 *
 *	2.	web.xml에 URL패턴을 각각 4가지로 등록해야함.
 *		예를들어, 1000가지 상품의 주문에 대한 페이지 관리가... 힘듬 ( X )
 * 
 *  3.	doGet(req,res)는 인스턴스화한적이 없으니 생성자가 없지만, 톰켓이 주입해준다.
 *  	주소번지를 전역변수로 넘겨서 method를 독립적으로 이용함  
 *  
 */
public class BoardSurvlet extends HttpServlet {
	/* 효과적인 디버깅을 위한 log4j 활용하기 (org.apache를 import하세요~) */
	Logger logger = Logger.getLogger(BoardDAO.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException
	{
		logger.info("doGet() 메소드 호출 성공");

		//URL매핑  http://IP:Port/Basic_Web/ch17/crud?command=select|insert|update|delete
		//'query/String'을 URL 끝에 넣어서 확인합니다.
		/*
		 * 	어노테이션을 통해 URL매핑된 이 서블릿클래스를 실행하고,
		 * 	URL끝의(..do?command=______) 'query/String'을 변경해서 구현결과를 확인해보세요.
		 * 
		 * 	http://localhost:9000/Basic_Web/ch17/crud.do?command=select
		 * 	http://localhost:9000/Basic_Web/ch17/crud.do?command=insert
		 * 	http://localhost:9000/Basic_Web/ch17/crud.do?command=update
		 * 	http://localhost:9000/Basic_Web/ch17/crud.do?command=delete
		 * 
		 */
		
		//분기기준을 화면단에 지정한 객체의 name을 파라미터로 받고 값을 저장함(String타입)
		//id는 화면단에서 , name은 서버단에서 사용한다!!
		String command = req.getParameter("command");
		
		//서버와의 만남을 위해 DAO클래스도 설계 및 준비한다.
		//DAO클래스는 서버와의 연결을 수행하는 클래스이고, DB에서 자료를 받아오는 과정이 담겨있어야한다.
		BoardDAO bDAO = new BoardDAO();
		
		res.setCharacterEncoding("EUC-KR");
		res.setContentType("text/html;cahrset=EUC-KR");
		PrintWriter out = res.getWriter();
		
		//[조회]게시글 목록 조회하기 구현할 때
		if("select".equals(command)) {
			logger.info("select 메소드 호출 성공");
			//INSERT HERE : DB연동
			out.println("<html><head><title>WebServletTest</title></head>");
			out.println("<body>");
			out.println("<font color='blue' size='20'>SELECT</font>가 호출성공했습니다.");
			out.println("</body></html>");
		}
		//[등록]글 등록하기 구현할 때
		else if("insert".equals(command)) {
			logger.info("insert 메소드 호출 성공");
			//INSERT HERE : DB연동
			out.println("<html><head><title>WebServletTest</title></head>");
			out.println("<body>");
			out.println("<font color='red' size='20'>INSERT</font>가 호출성공했습니다.");
			out.println("</body></html>");
		}
		//[수정]글 수정하기 구현할 때
		else if("update".equals(command)) {
			logger.info("update 메소드 호출 성공");
			//INSERT HERE : DB연동
			out.println("<html><head><title>WebServletTest</title></head>");
			out.println("<body>");
			out.println("<font color='green' size='20'>UPDATE</font>가 호출성공했습니다.");
			out.println("</body></html>");
		}
		//[삭제]글 삭제하기 구현할 때
		else if("delete".equals(command)) {
			logger.info("delete 메소드 호출 성공");
			//INSERT HERE : DB연동
			out.println("<html><head><title>WebServletTest</title></head>");
			out.println("<body>");
			out.println("<font color='magenta' size='20'>DELETE</font>가 호출성공했습니다.");
			out.println("</body></html>");
		}
	}
}
