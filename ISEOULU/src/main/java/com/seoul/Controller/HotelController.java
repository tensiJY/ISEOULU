package com.seoul.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.seoul.Service.HotelService;
import com.seoul.Util.PageUtil;

import com.seoul.VO.HotelVO;

@Controller
@RequestMapping("/Hotel")
public class HotelController {
	private static Logger logger = 
			Logger.getLogger(HotelController.class);
	
	@Autowired
	private HotelService hservice;
	
	/**
	 * 호텔 리스트
	 * @param nowPage
	 * @param sgCode
	 * @return
	 */
	@RequestMapping(value="/List", method=RequestMethod.GET)
	public ModelAndView hotelList(@RequestParam(value="nowPage", defaultValue="1") int nowPage, 
			@RequestParam(value="sgCode", defaultValue="1") int sgCode){
			
		ModelAndView mav = new ModelAndView();
		
		int totalCount = hservice.getTotalCount(sgCode);
		if(totalCount ==0){
			mav.addObject("sgCode", sgCode);
			mav.setViewName("Hotel/HotelList");
			return mav;
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 4);
		ArrayList<HotelVO>hList = hservice.getHotelList(pInfo, sgCode);		
				
		mav.addObject("sgCode", sgCode);
		mav.addObject("nowPage", nowPage);
		mav.addObject("PINFO", pInfo);
		mav.addObject("HLIST", hList);
		mav.setViewName("Hotel/HotelList");
		return mav;
	}
	
	
	/**
	 * 호텔 상세보기 뷰
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/View", method=RequestMethod.GET)
	public ModelAndView hotelView(HotelVO vo){
		
		ModelAndView mav = new ModelAndView();
		
		boolean check = hservice.getHotelViewCheck(vo.getContentid());
	
		if( !check ){
			
			mav.addObject("sgCode", vo.getSgCode());//	찾는 정보가 없을때 '제목'과 관련 정보가 없다는 것을 알리며 다른 뷰를 보여주고, 임시뷰 -> 리스트 (파라메터 이동)
			mav.addObject("nowPage", vo.getNowPage());//	siguncode=23, contentid=1865717,  title = 가회한옥체험관
			mav.addObject("TITLE", vo.getTitle());
			mav.setViewName("Hotel/ImsiView");
			return mav;
		}
		
		HotelVO hvo = hservice.getHotelView(vo.getContentid());
		HashMap map = hservice.getList(vo);//	2km 인근지역정보, 마커를 찍을 정보를 가져온다.
		hvo.setSgCode(vo.getSgCode());	   //	파라미터 릴레이(시군구지역코드 + 현재페이지)
		hvo.setNowPage(vo.getNowPage());
		mav.addObject("HVO", hvo);		   //	detailView에서 사용될 상세정보			
		mav.addObject("RLIST", map.get("RLIST"));  // 2Km 반경정보(호텔, 음식점, 관광지)
		mav.addObject("TLIST", map.get("TLIST"));  // detailView에서 마커를 컨트롤하기 위해
		mav.addObject("HTLIST", map.get("HTLIST"));// 호텔, 음식점, 관광지  좌표들을
		mav.addObject("FLIST", map.get("FLIST"));  // contentypeid 분리해서  
		mav.setViewName("Hotel/HotelView");		   // detailView에 보낸다.
		return mav;
	}
	
	
}

