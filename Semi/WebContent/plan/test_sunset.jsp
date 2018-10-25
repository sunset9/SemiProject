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
//구글 map api 호출 콜백함수
function initMap() {
	var map = new google.maps.Map(document.getElementById('map'), {
     zoom: 4,
     //역삼역 위도, 경도
     center: new google.maps.LatLng(37.4989567,127.03283520000002),
     mapTypeId: 'terrain'
   });

   // search box 관련 변수
   var input = document.getElementById('pac-input');
   var searchBox = new google.maps.places.SearchBox(input);
   
   
   //map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });

        var markers = [];
   
        
   
   // 사용자가 검색한 내용 담아놓는 변수
   var input_search;
   
   // 검색창에 입력 시 발생하는 콜백함수
   $("#pac-input").on("input", function(){
      input_search =$("#pac-input").val(); 
   });
   
   // 유저가 장소를 선택할 때 발생하는 이벤트에 대한 리스너
   searchBox.addListener('places_changed', function() {
	   var places = searchBox.getPlaces();
	   
	   places.forEach(function(place) {
		place = searchBox.getPlaces();
	   });
	   
		console.log(places);
       if (places.length == 0) {
         return;
       }

       // Clear out the old markers.
       markers.forEach(function(marker) {
         marker.setMap(null);
       });
       markers = [];

       // For each place, get the icon, name and location.
       var bounds = new google.maps.LatLngBounds();
       places.forEach(function(place) {
         if (!place.geometry) {
           console.log("Returned place contains no geometry");
           return;
         }
         var icon = {
           url: place.icon,
           size: new google.maps.Size(71, 71),
           origin: new google.maps.Point(0, 0),
           anchor: new google.maps.Point(17, 34),
           scaledSize: new google.maps.Size(25, 25)
         };

         // Create a marker for each place.
         markers.push(new google.maps.Marker({
           map: map,
           icon: icon,
           title: place.name,
           position: place.geometry.location
         }));

         if (place.geometry.viewport) {
           // Only geocodes have viewport.
           bounds.union(place.geometry.viewport);
         } else {
           bounds.extend(place.geometry.location);
         }
       });
       map.fitBounds(bounds);

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
	   //검색결과 새로고침
	   $("#results").empty();
      // 응답 상태가 ok가 아니면 alert 띄워주고 종료
        if (status != google.maps.places.PlacesServiceStatus.OK) {
          alert(status);
          return;
        }
      console.log("콜백함수 호출 횟수:"+ i++);
      // 받아온 결과값 반복문 작업
      console.log(predictions.description);
        predictions.forEach(function(prediction) {
           
          // 기존에 띄워준 결과와 중복되지 않으면 결과 보여줌   
          if(displayList.indexOf(prediction.id) == -1){
             // 결과 띄워주기 위한 태그 생성
             var li = $("<li onclick=\"test();\">").text(prediction.description);
           $("#results").append(li);
           
          
           displayList.push(prediction.id);
           
           /* var infowindow = new google.maps.InfoWindow();
           var service = new google.maps.places.PlacesService(map);
           
           service.getDetails({
        	   placeId: prediction.id
        	   }, function(place, status) {
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
                 console.log(place.name);
                 console.log(place.place_id);
                 console.log(place.formatted_address);
               }
             }); */
          }
        });
      
       
       
      $("#results").append($("<hr>"));
      }; // displaySuggestions function end
      
    //위도 경도 가져오는 친구
      /*  places.forEach(function(places) { */
		lat = places[0].geometry.location.lat();
		lon = places[0].geometry.location.lng();
 /* }); */
 
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
<!-- 구글맵 출력 script -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?
key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q
&libraries=places&callback=initMap"
    async defer></script>
    
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
</html>




