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
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="js/jquery-1.8.2.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
     google.load('visualization', '1.0', {'packages':['corechart']});
     google.setOnLoadCallback(drawCharts);

     //$(document).ready(drawCharts);

     function drawCharts(){
      $.ajax({
        url: 'getchartdata.php',
        type: 'GET',
        dataType: 'text',
        error: function(){
          alert('fail to get data');
        },
        success: function(text){
          var obj = jQuery.parseJSON(text);

          var lang_data = new google.visualization.DataTable();
          var lib_data = new google.visualization.DataTable();
          var method_data = new google.visualization.DataTable();

          lang_data.addColumn('string', 'Language');
          lang_data.addColumn('number', 'Count');

          var rows = "";
          var strings = "";

          if(obj.language != null){
            $.each(obj.language, function(key, value){
              rows += "['"+key+"', "+value+"],";
              strings += key+": "+value+"<br />";
            })

            rows = rows.substr(0,rows.length-1);
            
            eval("lang_data.addRows(["+rows+"]);");

            var lang_options = {'title':'Language Stastics',
                              'width':400,
                              'height':300};

          var lang_chart = new google.visualization.PieChart(document.getElementById('lang_chart'));

          lang_chart.draw(lang_data, lang_options);
          $('#lang_desc').html(strings);
          }else{
            $('#lang_chart').html('No enough data :(');
          }

          lib_data.addColumn('string', 'Library');
          lib_data.addColumn('number', 'Count');

          var rows = "";
          var strings = "";

          if(obj.library != null){
            $.each(obj.library, function(key, value){
              rows += "['"+key+"', "+value+"],";
              strings += key+": "+value+"<br />";
            })

            rows = rows.substr(0,rows.length-1);
            
            eval("lib_data.addRows(["+rows+"]);");

            var lib_options = {'title':'Language Stastics',
                              'width':400,
                              'height':300};

          var lib_chart = new google.visualization.PieChart(document.getElementById('lib_chart'));

          lib_chart.draw(lib_data, lib_options);
          $('#lib_desc').html(strings);
          }else{
            $('#lib_chart').html('No enough data :(');
          }

          method_data.addColumn('string', 'Method');
          method_data.addColumn('number', 'Count');

          var rows = "";
          var strings = "";

          if(obj.method != null){
            $.each(obj.method, function(key, value){
              rows += "['"+key+"', "+value+"],";
              strings += key+": "+value+"<br />";
            })

            rows = rows.substr(0,rows.length-1);
            
            eval("method_data.addRows(["+rows+"]);");

            var method_options = {'title':'Language Stastics',
                              'width':400,
                              'height':300};

          var method_chart = new google.visualization.PieChart(document.getElementById('method_chart'));

          method_chart.draw(method_data, method_options);
          $('#method_desc').html(strings);
          }else{
            $('#method_chart').html('No enough data :(');
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
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Manage Users <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="adduser.php">Add user</a></li>
                  <li><a href="userlist.php">User list</a></li>
                  <li><a href="modifyuser.php">how to modify users?</a></li>
                </ul>
              </li>
              <li class="active"><a href="stastics.php">Stastics</a></li>
              <li><a href="contact.php">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container">
    	<div class="hero-unit">
    		<h2>Errclipse Stastics page</h2>
        you can get stastics easily<br />
        not perfect yet. make it now!<br />
        <span style="text-align:right; color:#666666; font-size:10pt;">powered by Google chart</span>
    	</div>
      <div class="row">
        <div class="span5">
          <h2>Language</h2>
          <div id="lang_chart"></div>
        </div>
        <div class="span4">
          <h2>Data</h2>
          <div id="lang_desc"></div>
        </div>
      </div>
      <div class="row">
        <div class="span5">
          <h2>Library</h2>
          <div id="lib_chart"></div>
        </div>
        <div class="span4">
          <h2>Data</h2>
          <div id="lib_desc"></div>
        </div>
      </div>
      <div class="row">
        <div class="span5">
          <h2>Methods</h2>
          <div id="method_chart"></div>
        </div>
        <div class="span4">
          <h2>Data</h2>
          <div id="method_desc"></div>
        </div>
      </div>
      
    </div>
</body>
</html>
