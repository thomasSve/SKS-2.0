<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Eirik
  Date: 20.01.14
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="col-md-4">
    <form:form method="POST" modelAttribute="delemne" action="lagDelemne">
        <h2>Opprett delemne</h2>

        <p style="color: green"><strong>${emnerett}</strong></p>

        <p style="color: red"><strong>${delemnefeil}</strong></p>
        <p style="color: red"><strong>${delemneSQLfeil}</strong></p>

        <div class="form-group">
            <label for="delEmneNavn">Delemnenavn:</label>
            <form:input path="delEmneNavn" id="delEmneNavn" class="form-control" required="true"/>
        </div>
        <div class="form-group">
            <label for="nr">Delemnenr:</label>
            <form:input path="nr" id="nr" class="form-control" required="true"/>
        </div>
        <div class="form-group">
            <label for="semester">Semester:</label>

            <form:radiobutton path="semester" id="semester" class="btn-primary btn" value="h"/>Høst&nbsp;&nbsp;
            <form:radiobutton path="semester" id="semester" class="btn-primary btn" value="v"/>Vår

        </div>
        <div class="btn-input-group">
            <input type="submit" class="btn btn-primary btn-block" value="Legg til"/>
        </div>
    </form:form>
</div>
