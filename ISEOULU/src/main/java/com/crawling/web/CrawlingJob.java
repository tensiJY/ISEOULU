package com.crawling.web;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
https://jsoup.org/
http://derveljunit.tistory.com/253
http://w3devlabs.net/wp/?p=16946
http://derveljunit.tistory.com/253

 	.은 class 이고 # id이다..
	class=numcase 이면 .numcase
  
 	노드 분석
 		1. 총페이지를 계산하자
		    .numcase 의 결과 => "1-114 / 총 2,262건"
		    여기가 페이지 시작부터 종료페이지가 나온다. 그리고 옆에는 총 데이터 건수..
		    ==> - 단위로 짜른다. 또한 / <- 이거 앞까지 짤라야 총 페이지가 나온다..
		    String calPage = "1-114 / 총 2,262건";
			int pre = calPage.indexOf("-");
			int sub = calPage.indexOf(" /");
			String realPage = calPage.substring(pre+1, sub);
			System.out.println( Integer.parseInt(realPage));
 *  
 *  	2. 회사의 정보를 분석하자.
 *  		.riin 로 노드를 찾고, array로만든다.


 */
public class CrawlingJob {
	/**
	 * preURL 은 search
	 * 		"http://www.saramin.co.kr/zf_user/search/recruit/page/1?searchword=%EA%B0%9C%EB%B0%9C%EC%9E%90&company_cd=&loc_mcd=&loc_bcd=&cat_bcd=&exp_code=&period=&company=&employ_cnt=&rSearchword=&hSearchword=&hInclude=&hExcept=&searchType=search&order=relation&correctionSearch=";
	 * 		page/1	page/2	페이지 순으로 된다.
	 * searchWord 는 검색 키워드
	 * 		키워드 / 한칸을 띌때면 '+'로 즉 ==>>>  웹+개발자 // 웹개발자
	 * subURL 은 검색 조건인데..
	 * 
	 * 즉 preURL + search + sbuURL 문자열 조합하여 처음 검색 실시
	 *  
	 */
	private  String preURL = "http://www.saramin.co.kr/zf_user/search/recruit/page/";
	private  int strPage = 1;
	private  String search = "?searchword=";
	private  String word = "";
	private  String subURL = "&company_cd=&loc_mcd=&loc_bcd=&cat_bcd=&exp_code=&period=&company=&employ_cnt=&rSearchword=&hSearchword=&hInclude=&hExcept=&searchType=search&order=relation&correctionSearch=";
	private  int lastPage = 0;
			
	//	기본 사용법			
	//	String articleURL = "http://www.saramin.co.kr/zf_user/search/recruit/page/1?searchword=%EA%B0%9C%EB%B0%9C%EC%9E%90&company_cd=&loc_mcd=&loc_bcd=&cat_bcd=&exp_code=&period=&company=&employ_cnt=&rSearchword=&hSearchword=&hInclude=&hExcept=&searchType=search&order=relation&correctionSearch=";	
	//	Document doc = Jsoup.connect(articleURL).get();     
	//	Elements ele = doc.select(".company_inbox .riin");
	//	System.out.println(ele.toString());	
	
	public CrawlingJob(String keyWord, int startPage) throws IOException{
		this.word = keyWord;
		String url = preURL + strPage + search + word + subURL;
		Document doc = Jsoup.connect(url).get();
		//총 몇페이지인지 분석한다.	
		/*Elements page = doc.select(".numcase");
		String calPage = page.toString();
		int pre = calPage.indexOf("-");
		int sub = calPage.indexOf(" /");
		String realPage = calPage.substring(pre+1, sub);
		this.lastPage = Integer.parseInt(realPage);*/
		
		this.strPage = startPage;
		lastPage = strPage+1;
	}//
	
	
	
	public ArrayList<CrawlingVO> getJobList() throws IOException{
		ArrayList<CrawlingVO> list = new ArrayList<CrawlingVO>();
		
		for(int i=strPage; i<=lastPage; i++){
			///////////////////////////////////////////////////////////////////////////////////
				//	element 로 묶으면. 하나씩 조건을 뽑을 수 있다.
				//	rec_idx=32934082 <--- 게시글 번호임
				//	http://www.saramin.co.kr/zf_user/jobs/relay/view?view_type=search&rec_idx=숫자 해주면 되요..
				
			String url = preURL + i + search + word + subURL;
			Document doc = Jsoup.connect(url).get();
			Elements jobs = doc.select(".riin");
			
			for(Element j : jobs){
				String jtitle = j.select(".tit").text();	// 회사이름
				String jbody = j.select(".txt").text();		// 바디
				
				String hh = j.select(".txt").toString();	// 분석 시작...
				int a = hh.indexOf("rec_idx=");
				String prea = hh.substring(a+8);
				int b = prea.indexOf("&");
				String preb = prea.substring(0, b);
				String jlink = "http://www.saramin.co.kr/zf_user/jobs/relay/view?view_type=search&rec_idx=" + preb;
				list.add(new CrawlingVO()
						.setJtitle(jtitle)
						.setJbody(jbody)
						.setJlink(jlink));
			}//
			
			try {
				Thread.sleep(2000);	// 서버에 무리 주면 안되니....^^
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//for end;
		return list;
	}//method end;
	
	

}// class end


