package com.seoul.VO;

import com.seoul.Util.StringUtil;

public class HotelVO {
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
	
	//	stayinfotbl
	private String overview;
	
	//	stayintrotbl;
	private String accomcountlodging;
	private String barbecue;
	private String beauty;
	private String benikia;
	private String beverage;
	private String bicycle;
	private String campfire;
	private String checkintime;
	private String checkouttime;
	private String chkcooking;
	private String fitness;
	private String foodplace;
	private String goodstay;
	private String hanok;
	private String infocenterlodging;
	private String karaoke;
	private String parkinglodging;
	private String pickup;
	private String publicbath;
	private String publicpc;
	private String reservationlodging;
	private String reservationurl;
	private String roomcount;
	private String roomtype;
	private String sauna;
	private String seminar;
	private String sports;
	private String subfacility;
		
	
	private int nowPage;
	private int rno;
	
	//	거리
	private int round;
	
	
	
	
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getSgCode() {
		return sgCode;
	}
	public void setSgCode(int sgCode) {
		this.sgCode = sgCode;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getAreacode() {
		return areacode;
	}
	public void setAreacode(int areacode) {
		this.areacode = areacode;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getAccomcountlodging() {
		return accomcountlodging;
	}
	public void setAccomcountlodging(String accomcountlodging) {
		this.accomcountlodging = accomcountlodging;
	}
	public String getBarbecue() {
		return barbecue;
	}
	public void setBarbecue(String barbecue) {
		this.barbecue = barbecue;
	}
	public String getBeauty() {
		return beauty;
	}
	public void setBeauty(String beauty) {
		this.beauty = beauty;
	}
	public String getBenikia() {
		return benikia;
	}
	public void setBenikia(String benikia) {
		this.benikia = benikia;
	}
	public String getBeverage() {
		return beverage;
	}
	public void setBeverage(String beverage) {
		this.beverage = beverage;
	}
	public String getBicycle() {
		return bicycle;
	}
	public void setBicycle(String bicycle) {
		this.bicycle = bicycle;
	}
	public String getCampfire() {
		return campfire;
	}
	public void setCampfire(String campfire) {
		this.campfire = campfire;
	}
	public String getCheckintime() {
		return checkintime;
	}
	public void setCheckintime(String checkintime) {
		this.checkintime = checkintime;
	}
	public String getCheckouttime() {
		return checkouttime;
	}
	public void setCheckouttime(String checkouttime) {
		this.checkouttime = checkouttime;
	}
	public String getChkcooking() {
		return chkcooking;
	}
	public void setChkcooking(String chkcooking) {
		this.chkcooking = chkcooking;
	}
	public String getFitness() {
		return fitness;
	}
	public void setFitness(String fitness) {
		this.fitness = fitness;
	}
	public String getFoodplace() {
		return foodplace;
	}
	public void setFoodplace(String foodplace) {
		this.foodplace = foodplace;
	}
	public String getGoodstay() {
		return goodstay;
	}
	public void setGoodstay(String goodstay) {
		this.goodstay = goodstay;
	}
	public String getHanok() {
		return hanok;
	}
	public void setHanok(String hanok) {
		this.hanok = hanok;
	}
	public String getInfocenterlodging() {
		return infocenterlodging;
	}
	public void setInfocenterlodging(String infocenterlodging) {
		this.infocenterlodging = infocenterlodging;
	}
	public String getKaraoke() {
		return karaoke;
	}
	public void setKaraoke(String karaoke) {
		this.karaoke = karaoke;
	}
	public String getParkinglodging() {
		return parkinglodging;
	}
	public void setParkinglodging(String parkinglodging) {
		this.parkinglodging = parkinglodging;
	}
	public String getPickup() {
		return pickup;
	}
	public void setPickup(String pickup) {
		this.pickup = pickup;
	}
	public String getPublicbath() {
		return publicbath;
	}
	public void setPublicbath(String publicbath) {
		this.publicbath = publicbath;
	}
	public String getPublicpc() {
		return publicpc;
	}
	public void setPublicpc(String publicpc) {
		this.publicpc = publicpc;
	}
	public String getReservationlodging() {
		return reservationlodging;
	}
	public void setReservationlodging(String reservationlodging) {
		this.reservationlodging = reservationlodging;
	}
	public String getReservationurl() {
		return reservationurl;
	}
	public void setReservationurl(String reservationurl) {
		this.reservationurl = reservationurl;
	}
	public String getRoomcount() {
		return roomcount;
	}
	public void setRoomcount(String roomcount) {
		this.roomcount = roomcount;
	}
	public String getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}
	public String getSauna() {
		return sauna;
	}
	public void setSauna(String sauna) {
		this.sauna = sauna;
	}
	public String getSeminar() {
		return seminar;
	}
	public void setSeminar(String seminar) {
		this.seminar = seminar;
	}
	public String getSports() {
		return sports;
	}
	public void setSports(String sports) {
		this.sports = sports;
	}
	public String getSubfacility() {
		return subfacility;
	}
	public void setSubfacility(String subfacility) {
		this.subfacility = subfacility;
	}
	public int getSiguncode() {
		return siguncode;
	}
	public void setSiguncode(int siguncode) {
		this.siguncode = siguncode;
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
	
	
	
}
