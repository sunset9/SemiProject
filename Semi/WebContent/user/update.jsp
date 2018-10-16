<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%!  %>
</head>
<body>

<div>
<h1>프로필 수정</h1>
<form action="/user/update" method="post">
	<table>
	<tr>
	<td>이메일 : </td><td>${email}</td>
	</tr>
	<tr>
	<td>내등급 : </td><td>${grade}</td>
	</tr>
	<tr>
	<td>닉네임 : </td><td><input type="text" name="nickname" placeholder="${nickname}"></td>
	</tr>
	<tr>
	<td>비밀번호 : </td>
	<td><input type="button" name="password" value="변경하기"></td>
	</tr>
	</table>
	<button type="submit">저장</button>
</form>
</div>
<button>회원탈퇴하기</button>
</body>
</html>