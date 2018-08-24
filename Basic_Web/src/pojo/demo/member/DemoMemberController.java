package pojo.demo.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.demo.DemoFrame;
import pojo.demo.DemoPageCarrier;
import util.web.CookieMgr;
import util.web.HashMapBinder;
import util.web.Secure_Base64;

public class DemoMemberController implements DemoFrame{
	//��ƿ��Ƽ
	Logger logger = Logger.getLogger(DemoMemberController.class);//�α��غ�
	CookieMgr cm = null;
	//��ȯ�ڷ� �غ�
	DemoPageCarrier rpc = new DemoPageCarrier();
	String path = null;
	boolean isRedirect = false;
	List<Map<String,Object>> demoList = null;
	int status = -2; 
/* 	[[ ���°� ���� ]]
		  2 : ���̵�, ��� ��� ����
		  1 : ���̵� ���� �Ǵ� ���̵� �ߺ�����
		 -1 : ���̵� Ʋ�� �Ǵ� ���̵� �ߺ�����
		 -2 : �⺻�� 
*/	int result = 0;
	
	/******************************************************************************************************************
	 * @param	req : ���ǻ���, ����ڰ� �Է��� �� ��û, �ѱ�ó��, forwardó��, scope���� �б�, ����ҿ���
	 * @param	res : ����Ÿ��, ���ڵ�Ÿ�� ����, PrintWriter��ü����(����������ó������),�������̵�ó��(sendRedirect/forward)
	 * @param	pMap : ����ڰ� �Է��� ��, View�������� ������ �� ��� 
	 * @return	PageCorrier : ������ �̵� ���, ���� ��������?(����������), �ڷᱸ���߰�(����ó����� ���)
	 ******************************************************************************************************************/
	@Override //WorkFrame�� ���� ¥�� ���·� ����
	public DemoPageCarrier CRUD(HttpServletRequest req, HttpServletResponse res, Map<String, Object> pMap) throws Exception {
		logger.info("	[[ Member.C ]] CRUD ȣ�⼺�� - MemberController���� ���Լ��� !");
		Secure_Base64 sb = null;
		//���� �б��� ���� DAO ���� ����
		String crud = pMap.get("crud").toString();//�������� �б���� : DAO����
		String chop = pMap.get("chop").toString();//���ξ��� �б���� : DAO���� �޼ҵ弱��
		
		//[��ȸ]������ ���Ͽ�
		//URL���� : /Basic_Web/5_Framework/pojo/demo/member/select/ ]]
		if("select".equals(crud)) {
			logger.info("	[[ Member.C ]] select :: ��ȸ���� ����");
			
			//�α��ο� ���� ��û�̶��
			//URL���� : /Basic_Web/5_Framework/pojo/demo/member/select/memberLogin.demo" ]]
			if("memberLogin.demo".equals(chop)) {
				logger.info("	[[ Member.C ]] member login :: ȸ���α��� ����");
				//��û��ü���� ID�� PW�� ����
				String mem_id = req.getParameter("mem_id");
				String mem_pw = req.getParameter("mem_pw");
				//��ȣ��� �����ϱ� : Base64
				sb = new Secure_Base64();//��ȣ��� ����
				String sec_mem_id = sb.changeBase64(mem_id);
				String sec_mem_pw = sb.changeBase64(mem_pw);
				//���
				pMap = new HashMap<>();//Logic������ �ѱ������ ���ο� pMap�غ�
				pMap.put("mem_id", sec_mem_id);
				pMap.put("mem_pw", sec_mem_pw);
				
				//new HashMapBinder(req).bind(pMap);				//Logic������ �ѱ������ ���ο� pMap�غ�
				demoList = new DemoMemberLogic().memberLogin(pMap);	//Logic������ pMap���� �������� ����� ����
				
				cm = new CookieMgr(req, res);//��Ű�Ŵ��� �ʱ�ȭ
				String resultOut = demoList.get(0).get("resultOut").toString();
				
				//�α��ΰ���ǵ�
				logger.info("	[[ Member.C ]] resultOut : "+resultOut);
				if("The ID does not exist".equals(resultOut)) {//���̵� ����
					isRedirect = false;//������
					path = "./pojo_mem_login_fail.jsp";//����������
					demoList = null;
					status = -1;
					cm.setCookie("logOnStatus", String.valueOf(status));
					
				} else if("Passwords do not match".equals(resultOut)){//��й�ȣƲ��
					isRedirect = false;//������
					path = "./pojo_mem_login_fail.jsp";//����������
					demoList = null;
					status = -2;
					cm.setCookie("logOnStatus", String.valueOf(status));
					
				} else if(!"The ID does not exist".equals(resultOut)
						&&!"Passwords do not match".equals(resultOut)) {//�α��ο� ������ ���
					isRedirect = true;//��������Ʈ
					path = "./pojo_mem_login_success.jsp";
					status = 2;
					cm.setCookie("logOnStatus", String.valueOf(status));//�α��� ����
					cm.setCookie("mem_name",resultOut);//��Ű�� ����� �̸� �ѱ��
					
				} else //��Ÿ������Ȳ
					logger.info("	[[ Member.C ]] ����~~����~~����~~����~~����~~����~~");
				
				setPageCarrier(isRedirect, path, demoList, status);//�ɸ�� ��������
				return rpc;//��ȯ
			}
			//�ߺ��˻翡 ���� ��û�̶��
			//URL���� : /Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo" ]]
			else if("check_ID_Overlap.demo".equals(chop)) {
				logger.info("	[[ Member.C ]] id overlap check :: ���̵� �ߺ��˻� ��ȸ ����");
				String mem_id = req.getParameter("mem_id");//��û��ü�κ��� �Ѿ�� ID ����
				logger.info("	[[ Member.C ]] req.getParameter(\"mem_id\") = "+mem_id);
				
				//��ȣ��� �����ϱ� : Base64
				sb = new Secure_Base64();//��ȣ��� ����
				String sec_mem_id = sb.changeBase64(mem_id);
				logger.info("	[[ Member.C ]] sec_mem_id = "+sec_mem_id);
				
				demoList = new DemoMemberLogic().memberLogin(pMap);	//Logic������ pMap���� �������� ����� ����
				
				//�ɸ�� ��������
				isRedirect = true;
				rpc.setRedirect(isRedirect);//�ߺ������ �����̷�Ʈ
				rpc.setPath(path);	//��ǥ�������ּ�
				rpc.setDatas(null);	//�ߺ��˻翡 ���Ͽ� �ѱ� �ڷ�� ����
				logger.info("	[[ Member.C ]] rpc(Return Page Container)���ÿϷ� ");
				
			}//----end of if(�ߺ�üũ)
		}//--------end of if(��ȸ����)
		
		//[�Է�]������ ���Ͽ�
		//URL���� : /Basic_Web/5_Framework/pojo/demo/member/insert/... ]]
		else if("insert".equals(crud)) {
			logger.info("	[[ Member.C ]] insert :: �Է¾��� ����");
			
			//ȸ�����Կ� ���� ��û�̶��
			//URL���� : /Basic_Web/5_Framework/pojo/demo/member/insert/signup.demo ]]
			if("signup.demo".equals(chop)) {
				//�Ѿ�� �����͸� ���ε���
				HashMapBinder hmb = new HashMapBinder(req);
				hmb.bind(pMap);
				//������ Map�����͸� Logic�������� �����Ͽ� ����� ��ȯ����
				//result = mlog.memberSignup(pMap);
				logger.info("	[[ Member.C ]] �����ȯ ����! result = "+result);
				
				if(result==1) {//�Է¼����� ���
					logger.info("	[[ Member.C ]] �Է¼���(1)�� ���� ������ ��ȯ���� ����");
					//����
					isRedirect = true;			//sendRedirect
					path = "./pojo_mem_signup_success.jsp";
				} else {//�Է½����� ���
					logger.info("	[[ Member.C ]] �Է½���(0)�� ���� ������ ��ȯ���� ����");
					isRedirect = false;			//forward
					path = "./pojo_mem_signup_fail.jsp";
				}
				//�ɸ�� ��������
				rpc.setRedirect(isRedirect);
				rpc.setPath(path);		//��ǥ�������ּ�
				rpc.setDatas(null);	//ȸ������ ������������ ä������ �ƴҶ��� �� ���� 
				return rpc;
				
			}//----end of if(�ߺ�üũ)
		}//---end of else if(�Է¾���)
		
		//[����]������ ���Ͽ�
		else if("update".equals(crud)) {
			
		}//---end of else if(��������)
		
		//[����]������ ���Ͽ�
		else if("delete".equals(crud)) {
			
		}//---end of else if(��������)
		
		logger.info("	[[ Member.C ]] return rpc = "+rpc);
		return rpc;//FrontController���� ��ȯ
	}
	
	//ReturnPageCarrier Setting
	public DemoPageCarrier setPageCarrier(boolean isRedirect,String path
					  ,List<Map<String,Object>> demoList, int status) {
		rpc.setRedirect(isRedirect);//��������ȯ���
		rpc.setPath(path);			//��ǥ������ �ּ�
		rpc.setDatas(demoList);		//���������޿� �ڷ�
		rpc.setStatus(status);		//���������޿� ����
		logger.info("	[[ Member.C ]] rpc(Return Page Container)���ÿϷ� ");
		return rpc;
	}

}
