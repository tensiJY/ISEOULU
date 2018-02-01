<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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

<body class="page1" id="top">
<!---------------------------------------- header ---------------------------------------->
<header>
	<!--------------------- Stuck menu --------------------->
	<jsp:include page="../Common/utilmenu.jsp"></jsp:include>
</header>        
<!---------------------------------------- Content ---------------------------------------->
<section class="content">
	<div class="container">
		<!--------------------- 상단 지역 검색 영역 --------------------->
		<jsp:include page="../Common/searcharea.jsp"></jsp:include>
	</div>
</section>
<div class="form_block">
	<div class="container">
		<div class="row">
			<div class="grid_12">
				<h2>자유게시판</h2>
				<form id="form">
					<div class="form_spinner"><img src="../resources/images/Preloader_4.gif" alt=""></div>
					<div class="modal fade response-message">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">Modal title</h4>
								</div>
								<div class="modal-body">
									asfasfasf
								</div>      
							</div>
						</div>
					</div>
					<div class="response-error-message"></div>
					<label class="name">
						<input type="text" name="name" placeholder="Name:" value="" data-constraints="@Required @JustLetters" />
						<span class="empty-message">*This field is required.</span>
						<span class="error-message">*This is not a valid name.</span>
					</label>         
					<label class="email">
						<input type="text" name="email" placeholder="E-mail:" value="" data-constraints="@Required @Email" />
						<span class="empty-message">*This field is required.</span>
						<span class="error-message">*This is not a valid email.</span>
					</label>
					<label class="phone">
						<input type="text" name="phone" placeholder="Phone:" value="" data-constraints="@Required @JustNumbers"/>
						<span class="empty-message">*This field is required.</span>
						<span class="error-message">*This is not a valid phone.</span>
					</label>
					<label class="message">
						<textarea name="message" placeholder="Message:" data-constraints='@Required @Length(min=20,max=999999)'></textarea>
						<span class="empty-message">*This field is required.</span>
						<span class="error-message">*The message is too short.</span>
					</label>
					<div class="clear"></div>
					<div class="btns">
						<a href="#" data-type="submit" class="btn1">저장</a>
						<a href="#" data-type="reset" class="btn1">취소</a>
					</div>
				</form>   
			</div>
		</div>
	</div>
</div>

<!---------------------- footer ---------------------->
<jsp:include page="../Common/footer.jsp"></jsp:include>
</body>
</html>