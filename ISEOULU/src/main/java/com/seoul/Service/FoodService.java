package com.seoul.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.DAO.FoodDAO;
import com.seoul.Util.PageUtil;
import com.seoul.VO.FoodVO;



public class FoodService {
	
	public static Logger logger = 
			Logger.getLogger(HotelService.class);

	@Autowired
	private FoodDAO fdao;
	
	/**
	 * �� ������ ������ �˾Ƴ��� �Լ�
	 * @param sgCode
	 * @return
	 */
	public int getTotalCount(int sgCode){
		return fdao.getFoodCount(sgCode);
	}
	
	
	/**
	 * ���ĸ���Ʈ �������� �Լ�	- ����¡ó��(LIST)
	 * @param pInfo
	 * @return
	 */
	public ArrayList<FoodVO> getFoodList(PageUtil pInfo, int sgCode){
		int start = (pInfo.nowPage-1)*(pInfo.listCount)+1;
		int end = pInfo.nowPage * pInfo.listCount;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		map.put("sgCode", sgCode);
		ArrayList<FoodVO> fList = fdao.getFoodList(map);
		
		return fList;
	}
	
	
	/**
	 * ���Ļ󼼺��� �� �����Ͱ� �ִ� �� Ȯ���ϴ� �Լ�
	 * @param contentid
	 * @return boolean
	 */
	public boolean getFoodViewCheck(int contentid){
		int data = fdao.getFoodViewCheck(contentid);
		boolean check = false;
		
		
		if(data != 1){
			
			return check;
		}
		
		check = true;
		return check;
	}
	
	/**
	 * ���� �� ���������� ����readcount ����
	 * @param contentid
	 * @return HotelVO
	 */
	public FoodVO getFoodView(int contentid){
		
		return fdao.getFoodView(contentid);
	}
	
	/**
	 * ���� �α����� ���� ����Ʈ �������� 
	 * @param vo
	 * @return map
	 */
	public HashMap getList(FoodVO vo){
		HashMap map = new HashMap();// �����͸� �������� ���ؼ�, map�� X��ǥ Y��ǥ ����Ʈid�� ����
		map.put("MX", vo.getMapx());
		map.put("MY", vo.getMapy());
		map.put("CON", vo.getContentid()); 
		ArrayList rList = fdao.getRoundList(map); //	����	�� ������(2Km �ݰ� ������-ȣ��,������,������)
		ArrayList tList = fdao.getTravlList(map); //	����	����(32, 39 �̿��� ��ǥ ����)
		ArrayList hList = fdao.getHotelList(map);//	����	����(32, ��ǥ������) 
		ArrayList fList = fdao.getFooodList(map);//		����	����(39, ��ǥ������)
		HashMap listMap = new HashMap();	//	��ȸ�� �����͸� map�� ��Ƽ� ��Ʈ�ѷ��� ��ȯ
		listMap.put("RLIST", rList);
		listMap.put("TLIST", tList);
		listMap.put("HTLIST", hList);
		listMap.put("FLIST", fList);
		return listMap;
	}
	
}
