<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
  <script type="text/javascript">
  	
  	alert("${TITLE}... 찾으시는 정보가 없습니다.");
  	$(location).attr("href", "../Food/List.do?nowPage=${nowPage}&sgCode=${sgCode}");
  </script>
</body>
</html>
