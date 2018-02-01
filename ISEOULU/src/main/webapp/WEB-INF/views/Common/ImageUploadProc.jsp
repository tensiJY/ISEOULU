<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../se2/photo_uploader/popup/attach_photo.js"></script>
<body>
<script>
	$(function(){
		var	data = [{
			sFileName : "${NAME}",				//	title 속성에 들어가고
			sFileURL : "../seupload/${NAME}",	//	src 속성에 들어간다.
			bNewLine : true		// 이미지 그린후 줄 바꿈 처리 여부 결정
		}];
		setPhotoToEditor(data);
		self.close();
	});
</script>
</body>
</html>

