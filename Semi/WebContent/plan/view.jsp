<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../layout/headerWithMenu.jsp" />

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

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
		height: 100%;
	}
</style>
<style>


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
   
   console.log(searchBox);
   
   //map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });

        var markers = [];
   
        
   
   // 사용자가 검색한 내용 담아놓는 변수
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
        predictions.forEach(function(prediction) {
           
          // 기존에 띄워준 결과와 중복되지 않으면 결과 보여줌   
          if(displayList.indexOf(prediction.id) == -1){
             // 결과 띄워주기 위한 태그 생성
             var li = $("<li onclick=\"test();\">").text(prediction.description);
           $("#results").append(li);
           
          
           displayList.push(prediction.id);
           //검색결과 id 출력
           console.log(prediction.id);
           
           
           
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
	<div id="container" style="width:230px; border-radius:10px;float:left;">
	
		<!-- 게시자 정보 DIV -->
		<div id="menu" style="background-color:#EEEEEE;height:100px;float:bottom;width:100%;border-radius:10px;">
			사진<br>
			<b>사용자</b>님 <br>
			포스팅 : 10개 <br>
			등급 : 여행작가	 乃<br>
			총 여행 거리 : 80km
		</div>
		
	 	<!-- 가계부 DIV -->
		<div id="menu" style="background-color:#CCCCCC;height:135px;float:bottom;width:100%;border-radius:10px;">
			<b>가계부</b><br>
			교통 : <input type="text"><br>
			식비 : <input type="text"><br>
			문화 : <input type="text"><br>
			기타 : <input type="text"><br>
		</div>
		
		<!-- 검색 INPUT DIV -->
		<div id="menu" style="float:bottom;width:100%;border-radius:10px;">
		검색 : <input id="pac-input" class="controls" type="text" placeholder="Search Box">
		    <div id="right-panel"
		    style="border-top:3px solid; border-bottom:3px solid; border-left:3px dashed; border-right:3px groove; padding:3px;">
		    <ul>
		     <li id="results" ></li>
		     </ul>
		    </div>
		</div>
	</div>
	
	<!-- 우측 일정 & 타임테이블정보 (지도, 일정탭 & 타임테이블탭 등 )-->
	<div id="container" style="width:1000px; border-radius:10px;float:left;">
		<!-- 일정 / 스토리 탭 DIV -->
		<div id="content" style="background-color:#999999;height:50px;float:bottom;width:100%;border-radius:10px;">
			<input type="button" value="일정" >
			<input type="button" value="스토리" >
		</div>
		
		<!-- 구글맵 DIV -->
		<div id="content" style="background-color:#DDDDDD;height:500px;float:bottom;width:100%;border-radius:10px;">
			<div id="map"></div>
	 	</div>
	 	
	 	<!-- 타임테이블 -->
		<div id="content" style="background-color:#BBBBBB;height:200px;float:bottom;width:100%;border-radius:10px;">타임테이블 !!!</div>
	</div>
</div>
</body>
</html>
