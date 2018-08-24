package util.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class BasketMgr {
	Logger logger = Logger.getLogger(BasketMgr.class);//로그
	//페이지가 주입받은 요청객체(request)와 응답객체(response) 
	HttpServletRequest  req = null;
	HttpServletResponse res = null;
	StringBuilder basketList = null;
	
	public BasketMgr(HttpServletRequest req, HttpServletResponse res) throws IOException {
		logger.info("	[[ 쿠키+장바구니 매니저 ]] 호출성공");
		String mem_id = req.getSession().getAttribute("mem_id").toString();
		this.req = req;
		this.res = res;
		this.basketList = new StringBuilder(
				new CookieMgr(req,res).getCookieValue(mem_id+"_basket"));
		logger.info("	[[ 쿠키+장바구니 매니저 ]] basketList = "+basketList.toString());
	}
	public StringBuilder appendList(String target, int amount) {
		//basketList
		return null;
	}
    public static void main(String[] args)
    {
    	String cookieValue = "apple=1/water=2/bear=5/cable=1";
        StringBuilder sb = new StringBuilder(cookieValue);
        
        //원본확인
        System.out.println("1. "+sb);
        
        //추출 및 교체
        String[] divs = sb.toString().split("/");
        int index = 0;
        for(String str : divs) {
        	String [] objs = str.split("=");
        	//for(String str2 : objs) System.out.println(str2);
        	if("water".equals(objs[0])) {
        		objs[1]="4";
        		str = objs[0]+"="+objs[1];
        		System.out.println(str);
        		//sb.replace(start, end, str);
        		sb.replace(index+1, index+str.length()+1, str);
        	}
        	index += str.length();
        }
        
        //sb.replace(start, end, str)
        
        System.out.println("2. "+sb);
        
        /*sb.replace(8,15,"guitar=1");
        System.out.println("2. "+sb);
        
        sb.setCharAt(2,'네');
        sb.append(", 아기 곰~");
        System.out.println("3. "+sb);
        
        sb.reverse();
        System.out.println("4. "+sb);*/
    }
}
