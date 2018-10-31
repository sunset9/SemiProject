<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--froala Css -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css"> -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/froala_style.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/plugins/image.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/plugins/image_manager.min.css" rel="stylesheet" type="text/css" />
<!-- 2.5.1 -->

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- froala JS -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/froala_editor.pkgd.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/plugins/image.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/plugins/image_manager.min.js"></script>

<!-- jQuery ui -->
<script type="text/javascript" src='/resources/timetable/jquery-ui.min.js'></script>
  
<!-- moment.js (시간 포멧 설정용) --> 
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- floara 관련 -->
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script> -->
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script> -->


<style type="text/css">
.wholeHeader > div {
	padding-bottom: 40px;
}

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


.dropdown > ul > li {
	display: inline-block;
	position: relative;
	padding-left: 40px;
}

.dropdown > ul > li > ul {
	position: absolute;
	list-style-type: none;
	padding-left: 0px;
	display: none;
}

.dropdown > ul > li:hover > ul {
	display: block; /* 마우스 올리면 서브메뉴 보이는 */
}
</style>

<script type="text/javascript">
$(document).ready(function(){
	$(".newPlanClassName").click(function(){
		console.log("ddd");
		$(".btnNewPlan").toggle();
	});
});

</script>
</head>
<body>
<div class="wholeHeader">
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
			<form action="/contents/all" method="post">
				<select name="searchType">
					<option value="1">제목</option>
					<option value="2">닉네임</option>
				</select>
				<div class="searchBox common">
					<input type="text" name="search" />
				</div>
				<div><button type="submit">검색</button></div>
			</form>
			</div>
		</div>
		
		<div class="newPlan common">
			<button id="newPlan" class="newPlanClassName">새일정</button>
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
	<div class="dropdown">
		<ul>
			<li>
			<a href="/contents/all">모든 콘텐츠</a>
				<ul>
					<li><a href="/contents/recommend">추천 콘텐츠</a></li>
					<li><a href="/contents/newest">최신 콘텐츠</a></li>
				</ul>
			</li>

			<li>
				<div class="menu common">
					<!-- 로그인 상태일때 -->
					<c:if test="${login}">
						<a href="/user/myPage">마이페이지</a>
					</c:if>
				
					<!-- 비로그인 상태일때 -->
					<c:if test="${not login}">
						<a onclick="alert('로그인이 필요합니다.');">마이페이지</a>
					</c:if>
				</div>		
			</li>

			<li>
				<div class="menu common"><a href="/notice/list">공지사항</a></div>
			</li>
			
			<li>
				<div class="menu common"><a href="/qna/list">Q&A</a></div>
			</li>
			
			<li>
				<div class="menu common"><a href="/inquiry/list">문의사항</a></div>
			</li>
		</ul>
	</div>
</div><br>
<!-- /menubar끝 -->
</div>

