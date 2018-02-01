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
	 * �ñ����� �ڵ�� ���� ������ ������
	 * @param sgCode
	 * @return
	 */
	public int getHotelCount(int sgCode){
		return (Integer)sst.selectOne("hotel.hotelCount", sgCode);
	}
	
	
	/**
	 * ���� ����Ʈ(���)
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList<HotelVO> getHotelList(HashMap<String, Integer> map){
		return (ArrayList) sst.selectList("hotel.hotelList", map);
	}
	
	
	
	/**
	 * ���� �󼼺����� �����Ͱ� �ִ��� Ȯ���ϴ� �Լ�
	 * @param contentid
	 * @return Integer
	 */
	public int getHotelViewCheck(int contentid){
		return (Integer)sst.selectOne("hotel.hotelViewCheck", contentid);
	}
	
	/**
	 * ���� �󼼺��� ������ �� readcount ����
	 * @param contentid
	 * @return HotelVO
	 */
	public HotelVO getHotelView(int contentid){
		sst.update("hotel.updateReadcount", contentid);
		return (HotelVO)sst.selectOne("hotel.hotelView", contentid);
	}
	
	
	/**
	 * ȣ�� �ݰ� 2000m ����Ʈ ��������
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList getRoundList(HashMap map){
		return (ArrayList)sst.selectList("hotel.roundList", map);
	}
	
	
	//	�������� ��������Ʈ
	public ArrayList getTravlList(HashMap map){
		logger.info("	HotelDAO	getTravlList");
		
		return (ArrayList)sst.selectList("hotel.tList", map);
	}
	
	//	�������� ȣ�ڸ���Ʈ
	public ArrayList getHotelList2(HashMap map){
		logger.info("	HotelDAO	getHotelList2");
		
		return (ArrayList)sst.selectList("hotel.hList", map);
	}
	
	//	�������� ����������Ʈ
	public ArrayList getFooodList(HashMap map){
		logger.info("	HotelDAO	getFooodList");
		
		return (ArrayList)sst.selectList("hotel.fList", map);
	}	
	
}
