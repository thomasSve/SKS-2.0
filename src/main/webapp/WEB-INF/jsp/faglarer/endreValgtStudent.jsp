<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Endre student</h1>
    <table>
    <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">
        <tr>
            <td><c:out value="${bruker.fornavn}"/></td>
            <td><c:out value="${bruker.etternavn}"/></td>
            <td><c:out value="${bruker.mail}"/></td>


        </tr>
    </c:forEach>
    </table>
<form:form action="sveitserost" method="post" modelAttribute="valgtPerson">
    <select class="form-control" name="opValg">
        <option value="studass">Gjør til studentassistent</option>
        <option value="leggInnFag">Legg til nytt fag</option>
        <option value="fjernFag">Fjern fag</option>
    </select>

    <select id="fagValg" class="form-control">
        <c:forEach var="i" items="${personerBeans.fellesEmner}">
            <option value="<c:out value="
            ${i}"/>"><c:out value="${i}"/></option>
        </c:forEach>
    </select>

    <input type="submit" value="Lagre">
</form:form>