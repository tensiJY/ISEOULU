<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
	<title>자유게시판 글쓰기</title>
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
	var	count = 1;
	$(document).ready(function(){
		$("#wBtn").click(function(){
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			
			$("#wFrm").submit();
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
				<form method="POST" id="wFrm" action="../Board/WriteProc.do" enctype="multipart/form-data" >
				<div class="board_view">
				<table >
				<tbody>
					<tr>
						<td>글쓴이</td>
						<td>
							<input type="text" name="writer" id="writer"  class = "text5" value="${sessionScope.UID}" readonly >
						</td>
					</tr>
					<tr>
						<td>제목</td>
						<td><p class="blog_content"><input type="text" name="title" id="title" value="${CONTENT.title } " class = "text5"></p></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea  rows="20" cols="114" name="content" id="content" >
						    ${CONTENT.title } <br><br>
							<img src="${CONTENT.firstimage}"><br><br>
							${CONTENT.overview}</textarea></td>
					</tr>
					<tr>
						<td>첨부파일 </td>
						<td><input type="button" id="aBtn" value="추가"> <input type="file" name="files" id="files"></td>
					</tr>
					<tr id="target">
						<td>비밀번호</td>
						<td><input type="password" name="password" id="password" class = "text5"><br>
						</td>
					</tr>
					<tr>
						<td>태그</td>
						<td>
							<input type="text" id="tags" name="tags" class = "text5"><br>
							검색 태그는 공백이나 ,를 이용해서 입력해 주세요
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="wBtn" class="btn2"  value="글쓰기">
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
	<!-- 	이 부분은 display를 none으로 해 놓았으므로 화면 보이지 않는다. -->
	<div style="display:none" id="copy">
		<table>
		<tr>
			<td>첨부파일 </td>
			<td><input type="button"value="삭제" onClick="delete1(this);"> <input type="file" name="files" id="files"></td>
		</tr>
		</table>
	</div>
	<!---------------------- footer ---------------------->
<jsp:include page="../Common/footer.jsp"></jsp:include>	
</body>
</html>






