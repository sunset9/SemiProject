<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#morePwRegion").hide();
		$(".changePw").click(function(){
			$("#morePwRegion").toggle();
			$("#container").toggleClass('container');
		});
	});
</script>

<script type="text/javascript">
function deleteCheck(){
	var message = confirm("정말로 탈퇴하시겠습니까?");
	if(message == true){
		location.href="/user/delete";
	}else {
		return false;
	}
}


</script>

<style type="text/css">
#wrap {
	width: 580px;
	border: 1px solid black;	
}

#header {height: 40px;}

#left {
	width: 406px;
 	height: 150px;
	float: left;	
}

#right {
 	height: 150px;
}


#footer {height: 40px;}

.filebox label { 
	display: inline-block; 
	padding: .5em .75em; 
	color: #999; 
	font-size: inherit; 
	line-height: normal; 
	vertical-align: middle; 
	background-color: #fdfdfd; 
	cursor: pointer; 
	border: 1px solid #ebebeb; 
	border-bottom-color: #e2e2e2; 
	border-radius: .25em; } 
	
.filebox input[type="file"] { 
	/* 파일 필드 숨기기 */ 
	position: absolute; 
	width: 1px; 
	height: 1px; 
	padding: 0; 
	margin: -1px;
	overflow: hidden; 
	clip:rect(0,0,0,0); 
	border: 0; }


</style>

</head>
<body>

<!-- id로 로그인한 유저의 정보수정 -->
<c:if test="${user.sns_idx == 1 || socialUser.sns_idx == 1}">
<div id="wrap">
<form action="/user/update" method="post">
	<div id="header"><h2>프로필 수정</h2></div>
	<hr>
	<div id="container">
		<div id="left">
			<label>아이디 : </label>
			<input type="text" name="userid" value="${user.id}" readonly/><br>
			<label>내등급 : </label>
			<input type="text" name="grade" value="${user.grade}" disabled/><br>
			<label>닉네임 : </label>
			<input type="text" name="nickname" value="${user.nickname}"/><br>
			<div id="pwRegion">
				<label>비밀번호 : </label>
				<input type="button" class="changePw" name="changePw" value="변경하기" /><br>
			</div>
			<div id="morePwRegion">
				<label>현재 비밀번호 : </label>
				<input type="password" name="currPw"/><br>
				<label>새 비밀번호 : </label>
				<input type="password" name="newPw"/><br>
				<label>비밀번호 확인 : </label>
				<input type="password" name="newPwCheck"/><br>
			</div>
		</div>
		<div id="right">
			<img src="${user.profile}" name="image" style="border-radius:70px; width:100px;"/><br>
			<div id="changeImage"><input type="button" name="changeImage" value="변경하기"/></div>
		</div>
	</div>
	<hr>
	<div id="footer">
		<button type="submit">저장하기</button>
	</div>
</form>
<button onclick="deleteCheck();">회원탈퇴하기</button>
</div>

	<form action="/user/file" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile">
		<button>업로드</button>
	</form>
</c:if>


<!-- 소셜로그인한 유저의 정보수정 -->
<c:if test="${socialUser.sns_idx != 1 && user.sns_idx != 1}">
<div id="wrap">
<form action="/socialUser/update" method="post">
	<div id="header"><h2>프로필 수정</h2></div>
	<hr>
	<div id="container">
		<div id="left">
			<label>아이디 : </label>
			<input type="text" name="userid" value="${socialUser.id}" readonly/><br>
			<label>내등급 : </label>
			<input type="text" name="grade" value="${socialUser.grade}" disabled/><br>
			<label>닉네임 : </label>
			<input type="text" name="nickname" value="${socialUser.nickname}"/><br>
		</div>
		<div id="right">
			<div id="image"><img src="${socialUser.profile}" style="border-radius:70px; width:100px;"><br></div>
			<div id="changeImage"><input type="button" name="changeImage" value="변경하기"/></div>
		</div>
	</div>
	<hr>
	<div id="footer">
		<button type="submit">저장하기</button>
	</div>
</form>
<button onclick="deleteCheck();">회원탈퇴하기</button>
</div>
</c:if>
</body>
</html>