<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../layout/headerWithMenu.jsp" />

<style type="text/css">

.common {
	margin-left:10px;
	margin-right:10px;
}

table {width:100%; height:100%; }
td {vertical-align:middle;}

</style>
</head>
<body>

<div class="profileBox">
	<!-- 프로필사진, 정보수정 버튼 시작-->
	<div class="profile common">
		<div class="profileImage">
			<img src="/image/basicProfile.png" style="border-radius:70px; width:100px;">
		</div>
		<div class="updateBtn">
			<button onclick='location.href="/user/update";'>정보수정</button>	
		</div>
	</div>
	
	<!-- 사용자명, 등급, 포스팅개수 시작-->
	<div class="profile common">
		<div class="nickname">${user.nickname}님의 여행기</div>
		<div class="grade">등급 : </div>
		<div class="planCnt">포스팅 :</div>
	</div>
	
	<!-- 총여행거리 시작-->
	<div class="profile common">
		<div class="totalDistance">총 여행거리 : </div>
	</div>
</div>
</body>
</html>