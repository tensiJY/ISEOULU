<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!doctype html>
<html>
<head>
	<title>자유게시판 상세</title>
	<!----------------- 공통 css, meta태그, 스크립트 호출 ---------------->
	<jsp:include page="../Common/commonheader.jsp"></jsp:include>
	<script>
		$(document).ready(function(){
			$().UItoTop({ easingType: 'easeOutQuart' });
			$('#stuck_container').tmStickUp({});
			$('.gallery .gall_item').touchTouch();
		}); 
	</script>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#lBtn").click(function(){
			$(location).attr("href", "../Board/BoardList.do?nowPage=${nowPage}");
		});
		$("#sBtn").click(function(){
			$(location).attr("href", "../Board/BoardSearch.do?nowPage=${nowPage}");
		});
		$("#anBtn").click(function(){
			//	답글쓰기 요청을 한다.
			$(location).attr("href", "../Board/AnWriteForm.do?oriNo=${MAP.VIEW.no}&nowPage=${nowPage}&kind=${kind}");
		});
		
		$("#mBtn").click(function(){
			$(location).attr("href", "../Board/ModifyForm.do?oriNo=${MAP.VIEW.no}&nowPage=${nowPage}&kind=${kind}");
		});
		
		$("#dBtn").click(function() {
			var	pw = prompt("비밀번호를 입력해 주세요");			
			$("#password").val(pw);
			$("#ifrm").submit();
		});
	});
</script>
<!------------------------- Content ------------------------->
<section class="content">
	<div class="container">
		<!-------------------- top. 상세정보 view 영역 start -------------------->
		<div class="row">
			<div class="grid_12">
				<h3>자유게시판</h3>
				<div class="board_view">
					<table>
					<tbody>
					<tr>
						<td>글번호</td>
						<td>${MAP.VIEW.no}</td>
						<td>조회수</td>
						<td>${MAP.VIEW.hit}</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>${MAP.VIEW.writer}</td>
						<td>작성일</td>
						<td>${MAP.VIEW.wday}</td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="3">${MAP.VIEW.title}</td>
					</tr>
					<tr>
						<td>본문</td>
						<td colspan="3">${MAP.VIEW.content}</td>
					</tr>
					<tr>
						<td>태그</td>
						<td colspan="3">${MAP.VIEW.tags}</td>
					</tr>
				</tbody>
				</table>
				<!-- 	첨부파일 보여주고 -->
				<table border="1" width="800" align="center">
					
					<c:forEach var="files" items="${MAP.FILE}" varStatus="status">
						<tr>
							<td >첨부파일 </td>
							<td colspan="3">
								<a href="../Board/FileDownload.do?fNo=${files.no}">${files.oriName}</a> (${files.len}) 다운로드수 : ${files.download}
							</td>
						</tr>
					</c:forEach>
				</table>	
				<table>
				<tbody>
					<tr>
						<td>원글이전글</td>
						<td>
							<c:if test="${MAP.PRENEXT.PRENO ne 0}">
								<a href="../Board/BoardView.do?oriNo=${MAP.PRENEXT.PRENO}&nowPage=${nowPage}&kind=${kind}">${MAP.PRENEXT.PRETITLE}</a>
							</c:if>
							<c:if test="${MAP.PRENEXT.PRENO eq 0}">
								${MAP.PRENEXT.PRETITLE}
							</c:if>
						</td>
					</tr>
					<tr>
						<td>원글다음글</td>
						<td>
							<c:if test="${MAP.PRENEXT.NEXTNO ne 0}">
								<a href="../Board/BoardView.do?oriNo=${MAP.PRENEXT.NEXTNO}&nowPage=${nowPage}&kind=${kind}">${MAP.PRENEXT.NEXTTITLE}</a>
							</c:if>
							<c:if test="${MAP.PRENEXT.NEXTNO eq 0}">
								${MAP.PRENEXT.NEXTTITLE}
							</c:if>
						</td>
					</tr>
				</tbody>	
				</table>	 
				
				<table>
				<tbody>
					<tr>
						<td >
						    <c:if test="${kind eq 'list'}">
								<input type="button" id="lBtn"  class = "btn3" value="목록보기">
							</c:if>
							<c:if test="${kind eq 'search'}">
								<input type="button" id="sBtn"  class = "btn3"  value="목록보기">
							</c:if>
							<input type="button" id="anBtn"  class = "btn3"  value="답글쓰기">
							<input type="button" id="mBtn" class = "btn3"   value="수정하기">
							<input type="button" id="dBtn" class = "btn3"  value="삭제하기">
						</td>
					</tr>
				</tbody>	
				</table>
				<table>
				<tbody>
					<tr>
						<td >답글목록</td>
					</tr>
				</tbody>
				</table>		
			</div>
			
			<div class="board">
				<table>
				<tbody>
					<tr>
						<th width="150">번호</th>
						<th >제목</th>
						<th width="150">작성자</th>
						<th width="150">작성일</th>
						<th width="150">조회수</th>
					</tr>
				<c:forEach var="data" items="${MAP.LIST}">
					<tr>
						<td>${data.no}</td>
						<td > <p class="blog_content ta__left">
				<c:if test="${data.bstep ne 0}">	
					<c:forEach var="step" begin="1" end="${data.bstep}">
						&nbsp;&nbsp;&nbsp;
					</c:forEach>	
				</c:if>		
							<a href="../Board/BoardView.do?nowPage=${nowPage}&oriNo=${data.no}&kind=${kind}">${data.title}</a>
						</p></td>
						<td>${data.writer}</td>
						<td>${data.wday}</td>
						<td>${data.hit}</td>
					</tr>
				</c:forEach>		
				</tbody>
				</table>		
				<form method="POST" id="ifrm" action="../Board/DeleteProc.do">
					<input type="hidden" id="password" name="password">
						 <input type="hidden" id="oriNo" name="oriNo" value="${MAP.VIEW.no}">
					 <input type="hidden" id="nowPage" name="nowPage" value="${nowPage}">
				</form>	
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





