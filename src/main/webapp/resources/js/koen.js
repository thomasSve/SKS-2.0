function hentKoeStudent(koe_id){

        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function(){
            if (xmlhttp.readyState==4 && xmlhttp.status==200){
                document.getElementById("page-wrapper").innerHTML=xmlhttp.responseText;
            }

        }
        xmlhttp.open("POST","/koOversikt.htm",true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("koeid=" + koe_id);
}

function setKoeId(num){
    document.getElementById('hiddenKoe').value =num;
}

var clicked;
var emnenr;
function mysubmit() {
    var koeIdField = document.getElementById("hiddenKoe");
    var emneNavnField = document.getElementById("hiddenEmneNavn");
    koeIdField.value=clicked;
    emneNavnField.value=emnenr;
    }

function startStoppKoe(koe_id){
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/StartStoppKoe.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("KoeIndex=" + koe_id);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    oppdaterKoe();
}
function settIKo(koe_id){
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/settIKo.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("KoeIndex=" + koe_id);

    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
}
function StillIKo(emne_id){
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "StillIKo", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("KoeIndex=" + emne_id);

    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    oppdaterKoe();
}
function oppdaterKoe(){
    //TODO TED
}
//Fjernet visadmin, statuskoe og var statusknapp.





//Velg gruppe fra kø
function velgGruppeFraKoe(mailLeder) {
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/search.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("brukerLederIndex=" + mail);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    window.location = "godkjennOving.htm"
}


