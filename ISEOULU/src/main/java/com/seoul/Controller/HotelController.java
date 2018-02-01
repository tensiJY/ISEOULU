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
	 * ȣ�� ����Ʈ
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
	 * ȣ�� �󼼺��� ��
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/View", method=RequestMethod.GET)
	public ModelAndView hotelView(HotelVO vo){
		
		ModelAndView mav = new ModelAndView();
		
		boolean check = hservice.getHotelViewCheck(vo.getContentid());
	
		if( !check ){
			
			mav.addObject("sgCode", vo.getSgCode());//	ã�� ������ ������ '����'�� ���� ������ ���ٴ� ���� �˸��� �ٸ� �並 �����ְ�, �ӽú� -> ����Ʈ (�Ķ���� �̵�)
			mav.addObject("nowPage", vo.getNowPage());//	siguncode=23, contentid=1865717,  title = ��ȸ�ѿ�ü���
			mav.addObject("TITLE", vo.getTitle());
			mav.setViewName("Hotel/ImsiView");
			return mav;
		}
		
		HotelVO hvo = hservice.getHotelView(vo.getContentid());
		HashMap map = hservice.getList(vo);//	2km �α���������, ��Ŀ�� ���� ������ �����´�.
		hvo.setSgCode(vo.getSgCode());	   //	�Ķ���� ������(�ñ��������ڵ� + ����������)
		hvo.setNowPage(vo.getNowPage());
		mav.addObject("HVO", hvo);		   //	detailView���� ���� ������			
		mav.addObject("RLIST", map.get("RLIST"));  // 2Km �ݰ�����(ȣ��, ������, ������)
		mav.addObject("TLIST", map.get("TLIST"));  // detailView���� ��Ŀ�� ��Ʈ���ϱ� ����
		mav.addObject("HTLIST", map.get("HTLIST"));// ȣ��, ������, ������  ��ǥ����
		mav.addObject("FLIST", map.get("FLIST"));  // contentypeid �и��ؼ�  
		mav.setViewName("Hotel/HotelView");		   // detailView�� ������.
		return mav;
	}
	
	
}

