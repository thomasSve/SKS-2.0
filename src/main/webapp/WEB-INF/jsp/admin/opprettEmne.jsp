<%--
  Created by IntelliJ IDEA.
  User: Eirik
  Date: 20.01.14
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="col-md-6">
<form:form method="POST" modelAttribute="emne" action="lagEmne">
    <h2>Opprett emne</h2>

    <p style="color: red"><strong>${emnefeil}</strong></p>
    <p style="color: red"><strong>${emneSQLfeil}</strong></p>

    <div class="form-group">
        <label for="emneNavn">Emnenavn:</label>
        <form:input path="emneNavn" id="emneNavn" class="form-control" required="true"/>
    </div>
    <div class="form-group">
        <label for="emneKode">Emnekode:</label>
        <form:input path="emneKode" id="emneKode" class="form-control" required="true"/>
    </div>

    <input type="submit" class="btn btn-primary btn-block" value="Legg til"/>
    <br>
</form:form>

<form:form class="søkbar" role="search" modelAttribute="personerBeans" action="sokBoks" method="POST">
    <h3>Legg til lærer</h3>
    <div class="input-group">

        <input type="text" class="form-control" placeholder="S&oslash;k" name="srch-term" id="srch-term"/>

        <div class="input-group-btn">
            <button class="btn btn-success" type="submit"><i class="glyphicon glyphicon-search"></i>
            </button>
        </div>

    </div>
    <div class="span5">
        <table class="table table-condensed table-hover" id="minTable">
            <thead>
            <tr>
                <th class="header">Fornavn</th>
                <th class="header">Etternavn</th>
                <th class="header">Epost</th>
                <th class="header col-sm-1">Velg</th>
                <th class="header col-sm-1"></th>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="bruker" items="${personerBeans.valgt}" varStatus="status">
                <tr>
                    <td><c:out value="${bruker.fornavn}"/></td>
                    <td><c:out value="${bruker.etternavn}"/></td>
                    <td><c:out value="${bruker.mail}"/></td>
                    <td>
                            <button type="edit" class="btn btn-success btn-sm" data-toggle="modal"
                                    id="${bruker.mail}" onclick="velgBrukerL(this.id)"title="Velg">
                                Velg
                            </button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <input type="hidden" name="tab" value="endre">
    </div>
</form:form>
    <script src="<c:url value="/resources/js/admin.js"/>"></script>
