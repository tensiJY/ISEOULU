package com.seoul.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.VO.BoardVO;

/*
 * 게시판 관리 DAO 클래스
 */
public class BoardDAO extends SqlSessionDaoSupport {
     
	 //myBatis 등록을 할때 세션 관리를 위한 아래와 같이 SqlSessionTemplate를 
	 // <bean> 처리해 놓았으므로 DI 기법으로 불러오면 됨
	@Autowired
	public SqlSessionTemplate		sSession;
	
	/**
	 * 	게시판 목록을 조회 SQL 수행 함수
	 * 
	 * 	작성자		
	 * 	작성일		2017년 11월 14일
	 *
	 * 	파라메터	    HashMap map : 게시판목록 조회를 위한 시작번호 종료번호를 저장한 map
	 * 	반환값        ArrayList	  :  게시판목록 조회 SQL 수행 결과 
	 */
	
	public ArrayList getBoardList(HashMap map) {
		ArrayList	list = (ArrayList) sSession.selectList("board.boardList", map);
		return list;
	}
	
	/**
	 * 페이지 정보를 구하기 위한 총 데이터 개수 구하기 SQL 수행 함수
	 * 
	 * 	작성자		
	 * 	작성일		2017년 11월 14일
	 *
	 * 	파라메터	    
	 * 	반환값        int	  :  총데이터 갯수 
	 */
	
	public int getTotal(){
		return sSession.selectOne("board.getTotal");
	}
	
	//	검색 질의 실행 함수
	public ArrayList boardSearch(HashMap map) {
		return (ArrayList) sSession.selectList("board.search", map);
	}
	
	
	/**
	 * 게시판등록 SQL 수행 함수
	 * 
	 * 	작성자		
	 * 	작성일		2017년 11월 14일
	 *
	 * 	파라메터	    BoardVO bvo : 등록할 게시판정보
	 * 	반환값        
	 */

	/*
	 * 	게시물 등록 질의 실행 함수
	 */
	public void insertBoard(BoardVO bVO) {
		sSession.insert("board.insertBoard",bVO);
	}
	
	/*
	 * 파일 정보 등록 질의 실행 함수
	 */
	public void insertFileInfo(HashMap map) {
		sSession.insert("board.fileInfoInsert", map);
	}
	
	
//	댓글 입력 질의 실행 함수
	public void insertAn(BoardVO vo) {
		sSession.insert("board.anInsert", vo);
	}
	
	//	댓글 입력시 오더 조절 실행 함수
	public void updateOrder(HashMap map) {
		sSession.update("board.orderSet", map);
	}
	
	
	/*
	 * 	게시물 상세보기 질의 실행 함수
	 */
	public BoardVO	getBoardView(int oriNo) {
		System.out.println("fileinfoinsert");
		return (BoardVO) sSession.selectOne("board.boardView", oriNo);
	}
	
	/*
	 * 게시물 첨부파일 검색 질의 실행 함수
	 */
	public ArrayList	getUploadFile(int oriNo) {
		return (ArrayList) sSession.selectList("board.boardFile", oriNo);
	}
	
	/*
	 * 이전글 다음글 질의 실행함수
	 */
	public HashMap getPreNext(int bgroup, String kind, HashMap map) {
		if(kind.equals("list")) {
			return (HashMap) sSession.selectOne("board.prenext", bgroup);
		}
		else {
			return (HashMap) sSession.selectOne("board.searchprenext", map);
		}
	}
	
	//	그 글에 대한 그룹을 알아내기 위한 질의 명령 실행 함수
	public int getGroup(int oriNo) {
		return (Integer) sSession.selectOne("board.getGroup", oriNo);
	}
	
	//	답글을 꺼낼 질의를 실행할 함수
	public ArrayList getAnList(int bgroup) {
		return (ArrayList) sSession.selectList("board.anList", bgroup);
	}
	
	
	
	//	이미 본 글 번호를 조회할 질의 명령을 실행할 함수
	public HashMap getHitNO(String user) {
		return sSession.selectOne("board.getHitNO", user);
	}
	

	public void updateHitNo(String code, HashMap map) {
		if(code.equals("board.insertHitNO")) {
			sSession.insert(code, map);
		}
		else {
			sSession.update(code, map);
		}
		
	}
	//	실제 조회수를 증가할 함수
	public void updateHit(int oriNo) {
		sSession.update("board.updateHit", oriNo);
	}	
	/*
	 * 	다운로드 파일의 정보 검색 질의 실행 함수
	 */
	public BoardVO	getDownloadInfo(int fNo) {
		return (BoardVO) sSession.selectOne("board.downloadInfo", fNo);
	}
	
	/*
	 * 	검색 질의 실행 함수
	 */
	public ArrayList	getSearch(BoardVO bVO) {
		return (ArrayList) sSession.selectList("board.searchList", bVO);
	}
	/*
	 * 	검색 결과 개수 구하기 질의 실행 함수
	 */
	public int getSearchTotal(HashMap map) {
		return (Integer) sSession.selectOne("board.searchTotal", map);
	}
	//	다운로드시 다운로드수 업데이트 실행 함수
	public void updateDownloadCnt(int fNo) {
		sSession.update("board.downloadCnt",fNo);
	}
	
	
	//	수정 질의 실행 함수
	public int updateBoard(BoardVO bVO,ArrayList list) {
		int ret = sSession.update("board.updateBoard", bVO);
		if (ret !=0 ){
			 sSession.update("board.deleteFileinfo", bVO);
			int	size = list.size();		//	첨부 파일의 개수
			for(int i = 0; i < size; i++) {
				HashMap fmap = (HashMap) list.get(i);
				fmap.put("oriNo", bVO.getOriNo());
				sSession.insert("board.fileInfoInsert", fmap);
			}
		}
		return ret;
	}
	
   //	삭제 질의 실행 함수
	public int deleteBoard(BoardVO bVO) {
		 int ret =  sSession.update("board.deleteBoard", bVO);
		 if (ret !=0){
			 sSession.update("board.deleteFileinfo", bVO);
		 } 
		return ret;
	}
	
	// contentid에 해당하는 여행정보 조회 실행 함수 
	public HashMap getContent(int contentId){
		return (HashMap) sSession.selectOne("board.getContent", contentId);
	}
	
}
