function handleChange(evt) {

	var categories = [ "football", "basketball", "volleyball", "tennis",
			"tabletennis", "pool", "bowling", "golf", "hockey", "baseball",
			"crosscountry", "iceskating", "snowboard", "skatepark", "archery",
			"bicycle", "camping", "diving", "rowing", "skating", "swimming",
			"running", "gym" ];

	if (evt.pathNames[0] == 'login-box') {
		if(	$('#login-windowid').text() == 'Log out'){
			FB.logout(function(response){
//				alert('user is logged out'); //disable legend?
			});
			//logout ka teiste variantidega
			$('#login-windowid').text('Log in');
			$('#login-windowid2').text('Log in');

		}else{
			showLoginForm('a.login-window');
		}
	} else if ($.inArray(evt.pathNames[0], categories) >= 0) {
		getInfo(evt.pathNames[0]);
		closeLoginForm();
	} else {
	}
}

SWFAddress.addEventListener(SWFAddressEvent.CHANGE, handleChange);