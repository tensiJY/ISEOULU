<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>Insert title here</title>
</head>
<body>
	<c:if test="${from eq 'update'}">
		<c:if test="${count eq 0}">
			<script>
				alert("비밀번호가 달라서 수정할 수 없습니다.");
			</script>
		</c:if>
		<c:if test="${count ne 0}">
			<script>
				alert("수정이 완료되었습니다.");
			</script>
		</c:if>
	</c:if>
	<c:if test="${from eq 'delete'}">
		<c:if test="${count eq 0}">
			<script>
				alert("비밀번호가 달라서 삭제할 수 없습니다.");
			</script>
		</c:if>
		<c:if test="${count ne 0}">
			<script>
				alert("삭제가 완료되었습니다.");
			</script>
		</c:if>
	</c:if>
	
	<c:if test="${from eq 'update'}"> 
	<script>
		document.location.href = 
			"../Board/BoardView.do?nowPage=${nowPage}&oriNo=${oriNo}&kind=${kind}";
	</script>
	</c:if>
	<c:if test="${from eq 'delete'}">
		 <c:if test="${count ne 0}">
			<script>
				document.location.href= "../Board/BoardList.do";
			</script>
		 </c:if>
		 <c:if test="${count eq 0}">
			 <script>
			document.location.href = 
				"../Board/BoardView.do?nowPage=${nowPage}&oriNo=${oriNo}&kind=${kind}";
			</script>
		 </c:if>
	</c:if>	
</body>
</html>




