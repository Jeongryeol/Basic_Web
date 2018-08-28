package pojo.basic_ChargeLectureService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pojo.basic_ChargeLectureService.controller.CL_UpperController;
import pojo.basic_ChargeLectureService.controller.pack.ResultPack;

/*********************************************************************************************************************
 * [[ HeadServlet ]]
 * @author	Jeongryeol Lee (이정렬)<br>
 * @purpose
 * 		1. *.basic으로 끝나는 모든 URL을 하나의 Servlet으로 인터셉트
 * 	<br>2. 받은 요청을 업무에 따라 분배하고, 돌아온 결과를 화면으로 전달
 *********************************************************************************************************************/
public class CL_HeadServlet extends HttpServlet{
	Logger logger = Logger.getLogger(CL_HeadServlet.class);
	
	private void doService(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("	[[ HeadServlet ]] call : doService");
		String requestURI	= req.getRequestURI();
		String contextPath	= req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
	//─────┤ 업무분기 시작 ├──────────────────────────────────────────────────────────────────────────────
		ResultPack pack = null;
		CL_UpperController controller = new CL_Judgment().getController(command);
		if(controller!=null) {
			logger.info("	[[ HeadServlet : 업무분기 ]] controller = "+controller);
			try {
				pack = controller.execute(req,res);
			} catch (Exception e) {
				logger.info("	[[ HeadServlet ]] **ERROR |"+e.toString());
				e.printStackTrace();
			}
		} else {
			logger.info("	[[ HeadServlet ]] **ERROR | controller is null. close...");
			return;
		}
	//─────┤ 화면분기 시작 ├──────────────────────────────────────────────────────────────────────────────
		if(pack!=null) {
			logger.info("	[[ HeadServlet : 화면분기 ]] pack.isRedirect()	= "+pack.isRedirect());
			logger.info("	[[ HeadServlet : 화면분기 ]] pack.getPath()		= "+pack.getPath());
			if(pack.isRedirect()) {
				logger.info("	[[ HeadServlet : 화면분기 ]] choose : sendRedirect");
				res.sendRedirect(pack.getPath());
			} else {
				logger.info("	[[ HeadServlet : 화면분기 ]] choose : forward");
				RequestDispatcher view = req.getRequestDispatcher(pack.getPath());
				view.forward(req, res);
			}
		}
		logger.info("────┤ CLOSE ├─────────────────────────────────────────────────────────────────────────────────────────────────");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("────┤ START:GET ├─────────────────────────────────────────────────────────────────────────────────────────────────");
		doService(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("────┤ START:POST ├─────────────────────────────────────────────────────────────────────────────────────────────────");
		doService(req,res);
	}
}
