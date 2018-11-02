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

<link rel="stylesheet" href="/resources/planCommStyle.css">

<!-- 공개유무 슬라이드 버튼 -->
<style type="text/css">
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
	
	/* 저장버튼 활성화 버전*/
	#planSaveBtn:not([disabled]){
	    height: 34px;
		background: #4FB99F;
	    font-weight: bold;
	    color: #333;
	}
	
	/* 저장버튼 비활성화 버전*/
	#planSaveBtn[disabled]{
		height: 34px;
		background: #eee;
		color: #ccc;
	}
	 
	#googleSearch{
		width:100%;
		border-radius:10px;
		margin-top: 10px;
		border: 1px solid #ccc;
	}
	
	#pac-input{
		display: block;
		margin: 6px auto;
		width: 93%;
	    background: url(/resources/img/searchBg.png) -5px center no-repeat;
 	    border: 1px solid #ccc; 
	    padding: 5px 3px 5px 35px;
	}
	#searchResultView{
		padding:3px;
	}
</style>

<script>
	
	//숫자만 입력
	function Numberchk() { 
		if (event.keyCode < 46 || event.keyCode > 57) event.returnValue = false; 
	} 

	//콤마찍기
	function vComma(obj) { 
		var str    = "" + obj.value.replace(/,/gi,''); // 콤마 제거 
		var regx    = new RegExp(/(-?\d+)(\d{3})/); 
		var bExists = str.indexOf(".",0); 
		var strArr  = str.split('.'); 
		
		while(regx.test(strArr[0])){ 
			strArr[0] = strArr[0].replace(regx,"$1,$2"); 
		} 
		if (bExists > -1) 
			obj.value = strArr[0] + "." + strArr[1]; 
		else 
			obj.value = strArr[0]; 
	} 

	//공백제거
	function trim(str) { 
		return str.replace(/(^\s*)|(\s*$)/g, ""); 
	} 

	function getNumber(str) { 
		str = "" + str.replace(/,/gi,''); // 콤마 제거 
		str = str.replace(/(^\s*)|(\s*$)/g, ""); // trim 
		return (new Number(str)); 
	} 
	
</script>

<script>

// 서버에서 넘어온 일정의 시작, 끝 날짜 정보
var planStartDate = '${planView.start_date}';
var planEndDate = '${planView.end_date}';
// 서버에서 넘어온 timetable, location 정보들
var ttbList = ${ttbList };
var locList = ${locList };

var plan_idx = ${planView.plan_idx};

//환율정보
var USD_rate=0;
var KRW_rate=0;
var JPY_rate=0;

var isModify = 1;
var isStayWriteMode = false;

var isAlreadyAlert = false;
</script>
<script>
//저장하기
function store(beforeTtbIdx, afterTtbIdx){
	setCookie("isCookieTabClear", "false");
	
	activeStoreBtn(false);
	
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
	$("input[name='events']:last-child").val();
	
	// submit
// 	console.log("---store()----")
// 	console.log(timetables);
// 	$("#planForm").submit();
	
	var succ = false;
	$.ajax({
		url: "/plan/update"
		, type: "post"
		, async: false
		, dataType: "html"
		, data: {
			plan_idx: '${planView.plan_idx}'
			, user_idx: '${planView.user_idx}'
			, editOpened: $('.planInfo[name=editOpened]').val()
			, editTraveled: $('.planInfo[name=editTraveled]').val()
			, editTitleView: $('.planInfo[name=editTitleView]').val()
			, editStartDate: $('.planInfo[name=editStartDate]').val()
			, editEndDate: $('.planInfo[name=editEndDate]').val()
			, events: JSON.stringify(timetables)
		}
		, success: function(){
			if(!isStayWriteMode){
				window.location = "/plan?plan_idx=" + $('input[name=plan_idx]').val();
			}
			succ = true;
		}
		, error: function(){
			succ = false;
		}
	});
	return succ;
}
</script>

<script type="text/javascript">
// 읽기모드일때, 검색창 on/off
$(document).ready(function() {
    //환율정보 가져오는 api
    $.ajax({ 
      url: "http://api.manana.kr/exchange/rate/KRW/JPY,KRW,USD.json", 
      type: "GET", 
      crossDomain: true, 
      dataType: "json", 
      success: function (data) { 
   	   	 JPY_rate = data[0].rate; 
            KRW_rate = data[1].rate;
            USD_rate = data[2].rate;
        },
      error : function (e) {
        console.log(e);
   
		}
	 }); 
	
	// isCookieTabClear 플래그가 true 이고
	// 새로고침 된게 아닌 경우 (performance.navigation.type == 1 : 새로고침)
	if(getCookie("isCookieTabClear") == 'true' && performance.navigation.type != 1){
		deleteCookie('tab');
	}
	setCookie("isCookieTabClear", "true");
	
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, true);
	$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
	$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
	
	// 	수정모드일 때, 공개유무버튼
	$("#isChecked").click(function(){
			
// 		  $("p").toggle();
		  check = $("input[type='checkbox']").is(':checked');
		  
		  if(check) {
			  check = 1;
			document.getElementById("isClose").style.display= "none";
			document.getElementById("isOpen").style.display= "block";
		  } else {
			  check = 0;
			document.getElementById("isClose").style.display= "block";
			document.getElementById("isOpen").style.display= "none";
		  }
			console.log(check);
			
			return check;
		});

	
	// 일정 일자 변경할때의 처리
	var beforeStartDate = planStartDate;
	var beforeEndDate = planEndDate;
	$(".planInfo").on("change", function(){
		// 바뀐 날짜 값 받아오기
		var changedStartDate = $(".planInfo[name='editStartDate']").val();
		var changedEndDate = $(".planInfo[name='editEndDate']").val();
		
		// 예외처리
		if(changedStartDate > changedEndDate){
			alert("일정의 마지막일이 시작일보다 작을 수 없습니다.");
			$(".planInfo[name='editStartDate']").val(beforeStartDate);
			$(".planInfo[name='editEndDate']").val(beforeEndDate);
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
								$(".planInfo[name='editStartDate']").val(beforeStartDate);
							}else if(alertEndDate){
								$(".planInfo[name='editEndDate']").val(beforeEndDate);
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
	
	// 처음 탭 선택하여 띄워주기
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
	    
    // 탭 선택 시 속성값, 탭 쿠키값 변경
	$('#tab-main li').click(function(){
		var clickTab = $(this);
		// 저장버튼 활성화 상태이면 탭 안넘어가도록 경고창
		if($("#planSaveBtn").attr('disabled') == null){
			// 저장 확인 창 띄움
			$.confirm({
			    title: '저장하시겠습니까?',
			    content: '저장하시지 않으면 작성 중인 정보를 잃습니다.',
			    buttons: {
			    	confirm: { // 확인 버튼
			    		text: '저장'
			    		, btnClass: 'btn-blue'
			    		, action: function(){
			    			isStayWriteMode = true; // 수정모드 유지
			    			var succ = store(); // 저장 동작
				            if(succ){
				            	// 저장 버튼 비활성화
					            activeStoreBtn(false);
				    			// 탭 변경
				    			changeTab(clickTab); 
				    		}
			    			}
			        }, 
		        	취소: function () { // 취소 버튼
			        },
			    }
			});
		} else { // 저장버튼 비활성화 상태면 그냥 진행
			// 탭 변경
			changeTab(clickTab);
		}
		
	}); // tab on click 이벤트 설정
	
	
	// 일정 정보 수정하는 경우 저장버튼 활성화
	$("#planForm").on('change',function(){
		activeStoreBtn(true);
	});
	
}); // $(document).ready() End

function changeTab(clickTab){
	// active클래스 속성 변경
	$("#tab-main li").removeClass("active");
	clickTab.addClass("active");
    
    // 선택한탭의 내용 띄워지게
    $(".tab-content").hide();
    var activeTab = clickTab.attr("rel");
    $("." + activeTab).show();
    
    if(activeTab == 'tab-ttb'){ // 타임테이블 탭 선택한 경우
		setCookie('tab','tab-ttb');
		initFullCalendar(planStartDate, planEndDate, false);
		$('#calendar').fullCalendar('option', 'editable', true); // 수정 가능하게
		$('#calendar').fullCalendar('option', 'droppable', true); // 드롭할 수 있게
		initMap();
    }else if(activeTab == 'tab-story'){ // 스토리 탭 선택한 경우
		setCookie('tab','tab-story');
    	// ajax 통신으로 내용 불러오기
		displayStoryView();
    }
} 

// 스토리 뷰 ajax통신으로 띄워주기
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

// 저장버튼 활성화/비활성화 상태 바꿔주는 메소드
function activeStoreBtn(isEdit){
	if(isEdit){ // 수정했으면 버튼 활성화
		$("#planSaveBtn").removeAttr("disabled");
	}else{ // 저장 상태이면 버튼 비활성화
		$("#planSaveBtn").attr("disabled", 'disabled');
	}
}

// 페이지나가거나 새로고침 시 경고 메세지 띄워주기
window.onbeforeunload = function(){
	// 저장버튼 활성화 상태이고, 경고창을 아직 띄우지 않았을 경우
	if($("#planSaveBtn").attr('disabled') == null && isAlreadyAlert == false){
		return false;
	}
};
</script>

<header>
<!-- 플래너 배너 -->
<div id="container" style="width:100%; height:400px; border-radius:10px; background-image:url('${planView.bannerURL }');background-size: 100% 100%;">
	<form action="/plan/banner" method="post" enctype="multipart/form-data">
		<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
		<input type="hidden" name="user_idx" value="${planView.user_idx}" />
		<input type="file" name="uploadFile" style="float:right;">
		<button style="float:right;">배너 저장</button>
	</form>
	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
	<div id="editTitle" style="text-align:center;">
	
		<form action="/plan/update" method="post" id="planForm">
		
		<select name="editOpened" class ="planInfo">
			<c:if test="${planView.opened eq 1 }">
				<option value="1" selected="selected">공개</option>
				<option value="0">비공개</option>
			</c:if>
			<c:if test="${planView.opened eq 0 }">
				<option value="1" >공개</option>
				<option value="0" selected="selected">비공개</option>
			</c:if>
		</select>
		
			<div>
				<input type="hidden" name="isSendWriteMode" value="false">
				<input type="hidden" name="plan_idx" value="${planView.plan_idx}" />
				<input type="hidden" name="user_idx" value="${planView.user_idx}" />
				
				제목 : <input name="editTitleView" class="planInfo" type="text" value="${planView.title }" /><br><br>
				출발일 : <input name="editStartDate" class ="planInfo" type="date" value="${planView.start_date }"/>
				도착일 : <input name="editEndDate" class ="planInfo" type="date" value="${planView.end_date }"/><br><br>
				<select name="editTraveled" class ="planInfo" >
					<c:if test="${planView.traveled eq 1 }">
						<option value="1" selected="selected">여행 전</option>
						<option value="0">여행 후</option>
					</c:if>
					<c:if test="${planView.traveled eq 0 }">
						<option value="1" >여행 전</option>
						<option value="0" selected="selected">여행 후</option>
					</c:if>
				</select>
			</div>
			
		</form>
	</div>
		<br>
</div><br>
</header>
<nav>
<!-- 플래너 입력 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">
	<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
	<div id="container" style="width:230px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="userInfoView">
			
			<div class="profileImage">
				<img src="${writtenUserView.profile }" style="border-radius:70px; width:100px;"/>
			</div>
			<br>
			<b>${writtenUserView.nickname }</b>님 <br>
			포스팅 : <b>${writtenUserView.totalPlanCnt }</b>개 <br>
			등급 : <b>${writtenUserView.grade }</b><br>
			<b><fmt:formatNumber value='${writtenUserView.totalDist }' pattern="0.##"/></b> km<br>
		</div><br>
		
	 	<!-- 가계부 DIV -->
		<div id="accountView">

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
		<button id="planViewModeBtn" onclick="store();" style="width:100%;">읽기 모드로</button>
		<!-- 일정 저장 -->
		<button id="planSaveBtn" onclick="store();" style="width:100%;" disabled="true">저장 </button>
		
		<!-- 검색 INPUT DIV -->
		<div id="googleSearch" class="tab-content tab-ttb">
		<input id="pac-input" class="controls" type="text" placeholder="장소 검색">
		    <div id="searchResultView">
		    <ul>
				<li id="results" ></li>
			</ul>
	    	</div>
		</div><br>
	</div>
</div>
</nav>
<section>
	<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
	<div id="container" style="width:900px; border-radius:10px;float:left;margin-left: 20px;">
		<!-- 일정 / 스토리 탭  -->
		<ul class="tabs" id="tab-main">
			<li rel="tab-ttb">일정</li>
			<li rel="tab-story">스토리</li>
		</ul>
		
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

</section>
<!-- Maps JavaScript API 로드 -->
<script async defer
 src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&language=ko&callback=initMap">
</script>
</body>
</html>
