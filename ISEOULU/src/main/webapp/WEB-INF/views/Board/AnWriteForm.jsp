<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!doctype html>
<html>
<head>
	<title>자유게시판 답글쓰기</title>
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
<script src="../se2/js/HuskyEZCreator.js" charset="UTF-8"></script>
<script>
	var oEditors = [];
	$(function(){
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "content",
		 	sSkinURI: "../se2/SmartEditor2Skin.html",
			htParams : {
				bUseToolbar :true,
				bUseVerticalResizer : true,
				bUseModeChanger : true
			}
		});
	});
//	추가된 파일 첨부 입력상자의 개수를 기억할 변수
	var	count = 1;
	$(document).ready(function(){
		$("#wBtn").click(function(){
			//	스마트 에디터 스킨에 있는 내용을 textarea에 반영
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			
			$("#wfrm").submit();
		});
		$("#aBtn").click(function(){
			
			count++;
			if(count > 5) {
				count = 5;
				return;
			}
			var		div = $("#copy").find("tr");
			var copy = div.clone();
			$("#target").before(copy);
		});
	});
	
		function delete1(my) {
		var	tr = $(my).parents("tr");
		tr.remove();
		count--;
	}
	</script>
	<body class="page1" id="top">
	<!---------------------------------------- header ---------------------------------------->
	<header>
		<!--------------------- Stuck menu --------------------->
		<jsp:include page="../Common/utilmenu.jsp"></jsp:include>
	</header>        
	<!---------------------------------------- Content ---------------------------------------->
	<div class="form_block">
		<div class="container">
			<div class="row">
				<div class="grid_12">
					<h2>자유게시판</h2>

					<form method="POST" id="wfrm" action="../Board/AnWriteProc.do"  enctype="multipart/form-data">
					<input type="hidden" name="oriNo" value="${oriNo}">
					<input type="hidden" name="nowPage" value="${nowPage}">
					<input type="hidden" name="kind" value="${kind}">
					<div class="board_view">
					<table width="700" border="1" align="center">
						<tr>
							<td>글쓴이</td>
							<td>
								<input type="text" name="writer" id="writer"  class = "text5"  value="${sessionScope.UID}" readonly>
							</td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" id="title"  class = "text5" ></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content" id="content"  rows="20" cols="114" ></textarea></td>
						</tr>
						<tr>
							<td>첨부파일</td>
							<td><input type="button" id="aBtn" value="추가"> <input type="file" name="files" id="files"></td>
						</tr>
						<tr id="target">
							<td>비밀번호</td>
							<td><input type="password" name="password" id="password"  class = "text5" ></td>
						</tr>
						<tr>
							<td>태그</td>
							<td>
								<input type="text" id="tags" name="tags"  class = "text5"  ><br>
								검색 태그는 공백이나 ,를 이용해서 입력해 주세요
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" id="wBtn"  class = "btn2" value="답글쓰기">
							</td>
						</tr>	
					</tbody>
				</table>
				</div>
				</form>
		     </div>
		</div>
	</div>
</div> 
	<div style="display:none" id="copy">
		<table>
		<tr>
			<td>첨부파일</td>
			<td> <input type="button"value="삭제" onClick="delete1(this);"> <input type="file" name="files" id="files"></td>
		</tr>
		</table>
	</div>	
<jsp:include page="../Common/footer.jsp"></jsp:include>	
</body>
</html>



