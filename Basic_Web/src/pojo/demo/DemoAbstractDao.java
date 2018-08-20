package pojo.demo;

import java.util.Map;

import org.apache.log4j.Logger;

public abstract class DemoAbstractDao {
	Logger logger = Logger.getLogger(DemoAbstractDao.class);
	
	//회원가입에 대한 기본메소드
	public int memberSignup(Map<String,Object> pMap) {
		int result = 0;
		return result;
	}
	
	//회원아이디 중복검사에 대한 기본메소드
	public int memberIdExist(String mem_id) {
		int status = -2;
		return status;
	}
	
}
