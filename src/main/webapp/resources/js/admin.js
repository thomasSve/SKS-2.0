/*
    AdminFag
 */

function adminFagOperasjon(valgt) {
    if (document.getElementById("emnevalg").value !== "ingen") {
        document.getElementById("operasjonstekst").innerHTML = "Endre " + valgt + "";
    }
    else {
        document.getElementById("operasjonstekst").innerHTML = "Lag nytt fag";
    }
}
function endreEmne(valgt) {

    if (document.getElementsByTagName("edit").value !== "ingen") {
        document.getElementById("operasjonstekst").innerHTML = "Endre bruker";
        document.getElementById("leggtilknapp").innerHTML = "Lagre endring";

    }
    else {
        document.getElementById("operasjonstekst").innerHTML = "Legg til bruker";
        document.getElementById("leggtilknapp").innerHTML = "Legg til";
    }
}


function slettEmne(valgt)
{
    if(typeof(valgt) == "object"){
        $(valgt).closest("tr").remove();
        alert("Slett emne?")
    } else {
        return false;
    }
}


function slettEmneFraKnapp(emnekode) {
    var svar = confirm("Slette emnet " + emnekode + "?");
    if(svar){
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5

            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {

        }
        xmlhttp.open("POST", "/slettEmne.htm", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("emneIndex=" + emnekode);

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        }

        window.location = "searchFag.htm"
    }
}

function redigerEmneFraKnapp(emnekode) {

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/redigerEmne.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("emneIndex=" + emnekode);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }

    window.location = "adminEmneEndre.htm";

}

function adminEmneTilbake(emnekode) {
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/searchFag.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("emneIndex=" + emnekode);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    window.location = "searchFag.htm"
}


/*
    AdminBrukere
 */
function handleFileSelect(hendelse) {
    var minFil = hendelse.target.files[0];
    infoStreng = '<ul><li>Navn: ' + minFil.name + '</li>' +
        '<li>St&oslash;rrelse: ' + minFil.size + 'bytes</li>' +
        '<li>Type: ' + minFil.type + '</li></ul>';

    document.getElementById('filInfo').innerHTML = infoStreng;
}
/*
 <fieldset disabled><fieldset disabled>
 <fieldset disabled>
 <fieldset disabled>
 <fieldset disabled>

 */
function endreBruker(valgt) {

    if (document.getElementsByTagName("edit").value !== "ingen") {
        document.getElementById("operasjonstekst").innerHTML = "Endre bruker";
        document.getElementById("leggtilknapp").innerHTML = "Lagre endring";

    }
    else {
        document.getElementById("operasjonstekst").innerHTML = "Legg til bruker";
        document.getElementById("leggtilknapp").innerHTML = "Legg til";
    }
}


function slettBruker(valgt)
{
    if(typeof(valgt) == "object"){
        $(valgt).closest("tr").remove();
        alert("Slett bruker?")
    } else {
        return false;
    }
}


function slettBrukerFraKnapp(mail) {

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
    }
else {// code for IE6, IE5

    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
xmlhttp.onreadystatechange = function () {

    }
xmlhttp.open("POST", "/slettBruker.htm", true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
xmlhttp.send("brukerIndex=" + mail);

if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }

    window.location = "search.htm"


}

function redigerBrukerFraKnapp(mail) {

    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/redigerBruker.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("brukerIndex=" + mail);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }

    window.location = "adminBrukereEndre.htm";

}

function adminBrukereTilbake(mail) {
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
    xmlhttp.send("brukerIndex=" + mail);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }
    window.location = "search.htm"
}

function velgBrukerL(mail) {
    alert(mail);
    if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else {// code for IE6, IE5

        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {

    }
    xmlhttp.open("POST", "/velgBruker.htm", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("brukerIndex=" + mail);
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
    }

    window.location = "velgBruker.htm";

}
