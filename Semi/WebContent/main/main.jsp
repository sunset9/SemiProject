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
/* 메인 - 추천콘텐츠 더보기 버튼 */
function openrecomContents2(){
	location.href = "/contents/recommend";
}
/* 최신콘텐츠 더보기 버튼 */
function openNewContents2(){
	location.href = "/contents/newest";
}


function checkField(){
	if(newPlanForm.editTitleViewInmain.value == ""){
		alert("일정의 title을 입력해주세요.");
		newPlanForm.editTitleViewInmain.focus();
		return false;
	}
	if(newPlanForm.editStartDateInmain.value == ""){
		alert("출발일을 선택해주세요.");
		newPlanForm.editStartDateInmain.focus();
		return false;
	}
	if(newPlanForm.editEndDateInmain.value == ""){
		alert("도착일을 선택해주세요.");
		newPlanForm.editEndDateInmain.focus();
		return false;
	}
	if(newPlanForm.editTitleViewInmain.value != "" && newPlanForm.editStartDateInmain.value != "" 
			&& newPlanForm.editEndDateInmain.value != ""){
		$('#newPlanForm').submit();
	}
}

function checkDate(){
	if(newPlanForm.editTraveled.value == "1"){
		if(newPlanForm.editStartDateInmain.value < getTodayDate()){
			alert("[여행전] 일정의 출발일은 오늘 날짜보다 빠를 수 없습니다.");
		}
	}
	
	
}

function setDisabledDate(){
	if(newPlanForm.editTraveled.value == "1"){
		$('#editStartDateInmain').attr("min",getTodayDate());
	} else{
		$('#editStartDateInmain').removeAttr("min");
	}
}

function setEndDateDis(){
	$('#editEndDateInmain').attr("min",newPlanForm.editStartDateInmain.value);
}

function getTodayDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+'-'+mm+'-'+dd;
	return today;
}

</script>
<style>
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0); /* Black w/ opacity */
}

.modal-content {
	position: relative;
	background-color: white;
	margin: auto;
	padding: 10px;
	border: 1px solid #888;
	width: 370px;
	height: auto;
	border-radius: 7px;
	top: 132px;
    right: -315px;
    
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
	overflow:hidden;
}

.newConImg, .recomImg {
	text-align: center;
	position: absolute;
	-webkit-transform:scale(1);
    -moz-transform:scale(1);
    -ms-transform:scale(1); 
    -o-transform:scale(1);  
    transform:scale(1);
    -webkit-transition:.3s;
    -moz-transition:.3s;
    -ms-transition:.3s;
    -o-transition:.3s;
    transition:.3s;
}

.newConImg:hover, .recomImg:hover {
    -webkit-transform:scale(1.2);
    -moz-transform:scale(1.2);
    -ms-transform:scale(1.2);   
    -o-transform:scale(1.2);
    transform: scale(1.2);
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


#mainText {
   position: absolute;    
   display: inline-block;
  font-size: 66px;
    font: bold;
    font-weight: 900;
    color: white;
   top: 140px; 
   left: 140px;
}


#mainText2 {
   position: absolute;    
   display: inline-block;
   font-size: 23px;
    color: white;
    font-weight: 900;
   top: 230px;  
   left: 142px; 
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
   border: 1px solid #f9de90 ;
   background: linear-gradient( to bottom, #f9de90 ,#f3ce67, #FFCB37);
   background-color: #4FB99F;
   font-size: 18px;
   text-shadow:#999999 1px 1px 1px;
   font-weight: 900;
}

.btnNewPlan:hover {
   position: absolute;    
   display: inline-block;
   color: white;
   top: 180px;  
   right: 100px; 
   width: 250px;
   height: 60px;
   border-radius: 6px;
   border: none;
   background: linear-gradient( to bottom, #f9de90 ,#f3ce67, #FFCB37);
   background-color: #4FB99F;
   font-size: 18px;
   text-shadow:#999999 1px 1px 1px;
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
   
.modal-content select {
	margin-top: 10px;
    margin-bottom: 10px;
    height: 33px;
    width: -webkit-fill-available;

}

.npmTitle{
    height: 34px;
    width: -webkit-fill-available;
}

</style>

</head>
<body>

<!-- <div id="backImage" style="width: 1000px; margin-left:auto; margin-right: auto;">
	<img src="/resources/img/main/main_img14.jpeg" class="mainImage" />
</div>
 -->

<div class="mainImgContainer">
	<h1 id="mainText">Logo</h1>
	<h4 id="mainText2">Make your own trip.</h4>
	<img src="/resources/img/main/main_img12.jpeg" class="mainImage" />
	<!-- Trigger/Open The Modal -->
	<!-- 로그인 상태일때 -->
	<c:if test="${login}">
		<button id="btnModal" class="btnNewPlan" >새 일정 만들기</button>
		<button id="btnModal2" class="btnNewPlan" style="display: none;">새 일정 만들기</button>
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
	<div><h2  style="margin-top: 12px; margin-bottom: 0;">새 일정 만들기</h2></div>
	<hr>
	<form action="/plan/create" method="post" id="newPlanForm">
		<div>
			<div>
				<input type="text" class="npmTitle" id="editTitleViewInmain" name="editTitleView" placeholder="여행 제목" />
			</div>
			<div>
				<select name="editTraveled" id="editTraveled" style="width: -webkit-fill-available;" >
					<option value="1">여행 전</option>
					<option value="0">여행 후</option>
				</select> 
			</div>
			<p>
				<input type="date" id="editStartDateInmain" name="editStartDate" value="" onclick="setDisabledDate();" onblur="setEndDateDis();" /> 
				<input type="date" id="editEndDateInmain" name="editEndDate" value=""  /> 
			</p>
			<input type="hidden" id="editOpenedInmain" name="editOpened" value="0" /><br>
			<!-- <input class="form-control" type="submit" value="새 일정 추가" /> -->
			<!-- <button class="btn btn-default" style="width: -webkit-fill-available;" onclick="checkField();">새 일정 추가</button> -->
			<input type="button" class="btn btn-default" style="width: -webkit-fill-available;" onclick="checkField();" value="새 일정 추가"/>
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
