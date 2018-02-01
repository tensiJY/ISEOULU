package com.seoul.VO;




import java.sql.Date;
import java.util.StringTokenizer;

import org.springframework.web.multipart.MultipartFile;

/* 
 * 게시판 변수 등록  VO
 */
public class BoardVO {
	
	// 게시판 등록을 위한 변수
	private int no;
	private int 		rno;
	
	
	private String	title;
	private String 	writer;
	
	
	private	 String 	content;
	private	 String 	password;
	private	 String 	tags;
	private	 int 		hit;
	private int		bgroup;
	private	 int		bstep;
	private	 int		border;
	private Date		wday;
	private int	 	start;	 	
	private int		end ;	    
	
	
	//페이징을 위한 변수
	private	 int		nowPage;
	
	//파일전송을 위한 변수 
	private	 int		oriNo;
	private	String	oriName;
	private	String	saveName;
	private	long	len;
	private	String	path;
	private	int		download;
	private	Date	uploadDay;
	private	MultipartFile[] 	files;
	
	//검색을 위한 변수 
	private	String	target;
	private	String	word;
	private 	String  kind;


	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getWriter() {
		return writer;
	}
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
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDownload() {
		return download;
	}
	public void setDownload(int download) {
		this.download = download;
	}
	public Date getUploadDay() {
		return uploadDay;
	}
	public void setUploadDay(Date uploadDay) {
		this.uploadDay = uploadDay;
	}
	
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		//	이 함수는 뷰에서 사용자가 입력한 데이터를 알려주는 함수이다.
		//	tags라는 변수에 사용자가 입력한 데이터를 알려주면	
		//	사용자가 입력한 데이터 즉 tags 변수가 기억한 데이터는
		//		자바 스프링, 웹		의 형식으로 입력되어 있을 것이다.
		//	우리는 #자바#스프링#웹		의 형식으로 기억해 놓아아 한다.
		
		StringTokenizer	token = new StringTokenizer(tags, " ,");
		//		자바 스프링, 웹		==>		자바 		스프링		웹
		String	realData = "";
		while(token.hasMoreTokens()) {
			String	data = token.nextToken();
			realData = realData + "#" + data;
		}
		
		//	진짜 중요한 역활은	
		//	사용자가 전달한 데이터가 실제 사용할 데이터의
		//	형태가 다르면 setXxx 함수에서 가공해서 사용할 수 있다.
		
		
		
		this.tags = realData;
		//	자신이 가지고 있는 전역 변수에 그 데이터를 그대로 입력해 놓는다.
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	public int getBgroup() {
		return bgroup;
	}
	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}
	public int getBstep() {
		return bstep;
	}
	public void setBstep(int bstep) {
		this.bstep = bstep;
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public int getRno() {
		return rno;
	}
	public Date getWday() {
		return wday;
	}
	public void setWday(Date wday) {
		this.wday = wday;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	
	
}
