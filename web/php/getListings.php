<?php
$mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );
if($mysqli->connect_error) {
  exit('Could not connect');
}
// checking if username already exists in db
$query = "SELECT * from Listings";
$result = $mysqli->query($query);

echo "[";
$cnt = 0;
while($row = $result->fetch_assoc()) {
  // checking if username already exists in db
  if($cnt != 0){
    echo ",";
  } else {
    $cnt = $cnt + 1;
  }
  $usr = $row['Owner Username'];
  $query2 = "SELECT * from Owner where Username = '$usr'";
  $result2 = $mysqli->query($query2);
  $owner = $result2->fetch_assoc();
  $row['owner'] = $owner;
  echo json_encode($row);
}
echo "]";
