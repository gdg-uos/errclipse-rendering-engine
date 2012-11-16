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

      .input[type="text"] {
        font-size: 12px;
        height: auto;
        margin-bottom: 0px;
      }

      #condition{
        margin-bottom:0px;
      }

      #searchpanel{
        text-align:right;
      }

    </style>
    <script type="text/javascript">
    $(document).ready(getData);

    function getData(){
      var cond = $(condition).val();
      //trim data
      cond = $.trim(cond);

      var condData = null;

      if(cond != ""){
          condData = {'cond': cond};
      }

      

      $.ajax({
        url: 'getuser.php',
        type: 'POST',
        dataType: 'text',
        data: condData,
        error: function(){
          $("#contents_here").html("error. try again :(");
        },
        success: function(text){
          var obj  = jQuery.parseJSON(text);

          var ret = "<br /><table class='table table-bordered'><tr><td>#</td><td>id</td><td>email</td></tr>";

          $.each(obj, function(i,row){
              if(i%2 == 0){
                ret += "<tr class='success'><td>"+row[0]+"</td><td>"+row[1]+"</td><td>"+row[2]+"</td></tr>"
              }else{
                ret += "<tr class='warning'><td>"+row[0]+"</td><td>"+row[1]+"</td><td>"+row[2]+"</td></tr>"

              }
          })

          ret += "</table>";

          $('#contents_here').html(ret);
        }
      })

    }

    function clearcondition(){
      $(condition).val("");
      getData();
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
                  <li><a href="adduser.php">Add user</a></li>
                  <li class="active"><a href="userlist.php">User list</a></li>
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
        Input condition(id or data) and search.<br />If you want whole list, press 'clear condition' button!
      </h2>
      </div>
      <div id="searchpanel">
        <input type="text" id="condition" class="condition" placeholder="condition here" /><input type="button" value="search" class="btn btn-medium btn-primary" onclick="getData()"  />&nbsp;<input type="button" value="clear condition" class="btn btn-medium btn-sub" onclick="clearcondition()" />
      </div>
      <div id="contents_here">
        adsfadsf;
      </div>
    </div>
</body>
</html>
