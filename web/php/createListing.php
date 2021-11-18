<?php
//Need to add sessions to store username in the listing
//The html for this needs a form to take in the following info to be stored
 $date = $_POST['date'];
 $start_time = $_POST['start_time'];
 $duration = $_POST['duration'];
 $description = $_POST['description'];
 $username = $_POST['username'];

 print_r($_POST);

 $mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
 if($mysqli->connect_error) {
   exit('Could not connect');
 }

 $sql = "INSERT INTO Listings(Date, `Start Time`, Duration,  `Owner Username`, Description, Booked) VALUES('$date', '$start_time', '$duration', '$username', '$description', 0)";
 $result = $mysqli->query( $sql );

 if ($result == 1)
 {
     echo 0;
 }
 else
     echo 1;
?>
