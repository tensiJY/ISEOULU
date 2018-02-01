package com.seoul.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.seoul.DAO.BoardDAO;
import com.seoul.Service.BoardService;
import com.seoul.Util.FileUtil;
import com.seoul.Util.PageUtil;
import com.seoul.Util.SessionUtil;
import com.seoul.Util.StringUtil;
import com.seoul.VO.BoardVO;


@Controller
@RequestMapping("/Board")

public class BoardController {
	@Autowired
	public BoardService bService;
	
	public String	path = "D:\\SpringUpload";
	
	// 게시판에서는 한페이지에 10개씩 보여준다.
	public int listCount = 7;
	public static Logger logger = Logger.getLogger(BoardController.class);
	
	@RequestMapping("/BoardList")
	public ModelAndView boardList (@RequestParam(value="nowPage", defaultValue="1") int nowPage, HttpSession session) {
		logger.info("	request	Board/BoardList");
		int	total = bService.getTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, listCount);
		
     	ArrayList	list = bService.getBoardList(nowPage, pInfo);
		
		ModelAndView	mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("Board/BoardList");
		System.out.println("SESSION:"+session.getAttribute("UID"));
	    
		return mv;
	}
	

	/*
	 * 	검색 요청 처리 함수
	 */
	@RequestMapping("/BoardSearch")
	public ModelAndView boardSearch(BoardVO bVO, HttpSession session) {
		boolean	isTarget = StringUtil.isNull(bVO.getTarget());
		if(isTarget == true) {
			bVO.setTarget((String) session.getAttribute("target"));
			bVO.setWord((String) session.getAttribute("word"));
		}
		session.setAttribute("target", bVO.getTarget());
		session.setAttribute("word", bVO.getWord());
		
		PageUtil	pInfo = bService.getSearchTotal(bVO);
		ArrayList	list = bService.getSearch(bVO, pInfo);
		
		ModelAndView	mv = new ModelAndView();
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("Board/BoardSearch");
	
		return mv;
	}
	
	
	/** 
	 * 게시판정보 등록을 위해 등록화면 호출 함수 (서비스함수 호출 없이 게시판등록 화면만 호출)
	 * 	
	 * 	작성자	  소현
	 * 	작성일	  2017.11.14
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 	파라메타    없음
	 * 	반환값	   ModelAndView : 게시판등록 화면 
	 */
	@RequestMapping("WriteForm")
	public ModelAndView writeForm(@RequestParam(value="contentId", defaultValue="0") int contentId){
		ModelAndView mv = new ModelAndView();
		HashMap	map = new HashMap();
		map = bService.getContent(contentId);
		mv.addObject("CONTENT", map);
		mv.setViewName("Board/WriteForm");
		return mv;
	}
	

	/** 
	 * 게시판정보 등록을 실행하는 서비스호출 함수
	 * 	
	 * 	작성자	  소현
	 * 	작성일	  2017.11.14
	 * 
	 * 	파라메타    없음
	 * 	반환값	   ModelAndView : 게시판등록 화면 
	 */
	@RequestMapping("WriteProc")
	public ModelAndView writeProc(BoardVO bVO, HttpSession session) {
		boolean	isLogin = SessionUtil.isLogin(session);
		int		len = bVO.getFiles().length;
		ArrayList	fileInfoList = new ArrayList();
		for(int i = 0; i < len; i++) {
			String	name = bVO.getFiles()[i].getOriginalFilename();
			if(StringUtil.isNull(name)) {
				continue;
			}
			String saveName = FileUtil.upload(bVO.getFiles()[i], name, path);
			HashMap	map = new HashMap();
			map.put("oriName", bVO.getFiles()[i].getOriginalFilename());
			map.put("saveName", saveName);
			map.put("len", bVO.getFiles()[i].getSize());
			map.put("path", path);
			
			fileInfoList.add(map);
		}
		String	id = (String) session.getAttribute("UID");
		bVO.setWriter(id);
		bService.insertBoard(bVO, fileInfoList);
		//	뷰를 부른다.
		ModelAndView	mv = new ModelAndView();
		RedirectView	rv = new RedirectView("../Board/BoardList.do");
		mv.setView(rv);
		return mv;
	}
	
	//	답글 쓰기를 위한 폼 요청 처리 함수
	@RequestMapping("/AnWriteForm")
	public ModelAndView AnWriteForm(BoardVO bVO) {
		ModelAndView	mv = new ModelAndView();
		mv.addObject("oriNo", bVO.getOriNo());
		mv.addObject("nowPage", bVO.getNowPage());
		mv.addObject("kind",bVO.getKind());
		mv.setViewName("Board/AnWriteForm");
		return mv;
	}
	
    //	답글 쓰기 요청 처리 함수
	@RequestMapping("/AnWriteProc")
	public ModelAndView AnWriteProc(BoardVO bVO, HttpSession session) {
		int		len = bVO.getFiles().length;
		ArrayList	fileInfoList = new ArrayList();
		for(int i = 0; i < len; i++) {
			String	name = bVO.getFiles()[i].getOriginalFilename();
				if(StringUtil.isNull(name)) {
				continue;
			}
			String saveName = FileUtil.upload(bVO.getFiles()[i], name, path);
			HashMap	map = new HashMap();
			map.put("oriName", bVO.getFiles()[i].getOriginalFilename());
			map.put("saveName", saveName);
			map.put("len", bVO.getFiles()[i].getSize());
			map.put("path", path);
			
			fileInfoList.add(map);
		}			
		BoardVO	vo = bService.getOriInfo(bVO.getOriNo());
		int		newGroup = vo.getBgroup();
		int		newStep = vo.getBstep() + 1;
		int		newOrder = vo.getBorder() + 1;

		String	id = (String) session.getAttribute("UID");
		bVO.setWriter(id);
		bVO.setBgroup(newGroup);
		bVO.setBstep(newStep); 
		bVO.setBorder(newOrder);
		bService.anInsert(bVO,fileInfoList );
		RedirectView	rv = new RedirectView();
		rv.addStaticAttribute("nowPage", bVO.getNowPage());
		rv.addStaticAttribute("bgroup", bVO.getBgroup());
		rv.addStaticAttribute("oriNo", bVO.getOriNo());
		rv.addStaticAttribute("kind", bVO.getKind());
		rv.setUrl("../Board/BoardView.do");
		ModelAndView	mv = new ModelAndView();
		mv.setView(rv);
		return mv;
	}
	
	
//	상세보기 요청을 처리할 컨트롤러 함수
	@RequestMapping("/BoardView")
	public ModelAndView boardView(BoardVO bVO, HttpSession session) {
		String id = (String) session.getAttribute("UID");
		if (SessionUtil.isLogin(session) == true){
			boolean	isHit = bService.isHitNow(id, bVO.getOriNo());
			if(isHit == true) {
				bService.updateHit(bVO.getOriNo());
			} 
		}
		HashMap map1 = new HashMap(); 
		map1.put("oriNo", bVO.getOriNo());
		map1.put("target", (String) session.getAttribute("target"));
		map1.put("word", (String) session.getAttribute("word"));
		String kind = bVO.getKind();
		map1.put("kind", kind);
		HashMap	map = bService.getBoardView(bVO.getOriNo(),kind, map1);
		ModelAndView	mv = new ModelAndView();
		mv.addObject("MAP", map);
		mv.addObject("nowPage", bVO.getNowPage());
		mv.addObject("kind",kind);
		mv.setViewName("Board/BoardView");
		return mv;
	}
	
	/*
	 *	파일 다운로드 요청 처리함수 
	 */
	@RequestMapping("/FileDownload")
	public ModelAndView fileDownload(@RequestParam(value="fNo") int fNo) {
		BoardVO	bVO = bService.getDownloadInfo(fNo);
		File		downFile = new File(bVO.getPath() + "\\" + bVO.getSaveName());
		bService.updateDownloadCnt(fNo);
		return new ModelAndView("download", "downloadFile", downFile);
	}
	

	//	수정하기 폼 요청 처리 함수
	@RequestMapping("/ModifyForm")
	public ModelAndView modifyForm(BoardVO bVO) {
		BoardVO vo = bService.getModifyView(bVO.getOriNo());
		ModelAndView mv = new ModelAndView();
		mv.addObject("VIEW", vo);
		mv.addObject("nowPage", bVO.getNowPage());
		mv.addObject("kind",bVO.getKind());
		mv.setViewName("Board/ModifyForm");
		return mv;
	}
	
	//	수정하기 요청 처리함수
	@RequestMapping("/ModifyProc")
	public ModelAndView modifyProc(BoardVO bVO,HttpSession session) {	
		boolean	isLogin = SessionUtil.isLogin(session);
		if(!isLogin) {
			ModelAndView	mv = new ModelAndView();
			RedirectView	rv = new RedirectView("../Login/LoginForm.do");
			mv.setView(rv);
			return mv;
		}

		int		len = bVO.getFiles().length;
		
		ArrayList	fileInfoList = new ArrayList();
		for(int i = 0; i < len; i++) {
			String	name = bVO.getFiles()[i].getOriginalFilename();
			
			if(StringUtil.isNull(name)) {
				continue;
			}
			String saveName = FileUtil.upload(bVO.getFiles()[i], name, path);

			HashMap	map = new HashMap();
			map.put("oriName", bVO.getFiles()[i].getOriginalFilename());
			map.put("saveName", saveName);
			map.put("len", bVO.getFiles()[i].getSize());
			map.put("path", path);
			
			fileInfoList.add(map);
		}
		String	id = (String) session.getAttribute("UID");
		bVO.setWriter(id);;
		int		count =bService.updateBoard(bVO, fileInfoList);
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", bVO.getNowPage());
		mv.addObject("oriNo", bVO.getOriNo());
		mv.addObject("kind", bVO.getKind());
		mv.addObject("count", count);
		mv.addObject("from", "update");
		mv.setViewName("Board/ImsiView");
		return mv;
	}
	
	//	삭제 요청 처리함수
	@RequestMapping("/DeleteProc")
	public ModelAndView deleteProc(BoardVO bVO) {
		int		count = bService.deleteBoard(bVO);
		
		ModelAndView		mv = new ModelAndView();
		mv.addObject("nowPage", bVO.getNowPage());
		mv.addObject("oriNo", bVO.getOriNo());
		mv.addObject("count", count);
		mv.addObject("from", "delete");
		mv.setViewName("Board/ImsiView");
		return mv;
	}
	
}