package pojo.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.demo.DemoFrontAssisstant;
import pojo.demo.member.DemoMemberController;

/***************************************************************************************
 * [[ Servlet :: One for All ]] - POJO Framework에 따른 demo프로젝트의 최전방 분기객체
 * @작성자	이정렬
 * @작성일	2018.08.10	
 * @설계의도
 * <u>	요청받는 것에 대한 역할</u><br>
 * 		웹의 화면(View)에서 클라이언트(Client:사용자|업무담당자)가 요청한 모든 업무에 대하여 [하나의 서블릿이 관리]하고,
 * 		모든업무를 단위작업으로 분기도록 수행하여 [개별 Controller 계층]으로 [전달]역할을 수행한다.<br><br>
 * 		
 * 		"head"에 따라 분리된 [개별 Controller 계층]이 [Logic 계층 내부]로 "crud"를 전달한다.<br>
 * 		"crud"에 따라 분리된 [개별 Logic 계층 내부]에서  [Dao 계층]으로 "chop"을 전달한다.<br>
 * 		"chop"에 따라 분리된 [개별 Dao 계층 내부]에서 [개별 Mapper]에 맞는 DB연동을 수행한다.<br>
 * 		수행한 이후 나온 결과는 역순으로 전달한다.<br><br>
 * 
 * <u>	응답하는 것에 대한 역할</u><br>
 * 		전달되어온 결과에는 "응답할 페이지의 경로"와 "응답방법", "조회결과가" [PageCarrier]에 담겨서 들어온다.
 * 		담긴 내용 따라 출력을 분기하는 역할만을 수행한다.(쿠키나 세션도 이때 실제로 서버측으로 전달된다)
 ***************************************************************************************/

public class DemoFrontController extends HttpServlet {
	Logger logger = Logger.getLogger(DemoFrontController.class);//로그준비
	private static final long serialVersionUID = 2314188011099L;
	/*******************************************************************************************************
	 * [[ 업무분기 ]] - 요청객체를 분석하여 업무를 수행하도록 전달하고 결과를 수집함
	 * @param req : 화면에서 요청한 요청객체																				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 			URL / ParameterData / Cookie&Session
	 * @param res : 화면으로 전달할 응답객체 (현재 빈 상태)
	 *******************************************************************************************************/
	public void doFork(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("	[[ Front ]] doFork() 호출성공");
		//요청명령 추출
		DemoFrontAssisstant fas = new DemoFrontAssisstant();			   //설명   	/(프로젝트이름)/(프로젝트 저장 경로)/매핑명 /Head / curd / chop
		String pattern = fas.getPattern(req);//요청의 URL에서 패턴 추출		| ex. /Basic_Web/5_Framework/pojo/demo/member/select/check_ID_Overlap.demo
		String head = fas.getHead(pattern);	 //패턴에서 head(단위업무)추출	| ex. member
		String crud = fas.getCRUD(pattern);	 //패턴에서 crud(서버업무)추출	| ex. select
		String chop = fas.getChop(pattern);	 //패턴에서 chop(세부업무)추출	| ex. check_ID_Overlap.demo
		
		//분기업무에 필요한 객체 준비
		Map<String,Object> pMap = new HashMap<>();//업무전달에 필요한 맵객체
		DemoPageCarrier rpc = null;//결과반환용 객체
		
		//[head]명령 분기
		//회원(member)관련업무 :: /Basic_Web/5_Framework/pojo/demo/member/...
		if("member".equals(head)) {
			pMap.put("crud", crud);//서버업무 지시문 수집
			pMap.put("chop", chop);//세부업무 지시문 수집
			
			try {
				//회원관리용 컨트롤러로 전달 후 결과를 반환받음
				rpc = new DemoMemberController().CRUD(req, res, pMap); 
				doPage(req,res,rpc);//화면분기
				
			} catch (Exception e) {
				logger.info("	[[ F.member-예외 ]] "+e.toString());
				e.printStackTrace();
			}
		}
		//쇼핑(shopping)관련 단위업무
		else if("shop".equals(head)) {
			//head업무 분기 예시용
		}
		//투자(fund)관련 단위업무
		else if("fund".equals(head)) {
			//head업무 분기 예시용
		}
		//비정상적 접근 감지시
		else {
			logger.info("	[[ Front ]] ×××××(비정상적 접근 감지)×××××");
			doPage(req,res,null);
		}
	}
	/*******************************************************************************************************
	 * [[ 화면분기 ]] - 응답객체를 설정하여 화면을 출력함
	 * @param req : 화면을 forward방식으로 보낼때 사용할 요청객체
	 * @param res : 화면으로 전달할 응답객체 (현재 빈 상태 )
	 * @param wp : 응답객체를 설정할 논리처리결과객체																 				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 		path																						 				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 		isRedirect ( true:sendRedirect | false:forward )																 				<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 * 		datas ( 화면에 전달해야하는 자료집합 )
	 *******************************************************************************************************/
	public void doPage(HttpServletRequest req,HttpServletResponse res, DemoPageCarrier rpc) throws ServletException, IOException {
		logger.info("	[[ Front ]] doPage() 호출성공");
		//비정상적 접근에 대한 페이지 처리
		if(rpc==null) {
			RequestDispatcher view = req.getRequestDispatcher("/Basic_Web/5_Framework/pojo/pojo_wrongPage.jsp");
			view.forward(req, res);
			logger.info("	[[ Front ]] ×××××(비정상페이지 처리)×××××");
			return;
		}
		//결과값이 전달되어 왔을 경우 화면처리
		else {
			logger.info("	[[ Front ]] rpc값이 존재합니다. 페이지 전달방식 처리합니다.");
			//true : sendRedirect 방식
			if(rpc.isRedirect()) {
				logger.info("	[[ Front ]] true :: sendRedirect 실행");
				logger.info("	[[ Front ]] rpc.getpath() = "+rpc.getPath());
				//조회결과를 뿌릴 필요가 없는 경우, 이동할 jsp페이지로 sendRedirect로 이동 ( 폴더노출은 괜찮은가?? )
				res.sendRedirect(rpc.getPath());
			}
			//false : forward 방식
			else {
				logger.info("	[[ Front ]] true :: forward 실행");
				logger.info("	[[ Front ]] rpc.getpath() = "+rpc.getPath());
				//조회결과를 뿌려야하는 경우. 결과를 보여줄 jsp페이지를 forward하여 폴더경로가 노출되지 않도록 할 것.
				RequestDispatcher view = req.getRequestDispatcher(rpc.getPath());
				view.forward(req, res);
			}
			logger.info("E N D	[ ◀---- ] ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("START	[ --▼-- ] ────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		doFork(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("START	[ --▼-- ] ────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		doFork(req,res);
	}
	
}
