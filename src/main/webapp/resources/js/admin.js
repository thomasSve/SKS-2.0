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