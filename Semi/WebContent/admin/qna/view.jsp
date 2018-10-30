


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type = "text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		$(location).attr("href", history.go(-1));
	});
	$("#btnUpdate").click(function() {
		$(location).attr("href","/admin/qna/update?qna_idx=${qna.qna_idx}");
	});
	$("#btnDelete").click(function() {
		$(location).attr("href", "/admin/qna/delete?qna_idx=${qna.qna_idx}");
	});
});

</script>
<style>

#container {
	position : absolute;
	width: 1000px;
	
}

/* #btnGroup { */
/* 	text-align: center; */

/* } */


</style>
<a href ="/admin/main"><h1><strong>관리자 페이지</strong></h1></a>
<hr>
<div class= "container">

<h2><strong>Qna 사항 상세보기</strong></h2>
<hr>

<div >
<table class="table table-bordered">
<tr>
<td class = "bg-success"> 글번호</td><td>${qna.qna_idx }</td>
<td class = "bg-success"> 제목 </td> <td colspan="2">${qna.title }</td>
</tr>

<tr>
<td class ="bg-success">아이디</td> <td>${userid }</td>
<td class = "bg-success">닉네임</td> <td colspan="2">${userNick }</td>
</tr>

<tr><td class ="bg-success">본문</td> <td colspan="4">
<c:if test="${qnaFile.origin_name ne null}">
<img style="height: 200px ; width: 300px;" src ="/upload/qna/${qnaFile.stored_name }"><br><br>
</c:if>

${qna.content }</td>

<tr>
<td class ="bg-success">조회수</td><td>${qna.hit }</td>
</tr>

<tr>
<td class ="bg-success">작성일</td><td colspan="4">${qna.create_date }</td>
</tr>
</table>

</div>

<div class="btnGroup" style ="text-align: center;">
	<button id = "btnList"  class="btn btn-success">목록</button>
	<button id="btnUpdate" class="btn btn-warning">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>


</div>


</body>
</html>