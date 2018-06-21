package com.crawling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crawling.web.CrawlingService;
import com.crawling.web.CrawlingVO;
import com.crawling.web.PageUtil;

@Controller
@RequestMapping(value="/Crawling")
public class CrawlingController {
	
	@Inject
	private CrawlingService cs;
	
	@RequestMapping(value="/Main")
	public String main(Model model, @RequestParam(value="nowPage", defaultValue="1") int nowPage) throws Exception{
		
			
		PageUtil pageInfo = new PageUtil(nowPage, cs.getTotalCnt() , 10);
		
		int start = pageInfo.getStart();
		int end = pageInfo.getEnd();
		
		Map<String, Integer>map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<CrawlingVO> list = cs.getList(map);
		
		
		model.addAttribute("CrawlingVO", new CrawlingVO());
		model.addAttribute("cvo", list);
		model.addAttribute("pageInfo", pageInfo);
		
		return "Crawling/Main";
	}//
	
	@RequestMapping(value="/getJob")
	public String search(@ModelAttribute("cvo")CrawlingVO cvo, RedirectAttributes rttr) throws Exception{
		cs.getJob(cvo);
		rttr.addFlashAttribute("MSG", "검색 및 등록 완료");
		return "redirect:./Main.do";
	}//
	
	
	
}//
