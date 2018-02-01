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

import com.seoul.Service.TravelService;
import com.seoul.Util.PageUtil;

import com.seoul.VO.TravelVO;

@Controller
@RequestMapping("/Travel")
public class TravelController {
	private static Logger logger = 
			Logger.getLogger(TravelController.class);
	
	@Autowired
	private TravelService tservice;
	
	/**
	 * 관광 리스트
	 * @param nowPage
	 * @param sgCode
	 * @return
	 */
	@RequestMapping(value="/List", method=RequestMethod.GET)
	public ModelAndView travelList(@RequestParam(value="nowPage", defaultValue="1") int nowPage, 
			@RequestParam(value="sgCode", defaultValue="1") int sgCode){
			
		ModelAndView mav = new ModelAndView();
		
		int totalCount = tservice.getTotalCount(sgCode);
		if(totalCount ==0){
			mav.addObject("sgCode", sgCode);
			mav.setViewName("Travel/TravelList");
			return mav;
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 4);
		ArrayList<TravelVO>tList = tservice.getTravelList(pInfo, sgCode);		
				
		mav.addObject("sgCode", sgCode);
		mav.addObject("nowPage", nowPage);
		mav.addObject("PINFO", pInfo);
		mav.addObject("TLIST", tList);
		mav.setViewName("Travel/TravelList");
		return mav;
	}
	
	/**
	 * 관광상세보기 뷰
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/View", method=RequestMethod.GET)
	public ModelAndView TravelView(TravelVO vo){
		
		ModelAndView mav = new ModelAndView();
		
		boolean check = tservice.getTravelViewCheck(vo.getContentid());
	
		if( !check ){
			
			mav.addObject("sgCode", vo.getSgCode());
			mav.addObject("nowPage", vo.getNowPage());
			mav.addObject("TITLE", vo.getTitle());
			mav.setViewName("Travel/ImsiView");
			return mav;
			
		}
		
		TravelVO tvo = tservice.getTravelView(vo.getContentid());
		HashMap map = tservice.getList(vo);
		tvo.setSgCode(vo.getSgCode());	   //	파라미터 릴레이(시군구지역코드 + 현재페이지)
		tvo.setNowPage(vo.getNowPage());
		mav.addObject("TVO", tvo);		   //	detailView에서 사용될 상세정보			
		mav.addObject("RLIST", map.get("RLIST"));   // 2Km 반경정보(호텔, 음식점, 관광지)
		mav.addObject("TLIST", map.get("TLIST"));  //detailView에서 마커를 컨트롤하기 위해
		mav.addObject("HTLIST", map.get("HTLIST"));// 호텔, 음식점, 관광지  좌표들을
		mav.addObject("FLIST", map.get("FLIST"));  // contentypeid 분리해서 
		mav.setViewName("Travel/TravelView");		   // detailView에 보낸다.
		return mav;
	}
	/*
	 * 시군구 구별 처리함수
	 * 17.11.
	 */
//	@RequestMapping("TravelSearch")
//	public ModelAndView TravelSearch()
//	//파라메터 받고
//	
//	//검색
//	/ArrayList Totallist = tservice.TravelSearch();
//	
//	//결과를 본다.
//	ModelAndView	mv = new ModelAndView();
//	mav.addObject("TRAVELSEARCH", TravelSearch();
//	mav.setViewName("Common/commonheader");
//	return mav;
//	
	
}