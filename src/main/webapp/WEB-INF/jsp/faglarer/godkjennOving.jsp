<%--
  Created by IntelliJ IDEA.
  User: olve og gm, ass
  Date: 13.01.14
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="col-md-6">
    <h2>Godkjenn øvinger</h2>

    <form:form>
        <p>Kommentar: ${gruppeFraKoe.getKommentar()}</p>
        <table class="table table-condensed" id="godkjennGruppe">
            <tbody>
            <c:forEach var="bruker" items="${gruppeFraKoe.getMedlemmer()}" varStatus="status">
                <tr>
                    <td><c:out value="${bruker.fornavn}"></c:out> </td>
                    <td><c:out value="${bruker.etternavn}"></c:out> </td>
                    <td><c:out value="${gruppeFraKoe.getOvingerIString()}"></c:out> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form:form>

    <div class="btn-group">
        <button type="godkjenn" class="btn btn-success" name="godkjennKnapp"><i class="glyphicon glyphicon-ok"></i> Godkjenn</button>

        <button type="leggtil" class="btn btn-primary" name="leggTilStundeterKnapp"><i class="glyphicon glyphicon-user"></i> Legg til studenter
        </button>
        <button type="endreØvinger" class="btn btn-info" name="endreOvingerKnapp"data-toggle="modal" data-target="#velgøvingFellesModal"><i
                class="glyphicon glyphicon-plus"></i> Endre øvinger
        </button>
    </div>


</div>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-1.10.2.js"/>"></script>
