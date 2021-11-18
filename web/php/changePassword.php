<?php
 $username = $_POST['username'];
 $oldPassword = $_POST['OldPassword'];
 $newPassword = $_POST['NewPassword'];

 $mysqli = new mysqli( "cs-database.cs.loyola.edu", "USERNAME", "PASSWORD", "DATABASE" );


 $username = $mysqli->real_escape_string($username);
 $newPassword = $mysqli->real_escape_string($newPassword);

 $sql = "SELECT password FROM Sitters WHERE Username = '$username' UNION SELECT password FROM Owner WHERE Username = '$username'";

 $result = $mysqli->query( $sql );
 $row = $result->fetch_assoc();
 $saved_pass = $row['Password'];

 if(password_verify($oldPassword, $saved_pass))
 {
   $options = ['cost' => 12];
   $encryptedPassword = password_hash($newPassword, PASSWORD_BCRYPT, $options);
   $sql = "update users set encryptedPassword = '$encryptedPassword' where username = '$username'";
   $result = $mysqli->query( $sql );
   echo "Your password has been updated.";
 }
 else
 {
   echo "Password or Username Not Recognized";
 }
?>