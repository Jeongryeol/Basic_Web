package pojo.demo.member;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
/**
 * [[ MemberLogic ]]
 * @작성자	이정렬
 * @작성일	2018.08.13	
 * @설계의도
 * 		단순업무(CRUD/입력|수정|삭제|조회-테이블이 오직 한 개 일때)처리에서는 Logic클래스에서 하는 일이 없다.
 * 		하지만 로직계층은 다음과 같은 기준에서 필요하다
 * 		1) 프레임웍 구성의 약속
 * 		2) 판단, 선택, 책임, 결정 : 여러가지 프로세스를 한 개 클래스에서 처리할 때 필요성이 드러남
 *
 */
public class DemoMemberLogic {
	Logger logger = Logger.getLogger(DemoMemberLogic.class);
	/* 이 클래스에서 직접 호출하는 것은 의존성 주입이 아님.
	 * 객체에 대한 라이프사이클을 호출하는 측에서 모두 관리하므로 책임이 개발자에게 있다.
	 * 그러나 스프링과 같은 오픈 프레임웍을 활용하면 이렇게 직접 인스턴스화 하지 않는다.
	 * (해서는 안됨. 서버에 부담이 실림)
	 */
	DemoMemberDao mDao = new DemoMemberDao();

//----------------------------[ SELECT ]----------------------------
	
	//로그인
	public List<Map<String,Object>> memberLogin(Map<String,Object> pMap) {
		logger.info("	[[ Member.L ]] memberLogin 호출성공 - Logic계층 진입성공!");
		return mDao.memberLogin(pMap);
	}
	
	//아이디중복검사

//----------------------------[ INSERT ]----------------------------
	
	//회원가입
	public int memberSignup(Map<String,Object> pMap) {
		logger.info("	[[ Member.L ]] memberSignup 호출성공 - Logic계층 진입성공!");
		int result = 0;
		result = mDao.memberSignup(pMap);//dao와 연결하여 삽입업무 수행 후 결과값 리턴
		return result;
	}
	
}
