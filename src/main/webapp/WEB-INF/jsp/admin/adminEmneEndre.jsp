<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 23.01.14
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-6">
    <h3>Endre Emne (${redigerEmne.emneNavn})</h3>
    <form:form method="POST" modelAttribute="emne" action="redigerEmneLagre.htm">
        <p style="color: red"><strong>${melding}</strong></p>
        <p style="color: green"><strong>${Vellykket}</strong></p>

        <div class="form-group">
            <label for="endrekode">Emnekode</label>
            <form:input value="${redigerEmne.emneKode}" path="emneKode" id="endrekode" class="form-control"/>
            <errors path="emneKode"/>
        </div>
        <div class="form-group">
            <label for="endreNavn">Emnenavn</label>
            <form:input value="${redigerEmne.emneNavn}" path="emneNavn" id="endreNavn" class="form-control"/>
            <errors path="emneNavn"/>
        </div>
        <input type="hidden" name="redigerEmneKode" value="${redigerEmne.emneKode}">

        <div class="form-group">
            <label>Emneansvarlige</label>
            <c:forEach items="${redigerEmne.foreleserListe}" var="foreleser">
                <input value="${foreleser.etternavn}, ${foreleser.fornavn}" id="endreNavn" class="form-control"/>
            </c:forEach>
        </div>
        <div class="modal-footer">
            <button type="button" id="${redigerEmne.emneKode}" onclick="adminEmneTilbake(this.id)" class="btn btn-danger col-md-5" data-dismiss="modal">Tilbake</button>
            <input type="submit" id="endreEmne" value="Endre emne" style="float: right" class="btn btn-primary col-md-5"/>
        </div>
    </form:form>
</div>
<script src="<c:url value="/resources/js/admin.js"/>"></script>
