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

import com.seoul.Service.FoodService;
import com.seoul.Util.PageUtil;

import com.seoul.VO.FoodVO;

@Controller
@RequestMapping("/Food")
public class FoodController {
	private static Logger logger = 
			Logger.getLogger(FoodController.class);
	
	@Autowired
	private FoodService fservice;
	
	/**
	 * ���� ����Ʈ
	 * @param nowPage
	 * @param sgCode
	 * @return
	 */
	@RequestMapping(value="/List", method=RequestMethod.GET)
	public ModelAndView FoodList(@RequestParam(value="nowPage", defaultValue="1") int nowPage, 
			@RequestParam(value="sgCode", defaultValue="1") int sgCode){
			
		ModelAndView mav = new ModelAndView();
		
		int totalCount = fservice.getTotalCount(sgCode);
		if(totalCount ==0){
			mav.addObject("sgCode", sgCode);
			mav.setViewName("Food/FoodList");
			return mav;
		}
		
		PageUtil pInfo = new PageUtil(nowPage, totalCount, 4);
		ArrayList<FoodVO>fList = fservice.getFoodList(pInfo, sgCode);		
				
		mav.addObject("sgCode", sgCode);
		mav.addObject("nowPage", nowPage);
		mav.addObject("PINFO", pInfo);
		mav.addObject("FLIST", fList);
		mav.setViewName("Food/FoodList");
		return mav;
	}
	
	
	/**
	 * ���� �󼼺��� ��
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/View", method=RequestMethod.GET)
	public ModelAndView foodView(FoodVO vo){
		
		ModelAndView mav = new ModelAndView();
		
		boolean check = fservice.getFoodViewCheck(vo.getContentid());
	
		if( !check ){
			
			mav.addObject("sgCode", vo.getSgCode());//	ã�� ������ ������ '����'�� ���� ������ ���ٴ� ���� �˸��� �ٸ� �並 �����ְ�, �ӽú� -> ����Ʈ (�Ķ���� �̵�)
			mav.addObject("nowPage", vo.getNowPage());//	siguncode=23, contentid=1865717,  title = ��ȸ�ѿ�ü���
			mav.addObject("TITLE", vo.getTitle());
			mav.setViewName("Food/FoodView");
			return mav;
		}
		
		FoodVO fvo = fservice.getFoodView(vo.getContentid());
		HashMap map = fservice.getList(vo);//	2km �α���������, ��Ŀ�� ���� ������ �����´�.
		fvo.setSgCode(vo.getSgCode());	   //	�Ķ���� ������(�ñ��������ڵ� + ����������)
		fvo.setNowPage(vo.getNowPage());
		mav.addObject("FVO", fvo);		   //	detailView���� ���� ������			
		mav.addObject("RLIST", map.get("RLIST"));  // 2Km �ݰ�����(����, ������, ������)
		mav.addObject("TLIST", map.get("TLIST"));  // detailView���� ��Ŀ�� ��Ʈ���ϱ� ����
		mav.addObject("HTLIST", map.get("HTLIST"));// ȣ��, ������, ������  ��ǥ����
		mav.addObject("FLIST", map.get("FLIST"));  // contentypeid �и��ؼ�  
		mav.setViewName("Food/FoodView");		   // detailView�� ������.
		return mav;
	}
	
	
}

