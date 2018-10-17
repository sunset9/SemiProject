<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <title>Retrieving Autocomplete Predictions</title>
  <script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #right-panel {
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }

      #right-panel select, #right-panel input {
        font-size: 15px;
      }

      #right-panel select {
        width: 100%;
      }

      #right-panel i {
        font-size: 12px;
      }
    </style>
    <script>
function display(place, input_search){
	console.log(place);
	
// 	console.log(place.photos[0].getUrl());
	
	// 검색결과 - 장소명 표시
	$('#resultLayout').html("title: " + place.name);
		
	// 연관 검색어도 검색해서 표시
	var displaySuggestions = function(predictions, status) {
        if (status != google.maps.places.PlacesServiceStatus.OK) {
          alert(status);
          return;
        }

        predictions.forEach(function(prediction) {
          var li = document.createElement('li');
          li.appendChild(document.createTextNode(prediction.description));
          document.getElementById('results').appendChild(li);
          console.log(prediction);
        });
        console.log("----------");
      };

     var service = new google.maps.places.AutocompleteService();
     service.getQueryPredictions({ input: place.name}, displaySuggestions);
     
//      console.log(input_search);
//      service.getQueryPredictions({ input: input_search}, displaySuggestions);

	// ajax 테스트
// 	.ajax({
// 		url: "https://maps.googleapis.com/maps/api/place/queryautocomplete/json?"
// 		, method: "GET"
// 		, data: {
// 			key: "AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q"
// 			, language: "ko"
// 			, input: place.name
// 		}
// 		, dataType: "json"
// 		, success: function(){
// 			console.log("success!");
// 		}
// 		, fail: 
// 	})
     
}

function initAutocomplete() {

	// search box ui세팅
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	
	var input_search;
	
	// 검색창에 입력 시 발생하는 콜백함수
	$("#pac-input").on("change", function(){
		input_search =$("#pac-input").val(); 
	});
	
	// 유저가 장소를 선택할 때 발생하는 이벤트에 대한 리스너
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();
		
		if (places.length == 0) {
		  return;
		}
		
		var layout = document.getElementById('resultLayout'); 
		
		places.forEach(function(place) {
		 display(place, input_search);
		});
	});
 }
 </script>
         
  </head>
  
  <body>
  <input id="pac-input" class="controls" type="text" placeholder="Search Box">
	<div id="resultLayout" style="display:inline">검색결과</div>
    <div id="right-panel">
      <p>Query suggestions:</p>
      <ul id="results"></ul>
    </div>
  </body>
  
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&libraries=places&callback=initAutocomplete"
    async defer></script>
</html>