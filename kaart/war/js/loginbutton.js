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
	return false;
}

function logout(){
	FB.logout(function(response){
		//user is logged out
	});
	//logout ka teiste variantidega
	$('#login-windowid').text('Log in');
    document.getElementById("login-windowid").setAttribute( "onClick", "javascript: showLoginForm('a.login-window');" );
    $('#login-windowid').attr("href", "#login-box");
}