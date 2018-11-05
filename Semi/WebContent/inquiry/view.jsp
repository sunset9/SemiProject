<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerWithMenu.jsp" />

<script type = "text/javascript">
$(document).ready(function() {
	$("#btnList").click(function() {
		$(location).attr("href", "/inquiry/list");
	});
	$("#btnUpdate").click(function() {
		$(location).attr("href","/inquiry/update?inq_idx=${inquiry.inq_idx}");
	});
	$("#btnDelete").click(function() {
		$(location).attr("href", "/inquiry/delete?inq_idx=${inquiry.inq_idx}");
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

.winfo {
	background: rgba(255,203,55,0.5) ;
}


.inqContent{
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


.replist{
	margin-bottom: 10%;
	border: 1px solid #ddd;
}

</style>

<div class= "container">

<h2><strong>문의 사항 상세보기</strong></h2>
<hr>

<div class ="inqContent">
<table >
<tr style="border-top-style: hidden;">
<td class = "winfo" style="border-top-left-radius:10px; "> 글번호</td><td>${inquiry.inq_idx }</td>
<td class ="winfo">작성일</td><td colspan="2">${inquiry.create_date }</td>
</tr>

<tr>
<td class ="winfo">아이디</td> <td>${userid }</td>
<td class = "winfo">닉네임</td> <td colspan="2">${writerNick }</td>
</tr>

<tr>
<td class ="winfo">조회수</td><td>${inquiry.hit }</td>
<td class ="winfo">답변여부</td>

<td >
<c:if test="${inquiry.answer eq 0}">

<diV style="color:tomato;">답변 예정</diV>

</c:if>
<c:if test="${inquiry.answer eq 1}">
답변 완료
</c:if>
</td>

</tr>

<tr>
<td class = "winfo"> 제목 </td> <td colspan="4">${inquiry.title }</td>
</tr>

<tr><td class ="winfo" style="border-bottom-left-radius:10px;">본문</td> <td colspan="4">
<c:if test="${inqFile.origin_name ne null}">
<img style="height: 150px ; width: 300px;" src ="/upload/inquiry/${inqFile.stored_name }"><br><br>
</c:if>
${inquiry.content }</td>
</table>

</div> <br><br><br>

<div class="btnGroup" style ="text-align: center;">
	<button id = "btnList"  class="btn btn-success">목록</button>
	<c:if test="${user.user_idx eq inquiry.user_idx }">
	<button id="btnUpdate" class="btn btn-warning">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
	</c:if>
</div>


<!-- 댓글 부분 -->

<div>
<hr>
<div style="text-align: center;">
<p>댓글 달기 기능은 관리에게만 제공</p>
</div>

<!-- 댓글 리스트 부분 -->
<div class="replist">
<table class="table table-striped table-condensed">
<thead>
<tr>
	<th style="width: 10%;">작성자</th>
	<th style="width: 50%;">댓글</th>
	<th style="width: 20%;">작성일</th>
	<th style="width: 10%;"></th>
</tr>
</thead>
<tbody id = "replyBoby">
<c:forEach items="${repList}" var ="reply">
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



</div>

</body>
</html>