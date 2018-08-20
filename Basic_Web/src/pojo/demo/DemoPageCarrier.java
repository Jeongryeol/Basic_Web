package pojo.demo;

import java.util.List;
import java.util.Map;

/****************************************************************************************
 * [[ 페이지 이동 모듈 ]]
 * @작성자	이정렬
 * @작성일	2018.08.10
 * @설계의도
 * 		DAO의 결과를 화면에 전달하기 위해<br>
 * 		다음 3가지 자료를 "FrontController.java"로 전달한다
 * @자료
 * 		- isRedirect<br>
 * 		- path<br>
 * 		- datas
 * @설명
 * 		- <b>boolean <u>isRedirect</u></b> <br>&nbsp;&nbsp;&nbsp;&nbsp;
 * 		 : true(sendRedirect 방식) / false(forward 방식)<br> 
 * 		- <b>String <u>path</u></b> <br>&nbsp;&nbsp;&nbsp;&nbsp;
 * 		 : 전송할 페이지 url경로 ( 서블릿 | jsp )<br>
 * 		- <b>List <u>datas</u></b> <br>&nbsp;&nbsp;&nbsp;&nbsp;
 * 		 : 조회결과  
 ****************************************************************************************/
public class DemoPageCarrier {

	private	boolean	isRedirect	= false;//true : sendRedirect | false : foward 
	private	String	path		= null;	//이동할 페이지경로 ( Servlet | JSP )
	private List<Map<String,Object>> datas = null;//조회결과
	private int	status	= -2;//아이디,비밀번호찾기 상태값 기본디폴트값 -2설정
	
	//페이지 이동방식 getter,setter
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	//페이지 경로 getter,setter
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	//조회결과 getter,setter
	public List<Map<String,Object>> getDatas(){
		return datas;
	}
	public void setDatas(List<Map<String,Object>> datas) {
		this.datas = datas;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
