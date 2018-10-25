/**
 * 
 */
// Google map 객체
var map;

// 구글 맵 초기화 콜백함수
function initMap() {
	// 구글 맵 초기화
	map = new google.maps.Map(document.getElementById('map'), {
		zoom: 8, 
		center: {lat: 37.4601, lng: 126.4406},
		zoomControl: false,
		mapTypeControl: false,
		scaleControl: false,
		streetViewControl: false,
		rotateControl: false,
		fullscreenControl: false
	});
	
	// searchBox 설정 초기화
	initSearchBox();
	
	// 서버로부터 받은 타임테이블 뿌려주기
	var timetables = getTimetablesFromServer();
	viewMap('all', timetables);
}

//searchBox 설정 초기화
function initSearchBox(){
	// search box 객체 생성
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	
	// 사용자가 검색한 내용 담아놓는 변수
	var input_search;
	
	// 검색창에 입력 시 발생하는 콜백함수
	$("#pac-input").on("change", function(){
		input_search = $("#pac-input").val(); 
	});
	
	// 유저가 장소를 선택할 때 발생하는 이벤트에 대한 리스너
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();

		if (places.length == 0) {
			return;
		}
		
		displaySearch(places, input_search);
	});
}

//Marker 리스트
var markerList = [];
// 경로
var route;

// timetable : 선택한 타임테이블
// timetables : 현재 띄워져있는 모든 타임테이블
function viewMap(timetable, timetables){
	// 기존에 있던 마커들 삭제
	removeMarkerAll();
	
	var locations = [];
	var bounds  = new google.maps.LatLngBounds();
	var minZoomLv = 16;
	var diffDay = 0;
	var isExistSameDayTtb = false;
    var labelIdx = 1;
    
	if(timetable!='all'){
		var ttbStartDate = moment(timetable.start).format('YYYY-MM-DD');
	}
	
	// 선택한 일정과 같은 날에 있는 일정의 위도, 경도 정 보 저장
	timetables.forEach(function(ttb){
		// 모든 타임테이블의 경로를 보여주는게 아닌 경우만 계산
		if(timetable != 'all'){ 
			diffDay = getDiffDay(moment(ttb.start), ttbStartDate);
		}
		if(diffDay == 0){ 
			isExistSameDayTtb = true;
			var loc = new google.maps.LatLng(ttb.lat, ttb.lng);
			locations.push(loc);
		}
	});

	// 같은 날에 타임테이블이 하나도 없는경우(= 어떤 날에 하나 남은 타임테이블을 삭제한 경우) 전체 일정 루트로 그려주기
	if(!isExistSameDayTtb && timetable !='all'){
		viewMap('all', timetables);
		return;
	}
	
	// 마커 리스트 생성 & 확대 구역 설정
	locations.forEach(function(loc){
		// 아이콘 정의
		var icon = {
				url: '/resources/mapMarker/' + (labelIdx++) +'cb.png', // url
				scaledSize: new google.maps.Size(30, 32), // scaled size
				origin: new google.maps.Point(0, 0), // origin
				anchor: new google.maps.Point(8, 15) // anchor
		};
		
		var marker = new google.maps.Marker({
			position: loc
			, map: map
			, icon: icon,
			});
		markerList.push(marker);
		
		// bounds 에 위치 정보 추가
		bounds.extend(loc);
	});
	
	// bounds에 추가한 위치가 포함되도록 이동
	map.panToBounds(bounds);
	// bounds에 추가한 위치를 포함하도록 뷰포트 설정
	map.fitBounds(bounds);
	
	// Zoom Level이 지정한 값보다 작아지면 지정 값으로 재설정
	if(map.getZoom() < minZoomLv){
		map.setZoom(minZoomLv);
	}
	
	// 경로 그리기
	route = new google.maps.Polyline({
		path: locations,
		strokeColor: '#0078FF',
		strokeOpacity: 1.0,
		strokeWeight: 2
	});
	
	route.setMap(map);
}

// 기존 마커 모두 삭제
function removeMarkerAll(){
	for (var i = 0; i < markerList.length; i++) {
		markerList[i].setMap(null);
	}
	if(route!=null){
		route.setMap(null);
	}
}

//검색한 place_id 담아두는 리스트
var searched_ids = [];
// 검색 결과를 담은 요소의 id설정 시 사용
var searchId_idx;
var sessionToken;

function displaySearch(places, input_search){
	
	// place_id리스트 초기화
	searched_ids = [];
	// idx 초기화
	searchId_idx = 0;
	// 검색결과 화면 초기화
	$("#results").empty();
	
	// 세션 생성
	sessionToken = new google.maps.places.AutocompleteSessionToken();
	
	var service = new google.maps.places.AutocompleteService();
	places.forEach(function(place) { 
		// 선택된 장소 이름으로 쿼리 자동완성
		service.getPlacePredictions({ input: place.name, sessionToken: sessionToken}, autoSearchQuery);
	});
  
	// 사용자가 직접 검색한 이름으로 쿼리 자동완성
	service.getPlacePredictions({ input: input_search, sessionToken: sessionToken}, autoSearchQuery);
	  
	  
}

function autoSearchQuery(predictions, status) {
	// 응답 상태가 ok가 아니면 alert 띄워주고 종료
	if (status != google.maps.places.PlacesServiceStatus.OK) {
			alert(status);
			return;
	}

	// 받아온 결과값 반복문 작업
	predictions.forEach(function(prediction) {
		 
		// 기존에 띄워준 결과와 중복되지 않으면 결과 보여줌	
		if(searched_ids.indexOf(prediction.place_id) == -1){
			
		var place_id = prediction.place_id;
		searched_ids[searchId_idx++] = place_id;
		  
		// 장소 details 정보 가져오기
		// 디테일 정보 갖고오기 위한 구글서비스 객체
		var detailService = new google.maps.places.PlacesService(map);
		
		var reqPlace = {
			placeId: place_id
//			, sessionToken: sessionToken
		};
		
		detailService.getDetails(reqPlace, viewDetails);
		
		}
	});
}

//새로운 일정 id값 지정을 위한 변수
var id_idx = -1;

function viewDetails(placeRes, status, prediction){
//	console.log(placeRes);
	if (status === google.maps.places.PlacesServiceStatus.OK){
		// 결과 띄워주기 위한 태그 생성
		var li = $("<li>"); // 태그 생성
		// id지정
		li.attr("id", "search_"+(searchId_idx++)); 
		// 띄워줄 텍스트 지정
		li.text(placeRes.name); 
		// 주소 레벨에서 우선 적당한거 선택..
		var address = placeRes.address_components;
		var address_name;
		if(address.length < 3){
			address_name = address[address.length-1].long_name;
		}else{
			address_name = address[address.length-3].long_name
		}
		li.text(li.text() + " /" + address_name);
		// 장소 타입 지정. 우선 전부 다
		li.text(li.text() + " /" +  placeRes.types);
		// 드래그 가능하게 설정
		li.draggable({
			  zIndex: 999,
			  revert: true,		// will cause the event to go back to its
			  revertDuration: 0,  //  되돌려지는 시간
			  scroll: true // true: 드래그 요소 창 밖으로 끌면 자동으로 스크롤생기면서 아래로내릴 수 있음
		});
		// 사진 url
		var photo_url;
		if(placeRes.hasOwnProperty("photos")){
			photo_url = placeRes.photos[0].getUrl();
		}
		var img = $("<img width='150' height='90'>");
		img.attr("src", photo_url);
		li.append(img);
		
		// json data 설정
		li.data('event', {
			id: id_idx-- // 새로 추가한 요소는 id=-1
			, title: placeRes.name
			, address: placeRes.formatted_address
			, lat: placeRes.geometry.location.lat()
			, lng: placeRes.geometry.location.lng()
			, photo_url: photo_url
			, place_id: placeRes.place_id
			, stick: true // 드롭한 이벤트 고정 (false: 다음 등의 버튼 누르면 사라짐)
		});
		
		// 결과 공간에 띄워줌
		$("#results").append(li);
		
	}
}