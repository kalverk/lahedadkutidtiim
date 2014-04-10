function handleChange(evt) {

	var categories = [ "football", "basketball", "volleyball", "tennis",
			"tabletennis", "pool", "bowling", "golf", "hockey", "baseball",
			"crosscountry", "iceskating", "snowboard", "skatepark", "archery",
			"bicycle", "camping", "diving", "rowing", "skating", "swimming",
			"running", "gym" ];

	if (evt.pathNames[0] == 'login-box') {
		showLoginForm('a.login-window');
	} else if ($.inArray(evt.pathNames[0], categories) >= 0) {
		getInfo(evt.pathNames[0]);
		closeLoginForm();
	} else {
		closeLoginForm();
	}
}

SWFAddress.addEventListener(SWFAddressEvent.CHANGE, handleChange);