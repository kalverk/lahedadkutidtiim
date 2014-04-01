//jQuery on küll aeglasem kui javascriptiga seda teha aga jQuery on crossplatvorm
$(document).ready(function() {
	$('#football').click(function(event) {
		var categoryName = "football";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#basketball').click(function(event) {
		var categoryName = "basketball";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#volleyball').click(function(event) {
		var categoryName = "volleyball";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#tennis').click(function(event) {
		var categoryName = "tennis";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#tabletennis').click(function(event) {
		var categoryName = "tabletennis";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#pool').click(function(event) {
		var categoryName = "pool";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#bowling').click(function(event) {
		var categoryName = "bowling";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#golf').click(function(event) {
		var categoryName = "golf";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#hockey').click(function(event) {
		var categoryName = "hockey";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#baseball').click(function(event) {
		var categoryName = "baseball";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#crosscountry').click(function(event) {
		var categoryName = "crosscountry";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#iceskating').click(function(event) {
		var categoryName = "iceskating";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#snowboard').click(function(event) {
		var categoryName = "snowboard";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#skatepark').click(function(event) {
		var categoryName = "skatepark";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#archery').click(function(event) {
		var categoryName = "archery";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#bicycle').click(function(event) {
		var categoryName = "bicycle";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#camping').click(function(event) {
		var categoryName = "camping";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#diving').click(function(event) {
		var categoryName = "diving";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#rowing').click(function(event) {
		var categoryName = "rowing";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#skating').click(function(event) {
		var categoryName = "skating";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#swimming').click(function(event) {
		var categoryName = "swimming";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#gym').click(function(event) {
		var categoryName = "gym";
		getInfo(categoryName);
	});
});

$(document).ready(function() {
	$('#running').click(function(event) {
		var categoryName = "running";
		getInfo(categoryName);
	});
});

function getInfo(categoryName){
	breakInterval();
	$.get('KaartServlet', {
		method : "findByCategory",
		category : categoryName
	}, function(responseText) {
		toMap(responseText, categoryName);	
	});
}
