--	delete from areaTbl;
--	delete from sigunTbl;
--	delete from foodTbl;
--	delete from stayTbl;
--	delete from travelTbl;
--	delete from stayInfoTbl	
--	delete from travelInfoTbl
--	delete from foodInfoTbl
--	delete from stayIntroTbl
--	delete from foodIntroTbl
--	delete from TravelIntroTbl

--	drop table areaTbl;
--	drop table sigunTbl;
--	drop table foodTbl;
--	drop table stayTbl;
--	drop table travelTbl;
--	drop table stayInfoTbl;	
--	drop table travelInfoTbl;
--	drop table foodInfoTbl;
--	drop table stayIntroTbl;
--	drop table foodIntroTbl;
--	drop table TravelIntroTbl;

conn /as sysdba
create user trip identified by trip;
GRANT resource, connect TO trip;

--	1.	areaTbl ���� �ڵ�
create table areaTbl (
	areaCode number(5),	-- �����ڵ�
	areaName varchar2(500)		-- ������	
);

--	2.	sigungu	�ñ��� �ڵ�
create table sigunTbl (
	areaCode number(5),	-- �����ڵ�
	sigunCode number(5),	-- �ñ����ڵ�
	sigunName varchar2(500)	-- �ñ�����
);


create table baseTbl (
	areaCode number(5),	-- �����ڵ�
	sigunCode number(5),	-- �ñ����ڵ�
	addr1 varchar2(500),		-- ���ּ�
	cat1 varchar2(500),		-- ī�װ�1	
	cat2 varchar2(500),		-- ī�װ�2
	cat3 varchar2(500),		-- ī�װ�3
	contentid number(10),		-- ������id
	contenttypeid number(5),		-- ������Ÿ��id
	createdtime varchar2(500),		-- �����
	firstimage varchar2(2000),		-- ù��°�̹���
	firstimage2 varchar2(2000),		-- �ι�°�̹���
	mapx number(15,11),		-- x
	mapy number(15,11),		-- y
	modifiedtime varchar2(500), -- ������
	readcount varchar2(1000),		-- ��ȸ��
	tel varchar2(500), -- ��ȭ��ȣ
	title varchar2(500),		-- ���� 
	zipcode varchar2(500)	-- �����ּ�
);



-- ���� ������ ��ȸ
create table stayInfoTbl (
	contentid number(10),		-- ������id
	contenttypeid number(5),		-- ������Ÿ��id
	overview clob -- �󼼳���
);

-- ���� ������ ��ȸ
create table travelInfoTbl (
	contentid number(10),		-- ������id
	contenttypeid number(5),		-- ������Ÿ��id
	overview clob -- �󼼳���
);



-- ���� ������ ��ȸ
create table foodInfoTbl (
	contentid number(10),		-- ������id
	contenttypeid number(5),		-- ������Ÿ��id
	overview clob -- �󼼳���
);


--	���� ��Ʈ��
create table stayIntroTbl(
	accomcountlodging varchar2(1000),
	barbecue varchar2(1000),
	beauty varchar2(1000),
	benikia varchar2(1000),
	beverage varchar2(800),
	bicycle varchar2(800),
	campfire varchar2(800),
	checkintime varchar2(800),
	checkouttime varchar2(800),
	chkcooking varchar2(800),
	contentid number(10),		-- ������id ����
	contenttypeid number(5),		-- ������Ÿ��id
	fitness varchar2(1000),
	foodplace varchar2(2000),
	goodstay varchar2(1000),
	hanok varchar2(1000),
	infocenterlodging varchar2(2000),
	karaoke varchar2(1000),
	parkinglodging varchar2(2000),
	pickup varchar2(1000),
	publicbath varchar2(1000),
	publicpc varchar2(1000),
	reservationlodging varchar2(2000),
	reservationurl varchar2(2500),
	roomcount varchar2(1000),
	roomtype varchar2(1000),
	sauna varchar2(1000),
	seminar varchar2(1000),
	sports varchar2(1000),
	subfacility varchar2(2500)
);


--	���� ��Ʈ��
create table foodIntroTbl(
	chkcreditcardfood varchar2(1000),
	contentid number(10),		--  ����
	contenttypeid number(5),		-- ������Ÿ��id
	firstmenu varchar2(1000),
	infocenterfood varchar2(1000),
	kidsfacility varchar2(1000),
	opendatefood varchar2(2000),
	opentimefood varchar2(1000),
	packing varchar2(1000),
	parkingfood varchar2(1000),
	reservationfood varchar2(1000),
	restdatefood varchar2(1000),
	seat varchar2(1000),
	smoking varchar2(1000),
	treatmenu clob
);

--	����

create table TravelIntroTbl(
	chkbabycarriage varchar2(800), --12, 15 ��������
	chkcreditcard varchar2(800),
	chkpet varchar2(800),
	contentid number(10),		-- ������id ����
	contenttypeid number(5),		-- ������Ÿ��id 12,15, 14, 25, 28
	expguide varchar2(2000),
	heritage1 varchar2(800),
	heritage2 varchar2(800),
	heritage3 varchar2(800),
	infocenter varchar2(2000),
	opendate varchar2(1000),
	parking varchar2(1000),
	restdate varchar2(1000),	
	expagerange varchar2(1000),
	usetime varchar2(1000) -- 12,15 ����������
    chkbabycarriageculture varchar2(2000),   -- 14 ��ȭ ���� ����
    accomcountculture varchar2(2000),   -- ������ ���� 360��
    chkcreditcardculture varchar2(2000), --
    chkpetculture varchar2(1000), --
    infocenterculture varchar2(2000), -- ??
    parkingculture varchar2(2000), -- ��������
    parkingfee varchar2(3000), 
    usefee clob,
    restdateculture varchar2(2500),
    spendtime varchar2(2000),
    scale varchar2(1000),
    usetimeculture varchar2(2000), -- 14 ���� ��
    distance varchar2(1500), -- 25��������     , �Ÿ�
    taketime varchar2(1000) -- 25������       , �ҿ�ð�
    chkbabycarriageleports varchar2(1500), -- 28 �������� --���ð�
    chkcreditcardleports varchar2(1500), -- 
    chkpetleports varchar2(1500), --
    infocenterleports varchar2(1500), -- ����������ȭ
    reservation varchar2(1000), -- ������ȭ
    restdateleports varchar2(1300), -- ���³�
    expagerangeleports varchar2(1000), -- �̿밡�� ����
    parkingleports varchar2(1000), -- ��������
    usetimeleports varchar2(2000) -- �̿�ȳ� 28���� ��
);




SELECT mapy, mapx
     , SQRT( POWER(( 37.5549936996 - mapy) * 110979.309, 2)
           + POWER(( 126.9351391883 - mapx) *  88907.949, 2)
           ) z
  FROM foodtbl
     , (SELECT     37.5549936996 v_x    -- ������ǥ-����
             ,    126.9351391883 v_y    -- ������ǥ-�浵
             , 110979.309     r_x    -- ����-�Ÿ� ȯ����
             ,  88907.949     r_y    -- �浵-�Ÿ� ȯ����
             ,    1000.000     v_z    -- �˻��ݰ�
          FROM dual
        )
 WHERE SQRT( POWER((v_x - mapy) * r_x, 2)
           + POWER((v_y - mapx) * r_y, 2)
           ) <= v_z
   AND mapy BETWEEN v_x - v_z / r_x AND v_x + v_z / r_x
   AND mapx BETWEEN v_y - v_z / r_y AND v_y + v_z / r_y
 ORDER BY z
 
 
 /* 	<c:if test="${HVO.mapx eq 9999}">
			var mapContainer = document.getElementById('map'), 
		    mapOption = {
		        center: new daum.maps.LatLng(33.450701, 126.570667),
		        level: 3 
		    };  
			var map = new daum.maps.Map(mapContainer, mapOption); 
			//	�ּҷ� ��ǥ��ȯ ���̺귯��
			var geocoder = new daum.maps.services.Geocoder();
			geocoder.addressSearch('${HVO.addr1}', function(result, status) {
			    if (status === daum.maps.services.Status.OK) {
					var coords = new daum.maps.LatLng(result[0].y, result[0].x);
				    var marker = new daum.maps.Marker({
			            map: map,
			            position: coords
			        }); 
			        map.setCenter(coords);
			    } 
			});
		
		</c:if>	 */		

 
 create table TravelIntroTbl(
 	contentid number(10),		-- ������id ����
	contenttypeid number(5),		-- ������Ÿ��id 12,15, 14, 25, 28
	chkbabycarriage varchar2(800), --12,
    accomcount varchar2(2000),
	chkcreditcard varchar2(800),
	chkpet varchar2(800),
	expguide varchar2(2000),
	heritage1 varchar2(800),
	heritage2 varchar2(800),
	heritage3 varchar2(800),
	infocenter varchar2(2000),
	opendate varchar2(1000),
	parking varchar2(1000),
	restdate varchar2(1000),	
	expagerange varchar2(1000),
    useseason varchar2(1000),
	usetime varchar2(1000), -- 12,
    agelimit varchar2(2000), -- 15 start 
    bookingplace varchar2(2000),
    discountinfofestival varchar2(4000),
    eventenddate varchar2(2000),
    eventhomepage varchar2(1000),
    eventplace varchar2(1500),
    eventstartdate varchar2(2000),
    festivalgrade varchar2(1000),
    placeinfo varchar2(4000),
    playtime varchar2(4000),
    program varchar2(4000),
    spendtimefestival varchar2(2500),
    sponsor1 varchar2(1000),
    sponsor1tel varchar2(1000),
    sponsor2 varchar2(1000),
    sponsor2tel varchar2(1000),
    subevent varchar2(4000),
    usetimefestival varchar2(1500), -- 15 end
    chkbabycarriageculture varchar2(2000),   -- 14 ��ȭ ���� ����
    accomcountculture varchar2(2000),   -- ������ ���� 360��
    chkcreditcardculture varchar2(2000), --
    chkpetculture varchar2(1000), --
    infocenterculture varchar2(2000), -- ??
    parkingculture varchar2(2000), -- ��������
    parkingfee varchar2(3000), 
    usefee varchar2(4000),
    restdateculture varchar2(2500),
    spendtime varchar2(2000),
    scale varchar2(1000),
    usetimeculture varchar2(2000), -- 14 ���� ��
    distance varchar2(1500), -- 25��������     , �Ÿ�
    infocentertourcourse varchar2(1500),
    schedule varchar2(1500),
    theme varchar2(1000),
    taketime varchar2(1000), -- 25������       , �ҿ�ð�
    accomcountleports varchar2(2000), -- 28 �������� --���ð�
    chkbabycarriageleports varchar2(1500), 
    chkcreditcardleports varchar2(1500), -- 
    chkpetleports varchar2(1500), --
    expagerangeleports varchar2(1000), -- �̿밡�� ����
    infocenterleports varchar2(1500), -- ����������ȭ
    parkingfeeleports varchar2(2000),
    parkingleports varchar2(1000), -- ��������
    openperiod varchar2(3000),
    reservation varchar2(1000), -- ������ȭ
    restdateleports varchar2(1300), -- ���³�
    usefeeleports varchar2(2000),
    scaleleports varchar2(2000),
    usetimeleports varchar2(2000) -- �̿�ȳ� 28���� ��
);