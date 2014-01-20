<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Endre student</h1>
<form:form action="videresend" method="post">
    <input type="submit" class="btn btn-warning" value="Tilbake" name="tilbake"/>
</form:form>
<table class="table table-condensed table-hover" id="minTable">
    <thead>
    <tr>
        <th class="header">Fornavn</th>
        <th class="header">Etternavn</th>
        <th class="header">Epost</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td><c:out value="${sessionScope.valgtPerson.fornavn}"/></td>
        <td><c:out value="${sessionScope.valgtPerson.etternavn}"/></td>
        <td><c:out value="${sessionScope.valgtPerson.mail}"/></td>
    </tr>
    </tbody>
</table>

<hr>
<div class="text-success">
    <h3><c:out value="${forrigeOp}"/></h3>
</div>
<hr>

<form:form action="utførOperasjon" method="post">
    <div class="form-group">
        <div class="form-group">
            <label for="emner">Gjør til studentassistent i valgte fag:</label>
            <select id="emner" name="emner" class="form-control">
                <c:forEach var="emne" items="${sessionScope.valgtPerson.emne}" varStatus="status">
                    <option value="${emne.emneKode}"><c:out value="${emne.emneKode}"/></option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="btn btn-success" value="Lagre" name="lagre"/>
    </div>


    <hr>


    <div class="form-group">
        <div class="form-group">
            <label for="emner2">Fjern tilgang til valgte fag:</label>
            <select id="emner2" name="emner2" class="form-control">
                <c:forEach var="emne" items="${sessionScope.valgtPerson.emne}" varStatus="status">
                    <option value="${emne.emneKode}"><c:out value="${emne.emneKode}"/></option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="btn btn-danger" value="Fjern" name="fjern"/>
    </div>


    <hr>


    <div class="form-group">
        <div class="form-group">
            <label for="emner3">Gi tilgang til valgte fag:</label>
            <select id="emner3" name="emner3" class="form-control">
                <c:forEach var="emne" items="${sessionScope.emnerUtenTilgang}" varStatus="status">
                    <option value="${emne.emneKode}"><c:out value="${emne.emneKode}"/></option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" class="btn btn-success" value="Legg til" name="leggTil"/>
    </div>
</form:form>


