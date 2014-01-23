<?php
$to = $epost_til;
$subject="Ditt passord hos oss";
$header="from: astadsve.no <no-reply@astadsve.no>";

$messages= "Ditt passord og brukarnamn for Ã¥ logge inn: \r\n";
$messages.="\r\n";
$messages.="$brukarnamn_til \r\n";
$messages.="$passord_til \r\n";
$sentmail = mail($to,$subject,$messages,$header);
?>