<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../../layout/headerNoMenu.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	$("table").on("click","tr",function(){
		var notice_idx = $(this).children("td").eq(0).text();
		
		$(location).attr("href","/admin/comment/view?comm_idx="+comm_idx);
		
	});
	$("#btnSearch").click(function() {
		$(location).attr("href","/admin/comment/list?search="+$("#search").val());
	});
	
	// 선택체크 삭제
	$("#btnDelete").click(function() {
		// 선택된 체크박스
		var $checkboxes = $("input:checkbox[name='checkRow']:checked");

		//방법2
		// 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
		var map = $checkboxes.map(function() {
			return $(this).val();
		});
		
		var names = map.get().join(",");
// 		console.log("names : " + names);
		
// 		console.log( "map:" + map );	// 맵
// 		console.log( "map->array : " + map.get() );	// 맵->배열
// 		console.log( "array tostring : " + map.get().join(",") ); // toString
		
		// 전송 폼
		var $form = $("<form>")
			.attr("action", "/admin/comment/deleteList")
			.attr("method", "post")
			.append(
				$("<input>")
					.attr("type", "hidden")
					.attr("name", "names")
					.attr("value", names)
			);
		$(document.body).append($form);
		$form.submit();
	
	});
});


</script>

<<script type="text/javascript">
function deleteComm(comm_idx) {
// 	console.log("실행쓰?")
	$.ajax ( {
		type : "POST"
		, url:"/admin/comment/delete"
		, dataType: "json"
		, data:{comm_idx:comm_idx }
		, success: function(d) {
			
			if(d.success) {
				$("tr[data-comm_idx="+comm_idx+"]").html($("<td colspan='9'>").text("삭제되었습니다."));
// 				$("[data-user_idx='"+user_idx+"']").remove();
			}
		}
		,error: function() {
			console.log("실패")
		}
		
	});
};
</script>


<style>
.wrapper {
	border: 1px solid black;
	width: 1000px;
	margin :0 auto;
	display :grid;
	grid-template-columns : repeat(12, 1fr);
/* 	grid-template-rows : 700px; */
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
#searchBox{
	text-align: center;
}


</style>
<title>관리자 댓글관리 리스트</title>
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
<h3><strong>댓글 관리</strong></h3>
<div id ="listTable">
<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th></th>  <!-- 체크박스 공간 -->
<th>일정번호</th>
<th>스토리 장소</th>
<th>작성자</th>
<th>내용</th>
<th>작성일</th>
<th></th> <!-- 삭제 버튼 공간 -->
</tr>
</thead>
<tbody>
<c:forEach items ="${commList }" var = "comm">
<tr data-comm_idx="${comm.comm_idx }" >
<td><input type="checkbox" name="checkRow" value="${comm.comm_idx }" /></td>
<td>${comm.plan_idx }&nbsp;<a href ="/plan?plan_idx=${comm.plan_idx}" target="_blank">[새창]</a></td>
<td>${comm.place_name }</td>
<td>${comm.nickname }</td>
<td>${comm.content }</td>
<td>${comm.create_date }</td>
<td><button id="commDelete" onclick="deleteComm(${comm.comm_idx})">삭제</button></td>
</tr>
</c:forEach>
</tbody>

</table>
</div>

<div id="pagingBox" class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/admin/comment/list?search=${paging.search }" aria-label="First">
        <span aria-hidden="true">&larr;처음</span>
      </a>
    </li>
	</c:if>
	
  
  	<!-- 이전 페이지 -->
  	<!-- 첫 페이지라면 버튼 동작 안 되게 만들기 -->
  	<c:if test="${paging.curPage eq 1 }">
    <li class="disabled">
        <span aria-hidden="true">&laquo;</span>
    </li>
    </c:if>
    
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/admin/comment/list?curPage=${paging.curPage-1 }&search=${paging.search }" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    
    <!-- 페이징 리스트 -->
    <c:forEach
     begin="${paging.startPage }"
     end="${paging.endPage }"
     var="i">

		<!-- 현재 보고 있는 페이지번호만 강조해주기 -->
		<c:if test="${paging.curPage eq i}">          
    	  <li class="active"><a href="/admin/comment/list?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/admin/comment/list?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
    </c:forEach>

    
    <!-- 다음 페이지 -->
  	<c:if test="${paging.curPage eq paging.totalPage }">
    <li class="disabled">
        <span aria-hidden="true">&raquo;</span>
    </li>
	</c:if>
	
  	<c:if test="${paging.curPage ne paging.totalPage }">
    <li>
      <a href="/admin/comment/list?curPage=${paging.curPage+1 }&search=${paging.search }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
    <div id="btnBox">
	<button id="btnDelete">삭제</button>
  </div><br>
</div>
<div id="searchBox" class="col-xs-2, form-inline">
	<input type="text" id="search" placeholder="내용검색" class="form-control "/>
	<button id="btnSearch">검색</button>
</div><br>

</div>
</div>
</body>
</html>