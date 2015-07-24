<?php
/**
 * Creates Unsynced rows data as JSON
 */
    include_once 'db_functions.php';
    $db = new DB_Functions();
    $users = $db->getUnSyncRowCount();
    $a = array();
    $b = array();
    if ($users != false){
        while ($row = mysql_fetch_array($users)) {        
            $b["userId"] = $row["Id"];
            $b["userName"] = $row["Name"];
            array_push($a,$b);
        }
        echo json_encode($a);
    }
?>