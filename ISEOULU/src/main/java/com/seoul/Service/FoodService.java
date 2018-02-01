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
	 * 총 데이터 갯수를 알아내는 함수
	 * @param sgCode
	 * @return
	 */
	public int getTotalCount(int sgCode){
		return fdao.getFoodCount(sgCode);
	}
	
	
	/**
	 * 음식리스트 가져오는 함수	- 페이징처리(LIST)
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
	 * 음식상세보기 전 데이터가 있는 지 확인하는 함수
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
	 * 음식 상세 보기정보와 숙박readcount 증가
	 * @param contentid
	 * @return HotelVO
	 */
	public FoodVO getFoodView(int contentid){
		
		return fdao.getFoodView(contentid);
	}
	
	/**
	 * 음식 인근지역 정보 리스트 가져오기 
	 * @param vo
	 * @return map
	 */
	public HashMap getList(FoodVO vo){
		HashMap map = new HashMap();// 데이터를 가져오기 위해서, map에 X좌표 Y좌표 컨텐트id를 담음
		map.put("MX", vo.getMapx());
		map.put("MY", vo.getMapy());
		map.put("CON", vo.getContentid()); 
		ArrayList rList = fdao.getRoundList(map); //	다음	맵 오른쪽(2Km 반경 데이터-호텔,음식점,관광지)
		ArrayList tList = fdao.getTravlList(map); //	다음	관광(32, 39 이외의 좌표 정보)
		ArrayList hList = fdao.getHotelList(map);//	다음	숙박(32, 좌표정보들) 
		ArrayList fList = fdao.getFooodList(map);//		다음	음식(39, 좌표정보들)
		HashMap listMap = new HashMap();	//	조회한 데이터를 map에 담아서 컨트롤러에 반환
		listMap.put("RLIST", rList);
		listMap.put("TLIST", tList);
		listMap.put("HTLIST", hList);
		listMap.put("FLIST", fList);
		return listMap;
	}
	
}
