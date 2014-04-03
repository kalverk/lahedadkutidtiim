function adjustStyle(width) {
	width = parseInt(width);
	if (width < 701) {
		$("#size-stylesheet").attr("href", "css/small.css");
	} else if ((width >= 702) && (width < 1224)) {
		$("#size-stylesheet").attr("href", "css/medium.css");
	} else {
		$("#size-stylesheet").attr("href", "css/large.css");
	}
}

$(function() {
	adjustStyle($(this).width());
	$(window).resize(function() {
		adjustStyle($(this).width());
	});
});

// muuda map div kõrgust vastavalt brauseri kõrgusele (läheb vaja ka siis kui
// lisame
// pärast comment blocki.
$(document).ready(function() {
	$(window).resize();
});

$(window).resize(
		function() {
			var height = $(this).height() - $("#header").height()-30;
			var width = $(this).width()-20;
			$('#main-content').height(height);
			$('#main-content').width(width);
		});
