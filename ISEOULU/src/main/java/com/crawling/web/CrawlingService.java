package com.crawling.web;

import java.util.List;
import java.util.Map;

public interface CrawlingService {

	public void getJob(CrawlingVO cvo) throws Exception;

	public int getTotalCnt() throws Exception;

	public List<CrawlingVO> getList(Map<String, Integer> map);

}
