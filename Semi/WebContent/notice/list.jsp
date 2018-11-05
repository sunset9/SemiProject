<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerWithMenu.jsp" />

<script type="text/javascript">
$(document).ready(function() {
// 	$("table").on("click","tr",function(){
// 		var notice_idx = $(this).children("td").eq(0).text();
		
// 		$(location).attr("href","/notice/view?notice_idx="+notice_idx);
		
// 	});
	
	
	$("#btnSearch").click(function() {
		$(location).attr("href", "/notice/list?search="+$("#search").val());
	
	});
	
});
</script>

<style>
#container {
	position: absolute;
}

#listTable {
	width: 1000px;
	margin: 0 auto;
}

#writebtn {
	position: relative;
	text-align:right;
}
thead{
 	background-color:  #ED553B;
}

</style>
<div class ="container">

<h2> <strong>◎ 공지 사항 ◎</strong> </h2> 

<hr>

<div id ="listTable">
<table class="table table-hover table-striped table-condensed">
<thead>
<tr>
<th>번호</th>
<th>제목</th>
<th>작성자</th>
<th>조회수</th>
<th>작성일</th>
</tr>
</thead>

<tbody>
<c:forEach items ="${noticeList }" var = "notice">
<tr>
<td>${notice.rnum }</td>
<td><a href="/notice/view?notice_idx=${notice.notice_idx }">${notice.title }</a></td>
<td>${notice.writer }</td>
<td>${notice.hit }</td>
<td>${notice.create_date }</td>
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
      <a href="/notice/list" aria-label="First">
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
      <a href="/notice/list?search=${paging.search }&curPage=${paging.curPage-1 }" aria-label="Previous">
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
    	  <li class="active"><a href="/notice/list?search=${paging.search }&curPage=${i }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/notice/list?search=${paging.search }&curPage=${i }">${i }</a></li>
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
      <a href="/notice/list?search=${paging.search }&curPage=${paging.curPage+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
  
  <div id="searchBox" class="text-center">
	<input type="text" id="search" placeholder="제목검색"/>
	<button id="btnSearch">검색</button>
</div><br>

</div>
</div>
</body>
</html>