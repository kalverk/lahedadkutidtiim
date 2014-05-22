var isLargeLayout = false;
var isSmallLayout = false;

function adjustStyle(width) {
	width = parseInt(width);
	if (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i
			.test(navigator.userAgent.toLowerCase())) {
		$("#size-stylesheet").attr("href", "css/mobile.css");
	} else if (width < 701) {
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
	$(window).trigger('resize');
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
			if (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i
					.test(navigator.userAgent.toLowerCase())) {
				var height = $(this).height() - $("#header").height() - 30;
				var width = $(this).width() - 20;
				$('#page-wrap').height(height);
				$('#page-wrap').width(width);
				$('#main-content').height(height - 90);
				$('#main-content').width(width);
				$('#secondary-two').width(width);
				$('#login-windowid2').width(width);
				$('#toolbar').width(width - 40);
			} else {
				// seda tuleks vastavalt point onclickile muuta.
				var height = $(this).height() - $("#header").height() - 30;
				var width = $(this).width() - 20;
				$('#main-content').height(height);
				if (isLargeLayout) {
					$('#main-content').width(width - 250);
					$('#comments').height(height - 150);
					$('#secondary-two').height(height);
					$('#secondary-two').width(
							width - $('#main-content').width() - 30);
				} else if (isSmallLayout) {
					$('#main-content').width(width);
					$('#secondary-two').width(width);
				} else {
					$('#main-content').width(width);
					$('#secondary-two').width(width);
				}
			}
		});