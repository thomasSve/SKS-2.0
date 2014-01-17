<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 09.01.14
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-4">

    <form:form method="POST" modelattribute="passord" action="skiftPassord.htm" commandName="passord">
        <h2 class="form-header">Endre Passord</h2>
        <div class="form-group">
            <label for="gammeltPassord">N&aring;værende Passord</label>
            <form:password class="form-control" id="gammeltPassord" placeholder="Gammelt passord" path="gammeltPassord"
                           autofocus="true"/>
            <form:errors path="gammeltPassord"/>

        </div>

        <div class="form-group">
            <label for="nyttPassord">Nytt Passord</label>
            <form:password class="form-control" id="nyttPassord" placeholder="Nytt passord" path="nyttPassord"/>
            <form:errors path="nyttPassord"/>
            <form:password class="form-control" id="bNyttPassord" placeholder="Bekreft passord" path="bekreftPassord"/>
            <form:errors path="bekreftPassord"/>
        </div>

        <input class="btn btn-md btn-primary btn-block" type="submit" value="Endre passord">
    </form:form>
</div>
<script src="<c:url value="/resources/js/endrePassord.js"/>"></script>
