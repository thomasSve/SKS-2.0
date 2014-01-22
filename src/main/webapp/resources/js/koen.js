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

var delemnenr;      //Index i bruker-objektet, IKKE i DB
var emnenr;         //Index i bruker-objektet, IKKE i DB
function mysubmit() {
    var delemneNrField = document.getElementById("delemneNr");  //Index i bruker-objektet, IKKE i DB
    var emneNrField = document.getElementById("emneNr");        //Index i bruker-objektet, IKKE i DB
    delemneNrField.value=delemnenr;
    emneNrField.value=emnenr;
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
    alert(emne_id);
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
$(function (){
    $(".faarHjelpKnapp").popover(
        { title: 'Look! A bird!', content: "birdie", html:true }
    );
});
//Fjernet visadmin, statuskoe og var statusknapp.





//Velg gruppe fra kø
function velgGruppeFraKoe(gruppe) {
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/hentUtKoGruppe.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("gruppeIndexFraKoe=" + gruppe);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    window.location = "godkjennOving.htm"
}


