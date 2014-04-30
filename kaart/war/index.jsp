<%@page import="com.google.appengine.api.search.GetResponse"%>
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


</head>

<body class="no-js">
	<script type="text/javascript"> document.body.className = document.body.className.replace("no-js","js");
	</script>
	<div id="fb-root"></div>
	<div id="page-wrap">
		<div id="header">
			<div id="toolbar">
			
				<h1 id="nojs">Spordikaart</h1>
				<ul id="navigation">
				
					<li><input type="image" id="football"
						src="images/iconsB/football.png" class="ImgButton"></input></li>
					<li><input type="image" id="basketball"
						src="images/iconsB/basketball.png" class="ImgButton"></input></li>
					<li><input type="image" id="volleyball"
						src="images/iconsB/volleyball.png" class="ImgButton"></input></li>
					<li><input type="image" id="tennis"
						src="images/iconsB/tennis.png" class="ImgButton"></input></li>
					<li><input type="image" id="tabletennis"
						src="images/iconsB/tabletennis.png" class="ImgButton"></input></li>
					<li><input type="image" id="pool" src="images/iconsB/pool.png"
						class="ImgButton"></input></li>
					<li><input type="image" id="bowling"
						src="images/iconsB/bowling.png" class="ImgButton"></input></li>
					<li><input type="image" id="golf" src="images/iconsB/golf.png"
						class="ImgButton"></input></li>
					<li><input type="image" id="hockey"
						src="images/iconsB/hockey.png" class="ImgButton"></input></li>
					<li><input type="image" id="baseball"
						src="images/iconsB/baseball.png" class="ImgButton"></input></li>
					<li><input type="image" id="crosscountry"
						src="images/iconsB/crosscountry.png" class="ImgButton"></input></li>
					<li><input type="image" id="iceskating"
						src="images/iconsB/ice-skating.png" class="ImgButton"></input></li>
					<li><input type="image" id="snowboard"
						src="images/iconsB/snowboard.png" class="ImgButton"></input></li>
					<li><input type="image" id="skatepark"
						src="images/iconsB/skatepark.png" class="ImgButton"></input></li>
					<li><input type="image" id="archery"
						src="images/iconsB/archery.png" class="ImgButton"></input></li>
					<li><input type="image" id="bicycle"
						src="images/iconsB/bicycle.png" class="ImgButton"></input></li>
					<li><input type="image" id="camping"
						src="images/iconsB/camping.png" class="ImgButton"></input></li>
					<li><input type="image" id="diving"
						src="images/iconsB/diving.png" class="ImgButton"></input></li>
					<li><input type="image" id="rowing"
						src="images/iconsB/rowing.png" class="ImgButton"></input></li>
					<li><input type="image" id="skating"
						src="images/iconsB/skating.png" class="ImgButton"></input></li>
					<li><input type="image" id="swimming"
						src="images/iconsB/swimming.png" class="ImgButton"></input></li>
					<li><input type="image" id="running"
						src="images/iconsB/running.png" class="ImgButton"></input></li>
					<li><input type="image" id="gym" src="images/iconsB/gym.png"
						class="ImgButton"></input></li>
					<!-- kõige viimast pole miskipärast võimalik vajutada prob scrollbari viga -->
				</ul>
			</div>
			<div id="login">
				<ul id="navigation2">
					<li><a href="#login-box" id="login-windowid" class="login-window"
      					onclick="showLoginForm('a.login-window')">Log in</a></li>
				</ul>
			</div>
		</div>
		
		<div id="nojs2">
			<form action="KaartServlet" method="get">
				<select name="category">
				  <option value="football">Football</option>
				  <option value="basketball">Basketball</option>
				  <option value="volleyball">Volleyball</option>
				  <option value="tennis">Tennis</option>
				  <option value="tabletennis">Tabletennis</option>
				  <option value="pool">Pool</option>
				  <option value="bowling">Bowling</option>
				  <option value="golf">Golf</option>
				  <option value="hockey">Hockey</option>
				  <option value="baseball">Baseball</option>
				  <option value="crosscountry">Crosscountry</option>
				  <option value="iceskating">Iceskating</option>
				  <option value="snowboard">Snowboard</option>
				  <option value="skatepark">Skatepark</option>
				  <option value="archery">Archery</option>
				  <option value="bicycle">Bicycle</option>
				  <option value="diving">Diving</option>
				  <option value="rowing">Rowing</option>
				  <option value="skating">Skating</option>
				  <option value="running">Running</option>
				  <option value="gym">Gym</option>
				</select>
				<input type="hidden" name="method" value="infoNOJS" />
       			<input type="submit" value="Get locations" />        			
    		</form>
    		<table>
	    		<tr>
	    			<th>Name</th>
	    			<th>Description</th>
	    			<th>Link</th>
	    			<th>Location</th>
    			</tr>
    			<% if(request.getAttribute("id") != null){ %>
    			<%= (String)request.getAttribute("id") %>
    			<% }else{%>
    			<%=""%>
    			<%} %>
    		</table>
			
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
						id="username" name="username" value="" type="text" /></label> <label
						class="password"> <span>Password</span> <input
						id="password" name="password" value="" type="password" /></label>
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
	<div id="legend"></div>
	


<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script type='text/javascript'
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>
<script type="text/javascript"
	src="http://connect.facebook.net/en_US/all.js"></script>
<!--<script type="text/javascript" src="js/f.js"></script>-->
<script type="text/javascript" src="js/map_canvas_initialize.js"></script>
<script type='text/javascript' src='js/resolution-change.js'></script>
<script type="text/javascript" src="js/loginbutton.js"></script>
<script type="text/javascript" src="js/facebook.js"></script>
<script type="text/javascript" src="js/toolbarDOM.js"></script>
<script type="text/javascript" src="js/swfaddress.js"></script>
<script type="text/javascript" src="js/ajaxlinking.js"></script>
</body>
	

</html>