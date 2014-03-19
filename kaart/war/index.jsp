<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

<title>Spordikaart</title>

<link rel='stylesheet' type='text/css' href='css/style.css' />
<link id="size-stylesheet" rel='stylesheet' type='text/css'
	href='css/small.css' />

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>
<script type="text/javascript"
	src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript" src="js/map_canvas_initialize.js"></script>
<script type='text/javascript' src='js/resolution-change.js'></script>
<script type="text/javascript" src="js/loginbutton.js"></script>
<script type="text/javascript" src="js/facebook.js"></script>
<script type="text/javascript" src="js/toolbarDOM.js"></script>

</head>

<body>
	<div id="fb-root"></div>
	<div id="page-wrap">
		<div id="header">		
			<div id="toolbar">
				<ul id="navigation">
					<li><input type="image" id="football" src="images/iconsB/football.png" class="football"></input></li>
					<li><input type="image" id="basketball" src="images/iconsB/basketball.png" class="basketball"></input></li>
					<li><input type="image" id="volleyball" src="images/iconsB/volleyball.png" class="volleyball"></input></li>
					<li><input type="image" id="tennis" src="images/iconsB/tennis.png" class="tennis"></input></li>
					<li><input type="image" id="tabletennis" src="images/iconsB/tabletennis.png" class="tabletennis"></input></li>
					<li><input type="image" id="pool" src="images/iconsB/pool.png" class="pool"></input></li>
					<li><input type="image" id="bowling" src="images/iconsB/bowling.png" class="bowling"></input></li>
					<li><input type="image" id="golf" src="images/iconsB/golf.png" class="golf"></input></li>
					<li><input type="image" id="hockey" src="images/iconsB/hockey.png" class="hockey"></input></li>
					<li><input type="image" id="baseball" src="images/iconsB/baseball.png" class="baseball"></input></li>
					<li><input type="image" id="crosscountry" src="images/iconsB/crosscountry.png" class="crosscountry"></input></li>
					<li><input type="image" id="iceskating" src="images/iconsB/ice-skating.png" class="iceskating"></input></li>
					<li><input type="image" id="snowboard" src="images/iconsB/snowboard.png" class="snowboard"></input></li>
					<li><input type="image" id="skatepark" src="images/iconsB/skatepark.png" class="skatepark"></input></li>
					<li><input type="image" id="archery" src="images/iconsB/archery.png" class="archery"></input></li>
					<li><input type="image" id="bicycle" src="images/iconsB/bicycle.png" class="bicycle"></input></li>
					<li><input type="image" id="camping" src="images/iconsB/camping.png" class="camping"></input></li>
					<li><input type="image" id="diving" src="images/iconsB/diving.png" class="diving"></input></li>
					<li><input type="image" id="rowing" src="images/iconsB/rowing.png" class="rowing"></input></li>
					<li><input type="image" id="skating" src="images/iconsB/skating.png" class="skating"></input></li>
					<li><input type="image" id="swimming" src="images/iconsB/swimming.png" class="swimming"></input></li>
					<li><input type="image" id="running" src="images/iconsB/running.png" class="running"></input></li>
					<li><input type="image" id="gym" src="images/iconsB/gym.png" class="gym"></input></li>
					<!-- kõige viimast pole miskipärast võimalik vajutada prob scrollbari viga -->
				</ul>
			</div>
			<div id="login">
				<ul id="navigation2">
					<li><a href="#login-box" class="login-window"
						onclick="showLoginForm('a.login-window')">Log in</a></li>
				</ul>
			</div>
		</div>

		<div id="main-content">Map</div>

		<div id="secondary-two">Comment</div>

		<div id="login-box" class="login-popup">
			<a href="#" class="close" onclick="closeLoginForm()"><img
				src="images/close_pop.png" class="btn_close" title="Close Window"
				alt="Close" /></a>
			<form method="post" class="signin" action="#">
				<fieldset class="textbox">
					<label class="username"> <span>Username or email</span> <input
						id="username" name="username" value="" type="text"
						placeholder="Username" /></label> <label
						class="password"> <span>Password</span> <input
						id="password" name="password" value="" type="password"
						placeholder="Password" /></label>
					<button class="submit button" type="button">Sign in</button>
					<p>
						<a class="forgot" href="#">Forgot your password?</a>
					</p>
				</fieldset>
			</form>
			<div class="loginother">
				<label class="loginotherway"> <span>Login in with</span> <a
					href="#" onclick="touch('login_with_google')"><img
						src="images/google_login.png" class="google_login_button"
						alt="GoogleLogin" /></a> <a href="#" onclick="login()"><img
						src="images/facebook_login.png" class="fb_login_button"
						alt="FacebookLogin" /></a></label>
			</div>
		</div>
	</div>
	<div id="legend"><h3>Add locations</h3></div>

</body>

</html>