package pojo.basic_ChargeLectureService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.basic_ChargeLectureService.controller.dao.CL_Dao_Member;
import pojo.basic_ChargeLectureService.controller.pack.ResultPack;
import util.web.HashMapBinder;

public class CL_Controller_Member implements CL_UpperController {
	Logger logger = Logger.getLogger(CL_Controller_Member.class);
	String work = null;
	//생성자
	public CL_Controller_Member(String work) {
		this.work = work;
		logger.info("	[[ Cntl.Member ]] 생성자 | 초기화 된 work = "+work);
	}
	
	@Override
	public ResultPack execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("	[[ Cntl.Member ]] call : execute()");
		
		Map<String,Object> pMap = new HashMap<>();
		new HashMapBinder(req).bind(pMap);
		
		List<Map<String,Object>> rlist = null;
		CL_Dao_Member mDao = new CL_Dao_Member();	//DB연동클래스
		ResultPack pack = new ResultPack();	//Front로 보낼 결과팩
		
		logger.info("	[[ Cntl.Member ]] 업무분기실행( work="+work+" )");
		//조회|아이디중복체크 요청결과 팩킹
		if("idOverlap".equals(work)) {
			logger.info("	[[ Cntl.Member ]] choose idOverlap");
			rlist = mDao.select(pMap,"idOverlap");
			int result = (Integer)rlist.get(0).get("result");
			logger.info("	[[ Cntl.Member ]] result = "+result);
			pack.setRedirect(false);//forward
			pack.setPath("./idOverlap.jsp?result="+result);//파라미터로 결과값을 넘김
			logger.info("	[[ Cntl.Member ]] pack setting has completed");
		}
		//Error packing
		else {
			logger.info("	[[ Cntl.Member ]] **ERROR | if문을 타지 않았습니다.");
			logger.info("	[[ Cntl.Member ]] **ERROR | work = "+work);
		}
		return pack;
	}
}
