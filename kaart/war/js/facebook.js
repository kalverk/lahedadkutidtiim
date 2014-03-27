init = false;
window.fbAsyncInit = function() {
	FB.init({
		appId : '274740106025651', //võiks mingi faili teha, kust sensitive infot võetakse?
		status : true,
		cookie : true,
		xfbml : true
	});
	init = true;
};

function login(callBack) {
	if (!init) {
		window.fbAsyncInit();
	}
	FB.login(function(response) {
		if (response.authResponse) {
			FB.api('/me', function(response) {
				closeLoginForm();
//				alert('Good to see you, ' + response.name);
				enableLegend();	
			});
		}
	}, {
		perms : ""
	});

}

// Load the SDK Asynchronously
(function(d) {
	var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement('script');
	js.id = id;
	js.async = true;
	js.src = "//connect.facebook.net/en_US/all.js";
	ref.parentNode.insertBefore(js, ref);
}(document));