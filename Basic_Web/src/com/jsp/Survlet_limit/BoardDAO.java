package com.jsp.Survlet_limit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * [부드러운 협업을 위한 섬세한 시작]
 * @author	누가 작성했는지 작성
 * @sql		어떤 SQL문으로 작동시키는지 작성
 * @param	pMap : 무엇이 담기는지 작성
 * @return	무엇을 리턴하는지 작성
 */
public class BoardDAO {
	/* **************************
	 * 효과적인 디버깅을 위한 log4j 활용하기
	 * (org.apache를 import하세요~)
	 ****************************/
	Logger logger = Logger.getLogger(BoardDAO.class);
	
	//조회를 수행하는 DAO
	public List<Map<String,Object>> getBoardList(Map<String,Object> pMap) {
		logger.info("getBoardList() 메소드 호출 성공");
		List<Map<String,Object>> boardList
			= new ArrayList<Map<String,Object>>();
		//INSERT HERE : DB연동
		return boardList;
	}
	
	//입력을 수행하는 DAO
	public int boardInsert(Map<String,Object> pMap) {
		logger.info("boardInsert() 메소드 호출 성공");
		int result = 0;
		//INSERT HERE : DB연동
		return result;
	}
	
	//수정을 수행하는 DAO
	public int boardUpdate(Map<String,Object> pMap) {
		logger.info("boardUpdate() 메소드 호출 성공");
		int result = 0;
		//INSERT HERE : DB연동
		return result;
	}
	
	//삭제를 수행하는 DAO
	public int boardDelete(Map<String,Object> pMap) {
		logger.info("boardDelete() 메소드 호출 성공");
		int result = 0;
		//INSERT HERE : DB연동
		return result;
	}
}
