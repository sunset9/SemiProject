<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type = "text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		$(location).attr("href", "/admin/inquiry/list");
	});

	$("#btnDelete").click(function() {
		$(location).attr("href", "/admin/inquiry/delete?inq_idx=${inquiry.inq_idx}");
	});
	
	$("#btnRepInsert").click(function() {
		var $form = $("<form>").attr({
			action: "/reply/insert",
			method: "post"
		}).append(
			$("<input>").attr({
				type:"hidden",
				name:"inq_idx",
				value:"${inquiry.inq_idx}"
			})
		).append(
			$("<textarea>")
				.attr("name","content")
				.css("display","none")
				.text($("#replyContent").val())
		);
		$(document.body).append($form);
		$form.submit();
	});
});
function deleteReply(rep_idx){
	$.ajax({
		type: "post"
		, url:"/reply/delete"
		, dataType:"json"
		, data : {
			rep_idx:rep_idx
		}
		, success: function(data) {
			if(data.success) {
				
// 				console.log("삭제 되나여?");

				$("[data-rep_idx='"+rep_idx+"']").remove();
			} else {
				alert("댓글 삭제 실패 ")
			}
		}
		, error: function() {
			console.log("error")
		}
	});
} 
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

<span><h1><a href ="/admin/main"><strong>관리자 페이지</strong></a></h1></span><hr>
<div class= "container">

<h2><strong>문의 사항 상세보기</strong></h2>
<hr>

<div >
<table class="table table-bordered">
<tr>
<td class = "info"> 글번호</td><td>${inquiry.inq_idx }</td>
<td class = "info"> 제목 </td> <td colspan="2">${inquiry.title }</td>
</tr>

<tr>
<td class ="info">아이디</td> <td>${userid }</td>
<td class = "info">닉네임</td> <td colspan="2">${writerNick }</td>
</tr>

<tr><td class ="info">본문</td> <td colspan="4">
<c:if test="${inqFile.origin_name ne null}">
<img style="height: 150px ; width: 300px;" src ="/upload/inquiry/${inqFile.stored_name }"><br><br>
</c:if>
${inquiry.content }</td>

<tr>
<td class ="info">조회수</td><td>${inquiry.hit }</td>

<td class ="info">답변여부</td>

<td>
<c:if test="${inquiry.answer eq 0}">
답변 예정
</c:if>
<c:if test="${inquiry.answer eq 1}">
답변 완료
</c:if>
</td>

</tr>

<tr>
<td class ="info">작성일</td><td colspan="4">${inquiry.create_date }</td>
</tr>
</table>

</div>

<div class="btnGroup" style ="text-align: center;">
	<button id = "btnList"  class="btn btn-success">목록</button>

	<button id="btnDelete" class="btn btn-danger">삭제</button>
</div>


<!-- 댓글 부분 -->

<div>
<hr>
<!-- 댓글 입력 부분 -->
<div class= "form-inline text-center">
	<input type ="text" class="form-control" size="10" id ="replyWriter" value="${user.nickname }"  readonly="readonly"/>
	<textarea style ="resize: none;" rows="1" cols="60" class="form-control"id="replyContent"></textarea>
	<button id="btnRepInsert" >댓글 달기</button> <br><br>
</div> 

<!-- 댓글 리스트 부분 -->
<table class="table table-striped table-hover table-condensed">
<thead>
<tr>
	<th style="width: 10%;">작성자</th>
	<th style="width: 50%;">댓글</th>
	<th style="width: 20%;">작성일</th>
	<th style="width: 10%;"></th>
</tr>
</thead>
<tbody id = "replyBoby">
<c:forEach items="${replyList}" var ="reply">
<tr data-rep_idx="${reply.rep_idx }">
	<td>${reply.userid }</td>
	<td>${reply.content }</td>
	<td>${reply.create_date }</td>
	<td>
		<c:if test="${user.id eq reply.userid }">
		<button class="btn btn-danger" onclick="deleteReply(${reply.rep_idx});">삭제</button>
		</c:if>
	</td>
</tr>
</c:forEach>
</tbody>

</table>



</div>



</div>

</body>
</html>