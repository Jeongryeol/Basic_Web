package pojo.demo;

import java.util.Map;

import org.apache.log4j.Logger;

public abstract class DemoAbstractDao {
	Logger logger = Logger.getLogger(DemoAbstractDao.class);
	
	//ȸ�����Կ� ���� �⺻�޼ҵ�
	public int memberSignup(Map<String,Object> pMap) {
		int result = 0;
		return result;
	}
	
	//ȸ�����̵� �ߺ��˻翡 ���� �⺻�޼ҵ�
	public int memberIdExist(String mem_id) {
		int status = -2;
		return status;
	}
	
}
