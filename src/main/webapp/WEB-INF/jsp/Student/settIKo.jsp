<%@ page import="no.hist.tdat.javabeans.Bruker" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="no.hist.tdat.javabeans.beanservice.BrukerService" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="col-md-4">
    <h1>Sett deg i k&oslash; </h1>

    <form action="velgPlass.htm" modelAttribute="plassering" method="post" role="form">
        <div class="form-group">
            <label for="sitteplass">Sitteplass:</label>
            <select class="form-control" name="Sitteplass" id="sitteplass" path="plassering" onchange="">
                <c:forEach items="${plassering}" var="plassering">
                    <option value="${plassering.plassering_navn}">${plassering.plassering_navn}</option>
                </c:forEach>


            </select>
        </div>


        <div class="form-group">
            <label for="bordnr">Bordnr:</label>
            <select class="form-control" name="Bord" id="bordnr" path="plassering" onchange="visBord(this.value)">
                <%--Her må det være noe som går gjennom de forskjellige bordalternativene etter hva som er blitt
                valgt på "sitteplass"--%>




            </select>
        </div>

        <div class="form-group">
            <label>Oving:</label>
            <select multiple="true" class="form-control" path="flereOvinger">
                <%-- MÅ VÆRE EN FOR-LØKKE SOM GÅR GJENNOM ALLE ØVINGER FOR HVERT ENKELT FAG--%>
                <option value="Oving1">Oving1</option>
            </select>
        </div>

        <div class="form-group">
            <label for="beskrivelse">Beskrivelse</label>
            <input class="form-control" type="text" id="beskrivelse" path="beskrivelse">
        </div>

        <div class="form-group">
            <label >Gruppe?</label>
            <select>
                <c:forEach items="${personerBeans.valgt}" var="bruker">
                    <option value="${bruker.fornavn}">${bruker.fornavn}</option>
                 </c:forEach>
            </select>
        </div>

        <button type="submit" id="leggTil" class="btn btn-md btn-primary">Legg til i k&oslash;</button>
    </form>
</div>