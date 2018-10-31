<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Insert title here</title>
<style type="text/css">

.img_wrap {
	width: 300px;
	margin-top: 50px;
}

.img_wrap img {
	max-width: 100%;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

 	var sel_file;
 	
 	$(document).ready(function(){
 		$("#input_img").on("change", handleImgFileSelect);
 	});
 	
 	function handleImgFileSelect(e) {
 		var files = e.target.files;
 		var filesArr = Array.prototype.slice.call(files);
 		
 		filesArr.forEach(function(f){
 			if(!f.type.match("image.*")){
 				alert("확장자는 이미지 확장자만 가능합니다.");
 				return;
 			}
 			
 			sel_file = f;
 			
 			var reader = new FileReader();
 			reader.onload = function(e){
 				$("#img").attr("src", e.target.result);
 				console.log(e.target.result);
 			}
 			reader.readAsDataURL(f);
 		});
 		
 	}


</script>
</head>
<body>

<div>
	<h2><b>이미지 미리보기</b></h2>
	<p class="title">이미지 업로드</p>
	<input type="file" id="input_img" />
</div>

<div>
	<div class="img_wrap">
		<img id="img" />
	</div>
</div>
</body>
</html>