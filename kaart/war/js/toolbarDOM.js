//jQuery on küll aeglasem kui javascriptiga seda teha aga jQuery on crossplatvorm
$(document).ready(function() {
	$('#navigation input').click(function(event) {
		var categoryName = $(this).attr("id");
		var hash = "#" + $(this).attr("id");
		window.history.pushState(categoryName, "Spordikaart", hash);

//		getInfo(categoryName);
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
