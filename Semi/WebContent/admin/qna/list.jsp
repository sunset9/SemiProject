<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	$("#btnSearch").click(function() {
		$(location).attr("href","/admin/qna/list?search="+$("#search").val());
	});
	
	$("#btnWrite").click(function() {
		location.href="/admin/qna/write";
	});
	
	// 선택체크 삭제
	$("#btnDelete").click(function() {
		// 선택된 체크박스
		var $checkboxes = $("input:checkbox[name='checkRow']:checked");
	
// 		console.log($checkboxes);
		
		//방법2
		// 체크된 대상들을 map으로 만들고 map을 문자열로 만들기
		var map = $checkboxes.map(function() {
			return $(this).val();
		});
		
		var names = map.get().join(",");
		console.log("names : " + names);
		
		console.log( "map:" + map );	// 맵
		console.log( "map->array : " + map.get() );	// 맵->배열
		console.log( "array tostring : " + map.get().join(",") ); // toString
		
		// 전송 폼
		var $form = $("<form>")
			.attr("action", "/admin/qna/deleteList")
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


<style>
.wrapper {
	border: 1px solid black;
	width: 1000px;
	margin :0 auto;
	display :grid;
	grid-template-columns : repeat(12, 1fr);
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
<title>관리자 자주하는 질문 리스트</title>
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
<h3><strong>자주하는 질문</strong></h3>
<div id ="listTable">
<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<td></td>
<th>번호</th>
<th>제목</th>
<th>작성자</th>

<th>조회수</th>
<th>작성일</th>
</tr>
</thead>
<tbody>
<c:forEach items ="${qnaList }" var = "qna">
<tr>
<td><input type="checkbox" name="checkRow" value="${qna.qna_idx }" /></td>
<td>${qna.rnum }</td>
<td><a href="/admin/qna/view?qna_idx=${qna.qna_idx }">${qna.title }</a></td>
<td>${qna.writer }</td>

<td>${qna.hit }</td>
<td>${qna.create_date }</td>
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
      <a href="/admin/qna/list?search=${paging.search }" aria-label="First">
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
      <a href="/admin/qna/list?curPage=${paging.curPage-1 }&search=${paging.search }" aria-label="Previous">
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
    	  <li class="active"><a href="/admin/qna/list?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/admin/qna/list?curPage=${i }&search=${paging.search }">${i }</a></li>
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
      <a href="/admin/qna/list?curPage=${paging.curPage+1 }&search=${paging.search }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
    <div id="btnBox">
	<button id="btnDelete">삭제</button>
	<button id ="btnWrite">글쓰기</button>
  </div><br>
</div>
<div id="searchBox" class="col-xs-2, form-inline" >
	<input type="text" id="search"placeholder="제목검색"   class="form-control "/>
	<button id="btnSearch">검색</button>
</div><br>

</div>
</div>
</body>
</html>