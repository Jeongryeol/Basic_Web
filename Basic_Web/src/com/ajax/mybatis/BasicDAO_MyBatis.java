package com.ajax.mybatis;
/***************************************************************
 * @title	MyBatis�� Ȱ���Ͽ� CRUD ���� �� �����ϱ�
 * @date	2018.07.26
 * @author	Jeongryeol Lee
 * @email	duxbellorn@gmail.com
 * @comment
 * 	�ݵ�� �Ʒ� �� ����� �۾��� ��ģ �� �����մϴ�.
 * 	1)src.util.db_mybatis.maps �� �������� ������ xml���۹��� �����ϱ�
 * 		��) ���ϸ� : abcd1234.xml
 * 	2)rc.util.db_mybatis�� MapperConfig.xml�� �������� ����ϱ�
 * 		��) <mapper resource="com/mybatis/maps/abcd1234.xml"/>
 * 
 * 	�� �۾��������� ������ �߻��ϸ� �� �⵿�� ������ ���ܼ� 404�� ������ ��Ÿ���� ���ǹٶ��ϴ�.
 ***************************************************************/
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import util.db_mybatis.DBConnection;

public class BasicDAO_MyBatis {
	/*�����*/
	//Consoleâ�� �α����
	Logger logger = Logger.getLogger(BasicDAO_MyBatis.class);
	//DB�����ϱ�
	DBConnection dbcon	= null; //DB���� ����Ŭ����
	SqlSession		  sqlSes	= null;	//���ῡ ���� ��� ������ ��� ��ü
	/*********************************************************
	 * [[ MapperConfig.xml �� ���� ]]
	 * 	ORM�ַ�� �� �ϳ��� MyBatis�� ���ؼ�  ���������� ������ ����ŬDB�� ������ �ΰ�
	 * 	�ڷḦ �ְ�޴� ������ �ʿ��� ���� ���� ��ĵ �� ���� ��ü ������ �ϰ������� �ϱ� ����,
	 *  ���ῡ �ʿ��� �������� �����Ѵ�
	 *
	 * 	1) ����ŬDB������ ����̹�Ŭ���� ����
	 *  2) DB������ IP�ּҿ� ��Ʈ��ȣ ����
	 *  3) �ش� DB�� �����̸��� ��й�ȣ ����
	 *********************************************************/
	
	/*�޼ҵ�*/
	/*******************************************************************
	 * [[ SELECT :: ��ȸ���� �⺻�� ]]
	 * 
	 * @param pMap : Front(Web|App)���� �Է��� ������
	 * 	�� �Ķ���� �����ʹ� �Ʒ� ����� jsp���Ϸκ��� �Ѿ��
	 * 	WebContent/4_MyBatis/basic_dbConnection/Basic_Mybatis_SELECT.jsp
	 * 
	 * @return rlist : Back(DB)���� ��ȸ�� ������
	 * 	ListŸ�Կ� �� �ο� ������ Map<�˶��÷���,�ο찪>���� ����)
	 * 	���ϵ� �����ʹ� �Ʒ� ����� jsp���Ͽ��� ����
	 * 	WebContent/4_MyBatis/basic_dbConnection/Basic_Mybatis_SELECT.jsp
	 ********************************************************************/
	public List<Map<String,Object>> getDataList(int param_num){
		logger.info("getDataList ȣ�� ����");
		dbcon  = new DBConnection();//DB���� ����Ŭ���� �ν��Ͻ�����
		sqlSes = dbcon.getConnection();		//���ᰴü ����
		
		//DB���� ��ȸ�� ����� ��ȯ�ϱ� ���� List�ڷ���
		List<Map<String,Object>> rlist = null;
		
		try {
			/*************************************************************
			 * @param1 "testMapSelect"
			 * 	: src.util.db_mybatis.maps.BasicQuery.xml �� �޸� ����
			 * @param2 pMap
			 * 	: Front(Web|App)���� �Է��� ��ȸ���� �����͸� �Ķ���ͷ� �Ѱܹ��� MapŸ�� Ÿ��
			 *************************************************************/
			rlist = sqlSes.selectList("testSelect_id",param_num);
			
			//Ȯ�ο�α�
			logger.info("��ȸ��� : "+rlist.size()+"��");
			for(int i=0;i<rlist.size();i++) {
				logger.info(rlist.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getDataList �����߻�");
		}
		logger.info("getDataList ���� �� ��ȯ����");
		return rlist;//DB��ȸ�� �ڷ� ��ȯ�ϱ�
	}
}
