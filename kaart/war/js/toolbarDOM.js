$(document).ready(function() {
	$('#football').click(function(event) {
		var categoryName = "football";
		$.get('KaartServlet', {
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
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#volleyball').click(function(event) {
		var categoryName = "volleyball";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#tennis').click(function(event) {
		var categoryName = "tennis";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#tabletennis').click(function(event) {
		var categoryName = "tabletennis";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#pool').click(function(event) {
		var categoryName = "pool";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#bowling').click(function(event) {
		var categoryName = "bowling";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#golf').click(function(event) {
		var categoryName = "golf";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#hockey').click(function(event) {
		var categoryName = "hockey";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#baseball').click(function(event) {
		var categoryName = "baseball";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#crosscountry').click(function(event) {
		var categoryName = "crosscountry";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#iceskating').click(function(event) {
		var categoryName = "iceskating";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#snowboard').click(function(event) {
		var categoryName = "snowboard";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#skatepark').click(function(event) {
		var categoryName = "skatepark";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#archery').click(function(event) {
		var categoryName = "archery";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#bicycle').click(function(event) {
		var categoryName = "bicycle";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#camping').click(function(event) {
		var categoryName = "camping";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#diving').click(function(event) {
		var categoryName = "diving";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#rowing').click(function(event) {
		var categoryName = "rowing";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#skating').click(function(event) {
		var categoryName = "skating";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#swimming').click(function(event) {
		var categoryName = "swimming";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#gym').click(function(event) {
		var categoryName = "gym";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});

$(document).ready(function() {
	$('#running').click(function(event) {
		var categoryName = "running";
		$.get('KaartServlet', {
			category : categoryName
		}, function(responseText) {
			alert(responseText);
		});
	});
});