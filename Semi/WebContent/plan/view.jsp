<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 헤더  -->
<c:import url="../layout/headerWithMenu.jsp" />
<!-- 미니뷰 modal -->
<jsp:include page="/plan/timetable/miniViewTest.jsp"/>

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
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&callback=initMap">
</script>

<!-- 공개유무 슬라이드 버튼 -->
<style type="text/css">
	/* The switch - the box around the slider */
	.switch {
	  position: relative;
	display: inline-block;
	  width: 60px;
	  height: 34px;
	  vertical-align:middle;
	}
	 
	/* Hide default HTML checkbox */
/* 	.switch input {display:none;} */
	 
	/* The slider */
	.slider {
	  position: absolute;
	  cursor: pointer;
	  top: 0;
	  left: 0;
	  right: 0;
	  bottom: 0;
	  background-color: #ccc;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	 
	.slider:before {
	  position: absolute;
	  content: "";
	  height: 26px;
	  width: 26px;
	  left: 4px;
	  bottom: 4px;
	  background-color: white;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	 
	input:checked + .slider {
	  background-color: #2196F3;
	}
	 
	input:focus + .slider {
	  box-shadow: 0 0 1px #2196F3;
	}
	 
	input:checked + .slider:before {
	  -webkit-transform: translateX(26px);
	  -ms-transform: translateX(26px);
	  transform: translateX(26px);
	}
	 
	/* Rounded sliders */
	.slider.round {
	  border-radius: 34px;
	}
	 
	.slider.round:before {
	  border-radius: 50%;
	}
	 
	p {
	margin:0px;
	display:inline-block;
	font-size:15px; 
	font-weight:bold; 
	} 
/* ------------------------------------------------------------------------ */

	/* 가계부 그래프 */
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
		height: 100%;
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
		font-weight: bold;
		font-size: 1.5em;
		padding-top: 5px;
	}
	
	.fc-bg:not(:first-child){
		margin-left: 10px;  /* fc-bg(이벤트 덮고 있는 투명도 있는 판?)css 수정 , 왼쪽에 색 진하게 하는 효과줌*/
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

</script>
<script>
//저장하기
function store(){
	// 캘린더에 있는 모든 이벤트 정보 가져오기
	var events = $("#calendar").fullCalendar('clientEvents');
	console.log("저장할 때 events목록");
	console.log(events);
	
	var timetables = [];
	// form input 생성(넘겨줄 값)
	events.forEach(function(event){ // 모든 리스트 돌면서 timetable json 하나씩 생성
		// timetable json 생성
		
		var timetable = {
				ttb_idx: event.id
				, place_name: event.title
				, address: event.address
				, start_time: event.start.format("YYYY-MM-DD HH:mm") // 24시 형태
				, end_time: event.end.format("YYYY-MM-DD HH:mm") // 24시 형태
				, lat: event.lat
				, lng: event.lng
				, photo_url: event.photo_url
				, place_id: event.place_id
		}
	
		timetables.push(timetable);
	
		// --- json 여러개 낱개로 넘겨주기
		// input 태그 생성
// 		$("#planForm").append("<input type='hidden' name='events'>");
// 		$("input[name*='events']:last-child").val(JSON.stringify(timetable));
	});
		// --- json list 로 묶어서 넘겨주기
		// input 태그 생성
		$("#planForm").append("<input type='hidden' name='events'>");
		// 생성된 태그의 value값 설정 , timetable json 마샬링해서 지정
		$("input[name='events']:last-child").val(JSON.stringify(timetables));
	
	// submit
	console.log(timetables);
	$("#planForm").submit();     
}
</script>

<script type="text/javascript">
// 읽기모드일때, 검색창 on/off
var isModify = 0;

$(document).ready(function() {
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, true);
	
// 	스토리탭
	$("#btnStory").click(function() {
		document.getElementById("calendar").style.display= "none";
		document.getElementById("viewStory").style.display= "block";
		document.getElementById("googleMap").style.display= "none";
		document.getElementById("googleSearch").style.display= "none";
		
		//AJAX 처리하기
		$.ajax({ 	
			type: "post"
			, url: "/story/view"
// 			, data: {"isModify" : isModify }
			, dataType: "html"
			, success: function( d ) {
				
				$("#viewStory").html(d);
				
			}
			, error: function() {
				console.log("실패");
			}
		});
				
	});
// 	타임테이블탭
	$("#btnPlan").click(function() {
		document.getElementById("viewStory").style.display= "none";
		document.getElementById("calendar").style.display= "block";
		document.getElementById("googleMap").style.display= "block";
		if(isModify == 1) document.getElementById("googleSearch").style.display= "block";
	});
	
// 	수정버튼
	$("#btnModify").click(function() {
		isModify = 1;
		document.getElementById("viewStory").style.display= "none";
		document.getElementById("calendar").style.display= "block";
		document.getElementById("googleMap").style.display= "block";
		document.getElementById("googleSearch").style.display= "block";
		document.getElementById("planCommit").style.display= "block";
		document.getElementById("btnModify").style.display= "none";
		document.getElementById("btnSelectShare").style.display= "block";
		document.getElementById("btnAccGraph").style.display= "none";
		
		document.getElementById("viewTitle").style.display= "none";
		document.getElementById("editTitle").style.display= "block";
		
		// 타임테이블 수정  모드로 변경
		$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
		$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
	});
	
// 	$("#btnSelectShare").click(function() {
// 	});

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
	
// 	저장버튼
	$("#planCommit").click(function() {
		isModify = 0;
		document.getElementById("googleSearch").style.display= "none";
		document.getElementById("planCommit").style.display= "none";
		document.getElementById("btnModify").style.display= "block";
		document.getElementById("btnSelectShare").style.display= "none";
		document.getElementById("btnAccGraph").style.display= "block";
		document.getElementById("viewDaliyGraph").style.display= "none";
		document.getElementById("viewTotalGraph").style.display= "none";
		
		document.getElementById("viewTitle").style.display= "block";
		document.getElementById("editTitle").style.display= "none";
		
		// 타임테이블 읽기 모드로 변경
		$('#calendar').fullCalendar('option', 'editable', false); // 수정 불가능하게
		$('#calendar').fullCalendar('option', 'droppable', false); // 드롭 불가능하게

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
	
	$(".planDate").on("change", function(){
		var start_date = $(".planDate[name='editStartDate']").val();
		
// 		$('#calendar').fullCalendar('option', 'defaultDate',start_date);
		initFullCalendar(start_date, planEndDate, false);
	});
	
}); // $(document).ready() End
	
	
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
		<c:if test="${planView.user_idx eq userView.user_idx}">
		    <input id="btnModify" type="button" value="수정" style="float:right;">
		</c:if>
<!-- 		다르면 북마크 버튼을 보여준다 -->
		<c:if test="${planView.user_idx ne userView.user_idx}">
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
		
		
		<div id="editTitle" style="display:none;">
			<form action="/plan/update" method="post" id="planForm">
				<div >
					<input type="hidden" name="plan_idx" value="${planView.plan_idx }">
					제목 : <input id="editTitleView" name="editTitleView" type="text" value="${planView.title }" /><br><br>
					출발일 : <input name="editStartDate" class ="planDate" type="date" value="${planView.start_date }"/>
					도착일 : <input name="editEndDate" class ="planDate" type="date" value="${planView.end_date }"/><br><br>
					여행 전 <input id="editTravledBefore" name="editTraveled" type="radio" value="1" checked="checked"/> / 여행 후 <input id="editTravledAfter" name="editTraveled" type="radio" value="0" /><br><br>
				</div>
			</form>
		</div>
			<br>
	</div>
</div><br>
<!-- 플래너 입력 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">
	<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
	<div id="container" style="width:230px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="menu" style="background-color:#EEEEEE;height:100px;float:bottom;width:100%;border-radius:10px;">
			${userView.profile }<br>
			<b>${userView.nickname }</b>님 <br>
			포스팅 : <b>${userView.totalPlanCnt }</b>개 <br>
			등급 : <b>${userView.grade }</b><br>
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
<!-- 		<div id="menu" style="float:bottom;width:100%;border-radius:10px;"> -->
<!-- 			<form action="/update/ttb" method="post" id="ttbFrom"> -->
<%-- 				<input type="hidden" name="plan_idx" value="${planView.plan_idx }"> --%>
				<input id="planCommit" type="button" value="저장" onclick="store();" style="display:none;width:100%;">
<!-- 			</form> -->
<!-- 		</div><br> -->
		
		<!-- 검색 INPUT DIV -->
		<div id="googleSearch" style="float:bottom;width:100%;border-radius:10px;display:none;">
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
	<div id="container" style="width:900px; border-radius:10px;float:left;">
		<!-- 일정 / 스토리 탭 DIV -->
		<div id="content" style="float:bottom;width:100%;">
			<button id="btnPlan" style="width:447px;background-color:#ff5555;border-radius:10px;">일정</button>
			<button id="btnStory" style="width:447px;background-color:#5555ff;border-radius:10px;">스토리</button>
		</div>
		
		<!-- 구글맵 DIV -->
		<div id="googleMap" style="background-color:#DDDDDD;height:500px;float:bottom;width:100%;border-radius:10px;">
			<div id="map"></div>
	 	</div>
	 	
	 	<!-- 타임테이블 & 스토리테이블 -->
		<div id="calendar"></div>
		<div id="viewStory" ></div>
		
	</div>
</div>
</body>
</html>
