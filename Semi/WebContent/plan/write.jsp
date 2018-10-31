<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- 헤더  -->
<c:import url="../layout/headerWithMenu.jsp" />
<!-- 미니뷰 modal -->
<c:import url="/plan/timetable/miniViewWrite.jsp"/>

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
	/* The switch - the box around the slider */
	.switch {
	  position: relative;
	display: inline-block;
	  width: 60px;
	  height: 34px;
	  vertical-align:middle;
	}
	
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

</script>
<script>
//저장하기
function store(beforeTtbIdx, afterTtbIdx){
	// 캘린더에 있는 모든 이벤트 정보 가져오기
	var events = $("#calendar").fullCalendar('clientEvents');
	console.log("저장할 때 events목록");
	console.log(events);
	
	var timetables = [];
	// form input 생성(넘겨줄 값)
	events.forEach(function(event){ // 모든 리스트 돌면서 timetable json 하나씩 생성
		// timetable json 생성
		var timetable = getTtbJsonForServer(event);
	
		timetables.push(timetable);
	});

	
	// 미니뷰 작성인 경우
	// ttb_idx바꿔야하는 타임테이블 찾아서 ttb_idx값 변경
	if(beforeTtbIdx!=null && afterTtbIdx != null){
		for(var i = 0; i<timetables.length; i++){
			if(timetables[i].ttb_idx == beforeTtbIdx){
				timetables[i].ttb_idx = afterTtbIdx;
				break;
			}
		}
	}
	
	// --- json list 로 묶어서 넘겨주기
	// input 태그 생성
	$("#planForm").append("<input type='hidden' name='events'>");
	// 생성된 태그의 value값 설정 , timetable json 마샬링해서 지정
	$("input[name='events']:last-child").val(JSON.stringify(timetables));
	
	// submit
// 	console.log("---store()----")
// 	console.log(timetables);
	$("#planForm").submit();     
}
</script>

<script type="text/javascript">
// 읽기모드일때, 검색창 on/off
var isModify = 1;

$(document).ready(function() {
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, true);
	$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
	$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
	
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
		console.log("wrte.jsp isModify : " + isModify);
		
// 		//AJAX 처리하기
// 		$.ajax({ 	
// 			type: "get"
// 			, url: "/plan"
// 			, data: {"plan_idx" : plan_idx }
// 			, dataType: "html"
// 			, success: function( d ) {
				
// 				$("#").html(d);
				
// 			}
// 			, error: function() {
// 				console.log("실패");
// 			}
// 		});
		
		// 타임테이블 읽기 모드로 변경
		$('#calendar').fullCalendar('option', 'editable', false); // 수정 불가능하게
		$('#calendar').fullCalendar('option', 'droppable', false); // 드롭 불가능하게

		
	});
	
	// 일정 일자 변경할때의 처리
	var beforeStartDate = planStartDate;
	var beforeEndDate = planEndDate;
	$(".planDate").on("change", function(){
		// 바뀐 날짜 값 받아오기
		var changedStartDate = $(".planDate[name='editStartDate']").val();
		var changedEndDate = $(".planDate[name='editEndDate']").val();
		
		// 예외처리
		if(changedStartDate > changedEndDate){
			alert("일정의 마지막일이 시작일보다 작을 수 없습니다.");
			$(".planDate[name='editStartDate']").val(beforeStartDate);
			$(".planDate[name='editEndDate']").val(beforeEndDate);
			return;
		}
		
		// 바뀐 시작일이 기존 시작일보다 큰 경우(미래인 경우) 
		var alertStartDate = moment(changedStartDate) > moment(planStartDate);
		// 바뀐 종료일이 기족 종료일보다 작은 경우(과거인 경우)
		var alertEndDate = moment(changedEndDate) <  moment(planEndDate);
		if( alertStartDate || alertEndDate ){
			// 경고창 띄워주기
			$.ajax({
				url: '/plan/timetable/alert.jsp'
				, method: "GET"
				, dataType: "html"
				, success: function(d){
					$('body').append(d);
					$("#alertOnDateChange").modal('show');
					// 모달창이 켜지면
					$("#alertOnDateChange").on('shown.bs.modal',function(e){
						// 클릭 이벤트 걸어줌
						$("#btnOkOnDateChange").on("click", function(){
							// 바뀐 날짜 정보 저장해놓기 (추후에 캔슬했을 때 이 값으로 다시 돌려놓음)
							beforeStartDate = changedStartDate;
							beforeEndDate = changedEndDate;
							
							// 기간 외의 타임테이블 삭제하고
							deleteTimetableByDate(changedStartDate, changedEndDate);
							// 캘린더 다시 그려주기
							initFullCalendar(changedStartDate, changedEndDate, false);
						});
						// 취소버튼 누르면
						$("#btnCancelOnDateChange").on("click", function(){
							// 바꾸기 전  날짜로 다시 변경
							if(alertStartDate){
								$(".planDate[name='editStartDate']").val(beforeStartDate);
							}else if(alertEndDate){
								$(".planDate[name='editEndDate']").val(beforeEndDate);
							}
						});
					})
				}
			});
		} else{
			// 캘린더 다시 그려주기
			initFullCalendar(changedStartDate, changedEndDate, false);
		} 
		
		// 새로 캘린더 그려서 읽기모드로 세팅 -> 수정  모드로 변경
		$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
		$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
	});
	
    
//     console.log("현재 탭 쿠키 값: " + getCookie('tab'));
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
	
</script>

</head>
<body>
<!-- 플래너 대문 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;background-color:#EEEEEE;">

	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
	<div style="background-color:#EEEEEE;">
		
		<p id="isClose" style="display:none;">비공개</p>
		<p id="isOpen" style="display:none;">공개 </p>
		
		<label id="btnSelectShare" class="switch" >
			<input id="isChecked" type="checkbox" name="checkbox" value="check">
			<span class="slider round"></span>
		</label>
	</div><br>
		
	<div id="header" style="background-color:#EEEEEE;text-align:center;">
		
		<div id="editTitle" >
			<form action="/plan/update" method="post" id="planForm">
				<div >
					<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
					<input type="hidden" name="user_idx" value="${planView.user_idx}" />
					
					제목 : <input id="editTitleView" name="editTitleView" type="text" value="${planView.title }" /><br><br>
					출발일 : <input name="editStartDate" class ="planDate" type="date" value="${planView.start_date }"/>
					도착일 : <input name="editEndDate" class ="planDate" type="date" value="${planView.end_date }"/><br><br>
					<select name="editTraveled" >
						<c:if test="${planView.traveled eq 1 }">
							<option value="1" selected="selected">여행 전</option>
							<option value="0">여행 후</option>
						</c:if>
						<c:if test="${planView.traveled eq 0 }">
							<option value="1" >여행 전</option>
							<option value="0" selected="selected">여행 후</option>
						</c:if>
					</select>
<!-- 					여행 전 <input id="editTravledBefore" name="editTraveled" type="radio" value="1" checked="checked"/> -->
<!-- 					여행 후 <input id="editTravledAfter" name="editTraveled" type="radio" value="0" /><br><br> -->
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

			항공료 : ${airfare }<br> 
			교통 : ${traffic }<br>
			숙박 : ${stay }<br>
			입장료 : ${admission }<br>
			음식 : ${food }<br>
			오락 : ${play }<br>
			쇼핑 : ${shop }<br>
			기타 : ${etc }<br><br>
			<b>총합 : ${acc_total }</b><br>
			<b>환율 : ${accView.caled_cost }</b><br>
		</div><br>
		
		<!-- 일정 저장 -->
		<button id="planCommit" name="plan_idx" value="${planView.plan_idx}" onclick="store();" style="width:100%;">저장 </button>
		
		<!-- 검색 INPUT DIV -->
		<div id="googleSearch" class="tab-content tab-ttb" style="float:bottom;width:100%;border-radius:10px;">
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
				<div id="map" style="background-color:#DDDDDD;height:500px;float:bottom;width:100%;"></div>
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
