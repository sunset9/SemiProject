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
function openNewContents2(){
	$(".newContents2").toggle();
}

function openrecomContents2(){
	$(".recomContents2").toggle();
}
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

.newConDiv, .recomDiv {
	float: left;
	width: 33%;
	padding: 5px;
}

.newConDivInner, .recomDivInner{
	border: 1px solid #babbbc;
	width: 330px;
	height: 200px;
	position: relative;
	margin: auto;
}

.newConImg, .recomImg {
	text-align: center;
	position: absolute;
}

.recomImg:hover {
	transform: scale(0.98);
}

.newConP2, .recomP2 {
	text-align: center;
	background-color: rgba( 255, 255, 255, 0.5 );
	font-color: gray;
	position: absolute;
	width: 100%;
	top: 80%;
}

.mainImage {
	width: 100%;
	height: 100%;
}


.mainImgContainer {
    position: relative;
    display: inline-block;
}


h1 {
   position: absolute;    
   display: inline-block;
   font-size: 50px; /*or whatever you want*/
   color: black;  
   top: 140px; 
   left: 140px;
}


h4 {
   position: absolute;    
   display: inline-block;
   font-size: 20px; /*or whatever you want*/
   color: black;
   top: 230px;  
   left: 145px; 
} 

img {
  display: block;
  max-width: 100%;
  height: auto;
}

.btnNewPlan {
   position: absolute;    
   display: inline-block;
   color: white;
   top: 180px;  
   right: 100px; 
   width: 250px;
   height: 60px;
   border-radius: 6px;
   border: 1px solid #4FB99F;
   background-color: #4FB99F;
   font-size: 20px;
   
   
}


.openConBtn {
   border: 0px solid #577c91;
   background: #ebebeb;
   background: -webkit-gradient(linear, left top, left bottom, from(#ebebeb), to(#ebebeb));
   background: -webkit-linear-gradient(top, #ebebeb, #ebebeb);
   background: -moz-linear-gradient(top, #ebebeb, #ebebeb);
   background: -ms-linear-gradient(top, #ebebeb, #ebebeb);
   background: -o-linear-gradient(top, #ebebeb, #ebebeb);
   background-image: -ms-linear-gradient(top, #ebebeb 0%, #ebebeb 100%);
   padding: 7.5px 15px;
   -webkit-border-radius: 30px;
   -moz-border-radius: 30px;
   border-radius: 30px;
   -webkit-box-shadow: rgba(255,255,255,0.4) 0 0px 0, inset rgba(255,255,255,0.4) 0 0px 0;
   -moz-box-shadow: rgba(255,255,255,0.4) 0 0px 0, inset rgba(255,255,255,0.4) 0 0px 0;
   box-shadow: rgba(255,255,255,0.4) 0 0px 0, inset rgba(255,255,255,0.4) 0 0px 0;
   text-shadow: #cfcfcf 0 1px 0;
   color: #606366;
   font-size: 13px;
   text-decoration: none;
   vertical-align: middle;
   }
.openConBtn:hover {
   border: 0px solid #0a3c59;
   text-shadow: #cfcfcf 0 1px 0;
   background: #ebebeb;
   background: -webkit-gradient(linear, left top, left bottom, from(#ebebeb), to(#ebebeb));
   background: -webkit-linear-gradient(top, #ebebeb, #ebebeb);
   background: -moz-linear-gradient(top, #ebebeb, #ebebeb);
   background: -ms-linear-gradient(top, #ebebeb, #ebebeb);
   background: -o-linear-gradient(top, #ebebeb, #ebebeb);
   background-image: -ms-linear-gradient(top, #ebebeb 0%, #ebebeb 100%);
   color: #606366;
   }
.openConBtn:active {
   text-shadow: #faf7fa 0 1px 0;
   border: 0px solid #0a3c59;
   background: #ebebeb;
   background: -webkit-gradient(linear, left top, left bottom, from(#ebebeb), to(#ebebeb));
   background: -webkit-linear-gradient(top, #ebebeb, #ebebeb);
   background: -moz-linear-gradient(top, #ebebeb, #ebebeb);
   background: -ms-linear-gradient(top, #ebebeb, #ebebeb);
   background: -o-linear-gradient(top, #ebebeb, #ebebeb);
   background-image: -ms-linear-gradient(top, #ebebeb 0%, #ebebeb 100%);
   color: #424242;
   }
</style>

</head>
<body>

<!-- <div id="backImage" style="width: 1000px; margin-left:auto; margin-right: auto;">
	<img src="/resources/img/main/main_img14.jpeg" class="mainImage" />
</div>
 -->

<div class="mainImgContainer">
	<h1>Web Title</h1>
	<h4>Make your own trip.</h4>
	<img src="/resources/img/main/main_img14.jpeg" class="mainImage" />
	<!-- Trigger/Open The Modal -->
	<!-- 로그인 상태일때 -->
	<c:if test="${login}">
		<button id="btnModal" class="btnNewPlan" >새 일정 만들기</button>
		<button id="btnModal2" style="display: none;">새 일정 만들기</button>
	</c:if>
	
	<!-- 비로그인 상태일때 -->
	<c:if test="${not login}">
		<button id="btnModal" class="btnNewPlan" style="display: none;">새 일정 만들기</button>
		<button id="btnModal2" class="btnNewPlan" onclick="alert('로그인이 필요합니다.');">새 일정 만들기</button>
	</c:if>
</div>


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
	<h2 style="display: inline;">추천 콘텐츠</h2>
	<button style="float: right;" onclick="openrecomContents2();" class="openConBtn">+더보기</button>
	<div class="recomContents" style="height: 260px;">
		<c:forEach var="rList" items="${recomPlanList}" begin="0" end="2">
			<div class="recomDiv" style="margin-top: 15px; margin-bottom: 10px;">
				<div class="recomDivInner">
				<a href ="/plan?plan_idx=${rList.plan_idx }">
					<img class="recomImg" src="${rList.bannerURL}" style="width: 100%; height: 100%;" /><br>
					<p class="recomP2">${rList.nick}<br>${rList.title}</p>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="recomContents2" style="height: 260px; display: none;">
		<c:forEach var="rList" items="${recomPlanList}" begin="3" end="5">
			<div class="recomDiv">
				<div class="recomDivInner">
				<a href ="/plan?plan_idx=${nList.plan_idx }">
					<img class="recomImg" src="${rList.bannerURL}" style="width: 100%; height: 100%;" /><br>
					<p class="recomP2">${rList.nick}<br>${rList.title}</p>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>



<div>
	<h2 style="display: inline; width: 170px;">최신 콘텐츠</h2>
	<button style="float: right;" onclick="openNewContents2();" class="openConBtn">+더보기</button>
	<div class="newContents" style="height: 260px;">
		<c:forEach var="nList" items="${newestPlanList}" begin="0" end="2">
			<div class="newConDiv" style="margin-top: 15px; margin-bottom: 10px;">
				<div class="newConDivInner">
					<a href ="/plan?plan_idx=${nList.plan_idx }">
					<img class="newConImg" src="${nList.bannerURL}" style="width: 100%; height: 100%;" /><br>
					<p class="newConP2">${nList.nick}<br>${nList.title}</p>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	
	<div class="newContents2" style="display: none; height: 260px;">
		<c:forEach var="nList" items="${newestPlanList}" begin="3" end="5">
			<div class="newConDiv">
				<div class="newConDivInner">
				<a href ="/plan?plan_idx=${nList.plan_idx }">
					<img class="newConImg" src="${nList.bannerURL}" style="width: 100%; height: 100%;" /><br>
					<p class="newConP2">${nList.nick}<br>${nList.title}</p>
					</a>
				</div>
			</div>
		</c:forEach>
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


<jsp:include page="../layout/footer.jsp" />
