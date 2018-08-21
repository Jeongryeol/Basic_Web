package pojo.demo.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import pojo.demo.DemoAbstractDao;
import util.db_mybatis.DBConnection_pojoDemo;

public class DemoMemberDao{
	Logger logger = Logger.getLogger(DemoMemberDao.class);
	SqlSession sqlSes = new DBConnection_pojoDemo().getConnection();
	
//----------------------------[ SELECT ]----------------------------------------------------------	
	/**************************************************************************
	 * [[ 로그인 :: 아이디체크/비밀번호체크/회원아이디+회원명 반환 (일괄처리) ]]
	 * @param	pMap : 화면에서 입력한 회원의 아이디(mem_id)와 비밀번호(mem_pw)가 담겨있음
	 * @return	List<Map<S,O>> : resultOut컬럼에 담길 수 있는 결과
		a) 'The Id does not exist' 	//아이디 자체가 없는 경우 [-1]
		b) 'Passwords do not match 	//비밀번호가 틀린 경우 [1>>-2]
		c) 'mem_name'				//아이디,비밀번호가 모두 일치하는경우 [1>>2]
	 **************************************************************************/
	public List<Map<String,Object>>memberLogin(Map<String,Object> pMap) {
		logger.info("		[[ MemberDao ]] memberLogin 호출성공 - Dao계층 진입성공!");
		//select 조회결과가 없으면 List담을 수가 없다.
		//이 경우 NullPointerException이 발생하는 원인이 된다.
		//따라서 쿼리문이 출력하는 내용이 null일때 를 고려할 것(NVL함수)
		List<Map<String,Object>> rlist = sqlSes.selectList("memberLogin", pMap);//조회시작
		String resultOut = rlist.get(0).get("resultOut").toString();//반환값 확인
		logger.info("		[[ MemberDao ]] resultOut = "+resultOut);
		return rlist;
	}
	
	/**************************************************************************
	 * [[ 아이디 중복검사 ]]
	 * @param	mem_id : 화면에서 로그인했을때 얻어진 사용자의 아이디
	 * @return	status : 기본값:-2 / 아이디 없음(중복X): -1 / 아이디있음(중복):1
	 **************************************************************************/
	public int memberIdExist(String mem_id) {
		logger.info("		[[ MemberDao ]] memberIdExist 호출성공 - Dao계층 진입성공!");
		int status = -2;//초기값
		status = sqlSes.selectOne("memberIdExist", mem_id);
		logger.info("		[[ MemberDao ]] sta tus = "+status+" >> 초기값=-2, 중복=2,없음=-1");
		return status;
	}
	
	/**************************************************************************
	 * [[임시비밀번호 발번]]
	 * @param	mem_id : 화면에서 로그인 했을때, '세션'에 저장된 아이디를 가져옴
	 * @return	temp_pw : 발급된 
	 **************************************************************************/
	public String getTempPW(String mem_id) {
		String temp_pw = null;
		return temp_pw;
	}
	
//----------------------------[ INSERT ]----------------------------------------------------------
	
	//회원가입
	public int memberSignup(Map<String,Object> pMap) {
		logger.info("		[[ MemberDao ]] memberInsert 호출성공 - Dao계층 진입성공!");
		int result = 0;
		result = sqlSes.insert("memberSignup", pMap);
		logger.info("		[[ MemberDao ]] result = "+result);
		return result;
	}
	
}
