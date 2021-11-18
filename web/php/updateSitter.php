<?php

$name = $_POST['name'];
$email = $_POST['email'];
$username = $_POST['username'];
$phone_number = $_POST['phone_number'];
$zip = $_POST['zip'];
$state = $_POST['state'];
$street = $_POST['street'];
$city = $_POST['city'];


$mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
if($mysqli->connect_error) {
 exit('Could not connect');
}

// checking if username already exists in db
$query = "UPDATE `Sitters` SET `Name` = '$name', `Email` = '$email', `Phone Number` = '$phone_number', `State` = '$state', `Street` = '$street', `City` = '$city', `Zip Code` = '$zip' WHERE `Username` = '$username'";
$result = $mysqli->query($query);

if ($result == 1)
    echo 0;
else
    echo 1;

$mysqli->close();
