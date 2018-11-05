<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="../layout/headerWithMenu.jsp" />

<script type="text/javascript">
$(document).ready(function() {
// 	$("table").on("click","tr",function(){
// 		var inq_idx = $(this).children("td").eq(0).text();
		
// 		$(location).attr("href","/inquiry/view?inq_idx="+inq_idx);
		
// 	});
	
	$("#inqWrite").click(function() {
		location.href="/inquiry/write";
	});
	
	$("#btnSearch").click(function() {
		$(location).attr("href", "/inquiry/list?search="+$("#search").val());
	
	});
	
	$("#myInq").click(function() {
		location.href="/inquiry/mylist";
	});
});
</script>

<style>
.container {
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
 	background-color: rgba(255,203,55) ;
}

</style>
<div class ="container">

<h2> <strong>◎ 문의 사항 ◎</strong> </h2> 

<div id ="writebtn">
<c:if test="${login }">
<button id ="inqWrite" class="btn btn-warning">문의 하기</button>
</c:if>
</div>

<hr>

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
<c:forEach items ="${inquirylist }" var = "inq">
<tr>
<td>${inq.rnum }</td>
<td><a href="/inquiry/view?inq_idx=${inq.inq_idx }">${inq.title }</a></td>
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
      <a href="/inquiry/list" aria-label="First">
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
      <a href="/inquiry/list?search=${paging.search }&curPage=${paging.curPage-1 }" aria-label="Previous">
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
    	  <li class="active"><a href="/inquiry/list?search=${paging.search }&curPage=${i }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/inquiry/list?search=${paging.search }&curPage=${i }">${i }</a></li>
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
      <a href="/inquiry/list?search=${paging.search }&curPage=${paging.curPage+1 }" aria-label="Next">
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