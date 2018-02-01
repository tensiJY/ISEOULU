package com.seoul.Controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seoul.Service.MainService;
import com.seoul.VO.MainVO;


@Controller
@RequestMapping(value="/Main")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MainService ms;
	
	/**
	 * 메인화면
	 * jQuery-ui-tab에 리스트 노출 travel, hotel, food
	 * @param sgCode
	 * @param model
	 * @return MAP, sgCode
	 */
	@RequestMapping(value="/Main", method={RequestMethod.GET, RequestMethod.POST})
	public String main(@RequestParam(value="sgCode", defaultValue="0")int sgCode, Model model,
			@RequestParam(value="LOGINVIEW", defaultValue="FALSE")String loginview){
		
		Map<String, Object> map = ms.getHotPlace(sgCode); 
		
		model.addAttribute("MAP", map);
		model.addAttribute("sgCode", sgCode);
		
		if(loginview.equals("TRUE")){
						
			model.addAttribute("LOGINVIEW", "TRUE");
			
		} else{
			
			model.addAttribute("LOGINVIEW", "FALSE");
		}
		
		return "Main/index";
	}
	
	
	/**
	 * main이 로딩될때 ajax로 호출 (태그클라우드와 차트를 그릴 - travel, hotel, food 정보)
	 * @param sgCode
	 * @return map
	 */
	@RequestMapping(value="/ajaxList")
	public @ResponseBody Map<String, Object> ajaxList(@RequestBody 
			@RequestParam(value="sgCode") int sgCode){
		Map<String, Object> map = ms.getHotPlace(sgCode);
		return map;
	}
	
	/**
	 * list->view 상세정보  
	 * @param map
	 * @return resultMap
	 */
	@RequestMapping(value="/detailView")
	public @ResponseBody Map<String, Object> detailView(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = ms.getDetailView(map); 
		logger.info(resultMap.toString());
		return resultMap;
	}
	
	/**
	 * 
	 * @param title
	 * @return MainVO
	 */
	@RequestMapping(value="/CloudAjax")
	public @ResponseBody MainVO cloudAjax(@RequestParam(value="title") String title){
		MainVO mvo = ms.getOverView(title);
		return mvo;
	}
}
