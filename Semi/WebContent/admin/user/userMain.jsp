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
	width: 500px;
	margin : 0 auto;
}

th{
	text-align: center;
}

#searchBox{
	text-align: center;
	
}

#userdata{
	border: 1px solid black;
}

</style>

<title>유저 관리</title>
<hr>


<span><h1><a href ="/admin/main"><strong>관리자 페이지</strong></a></h1></span><hr>

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
<div id="userdata">
<table class="table">

<tbody>

<tr>
<td>총 회원수</td><td>${userCnt }</td></tr>

<tr>
<td>여행자 등급 회원 수 </td><td>${touristCnt }</td></tr>

<tr>
<td>여행 작가 등급 회원 수 </td><td>${authorCnt }</td>
</tr>

<tr>
<td>관리자 수</td><td>${managerCnt }</td>
</tr>

<!-- <tr> -->
<%-- <td>탈퇴한 수</td><td>${managerCnt }</td> --%>
<!-- </tr> -->

</tbody>

</table>
</div>
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