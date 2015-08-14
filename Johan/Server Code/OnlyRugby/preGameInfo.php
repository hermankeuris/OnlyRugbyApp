<?php
/**
 * This is a temporary file for testing execution
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

<?php
$servername = "localhost:87";
$username = "admin";
$password = "admin";
$dbname = "onlyRugbyDump";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "SELECT `name`, `surname`, `curr_position` FROM `players` WHERE `team_id`='1'";
$result = $conn->query($sql);
$a = array();
$b = array();

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
		$b["name"] = $row["name"];
		$b["surname"] = $row["surname"];
		$b["curr_position"] = $row["curr_position"];
		array_push($a,$b);
	}
	echo json_encode($a);
}
else {
    echo "0";
}
$conn->close();
?>