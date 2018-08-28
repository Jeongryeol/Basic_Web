package com.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.web.BasketMgr;

public class BasketTest extends HttpServlet {

	public void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BasketMgr bmgr = new BasketMgr(req, res);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doService(req,res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doService(req,res);
	}
	
}
