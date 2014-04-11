init = false;
window.fbAsyncInit = function() {
	FB.init({
		appId : '274740106025651', // võiks mingi faili teha, kust sensitive
									// infot võetakse?
		status : true,
		cookie : true,
		xfbml : true
	});
	init = true;
};

function login(e) {
	if (!init) {
		window.fbAsyncInit()
	}
	FB.login(function(e) {
		if (e.authResponse) {
			FB.api("/me", function(e) {
				closeLoginForm();
				enableLegend();
				$('#login-windowid').text('Log out');
				document.getElementById("login-windowid").setAttribute(
						"onClick", "javascript: logout();");
				$('#login-windowid').attr("href", "#");
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