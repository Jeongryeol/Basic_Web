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
	 * [[ �α��� :: ���̵�üũ/��й�ȣüũ/ȸ�����̵�+ȸ���� ��ȯ (�ϰ�ó��) ]]
	 * @param	pMap : ȭ�鿡�� �Է��� ȸ���� ���̵�(mem_id)�� ��й�ȣ(mem_pw)�� �������
	 * @return	List<Map<S,O>> : resultOut�÷��� ��� �� �ִ� ���
		a) 'The Id does not exist' 	//���̵� ��ü�� ���� ��� [-1]
		b) 'Passwords do not match 	//��й�ȣ�� Ʋ�� ��� [1>>-2]
		c) 'mem_name'				//���̵�,��й�ȣ�� ��� ��ġ�ϴ°�� [1>>2]
	 **************************************************************************/
	public List<Map<String,Object>>memberLogin(Map<String,Object> pMap) {
		logger.info("		[[ MemberDao ]] memberLogin ȣ�⼺�� - Dao���� ���Լ���!");
		//select ��ȸ����� ������ List���� ���� ����.
		//�� ��� NullPointerException�� �߻��ϴ� ������ �ȴ�.
		//���� �������� ����ϴ� ������ null�϶� �� ����� ��(NVL�Լ�)
		List<Map<String,Object>> rlist = sqlSes.selectList("memberLogin", pMap);//��ȸ����
		String resultOut = rlist.get(0).get("resultOut").toString();//��ȯ�� Ȯ��
		logger.info("		[[ MemberDao ]] resultOut = "+resultOut);
		return rlist;
	}
	
	/**************************************************************************
	 * [[ ���̵� �ߺ��˻� ]]
	 * @param	mem_id : ȭ�鿡�� �α��������� ����� ������� ���̵�
	 * @return	status : �⺻��:-2 / ���̵� ����(�ߺ�X): -1 / ���̵�����(�ߺ�):1
	 **************************************************************************/
	public int memberIdExist(String mem_id) {
		logger.info("		[[ MemberDao ]] memberIdExist ȣ�⼺�� - Dao���� ���Լ���!");
		int status = -2;//�ʱⰪ
		status = sqlSes.selectOne("memberIdExist", mem_id);
		logger.info("		[[ MemberDao ]] sta tus = "+status+" >> �ʱⰪ=-2, �ߺ�=2,����=-1");
		return status;
	}
	
	/**************************************************************************
	 * [[�ӽú�й�ȣ �߹�]]
	 * @param	mem_id : ȭ�鿡�� �α��� ������, '����'�� ����� ���̵� ������
	 * @return	temp_pw : �߱޵� 
	 **************************************************************************/
	public String getTempPW(String mem_id) {
		String temp_pw = null;
		return temp_pw;
	}
	
//----------------------------[ INSERT ]----------------------------------------------------------
	
	//ȸ������
	public int memberSignup(Map<String,Object> pMap) {
		logger.info("		[[ MemberDao ]] memberInsert ȣ�⼺�� - Dao���� ���Լ���!");
		int result = 0;
		result = sqlSes.insert("memberSignup", pMap);
		logger.info("		[[ MemberDao ]] result = "+result);
		return result;
	}
	
}
