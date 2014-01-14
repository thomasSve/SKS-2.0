var statusKnapp = document.getElementById("koStatus");

function statusKoe() {
    if (statusKnapp.value === "start") {
        statusKnapp.className = "btn btn-sm btn-success";
        statusKnapp.innerHTML = "Start K&oslash;en";
        statusKnapp.value = "stopp";
    } else {
        statusKnapp.className = "btn btn-sm btn-danger";
        statusKnapp.innerHTML = "Stopp K&oslash;en";
        statusKnapp.value = "start";
    }
}
function visAdmin(){

}