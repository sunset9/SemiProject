<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
//ajax 테스트
function ajaxTest(data){
	console.log("1");
	console.log(data);
	console.log("11");
// 	$.ajax({
// 		url: "https://maps.googleapis.com/maps/api/place/queryautocomplete/json"
// 		, method: "GET"
// 		, data: {
// 			key: "AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q"
// 			, language: "ko"
// 			, input: "피자"
// 		}
// 		, dataType: "json"
// 	// 	, success: function(){
// 	// 		console.log("success!");
// 	// 	}
// 	// 	, error: function(){
// 	// 		console.log("fail!");
// 	// 	} 
// 	}).done(function(data){
// 		console.log("done");
// 	})
}
</script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/place/queryautocomplete/json?
key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&language=ko&callback=ajaxTest&input=pizza"
    async defer></script>
</head>
<body>

</body>
</html>