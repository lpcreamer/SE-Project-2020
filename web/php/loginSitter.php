<?php
//Should work but has minor bug

 $mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );

$username = $mysqli->real_escape_string($_POST['username']);
$password = $_POST['password'];

$query = "SELECT * from Sitters where Username = '$username'";
$result = $mysqli->query($query);

if($result->num_rows == 0) {
  echo 2;
} else {
  while ($user = $result->fetch_assoc()) {
    $retrieved_password = $user['Password'];
  }

  $check = password_verify($password, $retrieved_password);
  if ($check) {
    setcookie('username', $username, time() + (86400 * 30), "/");
    setcookie('password', $password, time() + (86400 * 30), "/");
    echo 0;
  }

  else
      echo 1;

}

$mysqli->close();
