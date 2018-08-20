package pojo.demo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*******************************************************************************
 * [[ ������ �̵� ��� ]]
 * @�ۼ���	������
 * @�ۼ���	2018.08.10	
 * @�����ǵ�
 * 		"FrontController"�� �б��� ����������
 * 		�������� ������ ��" Controller"�������� ó���� ���κб����(CRUD)�� ���� �⺻Ʋ�� ����
 *******************************************************************************/

public interface DemoFrame {

	/***************************************************************************
	 * [[ �� ���ξ����� �б��Ͽ� ó���ϴ� �⺻Ʋ ]]
	 * @param	req	 : ȭ�鿡�� ���޹��� ��û��ü - ������ DB�� ��û�� ����
	 * @param	res	 : ȭ������ ���޺��� ���䰴ü - ��Ű/���� � ������ ���
	 * @param	pMap : �����б������� ��ϵ� Map������ - "command/fork"
	 * @return	_PageContainer
	 ***************************************************************************/
	public DemoPageCarrier CRUD(HttpServletRequest req, HttpServletResponse res
							  ,Map<String,Object> pMap) throws Exception;
	
}
