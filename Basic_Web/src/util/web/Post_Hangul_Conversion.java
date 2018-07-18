package util.web;

public class Post_Hangul_Conversion {

	//영문 인코딩 타입으로 읽어서 한글 인코딩 타입으로 전환
	public static String toKor(String en) {
		if(en==null) return null;
		try {
			return new String(en.getBytes("8859_1"),"EUC-KR");
		} catch (Exception e) {
			return en;
		} 
	}
	
	//영문 인코딩 타입으로 읽어서 한글 인코딩 타입으로 전환
	public static String toUTF(String en) {
		if(en==null) return null;
		try {
			return new String(en.getBytes("8859_1"),"UTF-8");
		} catch (Exception e) {
			return en;
		} 
	}
}
