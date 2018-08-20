package util.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/*****************************************************************************************
 * [[[ Cookie Manager ]]]
 * @version
 * 		<br>1.5
 * 		<br>▷ bindCookies
 * 		<br>1.4
 * 		<br>▷ isNumber() 추가 : 실수 및 음수에 대한 문자열 확인+인코딩+디코딩 외 일부 수정
 * 		<br>1.3
 * 		<br>▷ showAllCookieList() 추가
 * 		<br>1.2
 * 		<br>▷ 쿠키만들기 오버로드 메소드 추가
 * 		<br>1.1
 * 		<br>▷ 유효시간설정 , 경로설정 추가
 * 		<br>1.0
 * 		<br>▷ 최초배포
 * @author
 * 		Jeongryoel Lee(이정렬)
 * 		<br>테스터 : 박의, 이현영
 * 
 * @안내사항 사용시 원하는 기능만 펼쳐서 학습하길 권장합니다.
 * <br>아래 순서대로 정리되어 있습니다.
 * <br>링크를 클릭해서 세부주석을 확인하시기 바랍니다.
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @생성자 인스턴스호출
 * <br>{@linkplain #CookieMgr(HttpServletRequest req, HttpServletResponse res)}
 * <br>request/responser객체 전역변수 초기화
 * <br>생성된 쿠키를 맵에 담기
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @확인하기_1	이름으로_존재확인
 * <br>{@linkplain #checkCookie(String targetCookieName)}
 * <br>return : boolean
 * @확인하기_2	모든_쿠키_출력하기_콘솔창
 * <br>{@linkplain #showAllCookie_console()}
 * <br>retrun : void
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @생성1	쿠키_만들기
 * <br>{@linkplain #setCookie(String cookieName, String value)}
 * <br>return : void
 * @생성2	쿠키_만들기+시간
 * <br>{@linkplain #setCookie(String cookieName, String value, int maxAge)}
 * <br>return : void
 * @생성3	쿠키_만들기_시간_경로
 * <br>{@linkplain #setCookie(String cookieName, String value, int maxAge, String path)}
 * <br>return : void
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @읽기1	이름으로_쿠키주소번지_읽기
 * <br>{@linkplain #getCookie(String targetCookieName)}
 * <br>return : Cookie
 * @읽기2	이름으로_쿠키값_읽기	
 * <br>{@linkplain #getCookieValue(String targetCookieName)}
 * <br>return : String
 * @읽기3	값으로_쿠키이름_읽기
 * <br>{@linkplain #getCookieNameFromValue(String targetCookieName)}
 * <br>return : String
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @변경	이름으로_값_변경하기
 * <br>{@linkplain #changeCookieValue(String targetCookieName, String newValue)}
 * <br>return : int
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @삭제	이름으로_삭제하기
 * <br>{@linkplain #deleteCookie(String targetCookieName)}
 * <br>return : int
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @시간1	이름으로_유효시간_설정하기
 * <br>{@linkplain #setCookieAge(String targetCookieName, int second)}
 * <br>return : int
 * @시간2	이름으로_유효시간_알아내기
 * <br>{@linkplain #getCookieAge(String targetCookieName)}
 * <br>return : int
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @경로1	이름으로_생성경로_설정하기
 * <br>{@linkplain #setCookiePath(String targetCookieName, String path)}
 * <br> : Cookie
 * @경로2	이름으로_생성경로_알아내기
 * <br>{@linkplain #getCookiePath(String targetCookieName)}
 * <br> : String
 * <br>─────────────────────────────────────────────────────────────────────────────────────────────────────────
 * @값이_숫자인지_알아내기
 * <br>{@linkplain #isNumber(String value)}
 * <br> : boolean
 *****************************************************************************************/
public class CookieMgr {
	Logger logger = Logger.getLogger(CookieMgr.class);//로그
	//페이지가 주입받은 요청객체(request)와 응답객체(response) 
	HttpServletRequest  req = null;
	HttpServletResponse res = null;
	private Map<String,Cookie> cookieMap = new HashMap<>();//쿠키담는 Map
	private List<Map<String,Cookie>> cookieList = null;
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	/**************************************************
	 * [[ 생성자 ]] 인스턴스할때 파라미터에 쿠키를 입력, 즉시 맵에 담음 
	 * @param req : 웹페이지가 주입받은 요청 객체
	 * @param res : 웹페이지가 주입받은 응답 객체
	 **************************************************/
	public CookieMgr(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;	//전역변수에 초기화
		this.res = res;	//전역변수에 초기화
		
		Cookie[] cs = req.getCookies();//요청객체로부터 전체쿠키 배열로 반환
		if(cs!=null)
			for(int i=0;i<cs.length;i++)
				cookieMap.put(cs[i].getName(),cs[i]); //Map에 담기(쿠키이름:값)
	}
	/*******************************************************************
	 * [[ 쿠키이름으로 존재 확인하기 ]]
	 * @param	targetCookieName : 찾을 쿠키 이름
	 * @return	boolean : true(존재함) / false(없음)
	 *******************************************************************/
	public boolean checkCookie(String targetCookieName) {
		boolean isExist = cookieMap.get(targetCookieName)!=null;//들어있으면 true, 없으면 false
		if(isExist) {
			logger.info("[[쿠키로그]] checkCookie : "+isExist+" | '"+targetCookieName+"' 라는 쿠키는 존재합니다");
			return isExist;
		}
		logger.info("[[쿠키로그]] checkCookie : "+isExist+" | '"+targetCookieName+"' 라는 쿠키는 찾을 수 없네요");
		return isExist;
	}
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	/*******************************************************************
	 * [[ 현재 응답객체에 등록된 모든 쿠키를 콘솔창에 출력하기 ]]	
	 * @return	void : 콘솔창에서 확인하는 용도이므로 리턴값은 비어있습니다.
	 *******************************************************************/
	public void showAllCookie_console() throws IOException{
		logger.info("");
		logger.info("	[[ 쿠키매니저 ]] ▼ 현재 response에 등록된 모든 쿠키 리스트");
		logger.info("	[[ 쿠키매니저 ]] ------------------------------------");
		logger.info("	[[ 쿠키매니저 ]]	.   |	쿠키이름	: 쿠키값");
		logger.info("	[[ 쿠키매니저 ]] ====================================");
		String getCookieName, getCookieValue;
		int count = 0;
		for (Entry<String, Cookie> entry : cookieMap.entrySet()) {
			getCookieName  = URLDecoder.decode(entry.getValue().getName().toString(),"UTF-8");
			if("JSESSIONID".equals(getCookieName)) continue;
			getCookieValue = URLDecoder.decode(entry.getValue().getValue().toString(),"UTF-8");
			logger.info("	[[ 쿠키매니저 ]]	"+(++count)+"   |	"+getCookieName+"	: "+getCookieValue);
		}
		logger.info("	[[ 쿠키매니저 ]] ------------------------------------");
		logger.info("");
	}
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[ 쿠키 만들기 ]]
	 * @param	cookieName : 생성할 쿠키 이름
	 * @param	value : 생성할 쿠키에 담을 값
	 * @return	void : res객체에 담은 결과 반환할 것이 없음
	 *******************************************************************/
	public void setCookie(String cookieName, String value) throws IOException	{
		Cookie cookie = null;//문자일때만 인코딩하도록 변경
		if(isNumber(value)) { cookie = new Cookie(cookieName, value); }
		else { cookie = new Cookie(cookieName, URLEncoder.encode(value,"UTF-8")); }
		res.addCookie(cookie);//응답객체에 담음
		cookieMap.put(cookie.getName(),cookie);//맵에 담음
		logger.info("	[[ 쿠키매니저 ]] 쿠키가 응답객체에 담겼습니다. / "+cookieName+":"+value+"			※참고-모든쿠키보기 : showAllCookie_console();");
		showAllCookie_console();
	}
	/*******************************************************************
	 * [[ 쿠키 만들기 +시간 ]]
	 * @param	cookieName : 생성할 쿠키 이름
	 * @param	value : 생성할 쿠키에 담을 값
	 * @param	maxAge : 생성할 쿠키에 설정할 유효시간
	 * @return	void : res객체에 담은 결과 반환할 것이 없음
	 *******************************************************************/
	public void setCookie(String cookieName, String value, int maxAge) throws IOException	{
		Cookie cookie = null;//문자일때만 인코딩하도록 변경
		if(isNumber(value)) { cookie = new Cookie(cookieName, value); }
		else { cookie = new Cookie(cookieName, URLEncoder.encode(value,"UTF-8")); }
		cookie.setMaxAge(maxAge);//유효시간설정
		res.addCookie(cookie);//응답객체에 담음
		cookieMap.put(cookie.getName(),cookie);//맵에 담음
		logger.info("[[ 쿠키매니저 ]] 쿠키가 응답객체에 담겼습니다. / "+cookieName+":"+value+"			※참고-모든쿠키보기 : showAllCookie_console();");
		showAllCookie_console();
	}
	/*******************************************************************
	 * [[ 쿠키 만들기 +시간,경로 ]]
	 * @param	cookieName : 생성할 쿠키 이름
	 * @param	value : 생성할 쿠키에 담을 값
	 * @param	maxAge : 생성할 쿠키에 설정할 유효시간
	 * @param	path : 생성할 쿠키가 생성될 경로
	 * @return	void : res객체에 담은 결과 반환할 것이 없음
	 *******************************************************************/
	public void setCookie(String cookieName, String value, int maxAge, String path) throws IOException	{
		Cookie cookie = null;//문자일때만 인코딩하도록 변경
		if(isNumber(value)) { cookie = new Cookie(cookieName, value); }
		else { cookie = new Cookie(cookieName, URLEncoder.encode(value,"UTF-8")); }
		cookie.setMaxAge(maxAge);//유효시간설정
		cookie.setPath(path);//경로설정
		res.addCookie(cookie);//응답객체에 담음
		cookieMap.put(cookie.getName(),cookie);//맵에 담음
		logger.info("[[ 쿠키매니저 ]] 쿠키가 응답객체에 담겼습니다. / "+cookieName+":"+value+"			※참고-모든쿠키보기 : showAllCookie_console();");
		showAllCookie_console();
	}
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[ 쿠키이름으로 쿠키 주소번지 읽기 ]]
	 * @param	targetCookieName : 찾을 쿠키 이름
	 * @return	Cookie : 찾은 쿠키의 주소번지
	 *******************************************************************/
	public Cookie getCookie(String targetCookieName){
		Cookie ckAddr = cookieMap.get(targetCookieName);
		logger.info("[[ 쿠키매니저 ]] getCookie = "+ckAddr);
		return ckAddr;
	}
	/*******************************************************************
	 * [[ 쿠키이름으로 쿠키 값 읽기 ]]
	 * @param	targetCookieName : 찾을 쿠키 이름
	 * @return	String : 찾은 쿠키에 담긴 값 ( UTF-8 디코드 완료 )
	 *******************************************************************/
	public String getCookieValue(String targetCookieName) throws IOException{
		Cookie cookie = cookieMap.get(targetCookieName);//쿠키맵에서 해당쿠키얻음
		if(cookie!=null) {
			if(isNumber(targetCookieName)) {//숫자면 디코딩은 안합니다.
				String ckValue = cookie.getValue();
				logger.info("[[ 쿠키매니저 ]] getCookieValue = "+ckValue);
				return ckValue;
			}else {//문자일때 디코딩합니다.
				String ckValue = URLDecoder.decode(cookie.getValue(),"UTF-8");
				logger.info("[[ 쿠키매니저 ]] getCookieValue = "+ckValue);
				return ckValue;
			}
		} else {
			logger.info("[[ 쿠키매니저 ]] 읽은 내역이 없습니다 / targetCookieName = "+targetCookieName);
		}
		logger.info("[[ 쿠키매니저 ]] 값 읽기 실패했습니다.  null을 반환합니다.");
		return null;
	}
	/*******************************************************************
	 * [[ 쿠키값으로 쿠키 이름  읽기 ]]
	 * @param	targetCookieValue : 찾을 쿠키값
	 * @return	String : 찾은 쿠키의 이름
	 *******************************************************************/
	public String getCookieNameFromValue(String targetCookieValue) throws IOException{
		if(targetCookieValue==null) {
			logger.info("[[ 쿠키매니저 ]] 찾고자 하는 쿠키의 값이 비어있습니다.(null) 다시확인해주세요. ");
			return null;
		} else {
			logger.info("[[ 쿠키매니저 ]] 쿠키를 검색합니다. / 대상 쿠키값 = "+targetCookieValue);
		}
		String getCookieValue = null; 
		for (Entry<String, Cookie> entry : cookieMap.entrySet()) {
			getCookieValue = entry.getValue().getValue();//쿠키맵에서 읽어낸 쿠키값
			if(targetCookieValue.equals(getCookieValue)){
				String getCookieName = entry.getValue().getName().toString();
				logger.info("[[ 쿠키매니저 ]] 쿠키가 검색되었습니다. / 검색 쿠키이름 = "+getCookieName);
				return entry.getKey();
			}
		}
		logger.info("[[ 쿠키매니저 ]] 쿠키검색결과가 없습니다.");
		return null;
	}
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[ 쿠키이름으로 해당 쿠기 값을 변경하기 ]]
	 * @param	targetCookieName : 변경할 쿠키 이름
	 * @param	newValue : 변경할 쿠키 값
	 * @return	Cookie : 값이 변경된 쿠키
	 *******************************************************************/
	public int changeCookieValue(String targetCookieName, String newValue) throws IOException {
		if(checkCookie(targetCookieName)) {
			setCookie(targetCookieName,newValue);
			logger.info("[[ 쿠키매니저 ]] 변경내역 / targetCookieName = "+targetCookieName+"/ newValue : "+newValue);
			return 1;//변경결과를 콘솔창에 출력하고 변경결과를 1로 반환함
		} else {
			logger.info("[[ 쿠키매니저 ]] 변경내역이 없습니다 / targetCookieName = "+targetCookieName);
		}
		return 0;//실패한 변경결과를 콘솔창에 출력하고 변경결과를 0으로 반환함
	}
	
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[ 쿠키 이름으로 해당쿠키 삭제하기 ]]
	 * @param	targetCookieName : 지우고자하는 쿠키 이름
	 * @return	int : 1 (쿠키삭제성공) / 0 (쿠키삭제실패)
	 *******************************************************************/
	public int deleteCookie(String targetCookieName) throws IOException{
		if(checkCookie(targetCookieName)) {
			Cookie cookie_del = cookieMap.get(targetCookieName);//쿠키맵에서 지우고자하는 쿠키를 얻음
			cookie_del = new Cookie(targetCookieName,"");
			cookie_del.setMaxAge(0);
			res.addCookie(cookie_del);
			logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키를 삭제했습니다.");
			return 1;//대상쿠키가 존재하면 삭제후 로그출력 및 1을 반환
		} else {
			logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키는 없습니다. 삭제에 실패했습니다.");
		}
		return 0;//대상쿠키가 존재하지 않으면 삭제에 실패하고  로그출력 및 0을 반환
	}
	
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[ 쿠키이름으로 유효시간 설정하기 ]]
	 * @param	targetCookieName : 대상 쿠키 이름
	 * @param	second : 설정하고자하는 유효시간 (초)
	 * @return	int : 1 (시간설정성공) / 0 (시간설정실패)
	 *******************************************************************/
	public int setCookieAge(String targetCookieName,int second) {
		Cookie cookie_age = cookieMap.get(targetCookieName);//쿠키맵에서 변경하고자 하는 쿠키를 얻음
		if(cookie_age!=null) {
			cookie_age.setMaxAge(second);
			res.addCookie(cookie_age);
			logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키의 변경된 유효시간 : "+cookie_age.getMaxAge());
			return 1;//대상 쿠키가 존재하면 유효시간 변경후 1을 반환
		} else {
			logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키는 없습니다.");
		}
		return 0;//대상 쿠키가 존재하지 않으면 0을 반환
	}
	/*******************************************************************
	 * [[ 쿠키이름으로 유효시간 알아내기 ]]
	 * @param	targetCookieName : 대상 쿠키 이름
	 * @return	int : 설정된 유효시간 (초)
	 *******************************************************************/
	public int getCookieAge(String targetCookieName) {
		Cookie cookie = cookieMap.get(targetCookieName);//쿠키맵에서 해당쿠키얻음
		int ckAge = cookie.getMaxAge();//설정된 유효시간을 반환
		logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키의 유효시간 : "+ckAge);
		return ckAge;//설정된 유효시간을 반환
	}
	
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[ 쿠키이름으로 경로설정하기 ]]
	 * @param	targetCookieName : 대상 쿠키 이름
	 * @param	path : 설정할 쿠키 생성경로
	 * @return	int : 1 (설정성공) / 0 (설정실패)
	 *******************************************************************/
	public int setCookiePath(String targetCookieName, String path) throws IOException	{
		Cookie cookie_path = cookieMap.get(targetCookieName);
		if(cookie_path!=null) {
			cookie_path.setPath(path);
			res.addCookie(cookie_path);
			logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키의 변경된 경로 : "+cookie_path.getPath());
			return 1;//대상 쿠키가 존재하면 유효시간 변경후 1을 반환
		} else {
			logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키는 없습니다.");
		}
		return 0;//대상쿠키가 존재하지 않으면 0을 반환
	}
	/*******************************************************************
	 * [[ 쿠키이름으로 경로 알아내기 ]]
	 * @param	targetCookieName : 대상 쿠키 이름
	 * @return	String : 설정된 경로 
	 *******************************************************************/
	public String getCookiePath(String targetCookieName) {
		Cookie cookie_Path = cookieMap.get(targetCookieName);//쿠키맵에서 해당쿠키얻음
		String ckPath = cookie_Path.getPath();//설정된 쿠키저장경로를 반환
		logger.info("[[ 쿠키매니저 ]] '"+targetCookieName+"' 쿠키의 경로 : "+ckPath);
		return ckPath;//설정된 쿠키저장경로를 반환
	}
	
	
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	/*******************************************************************
	 * [[쿠키값으로 입력된 문자열이 숫자인지 문자인지 판단하기]]
	 * @param	value : 판별대상
	 * @return	result : 숫자면 true / 문자면 false를 반환
	 *******************************************************************/
	public boolean isNumber(String value) {
		char 	tempCh;
	    int 	dotCount = 0;//실수일 경우 .의 개수를 체크하는 변수
	    boolean result = true;

	    for (int i=0; i<value.length(); i++){
			tempCh= value.charAt(i);//입력받은 문자열을 문자단위로 검사
			//아스키 코드 값이 45이면 '-'문자로, 음수가 된다.
			if((int)tempCh==45) return true;
			//아스키 코드 값이 48 ~ 57사이면 0과 9사이의 문자이다.
			if ((int)tempCh < 48 || (int)tempCh > 57){//0~9사이의 문자가 아니고
				if(tempCh!='.' || dotCount > 0){		//'.'도 아니거나 '.'의 개수가 이미 1개 이상이라면
					result = false;			  		// 그 문자열은 숫자가 아니다.
					break;
				}else{//.일 경우 .개수 증가
					dotCount++;
				}
			}
	    }
	    return result;
	}
	
	
	/**
	 * [[ 특정 문자열을 포함하는 쿠키를 맵에 담아 반환하기 ]]
	 * @param	certainName : 쿠키이름 검색에 사용할 조건문자열
	 * @return	rCkMap : 조건에 해당하는 쿠키맵
	 */
	public Map<String,Cookie> addCookieBasket(String certainName){
		List<Cookie> ckList = new ArrayList<>();
		for(String cookieName : cookieMap.keySet()) {
			if(cookieName.contains(certainName)) {
				
			}
		}
		return null;
	}
}
