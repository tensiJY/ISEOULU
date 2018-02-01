package com.seoul.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.VO.FoodVO;



public class FoodDAO extends SqlSessionDaoSupport{
	public static Logger logger = 
			Logger.getLogger(FoodDAO.class);
	
	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * �ñ����� �ڵ�� ���� ������ ������
	 * @param sgCode
	 * @return
	 */
	public int getFoodCount(int sgCode){
		return (Integer)sst.selectOne("food.foodCount", sgCode);
	}
	
	
	/**
	 * ���� ����Ʈ(���)
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList<FoodVO> getFoodList(HashMap<String, Integer> map){
		return (ArrayList) sst.selectList("food.foodList", map);
	}
	
	
	
	/**
	 * ���� �󼼺����� �����Ͱ� �ִ��� Ȯ���ϴ� �Լ�
	 * @param contentid
	 * @return Integer
	 */
	public int getFoodViewCheck(int contentid){
		return (Integer)sst.selectOne("food.foodViewCheck", contentid);
	}
	
	/**
	 * ���� �󼼺��� ������ �� readcount ����
	 * @param contentid
	 * @return HotelVO
	 */
	public FoodVO getFoodView(int contentid){
		sst.update("food.updateReadcount", contentid);
		return (FoodVO)sst.selectOne("food.foodView", contentid);
	}
	
	
	/**
	 * ���� �ݰ� 2000m ����Ʈ ��������
	 * @param map
	 * @return ArrayList
	 */
	public ArrayList getRoundList(HashMap map){
		return (ArrayList)sst.selectList("food.roundList", map);
	}
	
	
	//	�������� ��������Ʈ
	public ArrayList getTravlList(HashMap map){
		logger.info("	HotelDAO	getTravlList");
		
		return (ArrayList)sst.selectList("hotel.tList", map);
	}
	
	//	�������� ���ĸ���Ʈ
	public ArrayList getHotelList(HashMap map){
		logger.info("	HotelDAO	getHotelList2");
		
		return (ArrayList)sst.selectList("hotel.hList", map);
	}
	
	//	�������� ����������Ʈ
	public ArrayList getFooodList(HashMap map){
		logger.info("	HotelDAO	getFooodList");
		
		return (ArrayList)sst.selectList("hotel.fList", map);
	}	
	
}
