<?php session_start(); 

	require_once("setting.php");
	$asd = mysql_connect($db_addr, $user_id,$user_password);
	$res = mysql_select_db($schemaName);

	$userId = $_POST["user_id"];
	$userEmail = $_POST["user_email"];

	if(trim($userId) == "" || trim($userEmail) == "")
		{
			echo "error";
			exit(0);
		}


	$ret = mysql_query("select * from user WHERE name=\"".$userId."\" OR email=\"".$userEmail."\"");

	$num = mysql_num_rows($ret);

	if($num == 0){
		$query = "insert into user values(0, \"".$userId."\", \"".$userEmail."\")";
		mysql_query($query);
		echo "allow";
	}else{
		$ret = mysql_fetch_row($ret);
		if($userId == $ret[1])
			echo "id";
		else
			echo "email";
	}
?>