package com.jsp.day05_googleMap_db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class parkDBConnectionMgr {

	public static parkDBConnectionMgr dbMgr = null;
	
	public final String _DRIVER	= "oracle.jdbc.driver.OracleDriver";
	public final String _URL	= "jdbc:oracle:thin:@192.168.0.221:1521:orcl11";
	public final String _USER 	= "king";
	public final String _PW		= "1111";
	
	Connection			con		= null;
	PreparedStatement	pstmt	= null;//SQL을 전달할때 사용하는 전령
	CallableStatement	cstmt	= null;//프로시저를 호출할 때 사용하는 전령
	ResultSet			rs		= null;
	
	//DB연동시 필요한 DBConnectionMgr 인스턴스 얻기
	public static parkDBConnectionMgr getInstance() {
		if(dbMgr==null) {
			dbMgr = new parkDBConnectionMgr();
		}
		return dbMgr;
	}
	
	public Connection getConnection() {
		try {
			Class.forName(_DRIVER);
			con = DriverManager.getConnection(_URL, _USER, _PW);
		}catch (ClassNotFoundException cne) {
			System.out.println("드라이버클래스를 찾을 수 없습니다.");
		}catch (Exception e) {
			System.out.println("오라클 서버에서 커넥션 실패!!!!!!");
		}
		return con;
	}
	
	//사용한 자원 반납하기 (생성순서와 반대순서로 닫기)
	/* 1. 프로시저 호출에 사용될때는 2개
	 * 2. INSERT, UPDATE, DELETE 일때만 2개
	 * 3. SELECT 일때 3개 
	 */
	public void freeConnection(Connection con, CallableStatement cstmt) {
		try {
			if(cstmt!=null) cstmt.close();
			if(con!=null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!= null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
