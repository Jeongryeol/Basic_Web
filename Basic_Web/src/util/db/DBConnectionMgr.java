package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionMgr {

	public static DBConnectionMgr dbMgr = null;
	
	public final String _DRIVER	= "oracle.jdbc.driver.OracleDriver";
	public final String _URL	= "jdbc:oracle:thin:@localhost:1521:orcl11";
	public final String _USER 	= "scott";
	public final String _PW		= "tiger";
	
	Connection			con		= null;
	PreparedStatement	pstmt	= null;
	ResultSet			rs		= null;
	
	//DB연동시 필요한 DBConnectionMgr 인스턴스 얻기
	public static DBConnectionMgr getInstance() {
		if(dbMgr==null) {
			dbMgr = new DBConnectionMgr();
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
	/* 1. INSERT, UPDATE, DELETE 일때만 2개
	 * 2; SELECT 일때 3개 */
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
