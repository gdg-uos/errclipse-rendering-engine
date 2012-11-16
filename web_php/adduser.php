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
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <script type="text/javascript">
    	function checkredundancy(){
    		$.ajax({
    			url: 'checkuser.php',
    			type: 'POST',
    			dataType: 'text',
    			data: {
    				'user_id':$('#user_id').val(),
    				'user_email': $('#user_email').val(),
    			},
    			error: function(){
    				alert('fail to get data');
    			},
    			success: function(text){
    				if(text == "allow"){
    					alert("congraturation! use the id!");
    					location.href = "index.php";
    				}else if(text == "id"){
    					alert("id duplicated!");
    				}else if(text =="email"){
    					alert("email duplicated!");
    				}else if(text == "error"){
    					alert("you need input all data");
    				}else{
    					alert(text);
    				}
    			}
    		})
    	}
    </script>
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
              <li class="dropdown active">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage Users <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li class="active"><a href="adduser.php">Add user</a></li>
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
    	<h2>
    		Welcome!<br />make here! just insert your id and email
    	</h2>
    	<form class="form-signin">
    		<input type="text" id='user_id' class="input-block-level" placeholder="Input id here" /> <br />
    		<input type="text" id='user_email' class="input-block-level" placeholder="Input email here" /> <br />
    		<input type="button" class="btn btn-large btn-sub" value="Check here" onclick="checkredundancy();" />
    	</form>
    	</div>
    </div>
</body>
</html>
