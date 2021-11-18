<?php
//Should work but has minor bug
 $mysqli = new mysqli( "cs-database.cs.loyola.edu", "lpcreamer", "1760156", "bmls" );


 $sql = "SELECT * FROM Owners";
 // $sql = "ALTER TABLE Sitters ADD COLUMN pet_care_instructions VARCHAR(255) NOT NULL";

 $result = $mysqli->query( $sql );
 $row = $result->fetch_assoc();

 $out = "";
  while ($row = $result->fetch_assoc())
  {
      print_r($row);
  }
