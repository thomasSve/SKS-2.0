<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Endre student</h1>
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
        <td>må kanskje settes med innerhtml elns?</td>
        <td><c:out value="${sessionScope.valgtPerson.etternavn}"/></td>
        <td><c:out value="${sessionScope.personerBeans}"/></td>
    </tr>
    </tbody>
</table>


<hr>

<form:form action="gjorTilStudass" method="post">
    <div class="form-group">
        <div class="form-group">
            <label for="emner">Gjør til studentassistent i valgte fag:</label>
            <select id="emner" class="form-control">
                <option value="hei1">1</option>
                <option value="hei2">2</option>

                <c:forEach var="emne" items="${sessionScope.personerBeans.valgtBruker.emne}" varStatus="status">
                    <option value="${emne.emneKode}"><c:out value="${emne.emneKode}"/></option>
                </c:forEach>

            </select>
        </div>
        <input type="submit" value="Lagre"/>
    </div>
</form:form>

<hr>

<form:form action="fjernFag" method="post">
    <div class="form-group">
        <div class="form-group">
            <label for="emner2">Fjern tilgang til valgte fag:</label>
            <select id="emner2" class="form-control">

            </select>
        </div>
        <input type="submit" value="Lagre"/>
    </div>
</form:form>

<hr>


<form:form action="leggTilFag" method="post">
    <div class="form-group">
        <div class="form-group">
            <label for="emner3">Gi tilgang til valgte fag:</label>
            <select id="emner3" class="form-control">

            </select>
        </div>
        <input type="submit" value="Lagre"/>
    </div>
</form:form>


