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

 	.�� class �̰� # id�̴�..
	class=numcase �̸� .numcase
  
 	��� �м�
 		1. ���������� �������
		    .numcase �� ��� => "1-114 / �� 2,262��"
		    ���Ⱑ ������ ���ۺ��� ������������ ���´�. �׸��� ������ �� ������ �Ǽ�..
		    ==> - ������ ¥����. ���� / <- �̰� �ձ��� ©��� �� �������� ���´�..
		    String calPage = "1-114 / �� 2,262��";
			int pre = calPage.indexOf("-");
			int sub = calPage.indexOf(" /");
			String realPage = calPage.substring(pre+1, sub);
			System.out.println( Integer.parseInt(realPage));
 *  
 *  	2. ȸ���� ������ �м�����.
 *  		.riin �� ��带 ã��, array�θ����.


 */
public class CrawlingJob {
	/**
	 * preURL �� search
	 * 		"http://www.saramin.co.kr/zf_user/search/recruit/page/1?searchword=%EA%B0%9C%EB%B0%9C%EC%9E%90&company_cd=&loc_mcd=&loc_bcd=&cat_bcd=&exp_code=&period=&company=&employ_cnt=&rSearchword=&hSearchword=&hInclude=&hExcept=&searchType=search&order=relation&correctionSearch=";
	 * 		page/1	page/2	������ ������ �ȴ�.
	 * searchWord �� �˻� Ű����
	 * 		Ű���� / ��ĭ�� �鶧�� '+'�� �� ==>>>  ��+������ // ��������
	 * subURL �� �˻� �����ε�..
	 * 
	 * �� preURL + search + sbuURL ���ڿ� �����Ͽ� ó�� �˻� �ǽ�
	 *  
	 */
	private  String preURL = "http://www.saramin.co.kr/zf_user/search/recruit/page/";
	private  int strPage = 1;
	private  String search = "?searchword=";
	private  String word = "";
	private  String subURL = "&company_cd=&loc_mcd=&loc_bcd=&cat_bcd=&exp_code=&period=&company=&employ_cnt=&rSearchword=&hSearchword=&hInclude=&hExcept=&searchType=search&order=relation&correctionSearch=";
	private  int lastPage = 0;
			
	//	�⺻ ����			
	//	String articleURL = "http://www.saramin.co.kr/zf_user/search/recruit/page/1?searchword=%EA%B0%9C%EB%B0%9C%EC%9E%90&company_cd=&loc_mcd=&loc_bcd=&cat_bcd=&exp_code=&period=&company=&employ_cnt=&rSearchword=&hSearchword=&hInclude=&hExcept=&searchType=search&order=relation&correctionSearch=";	
	//	Document doc = Jsoup.connect(articleURL).get();     
	//	Elements ele = doc.select(".company_inbox .riin");
	//	System.out.println(ele.toString());	
	
	public CrawlingJob(String keyWord, int startPage) throws IOException{
		this.word = keyWord;
		String url = preURL + strPage + search + word + subURL;
		Document doc = Jsoup.connect(url).get();
		//�� ������������ �м��Ѵ�.	
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
				//	element �� ������. �ϳ��� ������ ���� �� �ִ�.
				//	rec_idx=32934082 <--- �Խñ� ��ȣ��
				//	http://www.saramin.co.kr/zf_user/jobs/relay/view?view_type=search&rec_idx=���� ���ָ� �ǿ�..
				
			String url = preURL + i + search + word + subURL;
			Document doc = Jsoup.connect(url).get();
			Elements jobs = doc.select(".riin");
			
			for(Element j : jobs){
				String jtitle = j.select(".tit").text();	// ȸ���̸�
				String jbody = j.select(".txt").text();		// �ٵ�
				
				String hh = j.select(".txt").toString();	// �м� ����...
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
				Thread.sleep(2000);	// ������ ���� �ָ� �ȵǴ�....^^
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//for end;
		return list;
	}//method end;
	
	

}// class end


