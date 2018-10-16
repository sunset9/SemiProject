<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 스타일 -->
<style type="text/css">
.header {}

.common {
	display: inline-block;
}

.right {float:right;}

.menu {
	margin-right:80px;
	margin-left:80px;
	float: left;
}

</style>

</head>
<body>
<!-- header -->
<div class="header">
	<div class="logo common">
		<a href="/main">
			<img src="/image/logo.png" style="width:60px;" />
		</a>
	</div>
	
	<div class="right">
	<div class="search common">
		<div class="Type common">
			<select>
				<option>제목</option>
				<option>닉네임</option>
				<option>제목+내용</option>
			</select>
		</div>
		<div class="searchBox common">
			<input type="text" />
		</div>
	</div>
	
	<div class="newPlan common">
		<button>새일정</button>
	</div>
	
	<div class="loginBtn common">
		<button>로그인</button>
	</div>
	</div>
</div>
<!-- /header -->

