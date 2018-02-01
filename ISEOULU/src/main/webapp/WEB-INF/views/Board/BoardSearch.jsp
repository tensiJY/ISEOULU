<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<title>자유게시판 목록</title>
	<!----------------- 공통 css, meta태그, 스크립트 호출 ---------------->
	<jsp:include page="../Common/commonheader.jsp"></jsp:include>
	<script>
		$(document).ready(function(){
			$().UItoTop({ easingType: 'easeOutQuart' });
			$('#stuck_container').tmStickUp({});
			$('.gallery .gall_item').touchTouch();
			
			$("#wBtn").click(function(){
		    	$(location).attr("href","../Board/WriteForm.do");
		    });
			$("#sBtn").click(function(){
				$("#sfrm").submit();
			});
			
		}); 
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
		<!-------------------- top. 목록보기 view 영역 start -------------------->
		<div class="row">
			<div class="grid_12">
				<h3>자유게시판</h3>
				<div class="board">
				    <%--	검색 상자 --%>
					<form method="POST" id="sfrm" action="../Board/BoardSearch.do">
					<input type="hidden" name="nowPage" value="1">
					<table >
					<tbody>
						<tr  >
							<td width = "170">
								<select id="target" name="target"  class = "select_board">
									<option value="title">제목</option>
									<option value="body">본문</option>
									<option value="writer">글쓴이</option>
									<option value="tags">태그</option>
								</select>  
							</td>
							<td ><p class="blog_content ta__left">	      
								<input type="text" id="word" name="word" class = "text5"> &nbsp;&nbsp;      
								<input type="button" id="sBtn"  class="btn3" value="검색하기">
							</p></td>
						</tr>
					</tbody>	
					</table>
					</form>
					<!--  목록보기 -->
					<table>
					<tbody>
						<tr>
							<th width="150">번호</th>
							<th >제목</th>
							<th width="150">작성자</th>
							<th width="150">작성일</th>
							<th width="150">조회수</th>
						</tr>
						<c:forEach var="data" items="${LIST}">
							<tr>
								<td>${data.no}</td>
								<td><p class="blog_content ta__left">
									<a href="../Board/BoardView.do?nowPage=${PINFO.nowPage}&oriNo=${data.no}&kind=search">${data.title}</a> 
								</p></td>
								<td>${data.writer}</td>
								<td>${data.wday}</td>
								<td>${data.hit}</td>
							</tr>
						</c:forEach>	
					</tbody>
					</table>
					<%--	페이지 이동 기능 --%>
					<table >
					<tbody>
						<tr>
							<td align="center">
								<%--	[이전] --%>
								<c:if test="${PINFO.startPage eq 1}">
									[이전]
								</c:if>
								<c:if test="${PINFO.startPage ne 1}">
									<a href="../Board/BoardSearch.do?nowPage=${PINFO.startPage - 1}&kind=search">[이전]</a>
								</c:if>
								<%--	[1][2][3] --%>
								<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
									<a href="../Board/BoardSearch.do?nowPage=${page}&kind=search">[${page}]</a>
								</c:forEach>
								<%--	[다음] --%>
								<c:if test="${PINFO.endPage eq PINFO.totalPage}">
									[다음]
								</c:if>
								<c:if test="${PINFO.endPage ne PINFO.totalPage}">
									<a href="../Board/BoardSearch.do?nowPage=${PINFO.endPage + 1}&kind=search">[다음]</a>
								</c:if>
							</td>
						</tr>
					</tbody>	
					</table>
					
					<div class="btns">
					    <input type="button" id="wBtn"   class="btn2"  value="글쓰기">
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear sep__1"></div>
		<!-------------------- top. 상세정보 view 영역 end -------------------->
	</div>
</section>
<!---------------------- footer ---------------------->
<jsp:include page="../Common/footer.jsp"></jsp:include>
</body>
</html>
