<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	

	
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<jsp:include page="./Common.jsp" />
	
	<style type="text/css">
		
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
	/*  */
	
	
	</style>
	<link rel="style/sheet" href="../resources/need/style.css">
	<script type="text/javascript">
		$(function(){
			$("#fBtn").on('click', function(){
				$(this).parents('form').attr('action', './getJob.do').submit();
			});
			
		
		});
		
		const msg = '${MSG}';
		if(msg.length !=0){
			alert(msg);
		};
	</script>
</head>
<body>

	
<%-- 크롤링 기능.. --%>  
<form:form modelAttribute="CrawlingVO">
<div align="center">
	<div>
		<form:label path="keyWord"> 키워드 :  <form:input path="keyWord"/> </form:label> &nbsp;&nbsp;&nbsp;
		<form:label path="startPage"> 시작페이지 : <form:input path="startPage"/></form:label>
	</div>
	
	<div>
		<input type="button" id="fBtn" name="fBtn" value="전송하기">
	</div>
	
	<span>
		키워드 검색 유의 사항 <br>
		1. 웹 개발자 = >>>> 웹+개발자 <br>
		2. 웹개발자 = >>>> 웹개발자 <br>
		즉 띄어쓰기가 있는 것은 + 로 연결 한다. <br>
		3. 서버 부하 방지로 인해 2page씩만.<br>
		1페이지는 2페이지까지<br>
		3페이지는 4페이지까지.. ^^;;
		
	</span>
</div>
</form:form>



<%-- 정보 --%>
<table class="table" align="center" style="width: 90%; height: 80%">
	<tr>
		<th><input type="checkbox" id="chk1" name="chk2"></th>	
		<th style="width:10%">번호</th>
		<th style="width:20%">회사명</th>
		<th>내용</th>
		<th>링크</th>
		<th>생성일</th>
	</tr>
	<c:forEach items="${cvo}" var="c">
	<tr>
		<td><input type="checkbox" id="chk2" name="chk2" data-1="${c.jno}">  </td>
		<td> ${c.jno}</td>
		<td> ${c.jtitle}</td>
		<td>${c.jbody}</td>
		<td>
			
			<a href="<c:url value='${c.jlink}' />">${c.jtitle}</a>
		</td>
		<td>${c.jcreDate}</td>
	</tr>
	</c:forEach>
</table>

<%-- 페이지 --%>
<c:if test="${not empty cvo}">				
<%--	페이지 이동 기능 --%>
	<ul>
		<c:if test="${pageInfo.startPage eq 1}">
			<li><a href="#">이전</a></li>
		</c:if>
		<c:if test="${pageInfo.startPage ne 1}">
			<li><a href='../Crawling/Main.do?nowPage=${pageInfo.startPage - 1}'>이전</a></li>
		</c:if> 
		<c:forEach var="page" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
			<li><a href="../Crawling/Main.do?nowPage=${page}">${page}</a></li>
		</c:forEach>
				
		<c:if test="${pageInfo.endPage eq pageInfo.totalPage}">
			<li><a href="#">다음</a></li>
		</c:if>
				
		<c:if test="${pageInfo.endPage ne pageInfo.totalPage}">
			<li><a href="../Crawling/Main.do?nowPage=${pageInfo.endPage + 1}">다음</a></li>
		</c:if>
	</ul>
</c:if>			
<%-- 크롤링 삭제 --%>

</body>
</html>