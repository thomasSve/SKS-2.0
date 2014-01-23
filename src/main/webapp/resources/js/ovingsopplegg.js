function settMaxKrav() {
    document.getElementById("ant").max = document.getElementById("tot").value;
}
function settMaxKrav2() {
    var valg = document.getElementById("ovingValg").options;
    var antValgt = 0;
    for (var i = 0; i < valg.length; i++) {
        if (valg[i].selected) {
            antValgt++;
        }
    }
    document.getElementById("antGodkjente").max = antValgt;
    document.getElementById("antGodkjente").value = antValgt;
}

function utvid() {
    if (document.getElementById("visMerAkt").checked) {
        document.getElementById("visMer").style.display = 'block';
        settAntOvingerAvansert();
    }
    else {
        document.getElementById("visMer").style.display = 'none';
    }
}
function leggTilRegel() {
    var plass = document.getElementById("regler");
    if (plass.innerHTML === "<i>ingen</i>") {
        plass.innerHTML = "";
    }

    var txt = "";
    var valg = document.getElementById('ovingValg');
    for (var i = 0; i < valg.options.length; i++) {
        if (valg.options[i].selected) {
            txt += valg.options[i];
        }
    }

    plass.innerHTML += "HEI";
}

function settAntOvingerAvansert() {
    var liste = document.getElementById("ovingValg");
    nullstillListe(liste);
    var ant = document.getElementById("tot").value;
    document.getElementById("antGodkjente").value = 1;

    for (var i = 1; i < ((ant + 1) / 10); i++) {
        var opt = document.createElement('option');
        opt.value = "oving" + i;
        opt.text = "?ving " + i;
        if (i === 1) {
            opt.selected = "selected";
        }
        liste.add(opt);
    }
}

function nullstillListe(liste) {
    for (var i = liste.options.length - 1; i >= 0; i--) {
        liste.remove(i);
    }
}