<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/headerWithMenu.jsp" />
<style type="text/css">

.common {
	margin-left:10px;
	margin-right:10px;
}

.profileBox { margin: 50px; }

.tabCommon { float: left; margin-left:30px; margin-right:30px; }

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
		<div class="planCnt">포스팅 : ${cntPlan} 개</div>
	</div>
	
	<!-- 총여행거리 -->
	<div class="profile common">
		<div class="totalDistance">총 여행거리 : ${totDist} km</div>
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
	<div><h3>여기는 내 일정 리스트</h3></div>
		<c:forEach var="pList" items="${plannerList}" varStatus="status">
			<div>				
				<div>
				<div style="display:inline;">
					<form action="/plan/write" method="post" style="display:inline;">
						<input type="hidden" name="plan_idx" value="${status.current.plan_idx}" />
						<input type="hidden" name="user_idx" value="${status.current.user_idx}" />
						<input type="hidden" name="editStartDate" value="${status.current.start_date}" />
						<input type="hidden" name="editEndDate" value="${status.current.end_date}" />
						<input type="hidden" name="editTraveled" value="${status.current.traveled}" />
						<button type="submit">수정</button>
					</form>
				</div>
				<div style="display:inline;">
					<form action="/plan/delete" method="post">
						<input type="hidden" name="plan_idx" value="${status.current.plan_idx}" />
						<button type="submit">삭제</button>
					</form>		
				</div>
				</div>
				
				<div>
					<a href="/plan?plan_idx=${pList.plan_idx }">
					<input type="hidden" name="plan_idx" value="${status.current.plan_idx}" />
					<div><input type="image" src="${pList.bannerURL}" style="width: 300px;"></div>
					<div><button type="submit" style="border:0; ">글 제목 : ${pList.title}</button></div><hr>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div id="bookmarkList" class="list" style="display:none">
	<div><h3>여기는 북마크 리스트</h3></div>
		<c:forEach var="bList" items="${bookMarkList}">
			<div>
				<div>
					<button>삭제</button>
				</div>
				<div><img src="${bList.getBannerURL()}" style="width: 300px;"></div>
				<div>북마크 :  ${bList.getTitle()}</div><br>
			</div>
		</c:forEach>
	</div>
	
	<div id="inquiryList" class="list" style="display:none">
		<div><h3>여기는 모든 일정 리스트</h3></div>
		<c:forEach var="aList" items="${allPlanList}" varStatus="aStatus">
			<div>
				<a href="/plan?plan_idx=${pList.plan_idx }">
					<input type="hidden" name="plan_idx" value="${aStatus.current.plan_idx}" />
					<div><input type="image" src="${aList.getBannerURL()}" style="width: 300px;"></div>
					<div><button type="submit" style="border:0; ">글 제목 : ${aList.getTitle()}</button></div><hr>
				</a>
			</div>
		</c:forEach>
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
		<div class="planCnt">포스팅 : ${cntPlan} 개</div>
	</div>
	
	<!-- 총여행거리 -->
	<div class="profile common">
		<div class="totalDistance">총 여행거리 : ${totDist} km</div>
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
	<div><h3>여기는 내 일정 리스트</h3></div>
		<c:forEach var="pList" items="${plannerList}" varStatus="status">
			<div>				
				<div>
				<div style="display:inline;">
					<form action="/plan/write" method="get" style="display:inline;">
						<input type="hidden" name="plan_idx" value="${status.current.plan_idx}" />
						<button type="submit">수정</button>
					</form>
				</div>
				<div style="display:inline;">
					<form action="/PlanDeleteController" method="post">
						<input type="hidden" name="plan_idx" value="${status.current.plan_idx}" />
						<button type="submit">삭제</button>
					</form>		
				</div>
				</div>
				
				<div>
					<a href="/plan?plan_idx=${pList.plan_idx }">
					<input type="hidden" name="plan_idx" value="${status.current.plan_idx}" />
					<div><input type="image" src="${pList.getBannerURL()}" style="width: 300px;"></div>
					<div><button type="submit" style="border:0; ">글 제목 : ${pList.getTitle()}</button></div><hr>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div id="bookmarkList" class="list" style="display:none">
	<div><h3>여기는 북마크 리스트</h3></div>
		<c:forEach var="bList" items="${bookMarkList}">
			<div>
				<div>
					<button>삭제</button>
				</div>
				<div><img src="${bList.getBannerURL()}" style="width: 300px;"></div>
				<div>북마크 :  ${bList.getTitle()}</div><br>
			</div>
		</c:forEach>
	</div>
	
	<div id="inquiryList" class="list" style="display:none">
		<div><h3>여기는 모든 일정 리스트</h3></div>
		<c:forEach var="aList" items="${allPlanList}" varStatus="aStatus">
			<div>
				<a href="/plan?plan_idx=${pList.plan_idx }">
					<input type="hidden" name="plan_idx" value="${aStatus.current.plan_idx}" />
					<div><input type="image" src="${aList.getBannerURL()}" style="width: 300px;"></div>
					<div><button type="submit" style="border:0; ">글 제목 : ${aList.getTitle()}</button></div><hr>
				</a>	
			</div>
		</c:forEach>
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