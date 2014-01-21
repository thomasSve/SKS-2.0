<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 13.01.14
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6">
<h2>Godkjenn øvinger</h2>
    <div class="form-group">
        <label for="fornavn">Mail</label>

        <form:input path="mail" id="mail" class="form-control"/>

        <form:errors path="mail"/>
    </div>
    <div class="form-group">
        <label for="koeID">koe_id</label>

        <form:input path="koeID" id="koeID" class="form-control"/>

        <form:errors path="koeID"/>
    </div>

<table class="table table-condensed" id="godkjennGruppe">
    <tbody>
    <c:forEach var="koeBruker" items="${}">
    <tr>
        <td><c:out value="${koeBruker.mail}"/></td>
        <td><c:out value="${koeBruker.}"/>/td>
    </tr>
    </c:forEach>
    </tbody>
</table>

</div>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-1.10.2.js"/>"></script>
