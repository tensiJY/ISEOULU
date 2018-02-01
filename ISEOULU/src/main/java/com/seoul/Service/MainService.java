package com.seoul.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.DAO.MainDAO;
import com.seoul.VO.MainVO;

public class MainService {
	private static final Logger logger = LoggerFactory.getLogger(MainService.class);
	
	@Autowired
	private MainDAO mdao;
	
	/**
	 * 사람들이 많이 찾는 곳 리스트
	 * @param sgCode
	 * @return map
	 */
	public Map<String, Object> getHotPlace(int sgCode){
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		searchMap.put("SGCODE", sgCode);
		searchMap.put("TYPE", "TRAVEL");
		List<MainVO> tlist = mdao.getHotPlace(searchMap);
		
		searchMap.put("TYPE", "HOTEL");
		List<MainVO> hlist = mdao.getHotPlace(searchMap);
		
		searchMap.put("TYPE", "FOOD");
		List<MainVO> flist = mdao.getHotPlace(searchMap);
		
		searchMap.put("TYPE", "ALL");
		List<MainVO> alist = mdao.getHotPlace(searchMap);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TLIST", tlist);
		map.put("HLIST", hlist);
		map.put("FLIST", flist);
		map.put("ALIST", alist);		
		return map;
	}
	
	public Map<String, Object> getDetailView(Map map){
		Map<String, Object> resultMap = new HashMap<String, Object>(); 
		int check = mdao.getDetailCheck(map);		
		
		if(check==0){
			resultMap.put("RESULT", "FALSE");
			return resultMap;
		}
		else{
			resultMap.put("RESULT", "SUCCESS");
			MainVO mvo = mdao.getDetailView(map);
			resultMap.put("MAIN", mvo);
			return resultMap;
		}
		
	}
	
	public MainVO getOverView(String title){
		Map<String, Object> map = mdao.getContentid(title);
		MainVO mvo = mdao.getOverView(map);
		return mvo;
	}
}
