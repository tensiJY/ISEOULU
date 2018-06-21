package com.crawling.web;

import java.util.List;
import java.util.Map;

public interface CrawlingDAO {

	public void insertJob(List<CrawlingVO> list) throws Exception;

	public int getTotalCnt() throws Exception;

	public List<CrawlingVO> getList( Map<String, Integer> map);
	
}
