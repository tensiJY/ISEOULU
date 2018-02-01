package com.seoul.DAO;

import java.util.ArrayList; 
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.VO.TravelVO;



public class TravelDAO extends SqlSessionDaoSupport{
	public static Logger logger = 
			Logger.getLogger(TravelDAO.class);
	
	@Autowired
	private SqlSessionTemplate ses;
	
	/**
	 * 시군구 코드로 관광 가져오기
	 * @param sgCode
	 * @return
	 */
	public int getTravelCount(int sgCode){
		return (Integer)ses.selectOne("travel.travelCount", sgCode);
	}
	
	
	/*
	 * 관광 리스트(목록)
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList<TravelVO> getTravelList(HashMap<String, Integer> map){
		return (ArrayList) ses.selectList("travel.travelList", map);
	}
	
	
	
	/**
	 * 관광 상세보기전 데이터가 있는지 확인하는 함수
	 * @param contentid
	 * @return Integer 
	 */
	public int getTravelViewCheck(int contentid){
		return (Integer)ses.selectOne("travel.travelViewCheck", contentid);
	}
	
	/**
	 * 관광 상세데이터
	 * @param contentid
	 * @return TravelVO
	 */
	public TravelVO getTravelView(int contentid){
		ses.update("travel.updateReadcount", contentid);
		return (TravelVO)ses.selectOne("travel.travelView", contentid);
	}
	
	
	/**
	 * 관광지 반경 2km리스트 가져오기
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList getRoundList(HashMap map){
		return (ArrayList)ses.selectList("travel.roundList", map);
	}
	
	
//	다음지도 관광리스트
	public ArrayList getTravelList2(HashMap map){
		logger.info("	TravelDAO	getTravelList2");
		
		return (ArrayList)ses.selectList("travel.tList", map);
	}
	
//	다음지도 호텔리스트
	public ArrayList getHotelList(HashMap map){
		logger.info("	TravelDAO	getHotelList");
		
		return (ArrayList)ses.selectList("travel.hList", map);
	}
	
//	다음지도 음식점리스트
	public ArrayList getFooodList(HashMap map){
		logger.info("	TravelDAO	getFooodList");
		
		return (ArrayList)ses.selectList("travel.fList", map);
	}	
	
}
