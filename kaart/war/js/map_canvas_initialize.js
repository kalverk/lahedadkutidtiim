var initialLocation;
var browserSupportFlag = new Boolean();
var liivi = new google.maps.LatLng(58.37824850000001, 26.71467329999996);
var map;
var markersArray = [];
var forceToAddLocation;
var interval;

function initialize() {
	var map_canvas = document.getElementById('main-content');
	var mapOptions = {
		zoom : 14,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
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
		if (errorFlag === true) {
			alert("Geolocation service failed");
			initialLocation = liivi;
		} else {
			alert("Browser does not support geolocation.");
			initialLocation = liivi;
		}
		map.setCenter(initialLocation);
	}
	forceToAddLocation = false;
	showLegend();
	// for testing
	// enableLegend();
}

function showLegend(){
	var legend = document.getElementById('legend');
	var div = document.createElement('div');
	div.innerHTML = '<label class="legend-label">Add locations</label><input type="image" id="marker" src="images/iconsB/marker.png" class="marker" onclick="checkLoginStatus()"></input>';
	legend.appendChild(div);
	map.controls[google.maps.ControlPosition.RIGHT_TOP].push(document
			.getElementById('legend'));
}

function checkLoginStatus(){
	// teiste logimistega tuleb midagi sarnast teha
	FB.getLoginStatus(function(response){
		// kui ta lehele tulles on juba sisselogitud ja klikib legendil
		if(response.status=='connected'){
			addPlaces(map.getCenter(), true, true, true);
		// kui ta lehele tulles ei ole sisselogitud ja klikib legendil
		}else{
			showLoginForm("a.login-window");
			// getter oleks mõistlikum aga osad brauserid vist ei toeta seda
			forceToAddLocation = true;
		}
	});
}

function enableLegend() {
	if(forceToAddLocation){
		addPlaces(map.getCenter(), true, true, true);
	}
    document.getElementById("marker").setAttribute( "onClick", "javascript: addPlaces(map.getCenter(), true, true, true);" );
	google.maps.event.addListener(map, 'rightclick', function(event) {
		addPlaces(event.latLng, true, true, true);
	});
}

function toMap(responseText, categoryName) {
	if (markersArray) {
		for (var i = 0; i < markersArray.length; i++) {
			markersArray[i].setMap(null);
		}
		markersArray = [];
	}
	addToMap(responseText);
	doPoll(categoryName);
}

function addToMap(responseText){
	var array = responseText.split("|");
	for (var i = 0; i < array.length; i++) {
		// muidu viskab viimane element JSON.parse errori ja ei saa pollingut
		// teha
		if(!array[i]||array[i]===""){
			break;
		}
		else{
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
}

function doPoll(category){
	if(markersArray){
		var lastMarker = markersArray[markersArray.length-1];
		var id = marker.get("id");
	}
	interval = setInterval(function(){
		$.get('KaartServlet', {
			method : "findUpdates",
			category : category,
			lastid : id
		}, function(responseText) {
			addToMap(responseText);	
		});
	}, 60000);
}

function breakInterval(){
	clearInterval(interval);
}

function markerToMap(id, lat, lng) {
	var point = new google.maps.LatLng(lat, lng);
	marker = new google.maps.Marker({
		position : point,
		map : map
	});
	marker.set("id", id);
	listenMarker(marker);
	markersArray.push(marker);
}

function listenMarker(marker){
	google.maps.event.addListener(marker, "click", function(event) {
		$.get('KaartServlet', {
			method : "getPointDescription",
			id : this.get("id")
		}, function(responseText) {
			var array = responseText.split("|");
			var point = JSON.parse(array[0]);
			var id = point.id;
			var name = point.name;
			var description = point.description;
			var link = point.link;	
		    var location = geocode(point.location);
		    var inf = '<div class="marker-desc"><div class="marker-desc-win"><span><h1 id="pointName">'
				+ name+'</h1><p><div class="marker-desc-edit"><label><span>Description :</span><label id="pointDescription">'
				+ description+'</label></label><label><span>Address :</span><label id="pointLocation">'+location+'</label></label>';
			var urlRegEx = /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
			if(urlRegEx.test(link)){
				var url = link;
				var link_name = name;
				inf+='<label><span>Link :</span><label id="pointLink"><a href="'+url+'">'+link_name+'</a></label></label>';
			}
			inf+='</div></p></span></div></div>';
				// failist lugemine ei tööta internet exploreris seega jätame
				// stringis
				// var content = $().load("html/desc.html");
				// infowindow layout tuleb korda teha
			var infowindow = new google.maps.InfoWindow();
			infowindow.setContent(inf);
			infowindow.open(map, marker); 
		});
	});
}

function geocode(pointlocation){
	var latlngstr = point.location.split(",",2);
	var lat = parseFloat(latlngstr[0]);
    var lng = parseFloat(latlngstr[1]);
    var latlng = new google.maps.LatLng(lat, lng);
	var geocoder = new google.maps.Geocoder();
	geocoder.geocode({'latLng': pointlocation}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			if (results[1]) {
	        	var location = results[1].formatted_address;
	        }else{
	        	var location = pointlocation;
	        }
		}
		return location;
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
	
	markersArray.push(marker);

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
		var name = $('input[name="pName"]').val();
		var desc = $('textarea[name="pDesc"]').val();
		var link = $('input[name="pLink"]').val();
		var location = marker.getPosition().toString();
		var content = $('<div class="marker-desc">'
				+ '<div class="marker-desc-win"><span>'
				+ '<h1 id="pointName">'
				+ name
				+ '</h1><p>'
				+ '<div class="marker-desc-edit">'
				+ '<label><span>Description :</span>'
				+ '<label id="pointDescription">'
				+ desc
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
		$.get('KaartServlet', {
			method : "insertNewPoint",
			name : name,
			desc : desc,
			link : link,
			categories : allCheckedValues,
			location : location
		}, function(responseText) {
			infowindow.setContent(content[0]);
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