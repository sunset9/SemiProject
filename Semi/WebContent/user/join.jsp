<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="joinForm" action="/user/join" method="post" onsubmit="return sendIt();">
<div id="sign_up">
	<div class="header">
		<h3>회원가입</h3><br>
	</div>
	<div class="body">
		<div class="signUp">
			<label>이메일 : </label>
			<input type="email" autofocus id="userid" name="userid" placeholder="이메일"  /><br>
			<label>비밀번호 : </label>
			<input type="password" id="userpw" name="userpw"  placeholder="비밀번호"><br>
			<label>비밀번호 확인 : </label>
			<input type="password" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인"><br>
			<label>닉네임 : </label>
			<input type="text" id="usernickname" name="usernickname" placeholder="닉네임"><br>
			<input type="hidden" id="snsIdx" name="snsIdx" value="1" />
		</div>
		<div class="signUpBtn">
			<button type="submit" class="signUpBtn" >가입하기</button>
		</div>
	</div>
</div>
</form>

</html>