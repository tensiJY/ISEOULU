package com.seoul.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.VO.HotelVO;



public class HotelDAO extends SqlSessionDaoSupport{
	public static Logger logger = 
			Logger.getLogger(HotelDAO.class);
	
	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * 시군구의 코드로 숙박 갯수를 가져옴
	 * @param sgCode
	 * @return
	 */
	public int getHotelCount(int sgCode){
		return (Integer)sst.selectOne("hotel.hotelCount", sgCode);
	}
	
	
	/**
	 * 숙박 리스트(목록)
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList<HotelVO> getHotelList(HashMap<String, Integer> map){
		return (ArrayList) sst.selectList("hotel.hotelList", map);
	}
	
	
	
	/**
	 * 숙박 상세보기전 데이터가 있는지 확인하는 함수
	 * @param contentid
	 * @return Integer
	 */
	public int getHotelViewCheck(int contentid){
		return (Integer)sst.selectOne("hotel.hotelViewCheck", contentid);
	}
	
	/**
	 * 숙박 상세보기 데이터 와 readcount 증가
	 * @param contentid
	 * @return HotelVO
	 */
	public HotelVO getHotelView(int contentid){
		sst.update("hotel.updateReadcount", contentid);
		return (HotelVO)sst.selectOne("hotel.hotelView", contentid);
	}
	
	
	/**
	 * 호텔 반경 2000m 리스트 가져오기
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList getRoundList(HashMap map){
		return (ArrayList)sst.selectList("hotel.roundList", map);
	}
	
	
	//	다음지도 관광리스트
	public ArrayList getTravlList(HashMap map){
		logger.info("	HotelDAO	getTravlList");
		
		return (ArrayList)sst.selectList("hotel.tList", map);
	}
	
	//	다음지도 호텔리스트
	public ArrayList getHotelList2(HashMap map){
		logger.info("	HotelDAO	getHotelList2");
		
		return (ArrayList)sst.selectList("hotel.hList", map);
	}
	
	//	다음지도 음식점리스트
	public ArrayList getFooodList(HashMap map){
		logger.info("	HotelDAO	getFooodList");
		
		return (ArrayList)sst.selectList("hotel.fList", map);
	}	
	
}
