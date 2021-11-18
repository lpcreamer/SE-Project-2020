<?php

$id = $_POST['id'];

$mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
if($mysqli->connect_error) {
 exit('Could not connect');
}

// checking if username already exists in db
$query = "UPDATE `Listings` SET `Sitter Username` = NULL, Booked = 0 WHERE id = '$id'";

if ($mysqli->query($query) == 1) {
  echo 0;
} else {
  echo 1;
}


$mysqli->close();
