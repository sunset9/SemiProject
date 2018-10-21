/**
 * 
 */
function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 8, 
		center: {lat: 37.525, lng: 126.970}
	});
}
// timetable : 선택한 타임테이블
// timetables : 현재 띄워져있는 모든 타임테이블
function viewMap(timetable, timetables){
	var map = initMap();
	
	var positionList = [];
	var markerList = [];
	var ttbStartDate = timetable.start.format('YYYY-MM-DD');
	
	// 선택한 일정과 같은 날에 있는 일정의 위도, 경도 정보 저장
	timetables.forEach(function(ttb){
		var diffDay = getDiffDay(moment(ttb.start), ttbStartDate);
		if(diffDay == 0){ 
			positionList.push({lat: ttb.lat, lng:ttb.lng});
		}
	});
	
	// 마커 리스트 생성
	positionList.forEach(function(pos){
		var marker = new google.maps.Marker({position: pos, map: map});
	});
	
	
}