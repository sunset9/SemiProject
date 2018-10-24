<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../layout/headerWithMenu.jsp" />

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
	.switch input {display:none;}
	 
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
/* 	  font-weight:bold; */
	}
  
	#map {
		height: 100%;
	}
  
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

$(document).ready(function(){
	// 뿌려줄 timetable 리스트 가져오기
	var timetables = getTimetablesFromServer();
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, timetables);
	
});
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
				place_name: event.title
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
// 		$("form").append("<input type='hidden' name='events'>");
// 		$("input[name*='events']:last-child").val(JSON.stringify(timetable));
	});
		// --- json list 로 묶어서 넘겨주기
		// input 태그 생성
		$("form").append("<input type='hidden' name='events'>");
		// 생성된 태그의 value값 설정 , timetable json 마샬링해서 지정
		$("input[name*='events']:last-child").val(JSON.stringify(timetables));
	
	// submit
	console.log(timetables);
	$("form").submit();
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	$("#btnStory").click(function() {
		document.getElementById("calendar").style.display= "none";
		document.getElementById("viewStory").style.display= "block";
		document.getElementById("googleMap").style.display= "none";
		document.getElementById("googleSearch").style.display= "none";
		
		
		
		//AJAX 처리하기
		$.ajax({
			type: "post"
			, url: "/story/view"
			//, data: { }
			, dataType: "html"
			, success: function( d ) {
				
				$("#viewStory").html(d);
				
			}
			, error: function() {
				console.log("실패");
			}
		});
				
	});
	
	$("#btnPlan").click(function() {
		document.getElementById("viewStory").style.display= "none";
		document.getElementById("calendar").style.display= "block";
		document.getElementById("googleMap").style.display= "block";
		document.getElementById("googleSearch").style.display= "block";
	});
});

</script>

</head>

<body>
<!-- 플래너 대문 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">

	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
	<div id="header" style="background-color:#EEEEEE; margin:3px;">
		<script>
		var check = $("input[type='checkbox']");
		check.click(function(){
		  $("p").toggle();
		});
		</script>
		
		<label class="switch">
			<input type="checkbox">
			<span class="slider round"></span>
		</label>
		<p>비공개</p>
		<p style="display:none;">공개</p>
		
		<input id="btnModify" type="button" value="수정" style="position: absolute; right: 15px;">
		<input id="btnBookMark" type="button" value="북마크" style="position: absolute; right: 30px;">
		
		<h1 style="margin-bottom:0;" align="center">${planView.title }</h1>
			<h4 align="center">여행 경로 2개</h4>
			<h4 align="center">${planView.start_date } ~ ${planView.end_date }</h4>
			<h4 align="center">${planView.traveled }</h4>
			<br>
	 </div>
</div>
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
		<div id="menu" style="background-color:#CCCCCC;height:200px;float:bottom;width:100%;border-radius:10px;">
			<b>가계부</b><br>
			교통 : <input type="text"><br>
			식비 : <input type="text"><br>
			문화 : <input type="text"><br>
			기타 : <input type="text"><br><br>
			총합 : ${accView.origin_cost }<br>
			환율 : ${accView.caled_cost }<br>
		</div><br>
		
		<!-- 일정 저장 -->
		<div id="menu" style="float:bottom;width:100%;border-radius:10px;">
			<form action="/update/ttb" method="post">
				<input type="hidden" name="plan_idx" value="${planView.plan_idx }">
				<input type="button" value="저장" onclick="store();" style="width:100%;">
			</form>
		</div><br>
		
		<!-- 검색 INPUT DIV -->
		<div id="googleSearch" style="float:bottom;width:100%;border-radius:10px;">
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
		<div id="content" style="background-color:#999999;height:50px;float:bottom;width:100%;border-radius:10px;">
			<button id="btnPlan" >일정</button>
			<button id="btnStory" >스토리</button>
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
