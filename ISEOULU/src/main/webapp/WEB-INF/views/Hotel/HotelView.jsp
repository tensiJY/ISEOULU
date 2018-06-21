<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<title>호텔 정보</title>
	<!----------------- 공통 css, meta태그, 스크립트 호출 ---------------->
	 <jsp:include page="../Common/commonheader.jsp"></jsp:include>
	<style type="text/css">
	#imgDivSize : {height: 540px; width: auto; overflow: hidden;}
	#imgDivSize:HOVER{cursor: pointer;} 	
	#imgDivSize img {transition: 0.5s ease;-o-transition: 0.5s ease;-webkit-transition: 0.5s ease;width: 100%; box-shadow: 0 0 #fff;height:540px;}
	.text10 {font-size: 15px;margin-bottom: 2px;line-height: 20px;color: #535151;}   
	.imgDivSize2 {width: auto;height: 240px;overflow: hidden;}
	.imgDivSize2 img{width: 100%;height : 235px;}
	.color1:HOVER {cursor: pointer;color: #558ed5;}    
 	.btn:hover {cursor: pointer;} 
	</style> 
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f25b060d2f4516f13c808ae5cc65219c"></script>
	<script type="text/javascript">


		$(document).ready(function(){
			$().UItoTop({ easingType: 'easeOutQuart' });
			$('#stuck_container').tmStickUp({});
			$('.gallery .gall_item').touchTouch();
			$(".btn_search").on('click', function(){
				var sgCode = $("#Adults").val();
				$(".btn_search").attr('href', '../Hotel/List.do?sgCode='+sgCode);
			});			
			
			var sgCode = $("#sgCode").val();
			$("#Adults").val(sgCode);
		}); //document end 
		
		//	이미지 확대
		function imgOpen(a){window.open(a, "", "width=800 height=800");}	
	</script>
</head><!-- head end -->

<body class="page1" id="top">
<!------------------------- header ------------------------->
<header>
	<!--------------------- Stuck menu --------------------->
	<jsp:include page="../Common/utilmenu.jsp"></jsp:include>
</header>         
<!------------------------- Content ------------------------->
<section class="content">
	<div class="container">
				<c:if test="${HVO.mapx ne 9999}"><input type="hidden" id="mapx" value="${HVO.mapx}"><input type="hidden" id="mapy" value="${HVO.mapy}"></c:if>
				<input type="hidden" id="sgCode" value="${HVO.sgCode}">
		<!--------------------- 상단 지역 검색 영역 --------------------->
		<jsp:include page="../Common/searcharea.jsp"></jsp:include>
		
		<!-------------------- top. 상세정보 view 영역 start -------------------->
		<div class="row">
			<div class="grid_12">
				<h3>${HVO.title} (조회수 : ${HVO.readcount}) <a href="../Board/WriteForm.do?contentId=${HVO.contentid}"><img src="../resources/images/Sketch Book.png"></a></h3>
			</div>
		</div>
		<div class="row" >
			<div class="grid_55"  align=center>
				<c:if test="${HVO.firstimage eq 'empty'}">
				<img src="" alt="" class="img_inner fleft inn__1">
				</c:if>
				<c:if test="${HVO.firstimage ne 'empty'}">
				<div id="imgDivSize"><img id="vimg" src="${HVO.firstimage}" alt="" onClick='imgOpen("${HVO.firstimage}")'></div>
				</c:if>
			</div>
		
			<div class="grid_65" style="overflow:auto;  height:540px;">
				<div class="blog">
					<table>
					<tbody>
						<tr>
							<td>위치</td>
							<td><p class="blog_content">${HVO.addr1}</p></td>
						</tr>
						<tr>
							<td>문의/안내</td>
							<c:if test="${HVO.tel eq 'empty'}">
								<td>정보가 없습니다.</td>
							</c:if>
							<c:if test="${HVO.tel ne 'empty'}">
								<td>${HVO.tel}</td>
							</c:if>
						</tr>
						<tr>
							<td>체크인</td>
							<c:if test="${HVO.checkintime eq 'empty'}">
								<td>정보가 없습니다.</td>
							</c:if>
							<c:if test="${HVO.checkintime ne 'empty'}">
								<td>${HVO.checkintime}</td>
							</c:if>
						</tr>
						<tr>
							<td>체크아웃</td>
							<c:if test="${HVO.checkouttime eq 'empty'}">
								<td>정보가 없습니다.</td>
							</c:if>
							<c:if test="${HVO.checkouttime ne 'empty'}">
								<td>${HVO.checkouttime}</td>
							</c:if>
						</tr>						
					</tbody>
					</table>
				</div>
				<div class="clear"></div>
				<div class="blog_title">개요</div>
				<div class="blog">
					<table>
					<tbody>
						<tr>
							<td>개요</td>
							<td><p class="blog_content">${HVO.overview}</p></td>
						</tr>
						<tr>
							<td>정보제공자</td>
							<td>한국관광공사</td>
						</tr>
					</tbody>
					</table>
				</div>
				<div class="clear"></div>
				<div class="blog_title">이용안내</div>
				<div class="blog">
					<table>
					<tbody>
						<c:if test="${HVO.foodplace ne 'empty' }">
						<tr>
							<td>식당정보</td>
							<td>${HVO.foodplace}</td>
						</tr>
						</c:if>
					
						<c:if test="${HVO.parkinglodging ne 'empty' }">
						<tr>
							<td>주차시설</td>
							<td>${HVO.parkinglodging}</td>
						</tr>	
						</c:if>
							
						
						<c:if test="${HVO.pickup ne 'empty'}">
						<tr>
							<td>픽업</td>
							<td>${HVO.pickup}</td>
						</tr>	
						</c:if>
						
					
						<c:if test="${HVO.reservationlodging ne 'empty'}">
						<tr>
							<td>예약문의</td>
							<td>${HVO.reservationlodging}</td>
						</tr>
						</c:if>
					
						<c:if test="${HVO.reservationurl ne 'empty'}">
						<tr>
							<td>예약하기</td>
							<td>${HVO.reservationurl} <br></td>
						</tr>						
						</c:if>
					
						<c:if test="${HVO.roomcount ne 'empty' }">
						<tr>
							<td>방 수</td>
							<td>${HVO.roomcount}</td>
						</tr>						
						</c:if>

						<c:if test="${HVO.roomtype ne 'empty' }">
						<tr>
							<td>방 정보 </td>
							<td>${HVO.roomtype}</td>
						</tr>						
						</c:if>
						
						<c:if test="${HVO.subfacility ne 'empty' }">
						<tr>
							<td>이용시설</td>
							<td>${HVO.subfacility}</td>
						</tr>						
						</c:if>																			
					</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="clear sep__1"></div>
		<!-------------------- top. 상세정보 view 영역 end -------------------->
		
		<!-------------------- middle. 주변 업체정보 영역 start -------------------->
		<div class="row content_bg_blue">
			<div class="grid_12">
				<!-- <h2>Find Us</h2> -->
				<div class="blog_title text3">
					<!-- href 화면 돌아가서 span으로 수정했구요.. id값 지정해서 밑에서 이벤트 처리함 -->
					<span onClick="changeMarker('travel')" id="tid"><span class="map_cate_travel_off">여행지</span></span>&nbsp;&nbsp;
					<span onClick="changeMarker('food')" id="fid"><span class="map_cate_restaurant_off">음식점</span></span>&nbsp;&nbsp;
					<span onClick="changeMarker('hotel')" id="hid"><span class="map_cate_hotel_off">숙박</span></span></div>
				<div class="map">
					<div class="row">
						<div class="grid_7">
							<figure class="">
								
								<!-- 다음 지도  -->	
								<div id="map" style="width:100%;height:540px;"></div>
							 
					
							</figure>
						</div>
						<!-- 스크롤 달았어요 -->
						<div class="grid_45 content_bg_white margin-right" style="overflow:auto;  height:540px;"  >
						<!-- 관광 리스트 -->
						<c:forEach var="data" items="${RLIST}">
							<c:if test="${data.contenttypeid ne 39 && data.contenttypeid ne 32}">
								<div class="first">		
							</c:if>
							<c:if test="${data.contenttypeid eq 32}">
								<div class="third">
							</c:if>
							<c:if test="${data.contenttypeid eq 39}">
								<div class="second">
							</c:if>
									<div class="clear sep__2"></div>
									<div class="margin-col">
										<div class="block3">
											<!-- 사진이 없을 경우 -->
											
											<c:if test="${data.firstimage eq 'empty'}"><img src="../resources/images/page4_img4.jpg" alt="" class="img_inner noresize fleft"></c:if>
										<!-- 사진이 있을 경우 -->
											<c:if test="${data.firstimage ne 'empty'}">
											<div class="imgDivSize2" ><img src="${data.firstimage}" alt="" class="img_inner noresize fleft"></div>
											</c:if>
											<div class="imgDivSize2_1" style="float: left">		
												<div class="extra_wrapper">
												<!-- 관광 -->
												
												<c:if test="${data.contenttypeid ne 39}">
													<c:if test="${data.contenttypeid ne 32}">
													<span class="txt_icon_mappoint">A</span>&nbsp;&nbsp;<span class="txt_icon_travel">여행지</span>
													<div class="text10 color1">
														<a href="../Travel/View.do?sgCode=${data.siguncode}&contentid=${data.contentid}&title=${data.title}&mapx=${data.mapx}&mapy=${data.mapy}">${data.title} [${data.round} m]</a>
													</div>
													</c:if>
												</c:if>
			
												<!-- 음식-->
												<c:if test="${data.contenttypeid eq 39}">
													<span class="txt_icon_mappoint">B</span>&nbsp;&nbsp;<span class="txt_icon_restaurant">음식점</span>
													<div class="text10 color1"><a href="../Food/View.do?sgCode=${data.siguncode}&contentid=${data.contentid}&title=${data.title}&mapx=${data.mapx}&mapy=${data.mapy}">${data.title} [${data.round} m]</a></div>
												</c:if>
												
												<!-- 호텔 -->
												<c:if test="${data.contenttypeid eq 32}">
													<span class="txt_icon_mappoint">C</span>&nbsp;&nbsp;<span class="txt_icon_hotel">숙박</span>
													<div class="text10 color1"><a href="../Hotel/View.do?sgCode=${data.siguncode}&contentid=${data.contentid}&title=${data.title}&mapx=${data.mapx}&mapy=${data.mapy}">${data.title} [${data.round} m]</a></div></c:if>
													<c:if test="${data.addr1 eq 'empty'}"></c:if>
													<c:if test="${data.addr1 ne 'empty'}">${data.addr1}<br /></c:if>		
													<c:if test="${data.tel eq 'empty' }"></c:if>
													<c:if test="${data.tel ne 'empty' }">${data.tel}<br /></c:if>
													<c:if test="${data.round gt 700}"><span class="color1 ul" data-toggle="" data-target=""onClick='searchWay(this,${HVO.mapx},${HVO.mapy},${data.mapx},${data.mapy}, "${HVO.title}", "${data.title}")'>여기까지 이동 정보&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▼</span><span class="search"></span></c:if>	
												</div> <!-- extra_wrapper end -->
											</div>	
										</div>
									</div>
									<div class="clear sep__1"></div>
								<c:if test="${data.contenttypeid eq 39}">
								</div>
								</c:if>
								<c:if test="${data.contenttypeid eq 32}">
								</div>
								</c:if>
								<c:if test="${data.contenttypeid ne 39 && data.contenttypeid ne 32}">
								</div>		
								</c:if>
							</c:forEach>
							
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-------------------- middle. 주변 업체정보 영역 end -------------------->

		<!-------------------- bottom. 교통정보 영역 start -------------------->
		<div class="row content_bg_blue">
			<div class="grid_12">
				<!-- <h2>Find Us</h2> -->

				<div class="map">
					<div class="row">
						<div class="grid_115">
							<div class="content_bg_lightblue text4">
								주변 교통정보&nbsp;&nbsp;▶&nbsp;&nbsp;
								<!-- span 으로 수정함 -->
								<span class="btn" onClick='pointSearch(${HVO.mapx},${HVO.mapy},2)'>지하철</span> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; 
								<span class="btn" onClick='pointSearch(${HVO.mapx},${HVO.mapy},1)'>버스</span>
							</div>
							<figure class="">
								<div id="map2" style="width:100%;height:540px;"></div>
							</figure>
						</div>
						<div class="clear sep__2"></div>
					</div>
				</div>
			</div>
		</div>
		<!-------------------- bottom. 교통정보 영역 end -------------------->
		<!-- 모달 팝업 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">Modal title</h4>
		      </div>
		      <div class="modal-body">
			
		      </div>
		      <div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			  </div>
		    </div>
		  </div>
		</div>
	</div>
</section>

<script type="text/javascript">
	//	좌표를 담을 배열.
	var travelPositions = [];	//	여행좌표
	var foodPositions = [];	//	음식좌표
	var hotelPositions = [];	//	호텔좌표

	//	호텔, 여행, 음식 마커 객체를 가지고 있을 배열
	var hotelMarkers = [];
	var travelMarkers = [];
	var foodMarkers = [];
	
	//	click이벤트를 컨트롤할 변수 	0지도 off	1지도 on
	var hoEnt = 1;
	var trEnt = 1;
	var foEnt = 1;

	//	기본 지도를 그린다.
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new daum.maps.LatLng(${HVO.mapy}, ${HVO.mapx}),
	    level: 5// 지도의 확대 레벨
	};

	var map = new daum.maps.Map(mapContainer, mapOption);
	var markerPosition  = new daum.maps.LatLng(${HVO.mapy}, ${HVO.mapx});
	var marker = new daum.maps.Marker({
	    position: markerPosition
	});
	marker.setMap(map);
	//	기본지도 end

	//각각의 포지션 배열에 좌표와 타이틀을 집어 넣는다 
	<c:forEach var="t" items="${TLIST}" varStatus="status">
		travelPositions[${status.index}] = {
				latlng : new daum.maps.LatLng(${t.mapy}, ${t.mapx}),
				title : "${t.title}"
		}
	</c:forEach>

	<c:forEach var="h" items="${HTLIST}" varStatus="status">
		hotelPositions[${status.index}] = {
			latlng : new daum.maps.LatLng(${h.mapy}, ${h.mapx}),
			title : "${h.title}"
		};
	</c:forEach>	

	
	<c:forEach var="f" items="${FLIST}" varStatus="status">
		foodPositions[${status.index}] = {
			latlng : new daum.maps.LatLng(${f.mapy}, ${f.mapx}),
			title : "${f.title}"
		};
	</c:forEach>

	
	//마커이미지의 주소와, 크기, 옵션으로 마커 이미지를 생성하여 리턴하는 함수입니다
	var tImageSrc = '../resources/images/mblue.png';
	var fImageSrc = '../resources/images/mred.png';
	var hImageSrc = '../resources/images/mgreen.png';

 	//	호텔 마커를 생성하거 호텔 마커 배열에 추가한다.
 	//	이미지 사이즈 설정, 마커이미지 설정, 마커(포지션, 타이틀 이미지 설정)  마커 배열에 담는다.
	for (var i = 0; i < hotelPositions.length; i ++) {
	    var imageSize = new daum.maps.Size(24, 35); 
	    var markerImage = new daum.maps.MarkerImage(hImageSrc, imageSize); 
	        
	    var marker = new daum.maps.Marker({
	      	position: hotelPositions[i].latlng,
	        title : hotelPositions[i].title,
	        image : markerImage 
	    });
	
	   hotelMarkers.push(marker);
	};
 
	//	여행 마커를 생성하거 여행 마커 배열에 추가한다.
	for (var i = 0; i < travelPositions.length; i ++) {
	    var imageSize = new daum.maps.Size(24, 35); 
	    var markerImage = new daum.maps.MarkerImage(tImageSrc, imageSize); 
	    var marker = new daum.maps.Marker({
	       /*  map: map, 	이 주석을 지우면 바로 맵에 나타난다.*/ 
	        position: travelPositions[i].latlng,
	        title : travelPositions[i].title,
	        image : markerImage 
	    });
	   travelMarkers.push(marker);   //	push는 자바스크립트 배열에 사용하는 것으로써, 맨끝에 집어넣는다.
	} 
	
	//	음식 마커를 생성하고 음식 배열에 추가한다.
	for (var i = 0; i < foodPositions.length; i ++) {
	    var imageSize = new daum.maps.Size(24, 35); 
	    var markerImage = new daum.maps.MarkerImage(fImageSrc, imageSize); 
	    var marker = new daum.maps.Marker({
	    /*     map: map, */ 
	        position: foodPositions[i].latlng,
	        title : foodPositions[i].title,
	        image : markerImage 
	    });
	    foodMarkers.push(marker);
	} 
	
	//	호텔 마커들의 지도 표시 여부를 설정하는 함수
 	function setHotelMarkers(map){
	 for (var i = 0; i < hotelMarkers.length; i++) {  
	    	hotelMarkers[i].setMap(map);
	    } ;   
 	}
 	
	//	
 	function setTravelMarkers(map){
 		 for (var i = 0; i < travelMarkers.length; i++) {  
 			travelMarkers[i].setMap(map);
 		 } ;   
 	}
	
 	function setFoodMarkers(map){
		 for (var i = 0; i < foodMarkers.length; i++) {  
			 foodMarkers[i].setMap(map);
		 } ;   
	}
 	
 	var hotel = null;
 	
 	
 	//	click event 함수,	type = 'hotel', 'travel', 'food' 이며 각각 눌렀을 경우 if문 실행
 	function changeMarker(type){
	//	클릭한 곳이 hotel 단추 일경우 if 조건인 hotel을 실행
 		if(type=='hotel'){
	  		 
 			if(hoEnt==0){	// 	마커를 표시하고 지우는 hoEnt 변수를 설정한다,
 	//	hoEnt 값이 0일 경우, setHotelMarkers 함수에 null을 넣어 마커를 지운다. 
 			
 				setHotelMarkers(null);
 				$("#hid").find(".map_cate_hotel_on").remove();
 				$("#hid").append("<span class='map_cate_hotel_off'>숙박 </span>");
 				$(".third").hide();
 				hoEnt++;	//	마커를 지웠으니, hoEnt++ 시켜서 1로 만들어 놓는다. 
 	//	다시 클릭하거나, hoEnt 값이 1일 경우 마커를 생성한다. 			
 			}else {
 				$(".third").show();
 				setHotelMarkers(map);
 				$("#hid").find(".map_cate_hotel_off").remove();
 				$("#hid").append("<span class='map_cate_hotel_on'>숙박 </span>"); 
 				
 				hoEnt--;	//	마커를 생성했으니 숙박 색깔을 돌려 놓는다.
 	//	마커를 생성했으니 hoEnt-- 시켜서 0으로 만들어 놓는다.			
 			}
 			
 		}else if(type=='travel'){
 			if(trEnt==0){
 				
 				setTravelMarkers(null);
 				/* $(".map_cate_travel_on").text("여행지  "); */
 				$("#tid").find(".map_cate_travel_on").remove();
 				$("#tid").append("<span class='map_cate_travel_off'>여행지 </span>");
 				trEnt++;
 				$(".first").hide();
 			} else{
 				
 				setTravelMarkers(map);
 				/* $(".map_cate_travel_on").text("여행지 V");  */
 				$("#tid").find(".map_cate_travel_off").remove();
 				$("#tid").append("<span class='map_cate_travel_on'>여행지</span>");
 				trEnt--;
 				$(".first").show();
 			}
 			
 		}else if(type=='food'){
 			
 			if(foEnt==0){
 				
 				setFoodMarkers(null);
 				$("#fid").find(".map_cate_restaurant_on").remove();
 				$("#fid").append("<span class='map_cate_restaurant_off'>음식점 </span>");
 				foEnt++;
 				$(".second").hide();
 			}else{
 				
 				setFoodMarkers(map);
 				$("#fid").find(".map_cate_restaurant_off").remove();
 				$("#fid").append("<span class='map_cate_restaurant_on'>음식점 </span>"); 				
 				foEnt--;
 				$(".second").show();
 			}
 			
 		}
 	}
 	
 	
	changeMarker('hotel');
	changeMarker('travel');
	changeMarker('food');

//odsay 주영=InpNO/wfgBBD73C6CABx8ieK5nXEDbounYFOPEpAoXY

//	출발지 와 도착지
function searchWay(my, sx, sy , ex ,ey, at, bt){
	//	모달 속성 지정하기
	$(my).attr('data-toggle', 'modal');
	$(my).attr('data-target', '#myModal');
	
	var title = '<span style="font-size:15px; color:red;">'+at + '에서 ' + bt + '까지 이동 정보' + '</span>';
	$("#myModalLabel").html("");
	// body 내용 비우기
	$(".modal-body").html("");
	
	
 	$.ajax({
		url : 'https://api.odsay.com/api/searchPubTransPath',
		type : 'get',
		data : 'SX='+sx+'&SY='+sy+'&EX='+ex+'&EY='+ey+'&apiKey=EP6opbh6Snt8fsH6J/Gb3dxsmCmTj3APxjd/oTeK8o0'+'&SearchPathType=1',
		dataType : 'json',		
		success : function(data){
			var result = data.result;
			
			//	정보가 없을때 찾으시는 정보가 없다고 알려준다.
			if(result=="" ||  result == null ){
				$("#myModalLabel").html(title);
				$(".modal-body").append("<span>찾으시는 정보가 없습니다.</span>");
			}else {
				
				// 길찾기 api 호출 
				/*  searchPubTransPathAJAX(sx, sy, ex, ey); */ 
				
				var path = data.result.path;	//	가장가까운 거리의 정보를 알기위함
				var subPath = path[0].subPath;	//	가장가까운 거리의 subPath 출발~도착까지의 정보
				
				var payment = path[0].info.payment;					// 금액
				var totaltime = path[0].info.totalTime;				// 총 소요시간
				var totalDistance = path[0].info.totalDistance;		// 총 거리
				var firstStartStation = path[0].info.firstStartStation;// 출발역
				var lastEndStation = path[0].info.lastEndStation;	// 도착역
				
				var temp = "";					//	이동 정보를 한번에 저장하기 위한 변수
				temp += firstStartStation + " ~ " + lastEndStation ;
				temp += payment + " 원 " + totaltime + " 분 "  + totalDistance + " m" +"<br><br>";
											
				//	start	1 걸어서역까지	start!=end	환승	 start=end	걸어서 목적지까지..
				var subStart = 1;	//	걸어서 역까지 나타내는 변수
				var subEnd = 0 ;	//	환승과 목적지를 구분하는 변수
				
				//	걸어가는 횟수를 end변수에 ++ 시켜주어서, 환승+도착지까지 걷는 횟수를 카운트 시켜준다.
				for(var i=0; i<subPath.length;i++){
					if(subPath[i].trafficType==3){
						subEnd++;
					}
				}
								
				$.each(subPath, function(index, item){	
					var way = "";					//	지하철 1	/	도보3
					var tType = item.trafficType; 	//	지하철인지 도보(출발지, 환승, 목적지) 구분하기 위함.
					
					var distance = "";	 	// 도보 거리										
					var sectionTime ="";	// 소요시간
					var lane ="";			// 지하철 노선
					var stationCount ="";	// 정거장
					var startName = ""; 	// 출발역
					var endName = "";		// 마지막역
					
					if(tType==1){
						lane = item.lane[0].name;			//	지하철 노선번호
						stationCount = item.stationCount;	//	정거장
						startName = item.startName;			//	출발역
						endName = item.endName;				//	도착역
						sectionTime = item.sectionTime;		//	소요시간
						temp += "<b>지하철</b> : " + '['+lane +'] '; 			
						temp += startName + " ~ " + endName + "( " + stationCount +" 정거장)" + "( " + sectionTime + "분 )" +"<br><br>";
						
					}else if(tType==2){
					}else if(tType==3){
						 
						if(subStart == 1){
							way += "<b>도      보</b> : " + " 걸어서 "+ firstStartStation + "역까지  도보로이동 ";
							distance = item.distance + " m  ";
							sectionTime = ""+ item.sectionTime + " 분  " +"<br><br>";
						 	subStart++;
						}else if(subStart != subEnd){
							way += "<b>도      보</b> : " + " 환승 " + "<br><br>";
							distance = "";
							sectionTime = "";
							subStart++;
						}else if(subStart == subEnd){
							way += "<b>도      보</b> : " + lastEndStation + " 내려서 목적지까지 ";
							distance = item.distance + " m  ";
							sectionTime = ""+ item.sectionTime + " 분  " +"<br><br>";
						} 
				
						temp += way + distance + sectionTime;
					}
				});	//	정보를 닫을수 있도록 이벤트 함수 지정 searhDel(this) 
				
				$("#myModalLabel").append(title);
				$(".modal-body").append(temp);
			
			}
		},
		error : function(){
			console.log('error');
		}
	}); // ajax 종료
}//searchWay()함수 종료


function searchDel(delDiv){
	
	$(delDiv).parent().remove();
}




//	길찾기에 지도를 그린다.
var cont = document.getElementById('map2'),	//맵 컨테이너
	opt = {
	     center: new daum.maps.LatLng(${HVO.mapy}, ${HVO.mapx}),
	     level: 3
	};

var map2 = new daum.maps.Map(cont, opt);

var mkp  = new daum.maps.LatLng(${HVO.mapy}, ${HVO.mapx});
var mk = new daum.maps.Marker({
    position: opt.center,
    title : "${HVO.title}",
});

mk.setMap(map2)	// 길찾기 2번맵에 마커를 올린다. */ 


var station = [];		//	버스정류장 좌표와 타이틀을 가지고 있을 배열
var subway = []; 		// 지하철 좌표와 타이틀을 가지고 있을 배열
var stationMarker = []; // 버스정류장 마커를 가지고 있을 배열
var subwayMarker = [];	// 지하철 마커를 가지고 있을 배열

var stationCnt = 0;		//	버스정류장 이벤트를 제어하는 변수
var subwayCnt = 0;		//	지하철 이벤트를 제어하는 변수


//	x좌표, y좌표, 정류장 종류	c=2 지하철 , 1버스정류장
function pointSearch(a,b,c){
	var stationClass = c;
	if(stationClass=='1'){	
		if(stationCnt !=0){	//	이벤트를 제어하는 변수의 값이 0이면 마커가 없다는 것이며 마커를 그린다.
			return map2DelteMarker('1');//	1이면 버스 정류장 마커가 존재하기 때문에.. 지운다.
		}	
	}else if(stationClass=='2'){
		if(subwayCnt !=0){
			return map2DelteMarker('2');
		}
	}
	
	var radius='250';	//	중심이 되는 좌표의 반경 250m 대중교통정보를 가져온다.
	$.ajax({
		url :'https://api.odsay.com/api/pointSearch',
		type : 'get',
		data : 'x='+a+'&y='+b+'&apiKey=EP6opbh6Snt8fsH6J/Gb3dxsmCmTj3APxjd/oTeK8o0'+
			   '&radius='+radius+'&stationClass='+stationClass,
		dataType :'json',
		error : function(){
			console.log('pointSearch() error');
		},
		success : function(data){
			console.log(data.result);
			//	데이터가 없을 경우
			if(data.result.count == 0 || data.result == null){
				if(stationClass=='1'){
					return	alert(radius + "m " + '버스 정류장 정보가 없습니다.');
				}else if(stationClass=='2'){
					return	alert(radius + "m " + '지하철 역 정보가 없습니다.');
				}
			}
			
			$.each(data.result.station, function(index, item){
				if(stationClass=='1'){	//	버스
				station[index] = {
						latlng : new daum.maps.LatLng(item.y, item.x),
						title : item.stationName					
					}
								
				}else if(stationClass=='2'){	//지하철
				subway[index] = {
						latlng : new daum.maps.LatLng(item.y, item.x),
						title : item.laneName+"\r\n"+item.stationName+"역"					
					}
				}
			});	
			makeSet(stationClass);	//	마커를 그리기 위해 설정하는 함수
		}//	success end
	})// ajax end
}// pointSearch 함수 종료

var stationImageSrc = '../resources/images/bus.png';
var subwayImageSrc = '../resources/images/subway.png';


function makeSet(stationClass){	//	코드에 따라서  1 버스	 2지하철
	if(stationClass == '1'){
		for(var i=0; i<station.length; i++){
			  var imageSize = new daum.maps.Size(24, 35); 
			  var markerImage = new daum.maps.MarkerImage(stationImageSrc, imageSize); 
			  var marker = new daum.maps.Marker({
			      	position: station[i].latlng,
			        title : station[i].title,
			        image : markerImage 
			  });
			  //	생성된 마커를 배열에 저장한다.
		  stationMarker.push(marker);
		};
		stationMakeMarker(map2);//	마커를 지도에 노출시키기 위해서 함수를 실행
		
	}else if(stationClass == '2'){
		for(var i=0; i<subway.length; i++){
			  var imageSize = new daum.maps.Size(24, 35); 
			  var markerImage = new daum.maps.MarkerImage(subwayImageSrc, imageSize); 
			  var marker = new daum.maps.Marker({
			      	position: subway[i].latlng,
			        title : subway[i].title,
			        image : markerImage 
			  });
		  subwayMarker.push(marker) 	//	생성한 마커를 배열에 저장한다. 
		};	 
	  subwayMakeMarker(map2);			//	마커를 그린다.
	}
}//	마커를 그리는 함수 종료


function stationMakeMarker(map2){
	stationCnt++;	//	지도에 그렸으므로 event컨트롤 변수를 증감시킨다.
	
	for (var i = 0; i < stationMarker.length; i++) {  
		stationMarker[i].setMap(map2);
	}
}
//	마커를 지도에 나타내는 함수  
function subwayMakeMarker(map2){
	subwayCnt++;	//	지도에 그렸으므로 event컨트롤 변수를 증감시킨다.
	//	마커의 길이 만큰 반복하면서 지도에 마커를 노출시킨다.
	for (var i = 0; i < subwayMarker.length; i++) {  
		subwayMarker[i].setMap(map2);
	};
}  
//	생성된 마커를 지우는 함수  
function map2DelteMarker(type){
	
	if(type=='1'){		//	버스정류장
		stationCnt--;	//	이벤트를 제어하는 변수를 감소시킨다.
		for (var i = 0; i < stationMarker.length; i++) {  
			stationMarker[i].setMap(null);
		}//	생성된 마커의 길이 사이즈만큼 지도에 null을 넣어 마커를 지운다.
		
		
	}else if(type=='2'){//	지하철
		subwayCnt--;		
		for (var i = 0; i < subwayMarker.length; i++) {  
			subwayMarker[i].setMap(null);
		};
	}
	
}  
  
  
  
  
  
  
  
  
  
  
  
  
  
///////////////////////////////////////////////////////////////////////
//길찾기 api 함수
function searchPubTransPathAJAX(sx, sy, ex, ey){
	
	var xhr = new XMLHttpRequest();
	//ODsay apiKey 입력
	var url = "https://api.odsay.com/api/searchPubTransPath?SX="+sx+'&SY='+sy+'&EX='+ex+'&EY='+ey+'&apiKey=EP6opbh6Snt8fsH6J/Gb3dxsmCmTj3APxjd/oTeK8o0';
	xhr.open("GET", url, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
		console.log( JSON.parse(xhr.responseText) ); // <- xhr.responseText 로 결과를 가져올 수 있음
		//노선그래픽 데이터 호출
		callMapObjApiAJAX((JSON.parse(xhr.responseText))["result"]["path"][0].info.mapObj, sx, sy, ex, ey);
		}
	}
}

//노선그래픽함수
function callMapObjApiAJAX(mabObj, sx, sy, ex, ey){
	var xhr = new XMLHttpRequest();
	//ODsay apiKey 입력
	var url = "https://api.odsay.com/api/loadLane?mapObject=0:0@"+mabObj+"&apiKey=EP6opbh6Snt8fsH6J/Gb3dxsmCmTj3APxjd/oTeK8o0";
	xhr.open("GET", url, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var resultJsonData = JSON.parse(xhr.responseText);
			console.log(resultJsonData);
			drawDaumMarker(sx,sy);					// 출발지 마커 표시
			drawDaumMarker(ex,ey);					// 도착지 마커 표시
			drawDaumPolyLine(resultJsonData);		// 노선그래픽데이터 지도위 표시 */
			// boundary 데이터가 있을경우, 해당 boundary로 지도이동
		 	if(resultJsonData.result.boundary){
					var boundary = new daum.maps.LatLngBounds(
			                new daum.maps.LatLng(resultJsonData.result.boundary.top, resultJsonData.result.boundary.left),
			                new daum.maps.LatLng(resultJsonData.result.boundary.bottom, resultJsonData.result.boundary.right)
			                );
					map2.panTo(boundary);
			} 
		}
	}
}// 함수 종료

//지도위 마커 표시해주는 함수	
var m2 = null;
function drawDaumMarker(x,y){
	//	2번맵에 기존 생성된 마커를 지워준다
	
	
	if(m2 != null){
		/* m2.setMap(null); */
	}
	
	m2 = new daum.maps.Marker({
	    position: new daum.maps.LatLng(y, x),
	    map: map2
	});
	
}

//노선그래픽 데이터를 이용하여 지도위 폴리라인 그려주는 함수
function drawDaumPolyLine(data){
	var lineArray;
	
	for(var i = 0 ; i < data.result.lane.length; i++){
		for(var j=0 ; j <data.result.lane[i].section.length; j++){
			lineArray = null;
			lineArray = new Array();
			for(var k=0 ; k < data.result.lane[i].section[j].graphPos.length; k++){
				lineArray.push(new daum.maps.LatLng(data.result.lane[i].section[j].graphPos[k].y, data.result.lane[i].section[j].graphPos[k].x));
			}
			
		//지하철결과의 경우 노선에 따른 라인색상 지정하는 부분 (1,2호선의 경우만 예로 들음)
			if(data.result.lane[i].type == 1){
				var polyline = new daum.maps.Polyline({
				    map: map2,
				    path: lineArray,
				    strokeWeight: 3,
				    strokeColor: '#003499'
				});
			}else if(data.result.lane[i].type == 2){
				var polyline = new daum.maps.Polyline({
				    map: map2,
				    path: lineArray,
				    strokeWeight: 3,
				    strokeColor: '#37b42d'
				});
			}else{
				var polyline = new daum.maps.Polyline({
				    map: map2,
				    path: lineArray,
				    strokeWeight: 3
				});
			}
		}
	}
};


</script>


<!---------------------- footer ---------------------->
<jsp:include page="../Common/footer.jsp"></jsp:include>
</body>
</html>

