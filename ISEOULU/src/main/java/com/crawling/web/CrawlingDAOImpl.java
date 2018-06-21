package com.crawling.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CrawlingDAOImpl implements CrawlingDAO{
	
	private String namespace = "CrawlingJob";
	
	@Inject
	private SqlSession sql;

	@Override
	public void insertJob(List<CrawlingVO> list) throws Exception {
		for(int i=0; i<list.size(); i++){
			sql.insert(namespace+".insertJob", list.get(i));
			Thread.sleep(1000);
		}//
			
	}//
	
	@Override
	public int getTotalCnt() throws Exception {
		return sql.selectOne(namespace+".totalCnt");
	}
	
	@Override
	public List<CrawlingVO> getList(Map<String, Integer> map) {
		
		List<CrawlingVO> list = sql.selectList(namespace+".getJobList", map);
		return list;
	}
	
}//


