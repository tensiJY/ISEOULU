package com.crawling.web;
/* 이 클래스는  페이지 이동 기능을 구현하기 위해서 필요한 7가지 정보를 기억하고 만들어줄 클래스이다.
 * 페이징 처리하기 ***************************************************

[이전]	[1][2][3][4][5]		[다음]
[이전]	[6][7][8][9][10]	[다음]
위의 모양을 만들기 위해서 반드시 알아야 할 정보

1. 현재 페이지
2. 총 데이터 개수
	=> 위 2개는 반드시 누군가가 알려줘야 할 정보

3. 한 화면에 보여줄 목록의 개수  - 10개
4. 한 화면에서 이동 가능한 페이지 수
	=> 개발자가 강제로 지정해야 할 정보

5. 총 페이지 수 
6. 화면에 표시할 페이지의 시작 페이지
7. 화면에 표시할 페이지의 마지막 페이지
	=> 계산에 의해서 만들어질 정보
 */
public class PageUtil {
	private int nowPage;		// 현재 페이지를 기억할 변수
	private int totalCount;	// 총 데이터 개수를 기억할 변수
	
	private int listCount;	// 한 화면에 보여줄 목록의 개수 - 10개
	private int pageGroup;	// 한 화면에 보여줄 페이지 수 - 5페이지
	
	private int totalPage;	// 총 페이지 수
	private int startPage;	// 시작 페이지
	private int endPage;		// 끝 페이지
	
	private		int		start;
	private		int		end;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	// 누군가가 이 클래스를 new 시키면서 필수 정보를 알려주기로 하자.
	public PageUtil(int nowPage, int totalCount, int listCount) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.listCount = listCount;
		// 이따가 이 부분을 수정해서 결과를 확인하도록 하자.
		/*listCount = 4;*/
		pageGroup = 5;
		
		// 아래쪽에 만든 함수를 하나씩 호출해서 나머지 3개의 정보를 만들어놓자.
		calcTotalPage();
		calcStartPage();
		calcEndPage();
		

		calStart();	//	처음 시작번호
		calEnd();   //	마지막번호
	}
	// 총 페이지수를 계산할 함수
	private void calcTotalPage() {
		// 한 화면에 10개씩 출력하기로 했으므로 총 개수가 100개이면 10페이지가 필요하고 
		// 총 개수가 101개이면 11페이지가 필요하다.
		totalPage = (totalCount % listCount) == 0 ? (totalCount / listCount) : (totalCount / listCount) + 1;
	}
	
	// 시작 페이지를 계산할 함수
	private void calcStartPage() {
		// 원리
		// 현재 페이지가 몇 번째 그룹인지를 알아내고,
		int tempGroup = (nowPage - 1) / pageGroup + 1;
		// 그 그룹의 시작 페이지를 구한다.
		startPage = (tempGroup - 1) * pageGroup + 1;
	}
	
	// 마지막 페이지를 계산할 함수
	private void calcEndPage() {
		// 시작 페이지 + pageGroup - 1을 하면 된다.
		endPage = startPage + pageGroup - 1;
		// 다만 마지막 페이지는 이 결과가 아닐 수 있다.
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	private void calStart(){
		start = (nowPage-1)*(listCount)+1;
	}
	
	private void calEnd(){
		end = listCount*nowPage;
	}
	
	// 이 정보는 view에서 JSTL을 이용해서 사용해야 하므로 
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}	
}