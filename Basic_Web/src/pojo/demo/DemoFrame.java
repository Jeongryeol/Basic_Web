package pojo.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*******************************************************************************
 * [[ 페이지 이동 모듈 ]]
 * @작성자	이정렬
 * @작성일	2018.08.10	
 * @설계의도
 * 		"FrontController"가 분기한 단위업무를
 * 		업무별로 설정된 각" Controller"계층에서 처리할 세부분기업무(CRUD)에 대한 기본틀을 제공
 *******************************************************************************/

public interface DemoFrame {

	/***************************************************************************
	 * [[ 각 세부업무를 분기하여 처리하는 기본틀 ]]
	 * @param	req	 : 화면에서 전달받은 요청객체 - 꺼내서 DB에 요청할 내용
	 * @param	res	 : 화면으로 전달보낼 응답객체 - 쿠키/세션 등에 담을때 사용
	 * @param	pMap : 업무분기정보가 기록된 Map데이터 - "command/fork"
	 * @return	_PageContainer
	 ***************************************************************************/
	public DemoPageCarrier CRUD(HttpServletRequest req, HttpServletResponse res
							  ,Map<String,Object> pMap) throws Exception;
	
}
