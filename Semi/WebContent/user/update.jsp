<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>

</head>
<body>

<div id="wrap">
<form action="/user/update" method="post">
	<div id="header"><h2>프로필 수정</h2></div>
	<hr>
	<div id="container">
		<div id="left">
			<label>아이디 : </label>
			<input type="text" name="userid" value="${user.id}" disabled/><br>
			<label>내등급 : </label>
			<input type="text" name="nickname" value="${user.grade}" disabled/><br>
			<label>닉네임 : </label>
			<input type="text" name="nickname" placeholder="${user.nickname}"/><br>
			<div id="pwRegion">
				<label>비밀번호 : </label>
				<input type="button" class="changePw" name="changePw" value="변경하기" /><br>
			</div>
			<div id="morePwRegion">
				<label>현재 비밀번호 : </label>
				<input type="password"/><br>
				<label>새 비밀번호 : </label>
				<input type="password"/><br>
				<label>비밀번호 확인 : </label>
				<input type="password"/><br>
			</div>
		</div>
		<div id="right">
			<div id="image"><img src="${user.profile}" style="border-radius:70px; width:100px;"><br></div>
			<div id="changeImage"><input type="button" name="changeImage" value="변경하기"/></div>
		</div>
	</div>
	<hr>
	<div id="footer">
		<button type="submit">저장하기</button>
	</div>
</form>
<button>회원탈퇴하기</button>
</div>
</body>
</html>