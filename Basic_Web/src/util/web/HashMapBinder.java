package util.web;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/*************************************************************************************
 * [[ Request �� Map��ȯ Ŭ���� ]] 
 * @author	Jeongryeol
 * @comment	 ��û��ü�� ��� ������ Name:Value������� Map�� ����ִ� ���δ� Ŭ����
 *************************************************************************************/
public class HashMapBinder {
	HttpServletRequest req = null;//��û��ü �غ�
	//������
	public HashMapBinder(HttpServletRequest req) {
		this.req = req;//��û��ü(request)�� �����ּҸ� �޾� �����ص�
	}
	//Web���� ������ ��û��ü�� �ڷḦ ���� (������ �ʱ�ȭ)
	//DB�� ���� Map�迭 �ڷ����� �Űܴ���ִ� Ŭ����  
	public void bind(Map<String,Object> pMap){
		pMap.clear();//�ڷ��� �ʱ�ȭ
		//Enumeration : �ڹ� �ʱ������ ���ߵ� �������̽�
		//��ü���� ����(Vector)�� ��ü���� �ϳ��� ������� ��� ó���� �� �ִ� �޼ҵ带 �����ϴ� �̷���
		Enumeration<String> en = req.getParameterNames();//name�Ӽ����� �� ��� ������ ������� ����
		while(en.hasMoreElements()) {//�ڷ��� �ϰ�����
			String key = en.nextElement();		//�����ַ��̼ǿ� ��� �Ӽ���(name)�� Ű(key)�� ����
			pMap.put(key,req.getParameter(key));//Ű(key)�� Ű�� ���� ����ִ� ��(value)�� Map�� ����
		}
	}
}
