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
            <form:select class="form-control" name="Sitteplass" id="sitteplass" path="rom" onchange="visBord()">
                <option id="tom" value="tom">Velg Sitteplass</option>
                <%--<c:forEach items="${plassering}" var="plassering">
                    <form:option onclick="visBilde(this)" id="${plassering.plassering_navn}" value="${plassering.plassering_navn}">${plassering.plassering_navn}</form:option>
                </c:forEach>--%>
            </form:select>
        </div>


        <div class="form-group">
            <label for="bordnr">Bordnr:</label>
            <form:select class="form-control" name="Bord" id="bordnr" path="bordnr" disabled="true">
                <%--Her må det være noe som går gjennom de forskjellige bordalternativene etter hva som er blitt
                valgt på "sitteplass"--%>
                <%--
                <c:forEacb items="1" var="bordNr" end="${plassering.ant_bord}">
                    <form:option>bordNr</form:option>
                </c:forEach>
                --%>
            </form:select>
        </div>

        <div class="form-group">
            <label for="oving">Oving:</label>
            <form:select id="oving" multiple="true" class="form-control" path="oving" disabled="true">
                <%--
                <c:forEach items="delEmne.studentovinger" var="ovinger">
                    <form:option id="${ovinger.ovingnr}" value="${ovinger.ovingnr}">${ovinger.ovingnr}</form:option>
                </c:forEach>
                --%>
            </form:select>
        </div>

        <div class="form-group">
            <label for="beskrivelse">Beskrivelse</label>
            <form:textarea class="form-control" type="text" id="beskrivelse" path="beskrivelse"/>
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
<img src = "<c:url value="/resources/img/lab.png"/>" style="padding:1px;border:thin solid black;" id = "img"/>
                                    <%-- Svart ramme rundt bildet --%>
                                    <%-- style="float:right"   align="right" --%>
<script>
    function visBilde(){
        var valg = document.getElementById('sitteplass');



        if(valg.value == "labben"){
            document.getElementById("img").src = "resources/img/lab.png";

        } else if(valg.value == "polet"){
            document.getElementById("img").src = "resources/img/pol.png";

        } else if(valg.value == "nettlab"){
            document.getElementById("img").src = "resources/img/nettlab.png";

        }else{
            document.getElementById("img").src = "resources/img/lab.png";
        }
    }
    function visBord(){
        if(document.getElementById("sitteplass").value!="tom"){
            document.getElementById("bordnr").disabled = false;
        }else{
            document.getElementById("bordnr").disabled = true;
        }
    }
</script>

            <%--  <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">  --%>
