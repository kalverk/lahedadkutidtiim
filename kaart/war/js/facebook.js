init = false;
window.fbAsyncInit = function() {
	FB.init({
		appId : '274740106025651', // v�iks mingi faili teha, kust sensitive
									// infot v�etakse?
		status : true,
		cookie : true,
		xfbml : true
	});
	init = true;
};

function login(e) {
	var userName = "Madis";
	var userID = 112;
	$.post('KaartServlet', {
		method : "addUser",
		userID : userID,
		userName : userName
	}, function(responseText) {
		alert("user saved");
	});
	/*if (!init) {
		window.fbAsyncInit()
	}
	FB.login(function(e) {
		if (e.authResponse) {
			FB.api("/me", function(e) {
				closeLoginForm();
				var userName = e.name;
				var userID = e.userID
				//servletisse tegemata, kas nii saab need ikka k�tte?
				$.post('KaartServlet', {
					method : "addUser",
					userID : userID,
					userName : userName
				}, function(responseText) {
				});
				enableLegend();
				$('#login-windowid').text('Log out');
				$('#login-windowid2').text('Log out');

			    document.getElementById("login-windowid").setAttribute( "onClick", "javascript: logout();" );
			    document.getElementById("login-windowid2").setAttribute( "onClick", "javascript: logout();" );


			});
		}
	}, {
		perms : ""
	});*/
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