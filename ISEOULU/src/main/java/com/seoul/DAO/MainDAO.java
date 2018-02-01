package com.seoul.DAO;




import java.util.ArrayList;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.VO.MainVO;


public class MainDAO extends SqlSessionDaoSupport{
	private static final Logger logger = 
			LoggerFactory.getLogger(MainDAO.class);
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public ArrayList getHotPlace(Map map){
		return (ArrayList)sst.selectList("main.dynamicSQL", map);
	}
	
	
	public int getDetailCheck(Map map){
		return (Integer)sst.selectOne("main.detailCheck", map);
	}
	
	public MainVO getDetailView(Map map){
		return sst.selectOne("main.detailView", map);
	}
	
	

	public Map<String, Object> getContentid(String title) {
		return sst.selectOne("main.getContentid", title);
	}


	public MainVO getOverView(Map<String, Object> map) {
		return sst.selectOne("main.getOverView", map);
	}

}
