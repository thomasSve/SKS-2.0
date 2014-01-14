function endrePassord(){
    var nyttPassord = document.getElementById("nyttPassord");
    var bPassord = document.getElementById("bNyttPassord");
    if(nyttPassord.value != bPassord.value){
        alert("Nytt passord og bekreft passord stemmer ikke overens");
        nyttPassord.focus();
        return false;
    }
    return true;
}