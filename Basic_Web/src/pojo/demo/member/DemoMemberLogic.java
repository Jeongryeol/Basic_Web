package pojo.demo.member;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
/**
 * [[ MemberLogic ]]
 * @�ۼ���	������
 * @�ۼ���	2018.08.13	
 * @�����ǵ�
 * 		�ܼ�����(CRUD/�Է�|����|����|��ȸ-���̺��� ���� �� �� �϶�)ó�������� LogicŬ�������� �ϴ� ���� ����.
 * 		������ ���������� ������ ���� ���ؿ��� �ʿ��ϴ�
 * 		1) �����ӿ� ������ ���
 * 		2) �Ǵ�, ����, å��, ���� : �������� ���μ����� �� �� Ŭ�������� ó���� �� �ʿ伺�� �巯��
 *
 */
public class DemoMemberLogic {
	Logger logger = Logger.getLogger(DemoMemberLogic.class);
	/* �� Ŭ�������� ���� ȣ���ϴ� ���� ������ ������ �ƴ�.
	 * ��ü�� ���� ����������Ŭ�� ȣ���ϴ� ������ ��� �����ϹǷ� å���� �����ڿ��� �ִ�.
	 * �׷��� �������� ���� ���� �����ӿ��� Ȱ���ϸ� �̷��� ���� �ν��Ͻ�ȭ ���� �ʴ´�.
	 * (�ؼ��� �ȵ�. ������ �δ��� �Ǹ�)
	 */
	DemoMemberDao mDao = new DemoMemberDao();

//----------------------------[ SELECT ]----------------------------
	
	//�α���
	public List<Map<String,Object>> memberLogin(Map<String,Object> pMap) {
		logger.info("	[[ Member.L ]] memberLogin ȣ�⼺�� - Logic���� ���Լ���!");
		return mDao.memberLogin(pMap);
	}
	
	//���̵��ߺ��˻�

//----------------------------[ INSERT ]----------------------------
	
	//ȸ������
	public int memberSignup(Map<String,Object> pMap) {
		logger.info("	[[ Member.L ]] memberSignup ȣ�⼺�� - Logic���� ���Լ���!");
		int result = 0;
		result = mDao.memberSignup(pMap);//dao�� �����Ͽ� ���Ծ��� ���� �� ����� ����
		return result;
	}
	
}
