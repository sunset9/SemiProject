<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/user/join" method="post">
<div id="sign_up">
	<div class="header">
		<h3>이메일 주소로 회원가입</h3><br>
	</div>
	<div class="body">
		<div class="signUp">
			<input type="email" id="useremail" name="useremail" placeholder="이메일"><br>
			<input type="password" id="userpw" name="userpw" placeholder="비밀번호"><br>
			<input type="text" id="nickname" name="nickname" placeholder="닉네임"><br>
		</div>
		<div class="signUpBtn">
			<button class="signUpBtn" >가입하기</button>
		</div>
	</div>
</div>
</form>
</body>
</html>