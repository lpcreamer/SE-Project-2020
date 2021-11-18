<?php
 $email = $_POST['email'];
 $username = $_POST['username'];
 $password = $_POST['password'];
 $mysqli = new mysqli( "cs-database.cs.loyola.edu", "USERNAME", "PASSWORD", "DATABASE" );

 
 $email = $mysqli->real_escape_string($email);
 $username = $mysqli->real_escape_string($username);
 $password = $mysqli->real_escape_string($password);

 $options = ['cost' => 12];
 $encryptedPassword = password_hash($password, PASSWORD_BCRYPT, $options);

 $sql = "insert into users values('$username', '$email', '$encryptedPassword')";
 
 $result = $mysqli->query( $sql );
?>