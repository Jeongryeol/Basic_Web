package pojo.basic_ChargeLectureService.controller.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import pojo.basic_ChargeLectureService.controller.CL_Controller_Member;
import util.db_mybatis.basic_ChargeLectureService.DBConnection_basic;

public class CL_Dao_Member {
	Logger logger = Logger.getLogger(CL_Controller_Member.class);
	SqlSession		  sqlSes	= null;

//�������������������������������� SELECT ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������

	public List<Map<String,Object>> select(Map<String, Object> pMap, String crossword) {
		logger.info("	[[ Dao.Member ]] call : select");
		sqlSes = new DBConnection_basic().getConnection();
		
		//��ȸ|���̵��ߺ�üũ
		if("idOverlap".equals(crossword)) {
			logger.info("	[[ Dao_Member ]] choose : idOverlap");
			return sqlSes.selectList("idOverlap", pMap);
		}

		logger.info("	[[ Dao.Member ]] **ERROR : if���� Ÿ�� �ʾҽ��ϴ�. / crossword : "+crossword);
		return null;
	}

}
