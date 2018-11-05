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
		center: {lat: 37.530183, lng: 127.059928},
//		zoomControl: false,
//		mapTypeControl: false,
//		scaleControl: false,
		streetViewControl: false,
//		rotateControl: false,
//		fullscreenControl: false
	});

	if(isModify){
		// searchBox 설정 초기화
		initSearchBox();
	}
	
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
var markers = [];
// 경로
var route;

// 지도 그려주는 메소드
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
				url: '/resources/img/mapMarker/' + (labelIdx++) +'g.png', // url
				scaledSize: new google.maps.Size(30, 32), // scaled size
				origin: new google.maps.Point(0, 0), // origin
				anchor: new google.maps.Point(8, 15) // anchor
		};
		
		var marker = new google.maps.Marker({
			position: loc
			, map: map
			, icon: icon,
			});
		markers.push(marker);
		
		// bounds 에 위치 정보 추가
		bounds.extend(loc);
	});
	
	// 존재하는 타임테이블이 한 개 이상일 때 뷰 이동, 줌
	if(timetables.length > 0){
		// bounds에 추가한 위치가 포함되도록 이동
		map.panToBounds(bounds);
		// bounds에 추가한 위치를 포함하도록 뷰포트 설정
		map.fitBounds(bounds);
	}
	
	// Bounds 가 변경되었을 때
	// Zoom Level이 지정한 값보다 작아지면 지정 값으로 재설정
	google.maps.event.addListenerOnce(map, 'bounds_changed', function(event) {
		  if (this.getZoom() > minZoomLv) {
		    this.setZoom(minZoomLv);
		  }
		});
	
	// 경로 그리기
	route = new google.maps.Polyline({
		path: locations,
		strokeColor: '#4FB99F',
		strokeOpacity: 1.0,
		strokeWeight: 2
	});
	
	route.setMap(map);

}

// 기존 마커 모두 삭제
function removeMarkerAll(){
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
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

// 검색 결과 보여주기
function displaySearch(places, input_search){
	
	// place_id리스트 초기화
	searched_ids = [];
	// idx 초기화
	searchId_idx = 0;
	// 검색결과 화면 초기화
	$("#searchResultView").empty();
	
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

// 연관(?) 검색어
function autoSearchQuery(predictions, status) {
	// 응답 상태가 ok가 아니면 alert 띄워주고 종료
	if (status != google.maps.places.PlacesServiceStatus.OK) {
//			alert(status);
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

// 상세 정보 가져온 것 띄워주기
function viewDetails(placeRes, status, prediction){
//	console.log(placeRes);
	if (status === google.maps.places.PlacesServiceStatus.OK){
		// 결과 띄워주기 위한 태그 생성
		var resDiv = $("<div class='searchRes'>"); // 태그 생성

		// 0. id지정
		resDiv.attr("id", "search_"+(searchId_idx++)); 
		
		// 1. 장소 사진
		var photo;
		var photo_url;
		var imgDiv = $("<div class='searchResImg'>");
		var img = $("<img class='placeImg'>");
		if(placeRes.hasOwnProperty("photos")){ // 구글 제공 이미지 있는 경우
			if(placeRes.photos.length > 2){
				photo = placeRes.photos[2];
			}else{
				photo = placeRes.photos[0];
			}
			photo_url = photo.getUrl();
			
		}else{ // 제공 이미지 없는 경우
			photo_url = '/resources/img/placeNoPhoto.png';
		}
		// 넓이 높이 조정 (긴걸로 맞춤)
		if(photo != null){
			if(photo.width >= photo.height){
				img.css("width", "auto");
				img.css("height", "100%");
			} else{
				img.css("width", "100%");
				img.css("height", "auto");
			}
		}
		img.attr("src", photo_url);
		imgDiv.append(img);
		resDiv.append(imgDiv);
		
		// 2,3
		var infoDiv = $("<div class='searchResInfo'>");
		
		// 2. 장소 이름
		var infoName = $("<div class='infoName'>");
		infoName.text(placeRes.name);
		infoDiv.append(infoName);
		
		// 3. 장소 지역명 정보
		// 주소 지정
		var address = placeRes.address_components;
		// 나라이름 파싱
		var country_name; 
		address.forEach(function(addr){
			if(addr.types.indexOf("country") >= 0){
				country_name = addr.long_name;
				return;
			}
		});
		// 지역명 파싱
		var locality;
		address.forEach(function(addr){
			if(addr.types.indexOf("locality") >= 0){
				locality = addr.long_name;
				return;
			}
		});
		// 국가 수준 이하의 1 차 행정 수준 파싱
		var area_level_1; 
		if(area_level_1 == null){
			address.forEach(function(addr){
				if(addr.types.indexOf("administrative_area_level_1") >= 0){
					area_level_1 = addr.long_name;
					return;
				}
			});
		}
		var addrText = country_name;
		if(locality != null){ // 지역명 있으면 - 나라이름+지역명
			addrText = country_name + " " + locality;
		}else if(area_level_1 != null){ // 지역명 없고, 1차 행정수준명 있으면 - 나라이름+행정수준명
			addrText = country_name + " " + area_level_1;
		} 
		
		var infoAddr = $("<div class='infoAddr'>");
		infoAddr.text(addrText);
		infoDiv.append(infoAddr);
		
		resDiv.append(infoDiv);
		
		// 드래그 가능하게 설정
		resDiv.draggable({
			zIndex: 999,
			revert: true,		// will cause the event to go back to its
			revertDuration: 0,  //  되돌려지는 시간
			scroll: true // true: 드래그 요소 창 밖으로 끌면 자동으로 스크롤생기면서 아래로내릴 수 있음
		});
		
		// json data 설정
		resDiv.data('event', {
			id: 0
			, plan_idx: plan_idx
			, title: placeRes.name
			, address: placeRes.formatted_address
			, country_name: country_name
			, lat: placeRes.geometry.location.lat()
			, lng: placeRes.geometry.location.lng()
			, photo_url: photo_url
			, place_id: placeRes.place_id
			, stick: true // 드롭한 이벤트 고정 (false: 다음 등의 버튼 누르면 사라짐)
		});
		
		// 결과 공간에 띄워줌
		$("#searchResultView").append(resDiv);
		
	}
}