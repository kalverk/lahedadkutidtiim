init = false;
window.fbAsyncInit = function() {
	FB.init({
		appId : '274740106025651', //v�iks mingi faili teha, kust sensitive infot v�etakse?
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
				alert('Good to see you, ' + response.name);
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