package pojo.demo.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.demo.DemoFrame;
import pojo.demo.DemoPageCarrier;
import util.web.CookieMgr;
import util.web.HashMapBinder;
import util.web.Secure_Base64;

public class DemoMemberController implements DemoFrame{
	//유틸리티
	Logger logger = Logger.getLogger(DemoMemberController.class);//로그준비
	CookieMgr cm = null;
	//반환자료 준비
	DemoPageCarrier rpc = new DemoPageCarrier();
	String path = null;
	boolean isRedirect = false;
	List<Map<String,Object>> demoList = null;
	int status = -2; 
/* 	[[ 상태값 기준 ]]
		  2 : 아이디, 비번 모두 맞음
		  1 : 아이디만 맞음 또는 아이디 중복있음
		 -1 : 아이디가 틀림 또는 아이디 중복없음
		 -2 : 기본값 
*/	int result = 0;
	
	/******************************************************************************************************************
	 * @param	req : 세션생성, 사용자가 입력한 값 요청, 한글처리, forward처리, scope범위 읽기, 저장소역할
	 * @param	res : 마임타입, 인코딩타입 설정, PrintWriter객체생성(응답페이지처리가능),페이지이동처리(sendRedirect/forward)
	 * @param	pMap : 사용자가 입력한 값, View계층으로 전달할 값 담기 
	 * @return	PageCorrier : 페이지 이동 방법, 어디로 가야하지?(응답페이지), 자료구조추가(업무처리결과 담기)
	 ******************************************************************************************************************/
	@Override //WorkFrame에 의해 짜진 형태로 수행
	public DemoPageCarrier CRUD(HttpServletRequest req, HttpServletResponse res, Map<String, Object> pMap) throws Exception {
		logger.info("	[[ Member.C ]] CRUD 호출성공 - MemberController계층 진입성공 !");
		Secure_Base64 sb = null;
		//업무 분기후 개별 DAO 업무 시작
		String crud = pMap.get("crud").toString();//서버업무 분기기준 : DAO선택
		String chop = pMap.get("chop").toString();//세부업무 분기기준 : DAO내부 메소드선택
		
		//[조회]업무에 대하여
		//URL패턴 : /Basic_Web/5_Framework/pojo/demo/member/select/ ]]
		if("select".equals(crud)) {
			logger.info("	[[ Member.C ]] select :: 조회업무 선택");
			
			//로그인에 대한 요청이라면
			//URL패턴 : /Basic_Web/5_Framework/pojo/demo/member/select/memberLogin.demo" ]]
			if("memberLogin.demo".equals(chop)) {
				logger.info("	[[ Member.C ]] member login :: 회원로그인 시작");
				//요청객체에서 ID와 PW를 얻어내기
				String mem_id = req.getParameter("mem_id");
				String mem_pw = req.getParameter("mem_pw");
				//암호모듈 적용하기 : Base64
				sb = new Secure_Base64();//암호모듈 생성
				String sec_mem_id = sb.changeBase64(mem_id);
				String sec_mem_pw = sb.changeBase64(mem_pw);
				//담기
				pMap = new HashMap<>();//Logic계층에 넘기기위한 새로운 pMap준비
				pMap.put("mem_id", sec_mem_id);
				pMap.put("mem_pw", sec_mem_pw);
				
				//new HashMapBinder(req).bind(pMap);				//Logic계층에 넘기기위한 새로운 pMap준비
				demoList = new DemoMemberLogic().memberLogin(pMap);	//Logic계층에 pMap으로 돌려받은 결과값 저장
				
				cm = new CookieMgr(req, res);//쿠키매니저 초기화
				String resultOut = demoList.get(0).get("resultOut").toString();
				
				//로그인결과판독
				logger.info("	[[ Member.C ]] resultOut : "+resultOut);
				if("The ID does not exist".equals(resultOut)) {//아이디 없음
					isRedirect = false;//포워딩
					path = "./pojo_mem_login_fail.jsp";//실패페이지
					demoList = null;
					status = -1;
					cm.setCookie("logOnStatus", String.valueOf(status));
					
				} else if("Passwords do not match".equals(resultOut)){//비밀번호틀림
					isRedirect = false;//포워딩
					path = "./pojo_mem_login_fail.jsp";//실패페이지
					demoList = null;
					status = -2;
					cm.setCookie("logOnStatus", String.valueOf(status));
					
				} else if(!"The ID does not exist".equals(resultOut)
						&&!"Passwords do not match".equals(resultOut)) {//로그인에 성공한 경우
					isRedirect = true;//리다이텍트
					path = "./pojo_mem_login_success.jsp";
					status = 2;
					cm.setCookie("logOnStatus", String.valueOf(status));//로그인 성공
					cm.setCookie("mem_name",resultOut);//쿠키로 사용자 이름 넘기기
					
				} else //기타에러상황
					logger.info("	[[ Member.C ]] 에러~~에러~~에러~~에러~~에러~~에러~~");
				
				setPageCarrier(isRedirect, path, demoList, status);//케리어에 정보세팅
				return rpc;//반환
			}
			//중복검사에 대한 요청이라면
			//URL패턴 : /Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo" ]]
			else if("check_ID_Overlap.demo".equals(chop)) {
				logger.info("	[[ Member.C ]] id overlap check :: 아이디 중복검사 조회 시작");
				String mem_id = req.getParameter("mem_id");//요청객체로부터 넘어온 ID 발췌
				logger.info("	[[ Member.C ]] req.getParameter(\"mem_id\") = "+mem_id);
				
				//암호모듈 적용하기 : Base64
				sb = new Secure_Base64();//암호모듈 생성
				String sec_mem_id = sb.changeBase64(mem_id);
				logger.info("	[[ Member.C ]] sec_mem_id = "+sec_mem_id);
				
				demoList = new DemoMemberLogic().memberLogin(pMap);	//Logic계층에 pMap으로 돌려받은 결과값 저장
				
				//케리어에 정보세팅
				isRedirect = true;
				rpc.setRedirect(isRedirect);//중복결과를 리다이렉트
				rpc.setPath(path);	//목표페이지주소
				rpc.setDatas(null);	//중복검사에 대하여 넘길 자료는 없음
				logger.info("	[[ Member.C ]] rpc(Return Page Container)세팅완료 ");
				
			}//----end of if(중복체크)
		}//--------end of if(조회업무)
		
		//[입력]업무에 대하여
		//URL패턴 : /Basic_Web/5_Framework/pojo/demo/member/insert/... ]]
		else if("insert".equals(crud)) {
			logger.info("	[[ Member.C ]] insert :: 입력업무 선택");
			
			//회원가입에 대한 요청이라면
			//URL패턴 : /Basic_Web/5_Framework/pojo/demo/member/insert/signup.demo ]]
			if("signup.demo".equals(chop)) {
				//넘어온 데이터를 바인딩함
				HashMapBinder hmb = new HashMapBinder(req);
				hmb.bind(pMap);
				//저장한 Map데이터를 Logic계층으로 전달하여 결과를 반환받음
				//result = mlog.memberSignup(pMap);
				logger.info("	[[ Member.C ]] 결과반환 성공! result = "+result);
				
				if(result==1) {//입력성공한 경우
					logger.info("	[[ Member.C ]] 입력성공(1)에 대한 페이지 전환정보 설정");
					//설정
					isRedirect = true;			//sendRedirect
					path = "./pojo_mem_signup_success.jsp";
				} else {//입력실패한 경우
					logger.info("	[[ Member.C ]] 입력실패(0)에 대한 페이지 전환정보 설정");
					isRedirect = false;			//forward
					path = "./pojo_mem_signup_fail.jsp";
				}
				//케리어에 정보세팅
				rpc.setRedirect(isRedirect);
				rpc.setPath(path);		//목표페이지주소
				rpc.setDatas(null);	//회원가입 성공했을때는 채워지고 아닐때는 빈 상태 
				return rpc;
				
			}//----end of if(중복체크)
		}//---end of else if(입력업무)
		
		//[수정]업무에 대하여
		else if("update".equals(crud)) {
			
		}//---end of else if(수정업무)
		
		//[삭제]업무에 대하여
		else if("delete".equals(crud)) {
			
		}//---end of else if(삭제업무)
		
		logger.info("	[[ Member.C ]] return rpc = "+rpc);
		return rpc;//FrontController에게 반환
	}
	
	//ReturnPageCarrier Setting
	public DemoPageCarrier setPageCarrier(boolean isRedirect,String path
					  ,List<Map<String,Object>> demoList, int status) {
		rpc.setRedirect(isRedirect);//페이지전환방식
		rpc.setPath(path);			//목표페이지 주소
		rpc.setDatas(demoList);		//페이지전달용 자료
		rpc.setStatus(status);		//페이지전달용 상태
		logger.info("	[[ Member.C ]] rpc(Return Page Container)세팅완료 ");
		return rpc;
	}

}
