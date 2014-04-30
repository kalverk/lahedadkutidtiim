var isLargeLayout = false;
var isSmallLayout = false;

function adjustStyle(width) {
	width = parseInt(width);
	if (width < 701) {
		$("#size-stylesheet").attr("href", "css/small.css");
		isLargeLayout = false;
		isSmallLayout = true;
	} else if ((width >= 702) && (width < 1224)) {
		$("#size-stylesheet").attr("href", "css/medium.css");
		isLargeLayout = false;
		isSmallLayout = false;
	} else {
		$("#size-stylesheet").attr("href", "css/large.css");
		isLargeLayout = true;
		isSmallLayout = false;
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
			//seda tuleks vastavalt point onclickile muuta.
			var isPointDescription = true;
			var height = $(this).height() - $("#header").height()-30;
			var width = $(this).width()-20;
			$('#main-content').height(height);
			if(isPointDescription&&isLargeLayout){
				$('#main-content').width(width-200);
				$('#secondary-two').height(height);
				$('#secondary-two').width(width-$('#main-content').width()-22);
			}else if(isPointDescription&&isSmallLayout){
				$('#main-content').width(width);
				$('#secondary-two').height(height/2);
				$('#secondary-two').width(width);
			}
			else{
				$('#main-content').width(width);
				$('#secondary-two').height(height/2);
				$('#secondary-two').width(width);
			}
		});
