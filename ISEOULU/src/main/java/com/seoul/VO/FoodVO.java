package com.seoul.VO;

import com.seoul.Util.StringUtil;

public class FoodVO {
	//	시군구 코드
	private int areacode;
	private int siguncode;
	private int sgCode;
	
	//	basetbl;
	private String addr1;	//	신주소
	private String cat1;	//	카테고리1
	private String cat2;	//	카테고리2
	private String cat3;	
	private int contentid;	//	contentid	- id
	private int contenttypeid;
	private String createdtime;	// 등록일
	private String firstimage;	//	첫번째 이미지
	private String firstimage2;	//	두번째 이미지
	private double mapx; 	//	x좌표;
	private double mapy;	//	y좌표
	private String modifiedtime;	//	수정일
	private String readcount;
	private String tel;
	private String title;
	private String zipcode;
	
	//	foodinfotbl
	private String overview;
	
	//	foodintrotbl;
	private String chkcreditcardfood;
	private String firstmenu;
	private String infocenterfood;
	private String kidsfacility;
	private String opendatefood;
	private String opentimefood;
	private String packing;
	private String parkingfood;
	private String reservationfood;
	private String restdatefood;
	private String seat;
	private String smoking;
	private String treatmenu;
	
	

	private int nowPage;
	private int rno;
	
	//	거리
	private int round;
	
	
	public int getAreacode() {
		return areacode;
	}

	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}

	public int getSiguncode() {
		return siguncode;
	}

	public void setSiguncode(int siguncode) {
		this.siguncode = siguncode;
	}

	public int getSgCode() {
		return sgCode;
	}

	public void setSgCode(int sgCode) {
		this.sgCode = sgCode;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getCat1() {
		return cat1;
	}

	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	public String getCat2() {
		return cat2;
	}

	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	public String getCat3() {
		return cat3;
	}

	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	public int getContenttypeid() {
		return contenttypeid;
	}

	public void setContenttypeid(int contenttypeid) {
		this.contenttypeid = contenttypeid;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}

	public String getFirstimage2() {
		return firstimage2;
	}

	public void setFirstimage2(String firstimage2) {
		this.firstimage2 = firstimage2;
	}

	public double getMapx() {
		return mapx;
	}

	public void setMapx(double mapx) {
		this.mapx = mapx;
	}

	public double getMapy() {
		return mapy;
	}

	public void setMapy(double mapy) {
		this.mapy = mapy;
	}

	public String getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTitle() {
		return StringUtil.change(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getChkcreditcardfood() {
		return chkcreditcardfood;
	}

	public void setChkcreditcardfood(String chkcreditcardfood) {
		this.chkcreditcardfood = chkcreditcardfood;
	}

	public String getFirstmenu() {
		return firstmenu;
	}

	public void setFirstmenu(String firstmenu) {
		this.firstmenu = firstmenu;
	}

	public String getInfocenterfood() {
		return infocenterfood;
	}

	public void setInfocenterfood(String infocenterfood) {
		this.infocenterfood = infocenterfood;
	}

	public String getKidsfacility() {
		return kidsfacility;
	}

	public void setKidsfacility(String kidsfacility) {
		this.kidsfacility = kidsfacility;
	}

	public String getOpendatefood() {
		return opendatefood;
	}

	public void setOpendatefood(String opendatefood) {
		this.opendatefood = opendatefood;
	}

	public String getOpentimefood() {
		return opentimefood;
	}

	public void setOpentimefood(String opentimefood) {
		this.opentimefood = opentimefood;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getParkingfood() {
		return parkingfood;
	}

	public void setParkingfood(String parkingfood) {
		this.parkingfood = parkingfood;
	}

	public String getReservationfood() {
		return reservationfood;
	}

	public void setReservationfood(String reservationfood) {
		this.reservationfood = reservationfood;
	}

	public String getRestdatefood() {
		return restdatefood;
	}

	public void setRestdatefood(String restdatefood) {
		this.restdatefood = restdatefood;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public String getTreatmenu() {
		return treatmenu;
	}

	public void setTreatmenu(String treatmenu) {
		this.treatmenu = treatmenu;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	
	
	
	
}
