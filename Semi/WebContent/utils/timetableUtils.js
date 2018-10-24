function initFullCalendar(planStartDate, planEndDate, timetables){
	$('#calendar').fullCalendar({
		schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source'
		, defaultView: 'titetable' // 뷰 지정 (아래에 설정값 작성)
		, height: 833 // 캘린더 높이
		, defaultDate: planStartDate
		, defaultTimedEventDuration: '02:00:00' // 시간 기간 디폴트 지정 2 hours
		, forceEventDuration: true // end시간이 지정되지 않으면 강제로 할당
		, minTime: '06:00' // 시작 시점 시간 설정 
		, timeFormat: 'H:mm' // 이벤트에 표시되는 시간 포멧
		, slotLabelFormat:"HH:mm" // 왼쪽에 수직으로 표현되는 시간 포멧
		, locale: 'ko' // 언어 설정
		, header: false // 헤더에 아무것도 넣지 않기
		, editable: true 
 		, droppable: true // this allows things to be dropped onto the calendar
		, selectable: false // 빈공간 선택 disable
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
		, allDaySlot: false 
		, events: timetables
		// 이벤트 허용 범위
		, eventConstraint: {  
		   start: '06:00',
		   end: '24:00'
		}
		, eventOverlap: false // 이벤트 겹치게 허용하지 않음
		// 뷰 렌더링 될 때 콜백함수
		, viewRender: function (view, element)
		{
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
			intervalEnd = view.intervalEnd.clone().subtract(1, 'days')
			
			// 첫날이면 이전 버튼 숨기기
			if(intervalStart.format("YYYY-MM-DD") == planStartDate){
				$("#prevBtn").hide();
			}else{
			 $("#prevBtn").show();
			}
			// 마지막 날이면 이전 버튼 숨기기
			if(intervalEnd.format("YYYY-MM-DD") == planEndDate){
				$("#nextBtn").hide();
			}else{
			 $("#nextBtn").show();
			}
			
		}
		// 이벤트 랜더링 콜백함수
		, eventRender: function(event, element, view) {
			// 이벤트 안의 텍스트 padding
			element.css("padding", "5px 15px");
			
			// 날짜 별로 색상 다르게 해주기
			
			if(getDiffDay(event.start, planStartDate) == 0){
				element.css("background-color", "green");
			}else if(getDiffDay(event.start, planStartDate) == 1){
				element.css("background-color", "orange");
			}
		}
		// 이벤트에 마우스 오버 시 콜백 함수
		, eventMouseover: function( event, jsEvent, view ){
			// 선택한 일정에 자식태그로 x 아이콘 추가
			$(jsEvent.currentTarget).prepend("<span style='float: right; z-index: 3;' id ='btnRemove' class=\"glyphicon glyphicon-remove\"></span>");
			
			// 삭제 버튼 클릭 처리
			$("#btnRemove").on("click", function(){
				// '_id'값으로 판별하여 삭제
				$('#calendar').fullCalendar('removeEvents', event._id);
				
				// 지도 뷰 바꿔주기 
				var timetables = getTimetablesFromBrowser();
				viewMap(event, timetables);
			});
		}
		, eventMouseout: function( event, jsEvent, view ){
			// 마우스 때면 x표시 사라지도록
			$("#btnRemove").remove();
		}
		// 이벤트 클릭 시 콜백함수
		, eventClick: function(calEvent, jsEvent, view) {
			console.log(calEvent);
			// 지도 뷰 바꿔주기 - 선택한 타임테이블과 같은 날에 있는 모든 일정들의 좌표로
			var timetables = getTimetablesFromBrowser();
			viewMap(calEvent, timetables);
		}
		// 검색 결과에서 끌어다가 드롭 이벤트 발생시 호출
		, drop: function( date , jsEvent , ui , resourceId ){
			// 지도 뷰 바꿔주기 - 드롭한 날짜와 같은 날에 있는 모든 일정의 좌표로
			var dropTimetable = $(this).data('event');
			dropTimetable.start = date; // 드롭한 일정의 시작시간 json 형태에 저장
			var timetables = getTimetablesFromBrowser();
			viewMap(dropTimetable, timetables);
		}
		// 다른 시간으로 일정 옮길때 호출
		,eventDrop: function( event, delta, revertFunc, jsEvent, ui, view ){
			// 지도 뷰 바꿔주기 
			var timetables = getTimetablesFromBrowser();
			viewMap(event, timetables);
		}
		
	}); // end $().fullCalendar() initMethod
}

// 날짜 차이 구하기
// ttbStartDate : 특정 타임테이블의 시작 시간(몇일차인지 구하고 싶은 날짜시간). fullcalendar API의 시간 포멧(moment객체)
// planStartDate : 일정의 시작 날짜(기준이 되는 날짜) . YYYY-MM-DD 형태의 문자열
function getDiffDay(ttbStartDate, planStartDate ){
	var ttbStartDate = new Date(ttbStartDate.format("YYYY-MM-DD")); // 타임테이블의 시작 날짜
	var planStartDate = new Date(planStartDate); // 전체일정의 시작 날짜
	
	var diffMillSec = Math.abs(ttbStartDate - planStartDate); // 밀리초 구하기
	var diffDay = new Date(diffMillSec).getDate(); // Date 타입으로 변환 후 일자 구하기
	
	diffDay -= 1; // 같은 날: 1반환
	
	return diffDay;
}

// 브라우저에 띄워진 모든 타임테이블 리스트 가져오기
function getTimetablesFromBrowser(){
	var events = $("#calendar").fullCalendar('clientEvents');
	
	var timetables = [];
	
	events.forEach(function(event){ // 모든 리스트 돌면서 timetable json 하나씩 생성
		// timetable json 생성
//		console.log(event);
		var timetable = {
				id: event.id
				, title: event.title
				, address: event.address
				, start: event.start.format("YYYY-MM-DD HH:mm")
				, end: event.end.format("YYYY-MM-DD HH:mm")
				, lat: event.lat
				, lng: event.lng
				, photo_url: event.photo_url
		}
	
		timetables.push(timetable);
	});
	
	return timetables;
}

//서버로부터 받은 timetable, location JSON리스트 필요한 정보만 파싱
function getTimetablesFromServer(){
	var timetables=[];
	
	console.log("---- 서버에서 받은 타임테이블 목록들(ttb, loc) ----")
	console.log(ttbList);
	console.log(locList);
	
	for(var i = 0; i<ttbList.length; i++){
		var timetable = {
			id: ttbList[i].ttb_idx
			, title: locList[i].place_name
			, start: ttbList[i].start_time
			, end: ttbList[i].end_time
			, lat: locList[i].lat
			, lng: locList[i].lng
			, address: locList[i].address
			, photo_url: locList[i].photo_url
			, place_id: locList[i].place_id
		}
		
		timetables.push(timetable);
	}
	
	return timetables;
}