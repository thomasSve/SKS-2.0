<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 09.01.14
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-4">

    <form:form role="form" method="POST" modelattribute="" action="endrePassord">
        <h2 class="form-header">Endre Passord</h2>

        <div class="form-group">
            <label for="gammeltPassord">N&aring;værende Passord</label>
            <input class="form-control" type="password" id="gammeltPassord" placeholder="Gammelt passord" />
        </div>

        <div class="form-group">
            <label for="nyttPassord">Nytt Passord</label>
            <input class="form-control" type="password" id="nyttPassord" placeholder="Nytt passord" />
            <input class="form-control" type="password" id="bNyttPassord" placeholder="Bekreft passord" />
        </div>

        <button class="btn btn-md btn-primary btn-block" type="submit">Endre passord</button>
    </form:form>
</div>
