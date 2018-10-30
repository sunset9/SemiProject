<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type="text/javascript">


</script>

<style>
.wrapper {
	border: 1px solid black;
	width: 1000px;
	margin :0 auto;
	display :grid;
	grid-template-columns : repeat(12, 1fr);
	grid-template-rows : 500px;
}
.menu {
	background-color: #ccc;
	grid-column:span 3;
}

.content{
	grid-column:span 9;
}

ul.sub li{
	margin-bottom:  2px;
	height: 35px;
	line-height: 35px;
	cursor :pointer;
}

ul.sub li a {
	display: block;
	width: 100%;
	height: 100%;
	text-decoration: none;
	color:#000;
}

#listTable{
	text-align: center;
	margin : 0 auto;
}

th{
	text-align: center;
}

#searchBox{
	text-align: center;
	
}

</style>

<title>유저 관리</title>
<hr>

<a href ="/admin/main"><h1><strong>관리자 페이지</strong></h1></a>
<hr>

<div class= "wrapper">
<div class= "menu">
<h3><strong>MENU</strong></h3>
<ul class ="sub">
<li><a href="/admin/user/main">회원관리</a></li>
<li><a href="/admin/plan/list">여행일정 게시글 관리</a></li>
<li><a href="/admin/comment/list">댓글 관리 </a></li>
<li><a href="/admin/notice/list">공지사항 관리</a></li>
<li><a href="/admin/qna/list">QnA 관리</a></li>
<li><a href="/admin/inquiry/list">1:1문의 관리</a></li>
</ul>
</div>

<div class="content">
<h3><strong>사용자 현황</strong></h3>
<hr><br>

<div id ="listTable">
<table class="table table-hover table-striped table-condensed">
<thead>

<tr>
<th>전체 유저</th>
<th>여행자 수</th>
<th>여행작가 수</th>
<th>관리자 수</th>
</tr>

</thead>
<tbody>

<tr>
<td>${userCnt }</td>
<td>${touristCnt }</td>
<td>${authorCnt }</td>
<td>${managerCnt }</td>
</tr>

</tbody>

</table>
</div><br><br><br><br><hr>

<div id ="searchBox" class="col-xs-2, form-inline" >
<form action="/admin/user/list" method="get" >	
	<select name ="searchType" class="form-control" >
	<option value="1">닉네임</option>
	<option value="2">아이디</option>
	</select>
	<input type="text" id ="search" name ="search" class="form-control" />
	<button id="btnSearch">조회</button>
</form>
</div>

</div>

</div>
</body>
</html>