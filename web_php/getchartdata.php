<?php
	session_start();

	require_once("setting.php");
	mysql_connect($db_addr, $user_id,$user_password);
	mysql_select_db($schemaName);


	$ret_array["language"] = getResult("language");
	$ret_array["library"] = getResult("library");
	$ret_array["method"] = getResult("method");

	function getResult($table_name){
		$query =  "SELECT * FROM ".$table_name;
		$qr = mysql_query($query);

		$count = 0;

		while($ret = mysql_fetch_row($qr)){
			$array[$ret[0]] = $ret[2];
		}

		return $array;
	}

	echo json_encode($ret_array);
?>