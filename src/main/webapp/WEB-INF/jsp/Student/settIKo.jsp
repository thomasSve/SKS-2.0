<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="no.hist.tdat.javabeans.beanservice.BrukerService" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="col-md-4">
    <h1>Sett deg i k&oslash; </h1>

    <form:form action="velgPlass.htm" modelAttribute="koegrupper" method="post">
        <div class="form-group">
            <label for="sitteplass">Sitteplass:</label>
            <form:select class="form-control" name="Sitteplass" id="sitteplass" path="sitteplass" onchange="visBilde()">
                <option id="tom" value="tom">Velg Sitteplass</option>
                <c:forEach items="${plassering}" var="plassering">
                    <form:option onclick="visBilde(this)" id="${plassering.plassering_navn}"
                                 value="${plassering.plassering_navn}">${plassering.plassering_navn}</form:option>
                </c:forEach>
            </form:select>
        </div>


        <div class="form-group">
            <label for="bordnr">Bordnr:</label>
            <form:select class="form-control" name="Bord" id="bordnr" path="bordnr" disabled="true">
                <%--Her må det være noe som går gjennom de forskjellige bordalternativene etter hva som er blitt
                valgt på "sitteplass"--%>

                <c:forEach items="1" var="bordNr" end="${plassering.ant_bord}">
                    <form:option value="bordNr">bordNr</form:option>
                </c:forEach>

            </form:select>
        </div>

        <div class="form-group">
            <label for="oving">Oving:</label>
            <form:select id="oving" multiple="true" class="form-control" path="ovinger" disabled="true">
                <c:forEach items="delEmne.studentovinger" var="ovinger">
                    <form:option id="${ovinger.ovingnr}" value="${ovinger.ovingnr}">${ovinger.ovingnr}</form:option>
                </c:forEach>
            </form:select>
        </div>

        <div class="form-group">
            <label for="kommentar">Kommentar</label>
            <form:textarea class="form-control" type="text" id="kommentar" path="kommentar"/>
        </div>

        <div class="form-group">
            <label for="gruppe">Gruppe?</label>
            <form:select id="gruppe" path="medlemmer">
                <c:forEach items="${personerBeans.valgt}" var="bruker">
                    <form:option value="${bruker.fornavn}">${bruker.fornavn}</form:option>
                </c:forEach>
            </form:select>
        </div>

        <input type="submit" id="leggTil" class="btn btn-md btn-primary" value="Legg til i k&oslash;">
    </form:form>
</div>
<img src="<c:url value="/resources/img/lab.png"/>" style="padding:1px;border:thin solid black;" id="img"/>
<%-- Svart ramme rundt bildet --%>
<%-- style="float:right"   align="right" --%>
<script>
    function visBilde() {
        var valg = document.getElementById('sitteplass');

        switch (valg.value) {
            case ("labben"):
                document.getElementById("img").src = "resources/img/lab.png";
                break;
            case "polet":
                document.getElementById("img").src = "resources/img/pol.png";
                break;
            case  "nettlab":
                document.getElementById("img").src = "resources/img/nettlab.png";
                break;
        }
        if (document.getElementById("sitteplass").value != "tom") {
            document.getElementById("bordnr").disabled = false;
        } else {
            document.getElementById("bordnr").disabled = true;
        }

    }
</script>

<%--  <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">  --%>
