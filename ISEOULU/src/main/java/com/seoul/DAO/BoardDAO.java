package com.seoul.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.VO.BoardVO;

/*
 * �Խ��� ���� DAO Ŭ����
 */
public class BoardDAO extends SqlSessionDaoSupport {
     
	 //myBatis ����� �Ҷ� ���� ������ ���� �Ʒ��� ���� SqlSessionTemplate�� 
	 // <bean> ó���� �������Ƿ� DI ������� �ҷ����� ��
	@Autowired
	public SqlSessionTemplate		sSession;
	
	/**
	 * 	�Խ��� ����� ��ȸ SQL ���� �Լ�
	 * 
	 * 	�ۼ���		
	 * 	�ۼ���		2017�� 11�� 14��
	 *
	 * 	�Ķ����	    HashMap map : �Խ��Ǹ�� ��ȸ�� ���� ���۹�ȣ �����ȣ�� ������ map
	 * 	��ȯ��        ArrayList	  :  �Խ��Ǹ�� ��ȸ SQL ���� ��� 
	 */
	
	public ArrayList getBoardList(HashMap map) {
		ArrayList	list = (ArrayList) sSession.selectList("board.boardList", map);
		return list;
	}
	
	/**
	 * ������ ������ ���ϱ� ���� �� ������ ���� ���ϱ� SQL ���� �Լ�
	 * 
	 * 	�ۼ���		
	 * 	�ۼ���		2017�� 11�� 14��
	 *
	 * 	�Ķ����	    
	 * 	��ȯ��        int	  :  �ѵ����� ���� 
	 */
	
	public int getTotal(){
		return sSession.selectOne("board.getTotal");
	}
	
	//	�˻� ���� ���� �Լ�
	public ArrayList boardSearch(HashMap map) {
		return (ArrayList) sSession.selectList("board.search", map);
	}
	
	
	/**
	 * �Խ��ǵ�� SQL ���� �Լ�
	 * 
	 * 	�ۼ���		
	 * 	�ۼ���		2017�� 11�� 14��
	 *
	 * 	�Ķ����	    BoardVO bvo : ����� �Խ�������
	 * 	��ȯ��        
	 */

	/*
	 * 	�Խù� ��� ���� ���� �Լ�
	 */
	public void insertBoard(BoardVO bVO) {
		sSession.insert("board.insertBoard",bVO);
	}
	
	/*
	 * ���� ���� ��� ���� ���� �Լ�
	 */
	public void insertFileInfo(HashMap map) {
		sSession.insert("board.fileInfoInsert", map);
	}
	
	
//	��� �Է� ���� ���� �Լ�
	public void insertAn(BoardVO vo) {
		sSession.insert("board.anInsert", vo);
	}
	
	//	��� �Է½� ���� ���� ���� �Լ�
	public void updateOrder(HashMap map) {
		sSession.update("board.orderSet", map);
	}
	
	
	/*
	 * 	�Խù� �󼼺��� ���� ���� �Լ�
	 */
	public BoardVO	getBoardView(int oriNo) {
		System.out.println("fileinfoinsert");
		return (BoardVO) sSession.selectOne("board.boardView", oriNo);
	}
	
	/*
	 * �Խù� ÷������ �˻� ���� ���� �Լ�
	 */
	public ArrayList	getUploadFile(int oriNo) {
		return (ArrayList) sSession.selectList("board.boardFile", oriNo);
	}
	
	/*
	 * ������ ������ ���� �����Լ�
	 */
	public HashMap getPreNext(int bgroup, String kind, HashMap map) {
		if(kind.equals("list")) {
			return (HashMap) sSession.selectOne("board.prenext", bgroup);
		}
		else {
			return (HashMap) sSession.selectOne("board.searchprenext", map);
		}
	}
	
	//	�� �ۿ� ���� �׷��� �˾Ƴ��� ���� ���� ��� ���� �Լ�
	public int getGroup(int oriNo) {
		return (Integer) sSession.selectOne("board.getGroup", oriNo);
	}
	
	//	����� ���� ���Ǹ� ������ �Լ�
	public ArrayList getAnList(int bgroup) {
		return (ArrayList) sSession.selectList("board.anList", bgroup);
	}
	
	
	
	//	�̹� �� �� ��ȣ�� ��ȸ�� ���� ����� ������ �Լ�
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
	//	���� ��ȸ���� ������ �Լ�
	public void updateHit(int oriNo) {
		sSession.update("board.updateHit", oriNo);
	}	
	/*
	 * 	�ٿ�ε� ������ ���� �˻� ���� ���� �Լ�
	 */
	public BoardVO	getDownloadInfo(int fNo) {
		return (BoardVO) sSession.selectOne("board.downloadInfo", fNo);
	}
	
	/*
	 * 	�˻� ���� ���� �Լ�
	 */
	public ArrayList	getSearch(BoardVO bVO) {
		return (ArrayList) sSession.selectList("board.searchList", bVO);
	}
	/*
	 * 	�˻� ��� ���� ���ϱ� ���� ���� �Լ�
	 */
	public int getSearchTotal(HashMap map) {
		return (Integer) sSession.selectOne("board.searchTotal", map);
	}
	//	�ٿ�ε�� �ٿ�ε�� ������Ʈ ���� �Լ�
	public void updateDownloadCnt(int fNo) {
		sSession.update("board.downloadCnt",fNo);
	}
	
	
	//	���� ���� ���� �Լ�
	public int updateBoard(BoardVO bVO,ArrayList list) {
		int ret = sSession.update("board.updateBoard", bVO);
		if (ret !=0 ){
			 sSession.update("board.deleteFileinfo", bVO);
			int	size = list.size();		//	÷�� ������ ����
			for(int i = 0; i < size; i++) {
				HashMap fmap = (HashMap) list.get(i);
				fmap.put("oriNo", bVO.getOriNo());
				sSession.insert("board.fileInfoInsert", fmap);
			}
		}
		return ret;
	}
	
   //	���� ���� ���� �Լ�
	public int deleteBoard(BoardVO bVO) {
		 int ret =  sSession.update("board.deleteBoard", bVO);
		 if (ret !=0){
			 sSession.update("board.deleteFileinfo", bVO);
		 } 
		return ret;
	}
	
	// contentid�� �ش��ϴ� �������� ��ȸ ���� �Լ� 
	public HashMap getContent(int contentId){
		return (HashMap) sSession.selectOne("board.getContent", contentId);
	}
	
}
