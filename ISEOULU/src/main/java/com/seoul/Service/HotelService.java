package com.seoul.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.DAO.HotelDAO;
import com.seoul.Util.PageUtil;
import com.seoul.VO.HotelVO;



public class HotelService {
	
	public static Logger logger = 
			Logger.getLogger(HotelService.class);

	@Autowired
	private HotelDAO hdao;
	
	/**
	 * �� ������ ������ �˾Ƴ��� �Լ�
	 * @param sgCode
	 * @return
	 */
	public int getTotalCount(int sgCode){
		return hdao.getHotelCount(sgCode);
	}
	
	
	/**
	 * ���ڸ���Ʈ �������� �Լ�	- ����¡ó��(LIST)
	 * @param pInfo
	 * @return
	 */
	public ArrayList<HotelVO> getHotelList(PageUtil pInfo, int sgCode){
		int start = (pInfo.nowPage-1)*(pInfo.listCount)+1;
		int end = pInfo.nowPage * pInfo.listCount;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		map.put("sgCode", sgCode);
		ArrayList<HotelVO> hList = hdao.getHotelList(map);
		
		return hList;
	}
	
	
	/**
	 * ���ڻ󼼺��� �� �����Ͱ� �ִ� �� Ȯ���ϴ� �Լ�
	 * @param contentid
	 * @return boolean
	 */
	public boolean getHotelViewCheck(int contentid){
		int data = hdao.getHotelViewCheck(contentid);
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
	public HotelVO getHotelView(int contentid){
		
		return hdao.getHotelView(contentid);
	}
	
	/**
	 * ȣ�� �α����� ���� ����Ʈ �������� 
	 * @param vo
	 * @return map
	 */
	public HashMap getList(HotelVO vo){
		HashMap map = new HashMap();// �����͸� �������� ���ؼ�, map�� X��ǥ Y��ǥ ����Ʈid�� ����
		map.put("MX", vo.getMapx());
		map.put("MY", vo.getMapy());
		map.put("CON", vo.getContentid()); 
		ArrayList rList = hdao.getRoundList(map); //	����	�� ������(2Km �ݰ� ������-ȣ��,������,������)
		ArrayList tList = hdao.getTravlList(map); //	����	����(32, 39 �̿��� ��ǥ ����)
		ArrayList hList = hdao.getHotelList2(map);//	����	����(32, ��ǥ������) 
		ArrayList fList = hdao.getFooodList(map);//		����	����(39, ��ǥ������)
		HashMap listMap = new HashMap();	//	��ȸ�� �����͸� map�� ��Ƽ� ��Ʈ�ѷ��� ��ȯ
		listMap.put("RLIST", rList);
		listMap.put("TLIST", tList);
		listMap.put("HTLIST", hList);
		listMap.put("FLIST", fList);
		return listMap;
	}
	
}
