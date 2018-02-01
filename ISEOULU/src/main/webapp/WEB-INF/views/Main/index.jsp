<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<title>I SEOUL U 입니다.</title>
<style type="text/css">
.tablecont {position: relative;z-index: 500;}
.tablecont .td1 {width: 160px;}
.tablecont .td2 {width: 15px;}
.tablecont .td3 {width: 300px;}
.imgcont {width: 160px;height: 120px;overflow: hidden;}
.imgcont img {width: auto;height: auto;max-width: 160px;}
.divcont {margin: 1%;padding: 2%;}.btn4:HOVER {border: 1px solid #969696;color: #ffffff;background-color: #969696;cursor: pointer;}
.ui-tabs {position: relative;padding: .2em;}
.ui-tabs .ui-tabs-nav {margin: 0;padding: .2em .2em 0;}
.ui-tabs .ui-tabs-nav li {list-style: none;float: left;position: relative;top: 0;margin: 1px .2em 0 0;border-bottom-width: 0;padding: 0;white-space: nowrap;}
.ui-tabs .ui-tabs-nav .ui-tabs-anchor {float: left;padding: .5em 1em;text-decoration: none;}
.ui-tabs .ui-tabs-nav li.ui-tabs-active {margin-bottom: -1px;padding-bottom: 2px;}
.ui-tabs .ui-tabs-nav li.ui-tabs-active .ui-tabs-anchor, .ui-tabs .ui-tabs-nav li.ui-state-disabled .ui-tabs-anchor,.ui-tabs .ui-tabs-nav li.ui-tabs-loading .ui-tabs-anchor {cursor: text;}
.ui-tabs-collapsible .ui-tabs-nav li.ui-tabs-active .ui-tabs-anchor {cursor: pointer;}
.ui-tabs .ui-tabs-panel {display: block;border-width: 0;padding: 1em 1.4em;background: none;}
.btn7 {font-size: 20px;}
.btn5 {font-size: 20px;}
.btn6 {font-size: 20px;}
.detailView:HOVER {cursor: pointer;background-color: pink;}
#mOverView {margin: 1%;padding: 1%;}
#jqc span:HOVER{cursor: pointer;}
</style>
<!----------------- 공통 css, meta태그, 스크립트 호출 ---------------->
<jsp:include page="../Common/commonheader.jsp"></jsp:include>
<link rel="stylesheet" href="../resources/css/jqcloud.css">
<script src="../resources/js/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../resources/js/jqcloud-1.0.4.js"></script>
<c:if test="${LOGINVIEW eq 'TRUE'}">
		<script type="text/javascript">
			window.open("../Login/Login.do", "", "width=400, height=300");
		</script>
</c:if>
<script type="text/javascript">
				
		$(document).ready(function(){
		 	$().UItoTop({ easingType: 'easeOutQuart' });
			$('#stuck_container').tmStickUp({});
			$('.gallery .gall_item').touchTouch(); 
			<!-- -->
			$("#board").on('click', function(){
				$(location).attr('href', '../Board/BoardList.do');
			});
			
			// 	셀렛 박스에 값 너어주기
			var sigunCode = $("#sgCode").val();
			$("#Adults").val(sigunCode);
			
			//	찾아보기
			$(".btn_search").on('click', function(){
				var sgCode = $("#Adults").val();
				
				$(location).attr('href', "../Main/Main.do?sgCode="+sgCode);
			})
			
			//	탭 열기
			$(".btn4").on('click', function(){
				$("#tabs").slideToggle('1000');
				
			}); 
			//	ajax호출
			getAjaxList();
			
			
			
		}); // document end
		
		//	탭설정
		$( function() {
		   $( "#tabs" ).tabs();
		  
		 } );
		
		//	상세정보 설정	a:this b:contentid c:type(travel,hotel,food) d:title
		function detailView(a, b, c, d){
			var jsondata = {};
			jsondata.CONTENTID = b;
			jsondata.TYPE = c;
						
			$(a).attr('data-toggle', 'modal');
			$(a).attr('data-target', '#myModal');
			
			$('#myModalLabel').html(d);
			
			$.ajax({
				url : '../Main/detailView.do',
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify(jsondata),
				dataType : 'json',
				error : function(){console.log('나오지마')},
				success : function(response){
					console.log(response.MAIN);
					var result = response.RESULT;
					if(result == 'FALSE'){
						$('.modal-body').html("<span>찾으시는 정보가 없습니다.</span>");
					}else{
						var data = response.MAIN;
						
						var img = data.firstimage;
						var title = data.title;
						var addr1 = data.addr1;
						var tel = data.tel;
						if(tel=='empty'){
							tel = '전화번호가 없습니다';
						}
						var readCount = data.readcount;
						var overview = data.overview;
						
						$("#mImg").attr('src', img);
						$("#mTitle").html(title);
						$("#mAddr").html(addr1);
						$("#mTel").html(tel);
						$("#mReadCount").html(readCount);
						$("#mOverView").html(overview);
					}
				}// success end
				
			})  // ajax end
		}// detailView 함수 종료
		
		function modalSave(){
			var copy = $('.modal-body').children().html();
		/* 	$("#imsicopy").html(copy); */
			
		}
		
		var drawTlist = null;
		function drawChart() {
			
			var abcde = new google.visualization.DataTable();
	    	abcde.addColumn('string', 'Title');
	    	abcde.addColumn('number', 'readcount');
	    	
	    	$.each(drawTlist, function(index, item){
	    		var title = item.title;
	    		var readcount = item.readcount;
	    		readcount = parseInt(readcount);
	    		
	    		abcde.addRow([title, readcount]); 
	    	})
	  
	      var options = {title:'Travel',
	                     'width' : 650,
	                     'height': 500,
	                     legend: 'none'};
		  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	      chart.draw(abcde, options);
	      
	      $("rect").attr('fill','none');
	    }// function end
		
		var drawFlist = null;
		function drawChart2(){
			var abcde = new google.visualization.DataTable();
	    	abcde.addColumn('string', 'Title');
	    	abcde.addColumn('number', 'readcount');
	    	$.each(drawFlist, function(index, item){
	    		var title = item.title;
	    		var readcount = item.readcount;
	    		readcount = parseInt(readcount);
	    		
	    		abcde.addRow([title, readcount]); 
	    	})
		    
	    	var options = {title:'Food',
                'width' : 650,
                'height': 500,
                legend: 'none'};
 			var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));
 			chart.draw(abcde, options);
 
 			$("rect").attr('fill','none');
 		}//function end
 		
 		var drawHlist = null;
 		function drawChart3(){
			var abcde = new google.visualization.DataTable();
	    	abcde.addColumn('string', 'Title');
	    	abcde.addColumn('number', 'readcount');
	    	$.each(drawHlist, function(index, item){
	    		var title = item.title;
	    		var readcount = item.readcount;
	    		readcount = parseInt(readcount);
	    		
	    		abcde.addRow([title, readcount]); 
	    	})
		    
	    	var options = {title:'Hotel',
                'width' : 650,
                'height': 500,
                legend: 'none'};
 			var chart = new google.visualization.PieChart(document.getElementById('chart_div3'));
 			chart.draw(abcde, options);
 
 			$("rect").attr('fill','none');
 		}//function end 		
 		
 		//	태그클라우드에 정보를 담을 배열 객체
 		var jqList = [];
 		//	차트와, 태그 클라우드의 리스트를 요청
 		function getAjaxList(){
			var sgCode = $("#Adults").val();			
			$.ajax({
				url : '../Main/ajaxList.do',
				dataType : 'json',
				data : {sgCode : sgCode},
				type : 'get',
				error : function(){console.log('나오지마')},
				success : function(response){
					
					var tlist = response.TLIST;	//	차트
					var hlist = response.HLIST;	//	차트
					var flist = response.FLIST;	//	차트
					var alist = response.ALIST;	//	태그클라우드
										
					if(tlist == null || tlist.length == 0){
						
					}else {
						google.charts.load('current', {'packages':['corechart']});
					 	google.charts.setOnLoadCallback(drawChart);
					 	drawTlist = tlist;	
							
					}//tlist end
					
					if(flist == null || flist.length ==0 ){
						
					}else{
						google.charts.load('current', {'packages':['corechart']});
						google.charts.setOnLoadCallback(drawChart2);
						drawFlist = flist;	
					}
					
					if(hlist == null || hlist.length == 0){
						
					}else{
						google.charts.load('current', {'packages':['corechart']});
						google.charts.setOnLoadCallback(drawChart3);
						drawHlist = hlist;	
					}// hlist end
					
					if(alist==null || alist.length==0){
						
					}else{
						var len = alist.length;
					
						$.each(alist, function(index, item){
							jqList[index] = {'sgCode' : item.sgCode, 'contentid' : item.contentid, 'title' : item.title,
											 'rno' : item.rno, 'weight' : len  }
							len --;
							
						})
							
						drawCloud();
						
					};
				}
			});//ajax end
		}// getAjaxList end
		
		//	태그 클라우드 text weight event를 담을 배열 객체
		var words = [];
		
		function drawCloud(){
			
			for(var i=0; i<jqList.length; i++){
				words[i] = {text : jqList[i].title, 
							weight : parseInt(jqList[i].weight),  
							handlers : {'click': function(a){// 상세보기를 위한 이벤트 설정.
							//	modal 팝업을 실행시키기 위해서 속성 부여.
							 	$("#jqc").children().attr('data-toggle','modal');
								$("#jqc").children().attr('data-target', '#myModal');													
								
											var title = a.target.textContent;
							//					
											$.ajax({
												url :'../Main/CloudAjax.do',
												type : 'get',
												dataType : 'json',
												data : {title:title},
												success : function(data){
													
													var img = data.firstimage;
													var title = data.title;
													var addr1 = data.addr1;
													var tel = data.tel;
													if(tel=='empty'){
														tel = '전화번호가 없습니다';
													}
													var readCount = data.readcount;
													var overview = data.overview;
													
													$("#myModalLabel").html(data.title);
													$("#mImg").attr('src', img);
													$("#mTitle").html(title);
													$("#mAddr").html(addr1);
													$("#mTel").html(tel);
													$("#mReadCount").html(readCount);
													$("#mOverView").html(overview);
													
													
												},
												error : function(){console.log('error')}
											}); // ajax end
										 }	//function end
							}//
				}//words end
							
				
			};// for end
			
			
			 $('#jqc').jQCloud(words, {
				 /*  classPattern: null, */
				  /* shape: 'rectangular', */
				  /* colors: ["#800026", "#bd0026", "#e31a1c", "#fc4e2a", "#fd8d3c", "#feb24c", "#fed976", "#ffeda0", "#ffffcc"], */
				  colors : ["#0099ff", "#0cf", "#39d", "#90c5f0", "#90a0dd", "#a0ddff", "#9ce", "#aab5f0"],
				 /*  autoResize: true , */
				  fontSize: {
				    from: 0.032,
				    to: 0.003
				  } 
				}); 
			
		}// drawCloud end
		
		
		
	</script>
</head>

<body class="page1" id="top">
	<c:if test="${not empty sgCode}">
		<input type="hidden" id='sgCode' name='sgCode' value="${sgCode}">
	</c:if>
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

			<!-------------------- main. start -------------------->
			<div class="row">
				<div class="grid_4">
					<div class="banner">
						<div class="gall_block">
							<a href="../Travel/List.do"><img
								src="../resources/images/main_img1.png" alt=""></a>
							<div class="bann_capt ">
								<div class="maxheight">
									<img src="../resources/images/icon2.png" alt="">
									<div class="bann_title">Travel</div>
									<a href="../Travel/List.do">more</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="grid_4">
					<div class="banner">
						<div class="gall_block">
							<a href="../Food/List.do"><img
								src="../resources/images/page1_img3.jpg" alt=""></a>
							<div class="bann_capt  bn__1">
								<div class="maxheight">
									<img src="../resources/images/icon1.png" alt="">
									<div class="bann_title">Restaurant</div>
									<a href="../Food/List.do">more</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 숙박 -->
				<div class="grid_4">
					<div class="banner">
						<div class="gall_block">
							<a href="../Hotel/List.do"><img
								src="../resources/images/main_img4.png" alt=""></a>
							<div class="bann_capt  bn__2">
								<div class="maxheight">
									<img src="../resources/images/icon3.png" alt="">
									<div class="bann_title">Hotel</div>
									<a href="../Hotel/List.do">more</a>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="grid_12">
					<div class="box">
						<div class="row">
							<div class="preffix_1">
								<h3>
									사람들이 많이 찾는 곳 <span class="btn4">click</span>
								</h3>

								<div id="tabs" style="display: none">
									<ul>
										<li style="display: inline"><span class='btn7'><a
												href="#tabs-1">Travel</a></span></li>
										<li style="display: inline"><span class='btn5'><a
												href="#tabs-2">Restaurant</a></span></li>
										<li style="display: inline"><span class='btn6'><a
												href="#tabs-3">Hotel</a></span></li>
									</ul>
									<div id="tabs-1">
										<br>
										<br>
										<c:if test="${empty MAP.TLIST}"> 여행 정보가 없습니다 </c:if>
										<c:if test="${not empty MAP.TLIST}">
											
											<table class="tablecont" align="left">
												<c:forEach var="data" items="${MAP.TLIST}"
													varStatus="status">
													<tr>
														<td class="td1">
															<div class="imgcont">
																<img src="${data.firstimage}" alt=""
																	class="img_inner noresize fleft">
															</div>
														</td>
														<td class="td2"></td>
														<td class="td3">
															<div class="divcont">
																<span class="txt_icon_mappoint">${data.rno}</span>&nbsp;&nbsp;<span
																	class="txt_icon_travel">여행지</span>&nbsp;&nbsp;<span>조회수(${data.readcount})</span>
																<div class="text1 color1">
																	<span class="detailView" data-toggle="" data-target=""
																		onClick='detailView(this, "${data.contentid}", "TRAVEL", "${data.title}")'>${data.title}</span>
																</div>
																<c:if test="${data.addr1 ne 'empty'}">	${data.addr1}<br />
																</c:if>
																<c:if test="${data.tel eq 'empty' }">
																	<br />
																</c:if>
																<c:if test="${data.tel ne 'empty' }">	${data.tel}<br />
																</c:if>
															</div>
														</td>
													</tr>

												</c:forEach>
											</table>

											<div align="right">
												<div id="chart_div"></div>
											</div>
										</c:if>
									</div>

									<div id="tabs-2">
										<br>
										<br>
										<c:if test="${empty MAP.FLIST}"> 음식점 정보가 없습니다 </c:if>
										<c:if test="${not empty MAP.FLIST}">
											<table class="tablecont" align="left">
												<c:forEach var="data" items="${MAP.FLIST}"
													varStatus="status">
													<tr>
														<td class="td1">
															<div class="imgcont">
																<img src="${data.firstimage}" alt=""
																	class="img_inner noresize fleft">
															</div>
														</td>
														<td class="td2"></td>
														<td class="td3">
															<div class="divcont">
																<span class="txt_icon_mappoint">${data.rno}</span>&nbsp;&nbsp;<span
																	class="txt_icon_restaurant">음식점</span>&nbsp;&nbsp;<span>조회수(${data.readcount})</span>
																<div class="text1 color1">
																	<span class="detailView" data-toggle="" data-target=""
																		onClick='detailView(this, "${data.contentid}", "FOOD", "${data.title}")'>${data.title}</span>
																</div>
																<c:if test="${data.addr1 ne 'empty'}">	${data.addr1}<br />
																</c:if>
																<c:if test="${data.tel eq 'empty' }">
																	<br />
																</c:if>
																<c:if test="${data.tel ne 'empty' }">	${data.tel}<br />
																</c:if>
															</div>
														</td>
													</tr>
												</c:forEach>
											</table>
											<div align="right">
												<div id="chart_div2"></div>
											</div>
										</c:if>
									</div>

									<div id="tabs-3">
										<br>
										<br>
										<c:if test="${empty MAP.HLIST}"> 호텔 정보가 없습니다 </c:if>
										<c:if test="${not empty MAP.HLIST}">
											<table class="tablecont" align="left">
												<c:forEach var="data" items="${MAP.HLIST}"
													varStatus="status">
													<tr>
														<td class="td1">
															<div class="imgcont">
																<img src="${data.firstimage}" alt=""
																	class="img_inner noresize fleft">
															</div>
														</td>
														<td class="td2"></td>
														<td class="td3">
															<div class="divcont">
																<span class="txt_icon_mappoint">${data.rno}</span>&nbsp;&nbsp;<span
																	class="txt_icon_hotel">호텔</span>&nbsp;&nbsp;<span>조회수(${data.readcount})</span>
																<div class="text1 color1">
																	<span class="detailView" data-toggle="" data-target=""
																		onClick='detailView(this, "${data.contentid}", "HOTEL", "${data.title}")'>${data.title}</span>
																</div>
																<c:if test="${data.addr1 ne 'empty'}">	${data.addr1}<br />
																</c:if>
																<c:if test="${data.tel eq 'empty' }">
																	<br />
																</c:if>
																<c:if test="${data.tel ne 'empty' }">	${data.tel}<br />
																</c:if>
															</div>
														</td>
													</tr>
												</c:forEach>
											</table>
											<div align="right">
												<div id="chart_div3"></div>
											</div>
										</c:if>
									</div>
								</div>
								<!-- end -->

							</div>
							<!-- prefix end -->
						</div>
						<!-- row end -->

					<br><br>	
					<!-- jqcloud data-toggle="modal" data-target="#myModal"-->
					<div id="jqcloudContain">
						<div id="jqc" style="width: auto; height: 400px; border: 1px solid #ccc; background-color:white">
						</div>
					</div>

					</div>
					<!-- div box end -->
				</div>
			</div>

			
			<!-------------------- main. end -------------------->

			<!-- 모달 팝업 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						<div class="modal-body">

							<div class="row">
								<div class="grid_100"><img src="" alt="" class="img_inner fleft inn__1" id="mImg"></div>

								<div class="grid_101">
									<div class="blog">
										<table>
											<tbody>
												<tr><td>이름</td><td><p class="blog_content" id="mTitle"></p></td></tr>
												<tr><td>조회수</td><td><p class="blog_content" id="mReadCount"></p></td></tr>
												<tr><td>위치</td><td><p class="blog_content" id="mAddr"></p></td></tr>
												<tr><td>문의/안내</td><td><p class="blog_content" id="mTel"></p></td></tr>
											</tbody>
										</table>
									</div>
									<div class="clear"></div>
								</div>
							</div>

							<div id="mOverView"></div>
						</div>
						<!-- modal-body end -->
						<div class="modal-footer">
							<button type="button" class="btn btn-default"data-dismiss="modal">Close</button>
							<!-- <button type="button" class="btn btn-primary"
								onClick='modalSave()'>Save</button> -->
						</div>
					</div>
				</div>
			</div>
		</div>


	</section>
	<!---------------------- footer ---------------------->
	<jsp:include page="../Common/footer.jsp"></jsp:include>

</body>
</html>

