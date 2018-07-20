package com.jsp.day05_googleMap_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/* 
 * 맛집정보를 저장한 DataBase에 접속하여
 * 원하는 맛집정보를 조회해오는 Data Access Object class
 * 
 * 조회된 정보가 Google Map API의 marker에 표시할 정보로 사용된다.
 */
public class DAO_Food {
	
	/*선언부*/
	//static을 붙였기 때문에 바로 인스턴스화 됨
		//Calendar cal = Calendar.getInstance(); 이런식으로 인스턴스화가 가능하다!!
	parkDBConnectionMgr	dbMgr = parkDBConnectionMgr.getInstance();
	Connection			con	  = null;
	PreparedStatement	pstmt = null;
	ResultSet			rs 	  = null;//커서를 컨트롤하자 ::전체범위처리/부분범위처리/속도에 관한 이해
		//web.xml 확인/
	Logger logger = Logger.getLogger(DAO_Food.class);
	
	/*조회처리*/
	//맛집정보 조회처리
	public List<VO_FS_Menu> getFoodInfoList(VO_FS_Menu fsmvo) {
		/**********************************************
		 * 결과를 담아 메소드의 반환값으로 낼 리스트 선언
		 * EmpVO 클래스는 한번에 1개의 로우밖에 못담기 때문에,
		 * EmpVO 클래스를 여러 개 담아주는 ArrayList가 필요해진다.
		 * 이 때, 다형성을 적용하여 코드의 재사용성을 높여준다.
		 **********************************************/
		List<VO_FS_Menu> foodInfoList = null;
		foodInfoList = new ArrayList<VO_FS_Menu>();
		
		/**********************************************
		 * SQL문을 작성할때는
		 * 		StringBuilder(비동기화/단독/고속)나
		 * 		StringBuffer(동기화/경합/저속)를 사용한다.
		 * SQL문은 반드시 Toad에서 테스트를 마친 후에 사용한다.
		 **********************************************/
		StringBuilder sql = new StringBuilder();
	//	sql.append("");
		sql.append("SELECT fs.fs_code, fs.fs_name, fs.fs_latitude, fs.fs_longitude");
		sql.append("      ,fs.fs_size, fs.fs_opentime, fs.fs_closetime");
		sql.append("      ,fs.fs_good, fs.fs_bad, fs.fs_picture, fs.fs_juso");
		sql.append("      ,fsm.fsm_name, fsm.fsm_price");
		sql.append("  FROM FOOD_STORE fs, FS_menu fsm");
		sql.append(" WHERE fs.fsm_code = fsm.fsm_code");

		/* ********************************************************************
		  	자식관계에 있는 테이블의 정보를 부모테이블VO클래스에서 다루게 하고자 함
		  		부모테이블의 VO클래스로부터
		  		자식테이블의 VO클래스로 접근 및 저장,읽기 가능
		  
	  		[현재 테이블설계는 메뉴테이블이 부모(PK보유)이고 스토어테이블이 자녀(FK수용)]
	  
		  		ALTER TABLE KING.FS_MENU ADD (
				  CONSTRAINT XPKFS_MENU
				  PRIMARY KEY
				  (FSM_CODE)
				  USING INDEX KING.XPKFS_MENU
				  ENABLE VALIDATE);
				
				ALTER TABLE KING.FS_MENU ADD (
				  CONSTRAINT FOOD_STORE_FK 
				  FOREIGN KEY (FS_CODE) 
				  REFERENCES KING.FOOD_STORE (FS_CODE)
				  ENABLE VALIDATE);
		  
		   	[해쉬맵일 경우 키와 값으로 쉽게 해결할 수 있음]
		 **********************************************************************/
		//DB연동은 오라클 서버에 접속하는 것이므로 예외처리 필수임.
		try {
			con = dbMgr.getConnection();
				//연결정보로부터 전령을 준비할때 SQL문을 담음
			System.out.println("[[요청한 동적쿼리문]] "+sql.toString());
			pstmt = con.prepareStatement(sql.toString());
				//담은 정보를 돌리고 결과에 대한 커서 가져옴
			rs = pstmt.executeQuery();

			//테스트용 로그
		//	logger.info(sql);
		//	logger.info("rs.next() : "+rs.next());
		//	logger.info("rs.isFirst() : "+rs.isFirst());
			
			VO_FS_Menu menuVO = null;//VO클래스
			VO_FoodStore storeVO = null;//VO클래스
			
		//	System.out.println(rs.isFirst());//커서가 제일 앞에 있니?
		//	System.out.println(rs.isLast()); //커서가 제일 뒤에 있니?
		//	while(rs.previous()) {	//커서가 제일 뒤에 있다면 뒤로 돌아가게 반복문 가동
		//	while(rs.absolute(3) {	//커서를 특정위치에 옮겨가게 해준다. 
			while(rs.next()) {//커서가 제일 앞이라면 다음으로 달려가게 반복문 가동
			/*조회한 row 하나를 담을 새로운 VO 인스턴스로 생성하기*/
				//자식 : 스토어테이블 VO
				storeVO = new VO_FoodStore();
				//조회한 결과의 각 컬럼에 해당하는 로우값을 VO클래스에 저장(set)하기
				storeVO.setFs_code(rs.getInt("fs_code"));
				storeVO.setFs_name(rs.getString("fs_name"));
				storeVO.setFs_latitude(rs.getString("fs_latitude"));
				storeVO.setFs_longitude(rs.getString("fs_longitude"));
				storeVO.setFs_size(rs.getString("fs_size"));
				storeVO.setFs_opentime(rs.getString("fs_opentime"));
				storeVO.setFs_closetime(rs.getString("fs_closetime"));
				storeVO.setFs_good(rs.getInt("fs_good"));
				storeVO.setFs_bad(rs.getInt("fs_bad"));
				storeVO.setFs_picture(rs.getString("fs_picture"));
				storeVO.setFs_juso(rs.getString("fs_juso"));
				
				//부모 : 메뉴테이블 VO
				menuVO = new VO_FS_Menu();
				//부모테이블VO에 자식테이블VO담기
				menuVO.setFsvo(storeVO);
				//조회한 결과의 각 컬럼에 해당하는 로우값을 VO클래스에 저장(set)하기
				menuVO.setFsm_name(rs.getString("fsm_name"));
				menuVO.setFsm_price(rs.getString("fsm_price"));
				
				//저장된 EmpVO클래스를 List에 담기
				foodInfoList.add(menuVO);
				
			}//커서에 대한 조건이므로 커서의 다음내용이 없을때까지 반복수행함
			
		} catch (SQLException se) {//SQL에러
			System.out.println("SQL요청 중 에러발생!");
			System.out.println("[[query]] "+sql.toString());
			se.printStackTrace();
			/* *********************************************************
			 * SQLException 에러가 발생했다면 다음 중 해당되는 내용이 있는지 체크하세요.
			 * 	1. SQL문이 에러가 났는가? - Toad에서 에러체크
			 * 	2. 조회된 컬럼의 타입과 받아서 넣는 해쉬맵의 타입이 일치하는가?
			 * 		( ex. int-date,String-varchar2 )
			 ***********************************************************/
			
		} catch (Exception e) {//기타에러
			e.printStackTrace();
		}
		
		return foodInfoList;
	}
}
