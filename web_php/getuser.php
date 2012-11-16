<?php session_start(); 

	require_once("setting.php");
	$asd = mysql_connect($db_addr, $user_id,$user_password);
	$res = mysql_select_db($schemaName);


	$cond = "";
	$hasCondition = true;

	if($_POST["cond"] == ""){
		$hasCondition = false;
	}else{
		$cond = " WHERE name LIKE \"%".$_POST["cond"]."%\" OR email LIKE \"%".$_POST["cond"]."%\" ";
	}

	$ret = mysql_query("select * from user".$cond);

	$num = mysql_num_rows($ret);

	if($num == 0){
		echo "no_results";
	}else{
		$count = 0;
		while($res = mysql_fetch_row($ret)){
			$arr[$count++] = $res;
		}
		echo json_encode($arr);
	}
?>