<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../layout/headerWithMenu.jsp" />


<style type="text/css">
th{
	text-align: center;
}

#searchBox{
	text-align: center;
	
}
#planList{
	display: grid; 
	grid-template-columns:33% 33% 33%;
}

#planBox{
	border: 1px solid black; 
	padding:10px;
	margin : 3px;
}


</style>

<div class="content">
<h3><strong>모든 콘텐츠 페이지</strong></h3>
<hr><br>
<div id="planList" class="list" >

		<c:forEach items="${planList }" var="plan"> 
			<div id="planBox" data-plan_idx="${plan.plan_idx }"><a href="/plan?plan_idx=${plan.plan_idx }" >
				<div><img src="/upload/banner/${plan.bannerURL }" style="width: 100%;"></div>
				<div> Title : ${plan.title}  NickName : ${plan.nick }</div>
				</a>
			</div>
		</c:forEach>
	</div>



<div id="pagingBox" class="text-center">
  <ul class="pagination pagination-sm">
  	<!-- 처음으로 가기 -->
  	<c:if test="${paging.curPage ne 1 }">
    <li>
      <a href="/admin/plan/list?search=${paging.search }&searchType=${paging.searchType}" aria-label="First">
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
      <a href="/admin/plan/list?curPage=${paging.curPage-1 }&search=${paging.search }&searchType=${paging.searchType}" aria-label="Previous">
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
    	  <li class="active"><a href="/admin/plan/list?curPage=${i }&search=${paging.search }&searchType=${paging.searchType}">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/admin/plan/list?curPage=${i }&search=${paging.search }&searchType=${paging.searchType}">${i }</a></li>
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
      <a href="/admin/plan/list?curPage=${paging.curPage+1 }&search=${paging.search }&searchType=${paging.searchType}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
    <div id="btnDeleteBox">
	<button id="btnDelete">삭제</button>
  </div><br>
  
  <div id ="searchBox" class="col-xs-2, form-inline" >
<form action="/admin/plan/list" method="get" >	
	<select name ="searchType" class="form-control" >
	<option value="1">제목</option>
	<option value="2">닉네임</option>
	</select>
	<input type="text" name ="search" class="form-control" />
	<button id="btnSearch">조회</button>
</form>
</div><br>
</div>
</div>

</div>
</body>
</html>