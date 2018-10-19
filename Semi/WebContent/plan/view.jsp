<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/headerWithMenu.jsp" />

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>

<!-- 공개유무 슬라이드 버튼 -->
<style type="text/css">
	/* The switch - the box around the slider */
	.switch {
	  position: relative;
	  display: inline-block;
	  width: 60px;
	  height: 34px;
	  vertical-align:middle;
	}
	 
	/* Hide default HTML checkbox */
	.switch input {display:none;}
	 
	/* The slider */
	.slider {
	  position: absolute;
	  cursor: pointer;
	  top: 0;
	  left: 0;
	  right: 0;
	  bottom: 0;
	  background-color: #ccc;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	 
	.slider:before {
	  position: absolute;
	  content: "";
	  height: 26px;
	  width: 26px;
	  left: 4px;
	  bottom: 4px;
	  background-color: white;
	  -webkit-transition: .4s;
	  transition: .4s;
	}
	 
	input:checked + .slider {
	  background-color: #2196F3;
	}
	 
	input:focus + .slider {
	  box-shadow: 0 0 1px #2196F3;
	}
	 
	input:checked + .slider:before {
	  -webkit-transform: translateX(26px);
	  -ms-transform: translateX(26px);
	  transform: translateX(26px);
	}
	 
	/* Rounded sliders */
	.slider.round {
	  border-radius: 34px;
	}
	 
	.slider.round:before {
	  border-radius: 50%;
	}
	 
	p {
	  margin:0px;
	  display:inline-block;
	  font-size:15px;
	  font-weight:bold;
	}
</style>

<!-- 구글맵 -->
<style>
     /* Always set the map height explicitly to define the size of the div
      * element that contains the map. */
	#map {
		height: 80%;
	}
</style>
<style>


</style>
<script>
// This sample uses the Place Autocomplete widget to allow the user to search
// for and select a place. The sample then displays an info window containing
// the place ID and other information about the place that the user has
// selected.

// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

function initMap() {

	  var map = new google.maps.Map(document.getElementById('map'), {
	    center: {lat: -33.8688, lng: 151.2195},
	    zoom: 13
	  });

  var input = document.getElementById('pac-input');
  var autocomplete = new google.maps.places.Autocomplete(input);
  
  autocomplete.bindTo('bounds', map);

  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  var infowindow = new google.maps.InfoWindow();
  var infowindowContent = document.getElementById('infowindow-content');
  infowindow.setContent(infowindowContent);
  var marker = new google.maps.Marker({
    map: map
  });
  marker.addListener('click', function() {
    infowindow.open(map, marker);
  });
  
 	autocomplete.addListener('place_changed', function() {
      infowindow.close();
      var place = autocomplete.getPlace();
      if (!place.geometry) {
        return;
      }

      if (place.geometry.viewport) {
        map.fitBounds(place.geometry.viewport);
      } else {
        map.setCenter(place.geometry.location);
        map.setZoom(17);
      }

      // Set the position of the marker using the place ID and location.
      marker.setPlace({
        placeId: place.place_id,
        location: place.geometry.location
      });
      marker.setVisible(true);

      infowindowContent.children['place-name'].textContent = place.name;
      infowindowContent.children['place-id'].textContent = place.place_id;
      infowindowContent.children['place-address'].textContent =
          place.formatted_address;
      infowindow.open(map, marker);
    });
}

</script>

<script src="https://maps.googleapis.com/maps/api/js?
key=AIzaSyAO-YMjD9aGxBW1nEzgSFdzf7Uj8E4Lm9Q
&libraries=places&callback=initMap"
 async defer></script>

</head>

<body>

<!-- 플래너 대문 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">

	<!-- 플래너 대문 정보(공개유무, 수정버튼, 일정제목 등 UI) -->
	<div id="header" style="background-color:#EEEEEE; margin:3px;">
		<script>
		var check = $("input[type='checkbox']");
		check.click(function(){
		  $("p").toggle();
		});
		</script>
		
		<label class="switch">
			<input type="checkbox">
			<span class="slider round"></span>
		</label>
		<p>비공개</p>
		<p style="display:none;">공개</p>
		
		<input type="button" value="수정" style="position: absolute; right: 15px;">
		
		<h1 style="margin-bottom:0;" align="center">로고와 함께하는 역삼역 투어!!</h1>
			<h4 align="center">대한민국 서울</h4>
			<h4 align="center">2018.09.10 ~ 2018.09.15</h4>
			<h4 align="center">여행 전</h4>
			<br>
	 </div>
</div>
<!-- 플래너 입력 정보 DIV -->
<div id="container" style="width:100%; border-radius:10px;">

	<!-- 좌측 정보목록 (게시자 정보, 가계부, 검색 등 )-->
	<div id="container" style="width:200px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="menu" style="background-color:#EEEEEE;height:200px;float:bottom;width:100%;border-radius:10px;">
			사진<br>
			<b>사용자</b>님 <br>
			포스팅 : 10개 <br>
			등급 : 여행작가	 乃<br>
			총 여행 거리 : 80km
		</div>
		
	 	<!-- 가계부 DIV -->
		<div id="menu" style="background-color:#CCCCCC;height:200px;float:bottom;width:100%;border-radius:10px;">
			<b>가계부</b><br>
			교통 : <input type="text"><br>
			식비 : <input type="text"><br>
			문화 : <input type="text"><br>
			기타 : <input type="text"><br>
		</div>
		
		<!-- 검색 INPUT DIV -->
		<div id="menu" style="background-color:#AAAAAA;height:50px;float:bottom;width:100%;border-radius:10px;">
		검색 : <input type="text">
		</div>
	</div>
	
	<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
	<div id="container" style="width:1000px; border-radius:10px;float:left;">
		<!-- 일정 / 스토리 탭 DIV -->
		<div id="content" style="background-color:#999999;height:150px;float:bottom;width:100%;border-radius:10px;">
			<input type="button" value="일정" >
			<input type="button" value="스토리" >
		</div>
		
		<!-- 구글맵 DIV -->
		<div id="content" style="background-color:#DDDDDD;height:500px;float:bottom;width:100%;border-radius:10px;">
	
			<div id="map"></div>
			<input id="pac-input" class="controls" type="text"
	        placeholder="Enter a location">
		    
		    <div id="infowindow-content">
		      <span id="place-name"  class="title"></span><br>
		      Place ID <span id="place-id"></span><br>
		      <span id="place-address"></span>
		    </div>
	 	</div>
	 	
	 	<!-- 타임테이블 -->
		<div id="content" style="background-color:#BBBBBB;height:200px;float:bottom;width:100%;border-radius:10px;">타임테이블 !!!</div>
	</div>
</div>

</body>
</html>