package util.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/*********************************************************************
 * @author	박의
 * @since	2018.08.08
 * @comment	작성하고자하는 테이블명을 입력하면 해당하는 컬럼대로 VO클래스를 생성해주는 유틸리티 클래스
 *********************************************************************/
public class AutoCreate_VO_class {
	
	private DBConnectionMgr dbcon = DBConnectionMgr.getInstance();
	private Connection con;//연결객체
	private PreparedStatement pstmt;//동적쿼리객체
	private ResultSet rs;//조회커서조작객체
	private String tableName;//희망테이블명
	private File file;//파일생성수행객체
	private PrintWriter pw;//출력객체
	
	//[1] 실행 :: 테이블명 입력
	public static void main(String[] args) {
	/*	//값을 입력받아서 테이블명을 설정하는 코드 : 다수의 코드를 만들어야하더라도 개별로 생성하기	
	  	Scanner sc = new Scanner(System.in);
		System.out.println("만들고자하는 테이블 명을 입력하시오");
		String userInput = sc.next();
		System.out.println(userInput+"으로 테이블 생성을 시작합니다.");*/
		
		String userInput = "emp";//만들고자하는 테이블 명 기록
		new AutoCreate_VO_class(userInput);
	}
	
	//[2] 전역변수 초기화 + 지정메소드 호출
	public AutoCreate_VO_class(String tableName) {
		this.tableName = tableName;
		createVO();
	}

	//[3] 지정메소드 :: VO생성시 파일명과 경로를 지정하는 메소드
	public void createVO() {
		try {
			//파일명
			String fileName = tableName.substring(0, 1).toUpperCase()
							+ tableName.substring(1).toLowerCase();
			//파일경로
			file = new File("src/com/vo/", fileName + "VO.java");
			//파일생성
			createFile(file); //만약에 파일이름이 겹치는게 있다면 덮어씌움
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//[4] 파일생성메소드 :: 파일을 생성할때 어떻게 글이 작성이 되어야 하는가에 대해 작성
	public void createFile(File file) { 
		try {
			//쓰기객체 생성
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			//생성될 자바클래스 이름 지정 : 첫글자는 대문자, 두번째글자부터는 소문자로 변환
			String className = tableName.substring(0, 1).toUpperCase()
							 + tableName.substring(1).toLowerCase(); 
			
			List<Map<String, String>> tableInfos = getTableInfo();//테이블정보를 얻어오는 메소드 호출
			
			//객체실행
			pw.println("package com.vo;");
			pw.println();// 줄바꿈
			pw.println("public class " + className + "VO {");
			pw.println();
			//전역변수 생성
			for (Map<String, String> tableInfo : tableInfos) {
				String type		= tableInfo.get("colType");				 //컬럼타입에 따른 변환된 정보를 저장
				String colName	= tableInfo.get("colName").toLowerCase();//컬럼명은 소문자
				pw.println("	private "+ type + " " + colName +";");	 //컬럼명 전역변수 선언문 기록
			}
			//getter(),setter() 메소드 생성
			for (Map<String, String> tableInfo : tableInfos) {
				pw.println();//줄바꿈
				String colName	  = tableInfo.get("colName");			//컬럼명
				String methodName = colName.substring(0, 1).toUpperCase()
								  + colName.substring(1).toLowerCase();	//메소드명 (첫글자만 대문자)
				String type		  = tableInfo.get("colType");			//컬럼타입
				String varName	  = colName.toLowerCase();				//컬럼이름(전역변수)
				//getter
				pw.println("	public " + type + " get" + methodName + "() {");
				pw.println("		return " + varName + ";");
				pw.println("	}");
				pw.println();//줄바꿈
				//setter
				pw.println("	public void set" + methodName + "(" + type + " " + varName + ")" + "{");
				pw.println("		this." + varName + " = " + varName + ";");
				pw.println("	}");
			}
			pw.println();//줄바꿈
			pw.println("}");//닫기
			pw.flush();//쓰기실행
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();//닫기
		}
	}
	
	//테이블정보를 반환하는 메소드 : DB상의 컬럼정보와 데이터타입의 정보를 가져와서 적합하게 식별하고 담아두기.
	public List<Map<String, String>> getTableInfo() {
		//반환타입설정
		List<Map<String, String>> tableInfos = new ArrayList<>();
		//테이블정보를 조회활 SQL문 작성
		StringBuilder sql = new StringBuilder();
		tableName = tableName.toUpperCase();
		sql.append(//지정한 테이블의 컬럼에 대하여, 컬럼명과 컬럼타입을 읽어오는 쿼리문
				"SELECT CNAME, DECODE(COLTYPE,'NUMBER',DECODE(scale,0,'int','double')"
											+",'VARCHAR2','String'"
										   	+",'VARCHAR','String'"
										   	+",'DATE','String') type "
				+" FROM COL"
				+" WHERE TNAME = '" + tableName + "'");
		//DB연결
		try {
			con = dbcon.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, String> tableInfo = new HashMap<>();	//맵 생성
				tableInfo.put("colName", rs.getString("CNAME"));	//컬럼명 담기
				tableInfo.put("colType", rs.getString("type"));		//컬럼타입 담기
				tableInfos.add(tableInfo);							//맵에 담기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tableInfos;//컬럼정보가 담긴 리스트 반환 (K:V=컬럼명:컬럼타입)
	}

}
