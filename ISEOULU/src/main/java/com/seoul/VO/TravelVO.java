package com.seoul.VO;

import java.sql.Date;
import java.util.StringTokenizer;

import com.seoul.Util.StringUtil;

/**
 * 
 * @author 박주형
 * @date 2017.11.20
 *
 */
public class TravelVO {

	
	
	// 공통부분
	
	private int areacode; // 지역코드
	private int siguncode; // 시군코드
	private int sgCode;
	
	
	// BASETBL(기본 공통 테이블)

	private int contentid; // 콘텐츠 id
	private int contenttypeid; // 관광타입id
	private String firstimage; // 이미지 링크
	private String firstimage2; // 이미지 링크2
	private double mapx; // 지도 좌표 x의 값
	private double mapy; // 지도 좌표 y의 값
	private String addr1; // 주소
	private String cat1;	//카테고리
	private String cat2;	//카테고리2
	private String cat3;   //카테고리 3
	
	private String createdtime; // 등록일
	private String modifiedtime; //수정일
	private String readcount; // 조회수
	private String tel; // 전화번호
	private String title; // 상호, 이름, 명(해당 내용 제목)
	private String zipcode; // 우편번호

	
	private int nowPage;
	private int rno;
	
	//거리
	private int round;
	
	
	
	
	// travelinfotbl(관광상세정보 테이블)

	private String overview; // 관광소개

	// travelintrotbl(관광소개정보 테이블)

	/*
	 * 관광지타입
	 */
	private String chkbabycarriage;// 유모차 대여 여부
	private String accomcount;// 수용인원
	private String chkcreditcard;// 신용카드 가능 여부
	private String chkpet;// 애완동물 가능 여부
	private String expguide;// 체험안내
	private String heritage1;// 세계 문화유산 유무
	private String heritage2;// 세계 자연 유산 유무
	private String heritage3;// 세계 기록 유산 유무
	private String infocenter;// 문의 및 안내
	private String opendate;// 개장일
	private String parking;// 주차시설
	private String restdate;// 쉬는날
	private String expagerange;// 체험가능 연령
	private String useseason;// 이용시기
	private String usetime;// 이용시간

	/*
	 * 행사/공연/축제
	 */
	private String agelimit;// 관람 가능연령
	private String bookingplace;// 예매처
	private String discountinfofestival;// 할인정보
	private String eventenddate;// 행사 종료일
	private String eventhomepage;// 행사 홈페이지
	private String eventplace;// 행사장소
	private String eventstartdate;// 행사 시작일
	private String festivalgrade;// 축제등급
	private String placeinfo;// 행사장 위치 안내
	private String playtime;// 공연시간
	private String program;// 행사 프로그램
	private String spendtimefestival;// 관람 소요시간
	private String sponsor1;// 주최자 정보
	private String sponsor1tel;// 주최자 연락처
	private String sponsor2;// 주관사 정보
	private String sponsor2tel;// 주관사 연락처
	private String subevent;// 부대행사
	private String usetimefestival;// 이용요금

	/*
	 * 문화시설
	 */
	private String chkbabycarriageculture;// 유모차대여여부
	private String accomcountculture;// 수용인원
	private String chkcreditcardculture;// 신용카드 가능 여부
	private String chkpetculture;// 애완동물 가능 여부
	private String infocenterculture;// 문의 및 안내
	private String parkingculture;// 주차시설
	private String parkingfee;// 주차요금
	private String usefee;// 이용요금
	private String restdateculture;// 쉬는날
	private String spendtime;// 관람 소요시간
	private String scale;// 규모
	private String usetimeculture;// 이용시간

	/*
	 * 여행코스
	 */
	private String distance;// 코스총거리
	private String infocentertourcourse;// 문의 및 안내
	private String schedule;// 코스일정
	private String theme;// 코스 테마
	private String taketime;// 코스 총 소요시간
	
	/*
	 * 레저 스포츠
	 */
	
	private String accomcountleports;// 수용인원
	private String chkbabycarriageleports;// 유모차대여 여부
	private String chkcreditcardleports;// 신용카드 가능 여부
	private String chkpetleports;//애완동물 가능여부
	private String expagerangeleports;//체험가능 연령
	private String infocenterleports;//문의 및 안내
	private String parkingfeeleports;//주차 요금
	private String parkingleports;//주차시설
	private String openperiod;//개장시간
	private String reservation;//예약안내
	private String restdateleports;//쉬는날
	private String scaleleports;//규모
	private String usefeeleports;//입장료
	private String usetimeleports;//이용시간
	
	
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
	public String getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}
	public String getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
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
	
	
	public String getReadcount() {
		return readcount;
	}
	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getChkbabycarriage() {
		return chkbabycarriage;
	}
	public void setChkbabycarriage(String chkbabycarriage) {
		this.chkbabycarriage = chkbabycarriage;
	}
	public String getAccomcount() {
		return accomcount;
	}
	public void setAccomcount(String accomcount) {
		this.accomcount = accomcount;
	}
	public String getChkcreditcard() {
		return chkcreditcard;
	}
	public void setChkcreditcard(String chkcreditcard) {
		this.chkcreditcard = chkcreditcard;
	}
	public String getChkpet() {
		return chkpet;
	}
	public void setChkpet(String chkpet) {
		this.chkpet = chkpet;
	}
	public String getExpguide() {
		return expguide;
	}
	public void setExpguide(String expguide) {
		this.expguide = expguide;
	}
	public String getHeritage1() {
		return heritage1;
	}
	public void setHeritage1(String heritage1) {
		this.heritage1 = heritage1;
	}
	public String getHeritage2() {
		return heritage2;
	}
	public void setHeritage2(String heritage2) {
		this.heritage2 = heritage2;
	}
	public String getHeritage3() {
		return heritage3;
	}
	public void setHeritage3(String heritage3) {
		this.heritage3 = heritage3;
	}
	public String getInfocenter() {
		return infocenter;
	}
	public void setInfocenter(String infocenter) {
		this.infocenter = infocenter;
	}
	public String getOpendate() {
		return opendate;
	}
	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getRestdate() {
		return restdate;
	}
	public void setRestdate(String restdate) {
		this.restdate = restdate;
	}
	public String getExpagerange() {
		return expagerange;
	}
	public void setExpagerange(String expagerange) {
		this.expagerange = expagerange;
	}
	public String getUseseason() {
		return useseason;
	}
	public void setUseseason(String useseason) {
		this.useseason = useseason;
	}
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	public String getAgelimit() {
		return agelimit;
	}
	public void setAgelimit(String agelimit) {
		this.agelimit = agelimit;
	}
	public String getBookingplace() {
		return bookingplace;
	}
	public void setBookingplace(String bookingplace) {
		this.bookingplace = bookingplace;
	}
	public String getDiscountinfofestival() {
		return discountinfofestival;
	}
	public void setDiscountinfofestival(String discountinfofestival) {
		this.discountinfofestival = discountinfofestival;
	}
	public String getEventenddate() {
		return eventenddate;
	}
	public void setEventenddate(String eventenddate) {
		this.eventenddate = eventenddate;
	}
	public String getEventhomepage() {
		return eventhomepage;
	}
	public void setEventhomepage(String eventhomepage) {
		this.eventhomepage = eventhomepage;
	}
	public String getEventplace() {
		return eventplace;
	}
	public void setEventplace(String eventplace) {
		this.eventplace = eventplace;
	}
	public String getEventstartdate() {
		return eventstartdate;
	}
	public void setEventstartdate(String eventstartdate) {
		this.eventstartdate = eventstartdate;
	}
	public String getFestivalgrade() {
		return festivalgrade;
	}
	public void setFestivalgrade(String festivalgrade) {
		this.festivalgrade = festivalgrade;
	}
	public String getPlaceinfo() {
		return placeinfo;
	}
	public void setPlaceinfo(String placeinfo) {
		this.placeinfo = placeinfo;
	}
	public String getPlaytime() {
		return playtime;
	}
	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getSpendtimefestival() {
		return spendtimefestival;
	}
	public void setSpendtimefestival(String spendtimefestival) {
		this.spendtimefestival = spendtimefestival;
	}
	public String getSponsor1() {
		return sponsor1;
	}
	public void setSponsor1(String sponsor1) {
		this.sponsor1 = sponsor1;
	}
	public String getSponsor1tel() {
		return sponsor1tel;
	}
	public void setSponsor1tel(String sponsor1tel) {
		this.sponsor1tel = sponsor1tel;
	}
	public String getSponsor2() {
		return sponsor2;
	}
	public void setSponsor2(String sponsor2) {
		this.sponsor2 = sponsor2;
	}
	public String getSponsor2tel() {
		return sponsor2tel;
	}
	public void setSponsor2tel(String sponsor2tel) {
		this.sponsor2tel = sponsor2tel;
	}
	public String getSubevent() {
		return subevent;
	}
	public void setSubeven(String subevent) {
		this.subevent = subevent;
	}
	public String getUsetimefestival() {
		return usetimefestival;
	}
	public void setUsetimefestival(String usetimefestival) {
		this.usetimefestival = usetimefestival;
	}
	public String getChkbabycarriageculture() {
		return chkbabycarriageculture;
	}
	public void setChkbabycarriageculture(String chkbabycarriageculture) {
		this.chkbabycarriageculture = chkbabycarriageculture;
	}
	public String getAccomcountculture() {
		return accomcountculture;
	}
	public void setAccomcountculture(String accomcountculture) {
		this.accomcountculture = accomcountculture;
	}
	public String getChkcreditcardculture() {
		return chkcreditcardculture;
	}
	public void setChkcreditcardculture(String chkcreditcardculture) {
		this.chkcreditcardculture = chkcreditcardculture;
	}
	public String getChkpetculture() {
		return chkpetculture;
	}
	public void setChkpetculture(String chkpetculture) {
		this.chkpetculture = chkpetculture;
	}
	public String getInfocenterculture() {
		return infocenterculture;
	}
	public void setInfocenterculture(String infocenterculture) {
		this.infocenterculture = infocenterculture;
	}
	public String getParkingculture() {
		return parkingculture;
	}
	public void setParkingculture(String parkingculture) {
		this.parkingculture = parkingculture;
	}
	public String getParkingfee() {
		return parkingfee;
	}
	public void setParkingfee(String parkingfee) {
		this.parkingfee = parkingfee;
	}
	public String getUsefee() {
		return usefee;
	}
	public void setUsefee(String usefee) {
		this.usefee = usefee;
	}
	public String getRestdateculture() {
		return restdateculture;
	}
	public void setRestdateculture(String restdateculture) {
		this.restdateculture = restdateculture;
	}
	public String getSpendtime() {
		return spendtime;
	}
	public void setSpendtime(String spendtime) {
		this.spendtime = spendtime;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getUsetimeculture() {
		return usetimeculture;
	}
	public void setUsetimeculture(String usetimeculture) {
		this.usetimeculture = usetimeculture;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getInfocentertourcourse() {
		return infocentertourcourse;
	}
	public void setInfocentertourcourse(String infocentertourcourse) {
		this.infocentertourcourse = infocentertourcourse;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getTaketime() {
		return taketime;
	}
	public void setTaketime(String taketime) {
		this.taketime = taketime;
	}
	public String getAccomcountleports() {
		return accomcountleports;
	}
	public void setAccomcountleports(String accomcountleports) {
		this.accomcountleports = accomcountleports;
	}
	public String getChkbabycarriageleports() {
		return chkbabycarriageleports;
	}
	public void setChkbabycarriageleports(String chkbabycarriageleports) {
		this.chkbabycarriageleports = chkbabycarriageleports;
	}
	public String getChkcreditcardleports() {
		return chkcreditcardleports;
	}
	public void setChkcreditcardleports(String chkcreditcardleports) {
		this.chkcreditcardleports = chkcreditcardleports;
	}
	public String getChkpetleports() {
		return chkpetleports;
	}
	public void setChkpetleports(String chkpetleports) {
		this.chkpetleports = chkpetleports;
	}
	public String getExpagerangeleports() {
		return expagerangeleports;
	}
	public void setExpagerangeleports(String expagerangeleports) {
		this.expagerangeleports = expagerangeleports;
	}
	public String getInfocenterleports() {
		return infocenterleports;
	}
	public void setInfocenterleports(String infocenterleports) {
		this.infocenterleports = infocenterleports;
	}
	public String getParkingfeeleports() {
		return parkingfeeleports;
	}
	public void setParkingfeeleports(String parkingfeeleports) {
		this.parkingfeeleports = parkingfeeleports;
	}
	public String getParkingleports() {
		return parkingleports;
	}
	public void setParkingleports(String parkingleports) {
		this.parkingleports = parkingleports;
	}
	public String getOpenperiod() {
		return openperiod;
	}
	public void setOpenperiod(String openperiod) {
		this.openperiod = openperiod;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getRestdateleports() {
		return restdateleports;
	}
	public void setRestdateleports(String restdateleports) {
		this.restdateleports = restdateleports;
	}
	public String getScaleleports() {
		return scaleleports;
	}
	public void setScaleleports(String scaleleports) {
		this.scaleleports = scaleleports;
	}
	public String getUsefeeleports() {
		return usefeeleports;
	}
	public void setUsefeeleports(String usefeeleports) {
		this.usefeeleports = usefeeleports;
	}
	public String getUsetimeleports() {
		return usetimeleports;
	}
	public void setUsetimeleports(String usetimeleports) {
		this.usetimeleports = usetimeleports;
	}
	
	

	
	
}