<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerWithMenu.jsp" />

<script type = "text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		history.back();
	});

});


</script>
<style>

#container {
	position : absolute;
	width: 1000px;
	
}
.winfo {
	background: rgba(79	,185,159,0.5) ;
	text-align: center;
}
.qnaContent{
  	border: 1px solid #ddd;
	border-radius: 10px;
}

td{
    border-top: 1px solid #ddd;
	padding: 8px;
}

table{
    width: 100%;
    max-width: 100%;

    display: table;
	border-collapse: collapse;
    border-spacing: 0;
}
.btnGroup {
	text-align: center;
	margin-bottom: 10%;
}
#btnList{
/* 	background-color: #4FB99F; */
  	width:80px;
    background-color: #4FB99F;
    border: none;
    color:#fff;
	padding: 5px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 4px;
    border-radius:10px;
}
</style>

<div class= "container">

<h2><strong>자주하는 질문 상세보기</strong></h2>
<hr>

<div class="qnaContent">
<table >
<tr style="border-top-style: hidden;">
<td class = "winfo" style="border-top-left-radius:10px; "> 글번호</td><td>${qna.qna_idx }</td>
<td class = "winfo"> 제목 </td> <td colspan="2">${qna.title }</td>
</tr>

<tr>
<td class ="winfo">아이디</td> <td>${userid }</td>
<td class = "winfo">닉네임</td> <td colspan="2">${qna.writer }</td>
</tr>

<tr>
<td class ="winfo">조회수</td><td>${qna.hit }</td>
<td class ="winfo">작성일</td><td colspan="4">${qna.create_date }</td>
</tr>

<tr><td class ="winfo" style="border-bottom-left-radius:10px;">본문</td> <td colspan="4">
<c:if test="${qnaFile.origin_name ne null}">
<img style="height: 150px ; width: 300px;" src ="/upload/qna/${qnaFile.stored_name }"><br><br>
</c:if>
${qna.content }</td>


</table>

</div><br><br><br>

<div class="btnGroup" style ="text-align: center;">
	<button id = "btnList" >목록</button>

</div>


</div>


<jsp:include page="../layout/footer.jsp" />