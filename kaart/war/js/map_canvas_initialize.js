var initialLocation;
var browserSupportFlag = new Boolean();
var liivi = new google.maps.LatLng(58.37824850000001, 26.71467329999996);
var map;
var markersArray = [];
var forceToAddLocation;
var markerID = -1;
var interval;

function initialize() {
	var map_canvas = document.getElementById('main-content');
	var mapOptions = {
		zoom : 14,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map(map_canvas, mapOptions);
//	if (navigator.geolocation) {
//		browserSupportFlag = true;
//		navigator.geolocation.getCurrentPosition(function(position) {
//			initialLocation = new google.maps.LatLng(position.coords.latitude,
//					position.coords.longitude);
//			map.setCenter(initialLocation);
//		}, function() {
//			handleNoGeolocation(browserSupportFlag);
//		});
//	} else {
//		browserSupportFlag = false;
//		handleNoGeolocation(browserSupportFlag);
//	}
//
//	function handleNoGeolocation(errorFlag) {
//		if (errorFlag === true) {
//			alert("Geolocation service failed");
//			initialLocation = liivi;
//		} else {
//			alert("Browser does not support geolocation.");
			initialLocation = liivi;
//		}
		map.setCenter(initialLocation);
//	}
	forceToAddLocation = false;
	forceRating = false;
	showLegend();
	// for testing
	//enableLegend();
}

function showLegend() {
	var legend = document.getElementById('legend');
	var div = document.createElement('div');
	div.innerHTML = '<label class="legend-label">Add locations</label><input type="image" id="marker" src="images/iconsB/marker.png" class="marker" onclick="checkLoginStatus()"></input>'
			+ '<label id ="count" class="legend-label">Points on map: 0</label>';
	legend.appendChild(div);
	map.controls[google.maps.ControlPosition.RIGHT_TOP].push(document
			.getElementById('legend'));
}

function checkLoginStatus() {
	// teiste logimistega tuleb midagi sarnast teha
	FB.getLoginStatus(function(response) {
		// kui ta lehele tulles on juba sisselogitud ja klikib legendil
		if (response.status == 'connected') {
			addPlaces(map.getCenter(), true, true, true);
			// kui ta lehele tulles ei ole sisselogitud ja klikib legendil
		} else {
			showLoginForm("a.login-window");
			// getter oleks mõistlikum aga osad brauserid vist ei toeta seda
			forceToAddLocation = true;
		}
	});
}

function enableLegend() {
	if (forceToAddLocation) {
		addPlaces(map.getCenter(), true, true, true);
	}
	document.getElementById("marker").setAttribute("onClick",
			"javascript: addPlaces(map.getCenter(), true, true, true);");
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
	getCount(categoryName);
	doPoll(categoryName);
}

function addToMap(responseText) {
	var array = responseText.split("|");

	for (var i = 0; i < array.length; i++) {
		// muidu viskab viimane element JSON.parse errori ja ei saa pollingut
		// teha
		if (!array[i] || array[i] == "") {
			break;
		} else {
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

function getCount(categoryName) {
	$.get('KaartServlet', {
		method : "getCategoryCount",
		category : categoryName
	}, function(responseText) {
		toCount(responseText);
	});
}

function toCount(responseText) {
	if (responseText == '') {
		responseText = '0';
	}
	document.getElementById("count").innerHTML = 'Points on map: '
			+ responseText;
}

function doPoll(category) {
	if (markersArray) {
		var lastMarker = markersArray[markersArray.length - 1];
		var id = marker.get("id");
	}
	interval = setInterval(function() {
		$.get('KaartServlet', {
			method : "findUpdates",
			category : category,
			lastid : id
		}, function(responseText) {
			addToMap(responseText);
		});
	}, 60000);
}

function breakInterval() {
	clearInterval(interval);
}

function markerToMap(id, lat, lng) {
	var point = new google.maps.LatLng(lat, lng);
	marker = new google.maps.Marker({
		position : point,
		map : map,
		optimized : false //for testing with selenium!!!
	});
	marker.set("id", id);
	listenMarker(marker);
	markersArray.push(marker);
}

function listenMarker(marker) {
	google.maps.event
			.addListener(
					marker,
					"click",
					function(event) {
						$
								.get(
										'KaartServlet',
										{
											method : "getPointDescription",
											id : this.get("id")
										},
										function(responseText) {
											var array = responseText.split("|");
											var point = JSON.parse(array[0]);
											var id = point.id;
											var name = point.name;
											var description = point.description;
											var link = point.link;
											var rating = point.rating;
											var userFbId = point.userFbId;
											// /Latlng to address
											var latlngstr = point.location
													.split(",", 2);
											var lat = parseFloat(latlngstr[0]);
											var lng = parseFloat(latlngstr[1]);
											var latlng = new google.maps.LatLng(
													lat, lng);
											markerID = id;
											clearComments();
											initializeCommentBar();

											var geocoder = new google.maps.Geocoder();
											geocoder
													.geocode(
															{
																'latLng' : latlng
															},
															function(results,
																	status) {
																var inf = '<div class="marker-desc"><div class="marker-desc-win"><span><h1 id="pointName">'
																		+ name
																		+ '</h1><p><div class="marker-desc-edit"><label><span>Description :</span><label id="pointDescription">'
																		+ description
																		+ '</label></label>';
																if (status == google.maps.GeocoderStatus.OK) {
																	if (results[1]) {
																		var location = results[1].formatted_address;
																	} else {
																		var location = point.location;
																	}
																	inf += '<label><span>Address :</span><label id="pointLocation">'
																			+ location
																			+ '</label></label>';
																}
																var urlRegEx = /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
																if (urlRegEx
																		.test(link)) {
																	var url = link;
																	var link_name = name;
																	inf += '<label><span>Link :</span><label id="pointLink"><a href="'
																			+ url
																			+ '">'
																			+ link_name
																			+ '</a></label></label>';
																}
																inf += '<label><span>Rating :</span><label id="pointLocation">'
																	+ point.rating
																	+ '</label></label>';
																inf += '<div id="rating_bar"><ul><li class="circle"></li><li class="circle"></li><li class="circle"></li><li class="circle"></li><li class="circle"></li></ul></div>';
																var u = getUserID();
																if(u==userFbId){
																	//add deletion button
																	inf+='<button name="remove-marker" class="remove-marker" title="Remove Marker">Remove Marker</button></div></p></span></div></div>';
																	var content = $(inf);
																	var removebtn = content.find('button.remove-marker')[0];
																	google.maps.event.addDomListener(removebtn, "click", function(event) {
																		removeMarker(marker);
																	});
																}else{
																	inf +='</div></p></span></div></div>';
																	var content = $(inf);
																}
																// infowindow
																// layout tuleb
																// korda teha
																var infowindow = new google.maps.InfoWindow(
																		{
																			content : content[0],
																			minWidth : 300
																		});
																infowindow
																		.open(
																				map,
																				marker);
																google.maps.event.addListener(infowindow,'closeclick',function(){
																		markerID = -1;
																		clearComments();
																});
																var ratingbar = content.find('#rating_bar')[0];
																google.maps.event.addDomListenerOnce(ratingbar, "mouseover", function(event) {
																	var click = false;
																	$("#rating_bar ul li").mouseenter(function() {
																		if (!click) {
																			var str = $(this).index();
																			for (var i = 0; i <= str; i++) {
																				$('#rating_bar ul li').eq(i).css('background', '#B0E57C');
																			}
																		}
																	}).mouseleave(function() {
																		if (!click) {
																			var str = $(this).index();
																			for (var i = 0; i <= str; i++) {
																				$('#rating_bar ul li').eq(i).css('background', '#FFF0AA');
																			}
																		}
																	}).click(function() {
																		var str = $(this).index();
																		for (var i = 0; i < 6; i++) {
																			$('#rating_bar ul li').eq(i).css('background', '#FFF0AA');
																		}
																		for (var i = 0; i <= str; i++) {
																			$('#rating_bar ul li').eq(i).css('background', '#B0E57C');
																		}
																		click = true;
																		FB.getLoginStatus(function(response) {
																			// kui ta lehele tulles on juba sisselogitud ja klikib hindamisel
																			if (response.status == 'connected') {
																				//seo hinne kasutajaga ja saada ära
																				var userName = ""; //pole kindel kas see töötab!
																				u = response.authResponse.userID;
																				alert(u + " " + userName);
																				addUserRating(str + 1, marker.get("id"), u, userName);
																			} else {
																				showLoginForm("a.login-window");
																			}
																		});
																	});
																});
															});
										});
					});
}

function addUserRating(rating, pointID, userID, userName){
	$.post('KaartServlet', {
		method : "addUserRating",
		userID : userID,
		pointID : pointID,
		rating : rating,
		userName : userName
	}, function(responseText) {
		alert("Rating is posted");
	});
}

//Comment bar listeneride osa
$('#comment-buttonid').attr('disabled','disabled');

$('#commentBox').keyup(function(){
    var text = $('#commentBox').val();
    if(text.length<1){
        $('#comment-buttonid').attr('disabled','disabled');
        $('.comment-button').css('background', '#B4D8E7');
    }else{
    	$('.comment-button').css('background', '#56BAEC');
        $('#comment-buttonid').removeAttr('disabled');
    }
}); 

$('#comment-buttonid').click(function(){
    if ( $('#comment-buttonid').attr('disabled') == "disabled" || markerID < 0) {
       return false;
    }
    else {
    	var text = $('#commentBox').val();
    	FB.getLoginStatus(function(response) {
    		// kui ta lehele tulles on juba sisselogitud ja klikib kommenteerimisel
    		if (response.status == 'connected') {
				//seo kommentaar kasutajaga ja saada ära
    			var userName = ""; //pole kindel kas see töötab!
				var u = response.authResponse.userID;;
				alert(u+" "+userName);
				addUserComment(u, text, userName);
			} else {
				showLoginForm("a.login-window");
			}
    	});
   }
});

function clearComments(){
	$('#comment-list').empty();
}

function initializeCommentBar(){
	clearComments();
	$.get('KaartServlet', {
		method : "getPointComments",
		pointID : markerID
	}, function(responseText) {
		var array = responseText.split("!");
		var addComment = function(comment, name){
			$('#comment-list').append('<li id="c1"><div class="comment"><div class="comment-text"><p>'+comment+'</p></div><p class="comment-info">'+name+'</p></div>');
		}
		if(array.length<3){
			addComment("Be the first to share you experience with this location!", "Tinn the adminn");
		}else{
			for (var i = 1; i < array.length; i++) {
				var parts = array[i].split(";");
				var name = parts[0];
				var comment = parts[1];
				if(comment!=""&&name!=""){
					addComment(comment, name);
				}
			}
		}
	});
}

function addUserComment(userID, text, userName){
	$.post('KaartServlet', {
		method : "addUserComment",
		userID : userID,
		pointID : markerID,
		comment : text,
		userName : userName
	}, function(responseText) {
		$('#commentBox').val('');
		initializeCommentBar()
	});
}

function addPlaces(MapPos, InfoOpenDefault, Dragable, Removable) {
	var MapTitle = "Add new location";
	var EditForm = '<p><div class="marker-edit">'
			+ '<form name="SaveMarker" id="SaveMarker">'
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
			+ '<td><input id=rowbox type="checkbox" name="category" value="rowing">Rowing</td>'
			+ '<td><input type="checkbox" name="category" value="skating">Rollerskating</td>'
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
				+ '<div class="marker-desc-win"><span>' + '<h1 id="pointName">'
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
				+ '</div>' + ' </p>' + ' </span>' + '</div>' + '</div>');
		var userID = getUserID();
		$.post('KaartServlet', {
			method : "insertNewPoint",
			name : name,
			desc : desc,
			link : link,
			categories : allCheckedValues,
			location : location,
			userID : userID,
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
	}else{
		pointID = marker.get("id")
		$.post('KaartServlet', {
			method : "deleteUserPoint",
			pointID : pointID
		}, function(responseText) {
			marker.setMap(null)
		});
	}
}

google.maps.event.addDomListener(window, 'load', initialize);