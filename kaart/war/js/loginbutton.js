function showLoginForm(what) {

	var loginBox = $(what).attr('href');

	$(loginBox).fadeIn(300);

	var popMargTop = ($(loginBox).height() + 24) / 2;
	var popMargLeft = ($(loginBox).width() + 24) / 2;

	$(loginBox).css({
		'margin-top' : -popMargTop,
		'margin-left' : -popMargLeft
	});

	$('body').append('<div id="mask"></div>');
	$('#mask').fadeIn(300);
	
	//document.getElementById(what).href="#login-box";

	return false;
}

function closeLoginForm() {
	$('#mask , .login-popup').fadeOut(300, function() {
		$('#mask').remove();
	});
	//muudan log in button log out buttoniks
	$('#login-window').text('Log out');
    document.getElementById("login-window").setAttribute( "onClick", "javascript: logout();" );
	return false;
}

function logout(){
	FB.logout(function(response){
		//user is logged out
	});
	//logout ka teiste variantidega
	$('#login-window').text('Log in');
    document.getElementById("login-window").setAttribute( "onClick", "javascript: showLoginForm('a.login-window');" );
}