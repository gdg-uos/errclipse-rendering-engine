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
              <li class="active"><a href="index.php">Home</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage Users <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="adduser.php">Add user</a></li>
                  <li><a href="userlist.php">User list</a></li>
                  <li><a href="modifyuser.php">how to modify users?</a></li>
                </ul>
              </li>
              <li><a href="stastics.php">Stastics</a></li>
              <li><a href="contact.php">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container">
    	<div class="hero-unit">
    		<img src="img/logo.png" /> <br />
    		Errclipse Server managing page. you can see the stastics and manage users.<br />
    	</div>
    	<div class="row">
    		<div class="span4">
    			<h2>Add users</h2>
    			You can add users who wants join your group<br />
    			we only need a id, just for make stastics.<br /><br /><br /><br />
    			<a class="btn" href="adduser.php">Go add users &raquo;</a>
    		</div>
    		<div class="span4">
    			<h2>Stastics</h2>
    			You can get detail information, likes 'who works hard?' or 'who makes many errors'. is it awesome?<br />you can get easily here. why not try?<br /><br />
    			<a class="btn" href="#">Go view stastics &raquo;</a>
    		</div>
    		<div class="span4">
    			<h2>Contact</h2>
    			Do you want to know more?<br />
    			Enterprise service, global stastics, many solution informations..<br /><Br /><br />
    			<a class="btn" href="#">Contact us &raquo;</a>
    		</div>
    	</div>
    </div>
</body>
</html>
