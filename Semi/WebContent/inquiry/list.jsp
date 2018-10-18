<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<jsp:include page ="../layout/headerWithMenu.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	$("table").on("click","tr",function(){
		var inq_idx = $(this).children("td").eq(0).text();
		
		$(location).attr("href","/inquiry/view?inq_idx="+inq_idx);
		
	});
	
});
</script>


<div>
<div>
<h3> 문의 사항 </h3> <button id ="myInq">내 문의</button><button id ="inqWrite">문의 하기</button>
<hr>
</div>
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
<td>${inq.inq_idx }</td>
<td><a href="/inquiry/view?inq_idx=${inq.inq_idx }">${inq.title }</a></td>
<td>${inq.user_idx }</td>
<td>${inq.answer }</td>
<td>${inq.hit }</td>
<td>${inq.create_date }</td>
</tr>
</c:forEach>
</tbody>

</table>


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
      <a href="/inquiry/list?curPage=${paging.curPage-1 }" aria-label="Previous">
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
    	  <li class="active"><a href="/inquiry/list?curPage=${i }">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/inquiry/list?curPage=${i }">${i }</a></li>
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
      <a href="/inquiry/list?curPage=${paging.curPage+1 }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>

</div>

</body>
</html>