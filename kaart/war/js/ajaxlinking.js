function handleChange(evt) {
	string test;
	
	for(var a in evt.pathNames){
		console.log("saads");
	}
	

	
	
	if (evt.pathNames[0] == 'login-box'){
		 showLoginForm('a.login-window');
	    } else if (evt.pathNames[0] == 'contact') {
	        //load contact page
	    } else {
	    	closeLoginForm();
	    }
}

SWFAddress.addEventListener(SWFAddressEvent.CHANGE, handleChange);