<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<c:import url="/layout/headerNoMenu.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	// 선택체크 삭제
	$("#btnDelete").click(function() {
		// 선택된 체크박스
		var $checkboxes = $("input:checkbox[name='checkRow']:checked");
	
		console.log($checkboxes);
		
		
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
			.attr("action", "/admin/user/deleteList")
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

<script type="text/javascript">

function userDelete(user_idx) {
// 	console.log("실행쓰?")

	var ret = confirm(user_idx+"사용자를 삭제하시겠습니까?");
	console.log(ret);
	if (ret){
		$.ajax ( {
			type : "POST"
			, url:"/admin/user/delete"
			, dataType: "json"
			, data:{user_idx:user_idx }
			, success: function(d) {
				
				if(d.success) {
					$("tr[data-user_idx="+user_idx+"]").html($("<td colspan='9'>").text("삭제되었습니다."));
	// 				$("[data-user_idx='"+user_idx+"']").remove();
				}
			}
			,error: function() {
				console.log("실패")
			}
			
		});
	}
};

function gradeChange(user_idx,grade) {
	
	console.log("user등급: " +grade);
	
	var chageGrade = $("#userGrade option:selected").val();
	console.log(chageGrade);
	
	var ret = confirm(user_idx+"번 사용자의 등급을 "+grade+"에서 "+chageGrade+"로 변경하시겠습니까?");
	console.log(ret);
	
	if(ret) {
	
		$.ajax({
			type:"post"
			, url :"/admin/user/grade"
			, dataType:"json"
			, data:{
				user_idx:user_idx,
				
				usergrade : chageGrade
				
			}
			,success:function(data){
				if(data.success){
					alert ("등급이 변경되었습니다.")
					
				} else{
					alert("등급변경 실패")
				}
			}
			, error: function() {
				console.log("error")
			}
		});
	}
}

</script>

<style>
.wrapper {
	border: 1px solid black;
	width: 1200px;
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

#listTable{
	text-align: center;
	margin : 0 auto;
}

th{
	text-align: center;
}

#searchBox{
	text-align: center;
	
}

</style>

<title>유저 조건 조회</title>
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
<h3><strong>사용자 조건 조회</strong></h3>
<hr><br>

<div id ="listTable">
<table class="table table-hover table-striped table-condensed">
<thead>

<tr>
<th></th>
<th>회원 번호</th>
<th>아이디</th>
<th>닉네임</th>
<th>로그인 타입</th>
<th>등급</th>
<th>가입일</th>
<th>회원상태</th>
<th>삭제</th>
</tr>

</thead>
<tbody>
<c:forEach items ="${userList }" var = "user">
<tr data-user_idx="${user.user_idx }">
<td><input type="checkbox" name="checkRow" value="${user.user_idx }" /></td>
<td>${user.user_idx }</td>
<td>${user.id }</td>
<td>${user.nickname }</td>
<td>${user.snsType }</td>
<td>
	<select id="userGrade" onchange="gradeChange(${ user.user_idx},'${user.grade}');">
		<option value="관리자" <c:if test="${user.grade eq '관리자'}">selected</c:if>>관리자</option>
		<option value="여행작가" <c:if test="${user.grade eq '여행작가'}">selected</c:if>>여행작가</option>
		<option value="여행자" <c:if test="${user.grade eq '여행자'}">selected</c:if>>여행자</option>
	</select>
</td>
<td>${user.create_date }</td>
<td>
	<c:if test="${user.status eq 1}">
		회원
	</c:if>
	<c:if test="${user.status eq 0}">
		탈퇴회원
	</c:if>
</td>

<td>

 <c:if test="${user.status eq 1}">
<button id="userDelete" onclick="userDelete(${user.user_idx });">삭제</button>
</c:if> 
</td>
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
      <a href="/admin/user/list?search=${paging.search }&searchType=${paging.searchType}" aria-label="First">
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
      <a href="/admin/user/list?curPage=${paging.curPage-1 }&search=${paging.search }&searchType=${paging.searchType}" aria-label="Previous">
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
    	  <li class="active"><a href="/admin/user/list?curPage=${i }&search=${paging.search }&searchType=${paging.searchType}">${i }</a></li>
    	</c:if>
		<c:if test="${paging.curPage ne i}">          
    	  <li><a href="/admin/user/list?curPage=${i }&search=${paging.search }&searchType=${paging.searchType}">${i }</a></li>
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
      <a href="/admin/user/list?curPage=${paging.curPage+1 }&search=${paging.search }&searchType=${paging.searchType}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
    <div id="btnDeleteBox">
	<button id="btnDelete">삭제</button>
  </div><br>
  
  <div id ="searchBox" class="col-xs-2, form-inline" >
<form action="/admin/user/list" method="get" >	
	<select name ="searchType" class="form-control" >
	<option value="1">닉네임</option>
	<option value="2">아이디</option>
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