package util.web;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.log4j.Logger;

public class Secure_Base64 {

	Logger logger = Logger.getLogger(Secure_Base64.class);
	
	public String changeBase64(String strObj) {
		logger.info("	[[ ���ȸ�� ]] changeBase64 ���� ����");
		logger.info("	[[ ���ȸ�� ]] ��ȯ��� : "+strObj);
		byte[] strBytes = strObj.getBytes();				 //���ڷκ��� ����Ʈ�迭 ���
		Encoder strEncoder = Base64.getEncoder();		 //java�� �����ϴ� Base64API�� ���� ��ȯ�� ��ü ����
		byte[] encodedBytes = strEncoder.encode(strBytes);//��ȯ�밴ü�� ���� ����Ʈ�迭�� ���ڵ��� ����Ʈ�迭�� ����
		String base64result = new String(encodedBytes);	 //����� ��ȯ����� ���ڿ��� �ٽ� ����
		logger.info("	[[ ���ȸ�� ]] ��ȯ��� : "+base64result);
		return base64result;
	}
	
}
