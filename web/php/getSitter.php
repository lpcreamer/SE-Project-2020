<?php

$username = $_GET['username'];

$mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
if($mysqli->connect_error) {
  exit('Could not connect');
}

// checking if username already exists in db
$query = "SELECT * from Sitters where Username = '$username'";
$result = $mysqli->query($query);

echo json_encode($result->fetch_assoc());

$mysqli->close();
