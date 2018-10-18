<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../layout/headerWithMenu.jsp" />

<style type="text/css">

.common {
	margin-left:10px;
	margin-right:10px;
}

.profileBox { margin: 50px; }

.tabCommon { float: left; margin-left:30px; margin-right:30px; }

</style>
</head>
<body>

<!-- 유저 정보 -->
<div class="profileBox">
	<!-- 프로필사진, 정보수정 버튼 -->
	<div class="profile common">
		<div class="profileImage">
			<img src="/image/basicProfile.png" style="border-radius:70px; width:100px;">
		</div>
		<div class="updateBtn">
			<button onclick='location.href="/user/update";'>정보수정</button>	
		</div>
	</div>
	
	<!-- 사용자명, 등급, 포스팅개수 -->
	<div class="profile common">
		<div class="nickname">${user.nickname}님의 여행기</div>
		<div class="grade">등급 : ${user.grade}</div>
		<div class="planCnt">포스팅 :</div>
	</div>
	
	<!-- 총여행거리 -->
	<div class="profile common">
		<div class="totalDistance">총 여행거리 : </div>
	</div>
</div>

<!-- 일정, 북마크 리스트 -->
<div>
	<!-- 탭 버튼 -->
	<div class="tab">
		<button onclick="openList('planList')">내 일정</button>
		<button onclick="openList('bookmarkList')">북마크</button>
	</div>
	
	<div id="planList" class="list">
		여긴 일정 리스트
	</div>
	
	<div id="bookmarkList" class="list" style="display:none">
		여긴 북마크 리스트
	</div>
</div>

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
</script>
</body>
</html>