package util.web;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/*
 * 공통코드를 작성해봅시다
 * 	1)파라미터가 필요하다.
 * 		-누가 언제 어떤 클래스에서 인스턴스화 해야하는 것인가?
 * 		-어떤 시점에서 필요한 것일까?
 *  
 */
public class HashMapBinder {
	//요청객체 준비
	HttpServletRequest req = null;
	
	//생성자를 통해서 이 클래스(HashMapBinder)를 인스턴스화할때,
	//요청객체(request)의 원본주소를 받아 저장해둠
	public HashMapBinder(HttpServletRequest req) {
		this.req = req;
	}
	
	//Web에서 보내용 요청객체의 자료를 DB에 보낼 Map계열 자료형으로 옮겨담음  
	public void bind(Map<String,Object> pMap){
		//자료형 초기화
		pMap.clear();
		/* Enumeration는 자바 초기버전에 개발된 인터페이스로
		 * 객체들의 집합(Vector)의 객체들을 하나씩 순서대로 담아 처리할 수 있는 메소드를 제공하는 켈렉션이다. */
		Enumeration<String> en
				= req.getParameterNames();//name속성으로 된 모든 내용을 순서대로 담음
		//자료형 일괄저장
		while(en.hasMoreElements()) {
				//에뉴멀레이션에 담긴 속성값(name)을 키(key)로 담음
			String key = en.nextElement();
				//키(key)와 키에 따라 담고있는 값(value)을 Map에 담음
			pMap.put(key,req.getParameter(key));
		}
	}
}
