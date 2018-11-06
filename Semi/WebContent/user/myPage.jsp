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

.profileBox { 
	position: relative; 
	height: 300px;
	background-color: rgba( 227, 228, 229, 0.5 );
	margin-bottom:20px;
	border-radius: 8px;
}

.createDiv {
	position: absolute;
	border: 1px solid white;
	top:135px;
	left:170px;
	width:100px;
	border-radius: 7px;
}

.profileBox>img { 
	width:140px; 
	height:140px;
	position: absolute;
	top:40px;
	left:150px;
}

#UserUpdateModalBtn {
	position: absolute;
	top:190px;
	left:150px;
    color: white;
    width: 140px;
    height: 40px;
    border-radius: 6px;
    border: 1px solid #4FB99F;
    background-color: #4FB99F;
    font-size: 17px;
}

.grade {
	position: absolute;
	font-size:20px;
	top:100px;
	left:360px;
}

.planCnt {
	position: absolute;
	font-size:20px;
	top:145px;
	left:360px;
} 

.nickname {
	font-size: 25px;
	position: absolute;
	top:185px;
	left:359px;
}

.totalDistance {
	position: absolute;
	font-size:20px;
	top:192px;
	right:280px;
}


.smallText2 {
	position: absolute;
	font-size:40px;
	top:169px;
	right:170px;	
}

.tabCommon { float: left; margin-left:30px; margin-right:30px; }


.tab>button {
	border: none;
	font-size: 20px;
	margin-bottom:10px;
	width:120px;
	color: #686868;
}

.tab>button:hover {
	font-size: 25px;
	color: black;
	background-color: rgba( 227, 228, 229, 0.1 );
}

.listBox{
	display: grid; 
	grid-template-columns:33% 33% 33%;
}

#planBox,#bookmarkBox{
	
	padding:10px;
	margin : 3px;
	border: 1px solid #d8d8d8;
	border-radius: 6px;
}

#wrap, #wrap2 {
	width: 400px;
	overflow: hidden;
	height: auto;
	padding: 10px;
	position: relative;
}


#header {height: 40px;}


.filebox label { 
	display: inline-block; 
	padding: .5em .75em; 
	color: #999; 
	font-size: inherit; 
	line-height: normal; 
	vertical-align: middle; 
	background-color: #fdfdfd; 
	cursor: pointer; 
	border: 1px solid #ebebeb; 
	border-bottom-color: #e2e2e2; 
	border-radius: .25em; } 
	
.filebox input[type="file"] { 
	/* 파일 필드 숨기기 */ 
	position: absolute; 
	width: 1px; 
	height: 1px; 
	padding: 0; 
	margin: -1px;
	overflow: hidden; 
	clip:rect(0,0,0,0); 
	border: 0; }


/* The Modal (background) */
.UserUpdateModal, .sUserUpdateModal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.UserUpdateModal-content, .sUserUpdateModal-content {
    background-color: white;
    margin: 8% auto; /* 8% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 445px; /* Could be more or less, depending on screen size */
    overflow: hidden;
    height: auto;
    border-radius: 7px;
}

/* The Close Button */
.UserUpdateModalClose, .sUserUpdateModalClose {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.UserUpdateModalClose:hover,
.UserUpdateModalClose:focus,
.sUserUpdateModalClose:hover,
.sUserUpdateModalClose:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

#UserUpdateForm>input {
	position:absolute;
}


</style>
<script type="text/javascript">

$(document).ready(function(){
	$('.profileImage').click(function(){
		$('.fileBtn').click();
	});
	
	$('.fileBtn').change(function(){
		$('.uploadForm').submit();
	});
	
	$('.profileImage').hover(function(){
		$('.createDiv').appendchild('프로필변경');
	});
});

function deletePlan(plan_idx){
	$(location).attr("href", "/plan/delete?plan_idx="+plan_idx);
}
function updatePlan(plan_idx){
	$(location).attr("href", "/plan/update?plan_idx="+plan_idx);
}
function deleteBook(book_idx){
	$(location).attr("href", "/bookmark/delete?book_idx="+book_idx);
}
</script>
<!-- 비밀번호 변경하기 버튼 눌렀을때 -->
<script type="text/javascript">
	$(document).ready(function(){
		$("#morePwRegion").hide();
		$(".changePw").click(function(){
			$("#morePwRegion").toggle();
			$("#container").toggleClass('container');
		});
	});
</script>

<!-- 탈퇴하기 눌렀을때 -->
<script type="text/javascript">
function deleteCheck(){
	var message = confirm("정말로 탈퇴하시겠습니까?");
	if(message == true){
		location.href="/user/delete";
	}else {
		return false;
	}
}
</script>

<script type="text/javascript">
	function sendUpdateForm(){
		if(UserUpdateForm.currPw.value != ""){
			if(UserUpdateForm.newPw.value != UserUpdateForm.newPwCheck.value){
				alert("비밀번호가 일치하지 않습니다.");
				UserUpdateForm.newPwCheck.focus();
				UserUpdateForm.newPwCheck.select();
				return false;
			}
			
			if(UserUpdateForm.currPw.value.length<10){
				alert("비밀번호는 10자 이상 입력해주세요.");
				UserUpdateForm.newPw.focus();
				return false;
			}
			
			if(UserUpdateForm.newPw.value.length<10){
				alert("비밀번호는 10자 이상 입력해주세요.");
				UserUpdateForm.newPw.focus();
				return false;
			}
			if(UserUpdateForm.currPw.value != "" &&
					UserUpdateForm.newPw.value != "" &&
					UserUpdateForm.newPwCheck.value != ""){
				if (UserUpdateForm.afterCheckCurrPw.value == "현재 비밀번호와 일치"){	
					$("#UserUpdateForm").submit();
				}	
			}
		} else if(UserUpdateForm.currPw.value == ""){
			if(UserUpdateForm.nickname.value.length>10){
				alert("닉네임은 10자 이하로만 가능합니다.");
				UserUpdateForm.nickname.focus();
				return false;
			}
			$("#UserUpdateForm").submit();
		}
	}
	
 	function checkCurrPw() {
		var inputUserId = $("#UserIdForUpdate").val();
		var inputUserPw = $("#currPw").val();
		$.ajax({
			type: 'POST',
			url : '/user/check',
			data : {
				inputUserId : inputUserId,
				inputUserPw : inputUserPw
			},
			success : function(result){
				if(result == 2){
					$("#afterCheckCurrPw").val("현재 비밀번호와 불일치");
					$("#afterCheckCurrPw").css("color", "red");
					$("#userUpdateSubmit").prop("disabled",true);
				} else {
					$("#afterCheckCurrPw").val("현재 비밀번호와 일치");
					$("#afterCheckCurrPw").css("color", "blue");
					$("#userUpdateSubmit").prop("disabled",false);
				}
			}
		});
	}
	
	
</script>
</head>
<body>

<!-- 아이디로 로그인한 유저의 마이페이지 -->
<c:if test="${user.sns_idx == 1 || socialUser.sns_idx == 1}">
	<!-- 유저 정보 -->
	<div class="profileBox">
		<!-- 프로필사진, 정보수정 버튼 -->
		<img src="${user.profile}" class="profileImage img-circle" name="image"/>		
		<form action="/user/file" method="post" enctype="multipart/form-data" class="uploadForm">
			<input type="file" name="uploadFile" class="fileBtn" style="display: none;"/>
			<input type="submit" value="업로드" class="submitBtn" style="display: none;"/>
		</form>
		<button id="UserUpdateModalBtn">정보수정</button>	

	
		<!-- 사용자명, 등급, 포스팅개수 -->
		<span class="nickname">${user.nickname} 님의 여행기</span>
		<span class="grade">등급 : ${user.grade}</span>
		<span class="planCnt">포스팅 : ${user.totalPlanCnt} 개</span>
	
		<!-- 총여행거리 -->
		<span class="totalDistance">총 여행거리 : </span>
		<span class="smallText2"><fmt:formatNumber value='${user.totalDist}' pattern="0.##"/>km</span>
	</div>

<!-- 아이디 로그인 유저의 정보수정 The Modal -->
<div id="UserUpdateModal" class="UserUpdateModal">
	
	<!-- 정보수정 Modal Content -->
	<div class="UserUpdateModal-content">	
		<div id="wrap">
		<span class="UserUpdateModalClose">&times;</span>
		<div id="header"><h2>프로필 수정</h2></div>
		<hr>
		<div id="container">
			<div id="left">
				<form action="/user/update" method="post" id="UserUpdateForm">
					<label>아이디 : </label>
					<input type="text" id="UserIdForUpdate" name="userid" value="${user.id}" readonly/><br>
					<label>내등급 : </label>
					<input type="text" name="grade" value="${user.grade}" disabled/><br>
					<label>닉네임 : </label>
					<input type="text" name="nickname" id="nickname" value="${user.nickname}"/><br>
					<div id="pwRegion">
						<label>비밀번호 : </label>
						<input type="button" class="changePw" name="changePw" value="변경하기" /><br>
					</div>
					<div id="morePwRegion">
						<label>현재 비밀번호 : </label>
						<input type="password" id="currPw" name="currPw"/><br>
						<input type="text" id="afterCheckCurrPw" /><br>
						<label>새 비밀번호 : </label>
						<input type="password" id="newPw" name="newPw" onfocus="checkCurrPw();"/><br>
						<label>비밀번호 확인 : </label>
						<input type="password" id="newPwCheck" name="newPwCheck" /><br>
					</div>
					<input type="button" id="userUpdateSubmit" value="저장하기" onclick="sendUpdateForm();" />
				</form>
				<button onclick="deleteCheck();">회원탈퇴하기</button>
			</div>
		</div>
	</div>
	</div>
</div>


<!-- 일정, 북마크 리스트 -->
<div>
	<!-- 탭 버튼 -->
	<div class="tab">
		<button onclick="openList('planList')">내 일정</button>|
		<button onclick="openList('bookmarkList')">북마크</button>|
		<button onclick="openList('inquiryList')">내 문의</button>
	</div>
	
	<div id="planList" class="list">
	    <div id="planList" class="listBox">
	      <c:forEach items="${plannerList }" var="plan"> 
	        <div id="planBox" data-plan_idx="${plan.plan_idx }">
	        <a href="/plan?plan_idx=${plan.plan_idx }">
	          <div><img src="${plan.bannerURL }" style="width: 100%; border-radius:6px;"></div>
	          <div style="color:black;"> Title : ${plan.title}</div>
	
	        </a>
	          <div>
	              <button id ="planDelete" class="btn btn-default" onclick="deletePlan(${plan.plan_idx })">삭제</button>
	              <button id ="planUpdate" class="btn btn-default" onclick="updatePlan(${plan.plan_idx })">수정</button>
	          </div>
	        </div>
	      </c:forEach>
	    </div>
	</div>
</div>
		

	
	<div id="bookmarkList" class="list" style="display:none">
		<div id="planList" class="listBox" >
		<c:forEach items="${bookMarkList }" var="bList"> 
			<div id="bookmarkBox" data-book_idx="${bList.book_idx }">
			<a href="/plan?plan_idx=${bList.plan_idx }">
				<div><img src="${bList.bannerURL }" style="width: 100%; border-radius:6px;"></div>
				<div style="color:black;"> Title : ${bList.title} <br> NickName : ${bList.writeNick }</div>
				</a>
				<div>
					<button id ="bookDelete" class="btn btn-default" onclick="deleteBook(${bList.book_idx })" >삭제</button>
				</div>
			</div>
		</c:forEach>
		
	</div>
	
	</div>
	
	<div id="inquiryList" class="list" style="display:none">
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
		
	</div>
</div>
</c:if>




<!-- 소셜로그인한 유저의 마이페이지 -->
<c:if test="${socialUser.sns_idx != 1 && user.sns_idx != 1}">
<!-- 유저 정보 -->
<div class="profileBox">
	<!-- 프로필사진, 정보수정 버튼 -->
	<img src="${socialUser.profile}" class="profileImage img-circle" name="image"/>
	<form action="/user/file" method="post" enctype="multipart/form-data" class="uploadForm">
		<input type="file" name="uploadFile" class="fileBtn" style="display: none;"/>
		<input type="submit" value="업로드" class="submitBtn" style="display: none;"/>
	</form>
	<button id="sUserUpdateModalBtn">정보수정</button>	

	<!-- 사용자명, 등급, 포스팅개수 -->
	<span class="nickname">${socialUser.nickname} 님의 여행기</span>
	<span class="grade">등급 : ${socialUser.grade}</span>
	<span class="planCnt">포스팅 : ${user.totalPlanCnt} 개</span>

	<!-- 총여행거리 -->
	<span class="totalDistance">총 여행거리 : </span>
	<span class="smallText2"><fmt:formatNumber value='${user.totalDist}' pattern="0.##"/>km</span>
</div>
<hr>

<!-- 소셜유저 정보수정 The Modal -->
<div id="sUserUpdateModal" class="sUserUpdateModal">
	<!-- 소셜유저 정보수정 Modal Content -->
	<div class="sUserUpdateModal-content">
		<div id="wrap2">
		<span class="sUserUpdateModalClose">&times;</span>
		<form action="/socialUser/update" method="post" id="UserUpdateForm">
			<div id="header"><h2>프로필 수정</h2></div>
			<hr>
			<div id="container">
				<div id="left">
					<label>아이디 : </label>
					<input type="text" id="sUserId" name="userid" value="${socialUser.id}" readonly/><br>
					<label>내등급 : </label>
					<input type="text" name="grade" value="${socialUser.grade}" disabled/><br>
					<label>닉네임 : </label>
					<input type="text" name="nickname" id="nickname" value="${socialUser.nickname}"/><br>
				</div>
			</div>
				<button type="submit">저장하기</button>
		</form>
		<button onclick="deleteCheck();">회원탈퇴하기</button>
		</div>
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
	<div id="planList" class="listBox">
		<c:forEach items="${plannerList }" var="plan"> 
			<div id="planBox" data-plan_idx="${plan.plan_idx }">
			<a href="/plan?plan_idx=${plan.plan_idx }">
				<div><img src="${plan.bannerURL }" style="width: 100%; border-radius:6px;"></div>
				<div style="color:black;"> Title : ${plan.title}</div>
			
			</a></div>
			<div>
					<button id ="planDelete" class="btn btn-default">삭제</button>
					<button id ="planUpdate" class="btn btn-default">수정</button>
			</div>
		</c:forEach>
	</div>
	</div>
	
	</div>
	
	<div id="bookmarkList" class="list" style="display:none">
			<div id="planList" class="listBox" >

		<c:forEach items="${bookMarkList }" var="bList"> 
			<div id="bookmarkBox" data-book_idx="${bList.book_idx }">
			<a href="/plan?plan_idx=${bList.plan_idx }">
				<div><img src="${bList.bannerURL }" style="width: 100%; border-radius:6px;" /></div>
				<div style="color:black;"> Title : ${bList.title} <br> NickName : ${bList.writeNick }</div>
				</a><div>
					<button id ="planDelete" class="btn btn-default" onclick="deleteBook(${bList.book_idx })" >삭제</button>
				</div>
			</div>
		</c:forEach>
		
		</div>
	</div>
	
	<div id="inquiryList" class="list" style="display:none">
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

<!-- 정보수정 모달 스크립트 -->
<script type="text/javascript">
/* 일반유저 */
var UserUpdateModal = document.getElementById('UserUpdateModal');
var UserUpdateModalBtn = document.getElementById("UserUpdateModalBtn");
var UserUpdateModalClose = document.getElementsByClassName("UserUpdateModalClose")[0];
UserUpdateModalBtn.onclick = function() {
	UserUpdateModal.style.display = "block";
}
UserUpdateModalClose.onclick = function() {
	UserUpdateModal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == UserUpdateModal) {
    	UserUpdateModal.style.display = "none";
    }
}
</script>

<script type="text/javascript">
/* 소셜유저 */
var sUserUpdateModal = document.getElementById('sUserUpdateModal');
var sUserUpdateModalBtn = document.getElementById("sUserUpdateModalBtn");
var sUserUpdateModalClose = document.getElementsByClassName("sUserUpdateModalClose")[0];
sUserUpdateModalBtn.onclick = function() {
	sUserUpdateModal.style.display = "block";
}
sUserUpdateModalClose.onclick = function() {
	sUserUpdateModal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == sUserUpdateModal) {
    	sUserUpdateModal.style.display = "none";
    }
}
</script>

<jsp:include page="../layout/footer.jsp" />