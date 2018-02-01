package com.seoul.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.seoul.DAO.BoardDAO;
import com.seoul.Util.PageUtil;
import com.seoul.VO.BoardVO;

/*
 * �Խ��� ����  Service(�����Ͻ�����) Ŭ����
 */
public class BoardService {
	
	//�����ͺ��̽� ó���� ���� DAO Ŭ������ �� �ʿ��ؼ�  <bean> ó�� �س������Ƿ�
	//  DI ������� �ҷ����� ��
	@Autowired
	public 	BoardDAO		bDAO;
	

	public int getTotal(){
		int total = bDAO.getTotal();
		return total;
	}
	
	/** 
	 * �Խ��� ����� ��ȸ�� ó���ϱ� ���� ���� �Լ�
	 * 	
	 * 	�ۼ���	  
	 * 	�ۼ���	  2017.11.09
	 * 
	 * 	�Ķ��Ÿ    int nowPage:����������, PageUtil pInfo : ����������
	 * 	��ȯ��	   ArrayList : �Խ��Ǹ�� SQL���� ��� ���
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
	
	//	�˻� ó�� ���� �Լ�
	public ArrayList	boardSearch(BoardVO bVO) {
		HashMap	map = new HashMap();
		map.put("target", bVO.getTarget());
		map.put("word", bVO.getWord());
		
		ArrayList	list = bDAO.boardSearch(map);
		return list;
	}
	
	/** 
	 * �Խ������� ����� ó���ϱ� ���� ���� �Լ�
	 * 	
	 * 	�ۼ���	  
	 * 	�ۼ���	  2017.11.14
	 * 
	 * 	�Ķ��Ÿ    BoardVO bvo : ����� ���� ȭ�鿡�� �Է� ���� �Խù� ����
	 * 	��ȯ��	   ����
	 */

	/*
	 * 	�Խù� ��� ó�� �Լ�
	 */
	public void insertBoard(BoardVO bVO, ArrayList list) {
		bDAO.insertBoard(bVO);
		int	size = list.size();		//	÷�� ������ ����
		for(int i = 0; i < size; i++) {
			HashMap map = (HashMap) list.get(i);
			map.put("oriNo", bVO.getNo());
			bDAO.insertFileInfo(map);
		}
		
	}
	
	//	������ ������ �˾Ƴ��� ���� �Լ�
	public BoardVO getOriInfo(int oriNo) {
		BoardVO 	vo = bDAO.getBoardView(oriNo);
		return vo;
	}
	
	//	��� �Է� ó���� ���� �Լ�
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
	 * 	�󼼺��� ���� ������ �˻��� ���� ���� �Լ�
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
	
	//	��ȸ�� ���� ���θ� �Ǵ��� �Լ�
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
	//	���� ��ȸ�� ������ ó���� �Լ�
	public void updateHit(int oriNo) {
		bDAO.updateHit(oriNo);
	}
	
	
		
	/*
	 * 	�ٿ�ε� ���� ���� ó���� ���� �Լ�
	 */
	public BoardVO getDownloadInfo(int fNo) {
		return bDAO.getDownloadInfo(fNo);
	}	
	
	
	/*
	 * 	�˻� ���� ���ϱ� ó�� �Լ�
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
	 * 	�˻� ó�� �Լ�
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
	
	//	������ ���� ���� ���� ������ ó�� �Լ�
	public BoardVO getModifyView(int oriNo) {
		BoardVO 	vo = bDAO.getBoardView(oriNo);
		return vo;
	}

	//	���� ó���� ���� �Լ�
	public int updateBoard(BoardVO bVO,ArrayList fileInfoList) {
		return bDAO.updateBoard(bVO,fileInfoList);
	}
	
	//	���� ó���� ���� �Լ�
	public int deleteBoard(BoardVO bVO) {
		return bDAO.deleteBoard(bVO);
	}
	// contentId�� �ش��ϴ� ���� ���� ��ȸ �Լ�
	public HashMap getContent(int contentId){
		return bDAO.getContent(contentId);
	}
	
}
