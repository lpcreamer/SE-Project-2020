<?php
//Need to add sessions to store username in the listing
//The html for this needs a form to take in the following info to be stored
 $listing = $_POST['id'];

 print_r($_POST);

 $mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
 if($mysqli->connect_error) {
   exit('Could not connect');
 }

 $sql = "DELETE FROM Listings WHERE id = '$listing'";
 $result = $mysqli->query( $sql );

 if ($result == 1)
 {
     echo 0;
 }
 else
     echo 1;
?>
