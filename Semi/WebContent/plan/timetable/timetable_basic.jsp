<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src='/resources/timetable/jquery-ui.min.js'></script>
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
<style>

  body {
    margin-top: 240px;
    text-align: center;
    font-size: 14px;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
  }

  #wrap {
    width: 1100px;
    margin: 0 auto;
  }

  #external-events{
    float: left;
    width: 150px;
    padding: 0 10px;
    border: 1px solid #ccc;
    background: #eee;
    text-align: left;
  }

  #external-events h4 {
    font-size: 16px;
    margin-top: 0;
    padding-top: 1em;
  }

  #external-events .fc-event  {
    margin: 10px 0;
    cursor: pointer;
  }

  #external-events p {
    margin: 1.5em 0;
    font-size: 11px;
    color: #666;
  }

  #external-events p input {
    margin: 0;
    vertical-align: middle;
  }

  #calendar {
    float: right;
    width: 900px;
    height: 1000px;
    margin-bottom: 20px;
  }
  
  #btnStore {
  	float: left;
  	width: 170px;
  	padding: 0px 10px;
  	margin-top: 10px;
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
  
  .fc-title{ /* 이벤트 안의 제목 부분 */
  	font-weight: bold;
  	font-size: 1.5em;
  	padding-top: 5px;
  }
  
  .fc-bg:not(:first-child){
  	margin-left: 10px;  /* fc-bg(이벤트 덮고 있는 투명도 있는 판?)css 수정 , 왼쪽에 색 진하게 하는 효과줌*/
  }
</style>

<script type="text/javascript">
$(document).ready(function(){
	
	/* initialize the external events
    -----------------------------------------------------------------*/
	/*
	// 테스트용 div 드래그앤 드롭
    $('#external-events .fc-event').each(function() {
		// store data so the calendar knows to render an event upon drop
		$(this).data('event', {
		  title: $.trim($(this).text()), // use the element's text as the event title
		  stick: true // 드롭한 이벤트 고정 (false: 다음 등의 버튼 누르면 사라짐)
		});
		
		// make the event draggable using jQuery UI
		$(this).draggable({
		  zIndex: 999,
		  revert: true,      // will cause the event to go back to its
		  revertDuration: 0,  //  되돌려지는 시간
		  scroll: true // true: 드래그 요소 창 밖으로 끌면 자동으로 스크롤생기면서 아래로내릴 수 있음
		});
	});
	*/

	
	$('#calendar').fullCalendar({
		schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source'
		, height: "auto"
		, defaultView: 'titetable' // 뷰 지정 (아래에 설정값 작성)
		, defaultDate: '2018-04-07'
		, minTime: '06:00:00' // 시작 시점 시간 설정 
		, timeFormat: 'H:mm' // 이벤트에 표시되는 시간 포멧
		, slotLabelFormat:"HH:mm" // 왼쪽에 수직으로 표현되는 시간 포멧
		, locale: 'ko' // 언어 설정
		, header: false // 헤더에 아무것도 넣지 않기
		, editable: true 
		, droppable: true // this allows things to be dropped onto the calendar
		, selectable: true
		, eventLimit: true // allow "more" link when too many events
		, views: {
			titetable: {
				type: 'agenda',
				columnHeaderFormat: 'dddd M/D', // 헤더에 표시되는 날짜 포멧
				visibleRange: function(currentDate) {
				 // 보여지는 날짜 범위 설정
				  return {
				    start: currentDate.clone().subtract(0, 'days'),
				    end: currentDate.clone().add(3, 'days')
				  }
				}
			   },
		}
		//// uncomment this line to hide the all-day slot
		, allDaySlot: false
		, events: [
		  { id: '2', start: '2018-04-07T10:00:00', end: '2018-04-07T14:00:00', title: 'event 2' ,address: 'Seoul 1'},
		  { id: '3', start: '2018-04-09T14:00:00', end: '2018-04-09T17:30:00', title: 'event 3' ,address: 'Seoul 2'},
		  { id: '4', start: '2018-04-07T07:30:00', end: '2018-04-07T09:30:00', title: 'event 4' ,address: 'Seoul 3'},
		  { id: '5', start: '2018-04-08T10:00:00', end: '2018-04-08T15:00:00', title: 'event 5' ,address: 'Seoul 4'}
		]
		// 이벤트 허용 범위
		//, eventConstraint: {  
		//    start: '00:00',
		//	    end: '24:00'
		//}
		, eventOverlap: false // 이벤트 겹치게 허용하지 않음
		// 날짜 클릭 시 콜백 함수
		, dayClick: function(date, jsEvent, view, resource) {
			console.log(
			  'dayClick',
			  date.format(),
			  resource ? resource.id : '(no resource)'
			);
		}
		// 뷰 렌더링 될 때 콜백함수
		, viewRender: function (view, element)
		{
			// 헤더의 날짜 표시 css 수정
			var head = $(".fc-row.fc-widget-header").find("tr");
			head.css("height","50px");
			head.css("font-size","1.3em");
			
			// 왼쪽 시간 표시 css 수정 
			// 날짜 표시 있는 td는 셀 수직 병합 & css 수정
			var tdSpan = $("td.fc-axis.fc-time:has(span)");
			tdSpan.attr("rowspan","2");
			tdSpan.css("vertical-align","top");
			tdSpan.css("color","#888");
			// 날짜 표시 아래에 내용 없는 빈 td는 숨기기
			var tdNotSpan = $("td.fc-axis.fc-time:not(:has(span))");
			tdNotSpan.hide();
			
// 			// 이전 날짜, 다음 날짜 이동 버튼
			var prevBtn = $("<span class='glyphicon glyphicon-chevron-left' id='prevBtn'></span>"); // 이전 날짜 버튼
			var prevBtnPos = $("th:nth-child(2)"); // 이전 날짜 버튼 새로 놓을 위치
			prevBtnPos.prepend(prevBtn); // 첫날 날짜 셀에 버튼추가
			$("#prevBtn").click(function(){ // 클릭 이벤트 추가 (이전 날짜 이동)
				$('#calendar').fullCalendar('prev');
			});

		 	var nextBtn =  $("<span class='glyphicon glyphicon-chevron-right' id='nextBtn'></span>"); // 다음 날짜 버튼
			var nextBtnPos = $("th:nth-child(4)"); // 다음 날짜 버튼 새로 놓을 위치
			nextBtnPos.append(nextBtn);// 마지막 날짜 셀에 버튼추가
			$("#nextBtn").click(function(){// 클릭 이벤트 추가 (다음 날짜 이동)
				$('#calendar').fullCalendar('next');
			});
			
			
			// 표시해주고 있는 첫째날과 마지막 날
			intervalStart = view.intervalStart;
			intervalEnd = view.intervalEnd;
			// console.log(intervalStart.format("YYYY-MM-DD"));
			// console.log(intervalEnd.format("YYYY-MM-DD"));
			
			// 첫날이면 이전 버튼 숨기기
			if(intervalStart.format("YYYY-MM-DD") == '2018-04-07'){
				$("#prevBtn").hide();
			}else{
			 $("#prevBtn").show();
			}
			
		}
		// 이벤트 랜더링 콜백함수
		, eventRender: function(event, element, view) {
			// 이벤트 안의 텍스트 padding
			element.css("padding", "5px 15px");
			
			// 날짜 별로 색상 다르게 해주기
			if(event.start.format("YYYY-MM-DD") == '2018-04-07'){
				element.css("background-color", "green");
			}else if(event.start.format("YYYY-MM-DD") == '2018-04-08'){
				element.css("background-color", "orange");
			}
			
		}
		// 이벤트에 마우스 오버 시 콜백 함수
		, eventMouseover: function( event, jsEvent, view ){
			// 선택한 일정에 자식태그로 x 아이콘 추가
			$(jsEvent.currentTarget).prepend("<span style='float: right; z-index: 3;' class=\"glyphicon glyphicon-remove\"></span>");
		}
		, eventMouseout: function( event, jsEvent, view ){
			// 마우스 때면 x표시 사라지도록
			$(".fc-time-grid-event span.glyphicon.glyphicon-remove").remove();
		}
		// 이벤트 클릭 시 콜백함수
		, eventClick: function(calEvent, jsEvent, view) {
			// 삭제 버튼 클릭 처리
			$(document).on("click", ".glyphicon-remove", function(){
				// '_id'값으로 판별하여 삭제
				$('#calendar').fullCalendar('removeEvents', calEvent._id);
			});
		}
		
	}); // end $().fullCalendar()
	
	$('#calendar').fullCalendar('option', 'locale', 'ko');
 	$('#calendar').fullCalendar('option', 'eventConstraint',{start:"00:00", end:"24:00"}) // 이벤트 허용 범위 동적으로 설정

});

</script>
<script type="text/javascript"> 
// 저장하기
function store(){
	// 캘린더에 있는 모든 이벤트 정보 가져오기
	var events = $("#calendar").fullCalendar('clientEvents');
	console.log(events);
	
	var param;
	events.forEach(function(event){
		var timetable = {
				title: event.title
				, address: event.address
				, start: event.start._i
				, end: event.end._id
		}
		param += "&events=" + JSON.stringify(timetable);
	})
	location.href = "/timetable/recv?" + encodeURI(param.substring(1));
}
</script>
<script>
function display(place){
	console.log(place);
	
	console.log(place.photos[0].getUrl());
	
	// 검색결과 - 장소명 표시
	$('#resultLayout').html("title: " + place.name);
	
	// data 설정
	$('#resultLayout').data('event', {
		title: place.name
		, stick: true  // 드롭한 이벤트 고정 (false: 다음 등의 버튼 누르면 사라짐)
	})
	
	// 드래그 가능하게 설정
	$('#resultLayout').draggable({
	  zIndex: 999
	  , revert: true     // will cause the event to go back to its
	  , revertDuration: 0 //  되돌려지는 시간
	  , scroll: true // true: 드래그 요소 창 밖으로 끌면 자동으로 스크롤생기면서 아래로내릴 수 있음
	  , helper: "clone" // 드래그할 때  요소 복사 이펙트
	});
	
}

function initAutocomplete() {

	// search box ui세팅
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	
	// Listen for the event fired when the user selects a prediction and retrieve
	// more details for that place.
	// 유저가 장소를 선택할 때 발생하는 이벤트에 대한 리스너
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();
		
		if (places.length == 0) {
		  return;
		}
		
		var layout = document.getElementById('resultLayout'); 
		
		places.forEach(function(place) {
		 display(place);
		});
	});
}
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&callback=initAutocomplete"
        async defer></script>
</head>
<body>
  <div id='wrap'>

<!-- 	구글 검색 입력창 -->
	<input id="pac-input" class="controls" type="text" placeholder="Search Box">
	<div id="resultLayout" style="display:inline">검색결과</div>
	
<!-- 	테스트 드롭 다운 용 이벤트 리스트들 -->
<!--     <div id='external-events'> -->
<!--       <h4>Draggable Events</h4> -->
<!--       <div class='fc-event'>My Event 1</div> -->
<!--       <div class='fc-event'>My Event 2</div> -->
<!--       <div class='fc-event'>My Event 3</div> -->
<!--       <div class='fc-event'>My Event 4</div> -->
<!--       <div class='fc-event'>My Event 5</div> -->
<!--     </div> -->

    <div id='calendar'></div>
    
	<button id="btnStore" onclick="store();" >저장</button>

    <div style='clear:both'></div>

  </div>
</body>
</html>