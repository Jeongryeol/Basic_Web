package util.db_mybatis.demo;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
//MapperConfig.xml�� ��ϵ� DB�� �����ϴ� ������ ��� ���� SQL������ �������ִ� �����ڵ�
public class DBConnection_pojoDemo {
		//�α�
		Logger logger = Logger.getLogger(DBConnection_pojoDemo.class);
		//MyBatis
		String resource = "util/db_mybatis/demo/MapperConfig_pojoDemo.xml";//Ŀ�ؼǿ� �ʿ��� ������ ���� xml����
		SqlSessionFactory sqlMapper = null;
		SqlSession		  sqlSes	= null;

		public SqlSession getConnection(){
			logger.info("getConnection ȣ�� ����");
			
			try {
				//mybatis-3.4.6.pdf 2������  �ϴ� ����
				//InputStream ��� Reader class��� : �ѱ۹���Ʈ ����
				Reader reader = null;//2byte������ ó���ϴ� readerŬ����(�ѱ�-2byte)
				reader = Resources.getResourceAsReader(resource);
		        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		        sqlSes 	  = sqlMapper.openSession();//SqlSession ����
		        reader.close();//�ڿ��ݳ�
		        
			}catch (Exception e) {
				e.printStackTrace();
				logger.info("SqlSession ��������");
			}
			logger.info("getConnection ����");
			return sqlSes;
		}
}