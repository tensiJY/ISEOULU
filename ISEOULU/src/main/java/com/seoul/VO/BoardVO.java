package com.seoul.VO;




import java.sql.Date;
import java.util.StringTokenizer;

import org.springframework.web.multipart.MultipartFile;

/* 
 * �Խ��� ���� ���  VO
 */
public class BoardVO {
	
	// �Խ��� ����� ���� ����
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
	
	
	//����¡�� ���� ����
	private	 int		nowPage;
	
	//���������� ���� ���� 
	private	 int		oriNo;
	private	String	oriName;
	private	String	saveName;
	private	long	len;
	private	String	path;
	private	int		download;
	private	Date	uploadDay;
	private	MultipartFile[] 	files;
	
	//�˻��� ���� ���� 
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
		//	�� �Լ��� �信�� ����ڰ� �Է��� �����͸� �˷��ִ� �Լ��̴�.
		//	tags��� ������ ����ڰ� �Է��� �����͸� �˷��ָ�	
		//	����ڰ� �Է��� ������ �� tags ������ ����� �����ʹ�
		//		�ڹ� ������, ��		�� �������� �ԷµǾ� ���� ���̴�.
		//	�츮�� #�ڹ�#������#��		�� �������� ����� ���ƾ� �Ѵ�.
		
		StringTokenizer	token = new StringTokenizer(tags, " ,");
		//		�ڹ� ������, ��		==>		�ڹ� 		������		��
		String	realData = "";
		while(token.hasMoreTokens()) {
			String	data = token.nextToken();
			realData = realData + "#" + data;
		}
		
		//	��¥ �߿��� ��Ȱ��	
		//	����ڰ� ������ �����Ͱ� ���� ����� ��������
		//	���°� �ٸ��� setXxx �Լ����� �����ؼ� ����� �� �ִ�.
		
		
		
		this.tags = realData;
		//	�ڽ��� ������ �ִ� ���� ������ �� �����͸� �״�� �Է��� ���´�.
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
