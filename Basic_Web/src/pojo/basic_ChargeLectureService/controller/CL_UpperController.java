package pojo.basic_ChargeLectureService.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.basic_ChargeLectureService.controller.pack.ResultPack;
/***********************************************************************************
 * [[ UpperController ]]
 * @author	Jeongryeol Lee
 * @purpose	Controller Ʋ�� �����ϴ� �������̽� 
 ***********************************************************************************/
public interface CL_UpperController {

	public ResultPack execute(HttpServletRequest req, HttpServletResponse res) throws Exception; 
}
