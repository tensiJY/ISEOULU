package com.crawling.web;

import java.util.Date;

public class CrawlingVO {
	
	
	private String keyWord;
	private int startPage;
	private String email;
	
	private int jno;
	private String jtitle;
	private String jlink;
	private String jbody;
	private String jisshow;
	
	private Date jcreDate;
	
	
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public Date getJcreDate() {
		return jcreDate;
	}
	public void setJcreDate(Date jcreDate) {
		this.jcreDate = jcreDate;
	}
	public String getJbody() {
		return jbody;
	}
	public CrawlingVO setJbody(String jbody) {
		this.jbody = jbody;
		return this;
	}
	public int getJno() {
		return jno;
	}
	public CrawlingVO setJno(int jno) {
		this.jno = jno;
		return this;
	}
	public String getJtitle() {
		return jtitle;
	}
	public CrawlingVO setJtitle(String jtitle) {
		this.jtitle = jtitle;
		return this;
	}
	public String getJlink() {
		return jlink;
	}
	public CrawlingVO setJlink(String jlink) {
		this.jlink = jlink;
		return this;
	}
	public String getJisshow() {
		return jisshow;
		
	}
	public CrawlingVO setJisshow(String jisshow) {
		this.jisshow = jisshow;
		return this;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public CrawlingVO setKeyWord(String keyWord) {
		this.keyWord = keyWord;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public CrawlingVO setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String toString(){
		
		return keyWord + " " + email;
	}
	
	
	
	
}
