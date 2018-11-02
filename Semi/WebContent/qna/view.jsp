<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerWithMenu.jsp" />

<script type = "text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		$(location).attr("href", "/qna/list");
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

<div class= "container">

<h2><strong>자주하는 질문 상세보기</strong></h2>
<hr>

<div >
<table class="table table-bordered">
<tr>
<td class = "info"> 글번호</td><td>${qna.qna_idx }</td>
<td class = "info"> 제목 </td> <td colspan="2">${qna.title }</td>
</tr>

<tr>
<td class ="info">아이디</td> <td>${userid }</td>
<td class = "info">닉네임</td> <td colspan="2">${qna.writer }</td>
</tr>

<tr><td class ="info">본문</td> <td colspan="4">
<c:if test="${qnaFile.origin_name ne null}">
<img style="height: 150px ; width: 300px;" src ="/upload/qna/${qnaFile.stored_name }"><br><br>
</c:if>
${qna.content }</td>

<tr>
<td class ="info">조회수</td><td>${qna.hit }</td>

</tr>

<tr>
<td class ="info">작성일</td><td colspan="4">${qna.create_date }</td>
</tr>
</table>

</div>

<div class="btnGroup" style ="text-align: center;">
	<button id = "btnList"  class="btn btn-success">목록</button>

</div>


</div>

</body>
</html>