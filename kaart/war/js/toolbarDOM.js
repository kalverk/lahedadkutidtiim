//jQuery on küll aeglasem kui javascriptiga seda teha aga jQuery on crossplatvorm
$(document).ready(function() {
	$('#football').click(function(event) {
		var categoryName = "football";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);	
//			$('#welcometext').text(responseText);
		});
	});
});

$(document).ready(function() {
	$('#basketball').click(function(event) {
		var categoryName = "basketball";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#volleyball').click(function(event) {
		var categoryName = "volleyball";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#tennis').click(function(event) {
		var categoryName = "tennis";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#tabletennis').click(function(event) {
		var categoryName = "tabletennis";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#pool').click(function(event) {
		var categoryName = "pool";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#bowling').click(function(event) {
		var categoryName = "bowling";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#golf').click(function(event) {
		var categoryName = "golf";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#hockey').click(function(event) {
		var categoryName = "hockey";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#baseball').click(function(event) {
		var categoryName = "baseball";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#crosscountry').click(function(event) {
		var categoryName = "crosscountry";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#iceskating').click(function(event) {
		var categoryName = "iceskating";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#snowboard').click(function(event) {
		var categoryName = "snowboard";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#skatepark').click(function(event) {
		var categoryName = "skatepark";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#archery').click(function(event) {
		var categoryName = "archery";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#bicycle').click(function(event) {
		var categoryName = "bicycle";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#camping').click(function(event) {
		var categoryName = "camping";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#diving').click(function(event) {
		var categoryName = "diving";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#rowing').click(function(event) {
		var categoryName = "rowing";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#skating').click(function(event) {
		var categoryName = "skating";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#swimming').click(function(event) {
		var categoryName = "swimming";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#gym').click(function(event) {
		var categoryName = "gym";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});

$(document).ready(function() {
	$('#running').click(function(event) {
		var categoryName = "running";
		$.get('KaartServlet', {
			method : "findByCategory",
			category : categoryName
		}, function(responseText) {
			toMap(responseText);
		});
	});
});