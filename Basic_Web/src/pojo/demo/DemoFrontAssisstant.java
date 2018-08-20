package pojo.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pojo.demo.DemoFrontController;

/*******************************************************************************
 * [[ URL Pattern�� ���� ������ �з� ]]
 * @���ϼ���	[demo] / [head] / [crud] / [chop(.demo)]
 * @�����ǵ�
 * 		demo : ������Ʈ��<br>
 * 		head : �������� (ex. ȸ��|����|����|���� �� )<br>
 * 		crud : �������� (ex. ��ȸ|�Է�|����|���� )<br>
 * 		chop : ���ξ��� (ex. �ߺ��˻�|�����ȸ|ģ���߰�|����������|��ǰ���� �� )
 *******************************************************************************/
public class DemoFrontAssisstant {
	
	Logger logger = Logger.getLogger(DemoFrontController.class);//�α��غ�
	
	//��ü��ο��� URL������ �߶󳽴�
	public String getPattern(HttpServletRequest req) throws ServletException, IOException{
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String pattern = requestURI.substring(contextPath.length());//---/pojo/member/getList.demo
		logger.info("	[[ Front.As ]] pattern = "+pattern);
		return pattern;
	}
	//URL���Ͽ��� head(��������) �߶󳻱�
	public String getHead(String pattern) {
		String[] parts = pattern.split("/");
		String head = parts[4].toString();
		logger.info("	[[ Front.As ]] head = "+head);
		return head;
	}
	//URL���Ͽ��� crud(��������) �߶󳻱�
	public String getCRUD(String pattern) {
		String[] parts = pattern.split("/");
		String crud = parts[5].toString();
		logger.info("	[[ Front.As ]] crud = "+crud);
		return crud;
	}
	//URL���Ͽ��� chop(���ξ���) �߶󳻱�
	public String getChop(String pattern) {
		String[] parts = pattern.split("/");
		String chop = parts[6].toString();
		logger.info("	[[ Front.As ]] chop = "+chop);
		return chop;
	}

}
