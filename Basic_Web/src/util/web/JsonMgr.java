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
	 * [[ List를 JSON문자열로 변환하기 ]]
	 * @param	plist : List자료형
	 * @return	cvrtJSON : json문자열
	 **********************************************************************************/
	public String fromLIST_toJSON(List plist) {
		logger.info("	[[ JSON매니저 ]] 원본대상 | List<"+plist.get(0).getClass().getSimpleName()+">");
		String cvrtJSON = gson.toJson(plist);
		logger.info("	[[ JSON매니저 ]] 변환결과 | String(json문자열) : "+cvrtJSON);
		return cvrtJSON;
	}
	
	public Class[] fromJSON_toArray(String json) {
		logger.info("	[[ JSON매니저 ]] 변환대상 | String(json문자열) : "+json);
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
		svo.setMem_name("첫째");
		svo.setMem_phone("01022224444");
		spl.add(svo);
		
		svo = new SampleVO();
		svo.setMem_no(2);
		svo.setMem_id("asdf");
		svo.setMem_pw("5678");
		svo.setMem_name("둘째");
		svo.setMem_phone("01066668888");
		spl.add(svo);
		
		svo = new SampleVO();
		svo.setMem_no(3);
		svo.setMem_id("zxcv");
		svo.setMem_pw("9012");
		svo.setMem_name("셋째");
		svo.setMem_phone("01033335555");
		spl.add(svo);
		
		String a = jm.fromLIST_toJSON(spl);
		System.out.println(a);
		//자바스크립트 영역에서는 아래와 같은 형식으로 JSON문자열의 값을 컨트롤할 수 있다.
		//a[i].mem_id
		
		Class[] svo1 = jm.fromJSON_toArray(a);
		for(int i=0;i<svo1.length;i++) {
			System.out.println(svo1);
		}
	}
}
