<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">
.header {}

.common {
	display: inline-block;
	margin: 0 auto;
}

.right {float:right;}



/* .menu {
	margin-right:80px;
	margin-left:80px;
	float: left;
} */

</style>
</head>
<body>
<!-- header시작 -->
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
			<!-- 로그인 상태일때 -->
		<c:if test="${login}">
			<button onclick='location.href="/user/logout";'>로그아웃</button>
		</c:if>
		
		<!-- 비로그인 상태일때 -->
		<c:if test="${not login}">
			<button id="loginBtn" onclick='location.href="/user/login";'>로그인</button>
		</c:if>
		</div>
	</div>
</div>
<!-- /header끝 -->


<!-- menubar시작 -->
<div class="menubar">
	<div class="menu common"><a>모든콘텐츠</a></div>
	<div class="menu common"><a>
		<!-- 로그인 상태일때 -->
		<c:if test="${login}">
			<a href="/user/myPage">마이페이지</a>
		</c:if>
		
		<!-- 비로그인 상태일때 -->
		<c:if test="${not login}">
			<a onclick="alert('로그인이 필요합니다.');">마이페이지</a>
		</c:if>
	</a></div>
	<div class="menu common"><a>공지사항</a></div>
	<div class="menu common"><a>Q&A</a></div>
  	<div class="menu common"><a href="/inquiry/list">문의사항</a></div>
</div><br>

<!-- /menubar -->


<!-- /menubar끝 -->

