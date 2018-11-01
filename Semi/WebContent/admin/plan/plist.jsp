<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />



<script type="text/javascript">
function deletePlan(plan_idx){
	$.ajax( {
		type:"POST"
		, url :"/admin/plan/delete"
		, dataType :"json"
		,data : {
			plan_idx: plan_idx
		}
		,success: function(d) {
			if(d.success) {
				$("div[data-plan_idx="+plan_idx+"]").html($("<div>").text("삭제된 여행 일정"));
			}
		}
		,error: function() {
			console.log("일정 삭제 실패")
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



th{
	text-align: center;
}

#searchBox{
	text-align: center;
	
}
#planList{
	display: grid; 
	grid-template-columns:33.3% 33.3% 33.3%;
}

#planBox{
	border: 1px solid black; 
	padding:10px;
}

</style>

<title>일정 조회</title>
<hr>

<a href ="/admin/main"><h1><strong>관리자 페이지</strong></h1></a>
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
<h3><strong>일정 게시물 조회</strong></h3>
<hr><br>


	<div id="planList" class="list" >

		<c:forEach items="${planList }" var="plan"> 
			<div id="planBox" data-plan_idx="${plan.plan_idx }">
				<div><a href="/plan?plan_idx=${plan.plan_idx }">
				<div><img src="/upload/banner/${plan.bannerURL }" style="width: 100%;"></div>
				<div> Title : ${plan.title} <br> NickName : ${plan.nick }</div>
				</a></div>
				<div>
					<button id ="planDelete"  onclick="deletePlan(${plan.plan_idx})">삭제</button>
				</div>
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
	<option value="2">아이디</option>
	</select>
	<input type="text" name ="search" class="form-control" />
	<button id="btnSearch">조회</button>
</form>
</div>
</div>
</div>

</div>
</body>
</html>