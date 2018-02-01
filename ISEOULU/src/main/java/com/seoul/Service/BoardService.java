package com.seoul.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.DAO.BoardDAO;
import com.seoul.Util.PageUtil;
import com.seoul.VO.BoardVO;

/*
 * 게시판 관리  Service(비지니스로직) 클래스
 */
public class BoardService {
	
	//데이터베이스 처리를 위해 DAO 클래스가 가 필요해서  <bean> 처리 해놓았으므로
	//  DI 기법으로 불러오면 됨
	@Autowired
	public 	BoardDAO		bDAO;
	

	public int getTotal(){
		int total = bDAO.getTotal();
		return total;
	}
	
	/** 
	 * 게시판 목록을 조회를 처리하기 위한 서비스 함수
	 * 	
	 * 	작성자	  
	 * 	작성일	  2017.11.09
	 * 
	 * 	파라메타    int nowPage:현재페이지, PageUtil pInfo : 페이지정보
	 * 	반환값	   ArrayList : 게시판목록 SQL수행 결과 목록
	 */
	public ArrayList getBoardList(int nowPage, PageUtil pInfo) {
		
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		
		int	end = start + (pInfo.listCount - 1);
		
		HashMap		map = new HashMap();
		map.put("start", start);
		map.put("end", end);	
		ArrayList	list = bDAO.getBoardList(map);
		return list;
		
	}
	
	//	검색 처리 서비스 함수
	public ArrayList	boardSearch(BoardVO bVO) {
		HashMap	map = new HashMap();
		map.put("target", bVO.getTarget());
		map.put("word", bVO.getWord());
		
		ArrayList	list = bDAO.boardSearch(map);
		return list;
	}
	
	/** 
	 * 게시판정보 등록을 처리하기 위한 서비스 함수
	 * 	
	 * 	작성자	  
	 * 	작성일	  2017.11.14
	 * 
	 * 	파라메타    BoardVO bvo : 등록을 위해 화면에서 입력 받은 게시물 정보
	 * 	반환값	   없음
	 */

	/*
	 * 	게시물 등록 처리 함수
	 */
	public void insertBoard(BoardVO bVO, ArrayList list) {
		bDAO.insertBoard(bVO);
		int	size = list.size();		//	첨부 파일의 개수
		for(int i = 0; i < size; i++) {
			HashMap map = (HashMap) list.get(i);
			map.put("oriNo", bVO.getNo());
			bDAO.insertFileInfo(map);
		}
		
	}
	
	//	원글의 정보를 알아내는 서비스 함수
	public BoardVO getOriInfo(int oriNo) {
		BoardVO 	vo = bDAO.getBoardView(oriNo);
		return vo;
	}
	
	//	답글 입력 처리를 위한 함수
	public void anInsert(BoardVO vo,ArrayList list) {
		
		HashMap	map = new HashMap();
		map.put("bgroup", vo.getBgroup());
		map.put("border", vo.getBorder());
		bDAO.updateOrder(map);
		bDAO.insertAn(vo);
   		int	size = list.size();		
		for(int i = 0; i < size; i++) {
			HashMap fmap = (HashMap) list.get(i);
			fmap.put("oriNo", vo.getNo());
			bDAO.insertFileInfo(fmap);
		}
			
	}
	
	/*
	 * 	상세보기 관련 데이터 검색을 위한 서비스 함수
	 */
	public HashMap getBoardView(int oriNo, String kind, HashMap map1) {
		BoardVO	vo = bDAO.getBoardView(oriNo);
		ArrayList		flist = bDAO.getUploadFile(oriNo);
		int		group = bDAO.getGroup(oriNo);
		map1.put("bgroup", group);
		HashMap		map = bDAO.getPreNext(group, kind, map1);
		HashMap		rMap = new HashMap();
		ArrayList		list = bDAO.getAnList(group);
		rMap.put("VIEW", vo);
		rMap.put("LIST", list);
		rMap.put("FILE", flist);
		rMap.put("PRENEXT", map);
		
		return rMap;
	}
	
	//	조회수 증가 여부를 판단할 함수
	public boolean isHitNow(String id, int no) {
		HashMap	map = bDAO.getHitNO(id);
		if(map == null || map.size() == 0) {
			HashMap temp = new HashMap();
			temp.put("user", id);
			temp.put("no", "#"+ no + "#");
			bDAO.updateHitNo("board.insertHitNO", temp);	
			return true;
		}
		else {
			String	ano = (String) map.get("no");
			int	test = ano.indexOf("#" + no + "#");
			if(test == -1) {
				HashMap	temp = new HashMap();
				temp.put("user", id);
				temp.put("no", ano + "#" + no + "#");
				bDAO.updateHitNo("board.updateHitNO", temp);
				return true;
			}
			else {
				return false;
			}
		}
	}
	//	실제 조회수 증가를 처리할 함수
	public void updateHit(int oriNo) {
		bDAO.updateHit(oriNo);
	}
	
	
		
	/*
	 * 	다운로드 파일 정보 처리를 위한 함수
	 */
	public BoardVO getDownloadInfo(int fNo) {
		return bDAO.getDownloadInfo(fNo);
	}	
	
	
	/*
	 * 	검색 개수 구하기 처리 함수
	 */
	public PageUtil getSearchTotal(BoardVO bVO) {
		HashMap	map = new HashMap();
		map.put("target", bVO.getTarget());
		map.put("word", bVO.getWord());	
		int		total = bDAO.getSearchTotal(map);	
		PageUtil	pInfo = new PageUtil(bVO.getNowPage(), total,7);		
		return pInfo;
	}
	/*
	 * 	검색 처리 함수
	 */
	public ArrayList	getSearch(BoardVO bVO, PageUtil pInfo) {
		int		start = (pInfo.nowPage - 1) * pInfo.listCount + 1;
		int		end = pInfo.nowPage * pInfo.listCount;		
		bVO.setStart(start);
		bVO.setEnd(end);

		ArrayList	list = bDAO.getSearch(bVO);
		return list;
	}
	
	public void updateDownloadCnt(int fNo){
		bDAO.updateDownloadCnt(fNo);
	}
	
	//	수정을 위한 원글 내용 꺼내기 처리 함수
	public BoardVO getModifyView(int oriNo) {
		BoardVO 	vo = bDAO.getBoardView(oriNo);
		return vo;
	}

	//	수정 처리를 위한 함수
	public int updateBoard(BoardVO bVO,ArrayList fileInfoList) {
		return bDAO.updateBoard(bVO,fileInfoList);
	}
	
	//	삭제 처리를 위함 함수
	public int deleteBoard(BoardVO bVO) {
		return bDAO.deleteBoard(bVO);
	}
	// contentId에 해당하는 여행 정보 조회 함수
	public HashMap getContent(int contentId){
		return bDAO.getContent(contentId);
	}
	
}
