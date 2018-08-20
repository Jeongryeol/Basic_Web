package com.ajax.mybatis;
/***************************************************************
 * @title	MyBatis를 활용하여 CRUD 수행 및 리턴하기
 * @date	2018.07.26
 * @author	Jeongryeol Lee
 * @email	duxbellorn@gmail.com
 * @comment
 * 	반드시 아래 두 경로의 작업을 마친 후 실행합니다.
 * 	1)src.util.db_mybatis.maps 에 쿼리문을 저장한 xml매퍼문서 저장하기
 * 		예) 파일명 : abcd1234.xml
 * 	2)rc.util.db_mybatis에 MapperConfig.xml에 문서매퍼 등록하기
 * 		예) <mapper resource="com/mybatis/maps/abcd1234.xml"/>
 * 
 * 	이 작업과정에서 오류가 발생하면 웹 기동에 문제가 생겨서 404번 에러가 나타나니 주의바랍니다.
 ***************************************************************/
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import util.db_mybatis.DBConnection;

public class BasicDAO_MyBatis {
	/*선언부*/
	//Console창에 로그찍기
	Logger logger = Logger.getLogger(BasicDAO_MyBatis.class);
	//DB연결하기
	DBConnection dbcon	= null; //DB연결 공통클래스
	SqlSession		  sqlSes	= null;	//연결에 대한 모든 정보가 담긴 객체
	/*********************************************************
	 * [[ MapperConfig.xml 의 역할 ]]
	 * 	ORM솔루션 중 하나인 MyBatis를 통해서  물리적으로 떨어진 오라클DB와 연결을 맺고
	 * 	자료를 주고받는 과정에 필요한 공통 정보 스캔 및 공통 객체 관리를 일괄적으로 하기 위해,
	 *  연결에 필요한 정보들을 저장한다
	 *
	 * 	1) 오라클DB서버의 드라이버클래스 정보
	 *  2) DB서버의 IP주소와 포트번호 정보
	 *  3) 해당 DB의 계정이름과 비밀번호 정도
	 *********************************************************/
	
	/*메소드*/
	/*******************************************************************
	 * [[ SELECT :: 조회업무 기본꼴 ]]
	 * 
	 * @param pMap : Front(Web|App)에서 입력한 데이터
	 * 	위 파라미터 데이터는 아래 경로의 jsp파일로부터 넘어옴
	 * 	WebContent/4_MyBatis/basic_dbConnection/Basic_Mybatis_SELECT.jsp
	 * 
	 * @return rlist : Back(DB)에서 조회한 데이터
	 * 	List타입에 각 로우 정보가 Map<알라어스컬럼명,로우값>으로 저장)
	 * 	리턴된 데이터는 아래 경로의 jsp파일에서 사용됨
	 * 	WebContent/4_MyBatis/basic_dbConnection/Basic_Mybatis_SELECT.jsp
	 ********************************************************************/
	public List<Map<String,Object>> getDataList(int param_num){
		logger.info("getDataList 호출 성공");
		dbcon  = new DBConnection();//DB연결 공통클래스 인스턴스변수
		sqlSes = dbcon.getConnection();		//연결객체 갱성
		
		//DB에서 조회한 결과를 반환하기 위한 List자료형
		List<Map<String,Object>> rlist = null;
		
		try {
			/*************************************************************
			 * @param1 "testMapSelect"
			 * 	: src.util.db_mybatis.maps.BasicQuery.xml 내 메모 참고
			 * @param2 pMap
			 * 	: Front(Web|App)에서 입력한 조회관련 데이터를 파라미터로 넘겨받은 Map타입 타료
			 *************************************************************/
			rlist = sqlSes.selectList("testSelect_id",param_num);
			
			//확인용로그
			logger.info("조회결과 : "+rlist.size()+"건");
			for(int i=0;i<rlist.size();i++) {
				logger.info(rlist.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("getDataList 에러발생");
		}
		logger.info("getDataList 종료 및 반환성공");
		return rlist;//DB조회한 자료 반환하기
	}
}
