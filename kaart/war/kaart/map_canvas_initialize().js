var initialLocation;
var browserSupportFlag = new Boolean();
var liivi = new google.maps.LatLng(58.37824850000001, 26.71467329999996);

function initialize() {
	var map_canvas = document.getElementById('map_canvas');
	var mapOptions = {
		zoom : 18,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(map_canvas, mapOptions);
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

google.maps.event.addDomListener(window, 'load', initialize);