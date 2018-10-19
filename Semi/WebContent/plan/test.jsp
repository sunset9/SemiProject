<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Retrieving Autocomplete Predictions</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<style>
#map {
   height: 400px;  /* The height is 400 pixels */
   width: 100%;  /* The width is the width of the web page */
}
#pac-input{
   width : 500px;
}
</style>

<script>
// 구글 map api 호출 콜백함수


function initAutocomplete() {

   // search box 관련 변수
   var input = document.getElementById('pac-input');
   var searchBox = new google.maps.places.SearchBox(input);
   
   // 사용자가 검색한 내용 담아놓는 변수
   var input_search;
   
   // 검색창에 입력 시 발생하는 콜백함수
   $("#pac-input").on("change", function(){
      input_search =$("#pac-input").val(); 
   });
   
   // 유저가 장소를 선택할 때 발생하는 이벤트에 대한 리스너
   searchBox.addListener('places_changed', function() {
      var places = searchBox.getPlaces();
      
//       if (places.length == 0) {
//         return;
//       }
      display(places, input_search);
   });
 }

var lat = [];
var lon = [];

function display(places, input_search){
//    console.log(place.photos[0].getUrl());
   
   var displayList = [];
   var i = 1;
   var j = 0;
   // 쿼리자동완성 콜백 함수 (최대 결과 5개 반환)
   // 연관 검색어도 검색해서 표시
   var displaySuggestions = function(predictions, status) {
      // 응답 상태가 ok가 아니면 alert 띄워주고 종료
        if (status != google.maps.places.PlacesServiceStatus.OK) {
          alert(status);
          return;
        }
      console.log("콜백함수 호출 횟수:"+ i++);
      // 받아온 결과값 반복문 작업
        predictions.forEach(function(prediction) {
           
          // 기존에 띄워준 결과와 중복되지 않으면 결과 보여줌   
          if(displayList.indexOf(prediction.id) == -1){
             // 결과 띄워주기 위한 태그 생성
             var li = $("<li onclick=\"test();\">").text(prediction.description);
           $("#results").append(li);
             
           displayList.push(prediction.id);
           
           /* console.log(j);
             // list 에 검색 결과 넣기  
             lat = places[j].geometry.viewport.b.b;
             lon = places[j].geometry.viewport.f.b;
             j++; */
             
          }
        });
      
        places.forEach(function(places) {
        	
			lat = places.geometry.viewport.b.b;
			lon = places.geometry.viewport.f.b;
        });
      $("#results").append($("<hr>"));
      }; // displaySuggestions function end
      
      
      
     // Create a new session token.
     var sessionToken = new google.maps.places.AutocompleteSessionToken();
      
     var service = new google.maps.places.AutocompleteService();
     places.forEach(function(place) { 
        // 선택된 장소 이름으로 쿼리 자동완성
        service.getPlacePredictions({ input: place.name, sessionToken: sessionToken}, displaySuggestions);
     });
     
     // 사용자가 직접 검색한 이름으로 쿼리 자동완성
     service.getPlacePredictions({ input: input_search, sessionToken: sessionToken}, displaySuggestions);
     
     /* 상세 정보 (위도, 경도 등) 불러오기 테스트 중
    console.log("displayList:");
     console.log(displayList);
     var detailService = new google.maps.places.PlacesService(map);
     displayList.forEach(function(searchedPlace){
    	 
        console.log("디테일 정보 불러오기:");
        console.log(searchedPlace);
        console.log("id: "+searchedPlace.place_id);
        
        detailService.getDetails({
             placeId: searchedPlace.place_id
           }, function(place, status) {
               console.log(place);
             if (status === google.maps.places.PlacesServiceStatus.OK) {
               var marker = new google.maps.Marker({
                 map: map,
                 position: place.geometry.location
               });
               google.maps.event.addListener(marker, 'click', function() {
                 infowindow.setContent('<div><strong>' + place.name + '</strong><br>' +
                   'Place ID: ' + place.place_id + '<br>' +
                   place.formatted_address + '</div>');
                 infowindow.open(map, this);
               });
             }
           });
     });
     */
}

function test(places) {
	console.log(lat + ", " + lon);
}
</script>
  </head>
  
<body>
  <div id="map"></div>
  <input id="pac-input" class="controls" type="text" placeholder="Search Box">
    <div id="right-panel">
      <p>Query suggestions:</p>
      <ul >
      <li id="results" ></li>
      </ul>
    </div>
  </body>
  
<script>
var map;
function initMap() {
   map = new google.maps.Map(document.getElementById('map'), {
     zoom: 4,
     center: new google.maps.LatLng(32.8,-87.3),
     mapTypeId: 'terrain'
   });
   
   initAutocomplete();
}
</script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?
key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q
&libraries=places&callback=initMap"
    async defer></script>
</html>