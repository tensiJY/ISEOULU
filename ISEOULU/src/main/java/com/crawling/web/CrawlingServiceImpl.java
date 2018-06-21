package com.crawling.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class CrawlingServiceImpl implements CrawlingService{
	
	@Inject
	private CrawlingDAO cdao;
	
	@Override
	public void getJob(CrawlingVO cvo) throws Exception {
		String keyWord = cvo.getKeyWord();
		int startPage = cvo.getStartPage();
		CrawlingJob cj = new CrawlingJob(keyWord, startPage);
		ArrayList<CrawlingVO>list = cj.getJobList();
		/*for(CrawlingVO c : list){
			System.out.println(c.getJtitle() + " " + c.getJbody() + " " + c.getJlink());
		}*/
		cdao.insertJob(list);		
	}//
	
	@Override
	public int getTotalCnt() throws Exception {
		return cdao.getTotalCnt();
	}//
	
	@Override
	public List<CrawlingVO> getList(Map<String, Integer> map) {
		
		return cdao.getList(map);
	}
}// end
