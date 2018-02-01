<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<title>호텔 목록</title>
	<!----------------- 공통 css, meta태그, 스크립트 호출 ---------------->
	<jsp:include page="../Common/commonheader.jsp"></jsp:include>
	<style>
	.gall_item img { height:250px;}
	
	ul {	text-align:center;  }  
	ul li {display:inline;vertical-align:middle;}  
	ul li a {display:-moz-inline-stack;  /*FF2*/ 
    display:inline-block;  
    vertical-align:top;  
    padding:4px;  
    /* margin-right:3px;   */
    width:40px !important;  
    color:#000;  
    font:bold 12px tahoma;  
    border:3px solid #eee;  
    text-align:center;  
    text-decoration:none;  
    width /**/:40px;    /*IE 5.5*/  
}  
ul li a.now {  
    color:#fff;  
    background-color:#f40;  
    border:3px solid #f40;  
}  
ul li a:hover, ul li a:focus {  
    color:#fff;  
    border:3px solid #558ed5;  
    background-color:#558ed5;  
}  
	</style>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fdc7e7b044cb2414d704eacb4a72844b"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$().UItoTop({ easingType: 'easeOutQuart' });
			$('#stuck_container').tmStickUp({});
			$('.gallery .gall_item').touchTouch();
			//	검색
			$(".btn_search").on('click', function(){
				var sgCode = $("#Adults").val();
			
				$(".btn_search").attr('href', '../Hotel/List.do?sgCode='+sgCode);
			});
			
			$("#Adults").val("${sgCode}");

			
		<c:if test="${empty HLIST}">
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new daum.maps.LatLng(37.5009049, 126.8846297), // 지도의 중심좌표
			        level: 1
			    };
			var map = new daum.maps.Map(mapContainer, mapOption); 
			var markerPosition  = new daum.maps.LatLng(37.5009049, 126.8846297);
			var marker = new daum.maps.Marker({
			    position: markerPosition
			});
			marker.setMap(map);
			var iwContent = '<div style="padding:5px;">경영기술개발원교육센터 <br>' +
			'문의 전화 : 02-857-6300 <br>' + 
			'<a href="http://www.iedu.or.kr" style="color:blue" target="_blank">수강하러 가기</a></div>';
		    iwPosition = new daum.maps.LatLng(37.5009049, 126.8846297); //인포윈도우 표시 위치입니다
			
		    var infowindow = new daum.maps.InfoWindow({
		        position : iwPosition, 
		        content : iwContent 
		    });
		    infowindow.open(map, marker); 
		
	</c:if>	
	
	<c:if test="${not empty HLIST}">
	
		var mapx =	$("#mapx").val();
		var mapy =  $("#mapy").val();
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new daum.maps.LatLng(mapy, mapx), // 지도의 중심좌표
	        level: 7
	    };

		var map = new daum.maps.Map(mapContainer, mapOption); 
		
		//	호텔의 좌표와 컨텐트(설명)을 가지고 있을 배열
		var markerPosition = [];		
		//	위도 경도가 없을 때 지도에 보여주지 않는다.	마커포지션이라는 배열에 설명과 좌표 객체를 생성하여 배열에 저장
		//마커의 인포에서 detailView로 갈수있도록 파라메터 지정
		<c:forEach var="data" items="${HLIST}" varStatus="status">
			<c:if test="${data.mapx ne 9999}"> 
			markerPosition[${status.index}]  =  
				{
					content : '<div style="width:100px";><a href="../Hotel/View.do?nowPage=${nowPage}'+
							'&sgCode=${sgCode}&contentid=${data.contentid}'+
							'&title=${data.title}&mapx=${data.mapx}&mapy=${data.mapy}">${data.title}</a></div>'+
							'<div>${data.tel}</div',	
					latlng : new daum.maps.LatLng(${data.mapy}, ${data.mapx})
				};
			</c:if>		 
		</c:forEach>
		
		
		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		
	   	    
		    for (var i = 0; i < markerPosition.length; i ++) {
		        // 마커를 생성합니다
		        var marker = new daum.maps.Marker({
		            map: map, // 마커를 표시할 지도
		            position: markerPosition[i].latlng // 마커의 위치
		           
		        });

		        var infowindow = new daum.maps.InfoWindow({
		            content: markerPosition[i].content,  // 인포윈도우에 표시할 내용
		            removable : true
		        });  
		       
		        daum.maps.event.addListener(marker, 'click', makeClickListener(map, marker, infowindow));

		        	
			 };	// for문 종료
		    
			 function makeClickListener(map, marker, infowindow) {
				    return function() {
				        infowindow.open(map, marker);
				    };
				}

			

	</c:if>
	
	}); // 스크립트 종료
	 
	
    

	</script>
</head>

<body class="page1" id="top">
<!------------------------- header ------------------------->
<header>
	<!--------------------- Stuck menu --------------------->
	<jsp:include page="../Common/utilmenu.jsp"></jsp:include>
</header>        
<!------------------------- Content ------------------------->
<section class="content">
	<div class="container">
		<!--------------------- 상단 지역 검색 영역 --------------------->
		<jsp:include page="../Common/searcharea.jsp"></jsp:include>

		<!-------------------- List start -------------------->
		<div class="row">
			<!-------------------- Left start -------------------->
			<div class="grid_6">
				<c:if test="${not empty HLIST }">
					<c:forEach var="data" items="${HLIST}" varStatus="status">
						<c:if test="${(status.count mod 2) eq 1}">	
						<div class="grid-3">
							<div class="gall_block" >
								<c:if test="${data.firstimage ne 'empty'}">	
									<a href="${data.firstimage}" class="gall_item" ><img src="${data.firstimage}" alt=""></a>
								</c:if>
								<c:if test="${data.firstimage eq 'empty'}">	
									<a href="../resources/images/big3.jpg" class="gall_item"><img src="../resources/images/page2_img2.jpg" alt=""></a>
								</c:if>	
								
								<div class="gall_bot"><div class="text1"><a href="../Hotel/View.do?nowPage=${nowPage}&sgCode=${sgCode}&contentid=${data.contentid}&title=${data.title}&mapx=${data.mapx}&mapy=${data.mapy}">${data.title}</a></div>
									<br>
									주소 : ${data.addr1}<br>
									조회수 : ${data.readcount} <br>
									전화번호 : ${data.tel} <br>
									<!-- <a href="#" class="btn">more</a> -->
									<input type="hidden" id="mapy" value="${data.mapy}">
									<input type="hidden" id="mapx" value="${data.mapx}">
								</div>
							</div>
						</div>
						</c:if>
						
						<c:if test="${(status.count mod 2) eq 0}">
						<div class="grid_3">
							<div class="gall_block">
								<c:if test="${data.firstimage ne 'empty'}">	
									<a href="${data.firstimage}" class="gall_item"><img src="${data.firstimage}" alt=""></a>
								</c:if>
								<c:if test="${data.firstimage eq 'empty'}">	
									<a href="../resources/images/big3.jpg" class="gall_item"><img src="../resources/images/page2_img2.jpg" alt=""></a>
								</c:if>	
								
								<div class="gall_bot"><div class="text1"><a href="../Hotel/View.do?nowPage=${nowPage}&sgCode=${sgCode}&contentid=${data.contentid}&title=${data.title}&mapx=${data.mapx}&mapy=${data.mapy}">${data.title}</a></div>
									<br>
									주소 : ${data.addr1}<br>
									조회수 : ${data.readcount} <br>
									전화번호 : ${data.tel} <br>
									<!-- <a href="#" class="btn">more</a> -->
								</div>
							</div>
						</div>
						<div class="clear sep__1"></div>
						</c:if>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty HLIST}">
					숙박정보가 없습니다
				</c:if>

			</div>
			<!-------------------- Left end -------------------->
			
			<!-------------------- Right start -------------------->
			<div class="map">			
				<div class="grid_6">
					<div class="gall_block">
						<div class="maxheight">
							<figure class="">
								<!--	지도를 그리기 위한 div입니다 -->
								<div id="map" style="width:100%;height:800px;"></div>
								
								<!--	호텔 리스트가 없다면..다른 곳을 보여주자..-->

								
								<!--	호텔 리스트가 있다면!!! 리스트를 보여주자 -->
								
							</figure>
						</div>
					</div>
				</div>
			</div>
			<!-------------------- Right end -------------------->
		</div>
		<!-------------------- List end -------------------->
	</div>
				
<c:if test="${not empty HLIST}">				
<%--	페이지 이동 기능 --%>
	<ul>
		<c:if test="${PINFO.startPage eq 1}">
			<li><a href="#">이전</a></li>
		</c:if>
		<c:if test="${PINFO.startPage ne 1}">
			<li><a href='../Hotel/List.do?nowPage=${PINFO.startPage - 1}&sgCode=${sgCode}'>이전</a></li>
		</c:if> 
		<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
			<li><a href="../Hotel/List.do?nowPage=${page}&sgCode=${sgCode}">${page}</a></li>
		</c:forEach>
				
		<c:if test="${PINFO.endPage eq PINFO.totalPage}">
			<li><a href="#">다음</a></li>
		</c:if>
				
		<c:if test="${PINFO.endPage ne PINFO.totalPage}">
			<li><a href="../Hotel/List.do?nowPage=${PINFO.endPage + 1}&sgCode=${sgCode}">다음</a></li>
		</c:if>
	</ul>
</c:if>			

</section>
<!---------------------- footer ---------------------->
<jsp:include page="../Common/footer.jsp"></jsp:include>
</body>
</html>
