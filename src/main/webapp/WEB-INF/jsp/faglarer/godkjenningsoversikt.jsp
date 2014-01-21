<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function hentRettEmne(emnekode) {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {

        }
        xmlhttp.open("POST", "hentRiktigEmne", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("emne=" + emnekode);

        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //document.getElementById("calendar").innerHTML=xmlhttp.responseText;
            alert("jara");
        }
    }
</script>

<label for="emne">Velg emne:</label>
<select id="emne" name="emne" class="form-control" onchange="hentRettEmne(this.value)">
    <option value="ingen"><i>Ingen valgt</i></option>
    <c:forEach var="emne" items="${sessionScope.innloggetBruker.emne}">
        <c:forEach var="delemne" items="${emne.delemner}">
            <option value="${delemne.delEmneNavn}"><c:out value="${delemne.delEmneNavn}"/></option>
        </c:forEach>
    </c:forEach>
</select>

<div class="text-info">
<h2><c:out value="${sessionScope.valgtEmne.delEmneNavn}"/></h2>
</div>