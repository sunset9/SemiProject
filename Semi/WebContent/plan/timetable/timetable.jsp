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
		, height: "auto",
		 	defaultView: 'titetable',
			defaultDate: '2018-04-07',
			   minTime: '06:00:00', 
			locale: 'ko',
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar
			 selectable: true,
			eventLimit: true, // allow "more" link when too many events
			header: {
			 	left: 'prev',
			  right: 'next'
			}
		, views: {
			titetable: {
				type: 'agenda',
				columnHeaderFormat: 'dddd M/D',
				visibleRange: function(currentDate) {
				 // 표시해주는 범위 설정
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
		  // { id: '1', start: '2018-04-06', end: '2018-04-08', title: 'event 1' },
		  { id: '2', start: '2018-04-07T10:00:00', end: '2018-04-07T14:00:00', title: 'event 2' ,description: 'This is a cool event'},
		  { id: '3', start: '2018-04-09T14:00:00', end: '2018-04-09T17:30:00', title: 'event 3' },
		  { id: '4', start: '2018-04-07T07:30:00', end: '2018-04-07T09:30:00', title: 'event 4' },
		  { id: '5', start: '2018-04-08T10:00:00', end: '2018-04-08T15:00:00', title: 'event 5' }
		]
		, eventRender: function(event, element) {
			element.qtip({
			  content: event.description
			});
		 }
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
		, viewRender: function (view, element)
		{
			// 표시해주고 있는 첫째날과 마지막 날
			intervalStart = view.intervalStart;
			intervalEnd = view.intervalEnd;
			// console.log(intervalStart.format("YYYY-MM-DD"));
			// console.log(intervalEnd.format("YYYY-MM-DD"));
			
			// 첫날이면 이전 버튼 숨기기
			if(intervalStart.format("YYYY-MM-DD") == '2018-04-07'){
				$(".fc-prev-button").hide();
			}else{
			 $(".fc-prev-button").show();
			}
		}
		, eventRender: function(event, element, view) {
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
// 			$(".fc-content",this).prepend("<span style='float: right' class=\"glyphicon glyphicon-remove\"></span>"); // .fc-content 자식에추가
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
}
</script>
<script type="text/javascript">
// 날짜 정보 알려주는 헤더부분 커스텀마이징
$(document).ready(function(){
	var head = $(".fc-row.fc-widget-header").find("tr");
	head.css("height","50px");
	head.css("font-size","1.3em");
	
});
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
  
  .fc-ltr .fc-time-grid .fc-event-container { /* 이벤트 박스 관련 */ 
  	margin: 0 3px 0 3px; 
  	font-size: 1.1em;
  }
  

</style>
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
    
	<button id="btnStore" onclick="store();">저장</button>

    <div style='clear:both'></div>

  </div>
</body>
</html>