<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--froala Css -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css"> -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/froala_style.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/plugins/image.min.css" rel="stylesheet" type="text/css" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/css/plugins/image_manager.min.css" rel="stylesheet" type="text/css" />
<!-- 2.5.1 -->

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<!-- jquery-confirm 3.3.0 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>

<!-- froala JS -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/froala_editor.pkgd.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/plugins/image.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.8.5/js/plugins/image_manager.min.js"></script>

<!-- jQuery ui -->
<script type="text/javascript" src='/resources/timetable/jquery-ui.min.js'></script>
  
<!-- moment.js (시간 포멧 설정용) --> 
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>

<!-- 부트스트랩 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link href='https://cdn.rawgit.com/openhiun/hangul/14c0f6faa2941116bb53001d6a7dcd5e82300c3f/nanumbarungothic.css' rel='stylesheet' type='text/css'>

<style type="text/css">
body {
	width: 1160px;
	margin: 0 auto;
	font-family: 'Nanum Barun Gothic', sans-serif;
	font-size: 15px;
	padding-right: 0 !important; /* 모달 오픈 시 padding-right 생김. 해당 작동 막기위함 */
}

.wholeHeader > div {
	padding-bottom: 40px;
}

.common {
	display: inline-block;
	margin: 0 auto;
}

.right {float:right;}


.dropdown > ul > li {
	display: inline-block;
	position: relative;
	padding-left: 40px;
}

.dropdown > ul > li > ul {
	position: absolute;
	list-style-type: none;
	padding-left: 0px;
	display: none;
}

.dropdown > ul > li:hover > ul {
	display: block; /* 마우스 올리면 서브메뉴 보이는 */
}	
	
/* The Modal (background) */
.newPlanModal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    padding-top: 100px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  
}

/* Modal Content */
.newPlanmodal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 25%;
}

/* The Close Button */
.newPlanModalclose {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.newPlanModalclose:hover,
.newPlanModalclose:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

}
</style>

<script type="text/javascript">
/* $(document).ready(function(){
	$(".newPlanClassName").click(function(){
		console.log("ddd");
		$(".btnNewPlan").toggle();
	});
}); */
// 쿠기 설정 메소드
var setCookie = function(name, value, exp) {
	  var date = new Date();
	  date.setTime(date.getTime() + exp*24*60*60*1000);
	  document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};
// 쿠키 얻기 메소드
var getCookie = function(name) {
	  var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	  return value? value[2] : null;
};
// 쿠키 삭제 메소드
var deleteCookie = function(name) {
	  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

</script>
</head>
<body>
<div class="wholeHeader">
<!-- header시작 -->
<div class="header">
	<div class="logo common">
		<a href="/main">
			<img src="/image/logo.png" style="width:120px; height: 60px" />
		</a>
	</div>
	
	<div class="right">
		<div class="search common">
			<div class="Type common">
			<form action="/contents/all" method="get">
				<select name="searchType">
					<option value="1">제목</option>
					<option value="2">닉네임</option>
				</select>
				<div class="searchBox common">
					<input type="text" name="search" />
				</div>
				<div><button type="submit">검색</button></div>
			</form>
			</div>
		</div>
		
		<div class="newPlan common">
			<button id="newPlan" class="newPlanClassName">새일정</button>
		</div>
		
		<div class="loginBtn common">
			<!-- 로그인 상태일때 -->
		<c:if test="${login}">
			<button onclick='location.href="/user/logout";'>로그아웃</button>
		</c:if>
		
		<!-- 비로그인 상태일때 -->
		<c:if test="${not login}">
			<button id="loginBtn" onclick='location.href="/user/login";'>로그인</button>
		</c:if>
		</div>
	</div>
</div>
<!-- /header끝 -->


<!-- menubar시작 -->
<div class="menubar">
	<div class="dropdown">
		<ul>
			<li>
			<a href="/contents/all">모든 콘텐츠</a>
				<ul>
					<li><a href="/contents/recommend">추천 콘텐츠</a></li>
					<li><a href="/contents/newest">최신 콘텐츠</a></li>
				</ul>
			</li>

			<li>
				<div class="menu common">
					<!-- 로그인 상태일때 -->
					<c:if test="${login}">
						<a href="/user/myPage">마이페이지</a>
					</c:if>
				
					<!-- 비로그인 상태일때 -->
					<c:if test="${not login}">
						<a onclick="alert('로그인이 필요합니다.');">마이페이지</a>
					</c:if>
				</div>		
			</li>

			<li>
				<div class="menu common"><a href="/notice/list">공지사항</a></div>
			</li>
			
			<li>
				<div class="menu common"><a href="/qna/list">Q&A</a></div>
			</li>
			
			<li>
				<div class="menu common"><a href="/inquiry/list">문의사항</a></div>
			</li>
		</ul>
	</div>
</div><br>
<!-- /menubar끝 -->
</div>

<!-- The Modal -->
<div id="newPlanModal" class="newPlanModal">
<!-- Modal content -->
<div class="newPlanmodal-content">
	<span class="newPlanModalclose">&times;</span>
	<div><h2>새 일정 만들기</h2></div>
	<hr>
	<form action="/plan/create" method="post">
	<div>
		<input type="text" id="editTitleView" name="editTitleView" placeholder="여행 제목" /><br>
		<input type="date" id="editStartDate" name="editStartDate" value="" />
		<input type="date" id="editEndDate" name="editEndDate" value="" />
		<input type="hidden" id="editOpened" name="editOpened" value="0" /><br>
		<select name="editTraveled">
			<option value="1">여행 전</option>
			<option value="0">여행 후</option>
		</select>
		<input type="submit" value="새 일정 추가" />
	</div>
	</form>
</div>
</div>

<script type="text/javascript">
//Get the modal
var newPlanModal = document.getElementById('newPlanModal');

// Get the button that opens the modal
var newPlanbtn = document.getElementById("newPlan");

// Get the <span> element that closes the modal
var newPlanspan = document.getElementsByClassName("newPlanModalclose")[0];

// When the user clicks the button, open the modal 
newPlanbtn.onclick = function() {
	if($("#planSaveBtn").length == 0){
		// 모달 창 띄우기
		newPlanModal.style.display = "block";
	}else {
		// 저장버튼 활성화 상태이면 탭 안넘어가도록 경고창
		if($("#planSaveBtn").attr('disabled') == null){
			// 진행 확인 창 띄움
			$.confirm({
			    title: '계속하시겠습니까?',
			    content: '저장하시지 않으면 작성 중인 정보를 잃습니다.',
			    buttons: {
			    	cancle: { // 취소 버튼
			    		text: '취소'
			    		, btnClass: 'btn-blue'
			        },
		        	계속: function (){ // 계속 버튼
		        		isAlreadyAlert = true;
		        		// 모달 창 띄우기
	    				newPlanModal.style.display = "block";
			        }
			    }
			});
		} else { // 저장버튼 비활성화 상태면 그냥 진행
			// 모달 창 띄우기
			newPlanModal.style.display = "block";
		}
	} 
	
}

// When the user clicks on <span> (x), close the modal
newPlanspan.onclick = function() {
	newPlanModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == newPlanModal) {
    	newPlanModal.style.display = "none";
    }
}
</script>

