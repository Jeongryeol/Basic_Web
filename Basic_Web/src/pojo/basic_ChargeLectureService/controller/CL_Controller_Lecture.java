package pojo.basic_ChargeLectureService.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.basic_ChargeLectureService.controller.pack.ResultPack;

public class CL_Controller_Lecture implements CL_UpperController {
	Logger logger = Logger.getLogger(CL_Controller_Member.class);
	String work = null;
	//생성자
	public CL_Controller_Lecture(String work) {
		this.work = work;
	}

	@Override
	public ResultPack execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//강의관련업무쪽으로 배분하는 컨트롤러
		return null;
	}

}
