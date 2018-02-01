<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#board").on('click', function(){
			$(location).attr('href', '../Board/BoardList.do');
		});
		$("#login").on('click', function(){
			window.open("../Login/Login.do", "", "width=400, height=300");
		});
		$("#logout").on('click', function(){
			$(location).attr('href', '../Login/Logout.do');
		})
	}); 
</script>
	<section id="stuck_container">
		<div class="container">
			<div class="row">
				<div class="grid_12 right">
					<c:if test="${empty sessionScope.UID}">
						<span class="text2 fw"></span><span class="text2"> <a href="#"  id="login">로그인</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						<a href="#" id="board">자유게시판</a></span>
					 </c:if>
					  <c:if test="${not empty sessionScope.UID}">
					  			<span class="text2 fw">${sessionScope.UID}님</span><span class="text2"> 안녕하세요&nbsp;&nbsp;|&nbsp;&nbsp;
					  			<a href="#" id="board">자유게시판</a>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
					        	<span id="logout"><a href="#">로그아웃</a></span>
					  </c:if>
					<p class="center"><a href="../Main/Main.do"><img src="../resources/images/logo.png" alt="Logo alt" width="300"></a></p>
				</div>
			</div>
		</div>
	</section> 