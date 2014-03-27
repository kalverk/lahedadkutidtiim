var initialLocation;
var browserSupportFlag = new Boolean();
var liivi = new google.maps.LatLng(58.37824850000001, 26.71467329999996);
var map;
var markersArray = [];

function initialize() {
	var map_canvas = document.getElementById('main-content');
	var mapOptions = {
		zoom : 14,
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
	// for testing
	enableLegend();
}

function enableLegend() {
	var legend = document.getElementById('legend');
	var div = document.createElement('div');
	div.innerHTML = '<input type="image" id="marker" src="images/iconsB/marker.png" class="marker" onclick="addPlaces(map.getCenter(), true, true, true)"></input>';
	legend.appendChild(div);

	map.controls[google.maps.ControlPosition.RIGHT_TOP].push(document
			.getElementById('legend'));

	google.maps.event.addListener(map, 'rightclick', function(event) {
		addPlaces(event.latLng, true, true, true);
	});
}

function toMap(responseText) {
	if (markersArray) {
		for (var i = 0; i < markersArray.length; i++) {
			markersArray[i].setMap(null);
		}
		markersArray = [];
	}
	var array = responseText.split("|");
	for (var i = 0; i < array.length; i++) {
		var point = JSON.parse(array[i]);
		// parsing coords
		var location = point.location;
		var parts = location.split(",");
		var lat = parseFloat(parts[0].trim());
		var lng = parseFloat(parts[1].trim());
		var id = point.id;
		markerToMap(id, lat, lng);
	}
}

function markerToMap(id, lat, lng) {
	var point = new google.maps.LatLng(lat, lng);
	marker = new google.maps.Marker({
		position : point,
		map : map
	});
	marker.set("id", id);
	markersArray.push(marker);
	google.maps.event.addListener(marker, "click", function(event) {
		$.get('KaartServlet', {
			method : "getPointDescription",
			id : this.get("id")
		}, function(responseText) {
			var array = responseText.split("|");
			var point = JSON.parse(array[0]);
			var id = point.id;
			var name = point.name;
			var location = point.location;
			var description = point.description;
			var link = point.link;
			var content = $('<div class="marker-desc">'
					+ '<div class="marker-desc-win"><span>'
					+ '<h1 id="pointName">'
					+ name
					+ '</h1><p>'
					+ '<div class="marker-desc-edit">'
					+ '<label><span>Description :</span>'
					+ '<label id="pointDescription">'
					+ description
					+ '</label>'
					+ '</label>'
					+ '<label><span>Address :</span>'
					+ '<label id="pointLocation">'
					+ location
					+ '</label>'
					+ '</label>'
					+ ' <label><span>Link :</span>'
					+ ' <label id="pointLink">'
					+ link
					+ '</label>'
					+ '</label>'
					+ '</div>'
					+ ' </p>'
					+ ' </span>'
					+ '</div>'
					+ '</div>');
			// var content = $().load("html/desc.html");
			var infowindow = new google.maps.InfoWindow();
			infowindow.setContent(content[0]);
			infowindow.open(map, marker);
		});
	});
}

function addPlaces(MapPos, InfoOpenDefault, Dragable, Removable) {
	var MapTitle = "Add new location";
	var EditForm = '<p><div class="marker-edit">'
			+ '<form action="" method="POST" name="SaveMarker" id="SaveMarker">'
			+ '<label for="pName"><span>Place Name :</span><input type="text" name="pName" class="save-name" placeholder="Enter title" maxlength="40" /></label>'
			+ '<label for="pDesc"><span>Description :</span><textarea name="pDesc" class="save-desc" placeholder="Enter description" maxlength="250"></textarea></label>'
			+ '<label for="pLink"><span>Website :</span><input type="text" name="pLink" class="save-link" placeholder="Enter website" maxlength="40" /></label>'
			+ '<label for="pType"><span>Categories :</span>'
			+ '<table>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="football">Football</td>'
			+ '<td><input type="checkbox" name="category" value="basketball">Basketball</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="volleyball">Volleyball</td>'
			+ '<td><input type="checkbox" name="category" value="tennis">Tennis</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="bowling">Bowling</td>'
			+ '<td><input type="checkbox" name="category" value="golf">Golf</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="hockey">Hockey</td>'
			+ '<td><input type="checkbox" name="category" value="baseball">Baseball</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="crosscountry">Crosscountry</td>'
			+ '<td><input type="checkbox" name="category" value="iceskating">Iceskating</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="snowboard">Snowboard</td>'
			+ '<td><input type="checkbox" name="category" value="skateboard">Skateboard</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="archery">Archery</td>'
			+ '<td><input type="checkbox" name="category" value="bicycle">Bicycle</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="camping">Camping</td>'
			+ '<td><input type="checkbox" name="category" value="diving">Diving</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="rowing">Rowing</td>'
			+ '<td><input type="checkbox" name="category" value="rollerskating">Rollerskating</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="swimming">Swimming</td>'
			+ '<td><input type="checkbox" name="category" value="gym">Gym</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><input type="checkbox" name="category" value="running">Running</td>'
			+ '</tr>'
			+ '</table>'
			+ '</form>'
			+ '</div></p><button name="save-marker" class="save-marker">Save Marker</button>';
	var marker = new google.maps.Marker({
		position : MapPos,
		map : map,
		draggable : Dragable,
		animation : google.maps.Animation.DROP,
		title : MapTitle
	});

	var content = $('<div class="marker-info-win">'
			+ '<div class="marker-inner-win"><span class="info-content">'
			+ '<h1 class="marker-heading">'
			+ MapTitle
			+ '</h1>'
			+ EditForm
			+ '</span><button name="remove-marker" class="remove-marker" title="Remove Marker">Remove Marker</button>'
			+ '</div></div>');
	var infowindow = new google.maps.InfoWindow();
	infowindow.setContent(content[0]);
	var removebtn = content.find('button.remove-marker')[0];
	var savebtn = content.find('button.save-marker')[0];

	google.maps.event.addDomListener(removebtn, "click", function(event) {
		removeMarker(marker);
	});

	google.maps.event.addDomListener(savebtn, "click", function(event) {
		var allCheckedValues = $('input:checked').map(function() {
									return this.value;
								}).get().join();
		$.get('KaartServlet', {
			method : "insertNewPoint",
			name : $('input[name="pName"]').val(),
			desc : $('textarea[name="pDesc"]').val(),
			link : $('input[name="pLink"]').val(),
			categories : allCheckedValues,
			location : marker.getPosition().toString()
		}, function(responseText) {
			//close infowindow ja reload point to map hetkel j‰‰b see aktiivseks ja
			//kui uuesti klikkida avaneb vorm mitte infowindow
			infowindow.close();
			alert("point inserterd");
		});
	});

	google.maps.event.addListener(marker, 'click', function() {
		infowindow.open(map, marker);
	});

	infowindow.open(map, marker);
}

function removeMarker(marker) {
	if (marker.getDraggable()) {
		marker.setMap(null);
	}
	// else remove from DB?
}

google.maps.event.addDomListener(window, 'load', initialize);