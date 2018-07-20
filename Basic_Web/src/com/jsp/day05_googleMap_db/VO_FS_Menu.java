package com.jsp.day05_googleMap_db;
/***************************************************
 * DB Table Script정보로부터 생성된 컬럼정보를 기반으로 VO클래스 생성
 * 일괄편집 :	Alt + shift + A
 * 소문자 변경 :	Alt + shift + Y
 * Generate Getter and Setter Method
 * 
 * [equal-join + PK/FK에 대한 정보는 아래 주석 참고
 ***************************************************/
public class VO_FS_Menu {

	private int		fsm_code    = 0; //NUMBER(4)             NOT NULL,
	private String	fsm_name    = ""; //VARCHAR2(50 BYTE),
	private String	fsm_price   = ""; //VARCHAR2(50 BYTE),
	private String	fsm_picture = ""; //VARCHAR2(100 BYTE),
	private int		fsm_good    = 0; //NUMBER(10),
	private int		fsm_bad     = 0; //NUMBER(10),
	private int		fs_code     = 0; //NUMBER(4)
	
	public VO_FoodStore fsvo = null;
	/*
	 * 자식관계에 있는 테이블의 정보를 부모테이블VO클래스에서 다루게 하고자 함
	 * 		부모테이블의 VO클래스로부터
	 * 		자식테이블의 VO클래스로 접근 및 저장,읽기 가능
	 * 
	  	[현재 테이블설계는 메뉴테이블이 부모(PK보유)이고 스토어테이블이 자녀(FK수용)임]
	  
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
	  
	 *  해쉬맵일 경우 키와 값으로 쉽게 해결할 수 있음
	 */
	
	public int getFsm_code() {
		return fsm_code;
	}
	public void setFsm_code(int fsm_code) {
		this.fsm_code = fsm_code;
	}
	public String getFsm_name() {
		return fsm_name;
	}
	public void setFsm_name(String fsm_name) {
		this.fsm_name = fsm_name;
	}
	public String getFsm_price() {
		return fsm_price;
	}
	public void setFsm_price(String fsm_price) {
		this.fsm_price = fsm_price;
	}
	public String getFsm_picture() {
		return fsm_picture;
	}
	public void setFsm_picture(String fsm_picture) {
		this.fsm_picture = fsm_picture;
	}
	public int getFsm_good() {
		return fsm_good;
	}
	public void setFsm_good(int fsm_good) {
		this.fsm_good = fsm_good;
	}
	public int getFsm_bad() {
		return fsm_bad;
	}
	public void setFsm_bad(int fsm_bad) {
		this.fsm_bad = fsm_bad;
	}
	public int getFs_code() {
		return fs_code;
	}
	public void setFs_code(int fs_code) {
		this.fs_code = fs_code;
	}
	public VO_FoodStore getFsvo() {
		return fsvo;
	}
	public void setFsvo(VO_FoodStore fsvo) {
		this.fsvo = fsvo;
	}
	
}
