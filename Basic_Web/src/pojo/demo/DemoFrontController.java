package pojo.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.demo.DemoFrontAssisstant;
import pojo.demo.member.DemoMemberController;

/***************************************************************************************
 * [[ Servlet :: One for All ]] - POJO Framework�� ���� demo������Ʈ�� ������ �бⰴü
 * @�ۼ���	������
 * @�ۼ���	2018.08.10	
 * @�����ǵ�
 * <u>	��û�޴� �Ϳ� ���� ����</u><br>
 * 		���� ȭ��(View)���� Ŭ���̾�Ʈ(Client:�����|���������)�� ��û�� ��� ������ ���Ͽ� [�ϳ��� ������ ����]�ϰ�,
 * 		�������� �����۾����� �б⵵�� �����Ͽ� [���� Controller ����]���� [����]������ �����Ѵ�.<br><br>
 * 		
 * 		"head"�� ���� �и��� [���� Controller ����]�� [Logic ���� ����]�� "crud"�� �����Ѵ�.<br>
 * 		"crud"�� ���� �и��� [���� Logic ���� ����]����  [Dao ����]���� "chop"�� �����Ѵ�.<br>
 * 		"chop"�� ���� �и��� [���� Dao ���� ����]���� [���� Mapper]�� �´� DB������ �����Ѵ�.<br>
 * 		������ ���� ���� ����� �������� �����Ѵ�.<br><br>
 * 
 * <u>	�����ϴ� �Ϳ� ���� ����</u><br>
 * 		���޵Ǿ�� ������� "������ �������� ���"�� "������", "��ȸ�����" [PageCarrier]�� ��ܼ� ���´�.
 * 		��� ���� ���� ����� �б��ϴ� ���Ҹ��� �����Ѵ�.(��Ű�� ���ǵ� �̶� ������ ���������� ���޵ȴ�)
 ***************************************************************************************/

public class DemoFrontController extends HttpServlet {
	Logger logger = Logger.getLogger(DemoFrontController.class);//�α��غ�
	private static final long serialVersionUID = 2314188011099L;
	/*******************************************************************************************************
	 * [[ �����б� ]] - ��û��ü�� �м��Ͽ� ������ �����ϵ��� �����ϰ� ����� ������
	 * @param req : ȭ�鿡�� ��û�� ��û��ü																				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 			URL / ParameterData / Cookie&Session
	 * @param res : ȭ������ ������ ���䰴ü (���� �� ����)
	 *******************************************************************************************************/
	public void doFork(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("	[[ Front ]] doFork() ȣ�⼺��");
		//��û��� ����
		DemoFrontAssisstant fas = new DemoFrontAssisstant();			   //����   	/(������Ʈ�̸�)/(������Ʈ ���� ���)/���θ� /Head / curd / chop
		String pattern = fas.getPattern(req);//��û�� URL���� ���� ����		| ex. /Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo
		String head = fas.getHead(pattern);	 //���Ͽ��� head(��������)����	| ex. member
		String crud = fas.getCRUD(pattern);	 //���Ͽ��� crud(��������)����	| ex. select
		String chop = fas.getChop(pattern);	 //���Ͽ��� chop(���ξ���)����	| ex. check_ID_Overlap.demo
		
		//�б������ �ʿ��� ��ü �غ�
		Map<String,Object> pMap = new HashMap<>();//�������޿� �ʿ��� �ʰ�ü
		DemoPageCarrier rpc = null;//�����ȯ�� ��ü
		
		//[head]��� �б�
		//ȸ��(member)���þ��� :: /Basic_Web/5_Framework/pojo/demo/member/...
		if("member".equals(head)) {
			pMap.put("crud", crud);//�������� ���ù� ����
			pMap.put("chop", chop);//���ξ��� ���ù� ����
			
			try {
				//ȸ�������� ��Ʈ�ѷ��� ���� �� ����� ��ȯ����
				rpc = new DemoMemberController().CRUD(req, res, pMap); 
				doPage(req,res,rpc);//ȭ��б�
				
			} catch (Exception e) {
				logger.info("	[[ F.member-���� ]] "+e.toString());
				e.printStackTrace();
			}
		}
		//����(shopping)���� ��������
		else if("shop".equals(head)) {
			//head���� �б� ���ÿ�
		}
		//����(fund)���� ��������
		else if("fund".equals(head)) {
			//head���� �б� ���ÿ�
		}
		//�������� ���� ������
		else {
			logger.info("	[[ Front ]] ����������(�������� ���� ����)����������");
			doPage(req,res,null);
		}
	}
	/*******************************************************************************************************
	 * [[ ȭ��б� ]] - ���䰴ü�� �����Ͽ� ȭ���� �����
	 * @param req : ȭ���� forward������� ������ ����� ��û��ü
	 * @param res : ȭ������ ������ ���䰴ü (���� �� ���� )
	 * @param wp : ���䰴ü�� ������ ��ó�������ü																 				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 		path																						 				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 		isRedirect ( true:sendRedirect | false:forward )																 				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 		datas ( ȭ�鿡 �����ؾ��ϴ� �ڷ����� )
	 *******************************************************************************************************/
	public void doPage(HttpServletRequest req,HttpServletResponse res, DemoPageCarrier rpc) throws ServletException, IOException {
		logger.info("	[[ Front ]] doPage() ȣ�⼺��");
		//�������� ���ٿ� ���� ������ ó��
		if(rpc==null) {
			RequestDispatcher view = req.getRequestDispatcher("/Basic_Web/5_Framework/pojo/pojo_wrongPage.jsp");
			view.forward(req, res);
			logger.info("	[[ Front ]] ����������(������������ ó��)����������");
			return;
		}
		//������� ���޵Ǿ� ���� ��� ȭ��ó��
		else {
			logger.info("	[[ Front ]] rpc���� �����մϴ�. ������ ���޹�� ó���մϴ�.");
			//true : sendRedirect ���
			if(rpc.isRedirect()) {
				logger.info("	[[ Front ]] true :: sendRedirect ����");
				logger.info("	[[ Front ]] rpc.getpath() = "+rpc.getPath());
				//��ȸ����� �Ѹ� �ʿ䰡 ���� ���, �̵��� jsp�������� sendRedirect�� �̵� ( ���������� ��������?? )
				res.sendRedirect(rpc.getPath());
			}
			//false : forward ���
			else {
				logger.info("	[[ Front ]] true :: forward ����");
				logger.info("	[[ Front ]] rpc.getpath() = "+rpc.getPath());
				//��ȸ����� �ѷ����ϴ� ���. ����� ������ jsp�������� forward�Ͽ� ������ΰ� ������� �ʵ��� �� ��.
				RequestDispatcher view = req.getRequestDispatcher(rpc.getPath());
				view.forward(req, res);
			}
			logger.info("E N D	[ ��---- ] ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("START	[ --��-- ] ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
		doFork(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("START	[ --��-- ] ����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
		doFork(req,res);
	}
	
}
