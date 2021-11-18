<?php

$name = $_POST['name'];
$email = $_POST['email'];
$username = $_POST['username'];
$phone_number = $_POST['phone_number'];
$pet_type = $_POST['pet_type'];
$pet_name = $_POST['pet_name'];
$pet_breed = $_POST['pet_breed'];
$pet_weight = $_POST['pet_weight'];
$pet_care_instructions = $_POST['pet_care_instructions'];
$zip = $_POST['zip'];
$state = $_POST['state'];
$street = $_POST['street'];
$city = $_POST['city'];


$mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
if($mysqli->connect_error) {
 exit('Could not connect');
}

// checking if username already exists in db
$query = "UPDATE Owner SET Name = '$name', Email = '$email', `Phone Number` = '$phone_number', pet_type = '$pet_type', pet_breed = '$pet_breed', pet_name = '$pet_name', pet_care_instructions = '$pet_care_instructions', pet_weight = '$pet_weight', State = '$state', Street = '$street', City = '$city', `Zip Code` = '$zip' WHERE Username = '$username'";
$result = $mysqli->query($query);

if ($result == 1)
    echo 0;
else
    echo 1;

$mysqli->close();
