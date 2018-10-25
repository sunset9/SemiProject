<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <title>Simple demo — Overlapping Marker Spiderfier</title>
  <style>
    html, body { height: auto; }
    p { margin: 0.75em 0; }
    #map_element { position: absolute; bottom: 0; left: 0; right: 0; top: 0; }
  </style>
</head>

<body>
  <div id="map_element"></div>
  <script>
    // NOTES
    // this is pretty much the simplest possible demo, and it lacks some features you might want
    // (such as dismissing the open InfoWindow when new markers are spiderfied)
    var mapLibsReady = 0;
    var idx = 1;
    function mapLibReadyHandler() {
    	console.log("ss");
//       if (++ mapLibsReady < 2) return;
      var mapElement = document.getElementById('map_element');
      var map = new google.maps.Map(mapElement, { center: { lat: 52, lng: -1 }, zoom: 7 });
      var iw = new google.maps.InfoWindow();
      var oms = new OverlappingMarkerSpiderfier(map, { 
        markersWontMove: false,   // we promise not to move any markers, allowing optimizations
        markersWontHide: true,   // we promise not to change visibility of any markers, allowing optimizations
        basicFormatEvents: true  // allow the library to skip calculating advanced formatting information
      });
      
      for (var i = 0, len = window.mapData.length; i < len; i ++) {
        (function() {  // make a closure over the marker and marker data
          var markerData = window.mapData[i];  // e.g. { lat: 50.123, lng: 0.123, text: 'XYZ' }
          var marker = new google.maps.Marker({ position: markerData , label: String(idx++)});  // markerData works here as a LatLngLiteral
//           google.maps.event.addListener(marker, 'spider_click', function(e) {  // 'spider_click', not plain 'click'
//             iw.setContent(markerData.text);
//             iw.open(map, marker);
//           });
          oms.addMarker(marker);  // adds the marker to the spiderfier _and_ the map
        })();
      }
//       window.map = map;  // for debugging/exploratory use in console
//       window.oms = oms;  // ditto
    }
    // randomize some overlapping map data -- more normally we'd load some JSON data instead
    var baseJitter = 2.5;
    var clusterJitterMax = 0.1;
    var rnd = Math.random;
    var data = [];
    var clusterSizes = [2, 2, 2, 2,];
    for (var i = 0; i < clusterSizes.length; i++) {
      var baseLon = -1 - baseJitter / 2 + rnd() * baseJitter;
      var baseLat = 52 - baseJitter / 2 + rnd() * baseJitter;
      var clusterJitter = clusterJitterMax * rnd();
      for (var j = 0; j < clusterSizes[i]; j ++) data.push({
        lng:  baseLon - clusterJitter + rnd() * clusterJitter,
        lat:  baseLat - clusterJitter + rnd() * clusterJitter,
        text: Math.round(rnd() * 100) + '% happy'
      });
    }
    window.mapData = data;
    
  </script>

  <script async defer src="https://maps.google.com/maps/api/js?v=3&callback=mapLibReadyHandler&key=AIzaSyBKx-UGkzpvSol3xk5CiuBK7aSVs_1kgm4"></script>
  <script async defer src="https://cdnjs.cloudflare.com/ajax/libs/OverlappingMarkerSpiderfier/1.0.3/oms.min.js"></script>

</body>
</html>