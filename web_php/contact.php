<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>errclipse server page</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/jquery-1.8.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
	 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="index.php">Errclipse</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li><a href="index.php">Home</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage Users <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="adduser.php">Add user</a></li>
                  <li><a href="userlist.php">User list</a></li>
                  <li><a href="modifyuser.php">how to modify users?</a></li>
                </ul>
              </li>
              <li><a href="stastics.php">Stastics</a></li>
              <li  class="active"><a href="contact.php">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container">
    	<div class="hero-unit">
    		<img src="img/gdguos.png" /> <br /><Br />
    		Errclipse, started at Google deveolpers group University of Seoul<br />
        Dept. of Computer Science students start it, but anyone can join errclipse development.<br />
    	</div>
    	<div class="row">
    		<div class="span4">
    			<h2>License Information</h2>
    			We follow MIT license. so it can use anyone, anywhere, anyway!
          You don't need think about that.<br /><br />
    			<a class="btn" href="http://en.wikipedia.org/wiki/MIT_License">MIT license information &raquo;</a>
    		</div>
    		<div class="span4">
    			<h2>Github</h2>
    			Our github repository.<br />You can access source code easily. <br/><br/><br/><br/><br />
    			<a class="btn" href="https://github.com/gdg-uos/errclipse-rendering-engine/">Go Github &raquo;</a>
    		</div>
    		<div class="span4">
    			<h2>GDG UOS</h2>
    			There is GDG UOS blog. you can get our information here<br />
    			But... we use only Korean. sorry :'(<br /><Br /><br /><br />
    			<a class="btn" href="http://uos.gdg.kr">Visit the blog &raquo;</a>
    		</div>
    	</div>
      <br />Current manager page version: V0.1 <br />
    </div>
</body>
</html>
