 <?php

$name = $_POST['name'];
$username = $_POST['username'];
$email = $_POST['email'];
$password = $_POST['password'];
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
$query = "SELECT * from Sitters where Username = '$username'";
$username_result = $mysqli->query($query);


if ($username_result->num_rows > 0)
    echo 2;

// user does not yet exist in db
else
{
    $options = ['cost' => 12];
    $encrypted_password = password_hash($password, PASSWORD_BCRYPT, $options);
    $query = "INSERT into `Sitters` Values('$name', '$username', '$encrypted_password', '$email', $phone_number, '$state', '$street', '$zip', '$city')";

    if ($mysqli->query($query) == 1)
    {
        echo 0;
        setcookie('username', $username, time() + (86400 * 30), "/");
        setcookie('password', $password, time() + (86400 * 30), "/");
    }
    else
        echo 1;
}

$mysqli->close();
