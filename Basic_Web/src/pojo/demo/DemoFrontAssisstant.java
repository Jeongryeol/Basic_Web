package pojo.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pojo.demo.DemoFrontController;

/*******************************************************************************
 * [[ URL Pattern에 따른 업무명 분류 ]]
 * @패턴설계	[demo] / [head] / [crud] / [chop(.demo)]
 * @설계의도
 * 		demo : 프로젝트명<br>
 * 		head : 단위업무 (ex. 회원|쇼핑|투자|대출 등 )<br>
 * 		crud : 서버업무 (ex. 조회|입력|수정|삭제 )<br>
 * 		chop : 세부업무 (ex. 중복검사|결과조회|친구추가|내정보수정|물품삭제 등 )
 *******************************************************************************/
public class DemoFrontAssisstant {
	
	Logger logger = Logger.getLogger(DemoFrontController.class);//로그준비
	
	//전체경로에서 URL패턴을 잘라낸다
	public String getPattern(HttpServletRequest req) throws ServletException, IOException{
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String pattern = requestURI.substring(contextPath.length());//---/pojo/member/getList.demo
		logger.info("	[[ Front.As ]] pattern = "+pattern);
		return pattern;
	}
	//URL패턴에서 head(단위업무) 잘라내기
	public String getHead(String pattern) {
		String[] parts = pattern.split("/");
		String head = parts[4].toString();
		logger.info("	[[ Front.As ]] head = "+head);
		return head;
	}
	//URL패턴에서 crud(서버업무) 잘라내기
	public String getCRUD(String pattern) {
		String[] parts = pattern.split("/");
		String crud = parts[5].toString();
		logger.info("	[[ Front.As ]] crud = "+crud);
		return crud;
	}
	//URL패턴에서 chop(세부업무) 잘라내기
	public String getChop(String pattern) {
		String[] parts = pattern.split("/");
		String chop = parts[6].toString();
		logger.info("	[[ Front.As ]] chop = "+chop);
		return chop;
	}

}
