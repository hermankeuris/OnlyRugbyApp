<?php

	include_once 'db_functions.php';
    $db = new DB_Functions();
    $finduser = $db->getLoginInfo($_POST['usrname'],$_POST['password']);
	if($finduser != 0)
	{
		$row = mysql_fetch_array($finduser);
		$a = array($row['username'], $row['password']);
		echo json_encode($a);
	}
?>