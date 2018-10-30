<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerNoMenu.jsp" />

<script type="text/javascript">

$(document).ready(function() {
	$("#btnSearch").click(function() {
		$(location).attr("href","/admin/main?search="+$("#search").val());
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



</style>
<title>관리자 메인</title>
<hr>
<h1><strong>관리자 페이지</strong></h1>
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
<h3><strong>CONTENT</strong></h3>
<div id ="listTable">
<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>답변여부</th>
<th>조회수</th>
<th>작성일</th>
</tr>
</thead>

<tbody>
<c:forEach items ="${inqList }" var = "inq">
<tr>
<td>${inq.inq_idx }</td>
<td><a href="/admin/inquiry/view?inq_idx=${inq.inq_idx }">${inq.title }</a></td>
<td>${inq.writer }</td>
<td>

<c:if test="${inq.answer eq 0}">
<font color="FF6666" style="font-weight: bolder;">답변 예정</font>
</c:if>
<c:if test="${inq.answer eq 1}">
답변 완료
</c:if>

</td>
<td>${inq.hit }</td>
<td>${inq.create_date }</td>
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
      <a href="/admin/main?search=${paging.search }" aria-label="First">
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
      <a href="/admin/main?curPage=${paging.curPage-1 }&search=${paging.search }" aria-label="Previous">
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
    	  <li class="active"><a href="/admin/main?curPage=${i }&search=${paging.search }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/admin/main?curPage=${i }&search=${paging.search }">${i }</a></li>
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
      <a href="/admin/main?curPage=${paging.curPage+1 }&search=${paging.search }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</div>
<div id="searchBox" class="text-center">
	<input type="text" id="search"placeholder="제목검색" />
	<button id="btnSearch">검색</button>
</div>

</div>
</div>
</body>
</html>