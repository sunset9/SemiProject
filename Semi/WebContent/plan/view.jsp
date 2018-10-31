<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 헤더  -->
<c:import url="../layout/headerWithMenu.jsp" />
<!-- 미니뷰 modal -->
<jsp:include page="/plan/timetable/miniView.jsp"/>

<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

<!-- timetable utils -->
<script type="text/javascript" src="/utils/timetableUtils.js"></script>
<script type="text/javascript" src="/utils/mapUtils.js"></script>

<!-- Maps JavaScript API 로드 -->
<script async defer
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&language=ko&callback=initMap">
</script>

<!-- 공개유무 슬라이드 버튼 -->
<style type="text/css">
	/* 가계부 그래프 팝업 */
	.pop-layer .pop-container {
	  padding: 20px 25px;
	  margin:0px;
	  display:inline-block;
	  font-size:15px;
/* 	  font-weight:bold; */
	}
	
	.pop-layer p.ctxt {
	  color: #666;
	  line-height: 25px;
	}
	
	.pop-layer .btn-r {
	  width: 100%;
	  margin: 10px 0 20px;
	  padding-top: 10px;
	  border-top: 1px solid #DDD;
	  text-align: right;
	}
	
	.pop-layer {
	  display: none;
	  position: absolute;
	  top: 50%;
	  left: 50%;
	  width: 410px;
	  height: auto;
	  background-color: #fff;
	  border: 5px solid #3571B5;
	  z-index: 10;
	}
	
	.dim-layer {
	  display: none;
	  position: fixed;
	  _position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  z-index: 100;
	}
	
	.dim-layer .dimBg {
	  position: absolute;
	  top: 0;
	  left: 0;
	  width: 100%;
	  height: 100%;
	  background: #000;
	  opacity: .5;
	  filter: alpha(opacity=50);
	}
	
	.dim-layer .pop-layer {
	  display: block;
	}
	
	a.btn-layerClose {
	  display: inline-block;
	  height: 25px;
	  padding: 0 14px 0;
	  border: 1px solid #304a8a;
	  background-color: #3f5a9d;
	  font-size: 13px;
	  color: #fff;
	  line-height: 25px;
	}
	
	a.btn-layerClose:hover {
	  border: 1px solid #091940;
	  background-color: #1f326a;
	  color: #fff;
	}
/* 	----------------------------------------------------- */
/* 	구글 맵 크기 설정 */
	#map {
		background-color:#DDDDDD;
		height:450px;
		float:bottom;
		width:100%;
	}
/* 	----------------------------------------------------- */  
	#calendar {
	    float: right;
	    width: 900px;
	    height: 833px;
	    margin-bottom: 20px;
	}
	
	.fc-ltr .fc-time-grid .fc-event-container { /* 일정 이벤트 박스 관련 */ 
		margin: 0 3px 0 3px; 
		font-size: 1.1em;
	}
	
	.fc-axis { /* 왼쪽 시간  넓이 수정 */ 
		width: 60px !important;
	}
	
	#prevBtn { /* 이전날짜 선택 버튼*/
		cursor: pointer;
		float: left;
		margin-left: 5px;
	}
	
	#nextBtn { /* 다음 날짜 선택 버튼*/
		cursor: pointer;
		float: right;
		margin-right: 5px;
	}
	
	.fc-row.fc-widget-header tr{ /* 헤더의 날짜 정보*/
		height: 50px;
		font-size: 1.3em;
	}
	
	.fc-title{ /* 이벤트 안의 제목 부분 */
		cursor: pointer;
		font-weight: bold;
		font-size: 1.5em;
		padding-top: 5px;
	}
	
	.fc-bg:not(:first-child){
		margin-left: 10px;  /* fc-bg(이벤트 덮고 있는 투명도 있는 판?)css 수정 , 왼쪽에 색 진하게 하는 효과줌*/
	}
	
	/* 	----------------------------------------------------- */  	
	/* 탭관련 */
	#tab-main {
	    margin: 0;
	    padding: 0;
	    float: left;
	    list-style: none;
	    border-bottom: 1px solid #eee;
	    border-left: 1px solid #eee;
	    width: 100%;
	}
	#tab-main li {
	    float: left;
	    text-align:center;
	    cursor: pointer;
	    width: 50%;
 	    height: 50px; 
	    line-height: 50px;
	    border: 1px solid #eee;
	    border-left: none;
	    font-weight: bold;
	    background: #fafafa;
	    overflow: hidden;
	    position: relative;
	    color: #555;
	}
	
	#tab-main li.active {
	    background: #FFFFFF;
	    border-bottom: 5px solid #1e88e5;
	    color: #306490;
	    pointer-events: none; /* 이미 선택한 탭은 다시 클릭 안되도록 */
	}
	.tab-container {
	    border: 1px solid #eee;
	    border-top: none;
	    clear: both;
	    float: left;
        width: 100%; /* 이거 하면 타임테이블 지저분, 안하면 빈 스토리탭 container 좁게 지정*/
	}
	
</style>

<script>

// 서버에서 넘어온 일정의 시작, 끝 날짜 정보
var planStartDate = '${planView.start_date}';
var planEndDate = '${planView.end_date}';
// 서버에서 넘어온 timetable, location 정보들
var ttbList = ${ttbList };
var locList = ${locList };

var plan_idx = ${planView.plan_idx};

var is_diplayStory = false;

var isCookieTablClear = true;
</script>

<script type="text/javascript">
// 읽기모드일때, 검색창 on/off
var isModify = 0;
	console.log("view.jsp isModify : " + isModify);
	
$(document).ready(function() {
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, true);
	$('#calendar').fullCalendar('option', 'editable', false); // 수정 불가능하게
	$('#calendar').fullCalendar('option', 'droppable', false); // 드롭할 수 없게
	

	$("#btnModify").click(function() {
		isModify = 1;
		isCookieTablClear = false;
		
		$("#Modify").submit();
		console.log("view.jsp isModify : " + isModify);
	});

	// 	수정모드일 때, 공개유무버튼
	$("#isChecked").click(function(){
			
// 		  $("p").toggle();
		  var check = $("input[type='checkbox']").is(':checked');
		  
		  if(check) {
			document.getElementById("isClose").style.display= "none";
			document.getElementById("isOpen").style.display= "block";
		  } else {
			document.getElementById("isClose").style.display= "block";
			document.getElementById("isOpen").style.display= "none";
		  }
		  
		  console.log(check);
	});
	
	
// 	북마크 버튼
	$("#btnBookMark").click(function() {
		document.getElementById("viewStory").style.display= "none";
		document.getElementById("calendar").style.display= "block";
		document.getElementById("googleMap").style.display= "block";
		document.getElementById("googleSearch").style.display= "block";
	});
	
// 	가계부 그래프 팝업 동작
	$("#btnAccGraph").click(function(){
	    var $el = $($(this).attr('href'));        //레이어의 id를 $el 변수에 저장
	    var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

	    isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

	    var $elWidth = ~~($el.outerWidth()),
	        $elHeight = ~~($el.outerHeight()),
	        docWidth = $(document).width(),
	        docHeight = $(document).height();

	    // 화면의 중앙에 레이어를 띄운다.
	    if ($elHeight < docHeight || $elWidth < docWidth) {
	        $el.css({
	            marginTop: -$elHeight /2,
	            marginLeft: -$elWidth/2
	        })
	    } else {
	        $el.css({top: 0, left: 0});
	    }

	    $el.find('a.btn-layerClose').click(function(){
	        isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
	        return false;
	    });

	    $('.layer .dimBg').click(function(){
	        $('.dim-layer').fadeOut();
	        return false;
	    });
	});
	
	$("#totalGraph").click(function() {
		document.getElementById("viewTotalGraph").style.display= "block";
		document.getElementById("viewDaliyGraph").style.display= "none";
	});
	
	$("#daliyGraph").click(function() {
		document.getElementById("viewTotalGraph").style.display= "none";
		document.getElementById("viewDaliyGraph").style.display= "block";
	});
	
	
// 	console.log("현재 탭 쿠키 값: " + getCookie('tab'));
// 	console.log(isCookieTablClear);
    // 쿠키값이 없거나 tab-ttb 인 경우
	if(getCookie('tab')==null || getCookie('tab')=='tab-ttb'){
		$("#tab-main li").removeClass("active");
	    $("#tab-main li[rel='tab-ttb']").addClass("active");
		$(".tab-content").css('display', 'none');
	    $(".tab-content.tab-ttb").show();
	
	// 쿠키값이 tab-story인 경우    
	}else if(getCookie('tab')=='tab-story'){
		$("#tab-main li").removeClass("active");
		$("#tab-main li[rel='tab-story']").addClass("active");
		$(".tab-content").css('display', 'none');
		$(".tab-content.tab-story").show();
		
		// ajax 통신으로 내용 불러오기
		displayStoryView();
	}

	$('#tab-main li').click(function(){
		// active클래스 속성 변경
		$("#tab-main li").removeClass("active");
        $(this).addClass("active");
        
        // 선택한탭의 내용 띄워지게
        $(".tab-content").hide();
        var activeTab = $(this).attr("rel");
        $("." + activeTab).show();
        
        if(activeTab == 'tab-ttb'){ // 타임테이블 탭 선택한 경우
			setCookie('tab','tab-ttb');
        }else if(activeTab == 'tab-story'){ // 스토리 탭 선택한 경우
			setCookie('tab','tab-story');
        	// ajax 통신으로 내용 불러오기
			displayStoryView();
        }
	}); // tab on click 이벤트 설정
	
}); // $(document).ready() End
	
//스토리 뷰 ajax통신으로 띄워주기
function displayStoryView(){
	//AJAX 처리하기
	$.ajax({ 	
		type: "get"
		, url: "/story/view"
		, data: {"plan_idx" : plan_idx }
		, dataType: "html"
		, success: function( d ) {
			$("#viewStory").html(d);
		}
		, error: function() {
			console.log("실패");
		}
	});
}	

// 창이 사라질 때 탭 관련 쿠키 삭제
window.onbeforeunload = function(){
	if(isCookieTablClear){
		deleteCookie('tab');
	}
}
</script>

</head>
<body>
<!-- 플래너 대문 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;background-color:#EEEEEE;">

	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
	<div style="background-color:#EEEEEE;">
		
		<p id="isClose" style="display:none;">비공개</p>
		<p id="isOpen" style="display:none;">공개 </p>
		
		<label id="btnSelectShare" class="switch" style="display:none;" >
			<input id="isChecked" type="checkbox" name="checkbox" value="check">
			<span class="slider round"></span>
		</label>
		
<!-- 		게시자와 열람자가 같은 유저면 수정버튼을 -->
		<c:if test="${writtenUserView.user_idx eq loginedUserView.user_idx}">
		    <!-- <input id="btnModify" type="button" value="수정" style="float:right;"
		    onClick="location.href='/plan/write'"> -->
		    <form action="/plan/write" method="get" id="Modify">
		    	<input type="hidden" name="plan_idx" value="${planView.plan_idx}">
		    	<input id="btnModify" type="button" value="수정" style="float:right;">
		    </form>
		</c:if>
<!-- 		다르면 북마크 버튼을 보여준다 -->
		<c:if test="${writtenUserView.user_idx ne loginedUserView.user_idx}">
			<input id="btnBookMark" type="button" value="북마크" style="float:right;">
		</c:if>
		
	</div><br>
		
	<div id="header" style="background-color:#EEEEEE;text-align:center;">
		<div id="viewTitle">
			<h1 id="titleView" style="margin-bottom:0;">${planView.title }</h1>
			<h4 id="planRouteView"> 여행 경로 2개</h4> 
			<h4 id="dateView">${planView.start_date } ~ ${planView.end_date }</h4>
			<h4 id="traveledView">${planView.traveled }</h4>
		</div>
			<br>
	</div>
</div><br>
<!-- 플래너 입력 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">
	<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
	<div id="container" style="width:230px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="menu" style="background-color:#EEEEEE;height:100%;float:bottom;width:100%;border-radius:10px;">
			
			<div class="profileImage">
				<img src="${writtenUserView.profile }" style="border-radius:70px; width:100px;"/>
			</div>
			
			<br>
			<b>${writtenUserView.nickname }</b>님 <br>
			포스팅 : <b>${writtenUserView.totalPlanCnt }</b>개 <br>
			등급 : <b>${writtenUserView.grade }</b><br>
			<b>${planView.tot_dist }</b> km<br>
		</div><br>
		
	 	<!-- 가계부 DIV -->
		<div id="menu" style="background-color:#CCCCCC;height:100%;float:bottom;width:100%;border-radius:10px;">
			<b>가계부</b><br><br>
			
<!-- 			가계부 그래프 -->
			<a href="#layer2" id="btnAccGraph" >가계부 그래프</a><br>
			<div class="dim-layer">
			    <div class="dimBg"></div>
			    <div id="layer2" class="pop-layer">
			        <div class="pop-container">
			            <div class="pop-conts">
			                <div>
			                	<button id="totalGraph">전체</button> | <button id="daliyGraph">일일</button>
			                </div>
			                
			                <div id="viewTotalGraph" > 
			                	그래프 ㅇ_<
							</div>
							<div id="viewDaliyGraph" style="display:none;"> 
			                	그래프 >_ㅇ
							</div>
							
			                <div class="btn-r">
			                    <a href="#" class="btn-layerClose">X</a>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>

			교통 : 교통비입니다<br>
			식비 : 식비입니다<br>
			문화 : 추억의비용입니다<br>
			기타 : 헛짓거리비용입니다<br><br>
			총합 : ${accView.origin_cost }<br>
			환율 : ${accView.caled_cost }<br>
		</div><br>
		
		<!-- 일정 저장 -->

		<input id="planCommit" type="button" value="저장" onclick="store();" style="display:none;width:100%;">
		
		<!-- 검색 INPUT DIV -->
		<div id="googleSearch" style="float:bottom;width:100%;border-radius:10px; display:none;">
		검색 : <input id="pac-input" class="controls" type="text" placeholder="Search Box">
		    <div id="right-panel"
		    style="border-top:3px solid; border-bottom:3px solid; border-left:3px dashed; border-right:3px groove; padding:3px;">
		    <ul>
		     <li id="results" ></li>
		     </ul>
		    </div>
		</div><br>
		
	</div>
	
	<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
	<div id="container" style="width:900px; border-radius:10px;float:left;margin-left: 20px;">
		<!-- 일정 / 스토리 탭 DIV -->
		<ul class="tabs" id="tab-main">
			<li rel="tab-ttb">일정</li>
			<li rel="tab-story">스토리</li>
		</ul>
<!-- 		<div id="content" style="float:bottom;width:100%;"> -->
<!-- 			<button id="btnPlan" style="width:447px;background-color:#ff5555;border-radius:10px;">일정</button> -->
<!-- 			<button id="btnStory" style="width:447px;background-color:#5555ff;border-radius:10px;">스토리</button> -->
<!-- 		</div> -->
		
		<div class="tab-container">
		<div id="tab-ttb" class="tab-content tab-ttb">
			<!-- 구글맵 DIV -->
				<div id="map"></div>
		 	<!-- 타임테이블 -->
			<div id="calendar"></div>
	 	</div>
	 	
	 	<div id="tab-story" class="tab-content tab-story">
		 	<!-- 스토리테이블 -->
			<div id="viewStory"></div>
	 	</div>
	 	</div>
		
	</div>
</div>
</body>
</html>
