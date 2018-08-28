package pojo.basic_ChargeLectureService;

import org.apache.log4j.Logger;

import pojo.basic_ChargeLectureService.controller.CL_Controller_Lecture;
import pojo.basic_ChargeLectureService.controller.CL_Controller_Member;
import pojo.basic_ChargeLectureService.controller.CL_Controller_Payment;
import pojo.basic_ChargeLectureService.controller.CL_UpperController;

public class CL_Judgment {
	Logger logger = Logger.getLogger(CL_Judgment.class);
	
	public CL_UpperController getController(String command) {
		logger.info("	[[ Judgment ]] call : getController / cammand = "+command);
		String[] commands = command.split("/");
		for(int i=0;i<commands.length;i++)//확인용 로그반복문 
				logger.info("	[[ Judgment ]] command["+i+"] = "+commands[i]);
		
		CL_UpperController controller = null;
		//넘어온 커맨드가 3덩어리 이상일때
		if(commands.length>=3) {
			String category = commands[1];
			String work = commands[2];
			work = work.substring(0,work.length()-6);//.basic 제외한 글자만 반환
			
			//회원관련 업무 요청일때
			if("member".equals(category)) {
				controller = new CL_Controller_Member(work);
				logger.info("	[[ Judgment ]] choose : Controller_Member");
			}
			//강의관련 업무 요청일때
			else if("lecture".equals(category)) {
				controller = new CL_Controller_Lecture(work);
				logger.info("	[[ Judgment ]] choose : Controller_Lecture");
			}
			//결제관련 업무 요청일때
			else if("lecture".equals(category)) {
				controller = new CL_Controller_Payment(work);
				logger.info("	[[ Judgment ]] choose : Controller_Payment");
			}
		}
		return controller;
	}
}
