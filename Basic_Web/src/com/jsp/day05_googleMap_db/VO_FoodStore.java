package com.jsp.day05_googleMap_db;
/***************************************************
 * DB Table Script정보로부터 생성된 컬럼정보를 기반으로 VO클래스 생성
 * 일괄편집 :	Alt + shift + A
 * 소문자 변경 :	Alt + shift + Y
 * Generate Getter and Setter Method
 ***************************************************/
public class VO_FoodStore {
	
	private int	 	fs_code      = 0; 	  //NUMBER(4)                     NOT NULL,
	private String	fs_name      = "";  //VARCHAR2(50 BYTE)               NOT NULL,
	private String	fs_latitude  = "";  //VARCHAR2(50 BYTE)               NOT NULL,
	private String	fs_longitude = "";  //VARCHAR2(50 BYTE)               NOT NULL,
	private String	fs_size      = "";  //VARCHAR2(50 BYTE),
	private String	fs_opentime  = "";  //VARCHAR2(50 BYTE),
	private String	fs_closetime = "";  //VARCHAR2(50 BYTE),
	private int 	fs_good      = 0;	  //NUMBER(10),
	private int 	fs_bad     	 = 0;	  //NUMBER(10),
	private String	fs_picture   = "";  //VARCHAR2(100 BYTE),
	private String	fs_juso      = "";  //VARCHAR2(200 BYTE)
	
	public int getFs_code() {
		return fs_code;
	}
	public void setFs_code(int fs_code) {
		this.fs_code = fs_code;
	}
	public String getFs_name() {
		return fs_name;
	}
	public void setFs_name(String fs_name) {
		this.fs_name = fs_name;
	}
	public String getFs_latitude() {
		return fs_latitude;
	}
	public void setFs_latitude(String fs_latitude) {
		this.fs_latitude = fs_latitude;
	}
	public String getFs_longitude() {
		return fs_longitude;
	}
	public void setFs_longitude(String fs_longitude) {
		this.fs_longitude = fs_longitude;
	}
	public String getFs_size() {
		return fs_size;
	}
	public void setFs_size(String fs_size) {
		this.fs_size = fs_size;
	}
	public String getFs_opentime() {
		return fs_opentime;
	}
	public void setFs_opentime(String fs_opentime) {
		this.fs_opentime = fs_opentime;
	}
	public String getFs_closetime() {
		return fs_closetime;
	}
	public void setFs_closetime(String fs_closetime) {
		this.fs_closetime = fs_closetime;
	}
	public int getFs_good() {
		return fs_good;
	}
	public void setFs_good(int fs_good) {
		this.fs_good = fs_good;
	}
	public int getFs_bad() {
		return fs_bad;
	}
	public void setFs_bad(int fs_bad) {
		this.fs_bad = fs_bad;
	}
	public String getFs_picture() {
		return fs_picture;
	}
	public void setFs_picture(String fs_picture) {
		this.fs_picture = fs_picture;
	}
	public String getFs_juso() {
		return fs_juso;
	}
	public void setFs_juso(String fs_juso) {
		this.fs_juso = fs_juso;
	}

}