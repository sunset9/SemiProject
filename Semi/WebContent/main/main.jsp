<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/headerWithMenu.jsp" />


<!-- jQuery UI CSS파일 -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<!-- jQuery 기본 js파일 -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<!-- jQuery UI 라이브러리 js파일 -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript">

</script>
<style>
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #888;
	width: 25%; /* Could be more or less, depending on screen size */
}
/* The Close Button */
.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.newConDiv {
	float: left;
	width: 33%;
	padding: 5px;
}

.newConDivInner {
	border: 1px solid gray;
	width: 310px;
	height: 200px;
	position: relative;
	margin: auto;
}

.newConImg {
	text-align: center;
	position: absolute;
}

.newConP {
	text-align: center;
	background-color: rgba( 255, 255, 255, 0.5 );
	font-color: gray;
	position: absolute;
	width: 100%;
	top: 91%;
}
</style>

</head>
<body>

<!-- <div id="backImage">메인 대표 사진 부분</div>

<div id="webTitle">타이틀 부분</div>

<div id="introText">간단한 소개글 부분</div> -->

<!-- Trigger/Open The Modal -->
<!-- 로그인 상태일때 -->
<c:if test="${login}">
	<button id="btnModal" class="btnNewPlan" >새 일정 만들기</button>
	<button id="btnModal2" style="display: none;">새 일정 만들기</button>
</c:if>

<!-- 비로그인 상태일때 -->
<c:if test="${not login}">
	<button id="btnModal" class="btnNewPlan" style="display: none;">새 일정 만들기</button>
	<button id="btnModal2" onclick="alert('로그인이 필요합니다.');">새 일정 만들기</button>
</c:if>

<!-- The Modal -->
<div id="myModal" class="modal">

<!-- Modal content -->
<div class="modal-content">
	<span class="close">&times;</span>
	<div><h2>새 일정 만들기</h2></div>
	<hr>
	<form action="/plan/create" method="post">
	<div>
		<input type="text" id="editTitleViewInMain" name="editTitleView" placeholder="여행 제목" /><br>
		<input type="date" id="editStartDateInMain" name="editStartDate" value="" />
		<input type="date" id="editEndDateInMain" name="editEndDate" value="" />
		<input type="hidden" id="editOpenedInMain" name="editOpened" value="0" /><br>
		<select name="editTraveled">
			<option value="1">여행 전</option>
			<option value="0">여행 후</option>
		</select>
		<input type="submit" value="새 일정 추가" />
	</div>
	</form>
</div>
</div>
<hr>

<div>
<h2>추천 콘텐츠</h2>
</div>
<hr>

<div>
<h2>최신 콘텐츠</h2>
<div class="newContents">
	<c:forEach var="nList" items="${newestPlanList}" varStatus="nStatus">
		<div class="newConDiv">
			<div class="newConDivInner">
				<img class="newConImg" src="${nList.bannerURL}" style="width: 100%; height: 100%;" /><br>
				<p class="newConP">${nList.title}</p>
			</div>
		</div>
	</c:forEach>
</div>
<div>

</div>
</div>
<script type="text/javascript">
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("btnModal");
//var btn = document.getElementsByClassName("btnModal");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];                                          

// When the user clicks on the button, open the modal
var test1 = 0;

btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
</body>
</html>