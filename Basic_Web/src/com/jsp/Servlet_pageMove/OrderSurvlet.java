package com.jsp.Servlet_pageMove;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderSurvlet extends HttpServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res)
				throws ServletException, IOException
	{
		System.out.println("doGet() 호출 성공");
		
		//자료구조를 DB연동하면서 받아와야하는 과정을 아래와 같이 담아서 갈음한다.
		List<Map<String,Object>> orderList = 
				new ArrayList<Map<String,Object>>();
		Map<String,Object> pMap =
				new HashMap<String,Object>();
		pMap.put("ord_no",123456);
		pMap.put("oname","이순신");
		pMap.put("goods","스킨");
		orderList.add(pMap);
		
		pMap = new HashMap<String,Object>();
		pMap.put("ord_no",123457);
		pMap.put("oname","이성계");
		pMap.put("goods","지갑");
		orderList.add(pMap);
		
		pMap = new HashMap<String,Object>();
		pMap.put("ord_no",123458);
		pMap.put("oname","강감찬");
		pMap.put("goods","축구공");
		
		orderList.add(pMap);//List 생성완료

		//세션에 자료담기(자세한 설명은 추후)
		HttpSession session = req.getSession();
		session.setAttribute("orderList",orderList);
		
		res.sendRedirect("jsonOrderList.jsp");//페이지이동-404에러
		
	/*URL Pattern에 등록된 페이지로 이동하고자 할때, 서블릿에서 페이지전환을 수행할 수 있다.*/
		//서블릿 안에서 페이지 이동 1
//		res.sendRedirect("jsonOrderList.jsp");
		
		//서블릿 안에서 페이지 이동 2
//		RequestDispatcher view = req.getRequestDispatcher("jsonOrderList.jsp");
//		view.forward(req, res);
	}
	
}
