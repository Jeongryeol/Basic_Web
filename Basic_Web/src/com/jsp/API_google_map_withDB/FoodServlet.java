package com.jsp.API_google_map_withDB;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class FoodServlet extends HttpServlet {

	//로그를 찍는 loj4j의 로거 사용하기
	Logger logger = Logger.getLogger(FoodServlet.class);
	
	/* 	Tomcat이 주입해주는 res, req를 사용할 수  있는것은
		doGet(), doPost()메소드이므로 외부에서 처리해서 다시 메소드로 넘겨줌 */
	private void doService(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		DAO_Food fDao = new DAO_Food();
		List<VO_FS_Menu> foodList = fDao.getFoodInfoList(null);
		System.out.println(foodList.size());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
	{
		doService(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
	{
		doService(req,res);
	}

}
