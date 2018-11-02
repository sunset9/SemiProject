<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../layout/headerWithMenu.jsp" />
<style type="text/css">

.common {
	margin-left:10px;
	margin-right:10px;
}

.profileBox { margin: 50px; }

.tabCommon { float: left; margin-left:30px; margin-right:30px; }

.listBox{
	display: grid; 
	grid-template-columns:33% 33% 33%;
}

#planBox{
	border: 1px solid black; 
	padding:10px;
	margin : 3px;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	$('.profileImage').click(function(){
		$('.fileBtn').click();
	});
	
	$('.fileBtn').change(function(){
		$('.uploadForm').submit();
	})
});
	
</script>
</head>
<body>

<!-- 아이디로 로그인한 유저의 마이페이지 -->
<c:if test="${user.sns_idx == 1 || socialUser.sns_idx == 1}">
<!-- 유저 정보 -->
<div class="profileBox">
	<!-- 프로필사진, 정보수정 버튼 -->
	<div class="profile common">
		<div id="right">
			<div><img src="${user.profile}" class="profileImage" name="image" style="border-radius:10px; width:110px; height:100px;"/></div><br>
			<div>
				<form action="/user/file" method="post" enctype="multipart/form-data" class="uploadForm">
					<input type="file" name="uploadFile" class="fileBtn" style="display: none;"/>
					<input type="submit" value="업로드" class="submitBtn" style="display: none;"/>
				</form>
			</div>
		</div>
		<div class="updateBtn">
			<button onclick='location.href="/user/update";'>정보수정</button>	
		</div>
	</div>
	
	<!-- 사용자명, 등급, 포스팅개수 -->
	<div class="profile common">
		<div class="nickname">${user.nickname}님의 여행기</div>
		<div class="grade">등급 : ${user.grade}</div>
		<div class="planCnt">포스팅 : ${user.totalPlanCnt} 개</div>
	</div>
	
	<!-- 총여행거리 -->
	<div class="profile common">

		<div class="totalDistance">총 여행거리 : <fmt:formatNumber value='${user.totalDist}' pattern="0.##"/>km</div>
	</div>
</div>


<!-- 일정, 북마크 리스트 -->
<div>
	<!-- 탭 버튼 -->
	<div class="tab">
		<button onclick="openList('planList')">내 일정</button>
		<button onclick="openList('bookmarkList')">북마크</button>
		<button onclick="openList('inquiryList')">내 문의</button>
	</div>
	
	<div id="planList" class="list">
	<h3>여기는 내 일정 리스트</h3>
	<div id="planList" class="listBox">
		<c:forEach items="${plannerList }" var="plan"> 
			<div id="planBox" data-plan_idx="${plan.plan_idx }">
			<a href="/plan?plan_idx=${plan.plan_idx }">
				<div><img src="/upload/banner/${plan.bannerURL }" style="width: 100%;"></div>
				<div> Title : ${plan.title} <br> NickName : ${plan.nick }</div>
				<div>
					<button id ="planDelete"  onclick="deletePlan(${plan.plan_idx})">삭제</button>
				</div>
			</a></div>
		</c:forEach>
		</div>
	
	</div>
	
	<div id="bookmarkList" class="list" style="display:none">
	<div><h3>여기는 북마크 리스트</h3></div>
	
			<div id="planList" class="listBox" >

		<c:forEach items="${bookMarkList }" var="bList"> 
			<div id="bookmarkBox" data-book_idx="${bList.book_idx }">
			<a href="/plan?plan_idx=${bList.plan_idx }">
				<div><img src="/upload/banner/${bList.bannerURL }" style="width: 100%;"></div>
				<div> Title : ${bList.title} <br> NickName : ${bList.writeNick }</div>
				<div>
					<button id ="planDelete"  onclick="deletePlan(${bList.plan_idx})">삭제</button>
				</div>
			</a></div>
		</c:forEach>
		
	</div>
	
	</div>
	
	<div id="inquiryList" class="list" style="display:none">
		<div><h3>내가 한 문의</h3></div>
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
		
	</div>
</div>
</c:if>




<!-- 소셜로그인한 유저의 마이페이지 -->
<c:if test="${socialUser.sns_idx != 1 && user.sns_idx != 1}">
<!-- 유저 정보 -->
<div class="profileBox">
	<!-- 프로필사진, 정보수정 버튼 -->
	<div class="profile common">
		<div id="right">
			<div><img src="${socialUser.profile}" class="profileImage" name="image" style="border-radius:10px; width:110px; height:100px;"/></div><br>
			<div>
				<form action="/user/file" method="post" enctype="multipart/form-data" class="uploadForm">
					<input type="file" name="uploadFile" class="fileBtn" style="display: none;"/>
					<input type="submit" value="업로드" class="submitBtn" style="display: none;"/>
				</form>
			</div>
		</div>
		<div class="updateBtn">
			<button onclick='location.href="/socialUser/update";'>정보수정</button>	
		</div>
	</div>
	
	
	<!-- 사용자명, 등급, 포스팅개수 -->
	<div class="profile common">
		<div class="nickname">${socialUser.nickname}님의 여행기</div>
		<div class="grade">등급 : ${socialUser.grade}</div>
		<div class="planCnt">포스팅 : ${user.totalPlanCnt} 개</div>
	</div>
	
	<!-- 총여행거리 -->
	<div class="profile common">
		<div class="totalDistance">총 여행거리 : <fmt:formatNumber value='${user.totalDist}' pattern="0.##"/>km</div>
	</div>
</div>
<hr>

<!-- 일정, 북마크 리스트 -->
<div>
	<!-- 탭 버튼 -->
	<div class="tab">
		<button onclick="openList('planList')">내 일정</button>
		<button onclick="openList('bookmarkList')">북마크</button>
		<button onclick="openList('inquiryList')">내 문의</button>
	</div>
	
	<div id="planList" class="list">
	<h3>여기는 내 일정 리스트</h3>
	<div id="planList" class="listBox">
		<c:forEach items="${plannerList }" var="plan"> 
			<div id="planBox" data-plan_idx="${plan.plan_idx }">
			<a href="/plan?plan_idx=${plan.plan_idx }">
				<div><img src="/upload/banner/${plan.bannerURL }" style="width: 100%;"></div>
				<div> Title : ${plan.title}</div>
				<div>
					<button id ="planDelete"  onclick="deletePlan(${plan.plan_idx})">삭제</button>
				</div>
			</a></div>
		</c:forEach>
		</div>
	
	</div>
	
	<div id="bookmarkList" class="list" style="display:none">
		<div><h3>여기는 북마크 리스트</h3></div>
	
			<div id="planList" class="listBox" >

		<c:forEach items="${bookMarkList }" var="bList"> 
			<div id="bookmarkBox" data-book_idx="${bList.book_idx }">
			<a href="/plan?plan_idx=${bList.plan_idx }">
				<div><img src="/upload/banner/${bList.bannerURL }" style="width: 100%;"></div>
				<div> Title : ${bList.title} <br> NickName : ${bList.writeNick }</div>
				<div>
					<button id ="planDelete"  onclick="deletePlan(${bList.plan_idx})">삭제</button>
				</div>
			</a></div>
		</c:forEach>
		
		</div>
	</div>
	
	<div id="inquiryList" class="list" style="display:none">
		<div><h3>내가 한 문의</h3></div>
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
		
	</div>
</div>
</c:if>


<!-- 탭 작동 스크립트 -->
<script type="text/javascript">
	function openList(listName) {
		var i;
		var x = document.getElementsByClassName("list");
		for(i = 0; i < x.length; i++){
			x[i].style.display = "none";
		}
		document.getElementById(listName).style.display= "block";
	}
	
	function submitWithTitle() {
		
	}
</script>
</body>
</html>