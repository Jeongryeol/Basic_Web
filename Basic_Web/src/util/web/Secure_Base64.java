package util.web;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.log4j.Logger;

public class Secure_Base64 {

	Logger logger = Logger.getLogger(Secure_Base64.class);
	
	public String changeBase64(String strObj) {
		logger.info("	[[ 보안모듈 ]] changeBase64 실행 성공");
		logger.info("	[[ 보안모듈 ]] 변환대상 : "+strObj);
		byte[] strBytes = strObj.getBytes();				 //문자로부터 바이트배열 얻기
		Encoder strEncoder = Base64.getEncoder();		 //java가 제공하는 Base64API를 통해 변환용 객체 생성
		byte[] encodedBytes = strEncoder.encode(strBytes);//변환용객체를 통해 바이트배열을 인코딩한 바이트배열로 저장
		String base64result = new String(encodedBytes);	 //저장된 변환방식을 문자열로 다시 저장
		logger.info("	[[ 보안모듈 ]] 변환결과 : "+base64result);
		return base64result;
	}
	
}
