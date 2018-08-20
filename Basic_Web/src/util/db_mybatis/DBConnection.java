package util.db_mybatis;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
//MapperConfig.xml에 등록된 DB에 접속하는 정보를 모두 담은 SQL세션을 생성해주는 공통코드
public class DBConnection {
		//로그
		Logger logger = Logger.getLogger(DBConnection.class);
		//MyBatis
		String resource = "util/db_mybatis/MapperConfig.xml";//커넥션에 필요한 정보를 담은 xml문서
		SqlSessionFactory sqlMapper = null;
		SqlSession		  sqlSes	= null;

		public SqlSession getConnection(){
			logger.info("getConnection 호출 성공");
			
			try {
				//mybatis-3.4.6.pdf 2페이지  하단 참조
				//InputStream 대신 Reader class사용 : 한글바이트 때문
				Reader reader = null;//2byte단위로 처리하는 reader클래스(한글-2byte)
				reader = Resources.getResourceAsReader(resource);
		        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		        sqlSes 	  = sqlMapper.openSession();//SqlSession 생성
		        reader.close();//자원반납
		        
			}catch (Exception e) {
				e.printStackTrace();
				logger.info("SqlSession 생성에러");
			}
			logger.info("getConnection 종료");
			return sqlSes;
		}
}
