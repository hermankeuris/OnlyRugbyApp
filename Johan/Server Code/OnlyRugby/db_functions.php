<?php
	/**
	 * DB operations functions
	 */
	class DB_Functions {
	 
		private $db;
	 
		//put your code here
		// constructor
		function __construct() {
			include_once './db_connect.php';
			// connecting to database
			$this->db = new DB_Connect();
			$this->db->connect();
		}
	 
		// destructor
		function __destruct() {
	 
		}
	 
		/**
		 * Storing new user
		 * returns user details
		 */
		public function storeUser($User) {
			// Insert user into database
			$result = mysql_query("INSERT INTO user(Name) VALUES('$User')");
	 
			if ($result) {
				return true;
			} else {            
					// For other errors
					return false;
			}
		}
		 /**
		 * Getting all users
		 */
		public function getAllUsers() {
			$result = mysql_query("select * FROM user");
			return $result;
		}
		/**
		 * Get Yet to Sync row Count
		 */
		public function getUnSyncRowCount() {
			$result = mysql_query("SELECT * FROM user WHERE syncsts = FALSE");
			return $result;
		}
		/**
		 * Update Sync status of rows
		 */
		public function updateSyncSts($id, $sts){
			$result = mysql_query("UPDATE user SET syncsts = $sts WHERE Id = $id");
			return $result;
		}
		/**
		 * Find if user exists and password correct
		 */
		public function getLoginInfo($usrname, $password)
		{
			$result = mysql_query("SELECT * FROM user WHERE username = $usrname AND password = $password");
			return $result;
		}
	}
 
?>