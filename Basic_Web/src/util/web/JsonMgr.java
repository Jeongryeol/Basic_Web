package util.web;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import util.web.json.SampleVO;

public class JsonMgr {
	Logger logger = Logger.getLogger(JsonMgr.class);
	Gson gson = new Gson();
	
	/**********************************************************************************
	 * [[ List�� JSON���ڿ��� ��ȯ�ϱ� ]]
	 * @param	plist : List�ڷ���
	 * @return	cvrtJSON : json���ڿ�
	 **********************************************************************************/
	public String fromLIST_toJSON(List plist) {
		logger.info("	[[ JSON�Ŵ��� ]] ������� | List<"+plist.get(0).getClass().getSimpleName()+">");
		String cvrtJSON = gson.toJson(plist);
		logger.info("	[[ JSON�Ŵ��� ]] ��ȯ��� | String(json���ڿ�) : "+cvrtJSON);
		return cvrtJSON;
	}
	
	public Class[] fromJSON_toArray(String json) {
		logger.info("	[[ JSON�Ŵ��� ]] ��ȯ��� | String(json���ڿ�) : "+json);
		Class[] cvrtArray = gson.fromJson(json, Class[].class);
		return cvrtArray;
	}
	
	public static void main(String[] args) {
		JsonMgr jm = new JsonMgr();
		
		List<SampleVO> spl = new LinkedList<SampleVO>();
		
		SampleVO svo = new SampleVO();
		svo.setMem_no(1);
		svo.setMem_id("qwer");
		svo.setMem_pw("1234");
		svo.setMem_name("ù°");
		svo.setMem_phone("01022224444");
		spl.add(svo);
		
		svo = new SampleVO();
		svo.setMem_no(2);
		svo.setMem_id("asdf");
		svo.setMem_pw("5678");
		svo.setMem_name("��°");
		svo.setMem_phone("01066668888");
		spl.add(svo);
		
		svo = new SampleVO();
		svo.setMem_no(3);
		svo.setMem_id("zxcv");
		svo.setMem_pw("9012");
		svo.setMem_name("��°");
		svo.setMem_phone("01033335555");
		spl.add(svo);
		
		String a = jm.fromLIST_toJSON(spl);
		System.out.println(a);
		//�ڹٽ�ũ��Ʈ ���������� �Ʒ��� ���� �������� JSON���ڿ��� ���� ��Ʈ���� �� �ִ�.
		//a[i].mem_id
		
		Class[] svo1 = jm.fromJSON_toArray(a);
		for(int i=0;i<svo1.length;i++) {
			System.out.println(svo1);
		}
	}
}
