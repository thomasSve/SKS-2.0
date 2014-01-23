function emnekodeFraMenu(kode) {

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/emneOversikt.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("emnekodeFraNav=" + kode);

    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    window.location ="emneOversikt.htm";
}