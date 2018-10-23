<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/headerWithMenu.jsp" />

<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

<!-- 개인 utils -->
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
	  font-weight:bold;
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

<!-- 구글맵 -->
<style>
     /* Always set the map height explicitly to define the size of the div
      * element that contains the map. */
	#map {
		height: 100%;
	}
</style>
<script>
// 서버에서 넘어온 일정의 시작, 끝 날짜 정보
// var planStartDate = '${startDate}';
// var planEndDate = '${endDate}';
var planStartDate = '2018-04-17';
var planEndDate = '2018-04-21';
	
$(document).ready(function(){
	// 뿌려줄 timetable 리스트 가져오기
	var timetables = getTimetablesFromServer();
	
	// test-log
// 	console.log("recv측. 받은 events목록");
// 	console.log(timetables);
	
	// 브라우저에 timetable 그려주기
	initFullCalendar(planStartDate, planEndDate, timetables);
	
});
</script>
<script>
//서버로부터 받은 timetable, location JSON리스트 필요한 정보만 파싱
function getTimetablesFromServer(){
	var timetables=[];
	
// 	var ttbList = ${ttbList };
// 	var locList = ${locList };
	var ttbList = [];
	var locList = [];

	for(var i = 0; i<ttbList.length; i++){
		var timetable = {
			title: locList[i].place_name
			, start: ttbList[i].start_time
			, end: ttbList[i].end_time
			, lat: locList[i].lat
			, lng: locList[i].lng
			, address: locList[i].address
		}
		
		timetables.push(timetable);
	}
	
	return timetables;
}
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
		
		<input type="button" value="수정" style="position: absolute; right: 15px;">
		
		<h1 style="margin-bottom:0;" align="center">로고와 함께하는 역삼역 투어!!</h1>
			<h4 align="center">대한민국 서울</h4>
			<h4 align="center">2018.09.10 ~ 2018.09.15</h4>
			<h4 align="center">여행 전</h4>
			<br>
	 </div>
</div>
<!-- 플래너 입력 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">

	<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
	<div id="container" style="width:230px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="menu" style="background-color:#EEEEEE;height:100px;float:bottom;width:100%;border-radius:10px;">
			사진<br>
			<b>사용자</b>님 <br>
			포스팅 : 10개 <br>
			등급 : 여행작가	 乃<br>
			총 여행 거리 : 80km
		</div>
		
	 	<!-- 가계부 DIV -->
		<div id="menu" style="background-color:#CCCCCC;height:135px;float:bottom;width:100%;border-radius:10px;">
			<b>가계부</b><br>
			교통 : <input type="text"><br>
			식비 : <input type="text"><br>
			문화 : <input type="text"><br>
			기타 : <input type="text"><br>
		</div>
		
		<!-- 검색 INPUT DIV -->
		<div id="menu" style="float:bottom;width:100%;border-radius:10px;">
		검색 : <input id="pac-input" class="controls" type="text" placeholder="Search Box">
		    <div id="right-panel"
		    style="border-top:3px solid; border-bottom:3px solid; border-left:3px dashed; border-right:3px groove; padding:3px;">
		    <ul>
		     <li id="results" ></li>
		     </ul>
		    </div>
		</div>
	</div>
	
	<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
	<div id="container" style="width:1000px; border-radius:10px;float:left;">
		<!-- 일정 / 스토리 탭 DIV -->
		<div id="content" style="background-color:#999999;height:50px;float:bottom;width:100%;border-radius:10px;">
			<input type="button" value="일정" >
			<input type="button" value="스토리" >
		</div>
		
		<!-- 구글맵 DIV -->
		<div id="content" style="background-color:#DDDDDD;height:500px;float:bottom;width:100%;border-radius:10px;">
			<div id="map"></div>
	 	</div>
	 	
	 	<!-- 타임테이블 -->
		<div id="calendar"></div>
	</div>
</div>
</body>
</html>
