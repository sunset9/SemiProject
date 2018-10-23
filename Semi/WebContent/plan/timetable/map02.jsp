<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <style>
       /* Set the size of the div element that contains the map */
      #map {
        height: 400px;  /* The height is 400 pixels */
        width: 100%;  /* The width is the width of the web page */
       }
    </style>
    
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
  </head>
  
  <body>
    <h3>My Google Maps Demo</h3>
    <!--The div element for the map -->
    <div id="map"></div>
    
    
<script>
// Initialize and add the map
$(document).ready(function() {
 $("#1day").click(function(){
	setLoc(31.344,111.036, -10.344,125.036, 37.344,127.036); 
 });
 $("#2day").click(function(){
		setLoc(31.344,111.036, 31.344,125.036, 37.344,127.036); 
	 });
 $("#3day").click(function(){
		setLoc(8.344,36.036, -8.344,24.036, 12,1.036); 
	 });
});

var latlng;
var latlng2;
var latlng3;

function setLoc(lat, lng, lat2, lng2, lat3, lng3){
	latlng = {lat: lat, lng: lng};
	latlng2 = {lat: lat2, lng: lng2};
	latlng3 = {lat: lat3, lng: lng3};
	
	viewMap(latlng, latlng2, latlng3);
}

function viewMap(latlng, latlng2, latlng3){
	  // The map, centered at Uluru
	  var map = new google.maps.Map(
	      document.getElementById('map'), {zoom: 4});
	  
	  var bounds  = new google.maps.LatLngBounds();
	  
	  // The marker, positioned at Uluru
	  var marker = new google.maps.Marker({position: latlng, map: map});
	  var marker2 = new google.maps.Marker({position: latlng2, map: map});
	  var marker3 = new google.maps.Marker({position: latlng3, map: map});
	  
	  var loc = new google.maps.LatLng(marker.position.lat(), marker.position.lng());
	  var loc2 = new google.maps.LatLng(marker2.position.lat(), marker2.position.lng());
	  var loc3 = new google.maps.LatLng(marker3.position.lat(), marker3.position.lng());
	  
	  bounds.extend(loc);
	  bounds.extend(loc2);
	  bounds.extend(loc3);
	  
	  map.panToBounds(bounds);
	  map.fitBounds(bounds);     

	  var locs = [];
	  locs.push(loc);
	  locs.push(loc2);
	  locs.push(loc3);
	  
	  // 경로그리기
	  var flightPlanCoordinates = [
		  loc,
		  loc2,
		  loc3
	    ];
	  
	  console.log(flightPlanCoordinates);
	  var flightPath = new google.maps.Polyline({
	      path: locs,
	      geodesic: true,
	      strokeColor: '#FF0000',
	      strokeOpacity: 1.0,
	      strokeWeight: 2
	    });
	  flightPath.setMap(map);
}
</script>

    <script>
function initMap() {
  // The location of Uluru
	latlng = {lat: 1.344, lng: 111.036};
	latlng2 = {lat: -1.344, lng: 125.036};
	latlng3 = {lat: 3.344, lng: 127.036};
  
  	viewMap(latlng, latlng2, latlng3);
}
    </script>
    
  <button id="1day">1일</button>
  <button id="2day">2일</button>
  <button id="3day">3일</button>
  
  <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q&callback=initMap">
   </script>
    
  </body>
</html>