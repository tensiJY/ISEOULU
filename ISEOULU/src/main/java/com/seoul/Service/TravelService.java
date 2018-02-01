package com.seoul.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.DAO.TravelDAO;
import com.seoul.Util.PageUtil;
import com.seoul.VO.TravelVO;



public class TravelService {
	
	public static Logger logger = 
			Logger.getLogger(TravelService.class);

	@Autowired
	private TravelDAO tdao;
	
	/**
	 * 총데이터 갯수 구하기 함수
	 * @param sgCode
	 * @return
	 */
	public int getTotalCount(int sgCode){
		return tdao.getTravelCount(sgCode);
	}
	
	
	/**
	 * 여행 리스트 가져오는 함수
	 * @param pInfo
	 * @return
	 */
	public ArrayList<TravelVO> getTravelList(PageUtil pInfo, int sgCode){
		int start = (pInfo.nowPage-1)*(pInfo.listCount)+1;
		int end = pInfo.nowPage * pInfo.listCount;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		map.put("sgCode", sgCode);
		ArrayList<TravelVO> tList = tdao.getTravelList(map);
		
		return tList;
	}
	
	
	/**
	 * 여행 상세보기전 데이터 위치 확인 함수
	 * @param contentid
	 * @return boolean
	 */
	public boolean getTravelViewCheck(int contentid){
		int data = tdao.getTravelViewCheck(contentid);
		boolean check = false;
		
		
		if(data != 1){
			
			return check;
		}
		
		check = true;
		return check;
	}
	
	/**
	 * 여행 상세보기 함수
	 * @param contentid
	 * @return TravelVO
	 */
	public TravelVO getTravelView(int contentid){
		return tdao.getTravelView(contentid);
	}
	
	/**
	 * 여행 인근지역 정보 리스트 가져오기
	 * @param vo
	 * @return map
	 */
	public HashMap getList(TravelVO vo){
		HashMap map = new HashMap();// 데이터를 가져오기 위해서, map에 X좌표 Y좌표 컨텐트id를 담음
		map.put("MX", vo.getMapx());
		map.put("MY", vo.getMapy());
		map.put("CON", vo.getContentid()); 
		ArrayList rList = tdao.getRoundList(map);//다음	맵 오른쪽(2Km 반경 데이터-호텔,음식점,관광지)
		ArrayList tList = tdao.getTravelList2(map);//	다음	관광(32, 39 이외의 좌표 정보)
		ArrayList hList = tdao.getHotelList(map);//	다음	숙박(32, 좌표정보들) 
		ArrayList fList = tdao.getFooodList(map);//		다음	음식(39, 좌표정보들)
		HashMap listMap = new HashMap();//	조회한 데이터를 map에 담아서 컨트롤러에 반환
		listMap.put("RLIST", rList);
		listMap.put("TLIST", tList);
		listMap.put("HTLIST", hList);
		listMap.put("FLIST", fList);
		return listMap;
	}
	
}
