var initialLocation;
var browserSupportFlag = new Boolean();
var liivi = new google.maps.LatLng(58.37824850000001, 26.71467329999996);
var map;

function initialize() {
	var map_canvas = document.getElementById('main-content');
	var mapOptions = {
		zoom : 18,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	map = new google.maps.Map(map_canvas, mapOptions);
	if (navigator.geolocation) {
		browserSupportFlag = true;
		navigator.geolocation.getCurrentPosition(function(position) {
			initialLocation = new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude);
			map.setCenter(initialLocation);
		}, function() {
			handleNoGeolocation(browserSupportFlag);
		});
	} else {
		browserSupportFlag = false;
		handleNoGeolocation(browserSupportFlag);
	}

	function handleNoGeolocation(errorFlag) {
		if (errorFlag == true) {
			alert("Geolocation service failed");
			initialLocation = liivi;
		} else {
			alert("Browser does not support geolocation.");
			initialLocation = liivi;
		}
		map.setCenter(initialLocation);
	}
}

function toMap(responseText){
//	kuidagi peaks clearima mapi ilmselt
	var array = responseText.split("|");
	for(var i=0; i<array.length; i++){
		var point = JSON.parse(array[i]);
		//parsing coords
		var location = point.location;
		var parts = location.split(",");
		var lat = parseFloat(parts[0].trim());
		var lng = parseFloat(parts[1].trim());
		markerToMap(lat, lng);
	}	
}

function markerToMap(lat, lng){
	var point = new google.maps.LatLng(lat, lng);
	marker = new google.maps.Marker({
		position: point,
		map: map
	});
}

google.maps.event.addDomListener(window, 'load', initialize);