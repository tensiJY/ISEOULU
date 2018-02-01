<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>자유게시판 이미지 첨부</title>
	<jsp:include page="../Common/commonheader.jsp"></jsp:include>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#sbtn").click(function(){
			$("#frm").submit();
		});
		$("#cbtn").click(function(){
			window.close();
		});
	});
</script>
<body class="page1" id="top">
<div class="form_block">
	<div class="container">
		<div class="row">
			<div class="grid_12">
				<h3>자유게시판</h3>
				<form method="POST" id="frm" action="../Common/ImageUploadProc.do" enctype="multipart/form-data">
				<div class="board">
					<table>
					<tbody>
						<tr>
							<td align = "center" ><h5>첨부 이미지</h5> </td>
							<td><input type="file" id="files" name="files"></td>
						</tr>
						<tr>
							<td colspan="2" align = "center" >
								<input type="button" value="저장" id="sbtn" class = "btn3"> &nbsp;&nbsp;&nbsp;
								<input type="button" value="취소" id="cbtn" class = "btn3">
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
</html>